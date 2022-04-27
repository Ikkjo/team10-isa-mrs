<template>
    <div id="vacation-home-form">
        <div class="form-control">
            <label for="title">Title</label>
            <input type="text" v-model="title" name="title">
        </div>
        <addres-input :address="address" :city="city" :country="country"/>
        <rooms-input @updated="roomsUpdated"/>
        <pricing-input/>
        <div class="form-control">
            <label for="additional-services" class="block-label">Additional Services</label>
            <textarea name="additional-services" id="additional-services" cols="30" rows="4" v-model="additionalServices" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
        </div>
    </div>
</template>

<script>
import AddresInput from './AddressInput.vue'
import RoomsInput from './RoomsInput.vue'
import PricingInput from './PricingInput.vue'
export default {
    name: 'VacationHomeForm',
    components: {
        AddresInput,
        RoomsInput,
        PricingInput      
    },
    data() {
        return {
            title: '',
            address: '',
            city: '',
            country: '',
            rooms: [],
        }
    },
    methods: {
        roomsUpdated(rooms) {
            this.rooms = rooms;
        }
    }
}
</script>

<style>
#vacation-home-form {
}

</style>