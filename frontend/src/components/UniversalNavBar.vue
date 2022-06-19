<template>
    <div id="nav">
        <div v-if="businessClient.includes(role)">
            <BusinessClientNavBar/>
        </div>
        <div v-else-if="client === role">
            <ClientNavBar/>
        </div>
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
            role: null,
            client: "CLIENT",
            businessClient: ["HOUSE_OWNER", "SHIP_OWNER", "FISHING_INSTRUCTOR"],
        }
    },
    created() {
        this.role = localStorage.getItem('role')
        }
}
</script>

<style scoped>

</style>