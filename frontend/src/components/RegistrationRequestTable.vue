<template>
    <div class="wrapper">
        <h1>Registration Requests</h1>
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRequests"
            :rows="requests"
            :columns="columns"
            :is-loading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange"
            >
            <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'decline'">
                    <button class="btn decline" @click="openDeclineRequestModal(props.row.id)">Decline</button>
                </span>
                <span v-else-if="props.column.field == 'accept'">
                    <button class="btn accept" @click="acceptRequest(props.row.id)">Accept</button>
                </span>
                <span v-else-if="props.column.field == 'role'">
                    <span v-if="props.row.role === 'SHIP_OWNER'" class='material-icons'>directions_boat</span>
                    <span v-else-if="props.row.role === 'HOUSE_OWNER'" class='material-icons'>house</span>
                    <span v-else class='material-icons'>phishing</span>
                </span>
                <span v-else-if="props.column.field == 'dateOfBirth'">
                    {{ props.row.dateOfBirth }} (Age:<b>{{birthDateFn(props.row.dateOfBirth)}}</b>)
                </span>
                <span v-else>
                    {{props.formattedRow[props.column.field]}}
                </span>
            </template>
        </VueGoodTable>
        <DeclineRegistrationRequestModal
            v-if="showModal"
            :show="showModal"
            :rrUUID="rrUUID"
            @close="showModal=false"
            @removeRequest="removeRequest"
            />
    </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import DeclineRegistrationRequestModal from './DeclineRegistrationRequestModal.vue';
import axios from 'axios';

export default {
    name: "RegistrationRequestList",
    components: {
        VueGoodTable,
        DeclineRegistrationRequestModal
    },
    data() {
        return {
            rrUUID: null,
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
                    label: "Phone Number",
                    field: "phoneNumber",
                    columnIndex: 4,
                },
                {
                    label: "Birth Date",
                    field: "dateOfBirth",
                    html: true,
                    columnIndex: 5,
                },
                {
                    label: "Address",
                    field: row => this.addressFn(row),
                    sortable: false,
                    columnIndex: 6,
                },
                {
                    label: "City",
                    field: row => this.cityFn(row),
                    sortable: false,
                    columnIndex: 7,
                },
                {
                    label: "Country",
                    field: row => this.countryFn(row),
                    sortable: false,
                    columnIndex: 8,
                },
                {
                    label: "Registration Reason",
                    field: 'registrationReason',
                    columnIndex: 9,
                },
                {
                    label: 'Accept',
                    field: 'accept',
                    sortable: false,
                    width: '120px',
                    columnIndex: 10,
                },
                {
                    label: 'Decline',
                    field: 'decline',
                    sortable: false,
                    width: '120px',
                    columnIndex: 11,
                }
            ],
            requests: null,
            totalRequests: 0,
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
                if (this.requests[i].id === this.rrUUID){
                    this.requests.splice(i, 1);
                    break;
                }
            }
        },
        acceptRequest(data) {
            this.rrUUID = data.id
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/registration-request/'+ this.rrUUID +'/accept',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            }).then(() => {
                this.removeRequest();
            }).catch((error) => {
                alert('No connection.')
                console.error(error)
            });
        },
        openDeclineRequestModal(id) {
            this.rrUUID = id;
            this.showModal = true;
        },
        roleFn(row) {
            if (row.role === "SHIP_OWNER")
                return "<span class='material-icons'>directions_boat</span>"
            else if (row.role === "HOUSE_OWNER")
                return "<span class='material-icons'>house</span>"
            else    // FISHING_INSTRUCTOR
                return "<span class='material-icons'>phishing</span>"
        },
        birthDateFn(value) {
            let dateParts = value.split(".");
            let date = new Date(
                Number(dateParts[2]),   // Year
                Number(dateParts[1])-1, // Month
                Number(dateParts[0])    // Day
                );
            let now = new Date();
            let current_year = now.getFullYear();
            let year_diff = current_year - date.getFullYear();
            let birthday_this_year = new Date(current_year, date.getMonth(), date.getDate());
            let has_had_birthday_this_year = (now >= birthday_this_year);

            let age = has_had_birthday_this_year
                ? year_diff
                : year_diff - 1;
            return age
        },
        addressFn(row) {
            return row.addressDto.address;
        },
        cityFn(row) {
            return row.addressDto.city;
        },
        countryFn(row) {
            return row.addressDto.country;
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/registration-requests',
                params: {page: this.serverParams.page, size: this.serverParams.perPage, sort: sortString},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRequests = response.data.totalPages * this.serverParams.perPage;
                this.requests = response.data.reservations
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/registration-requests',
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRequests = response.data.totalPages * this.serverParams.perPage;
                this.requests = response.data.reservations
            })
            .catch((error) => {
                alert("Couldn't fetch registration requests. See console for more info.")
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