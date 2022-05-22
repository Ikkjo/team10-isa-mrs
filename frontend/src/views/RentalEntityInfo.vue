<template>
    <div id="rental-entity-info">
        <BusinessClientNavBar id="nav"/>    
        <div class="info">
            <RentalEntityBasicInfo :rentalEntity="rentalEntity" />
            <VacationHomeAdditionalInfo v-if="userRole === 'HOUSE_OWNER'" :rentalEntity="rentalEntity"/>
            <ShipAdditionalInfo v-if="userRole === 'SHIP_OWNER'" :rentalEntity="rentalEntity"/>
        </div>

    </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import RentalEntityBasicInfo from '@/components/RentalEntityBasicInfo.vue'
import VacationHomeAdditionalInfo from '@/components/VacationHomeAdditionalInfo.vue'
import ShipAdditionalInfo from '@/components/ShipAdditionalInfo.vue'
export default {
    name: 'RentalEntityInfo',
    props: ['rentalEntity'],
    components: {
        BusinessClientNavBar,
        RentalEntityBasicInfo,
        VacationHomeAdditionalInfo,
        ShipAdditionalInfo
    },
    data() {
        return {
            userRole: '',
        }
    },
    created() {
        this.userRole = window.localStorage.getItem('userRole')
    }

}
</script>

<style>
#rental-entity-info {
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
}
</style>