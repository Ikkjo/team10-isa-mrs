<template>
  <div class="homepage">
    <UniversalNavBar/>
    <SearchBar ref="searchBar" @searchPressed="search"/>
    <div class="listings">
      <RentalEntityCard class="listing" v-for="(rentalEntity, index) in rentalEntities" :key="index" :rentalEntity="rentalEntity"/>

    </div>
    <div class="page-div">
    <Paginate :container-class="'pagination'"
        v-model="page"
        :click-handler="pageChange"
        :page-count="50"
        :page-range="3"
        :page-class="'page-item'"
        :break-view-class="'page-item'"
        :next-class="'page-navigation'"
        :prev-class="'page-navigation'"
        :page-link-class="'page-link'">
    </Paginate>
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/HomepageSearchBar.vue";
import RentalEntityCard from "@/components/RentalEntityCard.vue";
import axios from 'axios';
import UniversalNavBar from "@/components/UniversalNavBar.vue";
import Paginate from 'vuejs-paginate'
export default {
    name: 'HomepageView',
    components: {
    SearchBar,
    RentalEntityCard,
    UniversalNavBar,
    Paginate
},


    data() {
      return {
        rentalEntities: [],
        searchQueryDefault: {
            city: "%",
            country: "%",
            address: "%",
            title: "%",
            fromDate: 0,
            toDate: 0,
            ofType: "",
            minPrice: 0,
            maxPrice: 10000
        },
        page: 0,
        sharedItems: SearchBar.data
      }
    },
    created() {
       
       axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/homepage")
          .then((response) => {
            this.rentalEntities = response.data
            })
          .catch(function(error) {
              console.log(error)
          })
    },
    methods: {
      search(searchQuery) {

        if(!searchQuery) {
          searchQuery = this.searchQueryDefault
        } 

        axios
          .get(process.env.VUE_APP_BASE_URL+"/api/v1/rental-entity/search", {
            params: {
              page: this.page-1,
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
            this.rentalEntities = response.data
          })
          .catch(function(error) {
              console.log(error)
          })
      },
      pageChange: function(page) {
        this.page = page
        this.search()
      }
    }
  }
</script>

<style>

.homepage input, select, textarea {
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

.page-div *{
  display: grid;
  align-content: center;
  align-items: center;
  text-align: center;
}

.pagination {
  margin: 1.7rem;
  display: flex;
  justify-content: center;
  list-style: none;
  border-radius: .25rem;
}
.page-item {
    margin: 0.2rem;
    display: inline-block;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    outline: none;
    color: var(--white, #fff);
    background-color: var(--orange-primary, orange);
    cursor: pointer;
    font-family: inherit;
    text-decoration: none;
    transition: 0.5s;
    font-size: 17px;
}

.page-navigation{
    margin: 0.3rem;
    display: inline-block;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    outline: none;
    color: var(--white, #fff);
    background-color: var(--orange-primary, orange);
    cursor: pointer;
    font-family: inherit;
    text-decoration: none;
    transition: 0.5s;
    font-size: 17px;
}

.page-link, .page-navigation a{
  color: var(--white, #fff);
}
.page-link:disabled {
    pointer-events: none;
    background-color: lightgray;
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