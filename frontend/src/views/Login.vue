<template>
<div class="container">
    <div class="inner-container">
        <div class="login-container">
            <h1>Log in.</h1>
            <LoginForm />
        </div>
    </div>
    <div class="inner-container picture-container"></div>
</div>
</template>

<script>
import LoginForm from '../components/LoginForm.vue'

export default {
    name: 'LoginPage',
    components: {
        LoginForm,
  }
}
</script>

<style>

.container {
    width: 100%;
    display: flex;
    align-items: center;    
}

.inner-container {
    width: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.registration-container {
    padding: 0 100px 0 100px;
}

.picture-container {
    background-image: url('../assets/registration-bg4.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}

p {
    padding-top: 20px;
    max-width: 500px;
}

</style>