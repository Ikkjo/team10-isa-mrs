import Vue from 'vue'
import Vuelidate from 'vuelidate'
import App from './App.vue'
import VuePhoneNumberInput from 'vue-phone-number-input'
import 'vue-phone-number-input/dist/vue-phone-number-input.css'
import vueCountryRegionSelect from 'vue-country-region-select'
import 'material-icons/iconfont/material-icons.css'


Vue.component('vue-phone-number-input', VuePhoneNumberInput)

Vue.use(Vuelidate)
Vue.use(vueCountryRegionSelect)

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  render: h => h(App),
}).$mount('#app')
