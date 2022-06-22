<template>
<div>
    <ClientNavBar id="nav"/>
    <div class="btn-group-calendar">
        <button class="btn calendar-view"
            :class="{'not-selected': !calendarView}"
            @click="calendarView=true"
            >Calendar view
        </button>
        <button class="btn table-view"
            :class="{'not-selected': calendarView}"
            @click="calendarView=false"
            >Table view
        </button>
    </div>
    <div v-if="!calendarView" class="reservation-table-container">
        <VueGoodTable
            mode="remote"
            :pagination-options="{
                enabled: true,
            }"
            :total-rows="totalRecords"
            :rows="rows"
            :columns="columns"
            :isLoading.sync="isLoading"
            @on-page-change="onPageChange"
            @on-sort-change="onSortChange"
            @on-column-filter="onColumnFilter"
            @on-per-page-change="onPerPageChange">
             <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field == 'cancel'">
                    <button class="btn" @click="cancelReservation(props.row.id)">Cancel</button>
                </span>
                <span v-else-if="props.column.field == 'ownerEmail'">
                    <router-link :to="'/user/'+props.row.ownerId">{{props.row.clientEmail}}</router-link>
                </span>
                <span v-else-if="props.column.field == 'rentalEntityTitle'">
                    <router-link :to="'/account/my-listings/'+props.row.rentalEntityId">{{props.row.rentalEntityTitle}}</router-link>
                </span>
                <span v-else>
                    {{props.formattedRow[props.column.field]}}
                </span>
            </template>
        </VueGoodTable>
    </div>
    <div v-else class="calendar-wrapper">
        <div class="calendar-container">
            <FullCalendar
            class='calendar'
            :options='calendarOptions'
            />
        </div>
    </div>
</div>
</template>

<script>
import ClientNavBar from '@/components/ClientNavBar.vue'
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table';
import axios from 'axios'
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'

export default {
    name: 'ClientReservations',
    components: {
        ClientNavBar,
        VueGoodTable,
        FullCalendar
    },
    data() {
        return {
            calendarView: true,
            isLoading: false,
            columns: [
                {
                    label: 'Owner',
                    field: 'ownerEmail',
                    columnIndex: 0,
                },
                {
                    label: 'Listing',
                    field: 'rentalEntityTitle',
                    columnIndex: 1,
                },
                {
                    label: 'Start Date',
                    field: 'startDate',
                    type: 'date',
                    formatFn: this.formatDate,
                    width: '160px',
                },
                {
                    label: 'End Date',
                    field: 'endDate',
                    formatFn: this.formatDate,
                    width: '160px',
                },
                {
                    label: 'Price',
                    field: 'price',
                    formatFn: this.formatPrice,
                    width: '100px',
                },
                {
                    label: 'Status',
                    field: 'status'
                },
                {
                    label: 'Cancel',
                    field: 'cancel',
                    sortable: false,
                    width: '120px',
                }
            ],
            rows: [],
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
            calendarOptions: {
                plugins: [
                    dayGridPlugin,
                ],
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth'
                },
                events: [],
            }
        }
    },
    methods: {
        formatDate(value) {
            return new Date(value).toDateString();
        },
        formatPrice(value) {
            return '$' + value;
        },
        updateParams(newProps) {
            this.serverParams = Object.assign({}, this.serverParams, newProps);
        },
        onPageChange(params) {
            this.updateParams({page: params.currentPage-1});
            this.loadItems();
        },
        onPerPageChange(params) {
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
            // let sortString = ''+this.serverParams.sort[0].field+','+this.serverParams.sort[0].type
            axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reservations',
                params: {page: this.serverParams.page, size: this.serverParams.perPage},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalItems;
                this.rows = response.data.reservations
                this.rows.forEach(this.convertReservationToEvent)
                console.log(response)
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
        },
        convertReservationToEvent(reservation) {
            let event = {
                id: reservation.id,
                title: reservation.rentalEntityTitle+" owned by "+reservation.businessClient,
                start: new Date(reservation.startDate).toISOString().replace(/T.*$/, ''),
                end: new Date(reservation.endDate+86400000).toISOString().replace(/T.*$/, ''),
                color: 'orange',
            }
            this.calendarOptions.events.push(event);
        },
        writeReview(id) {
            this.$router.push({name: 'reservation-report', params: {id: id}});
        }
    },
    mounted() {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reservations',
                params: {page: this.serverParams.page, size: this.serverParams.perPage},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                this.totalRecords = response.data.totalItems;
                this.rows = response.data.reservations
                this.rows.forEach(this.convertReservationToEvent)
                console.log(response.data.reservations)
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
    },
    computed: {
        canCancel(startDate) {
            this.props
            let threeFromToday = new Date()
            threeFromToday.setDate(threeFromToday.getDate + 3)

            return threeFromToday < new Date(startDate)
        }
    }
}
</script>

<style>

.reservation-table-container {
    max-width: 100%;
    margin-top: 15px;
    padding: 0px 5%;
}

.filter {
    padding: 3px 7px; 
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
    background-color: #fff;
    font-family: inherit;
}

.filter {
   height: 48px;
}

.filter select:hover,
.filter input:hover,
.filter input:active {
    border-color: var(--orange-primary, #f0a500);
}

.filter select:focus,
.filter input:focus {
    outline-color: var(--orange-primary, #f0a500);
}

.btn-group-calendar {
    margin-top: 70px;
    margin-left: 5%;
}

.btn-group-calendar * {
    width: 150px;
}

.calendar-view {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
}
.table-view {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}

.calendar-wrapper {
    width: 100%;
    margin-top: 15px;
    display: flex;
    justify-content: center;
}
.calendar-container {
    width: 50%;
}
.not-selected {
    background-color: lightgray;
}

.fc-button {
    display: inline-block !important;
    border: none !important;
    border-radius: 5px !important;
    outline: none !important;
    color: var(--white, #fff) !important;
    background-color: var(--orange-primary, orange) !important;
    cursor: pointer !important;
    font-family: inherit !important;
    text-decoration: none !important;
    /* transition: 0.5s !important; */
}
.fc-button:hover {
    background-color: var(--orange-secondary, orange);
}
.fc-button:active {
  transform: scale(0.98);
}
a.fc-daygrid-day-number {
    color: black !important;
}

/* I HAVE NO IDEA WHY THIS WORKS ON CANCELING THE BORDER OF FULLCALENDAR BUTTONS BUT IT WORKS */
.fc .fc-button-primary:not(:disabled):active, .fc .fc-button-primary:not(:disabled).fc-button-active { box-shadow: none !important; }
.fc.fc-button-primary:before, .fc.fc-button-primary:after { box-shadow: none !important; }
.fc .fc-button:not(:disabled) { box-shadow: none !important; }
</style>