import { defineCustomElement } from 'vue'
import App from './App.ce.vue'

const element = defineCustomElement(App) //defining custom element

customElements.define('nav-dashboard', element)
