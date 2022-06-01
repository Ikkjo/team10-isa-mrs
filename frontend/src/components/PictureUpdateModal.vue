<script>
export default {
    name: 'PictureUpdateModal',
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
                    <div class="modal-header">
                        <slot name="header">
                            <div @click="$emit('close')"><span class="material-icons close">close</span></div>
                        </slot>
                    </div>

                    <div class="modal-body">
                        <slot name="body"></slot>
                    </div>

                    <div class="modal-footer">
                        <slot name="footer">
                            <div>
                                <button
                            class="btn modal-default-button"
                            @click="$emit('save')"
                            >Save</button>
                            </div>
                        </slot>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

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
    max-width: 600px;
    margin: 0px auto;
    padding: 50px 10px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
    position: relative;
}

.modal-header h3 {
    margin-top: 0;
    color: #42b983;
}

.modal-body {
    margin: 20px 0;
    overflow: auto;
    max-height: 80vh;
}


.modal-default-button {
    position: absolute;
    right: 10px;
    bottom: 10px;
}


.close {
    color: var(--orange-primary);
    cursor: pointer;
    font-size: 2rem;
    position: absolute;
    right: 10px;
    top: 10px;
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
