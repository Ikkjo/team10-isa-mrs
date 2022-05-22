<template>
    <div class="info-section">
        <h2>Room info</h2>
        <div class="info-items">
            <InfoItem icon="bedroom_parent" label="Rooms" :text="rentalEntity.rooms.toString()"/>
            <InfoItem icon="bed" label="beds" :text="rentalEntity.beds.toString()"/>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
export default {
    name: 'VacationHomeAdditionalInfo',
    props: ['rentalEntity'],
    components: {
        InfoItem,
    }
}
</script>

<style scoped>
</style>
