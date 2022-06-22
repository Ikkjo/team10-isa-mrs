import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'homepage',
        component: () => import('../views/Homepage.vue'),
        meta: {
            guest: true,
            client: true,
            businessClient: true,
        }
    },
    {
        path: '/business-client/register',
        name: 'business-client-register',
        component: () => import('../views/BusinessClientRegistration.vue'),
        meta: {
            guest: true,
        }
    },
    {
        path: '/add-rental-entity',
        name: 'add-rental-entity',
        component: () => import('../views/AddRentalEntity.vue'), 
        meta: {
            businessClient: true,
        }
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login.vue'),
        meta: {
            guest: true,
        }
    },
    {
        path: '/client/register',
        name: 'client-registration',
        component: () => import('../views/ClientRegistration.vue'),
        meta: {
            guest: true,
        }
    },
    {
        path: '/admin',
        name: 'admin-panel',
        component: () => import('../views/AdminPanel.vue'),
        meta: {
            admin: true,
        },
        children: [
            {
                path: 'registration-requests',
                name: 'registration-requests',
                component: () => import('../components/RegistrationRequestTable.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'create-admin',
                name: 'create-admin',
                component: () => import('../components/CreateAdmin.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'deletion-requests',
                name: 'deletion-requests',
                component: () => import('../components/DeletionRequestsTable.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'account-info',
                name: 'admin-account-info',
                component: () => import('../components/AdminAccountInfo.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'business-client-reports',
                name: 'business-client-reports',
                component: () => import('../components/BusinessClientReportsTable.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'loyalty-programs',
                name: 'loyalty-programs',
                component: () => import("../components/LoyaltyPrograms.vue"),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'loyalty-programs/create-new',
                name: 'create-loyalty-program',
                component: () => import("../components/LoyaltyProgramCreate.vue"),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'loyalty-programs/:id',
                name: 'loyalty-program-details',
                component: () => import("../components/LoyaltyProgramDetails.vue"),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'earnings-report',
                name: 'earnings-report',
                component: () => import("@/components/AdminEarningsReport.vue"),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'users',
                name: 'users',
                component: () => import('../components/UsersTable.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'rental-entities',
                name: 'rental-entities',
                component: () => import('../components/RentalEntitiesTable.vue'),
                meta: {
                    admin: true,
                },
            },
            {
                path: 'client-reviews',
                name: 'client-reviews',
                component: () => import('../components/ReviewsTable.vue'),
                meta: {
                    admin: true,
                },
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
        name: 'business-client-manage-account',
        component: () => import('../views/AccountInfo.vue'),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/account/my-listings',
        name: 'my-listings',
        component: () => import('../views/MyListingsView.vue'),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/account/my-listings/:id',
        name: 'my-listing',
        component: () => import('../views/RentalEntityInfo.vue'),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/admin-verification',
        name: 'admin-verification',
        component: () => import("../views/AdminVerification.vue"),
        meta: {
            unverifiedAdmin: true,
        },
    },
    {
        path: '/account/reservations/:id/report',
        name: 'reservation-report',
        component: () => import("../views/BusinessClientReport.vue"),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/account/reservations',
        name: 'business-client-reservations',
        component: () => import('../views/BusinessClientReservations.vue'),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/account/statistics',
        name: 'statistics',
        component: () => import('../views/StatisticsView.vue'),
        meta: {
            businessClient: true,
        },
    },
    {
        path: '/listing/:id',
        name: 'listing-details-view',
        component: () => import('../views/RentalEntityDetailsView.vue'),
        meta: {
            guest: true,
            client: true,
            businessClient: true,
            unverifiedAdmin: true,
            admin: true,
        }
    },
    {
        path: '/client/manage-account',
        name: 'client-manage-account',
        component: () => import('../components/ClientAccountInfo.vue'),
        meta: {
            client: true,
        },
    },
    {
        path: '/404-not-found',
        name: 'not-found-404',
        component: () => import('../views/NotFound404.vue'),
        meta: {
            guest: true,
            client: true,
            businessClient: true,
            unverifiedAdmin: true,
            admin: true,
        }
    }

]

const router = new VueRouter({
    routes
    })

router.beforeEach((to, from, next) => {
    let didRoute = false;
    if (to.matched.some(record => record.meta.guest)) {
        if (localStorage.getItem('jwt') === null) {
            next()
            didRoute = true;
        }
        
    }
    if (to.matched.some(record => record.meta.client)) {
        if (
            localStorage.getItem('jwt') !== null 
            && localStorage.getItem('role') === 'CLIENT'
            ) {
            next();
            didRoute = true;
        }
    }
    if (to.matched.some(record => record.meta.businessClient)) { 
        if (
            localStorage.getItem('jwt') !== null 
            && ['SHIP_OWNER', 'HOUSE_OWNER', 'FISHING_INSTRUCTOR']
                .includes(localStorage.getItem('role'))
            ) {
            next();
            didRoute = true;
        }
    }
    if (to.matched.some(record => record.meta.unverifiedAdmin)) {
        if (
            localStorage.getItem('jwt') !== null 
            && localStorage.getItem('role') === 'UNVERIFIED_ADMIN'
            ) {
            next();
            didRoute = true;
        }
    }
    if (to.matched.some(record => record.meta.admin)) {
        if (
            localStorage.getItem('jwt') !== null 
            && ['MAIN_ADMIN', 'ADMIN']
                .includes(localStorage.getItem('role'))
            ) {
            next();
            didRoute = true;
        }
    }
    if (!didRoute)
        next({name: 'not-found-404'});
})
  
export default router