<template>
    <div class="admin-panel-wrapper">
        <div class="admin-sidebar">
            <div class="menu-title">Menu</div>
            <AdminMenuItem
                link="registration-requests"
                iconClass="material-icons"
                icon="group_add"
                title="Registration Requests"
                />
            <AdminMenuItem
                link="client-reviews"
                iconClass="material-icons"
                icon="reviews"
                title="Client Reviews"
                />
            <AdminMenuItem
                link="user-complaints"
                iconClass="material-icons"
                icon="flag"
                title="User Complaints"
                />
            <AdminMenuItem
                link="business-client-reports"
                iconClass="material-icons"
                icon="flag"
                title="Business Client Reports"
                />
            <AdminMenuItem
                link="deletion-requests"
                iconClass="material-icons"
                icon="person_remove"
                title="Deletion Requests"
                />
            <AdminMenuItem
                link="loyalty-programs"
                iconClass="material-icons"
                icon="card_membership"
                title="Loyalty Program"
                />
            <AdminMenuItem
                link="users"
                iconClass="material-icons"
                icon="group"
                title="All Users"
                />
            <AdminMenuItem
                link="rental-entities"
                iconClass="material-icons-outlined"
                icon="real_estate_agent"
                title="All Rental Entities"
                />
            <AdminMenuItem
                link="earnings-report"
                iconClass="material-icons"
                icon="bar_chart"
                title="Earnings Report"
                />
            <AdminMenuItem
                v-if="isMainAdmin"
                link="create-admin"
                iconClass="material-icons"
                icon="person_add"
                title="Create Admin"
                />
            <div class="bottom">
                <AdminMenuItem
                    link="admin-account-info"
                    iconClass="material-icons"
                    icon="manage_accounts"
                    title="Account Info"
                    />
                <div @click="logout" class="option">
                    <span class="icon-class material-icons">logout</span>
                    <span class="text">Logout</span>
                </div>
            </div>
        </div>
        <div class="admin-panel-body">
            <router-view/>
        </div>
    </div>
</template>

<script>
import AdminMenuItem from '../components/AdminMenuItem.vue'
export default {
    name: 'AdminPanel',
    components: {
        AdminMenuItem,
    },
    data() {
        return {
            role: null
        }
    },
    methods: {
        logout() {
            window.localStorage.removeItem('role');
            window.localStorage.removeItem('jwt');
            this.$router.push({name: 'login'})
        }
    },
    computed: {
        isMainAdmin() {
            return this.role !== null && this.role === 'MAIN_ADMIN' 
        }
    },
    created () {
        this.role = window.localStorage.getItem('role');
        if (this.$route.name === 'admin-panel')
            this.$router.push({name: 'registration-requests'});
    },
}
</script>

<style>

h1 {
    padding: 20px;
}

.admin-sidebar {
    height: 100vh; /* Full-height: remove this if you want "auto" height */
    width: 200px; /* Set the width of the sidebar */
    position: fixed; /* Fixed Sidebar (stay in place on scroll) */
    z-index: 1; /* Stay on top */
    top: 0; /* Stay at the top */
    left: 0;
    background-color: var(--orange-primary, orange); /* Black */
    overflow-x: hidden; /* Disable horizontal scroll */
    padding-top: 15px;
}

.admin-sidebar .menu-title {
    font-size: 1.8rem;
    padding-left: 10px;
    margin-bottom: 20px;
    text-decoration: none;
    display: block;
    color: white;
}

.admin-sidebar .option {
    height: 56px;
    text-decoration: none;
    color: white;
    display: flex;
    align-items: center;
    gap:10px;
    transition: 0.3s;
    padding:5px 15px;
    cursor: pointer;
}

.admin-sidebar .option.router-link-active {
    background-color: var(--orange-secondary, darkorange);
}

.admin-sidebar .option:hover {
    background-color: var(--orange-secondary, darkorange);
}

.admin-sidebar .option .icon-class {
    font-size: 2rem;
}

.admin-sidebar .option .text {
    font-size: 1.2rem;    
}

.bottom {
    position: absolute;
    bottom: 0;
    width: 200px;
}

/* When you mouse over the navigation links, change their color */
 
/* Style page content */
.admin-panel-body {
  margin-left: 200px; /* Same as the width of the sidebar */
  padding: 0px 10px;
}

/* Table style */
table {
    font-family: arial, sans-serif;
    width: 100%;
    border-collapse: collapse;
    margin: 25px 0;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

tbody tr {
    border-bottom: 1px solid #dddddd;
}

tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

tbody tr:last-of-type {
    border-bottom: 2px solid var(--orange-primary, orange);
}

th, td {
    padding: 12px 15px;
}

td {
    border-right: 1px solid lightgray;
    padding: 6px 10px;
    word-wrap: break-all;
    overflow-wrap:break-word;
}

thead tr {
    background-color: var(--orange-primary, orange);
    color: white;
    text-align: left;
}

</style>