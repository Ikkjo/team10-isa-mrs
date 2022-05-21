<template>
    <div class="info-section">
        <h2>Basic info</h2>
        <div class="info-items">
            <InfoItem icon="title" label="Title" :text="rentalEntity.title"/>
            <InfoItem icon="house" label="Address" :text="rentalEntity.address.address+', '+rentalEntity.address.city+', '+rentalEntity.address.country"/>
            <InfoItem icon="description" label="Description" :text="rentalEntity.description"/>
            <InfoItem icon="rule" label="Rules of Conduct" :text="rentalEntity.rulesOfConduct"/>
            <InfoItem icon="add_circle" label="Additional services" :text="rentalEntity.additionalServices"/>
            <InfoItem icon="attach_money" label="Price" :text="'$'+rentalEntity.price"/>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
export default {
    name: 'RentalEntityBasicInfo',
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