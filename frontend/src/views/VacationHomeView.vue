<template>
  <div>
    <vacation-home-card class="vacation-home-card" v-for="(vHome, index) in vacationHomes" :key="index" :vacationHome="vHome"/>
  </div>
</template>

<script>
import VacationHomeCard from "@/components/VacationHomeCard.vue"
export default {
    name: 'VacationHomeView',
    components: {
        VacationHomeCard,
    },
    data() {
      return {
        vacationHomes: [
          {
            image: "https://a0.muscache.com/im/pictures/miso/Hosting-604726298950758799/original/78d55499-cb48-4e83-a75f-faa42dd7d1ff.jpeg?im_w=720",
            title: 'Fina kucica',
            address: 'Bulevar Oslobodjenja, Novi Sad, Srbija',
            rooms: 3,
            beds: 3,
            price: 100,
          },
           {
            image: "https://a0.muscache.com/im/pictures/miso/Hosting-604726298950758799/original/78d55499-cb48-4e83-a75f-faa42dd7d1ff.jpeg?im_w=720",
            title: 'Fina kucica',
            address: 'Bulevar Oslobodjenja, Novi Sad, Srbija',
            rooms: 3,
            beds: 3,
            price: 100,
          },
           {
            image: "https://a0.muscache.com/im/pictures/miso/Hosting-604726298950758799/original/78d55499-cb48-4e83-a75f-faa42dd7d1ff.jpeg?im_w=720",
            title: 'Fina kucica',
            address: 'Bulevar Oslobodjenja, Novi Sad, Srbija',
            rooms: 3,
            beds: 3,
            price: 100,
          },
           {
            image: "https://a0.muscache.com/im/pictures/miso/Hosting-604726298950758799/original/78d55499-cb48-4e83-a75f-faa42dd7d1ff.jpeg?im_w=720",
            title: 'Fina kucica',
            address: 'Bulevar Oslobodjenja, Novi Sad, Srbija',
            rooms: 3,
            beds: 3,
            price: 100,
          },
        ]
      }
    }
  }
</script>

<style>
div {
  max-width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.vacation-home-card {
  margin: 10px;
}


</style> 