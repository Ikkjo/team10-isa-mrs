<template>
    <div id="address-input">
        <div class="form-control">
            <label for="address">Address</label>
            <input type="text" 
                @keyup="$emit('update:address', address);" 
                v-model="address" 
                name="address"
                @focus="validate? inFocus('address') : null" 
                @blur="validate? outFocus('address') : null" 
                :class="getClass('address')" 
                :placeholder="getPlaceholder('address', 'e.g. Groove Street 20')">
            <div class="alert-info" 
                v-if="!this.infocus['address'] && !($v.address.minLength && $v.address.maxLength)">
                Enter a valid address.
            </div>
        </div>
        <div class="form-control">
            <label for="city">City</label>
            <input type="text" 
                v-model="city" 
                @keyup="$emit('update:city', city);" 
                name="city"
                @focus="validate? inFocus('city') : null" 
                @blur="validate? outFocus('city') : null" 
                :class="getClass('city')" 
                :placeholder="getPlaceholder('city', 'e.g. Los Santos')"
                >
            <div class="alert-info" 
                v-if="!this.infocus['city'] && !($v.city.minLength && $v.city.maxLength)">
                Enter a valid city.
            </div>
        </div>
            <div class="form-control">
            <label for="country" class="block-label">Country</label>
            <country-select v-model="country" :value="country" :countryName="true" topCountry="US"/>
        </div>
    </div>
</template>

<script>
import { required, minLength, maxLength } from 'vuelidate/lib/validators'

export default {
    props: {
        validate: {
            type: Boolean,
            default: false,
        },
    },
    name: 'AddressInput',
    data() {
        return {
            address: '',
            city: '',
            country: '',
            infocus: {
                address: true,
                city: true,
            }
        }
    },
    watch: {
        country(newCountry, oldCountry) {
            if (newCountry !== oldCountry) this.$emit('update:country', this.country);
        }
    },
    methods: {
        isFocused(field) {
            return this.infocus[field]
        },
        inFocus(field) {
            this.infocus[field] = true
        },
        outFocus(field) {
            this.infocus[field] = false
        },
        getClass(field) {
            let cls = !this.isFocused(field) && this.$v[field].$invalid ? 'alert' : '';
            return cls;
        },
        getPlaceholder(field, defaultPlaceholder='') {
            let placeholder = !this.isFocused(field) && this.$v[field].$invalid ? 'Required' : defaultPlaceholder;
            return placeholder;
        }
    },
    validations: {
        address: {
            required,
            minLength: minLength(5),
            maxLength: maxLength(40)
        },
        city: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(40)
        },
    }

}
</script>

<style scoped>
#address-input {
    display: flex;
    margin: 15px 10px 15px 0px;
}

#address-input :last-child {
    margin-right: 0;
}

.form-control {
    width: 32%;
    margin-right: auto; 
}

.form-control input {
    height: 48px;
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
    margin-top: 65px;
}
</style>