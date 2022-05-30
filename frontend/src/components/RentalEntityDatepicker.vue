<template>
    <div>
        <DatePicker
        id="date-picker"
        color="orange"
        is-expanded
        is-range
        is-required
        :min-date='new Date()'
        v-model="date"
        isDragging
        >
        </DatePicker>
        <button @click="date = null" class="btn">Clear</button>
        <button @click="save" class="btn">Save</button>
    </div>
 
</template>

<script>
import DatePicker from 'v-calendar/lib/components/date-picker.umd'

export default {
    name: 'RentalEntityDatepicker',
    components: {
        DatePicker
    },
    data() {
        return {
        days: [],
        date: new Date(),
        dates: []
        };
    },
    methods: {
        save() {
            if (!this.containsObject(this.date, this.dates)) {
                console.log('added date')
                this.dates.push(this.date)
            }
        },
        containsObject(obj, list) {
            var i;
            for (i = 0; i < list.length; i++) {
                if (list[i] === obj) {
                    return true;
                }
            }
            return false;
        }
    },
}
</script>

<style>

</style>