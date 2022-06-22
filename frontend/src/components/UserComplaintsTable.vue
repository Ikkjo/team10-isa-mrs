<template>
    <div class="wrapper">
        <h1>Account Deletion Requests</h1>
        <VueGoodTable
            :rows="clientReports"
            :columns="columns"
            >
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'decide'">
                    <button class="btn" @click="openClientReportDecisionModal(props.row.id)">Decide</button>
                </span>
                <span v-else>
                    {{props.formattedRow[props.column.field]}}
                </span>
            </template>
        </VueGoodTable>
        <UserComplaintsDecisionModal
            v-if="showModal"
            :show="showModal"
            :crUUID="crUUID"
            @close="showModal=false"
            @removeRequest="removeRequest"
            />
    </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import UserComplaintsDecisionModal from './UserComplaintsDecisionModal.vue'
import axios from 'axios'

export default {
    name: 'UserComplaintsTable',
    components: {
        VueGoodTable,
        UserComplaintsDecisionModal
    },
    data() {
        return {
            crUUID: null,
            showModal: false,
            columns: [
                {
                    label: 'Client Email',
                    field: 'clientEmail',
                    columnIndex: 0,
                },
                {
                    label: 'Rental Entity Title',
                    field: 'rentalEntityTitle',
                    columnIndex: 2,
                },
                {
                    label: 'Business Client Email',
                    field: 'businessClientEmail',
                    columnIndex: 3,
                },
                {
                    label: "Complaint",
                    field: 'complaint',
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
            clientReports: null,
        }
    },
    methods: {
        removeRequest() {
            for (let i = 0; i < this.clientReports.length; i++) {
                console.log(this.clientReports[i].id)
                if (this.clientReports[i].id === this.crUUID){
                    this.clientReports.splice(i, 1);
                    break;
                }
            }
        },
        openDeletionDecisionModal(id) {
            this.crUUID = id;
            this.showModal = true;
        },
    },
    created () {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/client-reports',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        }).then((response) => {
            this.clientReports = response.data;
        }).catch((error) => {
            console.log(error);
        });
    },
}
</script>

<style>
</style>