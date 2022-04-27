<template>
    <div id="rooms-input">
        <div class="form-control">
            <label for="num-rooms">Rooms</label>
            <input  v-model="numRooms" type="number" name="num-rooms">   
        </div>
        <div class="form-control">
            <label for="room">Room</label>
            <select v-model="selectedRoom" @change="onChange($event)" name="room" id="room">
                <option v-for="i in Number(numRooms)" :key="i" :value="'Room '+ i" @select="selectedRoom = i">Room {{i}}</option>
            </select>
        </div>
        <div class="form-control">
            <label for="beds">Beds</label>
            <input type="number" name="beds" id="beds" :placeholder="bedPlaceholder" :value="bedInputValue">
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
        onChange(event) {
            if (this.selectedRoom != event.target.value) this.bedInputValue = 0
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