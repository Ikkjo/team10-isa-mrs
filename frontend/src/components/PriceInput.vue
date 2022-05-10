<template>
    <div id="pricing">
        <div class="form-control">
            <!-- <label for="price">Pricing</label> -->
            <div class="price-input">
                <button @click="decrease()" :disabled="price <= 0" class="btn-circle"><span class="material-icons-round">remove</span></button>
                <input v-model.number="price" :v-bind="checkPrice()" @keyup="$emit('updated', price);" type="text" placeholder="$00" name="price"/>
                <button @click="increase()" :disabled="price >= 10000" class="btn-circle"><span class="material-icons-round">add</span></button>
            </div>
            <!-- <label for="price">per night</label> -->
        </div>
    </div>
</template>

<script>
export default {
    name: 'PriceInput',
    data() {
        return {
            price: 0,
            increment: 5,
        }
    },
    methods: {
        checkPrice() {
            if (!/^([1-9][0-9]*)$/.test(this.price) || this.price > 10000) this.price = null
        },
        increase() {
            if (this.price < 9995) this.price += this.increment
            else this.price = 10000
            this.$emit('updated', this.price)
        },
        decrease() {
            this.price -= this.increment
            this.$emit('updated', this.price)
        }
    },
}
</script>

<style scoped>
#pricing {
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