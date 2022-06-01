<template>
    <div class="listings-container">
      <div class="listings">
        <RentalEntityCard class="listing" v-for="(rentalEntity, index) in listings" :key="index" :rentalEntity="rentalEntity"/>
      </div>
    </div>
</template>

<script>
import RentalEntityCard from "@/components/RentalEntityCard.vue"
import axios from 'axios';
export default {
    name: 'VacationHomepage',
    components: {
        RentalEntityCard,

    },
    data() {
      return {
        user: {},
        listings: [
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Idila pored Lidla",
            address: {
              address: "Braće Ribnikar 33",
              city: "Novi Sad",
              country: "Serbia",
            },
            description: "Lidl je veoma blizu!",
            rulesOfConduct: "Nema ljubimaca",
            additionalServices: "Wifi, kablovska",
            price: 23,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            rooms: 2,
            beds: 2
          },
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Prenoćište 'Na pola puta'",
            address: {
              address: "Fruškogorska 91",
              city: "Novi Sad",
              country: "Serbia",
            },
            description: "Na pola puta do Fruske Gore!",
            rulesOfConduct: "Ljubimci su dobrodosli",
            additionalServices: "Wifi, kablovska, parking za bicikle",
            price: 78,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            rooms: 3,
            beds: 5
          }
        ],
        
      }
    },
    created() {
       axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/business-client",
          { headers: { Authorization: 'Bearer ' + window.localStorage.getItem("jwt") }
          })
          .then((response) => {
            console.log(response.data)
            this.user = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
        axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/rental-entity",
          { headers: { Authorization: 'Bearer ' + window.localStorage.getItem("jwt") }
          })
          .then((response) => {
            console.log(response.data)
            this.listings = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
    }
  }
</script>

<style>

</style> 