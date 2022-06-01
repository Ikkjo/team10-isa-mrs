<template>
    <router-link
                :to="{name: link}"
                class="filter-button"
                :class="{'selected': isSelected}"
                @click.native="updateViewWhenSelected">
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
    },
    methods: {
        updateViewWhenSelected() {
            this.$emit('')
        }
    }
}
</script>

<style>

.filter-button {
    height: 15px;
    text-decoration: none;
    color: white;
    display: flex;
    align-items: center;
    gap:10px;
    transition: 0.3s;
    padding:5px 15px;
    cursor: pointer;
}

.filter-button.selected {
    background-color: var(--orange-secondary, darkorange);
}

.filter-button:hover {
    background-color: var(--orange-secondary, darkorange);
}

.filter-button .icon-class {
  font-size: 2rem;
}

.filter-button .text {
    font-size: 1.2rem;    
}
</style>