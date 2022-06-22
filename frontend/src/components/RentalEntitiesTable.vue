<template>
    <div class="wrapper">
        <h1>All Rental Entities</h1>
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRecords"
            :rows="rentalEntities"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"
            >
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'deleted'">
                    <button v-if="props.row.deleted" class="btn" @click="toggleDeletedStatus(props.row)">Enable</button>
                    <button v-else class="btn disable" @click="toggleDeletedStatus(props.row)">Disable</button>
                </span>
                <span v-else-if="props.column.field == 'type'">
                    <span v-if="props.row.type === 'Ship'" class='material-icons'>directions_boat</span>
                    <span v-else-if="props.row.type === 'VacationHome'" class='material-icons'>house</span>
                    <span v-else-if="props.row.type === 'Adventure'" class='material-icons'>phishing</span>
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
    name: 'RentalEntitiesTable',
    components: {
        VueGoodTable,
    },
    data() {
        return {
            columns: [
                {
                    label: 'Type',
                    field: 'type',
                    html: true,
                    sortable: false,
                    columnIndex: 0,
                    width: '80px',
                    tdClass: 'td-type',
                },
                {
                    label: 'Title',
                    field: 'title',
                    sortable: true,
                    columnIndex: 1,
                },
                {
                    label: 'Owner',
                    field: 'owner',
                    sortable: true,
                    columnIndex: 2,
                },
                {
                    label: 'Address',
                    field: 'address',
                    sortable: true,
                    columnIndex: 3,
                },
                {
                    label: 'City',
                    field: 'city',
                    sortable: true,
                    columnIndex: 4,
                },
                {
                    label: 'Country',
                    field: 'country',
                    sortable: true,
                    columnIndex: 5,
                },
                {
                    label: 'Enable/Disable',
                    field: 'deleted',
                    sortable: true,
                    width: '160px',
                    columnIndex: 6,
                }
            ],
            rentalEntities: null,
            totalRecords: 0,
            serverParams: {
                columnFilters: {
                },
                sort: [
                    {
                        field: 'id',
                        type: 'asc'
                    }
                ],
                page: 0, 
                perPage: 10
            },
            isLoading: false,
        }
    },
    methods: {
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
            axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/rental-entities',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalItems;
                this.rentalEntities = response.data.rentalEntities
            })
            .catch((error) => {
                alert("Couldn't fetch registration requests. See console for more info.")
                console.log(error);
            }) 
        },
        toggleDeletedStatus(rentalEntity) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/rental-entities/'+rentalEntity.id+'/toggle-deleted',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                rentalEntity.deleted = !rentalEntity.deleted;
            })
            .catch((error) => {
                alert("Couldn't fetch registration requests. See console for more info.")
                console.log(error);
            }) 
        },
    }, 
    created () {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/rental-entities',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                console.log(response)
                this.totalRecords = response.data.totalItems;
                this.rentalEntities = response.data.rentalEntities
            })
            .catch((error) => {
                alert("Couldn't fetch users. See console for more info.")
                console.log(error);
            })
    },
}
</script>

<style>
.td-type {
    text-align: center;
}

.btn.disable {
    background-color: red;
}

.btn.disable:hover {
    background-color: darkred;
}
</style>