<template>
<div>
    <!-- <div class="text">
        <h1>Sing up.</h1>
        <p>Let's get you all setup so you can verify your personal account and begin setting up your profile.</p>
    </div> -->
    <RegistrationForm class="registration-form"/>
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

.text {
    margin: 20px 10px;
}

.registration-form {
    width: 50%;
}


</style>