<template>
  <nav id="searchbar"
  @keypress.enter="$emit('searchPressed', getSearchQuery())">
      <div class="searchbar">
        <input class="input" type="text" v-model="cityInput" placeholder="City" />
        <input class="input" type="text" v-model="countryInput" placeholder="Country" />
      
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
        <button class="btn" @click="$emit('searchPressed', getSearchQuery())">Search</button>
        </div>
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
        dateRange: null
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
        fromDate: this.dateRange.start.getTime(),
        toDate: this.dateRange.start.getTime()
      }
    }
  }
    
}
</script>

<style>
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

.input {
  height: 48px;
  width: 160px;
  margin: 7px;
  padding: 10px;
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