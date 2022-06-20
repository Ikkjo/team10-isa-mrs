<template>
    <div class="wrapper">
        <h1>Business Client Reports</h1>
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRecords"
            :rows="reports"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"
            >
            <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'penalize'">
                    <button class="btn" @click="penalizeClient(props.row.id)">Penalize</button>
                </span>
                <span v-else-if="props.column.field == 'dont-penalize'">
                    <button class="btn" @click="dontPenalizeClient(props.row.id)">Don't Penalize</button>
                </span>
                <span v-else-if="props.column.field == 'role'">
                    <span v-if="props.row.role === 'SHIP_OWNER'" class='material-icons'>directions_boat</span>
                    <span v-else-if="props.row.role === 'HOUSE_OWNER'" class='material-icons'>house</span>
                    <span v-else class='material-icons'>phishing</span>
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
import axios from 'axios';

export default {
    name: 'BusinessClientReportsTable',
    components: {
        VueGoodTable,
    },
    data() {
        return {
            columns: [
                {
                    label: 'Type',
                    field: 'reportedByRole',
                    html: true,
                    columnIndex: 0,
                    tdClass: 'td-role',
                },
                {
                    label: 'Reported By',
                    field: 'reportedBy',
                    columnIndex: 1,
                },
                {
                    label: 'Person Reported',
                    field: 'personReported',
                    columnIndex: 2,
                },
                {
                    label: 'Rental Entity Title',
                    field: 'rentalEntityTitle',
                    columnIndex: 3,
                },
                {
                    label: "Penalization Reason",
                    field: 'message',
                    columnIndex: 4,
                },
                {
                    label: 'Penalize',
                    field: 'penalize',
                    sortable: false,
                    width: '160px',
                    columnIndex: 5,
                },
                {
                    label: "Don't Penalize",
                    field: 'dont-penalize',
                    sortable: false,
                    width: '160px',
                    columnIndex: 6,
                }
            ],
            reports: null,
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
        loadItems() {
            let sortString = ''+this.serverParams.sort[0].field+','+this.serverParams.sort[0].type
            axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/reports/not-reviewed',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalPages * this.serverParams.perPage;
                this.reports = response.data.reservations
            })
            .catch((error) => {
                this.isLoading = false;
                alert("Couldn't fetch reports. See console for more info.")
                console.log(error);
            })
        },
        penalizeClient(id) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reports/'+id+'/penalize',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                this.loadItems();
            })
            .catch((error) => {
                alert("Something went wrong. See console")
                console.log(error);
            })
        },
        dontPenalizeClient(id) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reports/'+id+'/dont-penalize',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                this.loadItems();
            })
            .catch((error) => {
                alert("Something went wrong. See console")
                console.log(error);
            })
        }
    },
    created () {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/reports/not-reviewed',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        })
        .then((response) => {
            this.totalRecords = response.data.totalPages * this.serverParams.perPage;
            this.reports = response.data.reservations
        })
        .catch((error) => {
            this.isLoading = false;
            alert("Couldn't fetch reports. See console for more info.")
            console.log(error);
        })
    },
}
</script>

<style scoped>
.btn.accept {
    background-color: green;
}

.btn.accept:hover {
    background-color: darkgreen;
}

.btn.decline {
    background-color: red;
}

.btn.decline:hover {
    background-color: darkred;
}

.td-role {
    text-align: center;
}
</style>