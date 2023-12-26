
<style src="./assets//Style/Dropdown.css"></style>

<style src="./assets//Style/ListPage.css"></style> <!-- Include the external CSS file -->
<style src="./assets//Style/Risk&Issues.css"></style> <!-- Include the external CSS file -->
<style src="./assets//Style/Main.css"></style> <!-- Include the external CSS file -->

<style>

@import 'http://localhost:8080/o/stcs-theme/css/main.css'; 


.glyphicon-nav {
  width: 18px;
  padding: 8px 0;
  position: relative;
  display: inline-block;
}
.glyphicon-nav:before {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  border-top: 2px solid red;
  border-bottom: 2px solid red;
  top: 0px;
}
.glyphicon-nav:after {
  content: '';
  position: absolute;
  top: 7px;
  left: 0px;
  height: 2px;
  width: 100%;
  background: Red;
}
</style> 


<script setup>
import { provide } from "vue";
import ProjectModuleEntry from "./Components/Views/ProjectModuleEntry.ce.vue"
provide("pageSizeNum",4);

</script>

<template>
  <div>
    <ProjectModuleEntry />
  </div>
  <!--calling ProjectModule.vue file  -->
</template>








