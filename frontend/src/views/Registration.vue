<template>
<div class="container">
    <!-- <div class="text">
        <h1>Sing up.</h1>
        <p>Let's get you all setup so you can verify your personal account and begin setting up your profile.</p>
    </div> -->

    <div class="inner-container">
        <div class="registration-container">
            <h1>Sign up.</h1>
            <p>Let's get you all set up so you can verify your personal account and begin setting up your profile.</p>
            <RegistrationForm />
        </div>
    </div>
    <div class="inner-container picture-container">
    </div>
</div>
</template>

<script>
import RegistrationForm from '../components/RegistrationForm.vue'

export default {
    name: 'RegistrationPage',
    components: {
        RegistrationForm,
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
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
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