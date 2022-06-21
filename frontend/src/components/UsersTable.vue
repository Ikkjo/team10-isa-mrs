<template>
    <div class="wrapper">
        <h1>All Users</h1>
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRecords"
            :rows="users"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"
            >
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'deleted'">
                    <div v-if="props.row.role !== 'MAIN_ADMIN'
                        || ((props.row.role === 'ADMIN'
                        || props.row.role ==='UNVERIFIED_ADMIN')
                        && isMainAdmin)">
                        <button v-if="props.row.deleted" class="btn" @click="toggleDeletedStatus(props.row)">Enable</button>
                        <button v-else class="btn disable" @click="toggleDeletedStatus(props.row)">Disable</button>
                    </div>
                </span>
                <span v-else-if="props.column.field == 'role'">
                    <span v-if="props.row.role === 'SHIP_OWNER'" class='material-icons'>directions_boat</span>
                    <span v-else-if="props.row.role === 'HOUSE_OWNER'" class='material-icons'>house</span>
                    <span v-else-if="props.row.role === 'FISHING_INSTRUCTOR'" class='material-icons'>phishing</span>
                    <span v-else-if="props.row.role === 'CLIENT'" class='material-icons'>person</span>
                    <span v-else-if="props.row.role === 'MAIN_ADMIN'" class='material-icons'>supervisor_account</span>
                    <!-- Admin and unsupervized admin -->
                    <span v-else class='material-icons'>supervised_user_circle</span> 
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
                    label: 'Type',
                    field: 'role',
                    html: true,
                    sortable: true,
                    columnIndex: 0,
                    tdClass: 'td-role',
                },
                {
                    label: 'First Name',
                    field: 'firstName',
                    sortable: true,
                    columnIndex: 1,
                },
                {
                    label: 'Last Name',
                    field: 'lastName',
                    sortable: true,
                    columnIndex: 2,
                },
                {
                    label: 'Email',
                    field: 'email',
                    sortable: true,
                    columnIndex: 3,
                },
                {
                    label: 'Phone Number',
                    field: 'phoneNumber',
                    sortable: true,
                    columnIndex: 4,
                },
                {
                    label: 'Enable/Disable',
                    field: 'deleted',
                    sortable: true,
                    width: '150px',
                    columnIndex: 5,
                }
            ],
            users: null,
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
            role: null,
        }
    },
    computed: {
        isMainAdmin() {
            return this.role !== null && this.role === 'MAIN_ADMIN' 
        }
    },
    methods: {
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/users',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalPages * this.serverParams.perPage;
                this.users = response.data.users
            })
            .catch((error) => {
                alert("Couldn't fetch registration requests. See console for more info.")
                console.log(error);
            }) 
        },
        toggleDeletedStatus(user) {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/users/'+user.id+'/toggle-deleted',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then(() => {
                user.deleted = !user.deleted;
            })
            .catch((error) => {
                alert("Couldn't fetch registration requests. See console for more info.")
                console.log(error);
            }) 
        },
    },
    created () {
        this.role = window.localStorage.getItem('role');
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/users',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                console.log(response)
                this.totalRecords = response.data.totalPages * this.serverParams.perPage;
                this.users = response.data.users
            })
            .catch((error) => {
                alert("Couldn't fetch users. See console for more info.")
                console.log(error);
            })
    },
}
</script>

<style >
.td-role {
    text-align: center;
}

.btn.disable {
    background-color: red;
}

.btn.disable:hover {
    background-color: darkred;
}
</style>