<template>
  <div id="app">
    <RegistrationPage/>
  </div>
</template>

<script>
import RegistrationPage from './views/Registration.vue'

export default {
  name: 'App',
  components: {
    RegistrationPage,
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap');
:root {
    /* variables */
    --orange-primary: #f0a500;
    --orange-secondary: #cf7500;
    --white: #f4f4f4;
    --black: #1a1c20;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
    font-family: 'Roboto', sans-serif;
    background-color: #fff;
    color: var(--black, #000);
}

a {
    text-decoration: none;
}

.btn {
    display: inline-block;
    border: none;
    border-radius: 49px;
    outline: none;
    color: var(--white, #fff);
    background-color: var(--orange-primary, orange);
    margin: 5px;
    cursor: pointer;
    font-family: inherit;
    text-decoration: none;
    transition: 0.5s;
}

.btn:hover {
    background-color: var(--orange-secondary, orange);
}

select {
  background-color: #fff;;
}

</style>
