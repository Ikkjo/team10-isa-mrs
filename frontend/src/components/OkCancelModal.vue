<script>
export default {
    name: 'OkCancelModal',
    props: {
        show: {
            type: Boolean,
            default: false,
        },
    },
}
</script>

<template>
    <transition name="modal">
        <div v-if="show" class="modal-mask">
            <div class="modal-wrapper">
                <div class="modal-container">
                    <div class="header">
                        <h3>Remove Listing</h3>
                        <div @click="$emit('close')"><span class="material-icons close">close</span></div>
                    </div>

                    <div class="modal-body">
                        <div>
                            <button
                                class="btn btn-red"
                                @click="$emit('ok')">Remove</button>
                            <button
                                class="btn cancel"
                                @click="$emit('close')">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

<style scoped>
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
    max-width: 300px;
    margin: 0px auto;
    padding: 0px 10px !important;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
    position: relative;
}

.modal-body {
    height: 90px;
    display: flex;
    justify-content: center;
}

.modal-body > * {
    margin-top: 5px;
}

.header {
    position: relative;
    padding: 10px;
}

.close {
    color: var(--orange-primary);
    cursor: pointer;
    font-size: 2rem;
    position: absolute;
    right: 0;
    top: 5px;
}

.btn {
    width: 100px;
}

.cancel {
    margin-left: 10px;
    background-color: lightgray;
}

.cancel:hover {
    background-color: gray;
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
