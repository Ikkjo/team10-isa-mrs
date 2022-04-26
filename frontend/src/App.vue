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

html, body {
  height: 100%;
}

body {
    font-family: 'Roboto', sans-serif;
    background-color: #fff;
    color: var(--black, #000);
}

a {
    text-decoration: none;
    color: var(--orange-primary, orange);
    transition: 0.5s;
}

a:hover {
  color: var(--orange-secondary, darkorange);
}

.btn {
    display: inline-block;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    outline: none;
    color: var(--white, #fff);
    background-color: var(--orange-primary, orange);
    cursor: pointer;
    font-family: inherit;
    text-decoration: none;
    transition: 0.5s;
    font-size: 17px;
}

.btn:hover {
    background-color: var(--orange-secondary, orange);
}

.btn:active {
  transform: scale(0.98);
}


</style>
