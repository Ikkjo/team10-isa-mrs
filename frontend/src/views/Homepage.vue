<template>
  <div>
    <HomepageNavBar id="nav"/>
    <SearchBar ref="searchBar" @searchPressed="search"/>
    <div class="listings">
      <RentalEntityCard class="listing" v-for="(rentalEntity, index) in rentalEntities" :key="index" :rentalEntity="rentalEntity"/>
    </div>
  </div>
</template>

<script>
import HomepageNavBar from "@/components/HomepageNavBar.vue"
import SearchBar from "@/components/HomepageSearchBar.vue";
import RentalEntityCard from "@/components/RentalEntityCard.vue";
import axios from 'axios';
export default {
    name: 'HomepageView',
    components: {
        HomepageNavBar,
        SearchBar,
        RentalEntityCard
    },


    data() {
      return {
        rentalEntities: [],
        user: null,
        searchQuery: {},
        sharedItems: SearchBar.data
      }
    },
    created() {
       
       axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/homepage")
          .then((response) => {
            console.log(response.data)
            this.rentalEntities = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
    },
    methods: {
      search(searchQuery) {
        console.log(searchQuery)
        axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/rental-entity/search", {
            params: {
              city: searchQuery.city,
              country: searchQuery.country,
              title: searchQuery.title,
              fromDate: searchQuery.fromDate,
              toDate: searchQuery.toDate
            }
          })
          .then((response) => {
            console.log(response.data)
            this.rentalEntities = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
      }
    }
  }
</script>

<style>
.listings-container {
  max-width: 100%;
  padding-top: 7110px;
}

.listings {
    display: grid;
    grid-template-columns: repeat(auto-fit, 20rem);
    justify-content: center;
    gap: 15px;
    max-width: 100%;
    padding-top: 150px;
}

@media screen and (max-width: 669px) {
  .listings {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0 15px;
    overflow: scroll;
  }
  
  .listing {
    margin-bottom: 5px;
    width: 100%;
  }

}
</style> 