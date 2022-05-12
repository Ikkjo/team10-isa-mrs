<template>
    <li class="nav-item" @click="$emit('click', event)">
        <router-link :to="link" class="nav-link">{{text}}</router-link>
        <slot name="menu"/>
    </li>
</template>

<script>
export default {
    name: 'NavItem',
    props: ['link', 'text']
}
</script>

<style>

.nav-item {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 5px;
}
</style>