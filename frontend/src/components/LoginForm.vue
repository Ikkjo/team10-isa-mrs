<template>
    <form id="login-form" @submit.prevent="logInPressed">
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
            <label for="password">Password</label>
            <input type="password" 
                v-model="password" 
                name="password"  
                @focus="inFocus('password')"
                @blur="outFocus('password')" 
                :class="getClass('password')"
                :placeholder="getPlaceholder('password', 'Password')">
            <div class="alert-info" 
                v-if="!this.infocus['password'] && !( $v.password.length > 0)">
            </div>
        </div>
        <div class="btn-div">
            <button class="btn"
                :disabled="$v.invalid">
                Log In
            </button>
            <div class="not-registered">Don't have an account yet? <router-link to="/client/register">Register an account</router-link></div>
        </div>
    </form>
</template>
<script>
import { required, maxLength, email } from 'vuelidate/lib/validators'
import axios from 'axios';

export default {
    name: 'LoginFrom',
    data() {
        return {
            email: '',
            password: '',
            infocus: {
                email: true,
                password: true,
            }
        }
    },
    validations:{
        email: {
            required,
            email
        },
        password: {
            required,
            maxLength: maxLength(30)
        },
    },
    methods: {
        logInPressed(){
            let loginDto = {
                    username: this.email,
                    password: this.password
                }
            console.log(loginDto)
            axios
                .post(process.env.VUE_APP_BASE_URL+"/api/v1/login", loginDto)
                .then(function(response) {
                    console.log(response)
                    window.localStorage.setItem("jwt", response.data)
                    // notify that awaiting accept
                })
                .catch(function(error) {
                    console.log(error);
                    // is email or password invalid
                })
        },
        updateYear(event){
            this.dateOfBirth.year = event;
            this.isDateValid();
            this.dateOfBirth.focused = true;
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
#login-form {
    display: flex;
    flex-direction: column;
}

.form-control {
    margin: 15px 0px;
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
    width: 100%;
    height: 100%;
    margin-top: auto;
    margin-bottom: auto;
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

.not-registered {
    font-size: 0.9rem !important;
    margin-top: 5px;
}
</style>