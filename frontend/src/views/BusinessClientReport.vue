<template>
    <div class="report-page">
        <BusinessClientNavBar id="nav"/>
        <div class="wrapper">
            <div class="container">
                <h1>Write Report</h1>
                <div v-if="reservation" class="details">
                    <h3>Details</h3>
                    <div class="rentalEntity detail">
                        <span v-if="role==='HOUSE_OWNER'">
                            Vacation home:
                        </span>
                        <span v-if="role==='SHIP_OWNER'">
                            Ship:
                        </span>
                        <span v-if="role==='FISHING_INSTRUCTOR'">
                            Adventure:
                        </span>
                        <span>
                            <router-link :to="'/account/my-listings/'+reservation.rentalEntityId"> {{reservation.rentalEntityTitle}} </router-link>
                        </span>
                    </div>
                    <div class="client detail">
                        Client: <router-link :to="'/user/'+reservation.clientId">{{reservation.clientEmail}}</router-link>
                    </div>
                    <div class="dates detail">
                        Reservation time: <i>{{new Date(reservation.startDate).toDateString()}} → {{new Date(reservation.endDate).toDateString()}}</i>
                    </div>
                    <div class="status detail">
                        Status: <b>{{reservation.status}}</b>
                    </div>
                    <div class="price detail">
                        Price: ${{reservation.price}}
                    </div>
                </div>
                <div class="report">
                    <div class="btn-group">
                        <button class="btn arrived"
                            :class="{'not-selected': !arrived}"
                            @click="arrived=true"
                            >Arrived
                        </button>
                        <button class="btn not-arrived"
                            :class="{'not-selected': arrived}"
                            @click="arrived=false"
                            >Didn't Arrive
                        </button>
                    </div>
                    <transition name="fade">
                        <div class="message" v-if="arrived">
                            <label for="message">message:</label>
                            <textarea 
                                :disabled="!arrived"
                                v-model="message" 
                                name="message" 
                                id="message" 
                                cols="30" rows="4" 
                                @focus="inFocus('message')" 
                                @blur="outFocus('message')" 
                                :class="getClass('message')" 
                                :placeholder="getPlaceholder('message', 'Write something about the client...')"
                                />
                            <div class="alert-info" 
                                v-if="!isFocused('message') 
                                && !($v.message.minLength 
                                && $v.message.maxLength)"
                                >
                                Must be between 5 to 300 characters.
                            </div>
                            <div class="chkbx">
                                <input id="bad-behaviour" type="checkbox" class="checkbox" v-model="badBehaviour"/>
                                <label for="bad-behaviour"> Report user for bad behaviour</label>
                            </div>
                        </div>
                    </transition>
                </div>
                <div class="submit">
                    <button type="button" class="btn" @click="submitReport">Submit report</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from '../components/BusinessClientNavBar.vue'
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
import axios from 'axios'
export default {
    name: 'BusinessClientReport',
    components: {
        BusinessClientNavBar,
    },
    data() {
        return {
            arrived: true,
            role: window.localStorage.getItem('role'),
            reservation: null,
            infocus: {
                message: true,
            },
            message: '',
            badBehaviour: false,
        }
    },
    methods: {
        isFocused(field) {
            return this.infocus[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        getClass(field) {
            let cls = !this.isFocused(field) 
                && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) 
                && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        },
        submitReport() {
            if (this.arrived) {
                let reviewDto = {
                    message: this.message,
                    penalize: this.badBehaviour
                };
                axios({
                    method: 'post',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/reviews/'+this.$route.params.id,
                    data: reviewDto,
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                })
                .then(() => {
                    this.$router.push({name: 'business-client-reservations'});
                })
                .catch((error) => {
                    alert("Something went wrong. See console for output.")
                    console.log(error);
                })
            }
            else {
                axios({
                    method: 'put',
                    url: process.env.VUE_APP_BASE_URL+'/api/v1/reviews/'+this.$route.params.id+'/didnt-arrive',
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                    },
                })
                .then(() => {
                    this.$router.push({name: 'business-client-reservations'});
                })
                .catch((error) => {
                    alert("Something went wrong. See console for output.")
                    console.log(error);
                })
            }
        }
    },
    validations: {
        message: {
            required,
            minLength: minLength(5),
            maxLength: maxLength(300),
        }
    },
    created () {
        axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/reservations/'+this.$route.params.id,
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
            .then((response) => {
                console.log(response)
                this.reservation = response.data;
            })
            .catch((error) => {
                alert("Something went wrong. See console for output.")
                console.log(error);
            }) 
    },
}
</script>

<style>
.report-page {
    max-width: 100%;
}

.wrapper {
    width: 100%;
    margin-top:70px;
    display: flex;
    justify-content: center;
}

.container {
    width: 800px;
}

.details {
    margin-top: 20px;
}

.rentalEntity {
    margin-top: 10px;
}

.detail {
    margin-bottom: 5px;
}

.report {
    margin-top: 20px;
}
.btn-group {
    margin-bottom: 20px;
}
.not-selected {
    background-color: lightgray;
}

.btn-group * {
    width: 130px;
}
.arrived {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
}
.not-arrived {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

.message {
    transition: 100ms;
}

textarea {
    width: 100%;
}

.chkbx {
    margin-top:20px;
}

.submit {
    margin-top: 20px;
}

</style>