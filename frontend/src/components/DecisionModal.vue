<template>
    <portal to="body">
        <transition name="modal">
            <div class="modal-mask">
                <div class="modal-wrapper" @click.self="$emit('close')">
                    <div class="modal-container">
                        <div class="modal-header">
                            <h2>Decide Deletion</h2>
                            <div @click="$emit('close')"><span class="material-icons close">close</span></div>
                        </div>
                        <div class="modal-body">
                            <div class="form-control">
                                <textarea name="description" 
                                    id="description" cols="30" rows="6"
                                    v-model="response" 
                                    @focus="inFocus('response')" 
                                    @blur="outFocus('response')" 
                                    :class="getClass('response')" 
                                    :placeholder="getPlaceholder('response', 'Answer the deletion request.')"/>
                                <div class="alert-info" 
                                    v-if="!isFocused('response')
                                    && !($v.response.minLength 
                                    && $v.response.maxLength)"
                                    >
                                    Enter a deletion response.
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button
                                class="btn decline"
                                @click="declineRequest"
                                :disabled="$v.$invalid"
                                >Decline</button>
                            <button
                                class="btn accept"
                                @click="acceptRequest"
                                :disabled="$v.$invalid"
                                >Accept</button>
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
        drUUID: String,
    },
    data() {
        return {
            response: '',
            infocus: {
                response: true,
            }
        }
    },
    methods: {
        acceptRequest() {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/deletion-request/'+ this.drUUID +'/accept',
                data: {response: this.response},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            }).then(() => {
                this.$emit('close');
                this.$emit('removeRequest');
            }).catch(() => {
                alert('No connection.')
            });
        },
        declineRequst() {
            axios({
                method: 'put',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/deletion-request/'+ this.drUUID +'/decline',
                data: {response: this.response},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
            }).then(() => {
                this.$emit('close');
                this.$emit('removeRequest');
            }).catch(() => {
                alert('No connection.')
            });
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
        response: {
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