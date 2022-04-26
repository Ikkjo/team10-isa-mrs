<template>
    <div id="pricing">
        <h1>Pricing</h1>
        <div class="form-control">
            <div class="wrapper">
                <label for="adults">Adults</label>
                <input v-model="adults" type="checkbox" name="adults" id="adults" class="checkbox">
            </div>
            
            <input v-if="adults" type="text" name="adults-price" id="adults-price">
        </div>
      
        <div class="form-control">
            <div class="wrapper">
                <label for="children">Children</label>
                <input v-model="children" type="checkbox" name="children" id="children" class="checkbox">
            </div>
            <input v-if="children" type="text" name="children-price" id="children-price">
        </div>
    
        <div class="form-control">
            <div class="wrapper">
                <label for="infants">Infants</label>
                <input v-model="infants" type="checkbox" name="infants" id="infants" class="checkbox">
            </div>
            <input v-if="infants" type="text" name="infants-price" id="infants-price">
        </div>

        <div class="form-control">
            <div class="wrapper">
                <label for="pets">Pets</label>
                <input v-model="pets" type="checkbox" name="pets" id="pets" class="checkbox">
            </div>
            <input v-if="pets" type="text" name="pets-price" id="pets-price">
        </div>

    </div>
</template>

<script>
export default {
    name: 'PricingForm',
    data() {
        return {
            adults: false,
            children: false,
            infants: false,
            pets: false,
        }
    }
}
</script>

<style scoped>
#pricing {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100%;
}

.form-control {
    display: flex;
    align-items: center;
    font-size: 26px;
    height: 10%;
}

.wrapper {
    width: 20%;
    display: flex;
    align-items: center;
}

.wrapper label {

    margin-right: 10%;
}

</style>