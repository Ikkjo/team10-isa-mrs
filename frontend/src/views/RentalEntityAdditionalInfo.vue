<template>
   <div id="additional-info">
        <div class="inner-container form">
            <h1>Additional information</h1>
            <div class="form-control">
                <label for="rules-of-conduct">Rules of conduct</label>
                <textarea v-model="rulesOfConduct" name="rules-of-conduct" id="rules-of-conduct" cols="30" rows="4" placeholder=""></textarea>
            </div>
            <div class="form-control">
                <label for="additional-services" class="block-label">Additional Services</label>
                <textarea v-model="additionalServices" name="additional-services" id="additional-services" cols="30" rows="4" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
            </div>
            <div class="btn-div">
                <button @click="cancel" class="btn btn-cancel">Cancel</button> 
                <button @click="next" class="btn">Next</button>
            </div>
        </div>
        <div class="inner-container picture-container"></div>
    </div>
</template>

<script>
import router from '@/router';
export default {
    name: 'VacationHomeAdditionalInfo',
    data() {
        return {
            rulesOfConduct: '',
            additionalServices: '',
        }
    },
    mounted() {
        if (localStorage.rulesOfConduct) this.rulesOfConduct = localStorage.rulesOfConduct;
        if (localStorage.additionalServices) this.additionalServices = localStorage.additionalServices;
    },
    methods: {
        next() {
            localStorage.rulesOfConduct = this.rulesOfConduct;
            localStorage.additionalServices = this.additionalServices;
            // route to next page
            router.push("/add-rental-entity/price")
        },
        cancel() {
            localStorage.removeItem("rulesOfConduct");
            localStorage.removeItem("additionalServices");
            // route to main page
        }
    }
}
</script>

<style>
#additional-info {
    width: 100%;
    display: flex;
    align-items: center;  
}

.inner-container {
    width: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100vh;
}

.form {
    padding: 0 100px 0 100px;
}

.picture-container {
    background-image: url('../assets/add-vacation-home-bg.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}

.btn-div {
    display: flex;
    justify-content: space-between;
}

.btn {
    margin-top: 15px;
    width: 25%;
    align-self: center;
}

.btn-cancel {
    margin-top: 0px !important;
}

</style>