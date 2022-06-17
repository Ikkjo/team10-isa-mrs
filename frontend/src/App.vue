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
    /* colors */
    --orange-primary: #f0a500;
    --orange-secondary: #cf7500;
    --white: #f4f4f4;
    --black: #1a1c20;
    /* sizing */
    --nav-height: 60px;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

html, body {
  /* height: 100%; */
}

body {
    font-family: 'Roboto', sans-serif;
    background-color: #fff;
    color: var(--black, #000);
}

.noselect {
  -webkit-touch-callout: none; /* iOS Safari */
    -webkit-user-select: none; /* Safari */
     -khtml-user-select: none; /* Konqueror HTML */
       -moz-user-select: none; /* Old versions of Firefox */
        -ms-user-select: none; /* Internet Explorer/Edge */
            user-select: none; /* Non-prefixed version, currently
                                  supported by Chrome, Edge, Opera and Firefox */
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

.btn-red {
  background-color: red;
}

.btn-red:hover {
  background-color: darkred;
}

.form {
  width: 100%;
}

button:disabled {
    pointer-events: none;
    background-color: lightgray;
}

.form-control {
  display: flex;
  flex-direction: column;
}

.input, .select, .textarea {
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

.input:hover, .textarea:hover, .select:hover {
    border-color: var(--orange-primary, #f0a500);
}

.input:focus, .textarea:focus, .select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

.form-control input, select, textarea,
.calendar input {
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

.form-control input:hover, textarea:hover, select:hover,
.calendar input:hover {
    border-color: var(--orange-primary, #f0a500);
}

.form-control input:focus, textarea:focus, select:focus
.calendar input:ocus {
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

ul {
  list-style: none;
}

#nav {
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 2;
}

#searchbar {
    position: fixed;
    margin-top: var(--nav-height);
    width: 100%;
    z-index: 1;
}

.alert {
    transition: 0.5s;
    border-color: red !important;
    border-width: 2px;
}

.alert-info {
    position: absolute;
    transition: 0.05s;
    color: red !important;
    font-size: 0.9rem;
}

.block {
  display: block;
}

</style>
