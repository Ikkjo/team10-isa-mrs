<template>
    <div class="wrapper">
        <h1>Create New Loyalty Program</h1>
        <div class="form-wrapper">
            <form @submit.prevent>
                <div class="form-control">
                    <label for="title">Title</label>
                    <input type="text"
                        v-model="loyaltyProgram.title" 
                        name="title" 
                        @focus="inFocus('title')" 
                        @blur="outFocus('title')" 
                        :class="getClass('title')" 
                        :placeholder="getPlaceholder('title')">
                    <div class="alert-info" 
                        v-if="!isFocused('title') && !($v.loyaltyProgram.title.minLength && $v.loyaltyProgram.title.maxLength)">
                                Title must be between 4 and 20 characters.
                    </div>
                </div>
                <div class="number-input">
                    <NumberInput 
                        @updated="updateAvailableAtPoints" 
                        placeholder="Required" 
                        label="Available at points" 
                        :increment="1" 
                        :minValue="0" 
                        :maxValue="100000"
                        />
                </div>
                <div class="color-picker">
                    <label>Color</label>
                    <CompactColorPicker
                        @input="updateColor"
                        value="orange"
                        />
                </div>
                <div class="number-input">
                    <NumberInput 
                        @updated="updateClientPointsPerReservation" 
                        placeholder="Required" 
                        label="Client points per reservation" 
                        :increment="1" 
                        :minValue="1" 
                        :maxValue="50"
                        />
                </div>
                <div class="number-input">
                    <NumberInput 
                        @updated="updateClientDiscount" 
                        placeholder="Required" 
                        label="Client discount" 
                        :increment="1" 
                        :minValue="0" 
                        :maxValue="100"
                        />
                </div>
                <div class="number-input">
                    <NumberInput 
                        @updated="updateBusinessClientPointsPerReservation" 
                        placeholder="Required" 
                        label="Business client points per reservation" 
                        :increment="1" 
                        :minValue="1" 
                        :maxValue="50"
                        />
                </div>
                <div class="number-input">
                    <NumberInput 
                        @updated="updateBusinessClientCut" 
                        placeholder="Required" 
                        label="Business client cut" 
                        :increment="1" 
                        :minValue="0" 
                        :maxValue="100"
                        />
                </div>
                <div class="btn-div">
                    <button class="btn"
                        @click="createLoyaltyProgram"
                        :disabled="$v.$invalid
                        || !colorIsValid
                        || !availableAtPointsIsValid
                        || !clientPointsPerReservationIsValid
                        || !clientDiscountIsValid
                        || !businessClientPointsPerReservationIsValid
                        || !businessClientCutIsValid">
                        Create Loyalty Program
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import NumberInput from '@/components/NumberInput.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
import CompactColorPicker from 'vue-color/src/components/Compact.vue'
import axios from 'axios'

export default {
    name:'LoyaltyProgramCreate',
    components: {
        NumberInput,
        CompactColorPicker,
    },
    data() {
        return {
            loyaltyProgram: {
                title: null,
                availableAtPoints: null,
                color: null,
                clientPointsPerReservation: null,
                clientDiscount: null,
                businessClientPointsPerReservation: null,
                businessClientCut: null,
            },
            infocus: {
                title: true,
            }
        }
    },
    validations: {
        loyaltyProgram: {
            title: {
                required,
                minLength: minLength(4),
                maxLength: maxLength(20),
            }
        }
    },
    methods: {
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
            return !this.isFocused(field) && this.$v.loyaltyProgram[field].$invalid ? 'alert' : '';
        },
        getPlaceholder(field, defaultPlaceholder='') {
            return !this.isFocused(field) && this.$v.loyaltyProgram[field].$invalid ? 'Required' : defaultPlaceholder;
        },
        updateAvailableAtPoints(value) {
            this.loyaltyProgram.availableAtPoints = value;
        },
        updateClientPointsPerReservation(value) {
            this.loyaltyProgram.clientPointsPerReservation = value;
        },
        updateClientDiscount(value) {
            this.loyaltyProgram.clientDiscount = value/100;
        },
        updateBusinessClientPointsPerReservation(value) {
            this.loyaltyProgram.businessClientPointsPerReservation = value;
        },
        updateBusinessClientCut(value) {
            this.loyaltyProgram.businessClientCut = value/100;
        },
        updateColor(value) {
            this.loyaltyProgram.color = value.hex;
        },
        createLoyaltyProgram() {
            let loyaltyDto = this.loyaltyProgram;
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/add',
                data: loyaltyDto,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                console.log(response.data)
                alert("Loyalty program successfully added!");
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
        }
    },
    computed: {
        colorIsValid() {
            return this.loyaltyProgram.color !== null;
        },
        availableAtPointsIsValid() {
            return this.loyaltyProgram.availableAtPoints >= 0;
        },
        clientPointsPerReservationIsValid() {
            return this.loyaltyProgram.clientPointsPerReservation > 0
                && this.loyaltyProgram.clientPointsPerReservation <= 50;
        },
        clientDiscountIsValid() {
            return this.loyaltyProgram.clientDiscount >= 0
                && this.loyaltyProgram.clientDiscount <= 1;
        },
        businessClientPointsPerReservationIsValid () {
            return this.loyaltyProgram.businessClientPointsPerReservation > 0
                && this.loyaltyProgram.businessClientPointsPerReservation <= 50;
        },
        businessClientCutIsValid() {
            return this.loyaltyProgram.businessClientCut >= 0
                && this.loyaltyProgram.businessClientCut <= 1;
        }

    },
}
</script>

<style>
.form-wrapper {
    width: 100%;
    display: flex;
    align-content: center;
    justify-content: center;
} 

.form-control {
    margin: 15px 0px;
}

.form-control input {
    width: 100%;
    height: 48px;
}

.form-control label {
    width: 100%;
}

.form-control {
    display: block;
}
.btn-div {
    margin-top: 30px;
    display:flex;
    justify-content: center;
}
.wrapper label {
    color: black !important;
}
.color-picker {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.color-picker label {
    align-self: flex-start;
}
</style>