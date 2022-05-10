<template>
    <div class="wrapper">
        <h1>Registration Requests</h1><hr>
        <div class="registration-request-wrapper">
            <div class="registration-request-list">
                <RegistrationRequest v-for="r in requests" :rr="r" :key="r.email"/>
            </div>
        </div>
    </div>
</template>

<script>
import RegistrationRequest from "../components/RegistrationRequest.vue"

export default {
    name: "RegistrationRequestList",
    components: {
        RegistrationRequest,
    },
    data() {
        return {
            requests: [
                {
                firstName: "Janko",
                lastName: "Janic",
                email: "janko@gmail.com",
                password: "HiHiHiHa",
                role: "HOUSE_OWNER",
                phone: "+3813284239",
                dateOfBirth: "26.4.1985.",
                address: "Slavija 49",
                city: "Beograd",
                country: "Serbia", 
                registrationReason: "Imam lepu vikendicu koja nicemu ne sluzi pa reko da je stavim na izdavanje."
                },
                {
                firstName: "Teodor",
                lastName: "Teodoric",
                email: "teodor@gmail.com",
                password: "HiHiHiHa",
                role: "FISHING_INSTRUCTOR",
                phone: "+0493284239",
                dateOfBirth: "04.07.2000.",
                address: "Puskinova 222",
                city: "Novi Sad",
                country: "Serbia", 
                registrationReason: "Pecam od malena i voleo bih da imam mogucnost da lakse ucim ostale kako da pecaju. :)"
                },
                {
                firstName: "Ilija",
                lastName: "Kalinic",
                email: "ikkjo@polywhy.com",
                password: "HiHiHiHa",
                role: "SHIP_OWNER",
                phone: "+0493284239",
                dateOfBirth: "10.10.2000.",
                address: "Somborski put 33",
                city: "Subotica",
                country: "Serbia", 
                registrationReason: "Rokeri s moravu :DDDDDDDD"
                },
                {
                firstName: "Janko",
                lastName: "Janic",
                email: "jasnko@gmail.com",
                password: "HiHiHiHa",
                role: "HOUSE_OWNER",
                phone: "+3813284239",
                dateOfBirth: "26.4.1985.",
                address: "Slavija 49",
                city: "Beograd",
                country: "Serbia", 
                registrationReason: "Imam lepu vikendicu koja nicemu ne sluzi pa reko da je stavim na izdavanje."
                },
                {
                firstName: "Teodor",
                lastName: "Teodoric",
                email: "tesodor@gmail.com",
                password: "HiHiHiHa",
                role: "FISHING_INSTRUCTOR",
                phone: "+0493284239",
                dateOfBirth: "04.07.2000.",
                address: "Puskinova 222",
                city: "Novi Sad",
                country: "Serbia", 
                registrationReason: "Pecam od malena i voleo bih da imam mogucnost da lakse ucim ostale kako da pecaju. :)"
                },
                {
                firstName: "Ilija",
                lastName: "Kalinic",
                email: "ikdkjo@polywhy.com",
                password: "HiHiHiHa",
                role: "SHIP_OWNER",
                phone: "+0493284239",
                dateOfBirth: "10.10.2000.",
                address: "Somborski put 33",
                city: "Subotica",
                country: "Serbia", 
                registrationReason: "Rokeri s moravu :DDDDDDDD"
                },
            ]
        }
    },

}
</script>

<style scoped>
h1 {
    padding: 20px;
}

.registration-request-wrapper {
    display: flex;
    width: 100%;
    justify-content: center;

}

.registration-request-list {
    display: flex;
    padding: 20px;
    flex-direction: column;
    margin-top: 20px;
    max-width: 720px;
    min-height: 90px;
    border-radius: 10px;
    background-color: var(--orange-primary,orange);
}

.registration-request-list :last-child {
    margin-bottom: 0;
}
</style>