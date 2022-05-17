<template>
    <li class="nav-item" @click="$emit('click')">
        <router-link :to="link" class="nav-link">
            {{text}} <span :class="'icon-button' + ' ' + iconClass ">{{icon}}</span>
        </router-link>
        <slot name="menu"/>
    </li>
</template>

<script>
export default {
    name: 'NavItem',
    props: {
        iconClass: {
            type: String,
            default: "material-icons"
        },
        icon: {
            type: String,
            default: "disabled_by_default",
        },
        link: {
            type: String,
            default: "",
        },
        text: {
            type: String,
            default: "",
        },
    },
}
</script>

<style>
.nav-item {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 5px;
}

.nav-link {
    padding: 10px;
    border-radius: 5px;
    color: var(--black);
    display: flex;
    align-items: center;
}

.nav-link:hover {
    background-color: rgb(236, 236, 236);
    color: var(--black);
}

</style>