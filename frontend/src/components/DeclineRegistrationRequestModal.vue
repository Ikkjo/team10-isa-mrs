<template>
    <portal to="body">
        <transition name="modal">
            <div class="modal-mask">
                <div class="modal-wrapper" @click.self="$emit('close')">
                    <div class="modal-container">
                        <div class="modal-header">
                            <h2>Decline Reason</h2>
                            <div @click="$emit('close')"><span class="material-icons close">close</span></div>
                        </div>
                        <div class="modal-body">
                            <div class="form-control">
                                <textarea name="description" 
                                    id="description" cols="30" rows="6"
                                    v-model="declineReason" 
                                    @focus="inFocus('declineReason')" 
                                    @blur="outFocus('declineReason')" 
                                    :class="getClass('declineReason')" 
                                    :placeholder="getPlaceholder('declineReason', 'Tell the client how to improve their registration request.')"/>
                                <div class="alert-info" 
                                    v-if="!isFocused('declineReason')
                                    && !($v.declineReason.minLength 
                                    && $v.declineReason.maxLength)"
                                    >
                                    Enter a decline reason.
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button
                            class="btn"
                            @click="declineRequest"
                            :disabled="$v.$invalid"
                            >Send Decline Reason</button>
                        </div>
                    </div>
                </div>
            </div>
        </transition>
    </portal>
</template>

<script>
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
import axios from 'axios'

export default {
    props: {
        rrUUID: String,
    },
    data() {
        return {
            declineReason: '',
            infocus: {
                declineReason: true,
            }
        }
    },
    methods: {
        declineRequest() {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/registration-request/'+ this.rrUUID +'/accept',
                data: {declineReason: this.declineReason},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            })
        },
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
            let cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },
    validations: {
        declineReason: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(200)
        }
    }
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

textarea {
    width: 100%;
}

.close {
    color: var(--orange-primary);
    cursor: pointer;
    font-size: 2rem;
}
</style>