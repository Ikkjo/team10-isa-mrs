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
    name: 'AdventureHomepage',
    components: {
        RentalEntityCard,

    },
    data() {
      return {
        user: {},
        listings: [
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Pecanje sa Dragom (Dragosav Micić)",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://img.freepik.com/free-photo/man-fishing-river_23-2148204141.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Dime Banjac Pecaros",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://images.unsplash.com/photo-1590456987995-15a2924fa659?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZmlzaGluZyUyMG1hbnxlbnwwfHwwfHw%3D&w=1000&q=80"],
          },
                              {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Marko i Miroslav Dulić",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://ak.picdn.net/offset/photos/53fcf200a6dfde507e9f39df/medium/photo.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Pecanje sa Dragom (Dragosav Micić)",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://img.freepik.com/free-photo/man-fishing-river_23-2148204141.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Dime Banjac Pecaros",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://images.unsplash.com/photo-1590456987995-15a2924fa659?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZmlzaGluZyUyMG1hbnxlbnwwfHwwfHw%3D&w=1000&q=80"],
          },
                              {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Marko i Miroslav Dulić",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://ak.picdn.net/offset/photos/53fcf200a6dfde507e9f39df/medium/photo.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Pecanje sa Dragom (Dragosav Micić)",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://img.freepik.com/free-photo/man-fishing-river_23-2148204141.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Dime Banjac Pecaros",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://images.unsplash.com/photo-1590456987995-15a2924fa659?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZmlzaGluZyUyMG1hbnxlbnwwfHwwfHw%3D&w=1000&q=80"],
          },
                              {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Marko i Miroslav Dulić",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://ak.picdn.net/offset/photos/53fcf200a6dfde507e9f39df/medium/photo.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Pecanje sa Dragom (Dragosav Micić)",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://img.freepik.com/free-photo/man-fishing-river_23-2148204141.jpg"],
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Dime Banjac Pecaros",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://images.unsplash.com/photo-1590456987995-15a2924fa659?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZmlzaGluZyUyMG1hbnxlbnwwfHwwfHw%3D&w=1000&q=80"],
          },
                              {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Marko i Miroslav Dulić",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://ak.picdn.net/offset/photos/53fcf200a6dfde507e9f39df/medium/photo.jpg"],
          },
          
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

      let userRole = window.localStorage.getItem('userRole')
      if (userRole === 'HOUSE_OWNER')
        this.coverPhoto = "https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"
      else if (userRole === 'SHIP_OWNER')
        this.coverPhoto = "https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"
      else if (userRole === 'FISHING_INSTRUCTOR')
        this.coverPhoto = "https://s2.best-wallpaper.net/wallpaper/3840x2160/1901/Man-fishing-lake-sunrise-morning_3840x2160.jpg"
    }
  }
</script>

<style>

</style> 