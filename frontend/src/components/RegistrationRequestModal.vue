<template>
    <transition name="modal">
        <div v-if="show" class="modal-mask">
            <div class="modal-wrapper" @click.self="$emit('close')">
                <div class="modal-container">
                    <div class="modal-header">
                        <h2>Registration Request</h2>
                        <button
                        @click="$emit('close')"
                        >Close</button>
                    </div>
                    <div class="modal-body">
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="firstName">First Name</label>
                                <div class="requestee-value">
                                    <p name="firstName">{{rr.firstName}}</p>
                                </div>
                            </div>
                            <div class="requestee-field">
                                <label for="firstName">Last Name</label>
                                <div class="requestee-value">
                                    <p name="firstName">{{rr.lastName}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="email">Email</label>
                                <div class="requestee-value">
                                    <p name="email">{{rr.email}}</p>
                                </div>
                            </div>                       
                        </div>
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="phone">Phone Number</label>
                                <div class="requestee-value">
                                    <p name="phone">{{rr.phone}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="address">Address</label>
                                <div class="requestee-value">
                                    <p name="address">{{rr.address}}</p>
                                </div>
                            </div>
                            <div class="requestee-field">
                                <label for="city">City</label>
                                <div class="requestee-value">
                                    <p name="city">{{rr.city}}</p>
                                </div>
                            </div>
                            <div class="requestee-field">
                                <label for="country">Country</label>
                                <div class="requestee-value">
                                    <p name="country">{{rr.country}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="dateOfBirth">Date of Birth</label>
                                <div class="requestee-value">
                                    <p name="dateOfBirth">{{dateOfBirth}}</p>
                                </div>
                            </div>
                            <div class="requestee-field">
                                <label for="role">Account Type</label>
                                <div class="requestee-value">
                                    <p name="role">{{rr.role}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-body-wrapper">
                            <div class="requestee-field">
                                <label for="registrationReason">Reason for Registration</label>
                                <div class="requestee-value">
                                    <p name="registrationReason">{{rr.registrationReason}}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                        class="btn decline"
                        @click="declineRequest"
                        >Decline</button>
                        <button
                        class="btn accept"
                        @click="acceptRequest"
                        >Accept</button>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
export default {
    name: "RegistrationRequest",
    props: {
        rr: Object,
        show: Boolean,
    },
    data() {
        return {
            dateOfBirth: null,
        }
    },
    methods: {
        acceptRequest() {
            // send axios request to accept registration
            // emit if no errors else show error
            this.$emit('close')
        },
        declineRequest() {
            // send axios request to decline registration
            // emit if no errors else show error
            this.$emit('close');
        },
        calculateAgeInYears (date) {
            var now = new Date();
            var current_year = now.getFullYear();
            var year_diff = current_year - date.getFullYear();
            var birthday_this_year = new Date(current_year, date.getMonth(), date.getDate());
            var has_had_birthday_this_year = (now >= birthday_this_year);

            return has_had_birthday_this_year
                ? year_diff
                : year_diff - 1;
        },
        preventClick(event) {
            event.stopPropagation();
        }
    },
    created () {
        let dateParts = this.rr.dateOfBirth.split(".");
        let dateOfBirth = new Date(
            Number(dateParts[2]),   // Year
            Number(dateParts[1])-1, // Month
            Number(dateParts[0])    // Day
            );
        let age = this.calculateAgeInYears(dateOfBirth);
        this.dateOfBirth = this.rr.dateOfBirth + "  (Age:" + age+ ")" 
    },
}
</script>

<style>
.modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
}

.modal-wrapper {
    display: table-cell;
    vertical-align: middle;
}

.modal-container {
    width: 50%;
    margin: 0px auto;
    padding: 2%;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
}

.modal-header {
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
}

.modal-body {
    width: 100%;
    margin-top: 20px;
    margin-bottom: 30px; 
}

.modal-body-wrapper {
    width: 100%;
    display: flex;
    flex-wrap: nowrap;
    margin-bottom: 10px;
}
.modal-body-wrapper :last-child {
    margin-right: 0;
}

.requestee-field {
    width: 100%;
    margin-right: 10px;
}

.requestee-field .requestee-value {
    border: 1px solid black;
    border-radius: 5px;
    padding: 5px;
}

.modal-footer {
    display: flex;
    justify-content: space-evenly;
    gap:10px;
}

.modal-footer .btn {
    width: 40%;
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

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from {
    opacity: 0;
}

.modal-leave-to {
    opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
}
</style>