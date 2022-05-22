<template>
  <div>
    <BusinessClientNavBar id="nav"/>
    <div class="listings-container">
      <div class="cover">
        <img :src="coverPhoto" alt="" srcset="">
        <div class="title">
          <h1>{{user.firstName}} {{user.lastName}}</h1>
          <div class="rating">
            Avarage rating: {{rating}}
            <span class="material-icons">star</span>
          </div>
        </div>
      </div>
      <div class="listings">
        <RentalEntityCard class="listing" v-for="(rentalEntity, index) in listings" :key="index" :rentalEntity="rentalEntity"/>
      </div>
    </div>
 
  </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import RentalEntityCard from "@/components/RentalEntityCard.vue"
import axios from 'axios';
export default {
    name: 'MyListingsView',
    components: {
        BusinessClientNavBar,
        RentalEntityCard,
    },
    data() {
      return {
        rating: 4.5,
        user: {},
        listings: [],
        coverPhoto: '',
        
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
}

.cover {
  width: 100%;
  position: relative;
}

.cover img {
  width: 100%;
  max-height: 350px;
  background-repeat: no-repeat;
  background-position: center;
  object-fit: cover;
  /* Add the blur effect */
  filter: blur(2px);
  -webkit-filter: blur(2px);
}

.cover .title {
  position: absolute;
  top: 89%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

.rating {
  display: flex;
  justify-content: center;
  align-items: center;
}

.listings {
    display: grid;
    grid-template-columns: repeat(auto-fit, 20rem);
    justify-content: center;
    gap: 15px;
    margin-top: 15px;
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