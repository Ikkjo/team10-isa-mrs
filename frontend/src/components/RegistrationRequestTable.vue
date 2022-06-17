<template>
    <div class="wrapper">
        <h1>Registration Requests</h1>
        <Vuetable ref="vuetable"
            :api-mode="false"
            :fields="fields"
            :data="requests"
            >
            <div slot="accept" slot-scope="props">
                <button
                    class="btn accept"
                    @click="acceptRequest(props.rowData)"
                    >Accept
                </button>
            </div>
            <div slot="decline" slot-scope="props">
                <button
                    class="btn decline"
                    @click="openDeclineRequestModal(props.rowData.id)"
                    >Decline
                </button>
            </div>
        </Vuetable>
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
import { Vuetable } from 'vuetable-3'
import axios from 'axios'
import DeclineRegistrationRequestModal from './DeclineRegistrationRequestModal.vue'

export default {
    name: "RegistrationRequestList",
    components: {
        Vuetable,
        DeclineRegistrationRequestModal
    },
    data() {
        return {
            rrUUID: null,
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
                    }
                },
                {
                    name: "firstName",
                    title: "First Name"
                },
                {
                    name: "lastName",
                    title: "Last Name"
                },
                {
                    name: "email",
                    title: "Email"
                },
                
                {
                    name: "phoneNumber",
                    title: "Phone number"
                },
                {
                    name: "dateOfBirth",
                    title: "Birthdate",
                    formatter (value) {
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
                        return value + " (Age:<b>" + age+ "</b>)"
                    }
                },
                {
                    name: "addressDto.address",
                    title: "Address"
                },
                {
                    name: "addressDto.city",
                    title: "City"
                },
                {
                    name: "addressDto.country",
                    title: "Country"
                },
                {
                    name: "registrationReason",
                    title: "Registration Reason"
                },
                'accept',
                'decline'
            ],
            requests: null
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
        }
    },
    created () {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/registration-requests',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
        }).then((response) => {
            this.requests = response.data;
        }).catch(() => {
            alert('No connection.')
        });
    },
}
</script>

<style>
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

.vuetable-td-firstName , .vuetable-td-lastName{
    width:120px;
    max-width: 120px;
}
.vuetable-td-email {
    max-width:160px;
    width:160px;
}

.vuetable-td-dateOfBirth {
    max-width: 110px;
    width: 110px;
    text-align: center;
}

.vuetable-td-role {
    max-width: 70px;
    text-align: center;
}

.vuetable-td-phone {
    width: 140px;
    max-width: 140px;
}

.vuetable-td-address\.address {
    width: 130px;
    max-width: 130px;
}

.vuetable-td-address\.city, .vuetable-td-address\.country {
    width: 100px;
    max-width: 100px;
}
</style>