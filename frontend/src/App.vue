<template>
  <div id="app">
    <!-- <RegistrationPage/> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {

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

#app {
  height: 100%;
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

button:disabled {
    pointer-events: none;
    background-color: lightgray;
}

.form-control {
  display: flex;
  flex-direction: column;
}

.form-control input, select, textarea {
    padding: 3px 7px;
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
    background-color: #fff;
    font-family: inherit;
}

.form-control input, select {
   height: 48px;
}

.form-control input:hover, textarea:hover, select:hover {
    border-color: var(--orange-primary, #f0a500);
}

.form-control input:focus, textarea:focus, select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

.block-label {
  display: block;
}

textarea {
  resize: none;
}

.alert {
    transition: 0.5s;
    border-color: red !important;
    border-width: 2px !important;
}

</style>
