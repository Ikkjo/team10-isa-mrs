<template>
  <nav id="searchbar">
      <div class="searchbar">
                 <SearchBarItem
                :link="'ships'"
                :iconClass="'material-icons-round'"
                :icon="'directions_boat'"
                :title="''"
       />
              <SearchBarItem
                :link="'vacation-homes'"
                :iconClass="'material-icons-round'"
                :icon="'home'"
                :title="''"
       />
              <SearchBarItem
                :link="'adventures'"
                :iconClass="'material-icons-round'"
                :icon="'phishing'"
                :title="''"
       />
      </div>
  </nav>
</template>

<script>
import SearchBarItem from './SearchBarItem.vue'
export default {
    name: 'SearchBar',
    components: {
    SearchBarItem
    }
}
</script>

<style>
.searchbar {
  height: var(--nav-height);
  border-bottom: 1px solid rgb(236, 236, 236);
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

.searchbar-nav {
  max-width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}

.searchbar .filter-button {
    height: 10px;
    text-decoration: none;
    color: var(--orange);
    display: flex;
    align-items: center;
    gap:10px;
    transition: 0.3s;
    cursor: pointer;
}

.searchbar .filter-button.selected {
    color: var(--orange-secondary, darkorange);
}

.searchbar .filter-button:hover {
    color: var(--orange-secondary, darkorange);
}

.searchbar .filter-button .icon-class {
  font-size: 2rem;
}

.searchbar .filter-button .text {
    font-size: 1.2rem;    
}

</style>