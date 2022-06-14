<template>
    <div id="absoluteCenteredDiv">
        <form class="box">
            <h1 v-if="user">{{user.firstName}}, set a password</h1>
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
            <div class="button-wrapper">
                <button type="button" class="btn" @click="setNewPassword">Set new password</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
import { required, minLength, maxLength, sameAs } from 'vuelidate/lib/validators'
export default {
    data() {
        return {
            user: null,
            password: '',
            confirmPassword: '',
            infocus: {
                password: true,
                confirmPassword: true,
            }
        }
    },
    validations: {
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
        },
        setNewPassword() {
            let dataDto = {
                newPassword: this.password,
            }
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/verify-admin',
                data: dataDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(function(response) {
                window.localStorage.setItem('role', response.data.role);
                this.$router.push({name: 'admin'})
            }).catch(function() {
                alert("Something failed :(");
            })
        }
    },
    created () {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                if(response.data.role !== "UNVERIFIED_ADMIN")
                    this.$router.push({name: 'admin'})
            }).catch(() => {
                this.$router.push({name: 'homepage'})
            })
    },
}
</script>

<style>
h1{
    font-size:1.5em;
    color:#525252;
    margin-bottom: 15px;
}
.box{
    background:white;
    width:400px;
    border-radius:6px;
    margin: 0 auto 0 auto;
    padding:20px;
    border-radius: 15px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}

.form-control {
    margin-bottom: 20px;
    display: block !important;
}

input {
    display: block;
    width: 100%;
}

.button-wrapper {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}
#absoluteCenteredDiv{
    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    width:400px;
    height: 300px;
}
</style>