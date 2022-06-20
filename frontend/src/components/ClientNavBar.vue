<template>
  <NavBar>
      <template slot="nav-right">
        <nav-item link="" text="Menu" icon="arrow_drop_down" @click="toggleMenu">
            <template slot="menu">
                <dropdown-menu v-show="showMenu">
                    <template slot="items">
                        <dropdown-item link="my-account" text="My account" iconClass="material-icons" icon="account_circle"></dropdown-item>
                        <dropdown-item link="" text="My reservations" iconClass="material-icons" icon="card_travel"></dropdown-item>
                        <dropdown-item link="" text="Reservation history" iconClass="material-icons" icon="calendar_month"></dropdown-item>
                        <div class="menu-item" @click="logout">
                            <span class="icon-button material-icons">logout</span> Logout
                        </div>
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
    name: 'ClientNavBar',
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
        },
        logout() {
            window.localStorage.removeItem("jwt")
            window.localStorage.removeItem("role")
            this.$router.push({name: "homepage"})
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

.menu-item {
    height: 50px;
    display: flex;
    align-items: center;
    border-radius: 8px;
    transition: background 500ms;
    padding: 0.5rem;
    color: var(--black);
    cursor: pointer;
}

.menu-item .icon-button {
    margin-right: 0.5rem;
}

.menu-item .icon-button:hover {
    filter: none;
}

.menu-item:hover {
    background-color: rgb(236, 236, 236);
    color: var(--orange-primary);
    /* color: black; */
}

.icon-right {
    margin-left: auto;
}

</style>