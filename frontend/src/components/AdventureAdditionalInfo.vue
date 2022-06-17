<template>
    <div v-if="vacationHomeCopy" class="info-section">
        <h2>Adventure info</h2>
        <div class="info-items">
            <InfoItem icon="assignment_ind" label="Short biography"
                :text="adventure.biography"
                :saveDisabled="$v.adventureCopy.biography.$invalid"
                @save="saveAdventureBiography">
                <template slot="edit">
                    <div class="form-control">
                        <textarea 
                            v-model="adventureCopy.biography" 
                            name="biography" 
                            id="biography" 
                            cols="30" rows="3" 
                            @focus="inFocus('biography')" 
                            @blur="outFocus('biography')" 
                            :class="getClass('biography')" 
                            :placeholder="getPlaceholder('biography', 'Something about yourself.')"
                            />
                        <div class="alert-info alert-textarea" 
                            v-if="!isFocused('biography') 
                            && !($v.adventureCopy.biography.minLength 
                            && $v.adventureCopy.biography.maxLength)"
                            >
                            Must be between 5 and 200 characters.
                        </div>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="people" label="Capacity"
                :text="ship.capacity.toString()"
                :saveDisabled="isAdventureCapacitySaveDisabled"
                @save="saveAdventureCapacity">
                <template slot="edit">
                    <NumberInput 
                        @updated="AdventureCapacityUpdated" 
                        placeholder="Required" 
                        label="Capacity" 
                        :increment="1" 
                        :minValue="1" 
                        :maxValue="20"
                        class="number-input"
                        />
                </template>
            </InfoItem>
            <InfoItem icon="phishing" label="Fishing equipment"
                :text="adventure.fishingEquipment"
                :saveDisabled="$v.adventureCopy.fishingEquipment.$invalid"
                @save="saveAdventureFishingEquipment">
                <template slot="edit">
                    <div class="form-control">
                        <textarea 
                            v-model="adventureCopy.fishingEquipment" 
                            name="fishing-equipment" 
                            id="fishing-equipment" 
                            cols="30" rows="4" 
                            @focus="inFocus('fishingEquipment')" 
                            @blur="outFocus('fishingEquipment')" 
                            :class="getClass('fishingEquipment')" 
                            :placeholder="getPlaceholder('fishingEquipment', 'Fishing rods, baits, hooks, weights...')"
                            />
                        <div class="alert-info alert-textarea" 
                            v-if="!isFocused('fishingEquipment') 
                            && !($v.adventureCopy.fishingEquipment.minLength 
                            && $v.adventureCopy.fishingEquipment.maxLength)"
                            >
                            Must be between 5 and 500 characters.
                        </div>
                    </div>
                </template>
            </InfoItem>
            <InfoItem icon="free_cancellation" label="Free cancellation"
                :text="adventure.freeCancellation ? 'Free cancellation' : 'Owner keeps a percentage'"
                :saveDisabled="adventureCopy.cancellation === null"
                @save="saveAdventureCancellation">
                <template slot="edit">
                    <div class="form-control">
                        <select name="cancellation" id="cancellation" v-model="adventureCopy.cancellation">
                            <option :value="null" selected disabled hidden>Choose an option</option>
                            <option :value="true">Free cancellation</option>
                            <option :value="false">Owner keeps a percentage</option>
                        </select>
                    </div>
                </template>
            </InfoItem>
        </div>
    </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators';

export default {
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
        AdventureCapacityUpdated(capacity) {
            this.adventureCopy.capacity = capacity;
        },
        saveAdventureBiography() {
            if (this.adventureCopy.biography !== this.adventure.biography)
                this.$emit("update:adventureBiography", this.adventureCopy.biography)
        },
        saveAdventureCapacity() {
            if (this.adventureCopy.capacity !== this.adventure.capacity)
                this.$emit("update:adventureCapacity", this.adventureCopy.capacity)
        },
        saveAdventureFishingEquipment() {
            if (this.adventureCopy.fishingEquipment !== this.adventure.fishingEquipment)
                this.$emit("update:adventureFishingEquipment", this.adventureCopy.fishingEquipment)
        },
        saveAdventureCancellation() {
            if (this.adventureCopy.cancellation !== this.adventure.cancellation)
                this.$emit("update:adventureCancellation", this.adventureCopy.cancellation)
        },
    },
    validations: {
        adventureCopy: {
            biography: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(500),
            },
            fishingEquipment: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(200),
            },
        }
    },
    computed: {
        isAdventureCapacitySaveDisabled() {
            return this.adventureCopy.capacity < 1 || this.adventureCopy.capacity > 20 
        }
    },
}
</script>

<style scoped>
.number-input {
    justify-content: center;
}
</style>