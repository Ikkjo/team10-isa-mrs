<template>
    <div id="rental-entity-basic-info">
        <div class="inner-container form">
            <h1>Basic information</h1>
            <div class="form-control">
                <label for="title">Title</label>
                <input type="text" v-model="title" name="title">
            </div>
            <address-input @update:address="addressUpdated" @update:city="cityUpdated" @update:country="countryUpdated"/>
            <div class="form-control">
                <label for="description" class="block-label">Description</label>
                <textarea name="description" id="description" cols="30" rows="4" v-model="description" placeholder=""></textarea>
            </div>
            <div class="btn-div">
                <button @click="cancel" class="btn btn-cancel">Cancel</button> 
                <button @click="next" class="btn">Next</button>
            </div>
        </div>
        // Add conditional classes for background-image
        <div class="inner-container picture-container"></div>
    </div>
</template>

<script>
import AddressInput from '../components/AddressInput.vue'

export default {
    name: 'RentalEntityBasicInfo',
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
        addressUpdated(address) {
            this.address = address;
        },
        cityUpdated(city) {
            this.city = city;
        },
        countryUpdated(country) {
            console.log("HEREEEEE")
            this.country = country;
        },
        next() {
            localStorage.title = this.title;
            localStorage.address = this.address;
            localStorage.city = this.city;
            localStorage.country = this.country;
            localStorage.description = this.description;
            // add router.route to next page
            this.$router.push("/add-rental-entity/additional-info");
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
#rental-entity-basic-info {
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

.form > * {
    margin: 15px 0;
    padding: 0 100px 0 100px;

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

.picture-container {
    background-image: url('../assets/add-vacation-home-bg.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}
</style>