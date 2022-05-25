<template>
    <div class="admin-panel-wrapper">
        <!-- <h1>Admin Panel</h1><hr>
        <div class="admin-panel">
            <router-link class="option" to="registration-requests">
                <div class="registration-requests">
                    <h2 class="title">Registration Requests</h2>
                    <p class="description">
                        List of registration requests from business clients. 
                        Accept valid registration requests.
                    </p>
                </div>
            </router-link>
        </div> -->
        <div class="admin-sidebar">
            <div class="menu-title">Menu</div>
            <AdminMenuItem
                :link="'registration-requests'"
                :isSelected="options.registrationRequest"
                @selected="selectedOption('registrationRequest')"
                :iconClass="'material-icons'"
                :icon="'person_add'"
                :title="'Registration Requests'"
                />
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
    },
}
</script>

<style>
/* 
h1 {
    padding: 20px;
}

.admin-panel {
    display: flex;
}

.option {
    margin: 20px;
}

.option .registration-requests {
    border-radius: 5px;
    border: 2px solid var(--orange-primary, orange);
    width: 300px;
    height: 150px;
    box-shadow:0 10px 16px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19) !important;
    transition: 0.5s;
}

.option .registration-requests:hover {
    border-color: var(--orange-secondary, darkorange);
}

.option .registration-requests h2 {
    margin-top: 10px;
    margin-left: 10px;
}

.option .registration-requests p {
    padding: 20px 10px;
} */

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

.admin-sidebar .option.selected {
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

/* When you mouse over the navigation links, change their color */
 
/* Style page content */
.admin-panel-body {
  margin-left: 200px; /* Same as the width of the sidebar */
  padding: 0px 10px;
}
</style>