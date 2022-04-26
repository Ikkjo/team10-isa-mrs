import Vue from 'vue'
import Vuelidate from 'vuelidate'
import App from './App.vue'
import VuePhoneNumberInput from 'vue-phone-number-input'
import 'vue-phone-number-input/dist/vue-phone-number-input.css'
import VuePortal from '@linusborg/vue-simple-portal'

Vue.use(VuePortal, {
  name: 'portal', // optional, use to rename component
})

Vue.component('vue-phone-number-input', VuePhoneNumberInput)

Vue.use(Vuelidate)

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  render: h => h(App),
}).$mount('#app')
