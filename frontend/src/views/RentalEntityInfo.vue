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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/title/'+this.rentalEntity.id,
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/address/'+this.rentalEntity.id,
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/description/'+this.rentalEntity.id,
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/rules-of-conduct/'+this.rentalEntity.id,
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/additional-services/'+this.rentalEntity.id,
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/update/price/'+this.rentalEntity.id,
                data: parseInt(price),
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
        }
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