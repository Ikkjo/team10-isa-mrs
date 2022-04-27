<template>
    <div id="pricing">
        <h1>Pricing</h1>
        <div class="form-control">
            <div class="wrapper">
                <button @click="decrease()" :disabled="price <= 0" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model="price" :v-bind="checkPrice()" type="text" placeholder="$00"/>
                <button @click="increase()" :disabled="price >= 10000" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
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
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100%;
}

.form-control {
    display: flex;
    align-items: center;
    font-size: 26px;
    width: 100%;
}

.form-control input {
    width: 240px;
}

.wrapper {
    display: flex;
    align-items: center;
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
}

.btn-circle:disabled {
    background-color: lightgray;
}

</style>