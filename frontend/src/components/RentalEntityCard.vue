<template>
    <div id="rental-entity-card" @click="detailedView()" >
        <img class="cover-photo" :src="rentalEntity.pictures[0]" alt="" srcset="">
        <div class="container">
            <div class="main-text">{{rentalEntity.title}}</div>
            <div class="desc">
                <div>{{rentalEntity.address.address}} {{rentalEntity.address.city}} {{rentalEntity.address.country}}</div>
                <div>{{rentalEntity.rooms}} rooms, {{rentalEntity.beds}} beds</div>
            </div>
            <div class="main-text">${{rentalEntity.price}} night</div>
            <div class="btn-container">
                <button v-if="isBusinessClient()" class="btn" @click.stop="showModal = true">Add Action</button>
            </div>
        </div>
        <portal to="body" v-if="isBusinessClient()">
            <!-- use the modal component, pass in the prop -->
            <ActionCreationModal
                @save="saveAction()"
                :show="showModal"
                @close="showModal=false"
                :buttonDisabled="buttonDisabled"
                class="modal">
                <template #body>
                    <ActionCreation 
                        :id="rentalEntity.id"
                        @updated:price="priceUpdated"
                        @updated:dateRange="dateRangeUpdated"
                        />
                </template>
            </ActionCreationModal>
      </portal>
    </div>
</template>

<script>
import ActionCreation from '@/components/ActionCreation.vue'
import ActionCreationModal from '@/components/ActionCreationModal.vue'
export default {
    name: 'RentalEntityCard',
    props: ['rentalEntity'],
    components: {
        ActionCreation,
        ActionCreationModal
    },
    data() {
        return {
            userRole: null,
            showModal: false,
            action: {
                price: null,
                dateRange: null,
            },
        }
    },
    methods: {
        detailedView() {
            let userRole = localStorage.getItem('userRole');
            if (userRole === null || userRole === 'CLIENT') {
                console.log('Shows rental entity details for client')
            }
            else if (this.isBusinessClient()) {
                this.$router.push({
                    name: 'my-listing',
                    params: {
                        id: this.rentalEntity.id,
                    }
                })
            }
        },
        isBusinessClient() {
            return ['HOUSE_OWNER','SHIP_OWNER', 'FISHING_INSTRUCTOR'].includes(localStorage.getItem('userRole'))
        },
        addAction() {
            console.log("Add action");
        },
        priceUpdated(price) {
            console.log("updated price")
            this.action.price = price
        },
        dateRangeUpdated(dateRange) {
            console.log("updated date")
            this.action.dateRange = dateRange
        },
        saveAction() {
            console.log(this.action)
        }
    },
    computed: {
        buttonDisabled() {
            return this.action.dateRange === null || this.action.dateRange === [] || this.action.price === null || this.action.price == 0 
        }
    }
}
</script>

<style scoped>
#rental-entity-card {
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
    cursor: pointer;
    max-width: 100%;
}


.cover-photo {
    object-fit: cover;
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
    width: 100%;
    height: 250px;
    border-radius: 5px 5px 0px 0px;
}

.desc {
    color: grey;
    padding: 5px 0;
}

.desc :first-child {
    margin-bottom: 2px;
}

.container {
    padding: 6px 5px;
    display: block !important;
}

.btn-container {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 6px 5px;
}

.btn {
    width: 100%;
    margin-top: 2px;
}

</style>