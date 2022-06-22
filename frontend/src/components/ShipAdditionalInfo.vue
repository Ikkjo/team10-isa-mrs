<template>
  <div v-if="shipCopy" class="info-section">
      <h2>Ship info</h2>
      <div class="info-items">
        <InfoItem icon="directions_boat" label="Type"
          :text="ship.type"
          :saveDisabled="$v.shipCopy.type.$invalid"
          @save="saveShipType">
          <template slot="edit">
            <div class="form-control">
              <input type="text" 
                  v-model="shipCopy.type" 
                  name="ship-type" 
                  @focus="inFocus('type')" 
                  @blur="outFocus('type')" 
                  :class="getClass('type')" 
                  :placeholder="getPlaceholder('type')"
                  >
              <div class="alert-info" 
                  v-if="!isFocused('type') 
                  && !($v.shipCopy.type.minLength 
                  && $v.shipCopy.type.maxLength)"
                  >
                  Must be between 5 and 50 characters.
              </div>
            </div>
          </template>
        </InfoItem> 
        <InfoItem icon="design_services" label="Lenght"
          :text="ship.length.toString()+' m'"
          :saveDisabled="isShipLengthSaveDisabled"
          @save="saveShipLength">
          <template slot="edit">
            <NumberInput 
              @updated="shipLengthUpdated" 
              placeholder="Required" 
              label="" 
              :increment="1" 
              :minValue="1" 
              :maxValue="50"
              class="number-input"
              />
          </template>
        </InfoItem>
        <InfoItem icon="numbers" label="Engine count"
          :text="ship.engineCount.toString()"
          :saveDisabled="isShipEngineCountSaveDisabled"
          @save="saveShipEngineCount">
          <template slot="edit">
            <NumberInput 
              @updated="shipEngineCountUpdated" 
              placeholder="Required" 
              label="" 
              :increment="1" 
              :minValue="1" 
              :maxValue="5"
              class="number-input"
              />
          </template>
        </InfoItem>
        <InfoItem icon="numbers" label="Engine power" :text="ship.enginePower.toString()+' kW'"
        :saveDisabled="isShipEnginePowerSaveDisabled"
        @save="saveShipEnginePower">
          <template slot="edit">
            <NumberInput 
              @updated="shipEnginePowerUpdated" 
              placeholder="Required" 
              label="" 
              :increment="5" 
              :minValue="1" 
              :maxValue="10000"
              class="number-input"
              />
          </template>
        </InfoItem>
        <InfoItem icon="speed" label="Max speed" :text="ship.maxSpeed.toString()+' km/h'"
        :saveDisabled="isShipMaxSpeedSaveDisabled"
        @save="saveShipMaxSpeed">
          <template slot="edit">
            <NumberInput
              @updated="shipMaxSpeedUpdated" 
              placeholder="Required" 
              label="" 
              :increment="5" 
              :minValue="1" 
              :maxValue="200"
              class="number-input"
              />
          </template>
        </InfoItem>
        <InfoItem icon="gps_fixed" label="Navigation equipment"
            :text="ship.navigationEquipment"
            :saveDisabled="$v.shipCopy.navigationEquipment.$invalid"
            @save="saveShipNavigationEquipment">
          <template slot="edit">
            <div class="form-control">
                <textarea 
                    v-model="shipCopy.navigationEquipment" 
                    name="navigation-equipment" 
                    id="navigation-equipment" 
                    cols="30" rows="4" 
                    @focus="inFocus('navigationEquipment')" 
                    @blur="outFocus('navigationEquipment')" 
                    :class="getClass('navigationEquipment')" 
                    :placeholder="getPlaceholder('navigationEquipment', 'GPS, radar, VHS radio, fishfinder...')"
                    />
                <div class="alert-info alert-textarea" 
                    v-if="!isFocused('navigationEquipment') 
                    && !($v.shipCopy.navigationEquipment.minLength 
                    && $v.shipCopy.navigationEquipment.maxLength)"
                    >
                    Must be between 3 and 500 characters.
                </div>
            </div>
          </template>
        </InfoItem>
        <InfoItem icon="phishing" label="Fishing equipment"
            :text="ship.fishingEquipment"
            :saveDisabled="$v.shipCopy.fishingEquipment.$invalid"
            @save="saveShipFishingEquipment">
          <template slot="edit">
            <div class="form-control">
                <textarea 
                    v-model="shipCopy.fishingEquipment" 
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
                    && !$v.shipCopy.fishingEquipment.maxLength"
                    >
                    Max 500 characters.
                </div>
            </div>
          </template>
        </InfoItem>
        <InfoItem icon="people" label="Capacity"
            :text="ship.capacity.toString()"
            :saveDisabled="isShipCapacitySaveDisabled"
            @save="saveShipCapacity">
          <template slot="edit">
            <NumberInput 
              @updated="shipCapacityUpdated" 
              placeholder="Required" 
              label="Capacity" 
              :increment="2" 
              :minValue="1" 
              :maxValue="100"
              class="number-input"
              />
          </template>
        </InfoItem>
        <InfoItem icon="free_cancellation" label="Free cancellation"
            :text="ship.freeCancellation ? 'Free cancellation' : 'Owner keeps a percentage'"
            :saveDisabled="shipCopy.cancellation === null"
            @save="saveShipCancellation">
            <template slot="edit">
                <div class="form-control">
                    <select name="cancellation" id="cancellation" v-model="shipCopy.cancellation">
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
import NumberInput from '../components/NumberInput.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
export default {
    name: 'ShipAdditionalInfo',
    props: ['ship'],
    components: {
        InfoItem,
        NumberInput,
    },
    data() {
      return {
        shipCopy: null,
        infocus: {
          type: true,
          navigationEquipment: true,
          fishingEquipment: true,
        },
      }
    },
    validations: {
        shipCopy: {
            type: {
                required,
                minLength: minLength(5),
                maxLength: maxLength(50),
            },
            navigationEquipment: {
                required,
                minLength: minLength(3),
                maxLength: maxLength(500),
            },
            fishingEquipment: {
                maxLength: maxLength(500),
            },
        }
    },
    methods: {
        shipLengthUpdated(length) {
            this.shipCopy.length = length;
        },
        shipEngineCountUpdated(engineCount) {
            this.shipCopy.engineCount = engineCount;
        },
        shipEnginePowerUpdated(enginePower) {
            this.shipCopy.enginePower = enginePower;
        },
        shipCapacityUpdated(capacity) {
            this.shipCopy.capacity = capacity;
        },
        shipMaxSpeedUpdated(maxSpeed) {
            this.shipCopy.maxSpeed = maxSpeed;
        },
        saveShipType() {
            if (this.shipCopy.type !== this.ship.type)
                this.$emit("update:shipType", this.shipCopy.type)

        },
        saveShipLength() {
            if (this.shipCopy.length !== this.ship.length)
                this.$emit("update:shipLength", this.shipCopy.length)

        },
        saveShipEngineCount() {
            if (this.shipCopy.engineCount !== this.ship.engineCount)
                this.$emit("update:shipEngineCount", this.shipCopy.engineCount)

        },
        saveShipEnginePower() {
            if (this.shipCopy.enginePower !== this.ship.enginePower)
                this.$emit("update:shipEnginePower", this.shipCopy.enginePower)

        },
        saveShipMaxSpeed() {
            if (this.shipCopy.maxSpeed !== this.ship.maxSpeed)
                this.$emit("update:shipMaxSpeed", this.shipCopy.maxSpeed)

        },
        saveShipNavigationEquipment() {
            if (this.shipCopy.navigationEquipment !== this.ship.navigationEquipment)
                this.$emit("update:shipNavigationEquipment", this.shipCopy.navigationEquipment)

        },
        saveShipFishingEquipment() {
            if (this.shipCopy.fishingEquipment !== this.ship.fishingEquipment)
                this.$emit("update:shipFishingEquipment", this.shipCopy.fishingEquipment)

        },
        saveShipCapacity() {
            if (this.shipCopy.capacity !== this.ship.capacity)
                this.$emit("update:shipCapacity", this.shipCopy.capacity)

        },
        saveShipCancellation(){
            if (this.shipCopy.cancellation !== this.ship.cancellation)
                this.$emit("update:shipCancellation", this.shipCopy.cancellation)

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
      setShipCopy(ship) {
            this.shipCopy = ship
      },
    },
    computed: {
        isShipLengthSaveDisabled() {
            return this.shipCopy.length < 1 || this.shipCopy.length > 50 
        },
        isShipEngineCountSaveDisabled() {
            return this.shipCopy.engineCount < 1 || this.shipCopy.engineCount > 5 
        },
        isShipEnginePowerSaveDisabled() {
            return this.shipCopy.enginePower < 1 || this.shipCopy.enginePower > 10000 
        },
        isShipMaxSpeedSaveDisabled() {
            return this.shipCopy.maxSpeed < 1 || this.shipCopy.maxSpeed > 200 
        },
        isShipCapacitySaveDisabled() {
            return this.shipCopy.capacity < 1 || this.shipCopy.capacity > 100 
        }
    }

}
</script>


<style scoped>
.number-input {
    justify-content: center;
}
</style>