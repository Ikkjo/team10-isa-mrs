import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/',
      name: 'homepage',
      component: () => import('../views/Homepage.vue'),
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
            },
            {
                path: 'deletion-requests',
                name: 'deletion-requests',
                component: () => import('../components/DeletionRequestsTable.vue')
            },
            {
                path: 'account-info',
                name: 'admin-account-info',
                component: () => import('../components/AdminAccountInfo.vue')
            },
            {
                path: 'business-client-reports',
                name: 'business-client-reports',
                component: () => import('../components/BusinessClientReportsTable.vue')
            },
            {
                path: 'loyalty-programs',
                name: 'loyalty-programs',
                component: () => import("../components/LoyaltyPrograms.vue")
            },
            {
                path: 'loyalty-programs/create-new',
                name: 'create-loyalty-program',
                component: () => import("../components/LoyaltyProgramCreate.vue"),
            },
            {
                path: 'loyalty-programs/:id',
                name: 'loyalty-program-details',
                component: () => import("../components/LoyaltyProgramDetails.vue")
            },
            {
                path: 'earnings-report',
                name: 'earnings-report',
                component: () => import("@/components/AdminEarningsReport.vue")
            }
            // TODO: Create this component after Ilija implements user complaints
            // {
            //     path: 'user-complaints',
            //     name: 'user-complaints',
            //     component: () => import('../components/UserComplaintsTable.vue')
            // }
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
    },
    {
        path: '/admin-verification',
        name: 'admin-verification',
        component: () => import("../views/AdminVerification.vue"),
    },
    {
        path: '/account/reservations/:id/report',
        name: 'reservation-report',
        component: () => import("../views/BusinessClientReport.vue"),
    },
    {
        path: '/account/reservations',
        name: 'business-client-reservations',
        component: () => import('../views/BusinessClientReservations.vue')
    },
    {
        path: '/account/statistics',
        name: 'statistics',
        component: () => import('../views/StatisticsView.vue')
    },
    {
        path: '/listing/:id',
        name: 'rental-entity-details',
        component: () => import('../views/RentalEntityDetails.vue')
    }
]

const router = new VueRouter({
    routes
    })
  
    export default router