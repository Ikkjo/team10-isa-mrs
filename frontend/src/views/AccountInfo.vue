<template>
    <div id="account-info">
        <BusinessClientNavBar id="nav"/>
        <div class="info">
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <PersonalInfoItem iconClass="material-icons" icon="account_box" label="Account type" :text="user.role" buttonText="Register new account"/>
                    <PersonalInfoItem iconClass="material-icons" icon="account_circle" label="Full name" :text="user.name+' '+user.lastname" buttonText="Edit"/>
                    <PersonalInfoItem iconClass="material-icons" icon="contact_phone" label="Phone number" :text="user.phoneNumber" buttonText="Edit"/>
                    <PersonalInfoItem iconClass="material-icons" icon="house" label="Address" :text="user.address" buttonText="Edit"/>
                    <PersonalInfoItem iconClass="material-icons" icon="calendar_month" label="Date of birth" :text="user.dateOfBirth" buttonText="Edit"/>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <PersonalInfoItem iconClass="material-icons" icon="email" label="Email" :text="user.email" buttonText="Change"/>
                    <PersonalInfoItem iconClass="material-icons" icon="password" label="Password" text="*********" buttonText="Change"/>
                    <PersonalInfoItem iconClass="material-icons" icon="info" label="Account status" :text="user.status" buttonText="Deactivate" style="color: red;"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import PersonalInfoItem from "@/components/PersonalInfoItem.vue"
export default {
    name: 'AccountInfo',
    components: {
        BusinessClientNavBar,
        PersonalInfoItem
    },
    data() {
        return {
            user: null,
        }
    },
    mounted() {
        this.user = localStorage.getItem('user')
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