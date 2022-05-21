<template>
    <div id="account-info">
        <BusinessClientNavBar id="nav"/>
        <div class="info">
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <InfoItem iconClass="material-icons" icon="account_box" label="Account type" :text="user.userRole" buttonText="Register new account"/>
                    <InfoItem iconClass="material-icons" icon="account_circle" label="Full name" :text="user.firstName+' '+user.lastName" buttonText="Edit"/>
                    <InfoItem iconClass="material-icons" icon="contact_phone" label="Phone number" :text="user.phoneNumber" buttonText="Edit"/>
                    <InfoItem iconClass="material-icons" icon="house" label="Address" :text="user.address.address+', '+user.address.city+', '+user.address.country" buttonText="Edit"/>
                    <InfoItem iconClass="material-icons" icon="calendar_month" label="Date of birth" :text="user.dateOfBirth" buttonText="Edit"/>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <InfoItem iconClass="material-icons" icon="email" label="Email" :text="user.email" buttonText="Change"/>
                    <InfoItem iconClass="material-icons" icon="password" label="Password" text="*********" buttonText="Change"/>
                    <InfoItem iconClass="material-icons" icon="info" label="Account status" text="Active" buttonText="Deactivate" style="color: red;"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import InfoItem from "@/components/InfoItem.vue"
import axios from "axios"
export default {
    name: 'AccountInfo',
    components: {
        BusinessClientNavBar,
        InfoItem
    },
    data() {
        return {
            user: {},
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

</style>