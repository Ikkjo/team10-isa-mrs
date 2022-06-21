<template>
    <div id="rental-entity-details">
        <UniversalNavBar id="nav"/>    
        <div class="info" v-if="rentalEntity">
            <RentalEntityDetails ref="basicInfo"
                :rentalEntity="rentalEntity" 
            />
            <VacationHomeAdditionalInfo
                v-if="rentalEntity.type === 'VacationHome'"
                :vacationHome="rentalEntity"
                ref="vacationHomeInfo"
                />
            <ShipAdditionalInfo
                v-if="rentalEntity.type === 'Ship'"
                :ship="rentalEntity"
                ref="shipInfo"
                />
            <AdventureAdditionalInfo
                v-if="rentalEntity.type === 'Adventure'"
                :adventure="rentalEntity"
                ref="adventureInfo"
                />
        </div>
    </div>
</template>

<script>
import UniversalNavBar from '@/components/UniversalNavBar.vue'
import RentalEntityDetails from '@/components/RentalEntityDetails.vue'
import VacationHomeAdditionalInfo from '@/components/VacationHomeAdditionalInfo.vue'
import ShipAdditionalInfo from '@/components/ShipAdditionalInfo.vue'
import AdventureAdditionalInfo from '@/components/AdventureAdditionalInfo.vue'
import axios from 'axios'
export default {
    name: 'RentalEntityDetailsView',
    components: {
        RentalEntityDetails,
        VacationHomeAdditionalInfo,
        ShipAdditionalInfo,
        AdventureAdditionalInfo,
        UniversalNavBar
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
                this.$refs.basicInfo.setRentalEntityCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                if (this.rentalEntity.type === 'VacationHome')
                    this.$refs.vacationHomeInfo.setVacationHomeCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                else if (this.rentalEntity.type === 'Ship')
                    this.$refs.shipInfo.setShipCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                else if (this.rentalEntity.type === 'Adventure')
                    this.$refs.adventureInfo.setAdventureCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
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