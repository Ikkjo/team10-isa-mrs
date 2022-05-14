<template>
    <div id="account-info">
        <business-user-nav id="nav"></business-user-nav>
        <div class="info">
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <personal-info-item iconClass="material-icons" icon="account_box" label="Account type" text="Rental House Owner" buttonText="Register new account"/>
                    <personal-info-item iconClass="material-icons" icon="account_circle" label="Full name" text="Imenko Prezimic" buttonText="Edit"/>
                    <personal-info-item iconClass="material-icons" icon="contact_phone" label="Phone number" text="+381 66 127 8217" buttonText="Edit"/>
                    <personal-info-item iconClass="material-icons" icon="house" label="Address" text="Ulica b.b., Grad, Drzava" buttonText="Edit"/>
                    <personal-info-item iconClass="material-icons" icon="calendar_month" label="Date of birth" text="12.06.1998." buttonText="Edit"/>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <personal-info-item iconClass="material-icons" icon="email" label="Email" text="example@rentr.com" buttonText="Change"/>
                    <personal-info-item iconClass="material-icons" icon="password" label="Password" text="*********" buttonText="Change"/>
                    <personal-info-item iconClass="material-icons" icon="info" label="Account status" text="Active" buttonText="Deactivate" style="color: red;"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import BusinessUserNav from "@/components/BusinessUserNav.vue"
import PersonalInfoItem from "@/components/PersonalInfoItem.vue"
export default {
    name: 'AccountInfo',
    components: {
        BusinessUserNav,
        PersonalInfoItem
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

hr {
    padding: 0 !important;
    display: block;
    margin: 0.5em auto;
    background-color: #EBEBEB;
    border: 1px solid #EBEBEB;
}

</style>