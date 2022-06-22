<template>
    <div id="rental-entity-details" class="details-view">
        <UniversalNavBar id="nav"/>    
        <div class="info" v-if="rentalEntity">
            <RentalEntityDetails ref="basicDetails"
                :rentalEntity="rentalEntity" 
            />
            <VacationHomeAdditionalDetails
                v-if="rentalEntity.type === 'VacationHome'"
                :vacationHome="rentalEntity"
                ref="vacationHomeDetails"
                />
            <ShipAdditionalDetails
                v-if="rentalEntity.type === 'Ship'"
                :ship="rentalEntity"
                ref="shipDetails"
                />
            <AdventureAdditionalDetails
                v-if="rentalEntity.type === 'Adventure'"
                :adventure="rentalEntity"
                ref="adventureDetails"
                />
        </div>
    </div>
</template>

<script>
import UniversalNavBar from '@/components/UniversalNavBar.vue'
import RentalEntityDetails from '@/components/RentalEntityDetails.vue'
import VacationHomeAdditionalDetails from '@/components/VacationHomeAdditionalDetails.vue'
import ShipAdditionalDetails from '@/components/ShipAdditionalDetails.vue'
import AdventureAdditionalDetails from '@/components/AdventureAdditionalDetails.vue'
import axios from 'axios'
export default {
    name: 'RentalEntityDetailsView',
    components: {
    RentalEntityDetails,
    VacationHomeAdditionalDetails,
    AdventureAdditionalDetails,
    UniversalNavBar,
    ShipAdditionalDetails
},
    data() {
        return {
            rentalEntity: null,
            role: ''
        }
    },
    methods: {
    },
    created() {
        this.role = window.localStorage.getItem('role')
        axios
            .get(process.env.VUE_APP_BASE_URL+"/api/v1/rental-entity/"+this.$route.params.id,
            { 
            })
            .then((response) => {
                console.log(response.data)
                this.rentalEntity = response.data;
                this.$refs.basicDetails.setRentalEntityCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                if (this.rentalEntity.type === 'VacationHome')
                    this.$refs.vacationHomeDetails.setVacationHomeCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                else if (this.rentalEntity.type === 'Ship')
                    this.$refs.shipDetails.setShipCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                else if (this.rentalEntity.type === 'Adventure')
                    this.$refs.adventureDetails.setAdventureCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
            })
            .catch(function(error) {
                console.log(error)
            })
    },
}
</script>

<style>
#rental-entity-details {
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

.form-control {
    display: block;
    max-width: 90%;
    margin-top: 10px;
    margin-bottom: 5px;
}

.form-control input,
.form-control textarea {
    width: 100%;
}
</style>