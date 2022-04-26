<template>
    <div id="pricing">
        <h1>Pricing</h1>
        <div class="form-control">
            <div class="wrapper">
                <button class="btn-circle"><span class="material-icons-round">add</span></button>
                <input v-model="price" :v-bind="isDisabled()" type="text" placeholder="$00">
                <button class="btn-circle"><span class="material-icons-round">remove</span></button>
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
        }
    },
    methods: {
        isDisabled() {
            if (!/^([1-9][0-9]*)$/.test(this.price) || this.price > 10000) this.price = null
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
</style>