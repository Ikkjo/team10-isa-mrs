<template>
  <div id="info-item">
    <div class="wrapper">
        <div class="label-div">
            <span :class="['icon', 'material-icons']">calendar_month</span>
            <div class="label">Availability</div>
        </div>
        <div v-show="!edit" @click="edit = !edit" class="edit noselect">Edit</div>
        <div v-show="edit" @click="cancelClicked" class="edit cancel noselect">Cancel</div>
    </div>
    <div class="calendar">
        <RentalEntityAvailabilityCalendar ref="calendar" :defaultSelection="availability" :edit="edit"/>
    </div>
    <button v-if="edit" @click="save" class="btn">Save</button>
  </div>
</template>

<script>
import RentalEntityAvailabilityCalendar from '@/components/RentalEntityAvailabilityCalendar.vue'
export default {
    name: 'EditAvailability',
    props: {
        availability: {
            type: Array,
            default() { return [] }
        }
    },
    components: {
        RentalEntityAvailabilityCalendar
    },
    data() {
        return {
            edit: false,
        }
    },
    methods: {
        cancelClicked() {
            this.$refs.calendar.dates = []
            this.$refs.calendar.dates = this.availability.map(a => new Date(a))
            this.edit = !this.edit
        },
        save() {
            console.log("saved")
            this.edit = !this.edit
            this.$emit("save", this.$refs.calendar.dates)
        }
    }
}
</script>

<style scoped>
#info-item {
    min-width: 300px;
    width: 100%;
}

.label-div {
    display: flex;
    align-items: center;
    color: var(--black) !important;
}

.icon {
    margin-right: 5px;
}

.text {
    margin-top: 5px;
    padding-left: 29px;
    font-size: 0.9rem;
    color: grey !important;
    display: inline-block;
}

.wrapper {
    position: relative;
}

.edit {
    display: inline-block;
    position: absolute;
    right: 0;
    top: 0;
    margin-top: 3px;
    text-decoration: underline;
    cursor: pointer;
}

.edit:hover {
    color: var(--orange-primary);
}

.edit:active {
    color: var(--orange-secondary);
}

.cancel {
    color: red;
}

.cancel:active, .cancel:hover {
    color: darkred;
}

.edit-slot {
    padding: 0px 5px;
}

.btn {
    margin-top: 15px;
}

.calendar {
    margin-top: 5px;
    padding-left: 29px;
}

</style>