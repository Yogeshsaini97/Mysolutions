import { defineCustomElement } from 'vue'
import App from './App.ce.vue'
//import ProgressBar from './Components/Global/ProgressBar.ce.vue'



//customElements.define('progress-bar', defineCustomElement(ProgressBar))
const element = defineCustomElement(App)


//Defining custom elments in the html section
customElements.define('team-members', element)
