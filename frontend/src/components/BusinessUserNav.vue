<template>
  <NavBar>
      <template slot="nav-right">
        <nav-item link="nav" text="List your property"/>
        <nav-item link="" text="Menu" iconClass="material-icons" icon="arrow_drop_down" @click="toggleMenu">
            <template slot="menu">
                <dropdown-menu v-show="showMenu">
                    <template slot="items">
                        <dropdown-item link="nav" text="Manage account" iconClass="material-icons" icon="account_circle"></dropdown-item>
                        <dropdown-item link="nav" text="Your listings" iconClass="material-icons" icon="house"></dropdown-item>
                        <dropdown-item link="nav" text="Reservations" iconClass="material-icons" icon="calendar_month"></dropdown-item>
                        <dropdown-item link="nav" text="Reviews" iconClass="material-icons" icon="reviews"></dropdown-item>
                        <dropdown-item link="nav" text="Statistics" iconClass="material-icons" icon="query_stats"></dropdown-item>
                        <dropdown-item link="logout" text="Logout" iconClass="material-icons" icon="logout"></dropdown-item>
                    </template>
                </dropdown-menu>
            </template>
        </nav-item>
      </template>
  </NavBar>
</template>

<script>
import NavItem from '@/components/NavItem.vue'
import NavBar from '@/components/NavBar.vue'
import DropdownMenu from '@/components/DropdownMenu.vue'
import DropdownItem from '@/components/DropdownItem.vue'
export default {
    name: 'BusinessUserNav',
    components: {
        NavBar,
        NavItem,
        DropdownMenu,
        DropdownItem,
    },
    data() {
        return {
            showMenu: false
        }
    },
    methods: {
        toggleMenu() {
            this.showMenu = !this.showMenu
        },
        close (e) {
            if (!this.$el.contains(e.target)) {
                this.showMenu = false
            }
        } 
    },
    mounted () {
        document.addEventListener('click', this.close)
    },
    beforeDestroy () {
        document.removeEventListener('click',this.close)
    }
}
</script>

<style>

</style>