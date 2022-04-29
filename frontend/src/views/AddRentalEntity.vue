<template>
  <div id="add-vacation-home">
      <div class="inner-container form">
        <RentalEntityBasicInfo/>
      </div>
      <div class="inner-container picture-container"></div>
  </div>
</template>

<script>
import RentalEntityBasicInfo from '../components/RentalEntityBasicInfo.vue'
export default {
    name: 'AddRentalEntity',
    components: {
        RentalEntityBasicInfo,
    }

}
</script>

<style>
#add-vacation-home {
    width: 100%;
    display: flex;
    align-items: center;  
}

.inner-container {
    width: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100vh;
}

.form {
    padding: 0 100px 0 100px;
}

.picture-container {
    background-image: url('../assets/add-vacation-home-bg.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}
</style>