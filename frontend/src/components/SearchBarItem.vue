<template>
    <router-link
                :to="{name: link}"
                class="filter-button">
                <span class="icon-class" :class="iconClass">{{icon}}</span>
                <span class="text">{{title}}</span>
    </router-link>
</template>

<script>
export default {
    name: 'SearchBarItem',
    props: {
        link: String,
        isSelected: Boolean,
        iconClass: String,
        icon: String,
        title: String,
    }
}
</script>

<style>

</style>