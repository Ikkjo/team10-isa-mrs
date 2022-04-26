<template>
    <div @click="showModal=true" class="registration-request">
        <p v-if="rr">rr.firstName</p>
        <p v-if="rr">rr.role</p>
        <portal  to="body">
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
        rr: Object,
        default: {}
    },
    data() {
        return {
            showModal: false,
        }
    },
    methods: {
    },
}
</script>

<style>
.registration-request {
    width: 200px;
    height: 50px;
    margin: 5px;
    border:1px solid black;
}
</style>