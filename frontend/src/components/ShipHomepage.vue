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
    name: 'ShipHomepage',
    components: {
        RentalEntityCard,

    },
    data() {
      return {
        user: {},
        listings: [
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://coolwallpapers.me/picsup/5732529-cruise-ships-wallpapers.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://p4.wallpaperbetter.com/wallpaper/743/395/681/maersk-maersk-line-container-ship-sea-wallpaper-preview.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://p4.wallpaperbetter.com/wallpaper/492/988/54/ship-container-ship-maersk-line-wallpaper-preview.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://trumpwallpapers.com/wp-content/uploads/Ship-Wallpaper-07-1920x1200-1.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://p4.wallpaperbetter.com/wallpaper/492/988/54/ship-container-ship-maersk-line-wallpaper-preview.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://coolwallpapers.me/picsup/5732529-cruise-ships-wallpapers.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
            },
            {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://coolwallpapers.me/picsup/5732529-cruise-ships-wallpapers.jpg"],
            rooms: 2,
            beds: 2
          },
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://trumpwallpapers.com/wp-content/uploads/Ship-Wallpaper-07-1920x1200-1.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://trumpwallpapers.com/wp-content/uploads/Ship-Wallpaper-07-1920x1200-1.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://p4.wallpaperbetter.com/wallpaper/492/988/54/ship-container-ship-maersk-line-wallpaper-preview.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://coolwallpapers.me/picsup/5732529-cruise-ships-wallpapers.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://trumpwallpapers.com/wp-content/uploads/Ship-Wallpaper-07-1920x1200-1.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Titova Spas",
            address: {
              address: "Kotorska luka 32",
              city: "Kotor",
              country: "Crna Gora",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "Simona 3",
            address: {
              address: "Dunavksa 3",
              city: "Beograd",
              country: "Srbija",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://r4.wallpaperflare.com/wallpaper/863/684/864/sea-beach-islands-landscape-wallpaper-44b51eb89a58fdb8ff279415d8952c59.jpg"],
            rooms: 2,
            beds: 2
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