<template>
    <div>
        <DatePicker
        v-model="value"
        @input="$emit('update', value)"
        :disabled-date="disabledDates"
        :multiple="true"
        value-type="x"
        class="datepicker"
        format="MM-DD-YYYY"
        />
    </div>
 
</template>

<script>
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';

export default {
    name: 'RentalEntityDatepicker',
    components: {
        DatePicker
    },
    data() {
        return {
        value: null,
        }
    },
    methods: {
        disabledDates(date) {
            return date <= new Date();
        },
    },
}
</script>

<style>

.datepicker {
    width: 100%;
}

</style>