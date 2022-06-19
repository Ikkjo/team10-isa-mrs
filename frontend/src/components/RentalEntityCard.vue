<template>
    <div id="rental-entity-card" @click="detailedView()">
        <img class="cover-photo" :src="rentalEntity.pictures[0]" alt="" srcset="">
        <div class="container">
            <div class="main-text">{{rentalEntity.title}}</div>
            <div class="desc">
                <div>{{rentalEntity.address.address}} {{rentalEntity.address.city}} {{rentalEntity.address.country}}</div>
                <div>{{rentalEntity.rooms}} rooms, {{rentalEntity.beds}} beds</div>
            </div>
            <div class="main-text">${{rentalEntity.price}} night</div>
            <div class="btn-container">
                <button v-if="isBusinessClient()" class="btn" @click.stop="showActionModal()">Add Action</button>
                <button v-if="isBusinessClient()" class="btn" @click.stop="showReservationModal()">Reserve</button>
            </div>
        </div>
        <portal to="body" v-if="isBusinessClient()">
            <!-- use the modal component, pass in the prop -->
            <ActionCreationModal
                @save="saveAction()"
                :show="isActionModalActive"
                @close="isActionModalActive=false; resetAction();"
                :buttonDisabled="actionButtonDisabled"
                class="modal">
                <template #body>
                    <ActionCreation 
                        :id="rentalEntity.id"
                        @updated:expiresOn="actionExpiresOnUpdated"
                        @updated:dateRange="actionDateRangeUpdated"
                        @updated:price="actionPriceUpdated"
                        @updated:maxPersons="actionMaxPersonsUpdated"
                        />
                </template>
            </ActionCreationModal>

            <ActionCreationModal
                @save="saveReservation()"
                :show="isReservationModalActive"
                @close="isReservationModalActive=false; resetReservation();"
                :buttonDisabled="reservationButtonDisabled"
                class="modal">
                <template #body>
                    <ReservationCreation 
                        :id="rentalEntity.id"
                        @updated:username="reservationUsernameUpdated"
                        @updated:dateRange="reservationDateRangeUpdated"
                        @updated:price="reservationPriceUpdated"
                        @updated:maxPersons="reservationMaxPersonsUpdated"/>
                </template>

            </ActionCreationModal>
      </portal>
    </div>
</template>

<script>
import ActionCreation from '@/components/ActionCreation.vue'
import ActionCreationModal from '@/components/ActionCreationModal.vue'
import axios from 'axios'
import ReservationCreation from './ReservationCreation.vue'
export default {
    name: 'RentalEntityCard',
    props: ['rentalEntity'],
    components: {
        ActionCreation,
        ActionCreationModal,
        ReservationCreation
    },
    data() {
        return {
            role: null,
            isActionModalActive: false,
            isReservationModalActive: false,
            action: {
                expiresOn: null,
                dateRange: null,
                price: null,
                maxPersons: 1,
            },
            reservation: {
                username: null,
                dateRange: null,
                price: null,
                maxPersons: 1,
            },
            show: true,
        }
    },
    methods: {
        detailedView() {
            let role = window.localStorage.getItem('role');
            if (role === null || role === 'CLIENT') {
                console.log('Shows rental entity details for client')
            }
            else if (this.isBusinessClient()) {
                this.$router.push({
                    name: 'my-listing',
                    params: {
                        id: this.rentalEntity.id,
                    }
                })
            }
        },
        isBusinessClient() {
            return ['HOUSE_OWNER','SHIP_OWNER', 'FISHING_INSTRUCTOR'].includes(window.localStorage.getItem('role'))
        },
        showActionModal() {
            this.isReservationModalActive = false;
            this.isActionModalActive = true;
        },
        showReservationModal() {
            this.isActionModalActive = false;   
            this.isReservationModalActive = true;
        },
        addAction() {
            console.log("Add action");
        },
        actionExpiresOnUpdated(expiresOn) {
            console.log("updated expires at")
            this.action.expiresOn = expiresOn.getTime()
        },
        actionPriceUpdated(price) {
            console.log("updated price")
            this.action.price = price
        },
        actionDateRangeUpdated(dateRange) {
            console.log("updated date range")
            this.action.dateRange = [dateRange.start.getTime(), dateRange.end.getTime()]
        },
        actionMaxPersonsUpdated(maxPersons) {
            console.log("updated max persons")
            this.action.maxPersons = maxPersons
        },
        reservationUsernameUpdated(username) {
            console.log("updated reservation username")
            this.reservation.username = username
        },
        reservationPriceUpdated(price) {
            console.log("updated price")
            this.reservation.price = price
        },
        reservationDateRangeUpdated(dateRange) {
            console.log("updated date range")
            this.reservation.dateRange = [dateRange.start.getTime(), dateRange.end.getTime()]
        },
        reservationMaxPersonsUpdated(maxPersons) {
            console.log("updated max persons")
            this.reservation.maxPersons = maxPersons
        },
        saveAction() {
            console.log(this.action)
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.rentalEntity.id+'/add-action',
                data: this.action,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                alert("Action added successfully")
                this.resetAction()
                this.isActionModalActive = false;
            })
            .catch((error) => {
                console.log(error);
                alert("Something went wrong")
            })
        },
        saveReservation() {
            console.log(this.reservation)
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.rentalEntity.id+'/add-reservation',
                data: this.reservation,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                alert("Reservation added successfully")
                this.resetReservation()
                this.isReservationModalActive = false;
            })
            .catch((error) => {
                console.log(error);
                alert("Something went wrong")
            })
        },
        resetAction() {
            Object.keys(this.action).forEach(key => this.action[key]=null)
            this.action.maxPersons = 1
        },
        resetReservation() {
            Object.keys(this.reservation).forEach(key => this.reservation[key]=null)
            this.reservation.maxPersons = 1
        },
    },
    created() {

    },
    computed: {
        actionButtonDisabled() {
            return  this.action.expiresOn === null
                    || this.action.dateRange === null || this.action.dateRange === []
                    || this.action.price === null || this.action.price == 0
                    || this.action.maxPersons === null || this.action.maxPersons == 0  
        },
        reservationButtonDisabled() {
            return this.reservation.username === null || this.reservation.username == ""
                    || this.reservation.dateRange === null || this.reservation.dateRange === []
                    || this.reservation.price === null || this.reservation.price == 0
                    || this.reservation.maxPersons === null || this.reservation.maxPersons == 0  
        }
    }
}
</script>

<style scoped>
#rental-entity-card {
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
    cursor: pointer;
    max-width: 100%;
}


.cover-photo {
    object-fit: cover;
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
    width: 100%;
    height: 250px;
    border-radius: 5px 5px 0px 0px;
}

.desc {
    color: grey;
    padding: 5px 0;
}

.desc :first-child {
    margin-bottom: 2px;
}

.container {
    padding: 6px 5px;
    display: block !important;
}

.btn-container {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 6px 5px;
}

.btn-container .btn:first-child {
    margin-right: 10px
}

.btn {
    width: 100%;
    margin-top: 2px;
}

</style>