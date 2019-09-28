import Vue from 'vue'
import App from './App.vue'
import data from './data'
import router from './router'

Vue.config.productionTip = false;
Vue.prototype.$user = data.user;

new Vue({
    router,
    data() {
        return data
    },
    render: h => h(App)
}).$mount('#app');
