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
/* #rental-entity-details {
    max-width: 100%;
}

h2 {
    margin-top: 20px;
}

.info {
    margin-top: calc(var(--nav-height) + 40px);
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.info-items > * {
    padding: 20px 0;
    border-bottom: 1px solid #EBEBEB;
}

.info-section {
    width: 600px;
    min-width: 300px;
    max-width: 100%;
} */
</style>