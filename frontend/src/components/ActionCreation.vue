<template>
    <div>
        <div class="form">
            <div class="form-control">
                <h1>Create an Action</h1>
                <div class="form-control">
                    <label for="rules-of-conduct">Availability</label>
                    <DatePicker
                        color="yellow"
                        is-expanded
                        :is-range="true"
                        v-model="dateRange"
                        :min-date='new Date()'
                        :disabled-dates="disabledDates"
                        @input="dateRangeUpdated"
                    />
                </div>
                <div class="form-control">
                    <label>Price</label>
                    <PriceInput @updated="priceUpdated" class=""/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PriceInput from '@/components/PriceInput.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import axios from 'axios'

export default {
    name: 'ActionCreation',
    props: ['id'],
    components: {
        PriceInput,
        DatePicker,
    },
    data() {
        return {
            dateRange: null,
            disabledDates: [],
        }
    },
    methods: {
        priceUpdated(price) {
            this.$emit('updated:price', price)
        },
        dateRangeUpdated() {
            this.$emit('updated:dateRange', this.dateRange)
        }
    },
    mounted() {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/'+this.id+'/availability',
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
    max-width: 100%;
}

.form-control label {
    margin-bottom: 5px;
}

</style>