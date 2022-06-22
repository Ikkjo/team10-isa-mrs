<template>
    <div class="dropdown">
        <slot name="items"/>
    </div>
</template>

<script>
export default {
    name: 'DropdownMenu'
}
</script>

<style>
.dropdown {
    position: absolute;
    top: 52px;
    width: 250px;
    transform: translateX(-33.2%);
    background-color: white;
    box-shadow: 0px 1px 8px lightgrey;
    border-radius: 8px;
    padding: 1rem;
    overflow: hidden;
    transition: height 500ms ease;
}
</style>