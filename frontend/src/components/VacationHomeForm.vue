<template>
    <div id="vacation-home-form">
        <div class="form-control">
            <label for="title">Title</label>
            <input type="text" v-model="title" name="title">
        </div>
        <address-input :address="address" :city="city" :country="country"/>
        <rooms-input @updated="roomsUpdated"/>
        <div class="form-control rules-form">
            <label for="rules-of-conduct">Rules of conduct</label>
            <div class="rules-text">
                <textarea name="allowed" id="allowed" cols="15" rows="4"></textarea>
                <textarea name="prohibited" id="prohibited" cols="15" rows="4"></textarea>
            </div>
        </div>
        <div class="form-control">
            <label for="additional-services" class="block-label">Additional Services</label>
            <textarea name="additional-services" id="additional-services" cols="30" rows="4" v-model="additionalServices" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
        </div>
        <pricing-input @updated="priceUpdated"/>
    </div>
</template>

<script>
import AddressInput from './AddressInput.vue'
import RoomsInput from './RoomsInput.vue'
import PricingInput from './PricingInput.vue'
export default {
    name: 'VacationHomeForm',
    components: {
        AddressInput,
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
            price: 0,
            additionalServices: '',

        }
    },
    methods: {
        roomsUpdated(rooms) {
            this.rooms = rooms;
        },
        priceUpdated(price) {
            this.price = price;
        }
    }
}
</script>

<style>
#vacation-home-form {
    display: flex;
    flex-direction: column;
}

.rules-text {
    display: flex;
}

.form-control {
    margin-top: 15px;
}

</style>