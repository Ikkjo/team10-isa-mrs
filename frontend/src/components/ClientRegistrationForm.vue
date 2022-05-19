<template>
    <form id="registration-form" @submit.prevent="signUpPressed">
        <div class="wrapper">
            <div class="form-control">
                <label for="first-name">First Name</label>
                <input type="text"
                    v-model="firstName" 
                    name="first-name" 
                    @focus="inFocus('firstName')" 
                    @blur="outFocus('firstName')" 
                    :class="getClass('firstName')" 
                    :placeholder="getPlaceholder('firstName')">
                <div class="alert-info" 
                    v-if="!this.infocus['firstName'] && !($v.firstName.minLength && $v.firstName.maxLength)">
                    First name must be 2 to 20 characters long.
                </div>
            </div>
            <div class="form-control">
                <label for="last-name">Last Name</label>
                <input type="text" 
                    v-model="lastName" 
                    name="last-name" 
                    @focus="inFocus('lastName')" 
                    @blur="outFocus('lastName')" 
                    :class="getClass('lastName')" 
                    :placeholder="getPlaceholder('lastName')">
                <div class="alert-info" 
                    v-if="!this.infocus['lastName'] && !($v.lastName.minLength && $v.lastName.maxLength)">
                    Last name must be 2 to 20 characters long.
                </div>
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="email">Email</label>
                <input type="text" 
                    v-model="email" 
                    name="email" 
                    @focus="inFocus('email')" 
                    @blur="outFocus('email')" 
                    :class="getClass('email')" 
                    :placeholder="getPlaceholder('email', 'example@rentr.com')">
                <div class="alert-info" 
                    v-if="!this.infocus['email'] && !$v.email.email">
                    Incorrect email format.
                </div>
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
                    @update="updatePhone"/>
                <div class="alert-info" 
                    v-if="phone && !phone.isValid">
                    Incorrect phone number.
                </div>
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="password">Password</label>
                <input type="password" 
                    v-model="password" 
                    name="password"  
                    @focus="inFocus('password')"
                    @blur="outFocus('password')" 
                    :class="getClass('password')"
                    :placeholder="getPlaceholder('password', 'At least 8 characters')">
                <div class="alert-info" 
                    v-if="!this.infocus['password'] && !($v.password.minLength && $v.password.maxLength)">
                    Password must be 8 to 30 characters long.
                </div>
            </div>
            <div class="form-control">
                <label for="confirm-password">Confirm password</label>
                <input type="password" 
                    v-model="confirmPassword" 
                    name="confirm-password" 
                    @focus="inFocus('confirmPassword')" 
                    @blur="outFocus('confirmPassword')" 
                    :class="getClass('confirmPassword')" 
                    :placeholder="getPlaceholder('confirmPassword')">
                <div class="alert-info" 
                    v-if="!this.infocus['confirmPassword'] && !$v.confirmPassword.sameAsPassword">
                    Passwords don't match.
                </div>
            </div>
        </div>
        <div class="btn-div">
            <button class="btn"
                :disabled="$v.$invalid || (!phone || !phone.isValid) 
                    || !dateOfBirth.isValid || role==='' || country===''">
                Create Account
            </button>
            <div class="already-registered">Already have an account? <router-link to="/login">Log in</router-link></div>
            <div class="business-registration">Want to rent your property? <router-link to="/business-user/register">Register a business account</router-link></div>
        </div>
    </form>
</template>
<script>
import { required, minLength, maxLength, sameAs, email } from 'vuelidate/lib/validators'
import axios from 'axios';

export default {
    name: 'ClientRegistrationForm',
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            phone: null,
            password: '',
            confirmPassword: '',
            role: 'CLIENT',
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
    },
    methods: {
        signUpPressed(){
            let clientRegistrationRequestDTO = {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    password: this.password,
                    role: 'CLIENT',
                    phone: this.phone.formattedNumber,
                }
            console.log(clientRegistrationRequestDTO)
            axios
                .post(process.env.VUE_APP_BASE_URL+"/api/v1/registration/client", clientRegistrationRequestDTO)
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
    margin-top: 20px;
}

.btn {
    width: 40%;
}

.btn-div p {
    margin-top: 0px;
}

.alert {
    transition: 0.5s;
    border-color: red !important;
    border-width: 2px;
}

.alert-info {
    position: absolute;
    transition: 0.05s;
    color: red !important;
    font-size: 0.9rem;
}

.already-registered {
    font-size: 0.9rem !important;
    margin-top: 5px;
}

.business-registration {
    font-size: 0.9rem !important;
    margin-top: 5px;
}
</style>