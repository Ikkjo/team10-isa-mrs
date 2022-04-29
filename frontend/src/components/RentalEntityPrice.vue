<template>
	<div id="price-input">
		<h1>Price</h1>
		<price-input @updated="priceUpdated" class="form-control pricing"/>
		<div class="btn-div">
			<button @click="cancel" class="btn btn-cancel">Cancel</button> 
			<button @click="next" class="btn">Next</button>
		</div>
	</div>
</template>

<script>
import PriceInput from './PriceInput.vue'
export default {
	name: 'RentalEntityPrice',
	components: {
		PriceInput,
	},
	data() {
		return {
			price: 0,
		}
	},
	mounted() {
		if (localStorage.price) this.price = localStorage.price;
	},
	methods: {
		priceUpdated(price) {
				localStorage.price = price;
		},
		next() {
            localStorage.title = this.title;
            localStorage.address = this.address;
            localStorage.city = this.city;
            localStorage.country = this.country;
            localStorage.description = this.description;
            // add router.route to next page
            this.$router.push("/add-rental-entity/additional-info");
        },
        cancel() {
            localStorage.removeItem("title"); 
            localStorage.removeItem("address"); 
            localStorage.removeItem("city"); 
            localStorage.removeItem("country");
            localStorage.removeItem("description");
            // add router.route to main page 
        },
	}

}
</script>

<style scoped>
#price-input {
	width: 100%;
	display: flex;
	flex-direction: column;
}

h1  {
	align-self: center;
}

.btn-div {
    display: flex;
    justify-content: space-between;
}

.btn {
    margin-top: 15px;
    width: 25%;
    align-self: center;
}

.btn-cancel {
    margin-top: 0px !important;
}
</style>