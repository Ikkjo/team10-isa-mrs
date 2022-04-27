<template>
    <div id="rooms-input">
        <div class="form-control">
            <label for="num-rooms">Rooms</label>
            <input v-model="numRooms" @change="addRoom" type="number" name="num-rooms">   
        </div>
        <div class="form-control">
            <label for="room">Room</label>
            <select v-model="selectedRoom" @change="onSelectedRoomChange" name="room" id="room">
                <option v-for="i in Number(numRooms)" :key="i" :value="'Room '+ i" @select="selectedRoom = i">Room {{i}}</option>
            </select>
        </div>
        <div class="form-control">
            <label for="beds">Beds</label>
            <input @change="onBedChange" type="number" name="beds" id="beds" :placeholder="bedPlaceholder" :value="bedInputValue">
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
            selectedRoom: null,
            bedInputValue: 0
        }
    },
    methods: {
        addRoom(event) {
             console.log(this.rooms.length);
            if (event.target.value > this.rooms.length)
                this.rooms.push({name: 'Room '+ (this.rooms.length+1), beds: 0});
            else if (event.target.value < this.rooms.length)
                if (this.rooms.length > 0) this.rooms.pop()
           
        },
        onSelectedRoomChange(event) {
            if (this.selectedRoom != event.target.value) this.bedInputValue = 0
            for (let room of this.rooms) {
                if (room.name === this.selectedRoom) {
                    this.bedInputValue = room.beds;
                }
            }
        },
        onBedChange(event) {
            for (let room of this.rooms) {
                if (room.name === this.selectedRoom) {
                    room.beds = event.target.value.trim();
                    break;
                }
            }
                
        }

        
    },
    computed: {
        bedPlaceholder() {
            return this.selectedRoom != null ? 'Beds in '+ this.selectedRoom : ''

        },
    }

}
</script>

<style>

</style>