<template>
    <div id="rooms-input">
        <div class="form-control">
            <label for="rooms">Rooms</label>
            <div class="container">
                <button @click="decreaseRooms()" :disabled="rooms <= 0" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="rooms" @change="updateRooms" type="number" name="rooms" min=0 :max=maxRooms>   
                <button @click="increaseRooms()" :disabled="rooms >= maxRooms" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
        </div>
        <div class="form-control">
            <label for="beds">Beds</label>
            <div class="container">
                <button @click="decreaseBeds()" :disabled="beds <= 0" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="beds" @change="updateBeds" type="number" name="beds" min=0 :max=maxBeds>    
                <button @click="increaseBeds()" :disabled="beds >= maxBeds" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
           
        </div>
    </div>
</template>

<script>
export default {
    name: 'RoomsInput',
    data() {
        return {
            rooms: 0,
            beds: 0,
            maxRooms: 20,
            maxBeds: 100,
        }
    },
    methods: {
        increaseRooms() {
            if (this.rooms < this.maxRooms) this.rooms += 1;
            this.updateRooms();
        },
        decreaseRooms() {
            if (this.rooms > 0) this.rooms -= 1;
            this.updateRooms();
        },
        increaseBeds() {
            if (this.beds < this.maxBeds) this.beds += 1;
            this.updateBeds();
        },
        decreaseBeds() {
            if (this.beds > 0) this.beds -= 1;
            this.updateBeds();
        },
        updateRooms() {
            this.$emit('updated:rooms', this.rooms);
        },
        updateBeds() {
            this.$emit('updated:beds', this.beds);
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


</style>