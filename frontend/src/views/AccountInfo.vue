<template>
    <div id="account-info">
        <business-user-nav></business-user-nav>
        <div class="info">
             <div class="info-section">
                <h2>Personal info</h2>
                <div class="info-items">
                    <personal-info-item iconClass="material-icons" icon="account_box" label="Account type" text="Rental House Owner" buttonText="Register new account"/>
                    <hr>
                    <personal-info-item iconClass="material-icons" icon="account_circle" label="Full name" text="Imenko Prezimic" buttonText="Edit"/>
                    <hr>
                    <personal-info-item iconClass="material-icons" icon="contact_phone" label="Phone number" text="+381 66 127 8217" buttonText="Edit"/>
                    <hr>
                    <personal-info-item iconClass="material-icons" icon="house" label="Address" text="Ulica b.b., Grad, Drzava" buttonText="Edit"/>
                    <hr>
                    <personal-info-item iconClass="material-icons" icon="calendar_month" label="Date of birth" text="12.06.1998." buttonText="Edit"/>
                    <hr>
                </div>
            </div>
            <div class="info-section">
                <h2>Login info</h2>
                <div class="info-items">
                    <personal-info-item iconClass="material-icons" icon="email" label="Email" text="example@rentr.com" buttonText="Change"/>
                    <hr>
                    <personal-info-item iconClass="material-icons" icon="password" label="Password" text="*********" buttonText="Change"/>
                    <hr>
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
    height: 100%;
}

h2 {
    margin: 15px 0;
}

.info {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.info-items > * {
    padding: 10px 0;
}

.info-section {
    width: 25%;
}

hr {
    padding: 0 !important;
    display: block;
    margin: 0.5em auto;
    background-color: #EBEBEB;
    border: 1px solid #EBEBEB;
}

</style>