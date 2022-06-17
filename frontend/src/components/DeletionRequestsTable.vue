<template>
    <div class="wrapper">
        <h1>Account Deletion Requests</h1>
        <Vuetable ref="vuetable"
            :api-mode="false"
            :fields="fields"
            :data="requests"
            >
            <div slot="decide" slot-scope="props">
                <button
                    class="btn"
                    @click="openDecisionModal(props.rowData)"
                    >Decide
                </button>
            </div>
        </Vuetable>
        <DecisionModal
            v-if="showModal"
            :show="showModal"
            :drUUID="drUUID"
            @close="showModal=false"
            @removeRequest="removeRequest"
            />
    </div>
</template>

<script>
import Vuetable from 'vuetable-3'
import DecisionModal from './DecisionModal.vue'
import axios from 'axios'

export default {
    components: {
        Vuetable,
        DecisionModal
    },
    data() {
        return {
            drUUID: null,
            showModal: false,
            fields: [
                {
                    name: "role",
                    title: "Type",
                    formatter (value) {
                        if (value === "SHIP_OWNER")
                            return "<span class='material-icons'>directions_boat</span>"
                        else if (value === "HOUSE_OWNER")
                            return "<span class='material-icons'>house</span>"
                        else    // FISHING_INSTRUCTOR
                            return "<span class='material-icons'>phishing</span>"
                    },
                    width: "70px"
                },
                {
                    name: "firstName",
                    title: "First Name",
                    width: "200px"
                },
                {
                    name: "lastName",
                    title: "Last Name",
                    width: "200px"
                },
                {
                    name: "deletionReason",
                    title: "Deletion Reason"
                },
                {
                    name: "decide",
                    title: "Decide",
                    width: "110px",
                }
            ],
            requests: null
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
        openDecisionModal(data) {
            this.drUUID = data.id;
            this.showModal = true;
        }
    },
    created () {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/deletion-requests',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        }).then((response) => {
            this.requests = response.data;
        }).catch((error) => {
            console.log(error);
        });
    },
}
</script>

<style>
.vuetable-td-role {
    text-align: center;
}
.vuetable-td-firstName , .vuetable-td-lastName{
    width:120px;
    max-width: 120px;
}
</style>