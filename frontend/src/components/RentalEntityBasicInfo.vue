<template>
    <div v-if="rentalEntityCopy" class="info-section">
        <PictureCollage :pictures="rentalEntity.pictures" @update="savePictures"/>
        <h2>Basic info</h2>
        <div class="info-items">
            <InfoItem icon="title" label="Title"
                :text="rentalEntity.title"
                @save="saveTitle"
                :saveDisabled="$v.rentalEntityCopy.title.$invalid"
                @cancelClicked="cancel">
                <template slot="edit">
                    <div class="form-control">
                        <!-- <label for="first-name">First Name</label> -->
                        <input type="text"
                            v-model="rentalEntityCopy.title"
                            name="title" 
                            @focus="inFocus('title')" 
                            @blur="outFocus('title')" 
                            :class="getClass('title')" 
                            :placeholder="getPlaceholder('title', rentalEntityCopy.title)"
                            ref="title">
                        <div class="alert-info" 
                            v-if="!isFocused('title') 
                            && !$v.rentalEntityCopy.title.minLength 
                            || !$v.rentalEntityCopy.title.maxLength"
                            >
                            Title must be between 5-50 characters.
                        </div>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="house" label="Address"
                :text="rentalEntity.address.address+', '+rentalEntity.address.city+', '+rentalEntity.address.country"
                @save="saveAddress"
                :saveDisabled="$v.rentalEntityCopy.address.$invalid"
                @cancelClicked="cancel"
                >
                <template slot="edit">
                    <div class="block-form">
                        <AddressInput
                        @update:address="updateAddress"
                        @update:city="updateCity"
                        @update:country="updateCountry"
                        :validate="true"/>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="description" label="Description"
                :text="rentalEntity.description"
                @save="saveDescription"
                :saveDisabled="$v.rentalEntityCopy.description.$invalid"
                @cancelClicked="cancel"
            >
                <template slot="edit">
                    <div class="form-control">
                        <!-- <label for="description" class="block-label">Description</label> -->
                        <textarea 
                            name="description" 
                            id="description" 
                            cols="30" rows="4" 
                            v-model="rentalEntityCopy.description"
                            @focus="inFocus('description')" 
                            @blur="outFocus('description')" 
                            :class="getClass('description')" 
                            :placeholder="getPlaceholder('description')"
                        />
                        <div class="alert-info" 
                            v-if="!isFocused('description') 
                            && !($v.rentalEntityCopy.description.minLength 
                            && $v.rentalEntityCopy.description.maxLength)"
                        >
                            Must be between 5 to 500 characters.
                        </div>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="rule" label="Rules of Conduct"
                :text="rentalEntity.rulesOfConduct"
                @save="saveRulesOfConduct"
                :saveDisabled="$v.rentalEntityCopy.rulesOfConduct.$invalid"
                @cancelClicked="cancel"
            >
                <template slot="edit">
                    <div class="form-control">
                        <!-- <label for="rules-of-conduct">Rules of conduct</label> -->
                        <textarea 
                            v-model="rentalEntityCopy.rulesOfConduct" 
                            name="rules-of-conduct" 
                            id="rules-of-conduct" 
                            cols="30" rows="4" 
                            @focus="inFocus('rulesOfConduct')" 
                            @blur="outFocus('rulesOfConduct')" 
                            :class="getClass('rulesOfConduct')" 
                            :placeholder="getPlaceholder('rulesOfConduct')"
                            />
                        <div class="alert-info" 
                            v-if="!isFocused('rulesOfConduct') 
                            && !($v.rentalEntityCopy.rulesOfConduct.minLength 
                            && $v.rentalEntityCopy.rulesOfConduct.maxLength)"
                            >
                            Must be between 5 to 200 characters.
                        </div>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="add_circle" label="Additional services"
                :text="rentalEntity.additionalServices"
                @save="saveAdditionalServices"
                :save="$v.rentalEntityCopy.additionalServices.$invalid"
                @cancelClicked="cancel"
            >
                <template slot="edit">
                     <div class="form-control">
                        <!-- <label for="additional-services" class="block-label">Additional Services</label> -->
                        <textarea 
                            v-model="rentalEntityCopy.additionalServices" 
                            name="additional-services" 
                            id="additional-services" 
                            cols="30" rows="4" 
                            @focus="inFocus('additionalServices')" 
                            @blur="outFocus('additionalServices')" 
                            :class="getClass('additionalServices')" 
                            :placeholder="getPlaceholder('additionalServices', 
                            'Some things you offer like: wifi, free parking, air conditioning...')"
                            />
                        <div class="alert-info" 
                            v-if="!isFocused('additionalServices') 
                            && !($v.rentalEntityCopy.additionalServices.minLength 
                            && $v.rentalEntityCopy.additionalServices.maxLength)"
                            >
                            Must be between 2 to 200 characters.
                        </div>
                     </div>
                </template>
            </InfoItem>
            <InfoItem icon="attach_money" label="Price"
                :text="'$'+rentalEntity.price"
                @save="savePrice"
                :saveDisabled="$v.rentalEntityCopy.price.$invalid"
                @cancelClicked="cancel"
            >
                <template slot="edit">
                    <PriceInput @updated="priceUpdated" class="form-control pricing"/>
                </template>
            </InfoItem>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
import PictureCollage from '@/components/PictureCollage.vue'
import AddressInput from '@/components/AddressInput.vue'
import PriceInput from '@/components/PriceInput.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
export default {
    name: 'RentalEntityBasicInfo',
    props:['rentalEntity'],
    components: {
        InfoItem,
        PictureCollage,
        AddressInput,
        PriceInput
    },
    data() {
        return {
            rentalEntityCopy: null,
            infocus: {
                title: true,
                address: true,
                description: true,
                rulesOfConduct: true,
                additionalServices: true,
                price: true,
            },
        }
    },
    validations:{
        rentalEntityCopy: {
            title: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(50),
            },
            address: {
                address: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(40)
                },
                city: {
                    required,
                    minLength: minLength(2),
                    maxLength: maxLength(40)
                },
                country: {
                    required
                },
            },
            description: {
                    required,
                    minLength: minLength(5),
                    maxLength: maxLength(500),
            },
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
            price: {
                required
            }
        },
    },
    methods: {
        saveTitle() {
            if (this.rentalEntityCopy.title !== this.rentalEntity.title)
                this.$emit('update:title', this.rentalEntityCopy.title)
        },
        saveAddress() {
            if (JSON.stringify(this.rentalEntityCopy.address) !== JSON.stringify(this.rentalEntity.address))
                this.$emit('update:address', this.rentalEntityCopy.address)
        },
        saveDescription() {
            if (this.rentalEntityCopy.description !== this.rentalEntity.description)
                this.$emit('update:description', this.rentalEntityCopy.description)
        },
        saveRulesOfConduct() {
            if (this.rentalEntityCopy.rulesOfConduct !== this.rentalEntity.rulesOfConduct)
                this.$emit('update:rulesOfConduct', this.rentalEntityCopy.rulesOfConduct)
        },
        saveAdditionalServices() {
            if (this.rentalEntityCopy.additionalServices !== this.rentalEntity.additionalServices)
                this.$emit('update:additionalServices', this.rentalEntityCopy.additionalServices)
        },
        savePrice() {
            if (this.rentalEntityCopy.price !== this.rentalEntity.price)
                this.$emit('update:price', this.rentalEntityCopy.price)
        },
        savePictures(pictures) {
            console.log("saving pictures..")
            this.$emit('update:pictures', pictures)
        },
        updateAddress(event){
            this.rentalEntityCopy.address.address = event;
        },
        updateCity(event){
            this.rentalEntityCopy.address.city = event;
        },
        updateCountry(event){
            this.rentalEntityCopy.address.country = event;
        },
        priceUpdated(event) {
            this.rentalEntityCopy.price = event;
        },
        setRentalEntityCopy(rentalEntity) {
            this.rentalEntityCopy = rentalEntity
        },
        cancel() {
            console.log("canceled")
            this.rentalEntityCopy = JSON.parse(JSON.stringify(this.rentalEntity))
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
            let cls = ''
            if (field in this.$v.rentalEntityCopy)
                cls = !this.isFocused(field) && this.$v.rentalEntityCopy[field].$invalid ? 'alert' : '';
            else
                cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = ''
            if (field in this.$v.rentalEntityCopy)
                placeholder = !this.isFocused(field) && this.$v.rentalEntityCopy[field].$invalid ? 'Required' : defaultPlaceholder;
            else
                placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            
            return placeholder;
        },
        mounted() {
            console.log("MOUNTED")
            this.rentalEntityCopy = JSON.parse(JSON.stringify(this.rentalEntity))
        }
    },
}
</script>
<style>
.pricing {
    max-width: 100% !important;
}
</style>