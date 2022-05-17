<template>
    <div id="number">
        <div class="form-control">
            <label for="number">{{label}}</label>
            <div class="number-input">
                <button @click="decrease()" :disabled="number <= minValue" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="number" :v-bind="checkNumber()" @keyup="$emit('updated', number);" type="text" :placeholder="placeholder" name="number" @focus="inFocus('number')" @blur="outFocus('number')" :class="getClass('number')"/>
                <button @click="increase()" :disabled="number >= maxValue" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
        </div>
    </div>
</template>

<script>
import {required, between} from 'vuelidate/lib/validators'
export default {
    name: 'NumberInput',
    props: {
        placeholder: String,
        label: String,
        increment: Number,
        minValue: Number,
        maxValue: Number,
    },
    data() {
        return {
            infocus: {
                number: true,
            },
            number: 1,
        }
    },
    validations() {
        return {
        number: {
                required,
                between: between(this.minValue, this.maxValue)
            }
        }
       
    },
    methods: {
        checkNumber() {
            if (!/^([1-9][0-9]*)$/.test(this.number)) this.number = null
        },
        increase() {
            if (this.number < this.maxValue) this.number += this.increment
            else this.number = this.maxValue
            this.$emit('updated', this.number)
        },
        decrease() {
            this.number -= this.increment
            this.$emit('updated', this.number)
        },
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
    },
}
</script>

<style scoped>
#number {
    display: flex;
}

.form-control {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form-control input {
    width: 240px;
    margin: 0 10px 0 10px;
    
}

.number-input {
    display: flex;
    align-items: center;
}

.wrapper label {
    color: lightgray;
}

.btn-circle {
    width: 33px;
    height: 33px;
    border: none;
    background: var(--orange-primary, orange);
    color: white;
    border-radius: 50%;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    transition: 0.4s;
}

.btn-circle:disabled {
    background-color: lightgray;
}

.alert-label {
    color: red;
    margin-top: 5px;
}

</style>