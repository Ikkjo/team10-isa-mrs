import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/business-user/register',
      name: 'business-partner-register',
      component: () => import('../views/Registration.vue')
    },
    {
      path: '/add-rental-entity',
      name: 'add-rental-entity',
      component: () => import('../views/AddRentalEntity.vue')
    },
    {
      path: '/',
      name: 'login',
      component: () => import('../views/Login.vue')
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
      path: '/admin/panel',
      name: 'admin-panel',
      component: () => import('../views/AdminPanel.vue')
    },
    {
      path: '/admin/registration-requests',
      name: 'registration-requests',
      component: () => import('../views/RegistrationRequestList.vue')
    },
    {
      path: '/account/manage-account',
      name: 'manage-account',
      component: () => import('../views/AccountInfo.vue')
    },
    {
      path: '/account/my-listings',
      name: 'my-listings',
      component: () => import('../views/VacationHomeView.vue')
    },
]

const router = new VueRouter({
    routes
  })
  
  export default router