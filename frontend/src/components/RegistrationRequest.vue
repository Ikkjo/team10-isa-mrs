<template>
    <div @click="showModal=true" class="registration-request">
        <p v-if="rr">
            <b>{{ rr.firstName+" "+rr.lastName }}</b>
            from <b> {{rr.city+", "+rr.country }}</b>
            as <i>{{ rr.role }}</i>
        </p>
        <portal to="body">
            <!-- use the modal component, pass in the prop -->
            <RegistrationRequestModal
            v-if="showModal"
            :show="showModal"
            @close="showModal=false"
            :rr="rr"/>
        </portal>
    </div>
</template>

<script>
// import axios from 'axios'
import RegistrationRequestModal from "./RegistrationRequestModal.vue"

export default {
    name: "RegistrationRequest",
    components: {
        RegistrationRequestModal,
    },
    props: {
        rr: {
            type: Object,
            default() { 
                return {
                firstName: "Test",
                lastName: "Testis",
                email: "testing@testing.com",
                password: "HiHiHiHa",
                role: "FISHING_INSTRUCTOR",
                phone: "+0493284239",
                dateOfBirth: "22.11.2000.",
                address: "Testing 49",
                city: "Sremska Mitrovica",
                country: "Republika Srbija", 
                registrationReason: "I love selling pot while covering up as a house owner :DDD. I love selling pot while covering up as a house owner :DDD. I love selling pot while covering up as a house owner :DDD."
                }
            }
        }
    },
    data() {
        return {
            showModal: false,
        }
    },
}
</script>

<style>
.registration-request {
    display: flex;
    justify-content: center;
    align-items: center;
    width:50%;
    max-width: 700px;
    min-height: 80px;
    margin-bottom: 10px;
    padding: 20px;
    border: 1px solid black;
    border-radius: 10px;
}

.registration-request:hover {
    background-color: whitesmoke
}
</style>