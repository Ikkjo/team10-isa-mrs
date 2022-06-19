<template>
    <div v-if="vacationHomeCopy" class="info-section">
        <h2>Room info</h2>
        <div class="info-items">
            <InfoItem icon="bedroom_parent" label="Rooms"
                :text="vacationHome.rooms.toString()"
                :saveDisabled="isRoomsSaveDisabled"
                @save="saveRooms">
                <template slot="edit">
                    <RoomsInput 
                        @updated:rooms="roomsUpdated" 
                        ref="roomsInput"
                        :showBed="false"
                    />
                </template>
            </InfoItem>
            <InfoItem icon="bed" label="Beds"
             :text="vacationHome.beds.toString()"
             :saveDisabled="isBedsSaveDisabled"
             @save="saveBeds">
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
    props: ['vacationHome'],
    components: {
        InfoItem,
        RoomsInput
    },
    data() {
        return {
            vacationHomeCopy: null,
        }
    },
    methods: {
        roomsUpdated(rooms) {
            this.vacationHomeCopy.rooms = rooms;
        },
        bedsUpdated(beds) {
            this.vacationHomeCopy.beds = beds;
        },
        setVacationHomeCopy(vacationHome) {
            this.vacationHomeCopy = vacationHome
        },
        saveRooms() {
            if (this.vacationHomeCopy.rooms !== this.vacationHome.rooms)
                this.$emit('update:rooms', this.vacationHomeCopy.rooms)
        },
        saveBeds() {
            if (this.vacationHomeCopy.beds !== this.vacationHome.beds)
                this.$emit('update:beds', this.vacationHomeCopy.beds)
        }
    },
    computed: {
        isRoomsSaveDisabled() {
            return this.vacationHomeCopy.rooms < 1 || this.vacationHomeCopy.rooms > 20 
        },
        isBedsSaveDisabled() {
            return this.vacationHomeCopy.beds < 1 || this.vacationHomeCopy.beds > 100 
        }
        
    }
}
</script>

<style scoped>
</style>
