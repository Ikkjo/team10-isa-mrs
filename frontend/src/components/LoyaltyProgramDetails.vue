<template>
    <div class="wrapper">
        <h1>Loyalty Program Details</h1>
        <div v-if="loyaltyProgram" class="info">
            <div class="info-section">
                <div class="info-items">
                    <h2>General Info</h2>
                    <InfoItem icon="title" label="Title"
                        :text="loyaltyProgram.title"
                        @save="saveTitle"
                        :saveDisabled="$v.loyaltyProgramCopy.title.$invalid"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <div class="form-control">
                                <input type="text"
                                    v-model="loyaltyProgramCopy.title"
                                    name="title" 
                                    @focus="inFocus('title')" 
                                    @blur="outFocus('title')" 
                                    :class="getClass('title')" 
                                    :placeholder="getPlaceholder('title', loyaltyProgramCopy.title)"
                                    ref="title">
                                <div class="alert-info" 
                                    v-if="!isFocused('title') 
                                    && !$v.loyaltyProgramCopy.title.minLength 
                                    || !$v.loyaltyProgramCopy.title.maxLength"
                                    >
                                    Title must be between 4 and 20 characters.
                                </div>
                            </div>
                        </template>
                    </InfoItem>
                    <InfoItem icon="numbers" label="Available at points"
                        :text="loyaltyProgram.availableAtPoints.toString()"
                        :saveDisabled="isLoyaltyProgramAvailablAtPointsDisabled"
                        @save="saveLoyaltyProgramAvailableAtPoints"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <NumberInput 
                                @updated="loyaltyProgramAvailableAtPointsUpdated" 
                                placeholder="Required" 
                                label="" 
                                :increment="1" 
                                :minValue="0" 
                                :maxValue="100000"
                                class="number-input"
                                />
                        </template>
                    </InfoItem>
                    <InfoItem icon="palette" label="Color"
                        :text="loyaltyProgram.color"
                        :saveDisabled="isLoyaltyProgramColorDisabled"
                        @save="saveLoyaltyProgramColor"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <CompactColorPicker
                                @input="updateColor"
                                :value="loyaltyProgram.color"
                                />
                        </template>
                    </InfoItem>
                </div>
                <div class="info-items client">
                    <h2>Client Options</h2>
                    <InfoItem icon="numbers" label="Client points per reservation"
                        :text="loyaltyProgram.clientPointsPerReservation.toString()"
                        :saveDisabled="isLoyaltyProgramClientPointsPerReservationDisabled"
                        @save="saveLoyaltyProgramClientPointsPerReservation"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <NumberInput 
                                @updated="loyaltyProgramClientPointsPerReservationUpdated" 
                                placeholder="Required" 
                                label="" 
                                :increment="1" 
                                :minValue="0" 
                                :maxValue="50"
                                class="number-input"
                                />
                        </template>
                    </InfoItem>
                    <InfoItem icon="numbers" label="Client discount"
                        :text="loyaltyProgram.clientDiscount.toString()+'%'"
                        :saveDisabled="isLoyaltyProgramClientDiscountDisabled"
                        @save="saveLoyaltyProgramClientDiscount"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <NumberInput 
                                @updated="loyaltyProgramClientDiscountUpdated" 
                                placeholder="Required" 
                                label="" 
                                :increment="1" 
                                :minValue="0" 
                                :maxValue="99"
                                class="number-input"
                                />
                        </template>
                    </InfoItem>
                </div>
                <div class="info-items business-client">
                    <h2>Business Client Options</h2>
                    <InfoItem icon="numbers" label="Business client cut"
                        :text="loyaltyProgram.businessClientCut.toString()+'%'"
                        :saveDisabled="isLoyaltyProgramBusinessClientCutDisabled"
                        @save="saveLoyaltyProgramBusinessClientCut"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <NumberInput 
                                @updated="loyaltyProgramBusinessClientCutUpdated" 
                                placeholder="Required" 
                                label="" 
                                :increment="1" 
                                :minValue="0" 
                                :maxValue="99"
                                class="number-input"
                                />
                        </template>
                    </InfoItem>
                    <InfoItem icon="numbers" label="Business client points per reservation"
                        :text="loyaltyProgram.businessClientPointsPerReservation.toString()"
                        :saveDisabled="isLoyaltyProgramBusinessClientPointsPerReservationDisabled"
                        @save="saveLoyaltyProgramBusinessClientPointsPerReservation"
                        @cancelClicked="cancel">
                        <template slot="edit">
                            <NumberInput 
                                @updated="loyaltyProgramBusinessClientPointsPerReservationUpdated" 
                                placeholder="Required" 
                                label="" 
                                :increment="1" 
                                :minValue="0" 
                                :maxValue="50"
                                class="number-input"
                                />
                        </template>
                    </InfoItem>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
import NumberInput from '@/components/NumberInput.vue'
import CompactColorPicker from 'vue-color/src/components/Compact.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
import axios from 'axios'

export default {
    name: 'LoyaltyProgramDetails',
    components: {
        CompactColorPicker,
        InfoItem,
        NumberInput
    },
    data() {
        return {
            loyaltyProgram: null,
            loyaltyProgramCopy: null,
            dates: null,
            infocus: {
                loyaltyProgram: {
                    title: true,
                }
            },
        }
    },
    validations: {
        loyaltyProgramCopy: {
            title: {
                required,
                minLength: minLength(4),
                maxLength: maxLength(20),
            }
        }
    },
    methods: {
        updateColor(value) {
            this.loyaltyProgramCopy.color = value.hex;
        },
        cancel() {
            this.loyaltyProgramCopy = JSON.parse(JSON.stringify(this.loyaltyProgram));
        },
        isFocused(field) {
            return this.infocus.loyaltyProgram[field]
        },
        inFocus(field) {
            this.infocus.loyaltyProgram[field] = true
        },
        outFocus(field) {
            this.infocus.loyaltyProgram[field] = false
        },
        getClass(field) {
            return !this.isFocused(field) && this.$v.loyaltyProgramCopy[field].$invalid ? 'alert' : '';
        },
        getPlaceholder(field, defaultPlaceholder='') {
            return !this.isFocused(field) && this.$v.loyaltyProgramCopy[field].$invalid ? 'Required' : defaultPlaceholder;
        },
        saveTitle() {
            if (this.loyaltyProgramCopy.title !== this.loyaltyProgram.title) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id+'/title',
                    data: this.loyaltyProgram.title,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.title = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        saveLoyaltyProgramAvailableAtPoints() {
            if (this.loyaltyProgramCopy.availableAtPoints !== this.loyaltyProgram.availableAtPoints) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id
                        +'/available-at-points/'+this.loyaltyProgram.availableAtPoints,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.availableAtPoints = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        loyaltyProgramAvailableAtPointsUpdated(value) {
            this.loyaltyProgramCopy.availableAtPoints = value;
        },
        saveLoyaltyProgramClientDiscount() {
            if (this.loyaltyProgramCopy.clientDiscount !== this.loyaltyProgram.clientDiscount) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id
                        +'/client-discount/'+this.loyaltyProgram.clientDiscount,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.clientDiscount = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        loyaltyProgramClientDiscountUpdated(value) {
            this.loyaltyProgramCopy.clientDiscount = value;
        },
        saveLoyaltyProgramBusinessClientCut() {
            if (this.loyaltyProgramCopy.businessClientCut !== this.loyaltyProgram.businessClientCut) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id
                        +'/business-client-cut/'+this.loyaltyProgram.businessClientCut,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.businessClientCut = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        loyaltyProgramBusinessClientCutUpdated(value) {
            this.loyaltyProgram.businessClientCut = value;
        },
        saveLoyaltyProgramBusinessClientPointsPerReservation() {
            if (this.loyaltyProgramCopy.businessClientPointsPerReservation !== this.loyaltyProgram.businessClientPointsPerReservation) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id
                        +'/business-client-points-per-reservation/'+this.loyaltyProgram.businessClientPointsPerReservation,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.businessClientPointsPerReservation = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        loyaltyProgramBusinessClientPointsPerReservationUpdated(value) {
            this.loyaltyProgram.businessClientPointsPerReservation = value;
        },
        saveLoyaltyProgramClientPointsPerReservation() {
            if (this.loyaltyProgramCopy.clientPointsPerReservation !== this.loyaltyProgram.clientPointsPerReservation) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id
                        +'/client-points-per-reservation/'+this.loyaltyProgram.clientPointsPerReservation,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.clientPointsPerReservation = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        },
        loyaltyProgramClientPointsPerReservationUpdated(value) {
            this.loyaltyProgram.clientPointsPerReservation = value;
        },
        saveLoyaltyProgramColor() {
            if (this.loyaltyProgramCopy.color !== this.loyaltyProgram.color) {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/update/'+this.loyaltyProgram.id+'/title',
                    data: this.loyaltyProgram.color,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                    })
                    .then((response) => {
                        console.log(response);
                        if (response.status >= 400)
                            alert("Title Invalid")
                        else
                            this.loyaltyProgram.color = response.data
                    })
                    .catch((error) => {
                        alert("Title Invalid")
                        console.log(error);
                    });
            }
        }
    },
    computed: {
        isLoyaltyProgramAvailablAtPointsDisabled() {
            return !this.loyaltyProgram.availableAtPoints > 0;
        },
        isLoyaltyProgramClientDiscountDisabled() {
            return this.loyaltyProgram.clientDiscount < 0 && this.loyaltyProgram.clientDiscount > 100;
        },
        isLoyaltyProgramBusinessClientCutDisabled() {
            return this.loyaltyProgram.businessClientCut < 0 && this.loyaltyProgram.businessClientCut > 100;
        },
        isLoyaltyProgramBusinessClientPointsPerReservationDisabled() {
            return this.loyaltyProgram.businessClientPointsPerReservation < 0 &&
                this.loyaltyProgram.businessClientPointsPerReservation > 50;
        },
        isLoyaltyProgramClientPointsPerReservationDisabled() {
            return this.loyaltyProgram.clientPointsPerReservation < 0 &&
                this.loyaltyProgram.clientPointsPerReservation > 50;
        },
        isLoyaltyProgramColorDisabled() {
            return this.loyaltyProgram.color === this.loyaltyProgramCopy.color;
        }
    },
    created() {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/loyalty-programs/'+this.$route.params.id,
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        })
        .then((response) => {
            this.loyaltyProgram = response.data;
            this.loyaltyProgramCopy = JSON.parse(JSON.stringify(this.loyaltyProgram));
        })
        .catch((error) => {
            alert("Couldn't fetch cards. See console for more info.")
            console.log(error);
        });
    }
}
</script>

<style>
.info-section {
    margin-top: 10px;
}

.info {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.info-items > * {
    padding: 20px 0;
    border-bottom: 1px solid #EBEBEB;
}

.info-section {
    width: 600px;
    min-width: 300px;
    max-width: 100%;
}

.form-control {
    width: 100%;
    display: inline-block;
    margin-top: 10px;
    margin-bottom: 5px;
}

.name-form {
    max-width: 50%;
}


.form-control input {
    width: 95% !important;
    display: block;
}

label {
    font-size: 0.9rem;
    color: grey;
    display: inline-block;
}

.block-form {
    display: block;
    margin-top: 10px;
    margin-bottom: 5px;
}

.btn-circle {
    flex-shrink: 0 !important;
}
</style>