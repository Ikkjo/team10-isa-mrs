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
                <textarea name="allowed" id="allowed" cols="15" rows="4" placeholder="Allowed" res></textarea>
                <textarea name="prohibited" id="prohibited" cols="15" rows="4" placeholder="Prohibited"></textarea>
            </div>
        </div>
        <div class="form-control">
            <label for="additional-services" class="block-label">Additional Services</label>
            <textarea name="additional-services" id="additional-services" cols="30" rows="4" v-model="additionalServices" placeholder="Some things you offer like: wifi, free parking, air conditioning..."></textarea>
        </div>
        <pricing-input @updated="priceUpdated" class="form-control pricing"/>
        <button class="btn">Add vacation home</button>
        <button class="btn btn-cancel">Cancel</button>
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

<style scoped>
#vacation-home-form {
    display: flex;
    flex-direction: column;
}

#vacation-home-form > * {
    margin: 15px 0;
}

.rules-text {
    display: flex;
}

.rules-text textarea {
    width: 50%;
}

.rules-text textarea:first-child {
    margin-right: 10px;
}

.pricing {
    align-self: center;
}

.btn {
    margin-top: 15px;
    width: 50%;
    align-self: center;
}

.btn-cancel {
    background-color: lightgray;
    margin-top: 0px !important;

}

.btn-cancel:hover {
    background-color: grey;
}

</style>