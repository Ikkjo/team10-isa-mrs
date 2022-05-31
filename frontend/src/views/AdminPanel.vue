<template>
    <div class="admin-panel-wrapper">

        <div class="admin-sidebar">
            <div class="menu-title">Menu</div>
            <AdminMenuItem
                :link="'registration-requests'"
                :isSelected="options.registrationRequest"
                @selected="selectedOption('registrationRequest')"
                :iconClass="'material-icons'"
                :icon="'group_add'"
                :title="'Registration Requests'"
                />
            <AdminMenuItem
                :link="'create-admin'"
                :isSelected="options.createAdmin"
                @selected="selectedOption('createAdmin')"
                :iconClass="'material-icons'"
                :icon="'person_add'"
                :title="'Create Admin'"
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
                createAdmin: false,
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