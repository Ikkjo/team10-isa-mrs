<template>
    <form id="registration-form">
        <div class="wrapper">
            <div class="form-control">
                <label for="first-name">First Name</label>
                <input type="text" v-model="firstName" name="first-name">
            </div>
            <div class="form-control">
                <label for="last-name">Last Name</label>
                <input type="text" v-model="lastName" name="last-name">
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="email">Email</label>
                <input type="text" v-model="email" name="email" placeholder="name@rentr.com">
            </div>
            <div class="form-control">
                <label for="phone-number">Phone Number</label>
                <vue-phone-number-input
                 class="phone-number" 
                 v-model="phone" 
                 default-country-code="RS" 
                 size="lg"
                 color="#f0a500"
                 valid-color="green"
                 error-color="red"

                 />
            </div>
        </div>
        <div class="wrapper">
            <div class="form-control">
                <label for="password">Password</label>
                <input type="password" v-model="password" name="password" placeholder="At least 8 characters">
            </div>
            <div class="form-control">
                <label for="confirm-password">Confirm password</label>
                <input type="password" v-model="confirmPassword" name="confirm-password" placeholder="">
            </div>
        </div>
        
        <div class="wrapper address-wrapper">
            <div class="form-control">
                <label for="address">Address</label>
                <input type="text" v-model="address" name="address" placeholder="">
            </div>
            <div class="form-control">
                <label for="city">City</label>
                <input type="text" v-model="city" name="city" placeholder="">
            </div>
             <div class="form-control">
                <label for="country" class="block-label">Country</label>
                <select name="country" id="country">
                    <option v-for="country in countries" :value="country" :key="country">{{country}}</option>
                </select>
            </div>
        </div>
        
        <div class="wrapper">
             <div class="form-control">
                <label for="role" class="block-label">Account type</label>
                <select name="role" id="role">
                    <option value="HOUSE_OWNER">Vacation Home Owner</option>
                    <option value="SHIP_OWNER">Ship Owner</option>
                    <option value="FISHING_INSTRUCTOR">Fishing Instructor</option>
                </select>
            </div>
             <div class="form-control">
                <label for="description" class="block-label">Reason for Registration</label>
                <textarea name="description" id="description" cols="30" rows="5"  placeholder="Tell us a few reasons why you want to join..."></textarea>
            </div>
        </div>
    </form>
</template>

<script>
export default {
    name: 'RegistrationFrom',
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            password: '',
            confirmPassword: '',
            address: '',
            city: '',
            country: '',
            coutries: []
        }
    },
    validations:{
        firstName: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(20)
        },
        lastName: {
            required,
            minLength: minLength(2),
            maxLength: maxLength(20)
        },
        email: {
            requried,
            email
        },
        password: {
            required,
            minLength: minLength(8),
            maxLength: maxLength(30)
        },
        confirmPassword: {
            required,
            sameAsPassword: sameAs("password")
        },
        address: {
            requred,
            minLength: minLength(5),
            maxLength: maxLength(40)
        },
        city: {
            requried,
            minLength: minLength(2),
            maxLength: maxLength(40)
        },
    },
    created() {
        this.countries = ['USA', 'Serbia']
    },
    methods: {

    },


}
</script>

<style scoped>

.form-control {
  margin: 20px 10px;
}

.wrapper {
    display: grid;
    grid-template-columns: 50% 50%;
}

.address-wrapper {
    grid-template-columns: 50% 30% 20%;
}

.form-control input, select, textarea {
    width: 100%;
    height: 48px;
    padding: 3px 7px;
    font-size: 17px;
    border-radius: 5px;
    border: 1px solid ;
    border-color: lightgrey;
    transition: 0.5s;
    outline-color: lightgrey;
}

.form-control input:hover, textarea:hover, select:hover {
    border-color: var(--orange-primary, #f0a500);
}

.form-control input:focus, textarea:focus, select:focus {
    outline-color: var(--orange-primary, #f0a500);
}

textarea {
    height: auto;
    resize: none;
}

.form-control slabel {
    width: 100%;
}

.form-control .block-label {
    display: block;
}

</style>