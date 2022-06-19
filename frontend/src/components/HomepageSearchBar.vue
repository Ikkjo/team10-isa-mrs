<template>
  
  <nav id="searchbar"
  @keypress.enter="$emit('searchPressed', getSearchQuery())">
  
      <div class="searchbar" >
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
                            <input :value="inputValue.start" v-on="inputEvents.start" placeholder="From"/>
                            <input :value="inputValue.end" v-on="inputEvents.end" placeholder="To"/>
                        </template>
          </DatePicker>
        </div>
        <div>
        <button class="btn" @click="$emit('searchPressed', getSearchQuery())">
          Search
          <span class="material-icons-outlined">search</span>
        </button>
        </div>
        
      </div>
      <div class="filter-bar">
        <NumberInput class="number-input"
                            @updated="minPriceUpdated" 
                            placeholder="" 
                            label="Min price" 
                            :increment="5" 
                            :minValue="0" 
                            :maxValue="100000000"
                            />
        <NumberInput class="number-input"
                            @updated="maxPriceUpdated" 
                            placeholder="" 
                            label="Max price" 
                            :increment="5" 
                            :minValue="0" 
                            :maxValue="100000000"
                            />
                         <SearchBarItem
                :link="'ships'"
                :iconClass="'material-icons-outlined'"
                :icon="'directions_boat'"
                :type="'Ship'"
                :title="'Ships'"
                @SearchBarButtonToggled="typeFilterChanged"
       />
              <SearchBarItem
                :link="'vacation-homes'"
                :iconClass="'material-icons-outlined'"
                :icon="'house'"
                :type="'VacationHome'"
                :title="'Vacation homes'"
                @SearchBarButtonToggled="typeFilterChanged"
       />
              <SearchBarItem
                :link="'adventures'"
                :iconClass="'material-icons-outlined'"
                :icon="'phishing'"
                :type="'Adventure'"
                :title="'Adventures'"
                @SearchBarButtonToggled="typeFilterChanged"
       />
      </div>
  </nav>
</template>

<script>
import SearchBarItem from './HomepageSearchBarItem.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import NumberInput from './NumberInput.vue'
export default {
    name: 'HomepageSearchBar',
    components: {
    SearchBarItem,
    DatePicker,
    NumberInput
    },
    data() {
      return {
        cityInput: '',
        countryInput: '',
        titleInput: '',
        addressInput: '',
        dateRange: {
          start: null,
          end: null
        },
        minPriceInput: 0,
        maxPriceInput: 0,
        typeFilter: {
        VacationHome: false,
        Ship: false,
        Adventure: false
        },
      }
    },
    created() {
    },
    methods: {
    getSearchQuery() {

      let from = this.dateRange.start
      let to = this.dateRange.end

      if(!from) {
        from = 0;
      }

      if(!to) {
        to = 0;
      }



      return {
        city: this.cityInput,
        country: this.countryInput,
        address: this.addressInput,
        title: this.titleInput,
        fromDate: from,
        toDate: to,
        minPrice: this.minPriceInput,
        maxPrice: this.maxPriceInput,
        ofType: this.typeFilterToString()
      }
    },
    minPriceUpdated(newPrice) {
      this.minPriceInput = newPrice
    },
    maxPriceUpdated(newPrice) {
      this.maxPriceInput = newPrice
    },
    typeFilterChanged(filterButtonData) {
      let type = filterButtonData.type
      let pressed = filterButtonData.state  
      console.log(filterButtonData)
      if(pressed) {
          switch(type) {
            case "VacationHome":
              this.typeFilter.VacationHome = true;
              break;
            case "Ship":
              this.typeFilter.Ship = true;
              break;
            case "Adventure":
              this.typeFilter.Adventure = true;
              break;
          }
        } else {
                    switch(type) {
            case "VacationHome":
              this.typeFilter.VacationHome = false;
              break;
            case "Ship":
              this.typeFilter.Ship = false;
              break;
            case "Adventure":
              this.typeFilter.Adventure = false;
              break;
          }

        }
      },
      typeFilterToString() {
        let retStr = ""
        if(this.typeFilter.VacationHome) {
          retStr = retStr.concat("VacationHome,")
          console.log("JEBEM TI MAJKU")
        }
        if(this.typeFilter.Ship) {
          retStr = retStr.concat("Ship,")
        }
        if(this.typeFilter.Adventure) {
          retStr = retStr.concat("Adventure,")
        }
        console.log(retStr)
        if (retStr){
          retStr = retStr.substring(0, retStr.length-1) // brise se , ako se dodalo nesto
        }
        return retStr
      },
  }
}
</script>

<style scoped>

.filter-bar{
  display: flex;
  justify-content: center;
  background-color: white;
  border-bottom: 1px solid rgb(236, 236, 236);
}

/* Button text doesn't align to center, needs fix */
.btn {
  margin: 7px;
  text-align: center;
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

.number-input {
   margin: 0 7px 0 7px;
}

.input:hover, .textarea:hover, .select:hover{
    border-color: var(--orange-primary, #f0a500);
}

.input:focus, .textarea:focus, .select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

@media screen and (max-width: 1200px) {
  .searchbar {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0 15px;
    height: auto;
  }

  #search {
    position: absolute;
  }

}

</style>