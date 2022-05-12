<template>
  <router-link to="link" class="menu-item">
      <span :class="'icon-button' + ' ' + iconClass ">{{icon}}</span>
      {{text}}
  </router-link>
</template>

<script>
export default {
    name: 'DropdownItem',
    props: ['link', 'text', 'iconClass', 'icon']

}
</script>

<style>
.menu-item {
  height: 50px;
  display: flex;
  align-items: center;
  border-radius: 8px;
  transition: background 500ms;
  padding: 0.5rem;
}

.menu-item .icon-button {
  margin-right: 0.5rem;
}

.menu-item .icon-button:hover {
  filter: none;
}

.menu-item:hover {
  background-color: #525357;
}

.icon-right {
  margin-left: auto;
}
</style>