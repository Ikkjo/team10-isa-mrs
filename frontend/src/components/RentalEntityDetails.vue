<template>
    <div class="info-section">
        <PictureCollage :pictures="rentalEntity.pictures" :editable="false"/>
        <h2>Basic info</h2>
        <div class="info-items">
            <InfoItem icon="title" label="Title"
                :text="rentalEntity.title"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
            </InfoItem>
            <InfoItem icon="attach_money" label="Price"
                :text="'$'+rentalEntity.price"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
            >
            </InfoItem>
            <InfoItem icon="house" label="Address"
                :text="rentalEntity.address.address+', '+rentalEntity.address.city+', '+rentalEntity.address.country"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
                >
                <template #body>
                    <GoogleMap 
                        :address="rentalEntity.address.address"
                        :city="rentalEntity.address.city"
                        :country="rentalEntity.address.country"
                    />
                </template>
            </InfoItem>
            <InfoItem icon="description" label="Description"
                :text="rentalEntity.description"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
            >
            </InfoItem>
            <InfoItem v-if="this.role!='CLIENT'" icon="calendar_month" label="Availability"
                :text="'Register or log in to view availability.'"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true"/>
            <!-- Currently implementing reservations -->
            <ClientReservationCreation v-else
                        :id="rentalEntity.id"
                        :pricePerNight="rentalEntity.price"
                        :maxPersons="rentalEntity.maxCapacity"
                        :rentalEntityTitle="rentalEntity.title"
                        @updated:dateRange="reservationDateRangeUpdated"
                        @updated:maxPersons="reservationMaxPersonsUpdated"/>
            <InfoItem icon="rule" label="Rules of Conduct"
                :text="rentalEntity.rulesOfConduct"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
            >
            </InfoItem>
            <InfoItem icon="add_circle" label="Additional services"
                :text="rentalEntity.additionalServices"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true">
            >
            </InfoItem>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
import PictureCollage from '@/components/PictureCollage.vue'
import ClientReservationCreation from './ClientReservationCreation.vue'
import GoogleMap from './GoogleMap.vue'
export default {
    name: 'RentalEntityDetails',
    props:['rentalEntity'],
    components: {
        InfoItem,
        PictureCollage,
        ClientReservationCreation,
        GoogleMap
    },
    data() {
        return {
            rentalEntityCopy: null,
            dates: null,
            infocus: {
                title: true,
                address: true,
                description: true,
                rulesOfConduct: true,
                additionalServices: true,
                price: true,
            },
            role: null
        }
    },
    created() {
        this.role = window.localStorage.getItem("role")
    },
    methods: {
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
    },
}
</script>
<style>
.pricing {
    max-width: 100% !important;
}
</style>