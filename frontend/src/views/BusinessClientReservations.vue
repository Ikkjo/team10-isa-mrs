<template>
<div>
    <BusinessClientNavBar id="nav"/>
    <div class="container">
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :totalRows="totalRecords"
            :rows="rows"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"/>
    </div>

</div>
</template>

<script>
import BusinessClientNavBar from '@/components/BusinessClientNavBar.vue'
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import axios from 'axios'

export default {
    components: {
        BusinessClientNavBar,
        VueGoodTable
    },
    data() {
        return {
            isLoading: false,
            columns: [
                {
                    label: 'Client',
                    field: 'client',
                    columnIndex: 0,
                },
                {
                    label: 'Rental Entity',
                    field: 'rentalEntityTitle',
                    columnIndex: 1,
                },
                {
                    label: 'Start Date',
                    field: 'startDate',
                    type: 'date',
                    formatFn: this.formatDate,
                },
                {
                    label: 'End Date',
                    field: 'endDate',
                    formatFn: this.formatDate
                },
                {
                    label: 'Price',
                    field: 'price',
                    formatFn: this.formatPrice
                },
                {
                    label: 'Status',
                    field: 'status'
                },
            ],
            rows: [],
            totalRecords: 0,
            serverParams: {
                columnFilters: {
                },
                sort: [
                    {
                        field: 'client',
                        type: 'asc'
                    }
                ],
                page: 0, 
                perPage: 10
            }
        }
    },
    methods: {
        formatDate(value) {
            return new Date(value).toDateString();
        },
        formatPrice(value) {
            return '$' + value;
        },
        updateParams(newProps) {
            this.serverParams = Object.assign({}, this.serverParams, newProps);
        },
        onPageChange(params) {
            this.updateParams({page: params.currentPage-1});
            this.loadItems();
        },
        onPerPageChange(params) {
            console.log(params.currentPerPage)
            this.updateParams({perPage: params.currentPerPage});
            this.loadItems();
        },
        onSortChange(params) {
            this.updateParams({
                sort: [{
                        type: params[0].type,
                        field: params[0].field,
                    }],
            });
            this.loadItems();
        },
        onColumnFilter(params) {
            this.updateParams(params);
            this.loadItems();
        },
        // load items is what brings back the rows from server
        loadItems() {
            let sortString = ''+this.serverParams.sort[0].field+','+this.serverParams.sort[0].type
            console.log(sortString)
            axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reservations',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalPages
                this.rows = response.data.reservations
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
        }
    },
    mounted() {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reservations',
                params: {page: this.serverParams.page, size: this.serverParams.perPage},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalPages
                this.rows = response.data.reservations
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
    }
}
</script>

<style scoped>

.container {
    max-width: 100%;
    margin-top: 70px;
    padding: 0px 10px;
}


</style>