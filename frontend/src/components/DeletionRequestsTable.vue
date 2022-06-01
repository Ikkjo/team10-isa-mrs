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
            requests: [
                {
                    id: "the",
                    firstName: "Cameron",
                    lastName: "Swaggers",
                    role: "FISHING_INSTRUCTOR",
                    deletionReason: "I hate this place. I hate this place. I hate this place."
                },
                {
                    id: "cum",
                    firstName: "Mihael",
                    lastName: "Jurluić",
                    role: "HOUSE_OWNER",
                    deletionReason: "I found a better alternative."
                },
                {
                    id: "bucket",
                    firstName: "Kašo",
                    lastName: "Čekićević",
                    role: "HOUSE_OWNER",
                    deletionReason: "Izgubio kuću na blekđeku :("
                },
                {
                    id: "yes.",
                    firstName: "Jovan",
                    lastName: "Stevičev",
                    role: "SHIP_OWNER",
                    deletionReason: "Slupao se u banderu. Ode brod"
                },
            ]
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