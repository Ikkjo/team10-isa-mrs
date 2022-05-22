<template>
    <div id="account-info">
        <BusinessClientNavBar id="nav"/>
        <div class="info">
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <InfoItem icon="account_box" label="Account type" :text="user.userRole" buttonText="Register new account" @editClicked="registerNewAccountClicked" :useSlot="false"/>
                    <InfoItem icon="account_circle" label="Full name" :text="user.firstName+' '+user.lastName" buttonText="Edit" @save="saveFullName" :saveDisabled="$v.user.firstName.$invalid || $v.user.lastName.$invalid ">
                        <template slot="edit">
                            <div class="form-control">
                                <label for="first-name">First Name</label>
                                <input type="text"
                                    v-model="user.firstName"
                                    name="first-name" 
                                    @focus="inFocus('firstName')" 
                                    @blur="outFocus('firstName')" 
                                    :class="getClass('firstName')" 
                                    :placeholder="getPlaceholder('firstName', user.firstName)">
                                <div class="alert-info" 
                                    v-if="!isFocused('firstName') 
                                    && !$v.user.firstName.minLength 
                                    || !$v.user.firstName.maxLength
                                    || !$v.user.firstName.alpha"
                                    >
                                    Between 2-20 characters, no numbers.
                                </div>
                            </div>
                            <div class="form-control">
                                <label for="last-name">Last Name</label>
                                <input type="text" 
                                    v-model="user.lastName" 
                                    name="last-name" 
                                    @focus="inFocus('lastName')" 
                                    @blur="outFocus('lastName')" 
                                    :class="getClass('lastName')" 
                                    :placeholder="getPlaceholder('lastName', user.lastName)">
                                <div class="alert-info" 
                                    v-if="!isFocused('lastName') && 
                                    !($v.user.lastName.minLength 
                                    && $v.user.lastName.maxLength
                                    && $v.user.lastName.alpha)"
                                    >
                                    Between 2-20 characters, no numbers.
                                </div>
                            </div>
                        </template>
                    </InfoItem>
                    <InfoItem icon="contact_phone" label="Phone number" :text="user.phoneNumber" buttonText="Edit"/>
                    <InfoItem icon="house" label="Address" :text="user.address.address+', '+user.address.city+', '+user.address.country" buttonText="Edit"/>
                    <InfoItem icon="calendar_month" label="Date of birth" :text="user.dateOfBirth" buttonText="Edit"/>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <InfoItem icon="email" label="Email" :text="user.email" buttonText="Change"/>
                    <InfoItem icon="password" label="Password" text="*********" buttonText="Change"/>
                    <InfoItem icon="info" label="Account status" text="Active" buttonText="Deactivate" style="color: red;"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import InfoItem from "@/components/InfoItem.vue"
import axios from "axios"
import { required, minLength, maxLength, sameAs, email, alpha } from 'vuelidate/lib/validators'
export default {
    name: 'AccountInfo',
    components: {
        BusinessClientNavBar,
        InfoItem
    },
    data() {
        return {
            user: {
                address: {},
            },
            userCopy: {
                address: {}
            },
            infocus: {
                firstName: true,
                lastName: true,
                username: true,
                email: true,
                password: true,
                confirmPassword: true,
                registrationReason: true,
                role: true,
                dateOfBirth: true,
            }
        }
    },
    validations:{
        user: {
            firstName: {
                required,
                minLength: minLength(2),
                maxLength: maxLength(20),
                alpha
            },
            lastName: {
                required,
                minLength: minLength(2),
                maxLength: maxLength(20),
                alpha
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
            country: {
                required
            },
        }
        
    },
    methods: {
        saveFullName() {
            if (this.user.firstName !== this.userCopy.firstName) {
                axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/update/firstname',
                data: this.user.firstName,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("First Name Invalid")
                    else
                        this.userCopy.firstName = this.user.firstName

                })
                .catch((error) => {
                    this.user.firstName = this.userCopy.firstName
                    alert("First Name Invalid")
                    console.log(error);
                }) 
            }
            if (this.user.lastName !== this.userCopy.lastName) {
                axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/update/lastname',
                data: this.user.lastName,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    if (response.status >= 400)
                        alert("Last Name Invalid")
                    else
                        this.userCopy.lastName = this.user.lastName
                })
                .catch((error) => {
                    this.user.lastName = this.userCopy.lastName
                    alert("Last Name Invalid")
                    console.log(error);
                }) 
            }
        },
        registerNewAccountClicked() {
            this.$router.push({ name: 'business-client-register' })
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
            let cls = !this.isFocused(field) && this.$v.user[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) && this.$v.user[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },
    created() {
        axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/business-client",
          { headers: { Authorization: 'Bearer ' + window.localStorage.getItem("jwt") }
          })
          .then((response) => {
            console.log(response.data)
            this.user = response.data
            this.userCopy = JSON.parse(JSON.stringify(response.data))
          })
          .catch(function(error) {
              console.log(error)
          })
    }

}
</script>

<style>
#account-info {
    max-width: 100%;
}

h2 {
    margin-top: 20px;
}

.info {
    margin-top: calc(var(--nav-height) + 40px);
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.info-items > * {
    padding: 20px 0;
    border-bottom: 1px solid #EBEBEB;
}

.info-section {
    width: 600px;
    min-width: 300px;
    max-width: 100%;
}

.form-control {
    display: inline-block;
    max-width: 50%;
}


.form-control input {
    width: 95% !important;
}

</style>