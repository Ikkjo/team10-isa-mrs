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
            registrationRequestUUID: null,
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
                    name: "phone",
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
                    name: "address.address",
                    title: "Address"
                },
                {
                    name: "address.city",
                    title: "City"
                },
                {
                    name: "address.country",
                    title: "Country"
                },
                {
                    name: "registrationReason",
                    title: "Registration Reason"
                },
                'accept',
                'decline'
            ],
            requests: [
                {
                    id: "the",
                    firstName: "Aleksandra",
                    lastName: "Sakal Franciskovic",
                    email: "iamnikoladamjanovic@gmail.com",
                    password: "HiHiHiHa",
                    role: "HOUSE_OWNER",
                    phone: "+3813284239",
                    dateOfBirth: "26.4.1985.",
                    address: {
                        address: "Hadzi ruimova 399",
                        city: "Sremska Mitrovica",
                        country: "Serbia"  
                    },
                    registrationReason: "Imam lepu vikendicu koja nicemu ne sluzi pa reko da je stavim na izdavanje."
                },
                {
                    id: "cum",
                    firstName: "Slavija",
                    lastName: "Lorđić",
                    email: "slavko@gmail.com",
                    password: "HiHiHiHa",
                    role: "SHIP_OWNER",
                    phone: "+3813542333",
                    dateOfBirth: "22.5.1987.",
                    address: {
                        address: "Slavija 49",
                        city: "Beograd",
                        country: "Serbia"  
                    },
                    registrationReason: "Volim da pecam hehehehhe. Ae prihvati me da ti platim 500e. The function got HENNESSY"
                },
                {
                    id: "bucket",
                    firstName: "Ilija",
                    lastName: "Kalinic",
                    email: "ikkjo@gmail.com",
                    password: "HiHiHiHa",
                    role: "FISHING_INSTRUCTOR",
                    phone: "+38654325435",
                    dateOfBirth: "22.22.2000.",
                    address: {
                        address: "Somborski put 33",
                        city: "Subotica",
                        country: "Serbia"  
                    },
                    registrationReason: "Pecanje ovo ono, pecanje ovo ono, pecanje ovo ono."
                },
                {
                    id: "from",
                    firstName: "Ana",
                    lastName: "Kličko",
                    email: "anaklicko@gmail.com",
                    password: "HiHiHiHa",
                    role: "HOUSE_OWNER",
                    phone: "+3865430900",
                    dateOfBirth: "21.2.2000.",
                    address: {
                        address: "Apatinski put 12",
                        city: "Sombor",
                        country: "Serbia"  
                    },
                    registrationReason: "Kuca je velika. Ima puno mesta puno soba, dodjite da izdajem"
                },
                {
                    id: "spongebob",
                    firstName: "Đorđe",
                    lastName: "Lođe",
                    email: "lordje@gmail.com",
                    password: "HiHiHiHa",
                    role: "FISHING_INSTRUCTOR",
                    phone: "+3863192894",
                    dateOfBirth: "21.4.1979.",
                    address: {
                        address: "Prazilučka 549",
                        city: "Novi Sad",
                        country: "Serbia"  
                    },
                    registrationReason: "Najmanji pecaros u Vojvodini i šire. Jeftino pružam svoje usluge. Molim vas prihvatite da mi deca ne kisnu"
                },
            ]
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
            }).catch(() => {
                alert('No connection.')
            });
        },
        openDeclineRequestModal(id) {
            this.rrUUID = id;
            this.showModal = true;
        }
    },
}
</script>

<style>
table {
    font-family: arial, sans-serif;
    width: 100%;
    border-collapse: collapse;
    margin: 25px 0;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

tbody tr {
    border-bottom: 1px solid #dddddd;
}

tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

tbody tr:last-of-type {
    border-bottom: 2px solid var(--orange-primary, orange);
}

th, td {
    padding: 12px 15px;
}

td {
    border-right: 1px solid lightgray;
    padding: 6px 10px;
    word-wrap: break-all;
    overflow-wrap:break-word;
}

thead tr {
    background-color: var(--orange-primary, orange);
    color: white;
    text-align: left;
}

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