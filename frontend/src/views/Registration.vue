<template>
<RegistrationForm class="registration-form"/>
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

.registration-form {
    width: 50%;
}

</style>