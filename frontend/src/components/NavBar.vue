<template>
  <nav class="navbar">
    <router-link to="nav" class="logo">Rentr.</router-link>
    <ul class="navbar-nav">
        <slot name="nav-right" class="nav-right"/>
    </ul>
  </nav>
</template>

<script>
export default {
    name: 'NavBar',
}
</script>

<style>
.navbar {
  height: var(--nav-height);
  border-bottom: 1px solid rgb(236, 236, 236);
  padding: 0 10%;
  background-color: white;
}

.navbar-nav {
  max-width: 100%;
  height: 100%;
  display: flex;
  justify-content: flex-end;
}

.logo {
  position: absolute;
  margin: 16px 0;
  font-size: 1.5rem;
  font-weight: 600;
}

.logo:hover {
  color: var(--orange-primary);

}

</style>