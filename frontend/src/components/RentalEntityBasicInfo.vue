<template>
    <div class="info-section">
        <PictureCollage :pictures="rentalEntity.pictures"/>
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
import PictureCollage from '@/components/PictureCollage.vue'
export default {
    name: 'RentalEntityBasicInfo',
    props: ['rentalEntity'],
    components: {
        InfoItem,
        PictureCollage
    },
}
</script>

<style>
</style>
