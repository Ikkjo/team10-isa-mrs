<template>
    <div v-if="vacationHomeCopy" class="info-section">
        <h2>Adventure info</h2>
        <div class="info-items">
            <InfoItem icon="assignment_ind" label="Short biography"
                :text="adventure.biography"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true"
                />
            <InfoItem icon="people" label="Capacity"
                :text="ship.capacity.toString()"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true"/>
            <InfoItem icon="phishing" label="Fishing equipment"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true"/>
            <InfoItem icon="free_cancellation" label="Free cancellation"
                :text="adventure.freeCancellation ? 'Free cancellation' : 'Owner keeps a percentage'"
                :useEditButton="false"
                :useSlot="false"
                :saveDisabled="true"/>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'

export default {
    name: "AdventureAdditionalDetails",
    props: {
        adventure: Object,
    },
    components: {
        InfoItem,
    },
    data() {
        return {
            adventureCopy: null,
            infocus: {
                fishingEquipment: true,
                biography: true,
            }
        }
    },
    methods: {
        setAdventureCopy(adventure) {
            this.adventureCopy = adventure;
        },
        isFocused(field) {
            return this.infocus[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        getClass(field) {
            let cls = ''
            if (field in this.$v.shipCopy)
                cls = !this.isFocused(field) && this.$v.shipCopy[field].$invalid ? 'alert' : '';
            else
                cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = ''
            if (field in this.$v.shipCopy)
                placeholder = !this.isFocused(field) && this.$v.shipCopy[field].$invalid ? 'Required' : defaultPlaceholder;
            else
                placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            
            return placeholder;
        },
    },
}
</script>

<style scoped>
.number-input {
    justify-content: center;
}
</style>