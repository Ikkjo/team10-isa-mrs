<template>
    <div id="rental-entity-basic-info">
        <div class="inner-container">
            <div class="form-container">
                <!-- STEP 1: BASIC INFORMATION -->
                <div class="form" v-show="step === 1">
                    <h1>Basic information</h1>
                    <div class="form-control">
                        <label for="title">Title</label>
                        <input type="text" v-model="form.title" name="title">
                    </div>
                    <address-input @update:address="addressUpdated" @update:city="cityUpdated" @update:country="countryUpdated"/>
                    <div class="form-control">
                        <label for="description" class="block-label">Description</label>
                        <textarea name="description" id="description" cols="30" rows="4" v-model="form.description" placeholder=""></textarea>
                    </div>
                </div>
                <!-- STEP 2: ADDITIONAL INFORMATION -->
                <div class="form" v-show="step === 2">
                    <h1>Additional information</h1>
                    <div class="form-control">
                        <label for="rules-of-conduct">Rules of conduct</label>
                        <textarea v-model="form.rulesOfConduct" name="rules-of-conduct" id="rules-of-conduct" cols="30" rows="4" placeholder=""></textarea>
                    </div>
                    <div class="form-control">
                        <label for="additional-services" class="block-label">Additional Services</label>
                        <textarea v-model="form.additionalServices" name="additional-services" id="additional-services" cols="30" rows="4" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
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

                <!-- The rest of the steps can be decided using v-if on type of logged in user -->
                <!-- VACATION HOUSE STEPS -->
                <!-- STEP 5: ROOMS INPUT -->
                <div class="form" v-show="step === 5" v-if="user.userRole === 'HOUSE_OWNER'">
                    <rooms-input @updated:rooms="roomsUpdated" @updated:beds="bedsUpdated" ref="roomsInput"/>
                </div>

                <!-- STEP 5: SHIP INPUT -->
                <div class="form ship-form" v-show="step === 5" v-if="user.userRole === 'SHIP_OWNER'">
                    <h1>Ship information</h1>
                    <div class="form-control">
                        <label for="ship-type">Ship Type</label>
                        <input type="text" v-model="form.shipType" name="ship-type" @focus="inFocus('shipType')" @blur="outFocus('shipType')" :class="getClass('shipType')" :placeholder="getPlaceholder('shipType')">
                        <div class="alert-info" 
                        v-if="!this.infocus['shipType'] && !($v.form.shipType.minLength && $v.form.shipType.maxLength)">
                        Must be between 5 and 50 characters.
                        </div>
                    </div>
                    <div class="number-input">
                        <div>
                            <number-input @updated="shipLengthUpdated" placeholder="" label="Ship Length (meters)" :increment="1" :minValue="1" :maxValue="50" />
                            <number-input @updated="capacityUpdated" placeholder="" label="Capacity" :increment="2" :minValue="1" :maxValue="100" />
                        </div>
                        <div>
                            <number-input @updated="engineCountUpdated" placeholder="" label="Number of Engines" :increment="1" :minValue="1" :maxValue="5" />
                            <number-input @updated="enginePowerUpdated" placeholder="" label="Engine Power (kw)" :increment="5" :minValue="1" :maxValue="10000" />
                        </div>
                        <div>
                            <number-input @updated="maxSpeedUpdated" placeholder="" label="Max Speed (km/h)" :increment="5" :minValue="1" :maxValue="200" />
                        </div>
                    </div>
                    
                    <div class="form-control">
                        <label for="navigation-equipment" class="block-label">Navigation Equipment</label>
                        <textarea v-model="form.navigationEquipment" name="navigation-equipment" id="navigation-equipment" cols="30" rows="4" @focus="inFocus('navigationEquipment')" @blur="outFocus('navigationEquipment')" :class="getClass('navigationEquipment')" :placeholder="getPlaceholder('navigationEquipment', 'GPS, radar, VHS radio, fishfinder...')"></textarea>
                        <div class="alert-info alert-textarea" 
                        v-if="!this.infocus['navigationEquipment'] && !($v.form.navigationEquipment.minLength && $v.form.navigationEquipment.maxLength)">
                        Must be between 3 and 500 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="fishing-equipment" class="block-label">Fishing Equipment</label>
                        <textarea v-model="form.fishingEquipment" name="fishing-equipment" id="fishing-equipment" cols="30" rows="4" @focus="inFocus('fishingEquipment')" @blur="outFocus('fishingEquipment')" :class="getClass('fishingEquipment')" :placeholder="getPlaceholder('fishingEquipment', 'Fishing rods, baits, hooks, weights...')"></textarea>
                        <div class="alert-info alert-textarea" 
                        v-if="!this.infocus['fishingEquipment'] && !$v.form.fishingEquipment.maxLength">
                        Max 500 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="cancelation" class="block-label">Cancellation</label>
                        <select name="cancelation" id="cancelation" v-model="form.freeCancellation">
                            <option :value="true" selected>Free cancellation</option>
                            <option :value="false">Owner keeps a percentage</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <div class="progress-bar">
                    <div class="bar" :style="step === 1 ? {width: 5 + '%'} : {width: 100/numSteps * (step-1) + '%'}"></div>
                </div>
                <div class="btn-div">
                    <button @click="back" class="btn btn-back">Back</button> 
                    <button v-if="step < numSteps" @click="next" :disabled="nextDisabled()" class="btn">Next</button>
                    <button v-if="step === numSteps" @click="finish" :disabled="finishDisabled()" class="btn">Finish</button>
                </div>
            </div>
        </div>
        <!-- Add conditional classes for background-image -->
        <div class="inner-container picture-container"></div>
    </div>
</template>

<script>
import AddressInput from '../components/AddressInput.vue'
import PriceInput from '../components/PriceInput.vue'
import RentalEntityPictureInput from '../components/RentalEntityPictureInput.vue'
import RoomsInput from '../components/RoomsInput.vue'
import NumberInput from '../components/NumberInput.vue'
import axios from 'axios';
import { required, minLength, maxLength } from 'vuelidate/lib/validators'

export default {
    name: 'AddRentalEntity',
    components: {
        AddressInput,
        PriceInput,
        RentalEntityPictureInput,
        RoomsInput,
        NumberInput,
    },
    data() {
        return {
            form: {
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
                shipType: '',
                shipLength: 1,
                engineCount: 1,
                enginePower: 1,
                maxSpeed: 1,
                navigationEquipment: '',
                fishingEquipment: '',
                capacity: 1,
                freeCancellation: true,

            },
            step: 1,
            numSteps: 5,
            user: {
                userRole: 'SHIP_OWNER'
            },
            infocus: {
                shipType: true,
                navigationEquipment: true,
                fishingEquipment: true,
            }
        }
    },
    validations: {
        form: {
            shipType: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(50),
            },
            navigationEquipment: {
                required,
                minLength: minLength(3),
                maxLength: maxLength(500),
            },
            fishingEquipment: {
                maxLength: maxLength(500),
            }
        }
       
    },
    methods: {
        addressUpdated(address) {
            this.form.address = address;
        },
        cityUpdated(city) {
            this.form.city = city;
        },
        countryUpdated(country) {
            this.form.country = country;
        },
        priceUpdated(price) {
				this.form.price = price;
		},
        picturesUpdated(pictures) {
            this.form.pictures = pictures;
        },
        roomsUpdated(rooms) {
            this.form.rooms = rooms;
        },
        bedsUpdated(beds) {
            this.form.beds = beds;
        },
        shipLengthUpdated(length) {
            this.shipLength = length;
        },
        engineCountUpdated(engineCount) {
            this.form.engineCount = engineCount;
        },
        enginePowerUpdated(enginePower) {
            this.form.enginePower = enginePower;
        },
        capacityUpdated(capacity) {
            this.form.capacity = capacity;
        },
        maxSpeedUpdated(maxSpeed) {
            this.form.maxSpeed = maxSpeed;
        },
        nextDisabled() {
            // TODO: Napraviti proveru po koracima 
            return false;
        },
        finishDisabled() {
            if (this.user.userRole === 'HOUSE_OWNER')
                return (this.$refs.roomsInput.$v.$invalid || this.$v.form.$invalid);
            if (this.user.userRole === 'SHIP_OWNER') {
                return this.$v.form.$invalid;
            }
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
        finish() {
            if (this.user.userRole === 'HOUSE_OWNER')
                this.postVacationHome();
            else if (this.user.userRole === 'SHIP_OWNER')
                this.postShip();
        },
        postVacationHome() {
            let vacationHomeDto = {
                title: this.form.title,
                address: {'address': this.form.address, 'city': this.form.city, 'country': this.form.country},
                description: this.form.description,
                rulesOfConduct: this.form.rulesOfConduct,
                additionalServices: this.form.additionalServices,
                price: this.form.price,
                pictures: this.form.pictures,
                rooms: this.form.rooms,
                beds: this.form.beds,
            }
            // username = JSON.parse(window.sessionStorage.getItem("user")).username;
            // token = JSON.parse(window.sessionStorage.getItem("user")).jwt
            // console.log(vacationHomeDto);
            
            axios({
                method: 'post',
                url: 'http://localhost:8888/api/v1/vacation-home-owner/vacation-homes',
                data: vacationHomeDto,
                // headers: {
                //     Authorization: 'Bearer ' + token,
                // },
            }).then(function(response) {
                console.log(response);
                // notify that awaiting accept
            })
            .catch(function(error) {
                console.log(error);
            })          
        },
        postShip() {
            let shipDto = {
                title: this.form.title,
                address: {'address': this.form.address, 'city': this.form.city, 'country': this.form.country},
                description: this.form.description,
                rulesOfConduct: this.form.rulesOfConduct,
                additionalServices: this.form.additionalServices,
                price: this.form.price,
                pictures: this.form.pictures,
                type: this.form.shipType,
                length: this.form.shipLength,
                engineCount: this.form.engineCount,
                enginePower: this.form.enginePower,
                maxSpeed: this.form.maxSpeed,
                navigationEquipment: this.form.navigationEquipment,
                fishingEquipment: this.form.fishingEquipment,
                capacity: this.form.capacity,
                freeCancellation: this.form.freeCancellation,
            }

            axios({
                method: 'post',
                url: 'http://localhost:8888/api/v1/ship-owner/ships',
                data: shipDto,
                // headers: {
                //     Authorization: 'Bearer ' + token,
                // },
            }).then(function(response) {
                console.log(response);
                // notify that awaiting accept
            })
            .catch(function(error) {
                console.log(error);
            }) 

        },
        isFocused(field) {
            return this.infocus[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        getClass(field) {
            let cls = !this.isFocused(field) && this.$v.form[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) && this.$v.form[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },
}
</script>

<style scoped>
#rental-entity-basic-info {
    width: 100%;
    display: flex;
    align-items: center;  
    overflow: hidden;
}

.inner-container {
    height: 100vh;
    width: 50%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

.form-container {
    overflow-y: auto;
    overflow-x: hidden;
    display: flex;
    align-items: center;
    height: calc(100% - 80px);
    width: 100%;
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
    background-color: white;
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

.btn:disabled {
    background-color: lightgray;
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

.ship-length {
    justify-self: center;
}

.ship-form .number-input {
    display: flex;
    flex-wrap: wrap;
}

.number-input div {
    margin-right: auto;
    margin-bottom: 15px;
}


.number-input :last-child {
    margin-bottom: 0 !important;
}

.alert-info {
    position: absolute;
    transition: 0.05s;
    color: red !important;
    font-size: 0.9rem;
    margin-top: 67px;
}

.alert-textarea {
    margin-top: 110px;
}

</style>