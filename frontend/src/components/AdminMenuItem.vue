<template>
    <router-link 
                :to="{name: link}" 
                class="option" 
                :class="{'selected': isSelected}"
                @click.native="updateSelected"
                >
                <span class="icon-class" :class="iconClass">{{icon}}</span>
                <span class="text">{{title}}</span>
    </router-link>
</template>

<script>
export default {
    name:'AdminMenuItem',
    props: {
        link: String,
        isSelected: Boolean,
        iconClass: String,
        icon: String,
        title: String,
    },
    methods: {
        updateSelected() {
            this.$emit('selected')
        }
    },
}
</script>