<template>
  <div id="info-item">
    <div class="wrapper">
        <div class="label-div">
            <span :class="'icon' + ' ' + iconClass ">{{icon}}</span>
            <div class="label">{{label}}</div>
        </div>
        <div class="edit noselect">{{buttonText}}</div>
    </div>
    <div class="text">{{text}}</div>
  </div>
</template>

<script>
export default {
    name: 'InfoItem',
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
        }
    },
}
</script>

<style>
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

</style>