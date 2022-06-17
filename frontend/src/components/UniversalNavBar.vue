<template>
    <div id="nav">
        <div v-if="this.isBusinessClientLoggedIn()">
            <BusinessClientNavBar/>
        </div>
        <div v-else-if="this.isClientLoggedIn()">
            <ClientNavBar/>
        </div>
        <div v-else-if="this.isAdminLoggedIn()"/>
        <div v-else>
            <UnknownUserNavBar/>  
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from './BusinessClientNavBar.vue';
import UnknownUserNavBar from './UnknownUserNavBar.vue';
import ClientNavBar from './ClientNavBar.vue';
export default {
    name: 'UniversalNavBar',
    components: {
    BusinessClientNavBar,
    UnknownUserNavBar,
    ClientNavBar
},
    data() {
        return{
            userRole: '',
            clientLogged: false,
            businessClientLogged: false,
            adminLogged: false,
            client: ["CLIENT"],
            businessClient: ["HOUSE_OWNER", "SHIP_OWNER", "FISHING_INSTRUCTOR"],
            admin: ["MAIN_ADMIN", "ADMIN", "UNVERIFIED_ADMIN"]
        }
    },
    methods: {
        isClientLoggedIn(){
            return this.client.includes(localStorage.getItem('userRole'));
        },
        isBusinessClientLoggedIn() {
            return this.businessClient.includes(localStorage.getItem('userRole'));
        },
        isAdminLoggedIn() {
            return this.admin.includes(localStorage.getItem('userRole'));
        }
    }



}
</script>

<style scoped>

</style>