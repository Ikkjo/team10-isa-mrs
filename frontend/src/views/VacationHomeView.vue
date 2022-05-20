<template>
  <div>
    <BusinessClientNavBar id="nav"/>
    <div class="container">
      <div class="cover">
        <img src="https://papers.co/wallpaper/papers.co-my69-house-swimmingpool-vacation-nature-city-35-3840x2160-4k-wallpaper.jpg" alt="" srcset="">
        <div class="title">
          <h1>{{user.name}} {{user.lastname}}</h1>
          <div class="rating">
            Avarage rating: {{rating}}
            <span class="material-icons">star</span>
          </div>
        </div>
      </div>
      <div class="vacation-homes">
        <VacationHomeCard class="vacation-home-card" v-for="(vHome, index) in vacationHomes" :key="index" :vacationHome="vHome"/>
      </div>
    </div>
 
  </div>
</template>

<script>
import BusinessClientNavBar from "@/components/BusinessClientNavBar.vue"
import VacationHomeCard from "@/components/VacationHomeCard.vue"
import axios from 'axios';
export default {
    name: 'VacationHomeView',
    components: {
        BusinessClientNavBar,
        VacationHomeCard,
    },
    data() {
      return {
        rating: 4.5,
        user: {
          name: 'Imenko',
          lastname: 'Prezimic',
          uuid: '123'
        },
        vacationHomes: [],
        
      }
    },
    mounted() {
      axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/vacation-home-owner/vacation-homes",
          { headers: { Authorization: 'Bearer ' + window.localStorage.getItem("jwt") }
          })
          .then((response) => {
            console.log(response.data);
            this.vacationHomes = response.data
          })
          .catch(function(error) {
              console.log(error);
              // is email taken
              // is phone taken
          })
    }
  }
</script>

<style>
.container {
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

.vacation-homes {
    display: grid;
    grid-template-columns: repeat(auto-fit, 20rem);
    justify-content: center;
    gap: 15px;
    margin-top: 15px;
}

.vacation-home-card {
}


@media screen and (max-width: 669px) {
  .vacation-homes {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0 15px;
  }
  
  .vacation-home-card {
    margin-bottom: 5px;
  }
}


</style> 