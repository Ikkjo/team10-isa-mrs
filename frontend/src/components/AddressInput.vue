<template>
   <div id="address-input">
            <div class="form-control">
                <label for="address">Address</label>
                <input type="text" @keyup="$emit('update:address', address);" v-model="address" name="address" placeholder="">
            </div>
            <div class="form-control">
                <label for="city">City</label>
                <input type="text" v-model="city" @keyup="$emit('update:city', city);" name="city" placeholder="">
            </div>
             <div class="form-control">
                <label for="country" class="block-label">Country</label>
                <country-select v-model="country" :value="country" :countryName="true" topCountry="US"/>
            </div>
        </div>
</template>

<script>
export default {
    name: 'AddressInput',
    data() {
        return {
            address: '',
            city: '',
            country: '',
        }
    },
    mounted() {
        if (this.$route.path === "/add-rental-entity") {
            if (localStorage.address) this.address = localStorage.address;
            if (localStorage.city) this.city = localStorage.city;
            if (localStorage.country) this.country = localStorage.country;
        }
    },
    watch: {
        country(newCountry, oldCountry) {
            if (newCountry !== oldCountry) this.$emit('update:country', this.country);
        }
    }

}
</script>

<style scoped>
#address-input {
    display: flex;
}

.form-control {
    width: 33.3%;
    margin-right: 10px; 
}

.form-control:last-child {
    margin-right: 0 
}

</style>