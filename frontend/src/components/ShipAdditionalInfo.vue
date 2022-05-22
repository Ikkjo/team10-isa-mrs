<template>
  <div class="info-section">
      <h2>Ship info</h2>
      <div class="info-items">
        <InfoItem icon="directions_boat" label="Type" :text="rentalEntity.type"/> 
        <InfoItem icon="design_services" label="Lenght" :text="rentalEntity.length.toString()+' m'"/>
        <InfoItem icon="numbers" label="Engine count" :text="rentalEntity.engineCount.toString()"/>
        <InfoItem icon="numbers" label="Engine power" :text="rentalEntity.enginePower.toString()+' kW'"/>
        <InfoItem icon="speed" label="Max speed" :text="rentalEntity.maxSpeed.toString()+' km/h'"/>
        <InfoItem icon="gps_fixed" label="Navigation equipment" :text="rentalEntity.navigationEquipment"/>
        <InfoItem icon="phishing" label="Fishing equipment" :text="rentalEntity.fishingEquipment"/>
        <InfoItem icon="people" label="Capacity" :text="rentalEntity.capacity.toString()"/>
        <InfoItem icon="free_cancellation" label="Free cancellation" :text="rentalEntity.freeCancellation ? 'Free cancellation' : 'Owner keeps a percentage'"/>
      </div>
      
  </div>
</template>

<script>
import InfoItem from '@/components/InfoItem.vue'
export default {
    name: 'ShipAdditionalInfo',
    props: ['rentalEntity'],
    components: {
        InfoItem,
    }

}
</script>


<style scoped>

</style>