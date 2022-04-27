<template>
    <div id="rooms-input">
        <div class="form-control">
            <label for="num-rooms">Rooms</label>
            <input v-model="numRooms" @change="addRoom" type="number" name="num-rooms">   
        </div>
        <div class="form-control">
            <label for="room">Room</label>
            <select :disabled="numRooms == 0" v-model="selectedRoom" @change="onSelectedRoomChange" name="room" id="room">
                <option v-for="i in Number(numRooms)" :key="i" :value="'Room '+ i" >Room {{i}}</option>
            </select>
        </div>
        <div class="form-control">
            <label for="beds">Beds</label>
            <input @change="onBedChange" type="number" name="beds" id="beds" :value="bedInputValue">
        </div>
    </div>
</template>

<script>
export default {
    name: 'RoomsInput',
    data() {
        return {
            numRooms: 0,
            rooms: [],
            selectedRoom: "Room 1",
            bedInputValue: 0
        }
    },
    methods: {
        addRoom(event) {
            if (event.target.value > this.rooms.length)
                this.rooms.push({name: 'Room '+ (this.rooms.length+1), beds: 0});
            else if (event.target.value < this.rooms.length)
                if (this.rooms.length > 0) this.rooms.pop()
            if (this.numRooms == 0) this.selectedRoom = "Room 1"
           
        },
        onSelectedRoomChange() {
            this.bedInputValue = this.getSelectedRoom().beds;
        },
        onBedChange(event) {
            this.getSelectedRoom().beds = event.target.value.trim();
            this.bedInputValue = event.target.value;
                
        },
        getSelectedRoom() {
            return this.rooms[Number(this.selectedRoom.slice(4,))-1];
        }
    },
}
</script>

<style>
#rooms-input {
    display: flex;
    align-items: center;
}

.form-control {
    display: flex;
    flex-direction: column;
    margin-left: 10px;
}

.form-control select {
    min-width: 100px;
}

</style>