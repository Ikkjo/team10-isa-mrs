<template>
  <div>
    <HomepageNavBar id="nav"/>
    <SearchBar/>
    <div class="listings-container">
      <div class="listings">
        <RentalEntityCard class="listing" v-for="(rentalEntity, index) in listings" :key="index" :rentalEntity="rentalEntity"/>
      </div>
    </div>
  </div>
</template>

<script>
import HomepageNavBar from "@/components/HomepageNavBar.vue"
import RentalEntityCard from "@/components/RentalEntityCard.vue"
import SearchBar from "@/components/SearchBar.vue";
import axios from 'axios';
export default {
    name: 'HomepageView',
    components: {
        HomepageNavBar,
        RentalEntityCard,
        SearchBar
    },
    data() {
      return {
        user: {},
        listings: [
          {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
          },
                    {
            id: "57cb00b1-7b78-4ea8-8097-98f366aa53bb",
            title: "",
            address: {
              address: "",
              city: "",
              country: "",
            },
            description: "",
            rulesOfConduct: "",
            additionalServices: "",
            price: 33,
            pictures: ["https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg"],
            availability:[],
            type: "",
            length: 91,
            engineCount: 33,
            enginePower: 55,
            maxSpeed: 80,
            navigationEquipment: "",
            fishingEquipment: "",
            capacity: 3,
            freeCancellation: false,
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
.listings-container {
  max-width: 100%;
  padding-top: 180px;
}

.listings {
    display: grid;
    grid-template-columns: repeat(auto-fit, 20rem);
    justify-content: center;
    gap: 15px;
}

@media screen and (max-width: 669px) {
  .listings {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0 15px;
  }
  
  .listing {
    margin-bottom: 5px;
    width: 100%;
  }

}


</style> 