<template>
  <div>
    <UniversalNavBar/>
    <SearchBar ref="searchBar" @searchPressed="search"/>
    <div class="listings">
      <RentalEntityCard class="listing" v-for="(rentalEntity, index) in rentalEntities" :key="index" :rentalEntity="rentalEntity"/>
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/HomepageSearchBar.vue";
import RentalEntityCard from "@/components/RentalEntityCard.vue";
import axios from 'axios';
import UniversalNavBar from "@/components/UniversalNavBar.vue";
export default {
    name: 'HomepageView',
    components: {
    SearchBar,
    RentalEntityCard,
    UniversalNavBar
},


    data() {
      return {
        rentalEntities: [],
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
              address: searchQuery.address,
              title: searchQuery.title,
              fromDate: searchQuery.fromDate,
              toDate: searchQuery.toDate,
              ofType: searchQuery.ofType,
              minPrice: searchQuery.minPrice,
              maxPrice: searchQuery.maxPrice
            }
          })
          .then((response) => {
            console.log(response.data)
            this.rentalEntities = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
      },
    }
  }
</script>

<style scoped>

.input, .select, .textarea {
    padding: 3px 7px;
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
    background-color: #fff;
    font-family: inherit;
}

.input, .select {
   height: 48px;
}

.input:hover, .textarea:hover, .select:hover {
    border-color: var(--orange-primary, #f0a500);
}

.input:focus, .textarea:focus, .select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

.listings {
    display: grid;
    grid-template-columns: repeat(auto-fit, 20rem);
    justify-content: center;
    gap: 15px;
    max-width: 100%;
    padding-top: 240px;
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