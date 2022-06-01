<template>
  <div id="info-item">
    <div class="wrapper">
        <div class="label-div">
            <span :class="'icon' + ' ' + iconClass ">{{icon}}</span>
            <div class="label">{{label}}</div>
        </div>
        <div v-show="!showSlot" @click="editClicked" class="edit noselect">{{buttonText}}</div>
        <div v-if="useSlot" v-show="showSlot" @click="cancelClicked" class="edit cancel noselect">Cancel</div>
    </div>
    <slot v-if="!showSlot" name="main"/>
    <div v-show="!showSlot" class="text">{{text}}</div>
    <div v-if="useSlot" v-show="showSlot" class="edit-slot">
        <slot name="edit"/>
        <button :disabled="saveDisabled" @click="save" class="btn" :class="{'btn-red': editButton==='Request Deletion', 'btn-disabled': saveDisabled}">{{editButton}}</button>
    </div>
  </div>
</template>

<script>
export default {
    name: 'InfoItem',
    data() {
        return {
            showSlot: false,
        }
    },
    props: {
        iconClass: {
            type: String,
            default: "material-icons"
        },
        icon: {
            type: String,
            default: "disabled_by_default",
        },
        label: {
            type: String,
            default: "",
        },
        text: {
            type: String,
            default: "",
        },
        buttonText: {
            type: String,
            default: "Edit"
        },
        useSlot: {
            type: Boolean,
            default: true,
        },
        saveDisabled: {
            type: Boolean,
        },
        editButton: {
            type: String,
            default: "Save"
        },
    },
    methods: {
        editClicked() {
            if (this.useSlot)
                this.showSlot = !this.showSlot
                this.$emit('editClicked')
        },
        cancelClicked() {
            this.showSlot = !this.showSlot
                this.$emit('cancelClicked')
        },
        save() {
            console.log("saved")
            this.showSlot = !this.showSlot
            this.$emit("save")
        }
    }
}
</script>

<style scoped>
#info-item {
    min-width: 300px;
    width: 100%;
}

.label-div {
    display: flex;
    align-items: center;
    color: var(--black) !important;
}

.icon {
    margin-right: 5px;
}

.text {
    margin-top: 5px;
    padding-left: 29px;
    font-size: 0.9rem;
    color: grey !important;
    display: inline-block;
}

.wrapper {
    position: relative;
}

.edit {
    display: inline-block;
    position: absolute;
    right: 0;
    top: 0;
    margin-top: 3px;
    text-decoration: underline;
    cursor: pointer;
}

.edit:hover {
    color: var(--orange-primary);
}

.edit:active {
    color: var(--orange-secondary);
}

.cancel {
    color: red;
}

.cancel:active, .cancel:hover {
    color: darkred;
}

.edit-slot {
    padding: 0px 5px;
}

.btn {
    margin-top: 15px;
}

</style>