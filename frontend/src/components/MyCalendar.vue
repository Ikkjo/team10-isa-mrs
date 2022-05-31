<template>
   <Calendar
    color="yellow"
    is-expanded
    :attributes="attributes"
    @dayclick="onDayClick"
    :min-date='new Date()'
    />
</template>

<script>
import Calendar from 'v-calendar/lib/components/calendar.umd'
export default {
    name: 'MyCalendar',
    props: {
        defaultSelection: {
            type: Array,
            default() {
                return []
            }
        },
        edit: {
            type: Boolean,
            default: true,
        }
    },
    components: {
        Calendar
    },
    data() {
        return {
            dates: this.defaultSelection.map(d => new Date(d)),
        };
    },
    computed: {
        yesterday() {
            let today = new Date()
            return today.setDate(today.getDate() - 1); 
        },
        attributes() {
            return this.dates.map(date => ({
                highlight: true,
                dates: date,
            }));
        },
    },
    methods: {
        onDayClick(day) {
            if (this.edit) {
                for (let i=0; i < this.dates.length; i++) {
                    if (JSON.stringify(day.date) === JSON.stringify(this.dates[i])) {
                        this.dates.splice(i, 1)
                        return
                    }
                }
                if (day.date < this.yesterday)
                    return
                this.dates.push(day.date);
                this.$emit('input', this.dates)
            }
           

        },
    },
}
</script>

<style>

</style>