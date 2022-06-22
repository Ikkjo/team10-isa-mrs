<template>
    <div>
        <div class="form">
            <div class="form-control">
                <h1>Reservation</h1>
                <div class="form-control">
                    <label>Duration</label>
                    <DatePicker
                        color="yellow"
                        v-model="dateRange"
                        is-expanded
                        :min-date='new Date()'
                        :highlight="true"
                        :is-range='true'
                        :attributes='availabilityAttr'
                        :available-dates='availability'
                        >
                    </DatePicker>                  
                </div>
                <div class="form-control" v-if="showPersons">
                    <label>Persons</label>
                    <NumberInput
                        @updated="maxPersonsUpdated"
                        :increment="1" 
                        :minValue="1" 
                        :maxValue="maxPersons"/>
                </div>
                <div class="form-control price-form">
                    <label>Total price: {{calculateTotalPrice}}</label>
                </div>
                <button class="btn" :disabled="!dateRange" @click="makeReservation">Make reservation</button>
                <div class="alert-info" v-show="invalidDateRange">
                Invalid email or password.
            </div>
            </div>
        </div>
    </div>
</template>

<script>
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import NumberInput from '@/components/NumberInput.vue'
import axios from 'axios'
export default {
    name: 'ClientReservationCreation',
    props: ['id', 'pricePerNight', 'maxPersons', 'rentalEntityTitle'],
    components: {
    DatePicker,
    NumberInput
},
    data() {
        return {
            dateRange: null,
            disabledDates: [],
            availability: [],
            availabilityAttr: [],
            infocus: { email: true },
            showPersons: false,
            reservation: {
                    startDate: null,
                    endDate: null,
                    price: null,
                    clientEmail: "",
                    rentalEntityId: null,
                    rentalEntityTitle: null,
            },
            invalidDateRange: false
        }
    },
    methods: {
        priceUpdated(price) {
            this.$emit('updated:price', price)
        },
        dateRangeUpdated() {
            this.$emit('updated:dateRange', this.dateRange)
        },
        maxPersonsUpdated(maxPersons) {
            this.$emit('updated:maxPersons', maxPersons)
        },
        getTotalPrice() {
            return this.pricePerNight * this.getDateRangeCount()
        },
        makeReservation() {
            this.reservation.startDate = this.dateRange.start.getTime()
            this.reservation.endDate = this.dateRange.end.getTime()     
            this.reservation.price = this.getTotalPrice()
            this.reservation.rentalEntityId = this.id
            this.reservation.rentalEntityTitle = this.rentalEntityTitle

            console.log(this.reservation)
            axios({
                method: 'post',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/client/make-reservation',
                data: this.reservation,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                }
            })
            
        },
        getDateRangeCount() {
            
            if (this.dateRange) {
                    let difference = this.dateRange.end.getTime() - this.dateRange.start.getTime();
                    let days = Math.ceil(difference / (1000 * 3600 * 24));
                    return days < 1 ? 1 : days 
            } else {
                return 1
            }
        }
        
    },
    computed: {
        calculateTotalPrice() {
            return this.getTotalPrice()
        }
    },
    mounted() {
        this.totalPrice = this.pricePerNight
        if(this.maxPersons) {
            this.showPersons = true
        }
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.id+'/taken-dates',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                let disabledDates = response.data.map(d => ({ start: new Date(d), end: null }))
                this.disabledDates = disabledDates;
            })
            .catch(function(error) {
                console.log(error);
            })

        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.id+'/availability',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.availabilityAttr = response.data.map(d => ({
                highlight: true,
                dates: new Date(d),
            }))
                this.availability = response.data.map(d =>({
                    start: new Date(d),
                    end: new Date(d)
                    }))
            })
            .catch(function(error) {
                console.log(error);
            })
    },

}
</script>

<style>
h1 {
    margin-bottom: 5px;
}

.form-control {
    padding: 5px 0px;
    max-width: 100% !important;
    margin-top: 0 !important;
}

.form-control input,
.form-control .date-picker
{
    width: 100%;
}

.form-control label {
    margin-bottom: 5px;
}
</style>