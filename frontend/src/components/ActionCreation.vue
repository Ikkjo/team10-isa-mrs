<template>
    <div>
        <div class="form">
            <div class="form-control">
                <h1>Create an Action</h1>
                 <div class="form-control">
                    <label>Expiration date</label>
                    <DatePicker
                        color="yellow"
                        v-model="expiresOn"
                        :min-date='new Date()'
                        @input="expiresOnUpdated">
                        <template v-slot="{ inputValue, inputEvents}">
                            <input :value="inputValue" v-on="inputEvents"/>
                        </template>
                    </DatePicker>
                </div>
                <div class="form-control">
                    <label for="rules-of-conduct">Availability</label>
                    <DatePicker
                        color="yellow"
                        class="date-picker"
                        :is-range="true"
                        v-model="dateRange"
                        :min-date='new Date()'
                        :disabled-dates="disabledDates"
                        :is-expanded="true"
                        @input="dateRangeUpdated" />
                </div>
                <div class="form-control price-form">
                    <label>Price</label>
                    <PriceInput @updated="priceUpdated" />
                </div>
                <div class="form-control">
                    <label>Max persons</label>
                    <NumberInput
                        @updated="maxPersonsUpdated"
                        :increment="1" 
                        :minValue="1" 
                        :maxValue="100"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PriceInput from '@/components/PriceInput.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import NumberInput from '@/components/NumberInput.vue'
import axios from 'axios'

export default {
    name: 'ActionCreation',
    props: ['id'],
    components: {
        PriceInput,
        DatePicker,
        NumberInput,
    },
    data() {
        return {
            dateRange: null,
            disabledDates: [],
            expiresOn: null,
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
        expiresOnUpdated() {
            this.$emit('updated:expiresOn', this.expiresOn)
        }
    },
    mounted() {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.id+'/taken-dates',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                let disabledDates = response.data.map(d => ({ start: new Date(d), end: new Date(d) }))
                console.log(disabledDates);
                this.disabledDates = disabledDates;
            })
            .catch(function(error) {
                console.log(error);
            })
    },
}
</script>

<style scoped>

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