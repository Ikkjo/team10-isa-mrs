import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Registration.vue')
    },
    {
      path: '/add-rental-entity',
      name: 'add-rental-entity',
      component: () => import('../views/AddRentalEntity.vue')
    },
    {
      path: '/admin/panel/',
      name: 'admin-panel',
      component: () => import('../views/AdminPanel.vue')
    }
]

const router = new VueRouter({
    routes
  })
  
  export default router