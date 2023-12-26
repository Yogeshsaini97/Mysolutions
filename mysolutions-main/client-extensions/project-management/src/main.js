import { defineCustomElement } from 'vue'
import App from './App.ce.vue'



// using definecustomElement method here 
const element = defineCustomElement(App)


//Defining custom elments in the html section
customElements.define('project-management', element)  // here we are defining our custom element, same could be seen in our index.html file


