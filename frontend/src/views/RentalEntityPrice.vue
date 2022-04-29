<template>
	<div id="rental-entity-price">
		<div class="inner-container form">
			<h1>Price</h1>
			<price-input @updated="priceUpdated" class="form-control pricing"/>
			<div class="btn-div">
				<button @click="cancel" class="btn btn-cancel">Cancel</button> 
				<button @click="next" class="btn">Next</button>
			</div>
		</div>
		<div class="inner-container picture-container"></div>
	</div>
</template>
	

<script>
import PriceInput from '../components/PriceInput.vue'
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
#rental-entity-price {
	width: 100%;
    display: flex;
    align-items: center;  
}

.inner-container {
    width: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100vh;
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

.picture-container {
    background-image: url('../assets/add-vacation-home-bg.jpg');
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
}
</style>