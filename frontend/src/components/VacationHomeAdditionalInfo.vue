<template>
    <div class="info-section">
        <h2>Room info</h2>
        <div class="info-items">
            <InfoItem icon="bedroom_parent" label="Rooms"
                :text="rentalEntity.rooms.toString()"
                :saveDisabled="isRoomsSaveDisabled"
                @saveClicked="saveRooms">
                <template slot="edit">
                    <RoomsInput 
                        @updated:rooms="roomsUpdated" 
                        ref="roomsInput"
                        :showBed="false"
                    />
                </template>
            </InfoItem>
            <InfoItem icon="bed" label="beds"
             :text="rentalEntity.beds.toString()"
             :saveDisabled="isBedsSaveDisabled"
             @saveClicked="saveBeds">
                 <template slot="edit">
                    <RoomsInput 
                        @updated:beds="bedsUpdated" 
                        ref="roomsInput"
                        :showRoom="false"
                    />
                </template>
            </InfoItem>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
import RoomsInput from '@/components/RoomsInput.vue'
export default {
    name: 'VacationHomeAdditionalInfo',
    props: ['rentalEntity'],
    components: {
        InfoItem,
        RoomsInput
    },
    data() {
        return {
            rentalEntityCopy: {
                rooms: 1,
                beds: 1,
            },
        }
    },
    methods: {
        roomsUpdated(rooms) {
            this.rentalEntityCopy.rooms = rooms;
        },
        bedsUpdated(beds) {
            this.rentalEntityCopy.beds = beds;
        },
        setRentalEntityCopy(rentalEntity) {
            this.rentalEntityCopy = rentalEntity
        },
        saveRooms() {
            this.$emit('update:rooms', this.rentalEntityCopy.rooms)
        },
        saveBeds() {
            this.$emit('update:beds', this.rentalEntityCopy.beds)
        }
    },
    computed: {
        isRoomsSaveDisabled() {
            return this.rentalEntityCopy.rooms < 1 || this.rentalEntityCopy.rooms > 20 
        },
        isBedsSaveDisabled() {
            return this.rentalEntityCopy.beds < 1 || this.rentalEntityCopy.beds > 100 
        }
        
    }
}
</script>

<style scoped>
</style>
