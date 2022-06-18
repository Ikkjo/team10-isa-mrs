<template>
   <div>
        <iframe :src="mapSrc" class="map"></iframe>
    </div>
</template>

<script>
export default {
    name: 'GoogleMap',
    props: ['address', 'city', 'country'],
    data() {
       return {
           mapSrc: null,
       }
    },
    methods: {
        updateMap() {
            this.mapSrc = "https://maps.google.com/maps?q=" + this.country + "," + this.city + "," + this.address + "&t=&z=13&ie=UTF8&iwloc=&output=embed"; 
        },
    },
     watch: {
    $props: {
      handler() {
        this.updateMap();
      },
      deep: true,
      immediate: true,
    },
  },
    mounted() {
        this.mapSrc = "https://maps.google.com/maps?&t=&z=13&ie=UTF8&iwloc=&output=embed";
    }

}
</script>

<style>

.map {
    width: 100%;
    height: 300px;
}

</style>