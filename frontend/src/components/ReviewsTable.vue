<template>
    <div class="wrapper">
        <h1>All Client Reviews</h1>
        <VueGoodTable
            mode="remote"
            :total-rows="totalRecords"
            :rows="reviews"
            :columns="columns"
            >
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'accept'">
                    <button class="btn" @click="acceptReview(props.rows.id)">Accept</button>
                </span>
                <span v-else-if="props.column.field == 'decline'">
                    <button class="btn" @click="declineReview(props.rows.id)">Decline</button>
                </span>
                <span v-else>
                    {{props.formattedRow[props.column.field]}}
                </span>
            </template>
        </VueGoodTable>
    </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import axios from 'axios'

export default {
    name: 'UserTable',
    components: {
        VueGoodTable
    },
    data() {
        return {
            columns: [
                {
                    label: 'Client',
                    field: 'clientEmail',
                    columnIndex: 0,
                },
                {
                    label: 'Rental Entity',
                    field: 'rentalEntityTitle',
                    columnIndex: 1,
                },
                {
                    label: 'Rating',
                    field: 'rating',
                    columnIndex: 2,
                },
                {
                    label: 'Message',
                    field: 'message',
                    columnIndex: 3,
                },
                {
                    label: 'Decide',
                    field: 'decide',
                    width: '120px',
                    tdClass: 'td-button',
                    columnIndex: 4,
                }
            ],
            reviews: null,
        }
    },
    methods: {
        removeRequest(reviewId) {
            for (let i = 0; i < this.reviews.length; i++) {
                console.log(this.reviews[i].id)
                if (this.reviews[i].id === reviewId){
                    this.reviews.splice(i, 1);
                    break;
                }
            }
        },
        acceptReview(reviewId) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/reviews/'+reviewId+'/accept',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                this.removeReview(reviewId);
            })
            .catch((error) => {
                alert("Couldn't accept review. See console for more info.")
                console.log(error);
            }) 
        },
        declineReview(reviewId) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/reviews/'+reviewId+'/decline',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                this.removeReview(reviewId);
            })
            .catch((error) => {
                alert("Couldn't decline review. See console for more info.")
                console.log(error);
            }) 
        },
    },
    created () {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/reviews',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.reviews = response.data
            })
            .catch((error) => {
                alert("Couldn't fetch reviews. See console for more info.")
                console.log(error);
            })
    },
}
</script>

<style>
.td-button {
    text-align: center;
}
</style>