<template>
  
  <nav id="searchbar"
  @keypress.enter="$emit('searchPressed', getSearchQuery())">
  
      <div class="searchbar">
        <input class="input" type="text" v-model="countryInput" placeholder="Country" />
        <input class="input" type="text" v-model="cityInput" placeholder="City" />
        <input class="input" type="text" v-model="addressInput" placeholder="Address" />
        <input class="input" type="text" v-model="titleInput" placeholder="Title" />
        <div class="calendar">
          <DatePicker class="calendar"
                        color="yellow"
                        v-model="dateRange"
                        :min-date='new Date()'
                        :is-range='true'
                        >
                        <template v-slot="{ inputValue, inputEvents}">
                            <input :value="inputValue.start" v-on="inputEvents.start"/>
                            <input :value="inputValue.end" v-on="inputEvents.end"/>
                        </template>
          </DatePicker>
        </div>
        <div class="btn-div">
        <button class="btn" @click="$emit('searchPressed', getSearchQuery())">
          Search
          <span class="material-icons-outlined">search</span>
        </button>
        </div>
        
      </div>
      <div class="button-bar">
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
      </div>
  </nav>
</template>

<script>
import SearchBarItem from './HomepageSearchBarItem.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
export default {
    name: 'HomepageSearchBar',
    components: {
    SearchBarItem,
    DatePicker
    },
    data() {
      return {
        cityInput: '',
        countryInput: '',
        titleInput: '',
        addressInput: '',
        dateRange: {
          start: new Date(),
          end: new Date()
        }
      }
    },
    created() {
    },
    methods: {
    getSearchQuery() {
      return {
        city: this.cityInput,
        country: this.countryInput,
        address: this.addressInput,
        title: this.titleInput,
        fromDate: this.dateRange.start.getTime(),
        toDate: this.dateRange.start.getTime()
      }
    }
  }
}
</script>

<style scoped>

.button-bar{
  display: flex;
  justify-content: center;
  border-bottom: 1px solid rgb(236, 236, 236);
}
.searchbar {
  height: calc(var(--nav-height) + 10px);
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

/* .input {

} */

.input, .select, .textarea,
.calendar input{
    padding: 3px 7px;
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
    background-color: #fff;
    font-family: inherit;
    height: 48px;
    width: 160px;
    margin: 7px;
    padding: 10px;
}

.input:hover, .textarea:hover, .select:hover{
    border-color: var(--orange-primary, #f0a500);
}

.input:focus, .textarea:focus, .select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

.btn-div {
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.btn {
  height: 48px;
  width: calc(160px/1.3);
  margin: 20px;
  padding: 10px;
  text-align: center;
  font-size: 16px;
}

.btn-div p {
  margin-top: 0px;
}

.btn {
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

.btn:hover {
    background-color: var(--orange-secondary, orange);
}

.btn:active {
  transform: scale(0.98);
}

@media screen and (max-width: 669px) {
  .searchbar {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0 15px;
    overflow: scroll;
  }

}

</style>