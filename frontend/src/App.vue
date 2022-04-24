<template>
  <div id="app">
    <RegistrationForm/>
  </div>
</template>

<script>
import RegistrationForm from './components/RegistrationForm.vue'

export default {
  name: 'App',
  components: {
    RegistrationForm,
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
    background-color: var(--white, #fff);
    color: var(--black, #000);
}

a {
    text-decoration: none;
}

button {
  background-color: var(--orange-primary, orange);
}

input {
  background-color: var(--white, #fff);
}

</style>
