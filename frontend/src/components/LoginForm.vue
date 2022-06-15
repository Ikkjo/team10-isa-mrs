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
            <div class="not-registered">Don't have an account yet? <router-link :to="{name: 'client-registration'}">Register an account</router-link></div>
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
            axios
                .post(process.env.VUE_APP_BASE_URL+"/api/v1/login", loginDto)
                .then((response) => {
                    window.localStorage.setItem("jwt", response.data);
                    let jwtData = response.data.split('.')[1];
                    let decodedJwtJsonData = window.atob(jwtData);
                    let decodedJwtData = JSON.parse(decodedJwtJsonData);
                    window.localStorage.setItem("role", decodedJwtData.auth);
                    if (window.localStorage.role === "UNVERIFIED_ADMIN"){
                        this.$router.push({name: 'admin-verification'})
                    }
                    else if (['MAIN_ADMIN', 'ADMIN'].includes(window.localStorage.role)){
                        this.$router.push({name: 'admin-panel'})
                    }
                    else {
                        this.$router.push({name: 'homepage'})
                    }
                })
                .catch((error) => {
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
    color: red !important;
}

.not-registered {
    font-size: 0.9rem !important;
    margin-top: 5px;
}
</style>