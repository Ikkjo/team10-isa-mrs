<template>
    <div class="info-section">
        <h2>Remove listing</h2>
        <div class="info-items">
            <div class="info-item">
                <div class="label-div">
                    <span class="material-icons label-icon">remove_circle</span>
                    <span class="label">Want to permanently remove this listing?</span>
                </div>
                <div @click="showModal = true" class="edit noselect">Remove</div>
            </div>
            <portal to="body">
                <!-- use the modal component, pass in the prop -->
                <OkCancelModal
                v-if="showModal"
                :show="showModal"
                @close="showModal=false"
                @ok="removeListing()"/>
            </portal>
        </div>
    </div>
</template>

<script>
import OkCancelModal from '@/components/OkCancelModal.vue'
import axios from 'axios'
export default {
    name: 'RemoveListing',
    props: ['rentalEntityId'],
    components: {
        OkCancelModal,
    },
    data() {
        return {
            showModal: false,
        }
    },
    methods: {
        removeListing() {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/rental-entity/delete/'+this.rentalEntityId,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then(() => {
                    alert('Listing removed')                    
                    this.$router.push({name: 'my-listings'})
                })
                .catch((error) => {
                    alert("Something went wrong")
                    console.log(error);
                }) 
        }
    }

}
</script>

<style scoped>
.info-item {
    min-width: 300px;
    width: 100%;
    position: relative;
}

.label-div {
    display: flex;
    align-items: center;
}

.label-icon {
    margin-right: 5px;
}

.edit {
    display: inline-block;
    position: absolute;
    right: 0;
    top: 0;
    margin-top: 20px;
    text-decoration: underline;
    cursor: pointer;
    color: red;
}

.edit:hover {
    color: darkred;
}

.edit:active {
    color: red;
}

</style>