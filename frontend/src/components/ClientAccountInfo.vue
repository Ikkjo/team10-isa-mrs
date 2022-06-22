<template>
    <div id="account-info" >
        <ClientNavBar id="nav"/>
        <div class="info" >
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <InfoItem icon="account_box" label="Account type" :text="user.role" buttonText="Register new account" @editClicked="registerNewAccountClicked" :useSlot="false"/>
                    <InfoItem icon="account_circle" label="Full name" :text="user.firstName+' '+user.lastName" buttonText="Edit" @save="saveFullName" @cancelClicked="cancelEdit" :saveDisabled="$v.user.firstName.$invalid || $v.user.lastName.$invalid ">
                        <template slot="edit">
                            <div class="form-control name-form">
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
                            <div class="form-control name-form">
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
                    <InfoItem icon="contact_phone" label="Phone number" :text="user.phoneNumber" buttonText="Edit" @save="savePhoneNumber" @cancelClicked="cancelEdit" :saveDisabled="phoneNumberTmp && !phoneNumberTmp.isValid">
                        <template slot="edit">
                            <div class="form-control block-form">
                                <PhoneNumberInput
                                    class="phone-number" 
                                    v-model="user.phoneNumber" 
                                    default-country-code="RS" 
                                    size="lg"
                                    color="#f0a500"
                                    valid-color="green"
                                    error-color="red"
                                    @update="updatePhone"/>
                            </div>
                        </template>
                    </InfoItem>
                    <InfoItem icon="loyalty" label="Loyalty points" :text="user.loyaltyPoints.toString() + ' (' +  user.loyaltyStatus + ')'" buttonText="" :useSlot="false"/>
                    <InfoItem icon="fmd_bad" label="Penalty points" :text="user.penaltyPoints.toString()" buttonText="" :useSlot="false"/>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <InfoItem icon="email" label="Email" :text="user.email" buttonText="" :useSlot="false">
                    </InfoItem>
                    <InfoItem icon="password" label="Password" text="*********" buttonText="Change" @save="savePassword" :saveDisabled="$v.currentPassword.$invalid || $v.newPassword.$invalid || $v.confirmNewPassword.$invalid">
                        <template slot="edit">
                            <div class="wrapper">
                                <div class="form-control">
                                    <label for="password">Current Password</label>
                                    <input type="password" 
                                        v-model="currentPassword" 
                                        name="password"  
                                        @focus="inFocus('currentPassword')"
                                        @blur="outFocus('currentPassword')" 
                                        :class="getClass('currentPassword')"
                                        :placeholder="getPlaceholder('currentPassword')">
                                </div>
                                <div class="form-control">
                                    <label for="password">New Password</label>
                                    <input type="password" 
                                        v-model="newPassword" 
                                        name="password"  
                                        @focus="inFocus('newPassword')"
                                        @blur="outFocus('newPassword')" 
                                        :class="getClass('newPassword')"
                                        :placeholder="getPlaceholder('newPassword', 'At least 8 characters')">
                                    <div class="alert-info" 
                                        v-if="!this.infocus['newPassword'] && !($v.newPassword.minLength && $v.newPassword.maxLength)">
                                        Must have 8-30 characters.
                                    </div>
                                </div>
                                <div class="form-control">
                                    <label for="confirm-password">Confirm new password</label>
                                    <input type="password" 
                                        v-model="confirmNewPassword" 
                                        name="confirm-password" 
                                        @focus="inFocus('confirmNewPassword')" 
                                        @blur="outFocus('confirmNewPassword')" 
                                        :class="getClass('confirmNewPassword')" 
                                        :placeholder="getPlaceholder('confirmNewPassword')">
                                    <div class="alert-info" 
                                        v-if="!this.infocus['confirmNewPassword'] && !$v.confirmNewPassword.sameAsPassword">
                                        Passwords don't match.
                                    </div>
                                </div>
                            </div>
                        </template>
                    </InfoItem>
                    <InfoItem icon="info"
                    label="Account status"
                    text="Active"
                    buttonText="Delete account"
                    style="color: red;"
                    editButton="Request Deletion"
                    :saveDisabled="$v.deletionReason.$invalid"
                    @save="sendDeleteRequest"
                    @cancelClicked="deletionReason=''"
                    >
                    <template slot="edit">
                        <div class="form-control block-form">
                            <label for="deletionReason" class="block-label">Why do you want to delete your account?</label>
                            <textarea 
                                name="deletionReason" 
                                id="deletionReason" 
                                cols="30" rows="4" 
                                v-model="deletionReason"
                                @focus="inFocus('deletionReason')" 
                                @blur="outFocus('deletionReason')" 
                                :class="getClass('deletionReason')" 
                                :placeholder="getPlaceholder('deletionReason')"
                            />
                            <div class="alert-info" 
                                v-if="!isFocused('deletionReason') 
                                && !($v.deletionReason.minLength 
                                && $v.deletionReason.maxLength)"
                            >
                                Must be between 5 to 200 characters.
                            </div>
                        </div>
                        </template>
                    </InfoItem>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ClientNavBar from "./ClientNavBar.vue";
import InfoItem from "@/components/InfoItem.vue"
import PhoneNumberInput from 'vue-phone-number-input'
import axios from "axios"
import { required, minLength, maxLength, sameAs, alpha } from 'vuelidate/lib/validators'
export default {
    name: 'ClientAccountInfo',
    components: {
        ClientNavBar,
        PhoneNumberInput,
        InfoItem
    },
    data() {
        return {
            user: {
                phoneNumber: '',
                loyaltyPoints: 0,
                penaltyPoints: 0
            },
            phoneNumberTmp: null,
            userCopy: {
            },
            currentPassword: '',
            newPassword: '',
            confirmNewPassword: '',
            deletionReason: '',
            deletionRequestSent: false,
            infocus: {
                firstName: true,
                lastName: true,
                username: true,
                email: true,
                currentPassword: true,
                newPassword: true,
                confirmNewPassword: true,
                registrationReason: true,
                role: true,
                deletionReason: true,
            },
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
        },
        currentPassword: {
                required
            },
        newPassword: {
            required,
            minLength: minLength(8),
            maxLength: maxLength(30),
        },
        confirmNewPassword: {
            required,
            sameAsPassword: sameAs("newPassword")
        },
        deletionReason: {
            required,
            minLength: minLength(5),
            maxLength: maxLength(200)
        }
        
    },
    methods: {
        saveFullName() {
            if (this.user.firstName !== this.userCopy.firstName) {
                axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/user/update/firstname',
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
                        this.userCopy.firstName = response.data

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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/user/update/lastname',
                data: this.user.lastName,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    if (response.status >= 400)
                        alert("Last Name Invalid")
                    else
                        this.userCopy.lastName = response.data
                })
                .catch((error) => {
                    this.user.lastName = this.userCopy.lastName
                    alert("Last Name Invalid")
                    console.log(error);
                }) 
            }
        },
        savePhoneNumber() {
            if (this.phoneNumberTmp.formattedNumber !== this.userCopy.phoneNumber) {
                axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/user/update/phone-number',
                data: this.phoneNumberTmp.formattedNumber,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Phone Number Invalid")
                    else
                        this.userCopy.phoneNumber = response.data

                })
                .catch((error) => {
                    this.user.phoneNumber = this.userCopy.phoneNumber
                    alert("Phone Number Invalid")
                    console.log(error);
                }) 
            }
        },
        savePassword(){
            if (this.newPassword !== this.currentPassword) {
                axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/user/update/password',
                data: { currentPassword: this.currentPassword, newPassword: this.newPassword },
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Password Invalid")
                    else
                        alert("Password changed successfully")
                })
                .catch((error) => {
                    alert("Password Invalid")
                    console.log(error);
                }) 
            }
            else
                alert("Current and new password are the same")
        },
        registerNewAccountClicked() {
            this.$router.push({ name: 'client-registration' })
        },
        sendDeleteRequest() {
            if (this.deletionRequestSent)
                alert("Deletion request already sent")
            else {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/user/delete',
                    data: this.deletionReason,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Failed to send deletion request")
                        else {
                            alert("Deletion request sent succesfully")
                            this.deletionRequestSent = true
                        }
                    })
                    .catch((error) => {
                        alert("Failed to send deletion request")
                        console.log(error);
                    })
            }
        },
        updatePhone(phoneNumber){
            this.phoneNumberTmp = phoneNumber
        },
        cancelEdit() {
            this.user = JSON.parse(JSON.stringify(this.userCopy))
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
            let cls = ''
            if (field in this.$v.user)
                cls = !this.isFocused(field) && this.$v.user[field].$invalid ? 'alert' : '';
            else
                cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = ''
            if (field in this.$v.user)
                placeholder = !this.isFocused(field) && this.$v.user[field].$invalid ? 'Required' : defaultPlaceholder;
            else
                placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            
            return placeholder;
        }
    },
    created() {
        axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/client",
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

<style scoped>
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
    margin-top: 10px;
    margin-bottom: 5px;
}

.name-form {
    max-width: 50%;
}


.form-control input,
.form-control textarea {
    width: 95% !important;
}

.phone-form {
    display: flex;
}

.date-form {
    display: block;
}

label {
    font-size: 0.9rem;
    color: grey;
    display: inline-block;
}

.block-form {
    display: block;
    margin-top: 10px;
    margin-bottom: 5px;
}

.wrapper {
    display: flex;
}

</style>