<template>
    <div @click="toggleButton()" class="filter-button" :class="{selected: buttonToggled}" >
                <span class="icon-class" :class="iconClass">{{icon}}</span>
                <span class="text">{{title}}</span>
    </div>
</template>

<script>
export default {
    name: 'HomepageSearchBarItem',
    data() {
        return {
            buttonToggled: false
        }
    },
    props: {
        isSelected: Boolean,
        iconClass: String,
        icon: String,
        title: String,
    },
    methods: {
        toggleButton() {

            if(this.buttonToggled) {
                this.buttonToggled = false;
                this.$emit("SearchBarButtonToggled", false)
            } else {
                this.buttonToggled = true;
                this.$emit("SearchBarButtonToggled", true)
            }
        }
    }
}
</script>

<style scoped>
.filter-button {
    text-decoration: none;
    color: var(--orange);
    display: grid;
    align-items: center;
    gap:6px;
    transition: 0.3s;
    padding: 5px 25px;
    cursor: pointer;
}

.filter-button.selected {
    color: var(--orange-secondary, darkorange);
}

/* .filter-button:hover {
    color:#f0a400a3;
} */

.filter-button .icon-class {
  font-size: 1.6rem;
  display: flex;
  justify-self: center;
}

.filter-button .text {
    font-size: 0.9rem;
    text-align: center; 
    justify-self: center;
}
</style>