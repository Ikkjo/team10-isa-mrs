<template>
    <div id="rental-entity-basic-info">
        <div class="inner-container">
            <div class="form-container">
                <!-- STEP 1: BASIC INFORMATION -->
                <div class="form" v-show="step === 1">
                    <h1>Basic information</h1>
                    <div class="form-control">
                        <label for="title">Title</label>
                        <input type="text" 
                            v-model="baseInfo.first.title"
                            name="title"
                            @focus="inFocus('title')" 
                            @blur="outFocus('title')" 
                            :class="getClassFirst('title')" 
                            :placeholder="getPlaceholderFirst('title')"
                            >
                        <div class="alert-info" 
                            v-if="!isFocused('title') 
                            && !($v.baseInfo.first.title.minLength 
                            && $v.baseInfo.first.title.maxLength)"
                            >
                            Must be between 5 to 50 characters.
                        </div>
                    </div>
                    <AddressInput 
                        @update:address="addressUpdated" 
                        @update:city="cityUpdated" 
                        @update:country="countryUpdated"
                        :validate="true"
                        />
                    <div class="form-control">
                        <label for="description" class="block-label">Description</label>
                        <textarea 
                            name="description" 
                            id="description" 
                            cols="30" rows="4" 
                            v-model="baseInfo.first.description"
                            @focus="inFocus('description')" 
                            @blur="outFocus('description')" 
                            :class="getClassFirst('description')" 
                            :placeholder="getPlaceholderFirst('description')"
                            />
                        <div class="alert-info" 
                            v-if="!isFocused('description') 
                            && !($v.baseInfo.first.description.minLength 
                            && $v.baseInfo.first.description.maxLength)"
                            >
                            Must be between 5 to 500 characters.
                        </div>
                    </div>
                </div>
                <!-- STEP 2: ADDITIONAL INFORMATION -->
                <div class="form" v-show="step === 2">
                    <h1>Additional information</h1>
                    <div class="form-control">
                        <label for="rules-of-conduct">Rules of conduct</label>
                        <textarea 
                            v-model="baseInfo.second.rulesOfConduct" 
                            name="rules-of-conduct" 
                            id="rules-of-conduct" 
                            cols="30" rows="4" 
                            @focus="inFocus('rulesOfConduct')" 
                            @blur="outFocus('rulesOfConduct')" 
                            :class="getClassSecond('rulesOfConduct')" 
                            :placeholder="getPlaceholderSecond('rulesOfConduct')"
                            />
                        <div class="alert-info" 
                            v-if="!isFocused('rulesOfConduct') 
                            && !($v.baseInfo.second.rulesOfConduct.minLength 
                            && $v.baseInfo.second.rulesOfConduct.maxLength)"
                            >
                            Must be between 5 to 200 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="additional-services" class="block-label">Additional Services</label>
                        <textarea 
                            v-model="baseInfo.second.additionalServices" 
                            name="additional-services" 
                            id="additional-services" 
                            cols="30" rows="4" 
                            @focus="inFocus('additionalServices')" 
                            @blur="outFocus('additionalServices')" 
                            :class="getClassSecond('additionalServices')" 
                            :placeholder="getPlaceholderSecond('additionalServices', 
                            'Some things you offer like: wifi, free parking, air conditioning...')"
                            />
                        <div class="alert-info" 
                            v-if="!isFocused('additionalServices') 
                            && !($v.baseInfo.second.additionalServices.minLength 
                            && $v.baseInfo.second.additionalServices.maxLength)"
                            >
                            Must be between 2 to 200 characters.
                        </div>
                    </div>
                </div>
                <!-- STEP 3: PRICE -->
                <div class="form price-div" v-show="step === 3">
                    <h1>Price</h1>
                    <PriceInput @updated="priceUpdated" class="form-control pricing"/>
                </div>
                <!-- STEP 4: PHOTOS -->
                <div class="form picture-form" v-show="step === 4">
                    <RentalEntityPictureInput @updated="picturesUpdated"/>
                </div>

                <!-- The rest of the steps can be decided using v-if on type of logged in user -->
                <!-- STEP 5: VACATION HOME INPUT -->
                <div class="form" v-show="step === 5" v-if="userRole === 'HOUSE_OWNER'">
                    <RoomsInput 
                        @updated:rooms="roomsUpdated" 
                        @updated:beds="bedsUpdated" 
                        ref="roomsInput"
                    />
                </div>

                <!-- STEP 5: SHIP INPUT -->
                <div class="form ship-form" v-show="step === 5" v-if="userRole === 'SHIP_OWNER'">
                    <h1>Ship information</h1>
                    <div class="form-control">
                        <label for="ship-type">Ship Type</label>
                        <input type="text" 
                            v-model="ship.type" 
                            name="ship-type" 
                            @focus="inFocusShip('type')" 
                            @blur="outFocusShip('type')" 
                            :class="getClassShip('type')" 
                            :placeholder="getPlaceholderShip('type')"
                            >
                        <div class="alert-info" 
                            v-if="!isFocusedShip('type') 
                            && !($v.ship.type.minLength 
                            && $v.ship.type.maxLength)"
                            >
                            Must be between 5 and 50 characters.
                        </div>
                    </div>
                    <div class="number-input">
                        <div>
                            <NumberInput 
                                @updated="shipLengthUpdated" 
                                placeholder="" 
                                label="Ship Length (meters)" 
                                :increment="1" 
                                :minValue="1" 
                                :maxValue="50"
                                />
                            <NumberInput 
                                @updated="shipCapacityUpdated" 
                                placeholder="" 
                                label="Capacity" 
                                :increment="2" 
                                :minValue="1" 
                                :maxValue="100"
                                />
                        </div>
                        <div>
                            <NumberInput 
                                @updated="shipEngineCountUpdated" 
                                placeholder="" 
                                label="Number of Engines" 
                                :increment="1" 
                                :minValue="1" 
                                :maxValue="5"
                                />
                            <NumberInput 
                                @updated="shipEnginePowerUpdated" 
                                placeholder="" 
                                label="Engine Power (kw)" 
                                :increment="5" 
                                :minValue="1" 
                                :maxValue="10000"
                                />
                        </div>
                        <div>
                            <NumberInput
                                @updated="shipMaxSpeedUpdated" 
                                placeholder="" 
                                label="Max Speed (km/h)" 
                                :increment="5" 
                                :minValue="1" 
                                :maxValue="200"
                                />
                        </div>
                    </div>
                    
                    <div class="form-control">
                        <label for="navigation-equipment" class="block-label">Navigation Equipment</label>
                        <textarea 
                            v-model="ship.navigationEquipment" 
                            name="navigation-equipment" 
                            id="navigation-equipment" 
                            cols="30" rows="4" 
                            @focus="inFocusShip('navigationEquipment')" 
                            @blur="outFocusShip('navigationEquipment')" 
                            :class="getClassShip('navigationEquipment')" 
                            :placeholder="getPlaceholderShip('navigationEquipment', 'GPS, radar, VHS radio, fishfinder...')"
                            />
                        <div class="alert-info alert-textarea" 
                            v-if="!isFocusedShip('navigationEquipment') 
                            && !($v.ship.navigationEquipment.minLength 
                            && $v.ship.navigationEquipment.maxLength)"
                            >
                            Must be between 3 and 500 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="fishing-equipment" class="block-label">Fishing Equipment</label>
                        <textarea 
                            v-model="adventure.fishingEquipment" 
                            name="fishing-equipment" 
                            id="fishing-equipment" 
                            cols="30" rows="4" 
                            @focus="inFocusShip('fishingEquipment')" 
                            @blur="outFocusShip('fishingEquipment')" 
                            :class="getClassShip('fishingEquipment')" 
                            :placeholder="getPlaceholderShip('fishingEquipment', 'Fishing rods, baits, hooks, weights...')"
                            />
                        <div class="alert-info alert-textarea" 
                            v-if="!isFocusedShip('fishingEquipment')
                            && !$v.ship.fishingEquipment.maxLength"
                            >
                            Max 500 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="cancellation" class="block-label">Cancellation</label>
                        <select name="cancellation" id="cancellation" v-model="ship.cancellation">
                            <option :value="null" selected disabled hidden>Choose an option</option>
                            <option :value="true">Free cancellation</option>
                            <option :value="false">Owner keeps a percentage</option>
                        </select>
                    </div>
                </div>
                
                <!-- STEP 5: ADVENTURE INPUT -->
                <div class="form adventure-form" v-show="step === 5" v-if="userRole === 'FISHING_INSTRUCTOR'">
                    <h1>Adventure information</h1>
                    <div class="form-control">
                        <label for="fishing-instructor-bio">Short Biography</label>
                        <textarea 
                            v-model="adventure.fishingInstructorBio" 
                            name="fishing-instructor-bio" 
                            id="fishing-instructor-bio" 
                            cols="30" rows="3" 
                            @focus="inFocusAdventure('fishingInstructorBio')" 
                            @blur="outFocusAdventure('fishingInstructorBio')" 
                            :class="getClassAdventure('fishingInstructorBio')" 
                            :placeholder="getPlaceholderAdventure('fishingInstructorBio', 'Something about yourself.')"
                            />
                        <div class="alert-info alert-textarea"
                            v-if="!isFocusedAdventure('fishingInstructorBio') 
                            && !($v.adventure.fishingInstructorBio.minLength 
                            && $v.adventure.fishingInstructorBio.maxLength)"
                            >
                            Must be between 5 to 200 characters.
                        </div>
                    </div> 
                    <div class="number-input">
                        <NumberInput 
                            @updated="adventureMaxCapacityUpdated" 
                            placeholder="" 
                            label="Maximum number of people" 
                            :increment="1" 
                            :minValue="1" 
                            :maxValue="20"
                            />
                    </div>
                    <div class="form-control">
                        <label for="fishing-equipment" class="block-label">Fishing Equipment</label>
                        <textarea 
                            v-model="adventure.fishingEquipment" 
                            name="fishing-equipment" 
                            id="fishing-equipment" 
                            cols="30" rows="4" 
                            @focus="inFocusAdventure('fishingEquipment')" 
                            @blur="outFocusAdventure('fishingEquipment')" 
                            :class="getClassAdventure('fishingEquipment')" 
                            :placeholder="getPlaceholderAdventure('fishingEquipment', 'Fishing rods, baits, hooks, weights...')"
                            />
                        <div class="alert-info alert-textarea" 
                            v-if="!isFocusedAdventure('fishingEquipment')
                            && !($v.adventure.fishingEquipment.maxLength 
                            && $v.adventure.fishingEquipment.minLength)"
                            >
                            Must be between 5 and 500 characters.
                        </div>
                    </div>
                    <div class="form-control">
                        <label for="cancellation" class="block-label">Cancellation</label>
                        <select name="cancellation" id="cancellation" v-model="adventure.cancellation">
                            <option :value="null" selected disabled hidden>Choose an option</option>
                            <option :value="true">Free cancellation</option>
                            <option :value="false">Owner keeps a percentage</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <div class="progress-bar">
                    <div class="bar" 
                        :style="step === 1 ? {width: 5 + '%'} : {width: 100/numSteps * (step-1) + '%'}"
                        />
                </div>
                <div class="btn-div">
                    <button @click="back" class="btn btn-back">Back</button> 
                    <button 
                        v-if="step < numSteps" 
                        @click="next" 
                        :disabled="nextDisabled()" 
                        class="btn"
                        >
                        Next
                        </button>
                    <button 
                        v-if="step === numSteps" 
                        @click="finish" 
                        :disabled="finishDisabled()" 
                        class="btn"
                        >
                        Finish
                        </button>
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
            baseInfo: {
                first: {
                    title: '',
                    address: '',
                    city: '',
                    country: '',
                    description: '',
                },
                second: {
                    rulesOfConduct: '',
                    additionalServices: '',
                },
                third: {
                    price: '',
                },
                fourth: {
                    pictures: [],
                }
            },
            vacationHome:{
                rooms: 1,
                beds: 1,
            },
            ship: {
                type: '',
                length: 1,
                engineCount: 1,
                enginePower: 1,
                maxSpeed: 1,
                navigationEquipment: '',
                fishingEquipment: '',
                capacity: 1,
                cancellation: null,
            },
            adventure: {
                fishingEquipment: '',
                fishingInstructorBio: '',
                capacity: 1,
                cancellation: null,
            },

            infocus: {
                // baseInfo
                title: true,
                address: true,
                city: true,
                country: true,
                description: true,
                rulesOfConduct: true,
                additionalServices: true,
                price: true,
                pictures: true,
                ship: {
                    type: true,
                    navigationEquipment: true,
                    fishingEquipment: true,
                },
                adventure: {
                    fishingEquipment: true,
                    fishingInstructorBio: true,
                }
            },
            step: 1,
            numSteps: 5,
            userRole: '',
        }
    },
    validations: {
        baseInfo: {
            first: {
                title: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(50),
                },
                address: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(40),
                },
                city: {
                    required,
                    minLength: minLength(2),
                    maxLength: maxLength(40),
                },
                country: {
                    required
                },
                description: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(500),
                },
            },
            second: {
                rulesOfConduct: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(200),
                },
                additionalServices: {
                    required,
                    minLength: minLength(2),
                    maxLength: maxLength(200),
                },
            },
            third: {
                price: {
                    required
                }
            }
        },
        ship: {
            type: {
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
            },
        },
        adventure: {
            fishingEquipment: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(500)
            },
            fishingInstructorBio: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(200)
            }
        }
    },
    methods: {
        addressUpdated(address) {
            this.baseInfo.first.address = address;
        },
        cityUpdated(city) {
            this.baseInfo.first.city = city;
        },
        countryUpdated(country) {
            this.baseInfo.first.country = country;
        },
        priceUpdated(price) {
            this.baseInfo.third.price = price;
            this.infocus.price = false;
		},
        picturesUpdated(pictures) {
            this.baseInfo.fourth.pictures = pictures;
        },
        roomsUpdated(rooms) {
            this.vacationHome.rooms = rooms;
        },
        bedsUpdated(beds) {
            this.vacationHome.beds = beds;
        },
        shipLengthUpdated(length) {
            this.ship.shipLength = length;
        },
        shipEngineCountUpdated(engineCount) {
            this.ship.engineCount = engineCount;
        },
        shipEnginePowerUpdated(enginePower) {
            this.ship.enginePower = enginePower;
        },
        shipCapacityUpdated(capacity) {
            this.ship.capacity = capacity;
        },
        shipMaxSpeedUpdated(maxSpeed) {
            this.ship.maxSpeed = maxSpeed;
        },
        adventureMaxCapacityUpdated(capacity){
            this.adventure.capacity = capacity;
        },
        getPictures() {
            return this.baseInfo.fourth.pictures.map(picture => 
                picture.getFileEncodeDataURL()
            );
        },
        encodeToBase64(file) {
            // Encode the file using the FileReader API
            const reader = new FileReader();
            reader.onloadend = () => {
                // Use a regex to remove data url part
                const base64String = reader.result
                    .replace('data:', '')
                    .replace(/^.+,/, '');

                console.log(base64String);
                // Logs wL2dvYWwgbW9yZ...
            };
            return reader.readAsDataURL(file);
        },
        nextDisabled() {
            if (this.step === 1 && this.$v.baseInfo.first.$invalid)
                return true;
            if (this.step === 2 && this.$v.baseInfo.second.$invalid)
                return true;
            if (this.step === 3 && this.$v.baseInfo.third.$invalid)
                return true;
            if (this.step === 4 
                && this.baseInfo.fourth.pictures.length < 5)
                return true;
            return false;
        },
        finishDisabled() {
            if (this.userRole === 'HOUSE_OWNER')
                return this.$refs.roomsInput.$v.$invalid 
                    || this.$v.baseInfo.$invalid;
            if (this.userRole === 'SHIP_OWNER')
                return this.$v.ship.$invalid
                    || this.$v.baseInfo.$invalid;
            if (this.userRole === 'FISHING_INSTRUCTOR')
                return this.$v.adventure.$invalid
                    || this.$v.baseInfo.$invalid;
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
            if (this.userRole === 'HOUSE_OWNER')
                this.postVacationHome();
            else if (this.userRole === 'SHIP_OWNER')
                this.postShip();
            else if (this.userRole === 'FISHING_INSTRUCTOR')
                this.postAdventure();
        },
        postVacationHome() {
            let vacationHomeDto = {
                title: this.baseInfo.first.title,
                address: {
                    'address': this.baseInfo.first.address, 
                    'city': this.baseInfo.first.city, 
                    'country': this.baseInfo.first.country
                    },
                description: this.baseInfo.first.description,
                rulesOfConduct: this.baseInfo.second.rulesOfConduct,
                additionalServices: this.baseInfo.second.additionalServices,
                price: this.baseInfo.third.price,
                pictures: this.getPictures(),
                rooms: this.vacationHome.rooms,
                beds: this.vacationHome.beds,
            }
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/vacation-home-owner/add-vacation-home',
                data: vacationHomeDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(function(response) {
                console.log(response);
                // notify that awaiting accept
            })
            .catch(function(error) {
                console.log(error);
            })          
        },
        postShip() {
            let shipDto = {
                title: this.baseInfo.first.title,
                address: {
                    'address': this.baseInfo.first.address, 
                    'city': this.baseInfo.first.city, 
                    'country': this.baseInfo.first.country
                    },
                description: this.baseInfo.first.description,
                rulesOfConduct: this.baseInfo.second.rulesOfConduct,
                additionalServices: this.baseInfo.second.additionalServices,
                price: this.baseInfo.third.price,
                pictures: this.getPictures(),
                type: this.ship.type,
                length: this.ship.length,
                engineCount: this.ship.engineCount,
                enginePower: this.ship.enginePower,
                maxSpeed: this.ship.maxSpeed,
                navigationEquipment: this.ship.navigationEquipment,
                fishingEquipment: this.ship.fishingEquipment,
                capacity: this.ship.capacity,
                freeCancellation: this.ship.cancellation,
            }
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship-owner/add-ship',
                data: shipDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(function(response) {
                console.log(response);
                // notify that awaiting accept
            }).catch(function(error) {
                console.log(error);
            }) 
        },
        postAdventure(){
            let adventureDto = {
                title: this.baseInfo.first.title,
                address: {
                    'address': this.baseInfo.first.address, 
                    'city': this.baseInfo.first.city, 
                    'country': this.baseInfo.first.country
                    },
                description: this.baseInfo.first.description,
                rulesOfConduct: this.baseInfo.second.rulesOfConduct,
                additionalServices: this.baseInfo.second.additionalServices,
                price: this.baseInfo.third.price,
                pictures: this.getPictures(),
                fishingEquipment: this.adventure.fishingEquipment,
                instructorBiography: this.adventure.fishingInstructorBio,
                capacity: this.adventure.capacity,
                freeCancellation: this.cancellation,
            }

            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/fishing-instructor/add-adventure',
                data: adventureDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            }).then(function(response) {
                console.log(response);
                // notify that awaiting accept
            }).catch(function(error) {
                console.log(error);
            }) 
        },
        isFocused(field) {
            return this.infocus[field]
        },
        isFocusedShip(field) {
            return this.infocus.ship[field]
        },
        isFocusedAdventure(field) {
            return this.infocus.adventure[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        inFocusShip(field) {
            this.infocus.ship[field] = true
        },
        inFocusAdventure(field) {
            this.infocus.adventure[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        outFocusShip(field) {
            this.infocus.ship[field] = false
        },
        outFocusAdventure(field) {
            this.infocus.adventure[field] = false
        },
        getClassFirst(field) {
            let cls = !this.isFocused(field) 
                && this.$v.baseInfo.first[field].$invalid ? 'alert' : '';
            return cls;
        },
        getClassSecond(field) {
            let cls = !this.isFocused(field) 
                && this.$v.baseInfo.second[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholderFirst(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) 
                && this.$v.baseInfo.first[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        },
        getPlaceholderSecond(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) 
                && this.$v.baseInfo.second[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        },
        getClassAdventure(field) {
            let cls = !this.isFocusedAdventure(field) 
                && this.$v.adventure[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholderAdventure(field, defaultPlaceholder='') {
            let placeholder = !this.isFocusedAdventure(field) 
                && this.$v.adventure[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        },
        getClassShip(field) {
            let cls = !this.isFocusedShip(field) 
                && this.$v.ship[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholderShip(field, defaultPlaceholder='') {
            let placeholder = !this.isFocusedShip(field) 
                && this.$v.ship[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },
    created() {
        this.userRole = window.localStorage.getItem('userRole')
    }
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
    height: calc(100% - 80px);
    width: 100%;
    position: relative;
}

.form > * {
    margin: 15px 0;
}

.form {
    padding: 5%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.picture-form {
    max-height: 100%;
    overflow-y: auto;
    overflow-x: hidden;
}

.form-control {
    display: block !important;
}

.form-control input, textarea {
    width: 100% !important; 
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

.alert {
    transition: 0.5s;
    border-color: red !important;
    border-width: 2px;
}



</style>