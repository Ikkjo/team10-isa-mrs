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
  transform: translateX(-40%);
  background-color: #242526;
  border: 1px solid #474a4d;
  border-radius: 8px;
  padding: 1rem;
  overflow: hidden;
  transition: height 500ms ease;
}
</style>