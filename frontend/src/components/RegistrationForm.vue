<template>
    <form class="registration-from">
        <div>
            <div class="form-control">
                <label for="first-name">First Name</label>
                <input type="text" v-model="firstName" name="first-name">
            </div>
            <div class="form-control">
                <label for="last-name">Last Name</label>
                <input type="text" v-model="lastName" name="last-name">
            </div>
        </div>
        <div>
            <div class="form-control">
                <label for="email">Email</label>
                <input type="text" v-model="email" name="email" placeholder="name@rentr.com">
            </div>
            <div class="form-control">
                <label for="phone-number">Phone Number</label>
                <input type="text" v-model="phone" name="phone-number" placeholder="+381 612345678">
            </div>
        </div>
        <div>
            <div class="form-control">
                <label for="password">Password</label>
                <input type="text" v-model="email" name="password" placeholder="At least 8 characters">
            </div>
            <div class="form-control">
                <label for="confirm-password">Confirm password</label>
                <input type="text" v-model="confirmPassword" name="confirm-password" placeholder="">
            </div>
        </div>
        <div>
            <div class="form-control">
                <label for="address">Address</label>
                <input type="text" v-model="address" name="address" placeholder="">
            </div>
            <div class="form-control">
                <label for="city">City</label>
                <input type="text" v-model="city" name="city" placeholder="">
            </div>
             <div class="form-control">
                <label for="country">Country</label>
                <select name="country" id="country">
                    <option v-for="country in countries" :value="country" :key="country">{{country}}</option>
                </select>
            </div>
        </div>
        
        <div>
             <div class="form-control">
                <label for="role">Account type</label>
                <select name="role" id="role">
                    <option value="HOUSE_OWNER">Vacation Home Owner</option>
                    <option value="SHIP_OWNER">Ship Owner</option>
                    <option value="FISHING_INSTRUCTOR">Fishing Instructor</option>
                </select>
            </div>
             <div class="form-control">
                <label for="description">Reason for Registration</label>
                <textarea name="description" id="description" cols="30" rows="5" placeholder="Tell us a few reasons why you want to join..."></textarea>
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
            lastNmae: '',
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
    created() {
        this.countries = ['USA', 'Serbia']
    },
    methods: {

    },


}
</script>

<style>




</style>