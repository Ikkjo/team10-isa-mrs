<template>
   <div id="address-input">
            <div class="form-control">
                <label for="address">Address</label>
                <input type="text" :value="address" @keyup="$emit('update:address', address);" name="address" placeholder="">
            </div>
            <div class="form-control">
                <label for="city">City</label>
                <input type="text" :value="city" @keyup="$emit('update:city', city);" name="city" placeholder="">
            </div>
             <div class="form-control">
                <label for="country" class="block-label">Country</label>
                <country-select value:="country" @keyup="$emit('update:country', country);" :country="country" topCountry="US"/>
            </div>
        </div>
</template>

<script>
export default {
    name: 'AddressInput',
    props: {
        address: String,
        city: String,
        country: String,
    }

}
</script>

<style scoped>
#address-input {
    display: flex;
    width: 100%;
}

.form-control {
    display: flex;
    flex-direction: column;
}

.form-control input {
    width: 240px;
}

</style>