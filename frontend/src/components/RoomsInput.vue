<template>
    <div id="rooms-input">
        <div class="form-control">
            <label for="rooms">Rooms</label>
            <div class="container">
                <button @click="decreaseRooms()" :disabled="rooms <= minRooms" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="rooms" @change="updateRooms" type="number" name="rooms" @focus="inFocus('rooms')" @blur="outFocus('rooms')" :class="getClass('rooms')" :placeholder="getPlaceholder('rooms')">   
                <button @click="increaseRooms()" :disabled="rooms >= maxRooms" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
            <div v-show="!isFocused('rooms') && $v.rooms.$invalid" class="alert-label">Value must be between {{minRooms}} and {{maxRooms}}</div> 
        </div>
        <div class="form-control">
            <label for="beds">Beds</label>
            <div class="container">
                <button @click="decreaseBeds()" :disabled="beds <= minBeds" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="beds" @change="updateBeds" type="number" name="beds" @focus="inFocus('beds')" @blur="outFocus('beds')" :class="getClass('beds')" :placeholder="getPlaceholder('beds')">
                <button @click="increaseBeds()" :disabled="beds >= maxBeds" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
            <div v-show="!isFocused('beds') && $v.beds.$invalid" class="alert-label">Value must be between {{minBeds}} and {{maxBeds}}</div>    
        </div>
    </div>
</template>

<script>
import {required, between} from 'vuelidate/lib/validators'
export default {
    name: 'RoomsInput',
    data() {
        return {
            rooms: 1,
            beds: 1,
            minRooms: 1,
            maxRooms: 20,
            minBeds: 1,
            maxBeds: 100,
            infocus: {
                rooms: true,
                beds: true,
            },
        }
    },
    validations: {
        rooms: {
            required,
            between: between(1, 20),
        },
        beds: {
            required,
            between: between(1, 100),
        },
    },
    methods: {
        increaseRooms() {
            if (this.rooms < this.maxRooms) this.rooms += 1;
            this.updateRooms();
        },
        decreaseRooms() {
            if (this.rooms > this.minRooms) this.rooms -= 1;
            this.updateRooms();
        },
        increaseBeds() {
            if (this.beds < this.maxBeds) this.beds += 1;
            this.updateBeds();
        },
        decreaseBeds() {
            if (this.beds > this.minBeds) this.beds -= 1;
            this.updateBeds();
        },
        updateRooms() {
            this.$emit('updated:rooms', this.rooms);
        },
        updateBeds() {
            this.$emit('updated:beds', this.beds);
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
            let cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, placeholder='Required', defaultPlaceholder='') {
            let newPlaceholder = !this.isFocused(field) && this.$v[field].$invalid ? placeholder : defaultPlaceholder;
            return newPlaceholder;
        }
    },
}
</script>

<style scoped>
#rooms-input {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.container {
    display: flex;
    align-items: center;
}

.form-control {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form-control:last-child {
    margin-top: 20px
}

.form-control input {
    width: 240px;
    margin: 0 10px 0 10px;
}

.btn-circle {
    width: 33px;
    height: 33px;
    border: none;
    background: var(--orange-primary, orange);
    color: white;
    border-radius: 50%;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    transition: 0.4s;
}

.btn-circle:disabled {
    background-color: lightgray;
}

.alert-label {
    color: gray;
    margin-top: 5px;
}

</style>