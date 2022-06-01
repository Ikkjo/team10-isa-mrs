<template>
  <search id="searchbar">
       <SearchBarItem
                :link="'ships'"
                :isSelected="options.ships"
                @selected="selectedOption('ships')"
                :iconClass="'material-icons-round'"
                :icon="'directions_boat'"
                :title="'Ships'"
       />
  </search>
</template>

<script>
import SearchBarItem from './SearchBarItem.vue'
export default {
    name: 'SearchBar',
    components: {
    SearchBarItem
},
    data() {
        return {
            options:{
                registrationRequest: false,
            }
        }
    },
    methods: {
        selectedOption(option) {
            if (!this.options[option]){
                for (const o in this.options){
                    if (o === option)
                        this.options[o] = true;
                    else
                        this.options[o] = false
                }
            }
        }
    }

}
</script>

<style>
.searchbar {
  height: var(--nav-height);
  border-bottom: 1px solid rgb(236, 236, 236);
  padding: 0 10%;
  background-color: white;
}

.searchbar-nav {
  max-width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}

</style>