<template>
    <div id="rental-entity-basic-info">
        <section class="inner-container">
            <!-- STEP 1: BASIC INFORMATION -->
            <div class="form" v-show="step === 1">
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
            </div>
            <!-- STEP 2: ADDITIONAL INFORMATION -->
            <div class="form" v-show="step === 2">
                <h1>Additional information</h1>
                <div class="form-control">
                    <label for="rules-of-conduct">Rules of conduct</label>
                    <textarea v-model="rulesOfConduct" name="rules-of-conduct" id="rules-of-conduct" cols="30" rows="4" placeholder=""></textarea>
                </div>
                <div class="form-control">
                    <label for="additional-services" class="block-label">Additional Services</label>
                    <textarea v-model="additionalServices" name="additional-services" id="additional-services" cols="30" rows="4" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
                </div>
            </div>
            <!-- STEP 3: PRICE -->
            <div class="form price-div" v-show="step === 3">
                <h1>Price</h1>
                <price-input @updated="priceUpdated" class="form-control pricing"/>
            </div>
            <!-- STEP 4: PHOTOS -->
            <div class="form" v-show="step === 4">
                <rental-entity-picture-input @updated="picturesUpdated"/>
            </div>
            <!-- VACATION HOUSE STEPS -->
            <!-- STEP 5: ROOMS INPUT -->
            <div class="form" v-show="step === 5">
                <rooms-input @updated:rooms="roomsUpdated" @updated:beds="bedsUpdated"/>
            </div>

            <div class="bottom">
                <div class="progress-bar">
                    <div class="bar" :style="step === 1 ? {width: 5 + '%'} : {width: 20 * (step-1) + '%'}"></div>
                </div>
                <div class="btn-div">
                    <button @click="back" class="btn btn-back">Back</button> 
                    <button @click="next" class="btn">Next</button>
                </div>
            </div>
        </section>
        
        <!-- Add conditional classes for background-image -->
        <div class="inner-container picture-container"></div>
    </div>
</template>

<script>
import AddressInput from '../components/AddressInput.vue'
import PriceInput from '../components/PriceInput.vue'
import RentalEntityPictureInput from '../components/RentalEntityPictureInput.vue'
import RoomsInput from '../components/RoomsInput.vue'

export default {
    name: 'AddRentalEntity',
    components: {
        AddressInput,
        PriceInput,
        RentalEntityPictureInput,
        RoomsInput,
    },
    data() {
        return {
            title: '',
            address: '',
            city: '',
            country: '',
            description: '',
            rulesOfConduct: '',
            additionalServices: '',
            price: '',
            pictures: [],
            rooms: 0,
            beds: 0,
            step: 1,

        }
    },
    methods: {
        addressUpdated(address) {
            this.address = address;
        },
        cityUpdated(city) {
            this.city = city;
        },
        countryUpdated(country) {
            this.country = country;
        },
        priceUpdated(price) {
				this.price = price;
		},
        picturesUpdated(pictures) {
            this.pictures = pictures;
        },
        roomsUpdated(rooms) {
            this.rooms = rooms;
        },
        bedsUpdated(beds) {
            this.beds = beds;
        },
        next() {
            // add check if step is == maxSteps
            this.step +=1 ;
            if (this.step === 4) {
                    this.$nextTick(function () {
                    // DOM updated
                    window.dispatchEvent(new Event('resize'));
                });
            }
        },
        back() {
            if (this.step > 1) this.step -= 1;
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
    height: 100%;
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

.price-div {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.price-div > * {
    margin: 7px 0;
}

.bottom {
    position: absolute;
    bottom: 0;
    width: 50%;
}

.progress-bar {
    background-color: lightgrey;
    height: 10px;
}

.bar {
    height: 100%;
    background-color: var(--orange-primary, orange);
    transition: 0.7s;
}

.btn-div {
    display: flex;
    justify-content: space-between;
}

.btn {
    width: 25%;
    margin: 15px;
}

.btn-back {
    background-color: lightgray;

}

.btn-back:hover {
    background-color: grey;
}

.picture-container {
    background-image: url('../assets/add-vacation-home-bg.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}
</style>