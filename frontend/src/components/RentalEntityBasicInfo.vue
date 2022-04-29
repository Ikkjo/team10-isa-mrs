<template>
    <div id="basic-info">
        <h1>Basic information</h1>
        <div class="form-control">
            <label for="title">Title</label>
            <input type="text" v-model="title" name="title">
        </div>
        <address-input :address="address" :city="city" :country="country"/>
        <div class="form-control">
            <label for="description" class="block-label">Description</label>
            <textarea name="description" id="description" cols="30" rows="4" v-model="description" placeholder=""></textarea>
        </div>
        <div class="btn-div">
            <button @click="cancel" class="btn btn-cancel">Cancel</button> 
            <button @click="next" class="btn">Next</button>
        </div>
      
    </div>
</template>

<script>
import AddressInput from './AddressInput.vue'

export default {
    name: 'VacationHomeBasicInfo',
    components: {
        AddressInput,
    },
    data() {
        return {
            title: '',
            address: '',
            city: '',
            country: '',
            description: '',

        }
    },
    mounted() {
        if (localStorage.title) this.title = localStorage.title;
        if (localStorage.address) this.address = localStorage.address;
        if (localStorage.city) this.city = localStorage.city;
        if (localStorage.country) this.country = localStorage.country;
        if (localStorage.description) this.description = localStorage.description;
    },
    methods: {
        next() {
            localStorage.title = this.title;
            localStorage.address = this.address;
            localStorage.city = this.city;
            localStorage.country = this.country;
            localStorage.description = this.description;
            // add router.route to next page
            this.$router.push("/rental-entity-additional-information");
        },
        cancel() {
            localStorage.removeItem("title"); 
            localStorage.removeItem("address"); 
            localStorage.removeItem("city"); 
            localStorage.removeItem("country");
            localStorage.removeItem("description");
            // add router.route to main page 
        },
        // addVacationHomePressed() {
        //     let vacationHomeDTO = {
        //         title: this.title,
        //         address: {'address': this.address, 'city': this.city, 'country': this.country},
        //         rooms: this.rooms,
        //         price: this.price,
        //         rulesOfConduct: this.rulesOfConduct,
        //         additionalServices: this.additionalServices,
        //     }
        //     console.log(vacationHomeDTO)
        //     axios
        //         .post("http://localhost:8888/api/v1/vacation-home-owner/vacation-homes", vacationHomeDTO)
        //         .then(function(response) {
        //             console.log(response);
        //             // notify that awaiting accept
        //         })
        //         .catch(function(error) {
        //             console.log(error);
        //         })
        // }
    },
}
</script>

<style scoped>
#basic-info {
    display: flex;
    flex-direction: column;
}

#basic-info > * {
    margin: 15px 0;
}

.pricing {
    align-self: center;
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