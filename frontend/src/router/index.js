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
      component: () => import('../views/RentalEntityBasicInfo.vue')
    },
    {
        path: '/add-rental-entity/additional-info',
        name: 'add-rental-entity-additional-info',
        component: () => import('../views/RentalEntityAdditionalInfo.vue')
    },
    {
      path: '/add-rental-entity/price',
      name: 'add-rental-entity-price',
      component: () => import('../views/RentalEntityPrice.vue')
    },
]

const router = new VueRouter({
    routes
  })
  
  export default router