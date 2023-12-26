<template>
  <!-- ----------------   -->
  <div class="breadcrumb bg-default text-white pt-3">
    <div class="container-fluid">
      <div class="row" style="align-items: flex-start;justify-content: space-between;"> 
        <div class="d-flex">
          <a href="javascript:void(0)" class="btn btn-primary align-center rounded-10" @click="BackButton()"><img
              src="http://localhost:8080/documents/d/guest/angle-right-white" alt="img" /></a>
          <div class="ml-3">
            <h4 class="mb-0">Team members</h4>

            <p class="breadcrumbmain mb-0 font-10">
              <li>Account</li>
              <li v-for="(item, index) in breadcrumbs" :key="index">
                <span> <img src="http://localhost:8080/documents/d/guest/angle-right" class="mx-1 opacity-60" alt="img" />
                  {{ item.label }}</span>
              </li>
            </p>
 </div>
        </div>
        <div class="col-md-6 d-flex justify-content-end z-index-9 mt-1" style="align-items: center;">
          <!--Data loader start here-->
          <div style="min-width: 10rem">
            <div v-if="ProcessBarReactive.display">
          <ProgressBarCe :actualValue="ProcessBarReactive.actualValue" :plannedValue="ProcessBarReactive.plannedValue" />
        </div>
        </div>
          <!--Data Loader end here-->
          
         
        
          <div class="col-md-6 d-flex justify-content-end z-index-9 mt-1">
          <a href="teamMembersAdd.html" class="btn btn-primary align-center rounded-10"><img src="http://localhost:8080/documents/d/guest/add-white" alt="img" /></a>
          <a href="javascript:void(0)" class="btn btn-primary align-center rounded-10 ml-4"><img src="http://localhost:8080/documents/d/guest/filter-white" alt="img" /></a>
          <a href="javascript:void(0)" class="btn btn-primary align-center rounded-10 ml-4"><img src="http://localhost:8080/documents/d/guest/cloud-download-white" alt="img" /></a>
          <a href="javascript:void(0)" class="btn btn-light align-center rounded-10 ml-2 text-default"><img
            src="http://localhost:8080/documents/d/guest/headset"  alt="img" /><div style="min-width: 4rem">Talk to us</div><img
            src="http://localhost:8080/documents/d/guest/angle-right"  alt="img" /></a>
        </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { inject, ref } from 'vue';
import ProgressBarCe from './ProgressBar.ce.vue';



const CurrentPage = inject('CurrentPage');
const ChangePage = inject('ChangePage');
///////////////code for progress bar setting inject  start here ///////////
const ProcessBarReactive = inject('ProcessBarReactive');
///////////////code for progress bar setting inject  end here ///////////

//////////////code for breadcrumbs start here ////////
const breadcrumbs = inject('breadcrumbs');
const BackButton = inject('BackButton');
/////////////code fot  breadcrumbs end here //////////

</script>



<style></style>

