<template>
    <form id="registration-form" @submit.prevent="signUpPressed">
        <div class="wrapper">
            <div class="form-control">
                <label for="first-name">First Name</label>
                <input type="text" v-model="firstName" name="first-name" @focus="inFocus('firstName')" @blur="outFocus('firstName')" :class="getClass('firstName')" :placeholder="getPlaceholder('firstName')" pattern="[a-zA-Z\.]+$">
            </div>
            <div class="form-control">
                <label for="last-name">Last Name</label>
                <input type="text" v-model="lastName" name="last-name" @focus="inFocus('lastName')" @blur="outFocus('lastName')" :class="getClass('lastName')" :placeholder="getPlaceholder('lastName')" pattern="[a-zA-Z\.]+$">
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="email">Email</label>
                <input type="text" v-model="email" name="email" @focus="inFocus('email')" @blur="outFocus('email')" :class="getClass('email')" :placeholder="getPlaceholder('email', 'example@rentr.com')">
            </div>
            <div class="form-control">
                <label for="phone-number">Phone Number</label>
                <vue-phone-number-input
                    class="phone-number" 
                    v-model="phoneTmp" 
                    default-country-code="RS" 
                    size="lg"
                    color="#f0a500"
                    valid-color="green"
                    error-color="red"
                    @update="updatePhone"
                 />
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="password">Password</label>
                <input type="password" v-model="password" name="password"  @focus="inFocus('password')" @blur="outFocus('password')" :class="getClass('password')" :placeholder="getPlaceholder('password', 'At least 8 characters')">
            </div>
            <div class="form-control">
                <label for="confirm-password">Confirm password</label>
                <input type="password" v-model="confirmPassword" name="confirm-password" @focus="inFocus('confirmPassword')" @blur="outFocus('confirmPassword')" :class="getClass('confirmPassword')" :placeholder="getPlaceholder('confirmPassword')">
            </div>
        </div>
        <AddresInput :address="address" :city="city" :country="country"/>
        <div class="wrapper">
            <div class="form-control">
                <label for="datepicker">Date of Birth</label>
                <DropdownDatepicker
                    id="datepicker" 
                    name="datepicker" 
                    displayFormat="dmy" 
                    :minAge="18"
                    :onDayChange="updateDay"
                    :onMonthChange="updateMonth"
                    :onYearChange="updateYear"
                    />
            </div>
            <div class="form-control">
                <label for="role" class="block-label">Account Type</label>
                <select name="role" id="role" v-model="role">
                    <option value="">Choose account type</option>
                    <option value="HOUSE_OWNER">Vacation Home Owner</option>
                    <option value="SHIP_OWNER">Ship Owner</option>
                    <option value="FISHING_INSTRUCTOR">Fishing Instructor</option>
                </select>
            </div>
        </div>
        <div class="wrapper" id="registration-reason">
             <div class="form-control">
                <label for="description" class="block-label">Reason for Registration</label>
                <textarea name="description" id="description" cols="30" rows="4" v-model="registrationReason" @focus="inFocus('registrationReason')" @blur="outFocus('registrationReason')" :class="getClass('registrationReason')" :placeholder="getPlaceholder('registrationReason', 'Tell us a few reasons why you want to join...')"></textarea>
            </div>
        </div>
        <div class="btn-div">
            <button class="btn">Create Account</button>
            <p>Already have an account? <a href="/login">Log in</a></p>
        </div>
    </form>
</template>
<script>
import { required, minLength, maxLength, sameAs, email } from 'vuelidate/lib/validators'
import DropdownDatepicker from 'vue-dropdown-datepicker/src/dropdown-datepicker.vue';
import AddresInput from './AddressInput.vue';
import axios from 'axios';

export default {
    name: 'RegistrationFrom',
    components: {
        DropdownDatepicker,
        AddresInput,
    },
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            phone: null,
            password: '',
            confirmPassword: '',
            address: '',
            city: '',
            country: '',
            coutries: [],
            registrationReason: '',
            role: '',
            phoneTmp: '',
            dateOfBirth:{
                day: null,
                month: null,
                year: null,
            },
            infocus: {
                firstName: true,
                lastName: true,
                username: true,
                email: true,
                password: true,
                confirmPassword: true,
                registrationReason: true,
            }
        }
    },
    validations:{
        firstName: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(20)
        },
        lastName: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(20)
        },
        email: {
            required,
            email
        },
        password: {
            required,
            minLength: minLength(8),
            maxLength: maxLength(30)
        },
        confirmPassword: {
            required,
            sameAsPassword: sameAs("password")
        },
        address: {
            required,
            minLength: minLength(5),
            maxLength: maxLength(40)
        },
        city: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(40)
        },
        registrationReason: {
            required,
            minLength: minLength(20),
            maxLength: maxLength(200),
        }
    },
    created() {
        this.countries = ['USA', 'Serbia']
    },
    methods: {
        signUpPressed(){
            let registrationRequestDTO = {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    password: this.password,
                    role: this.role,
                    phone: this.phone.formattedNumber,
                    dateOfBirth: ""+this.dateOfBirth.day+"."+this.dateOfBirth.month+"."+this.dateOfBirth.year+".",
                    address: this.address,
                    city: this.city,
                    country: this.country,
                    registrationReason: this.registrationReason
                }
            console.log(registrationRequestDTO)
            axios
                .post(process.env.VUE_APP_BASE_URL+"/api/v1/registration", registrationRequestDTO)
                .then(function(response) {
                    console.log(response)
                    // notify that awaiting accept
                })
                .catch(function(error) {
                    console.log(error);
                    // is email taken
                    // is phone taken
                })
        },
        updatePhone(event){
            this.phone = event
        },
        updateDay(event){
            this.dateOfBirth.day = event;
        },
        updateMonth(event){
            this.dateOfBirth.month = event;
        },
        updateYear(event){
            this.dateOfBirth.year = event
        },
        isFocused(field) {
            return this.infocus[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        getClass(field) {
            let cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },


}
</script>

<style scoped>

.form-control {
    margin: 15px 10px 15px 0px;
}

.wrapper {
    display: grid;
    grid-template-columns: 50% 50%;
}

.address-wrapper {
    grid-template-columns: 50% 30% 20%;
}

textarea {
    height: auto;
    width: 100%;
}

.form-control input, select {
    width: 100%;
    height: 48px;
}

.form-control label {
    width: 100%;
}

.form-control {
    display: block;
}

#datepicker {
    display: grid;
    grid-template-columns: 33.3% 33.3% 33.3%;
}

#registration-reason {
    display: block;
}

.btn-div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.btn {
    width: 40%;
}

.btn-div p {
    margin-top: 10px;
}

.alert {
    transition: 0.5s;
    border-color: red !important;
    border-width: 2px;
}

</style>