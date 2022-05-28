<template>
    <div id="rental-entity-info">
        <BusinessClientNavBar id="nav"/>    
        <div class="info">
            <RentalEntityBasicInfo ref="basicInfo"
                :rentalEntity="rentalEntity" 
                @update:title="updateTitle" 
                @update:address="updateAddress"
                @update:description="updateDescription"
                @update:rulesOfConduct="updateRulesOfConduct"
                @update:additionalServices="updateAdditionalServices"
                @update:price="updatePrice"
            />
            <VacationHomeAdditionalInfo
                v-if="userRole === 'HOUSE_OWNER'"
                :rentalEntity="rentalEntity"
                ref="vacationHomeInfo"
                @update:rooms="updateRooms"
                @update:beds="updateBeds"/>
            <ShipAdditionalInfo
                v-if="userRole === 'SHIP_OWNER'"
                :ship="rentalEntity"
                ref="shipInfo"
                @update:shipType="updateShipType"
                @update:shipLength="updateShipLength"
                @update:shipEngineCount="updateShipEngineCount"
                @update:shipEnginePower="updateShipEnginePower"
                @update:shipMaxSpeed="updateShipMaxSpeed"
                @update:shipNavigationEquipment="updateShipNavigationEquipment"
                @update:shipFishingEquipment="updateShipFishingEquipment"
                @update:shipCapacity="updateShipCapacity"
                @update:shipCancellation="updateShipCancellation"/>
        </div>

    </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import RentalEntityBasicInfo from '@/components/RentalEntityBasicInfo.vue'
import VacationHomeAdditionalInfo from '@/components/VacationHomeAdditionalInfo.vue'
import ShipAdditionalInfo from '@/components/ShipAdditionalInfo.vue'
import axios from 'axios'
export default {
    name: 'RentalEntityInfo',
    components: {
        BusinessClientNavBar,
        RentalEntityBasicInfo,
        VacationHomeAdditionalInfo,
        ShipAdditionalInfo
    },
    data() {
        return {
            userRole: '',
            rentalEntity: {
                pictures: [],
                address: '',
                beds: 0,
                rooms: 0,
                title: ''
            },
        }
    },
    methods: {
        updateTitle(title) {
            console.log(title)
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/title',
                data: title,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Title Invalid")
                    else
                        this.rentalEntity.title = response.data
                })
                .catch((error) => {
                    alert("Title Invalid")
                    console.log(error);
                }) 
        },
        updateAddress(address) {
            console.log(address)
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/address',
                data: address,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Address Invalid")
                    else
                        this.rentalEntity.address = response.data
                })
                .catch((error) => {
                    alert("Address Invalid")
                    console.log(error);
                }) 
        },
        updateDescription(description) {
            console.log(description)
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/description',
                data: description,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Description Invalid")
                    else
                        this.rentalEntity.description = response.data
                })
                .catch((error) => {
                    alert("Description Invalid")
                    console.log(error);
                }) 
        },
        updateRulesOfConduct(rulesOfConduct) {
            console.log(rulesOfConduct)
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/rules-of-conduct',
                data: rulesOfConduct,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Rules of Conduct Invalid")
                    else
                        this.rentalEntity.rulesOfConduct = response.data
                })
                .catch((error) => {
                    alert("Rules of Conduct Invalid")
                    console.log(error);
                })
        },
        updateAdditionalServices(additionalServices) {
            console.log(additionalServices)
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/additional-services',
                data: additionalServices,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("AdditionalServices Invalid")
                    else
                        this.rentalEntity.additionalServices = response.data
                })
                .catch((error) => {
                    alert("AdditionalServices Invalid")
                    console.log(error);
                }) 
        },
        updatePrice(price) {
            console.log(parseInt(price))
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/'+this.rentalEntity.id+'/price/'+price,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Price Invalid")
                    else
                        this.rentalEntity.price = response.data
                })
                .catch((error) => {
                    alert("Price Invalid")
                    console.log(error);
                }) 
        },
        updateRooms(rooms) {
            console.log("updating rooms...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/vacation-homes/update/'+this.rentalEntity.id+'/rooms/'+rooms,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Rooms Invalid")
                    else
                        this.rentalEntity.rooms = response.data
                })
                .catch((error) => {
                    alert("Rooms Invalid")
                    console.log(error);
                }) 
        },
        updateBeds(beds) {
            console.log("updating beds...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/vacation-homes/update/'+this.rentalEntity.id+'/beds/'+beds,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Beds Invalid")
                    else
                        this.rentalEntity.beds = response.data
                })
                .catch((error) => {
                    alert("Beds Invalid")
                    console.log(error);
                }) 
        },
        updateShipType(type) {
            console.log("updating ship type...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/type',
                data: type,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Type Invalid")
                    else
                        this.rentalEntity.type = response.data
                })
                .catch((error) => {
                    alert("Ship Type Invalid")
                    console.log(error);
                }) 

        },
        updateShipLength(length) {
            console.log("updating ship length..")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/length/'+length,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship length Invalid")
                    else
                        this.rentalEntity.length = response.data
                })
                .catch((error) => {
                    alert("Ship length Invalid")
                    console.log(error);
                }) 

        },
        updateShipEngineCount(engineCount) {
            console.log("updating ship engine count...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/engine-count/'+engineCount,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Engine Count Invalid")
                    else
                        this.rentalEntity.engineCount = response.data
                })
                .catch((error) => {
                    alert("Ship Engine Count Invalid")
                    console.log(error);
                }) 

        },
        updateShipEnginePower(enginePower) {
            console.log("updating ship engine power...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/engine-power/'+enginePower,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Engine Power Invalid")
                    else
                        this.rentalEntity.enginePower = response.data
                })
                .catch((error) => {
                    alert("Ship Engine Power Invalid")
                    console.log(error);
                }) 

        },
        updateShipMaxSpeed(maxSpeed) {
            console.log("updating ship max speed...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/max-speed/'+maxSpeed,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Max Speed Invalid")
                    else
                        this.rentalEntity.maxSpeed = response.data
                })
                .catch((error) => {
                    alert("Ship Max Speed Invalid")
                    console.log(error);
                }) 

        },
        updateShipNavigationEquipment(navigationEquipment) {
            console.log("updating ship navigation equipment..")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/navigation-equipment',
                data: navigationEquipment,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Navigation Equipment Invalid")
                    else
                        this.rentalEntity.navigationEquipment = response.data
                })
                .catch((error) => {
                    alert("Ship Navigation Equipment Invalid")
                    console.log(error);
                }) 

        },
        updateShipFishingEquipment(fishingEquipment) {
            console.log("updating ship fishing equipment..")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/fishing-equipment',
                data: fishingEquipment,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Fishing Equipment Invalid")
                    else
                        this.rentalEntity.fishingEquipment = response.data
                })
                .catch((error) => {
                    alert("Ship Fishing Equipment Invalid")
                    console.log(error);
                }) 

        },
        updateShipCapacity(capacity) {
            console.log("updating ship capcity...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/capacity/'+capacity,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Capacity Invalid")
                    else
                        this.rentalEntity.capacity = response.data
                })
                .catch((error) => {
                    alert("Ship Capacity Invalid")
                    console.log(error);
                }) 

        },
        updateShipCancellation(cancellation){
            console.log("updating ship...")
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/ship/update/'+this.rentalEntity.id+'/free-cancellation/'+cancellation,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status >= 400)
                        alert("Ship Cancellation Invalid")
                    else
                        this.rentalEntity.freeCancellation = response.data
                })
                .catch((error) => {
                    alert("Ship Cancellation Invalid")
                    console.log(error);
                }) 

        },
    },
    created() {
        this.userRole = window.localStorage.getItem('userRole')
        axios
            .get(process.env.VUE_APP_BASE_URL+"/api/v1/rental-entity/"+this.$route.params.id,
            { headers: { Authorization: 'Bearer ' + window.localStorage.getItem("jwt") }
            })
            .then((response) => {
                console.log(response.data)
                this.rentalEntity = response.data
                this.$refs.basicInfo.setRentalEntityCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                if (this.userRole === 'HOME_OWNER')
                    this.$refs.vacationHomeInfo.setRentalEntityCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                else if (this.userRole === 'SHIP_OWNER')
                    this.$refs.shipInfo.setShipCopy(JSON.parse(JSON.stringify(this.rentalEntity)));
                // TODO: FISHING_INSTRUCTOR
            })
            .catch(function(error) {
                console.log(error)
            })
        
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