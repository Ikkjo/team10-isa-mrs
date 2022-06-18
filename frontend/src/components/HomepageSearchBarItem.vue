<template>
    <div @click="toggleButton()" class="filter-button" :class="{selected: buttonState}" >
                <span class="icon-class" :class="iconClass">{{icon}}</span>
                <span class="text">{{title}}</span>
    </div>
</template>

<script>
export default {
    name: 'HomepageSearchBarItem',
    data() {
        return {
            buttonState: false
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
            this.buttonState = !(this.buttonState)
            this.$emit("SearchBarButtonToggled", this.toggleState)

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

input, select, textarea{
    padding: 3px 7px;
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
    background-color: #fff;
    font-family: inherit;
}

.input, .select {
   height: 48px;
}

input:hover, textarea:hover, select:hover{
    border-color: var(--orange-primary, #f0a500);
}

input:focus, textarea:focus, select:focus {
    outline-color: var(--orange-primary, #f0a500);
}
</style>