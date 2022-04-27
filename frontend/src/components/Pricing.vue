<template>
    <div id="pricing">
        <div class="form-control">
            <div class="price-input">
                <button @click="decrease()" :disabled="price <= 0" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model="price" :v-bind="checkPrice()" type="text" placeholder="$00" name="price"/>
                <button @click="increase()" :disabled="price >= 10000" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
            <label for="price">per night</label>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PricingForm',
    data() {
        return {
            price: '',
            increment: 5,
        }
    },
    methods: {
        checkPrice() {
            if (!/^([1-9][0-9]*)$/.test(this.price) || this.price > 10000) this.price = null
        },
        increase() {
            this.price += this.increment
        },
        decrease() {
            this.price -= this.increment
        }
    }
}
</script>

<style scoped>
#pricing {
    height: 100%;
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

.price-input {
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

</style>