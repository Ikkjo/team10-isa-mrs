<template>
    <div class="wrapper">
        <h1>Create New Administrator</h1>
        <div class="form-wrapper">
            <form @submit.prevent="createAdminPressed">
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
                        v-if="!isFocused('firstName') && !($v.firstName.minLength && $v.firstName.maxLength)">
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
                        v-if="!isFocused('lastName') && !($v.lastName.minLength && $v.lastName.maxLength)">
                        Last name must be 2 to 20 characters long.
                    </div>
                </div>
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
                        v-if="!isFocused('email') && !$v.email.email">
                        Incorrect email format.
                    </div>
                </div>
                <div class="form-control">
                    <label for="password">Password</label>
                    <input type="password" 
                        v-model="password" 
                        name="password"  
                        @focus="inFocus('password')"
                        @blur="outFocus('password')"
                        :class="getClass('password')"
                        :placeholder="getPlaceholder('password', 'Password')">
                    <div class="alert-info" 
                        v-if="!isFocused('password') 
                        && !($v.password.minLength 
                        && $v.password.maxLength)"
                        >
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
                        v-if="!isFocused('confirmPassword') 
                        && !$v.confirmPassword.sameAsPassword"
                        >
                        Passwords don't match.
                    </div>
                </div>
                <div class="btn-div">
                    <button class="btn"
                        :disabled="$v.$invalid">
                        Create Admin
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import { required, minLength, maxLength, email, sameAs } from 'vuelidate/lib/validators'
import axios from 'axios';

export default {
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            confirmPassword: '',
            infocus: {
                firstName: true,
                lastName: true,
                email: true,
                password: true,
                confirmPassword: true,
            },
        }
    },
    methods: {
        createAdminPressed() {
            let adminRegistrationDto = {
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email,
                password: this.password,
            };
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/create-admin',
                data: adminRegistrationDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                alert("Admin successfully added!");
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
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
}
</script>

<style>
.form-wrapper {
    width: 100%;
    display: flex;
    align-content: center;
    justify-content: center;
} 

.form-control {
    margin: 15px 0px;
}

.form-control input {
    width: 100%;
    height: 48px;
}

.form-control label {
    width: 100%;
}

.form-control {
    display: block;
}
.btn-div {
    margin-top: 30px;
    display:flex;
    justify-content: center;
}

</style>