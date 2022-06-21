<template>
    <div class="wrapper">
        <h1>Account Deletion Requests</h1>
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRecords"
            :rows="requests"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"
            >
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'decide'">
                    <button class="btn" @click="openDeletionDecisionModal(props.row)">Decide</button>
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
        <DeletionDecisionModal
            v-if="showModal"
            :show="showModal"
            :drUUID="drUUID"
            @close="showModal=false"
            @removeRequest="removeRequest"
            />
    </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import DeletionDecisionModal from './DeletionDecisionModal.vue'
import axios from 'axios'

export default {
    components: {
        VueGoodTable,
        DeletionDecisionModal
    },
    data() {
        return {
            drUUID: null,
            showModal: false,
            columns: [
                {
                    label: 'Type',
                    field: 'role',
                    html: true,
                    columnIndex: 0,
                    tdClass: 'td-role',
                },
                {
                    label: 'First Name',
                    field: 'firstName',
                    columnIndex: 1,
                },
                {
                    label: 'Last Name',
                    field: 'lastName',
                    columnIndex: 2,
                },
                {
                    label: 'Email',
                    field: 'email',
                    columnIndex: 3,
                },
                {
                    label: "Deletion Reason",
                    field: 'deletionReason',
                    columnIndex: 4,
                },
                {
                    label: 'Decide',
                    field: 'decide',
                    sortable: false,
                    width: '120px',
                    columnIndex: 5,
                }
            ],
            requests: null,
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
        removeRequest() {
            for (let i = 0; i < this.requests.length; i++) {
                console.log(this.requests[i].id)
                if (this.requests[i].id === this.drUUID){
                    this.requests.splice(i, 1);
                    break;
                }
            }
        },
        openDeletionDecisionModal(data) {
            this.drUUID = data.id;
            this.showModal = true;
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
            axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/deletion-requests',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalPages * this.serverParams.perPage;
                this.requests = response.data.deletionRequests
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
            url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/deletion-requests',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        }).then((response) => {
            this.totalRecords = response.data.totalPages * this.serverParams.perPage;
            this.requests = response.data.deletionRequests;
        }).catch((error) => {
            console.log(error);
        });
    },
}
</script>

<style>
.td-role {
    text-align: center;
}
</style>