<template>
  <nav id="searchbar"
  @keypress.enter="$emit('searchPressed', getSearchQuery())">
      <div class="searchbar">
        <input class="search-input" type="text" v-model="cityInput" placeholder="City..." />
        <input class="search-input" type="text" v-model="countryInput" placeholder="Country..." />
      
                 <SearchBarItem
                :link="'ships'"
                :iconClass="'material-icons-outlined'"
                :icon="'directions_boat'"
                :title="'Ships'"
       />
              <SearchBarItem
                :link="'vacation-homes'"
                :iconClass="'material-icons-outlined'"
                :icon="'house'"
                :title="'Vacation homes'"
       />
              <SearchBarItem
                :link="'adventures'"
                :iconClass="'material-icons-outlined'"
                :icon="'phishing'"
                :title="'Adventures'"
       />
        <input class="search-input" type="text" v-model="titleInput" placeholder="Title..." />
        <!-- Skontaj kako radi Vladanov datepicker -->
        <div v-if="showDatePicker" class="calendar">
          <RentalEntityAvailabilityCalendar ref="calendar" :defaultSelection="availability" :edit="false"/>
        </div>
        <div class="btn-div">
        <button class="btn" @click="$emit('searchPressed', getSearchQuery())">Search</button>
        </div>
      </div>
  </nav>
</template>

<script>
import SearchBarItem from './HomepageSearchBarItem.vue'
import RentalEntityAvailabilityCalendar from './RentalEntityAvailabilityCalendar.vue'
export default {
    name: 'HomepageSearchBar',
    components: {
    SearchBarItem,
    RentalEntityAvailabilityCalendar
    },
    data() {
      return {
        cityInput: '',
        countryInput: '',
        titleInput: '',
        fromDate: new Date(),
        toDate: new Date(),
        showDatePicker: false
      }
    },
    created() {
    },
    methods: {
    getSearchQuery() {
      return {
        city: this.cityInput,
        country: this.countryInput,
        title: this.titleInput,
        fromDate: this.fromDate.getTime().toString(),
        toDate: this.toDate.getTime().toString()
      }
    },
    }
    
}
</script>

<style select>
.searchbar {
  height: calc(var(--nav-height) + 10px);
  border-bottom: 1px solid rgb(236, 236, 236);
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.searchbar-nav {
  max-width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}

.search-input {
  display: block;
  height: 48px;
  width: 160px;
  margin: 20px;
  padding: 10px;
  text-align: center;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.calendar {
    margin-top: 5px;
    padding-left: 29px;
}

.btn-div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

}

.btn {
  height: 48px;
  width: calc(160px/1.5);
  margin: 20px;
  padding: 10px;
  text-align: center;
  font-size: 16px;
}

.btn-div p {
    margin-top: 0px;
}

</style>