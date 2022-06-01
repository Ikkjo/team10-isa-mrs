import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/',
      name: 'homepage',
      component: () => import('../views/Homepage.vue'),
      children: [
        {
            path: 'ships',
            name: 'ships',
            component: () => import('../components/HomepageShipListings.vue')
        },
        {
            path: 'adventures',
            name: 'adventures',
            component: () => import('../components/HomepageAdventureListings.vue')
        },
        {
            path: 'vacation-homes',
            name: 'vacation-homes',
            component: () => import('../components/HomepageVacationHomeListings.vue')
        }
      ]
    },
    {
      path: '/business-client/register',
      name: 'business-client-register',
      component: () => import('../views/BusinessClientRegistration.vue')
    },
    {
        path: '/add-rental-entity',
        name: 'add-rental-entity',
        component: () => import('../views/AddRentalEntity.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/client/register',
        name: 'client-registration',
        component: () => import('../views/ClientRegistration.vue')
    },
    {
        path: '/admin',
        name: 'admin-panel',
        component: () => import('../views/AdminPanel.vue'),
        children: [
            {
                path: 'registration-requests',
                name: 'registration-requests',
                component: () => import('../components/RegistrationRequestTable.vue')
            },
            {
                path: 'create-admin',
                name: 'create-admin',
                component: () => import('../components/CreateAdmin.vue')
            }
        ]
    },
    {
        path: '/account/manage-account',
        name: 'manage-account',
        component: () => import('../views/AccountInfo.vue')
    },
    {
        path: '/account/my-listings',
        name: 'my-listings',
        component: () => import('../views/MyListingsView.vue')
    },
    {
      path: '/account/my-listings/:id',
      name: 'my-listing',
      component: () => import('../views/RentalEntityInfo.vue'),
    }
]

const router = new VueRouter({
    routes
    })
  
    export default router