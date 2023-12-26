<template>
  <!-- ----------------   -->
  <div class="breadcrumb bg-default text-white pt-3">
    <div class="container-fluid">
      <div class="row" style="align-items: flex-start;justify-content: space-between;">
        <div class="d-flex">
          <a href="javascript:void(0)" style="    width: 4rem;" class="btn btn-primary align-center rounded-10" @click="BackButton()"><img
              :src="'/documents/d/mysolutions/angle-right-white'" alt="img" /></a>
          <div class="ml-3">
            <h4 class="mb-0">Raise a ticket</h4>

            <p class="breadcrumbmain mb-0 font-10">
              <li>Support</li>
              <li v-for="(item, index) in breadcrumbs" :key="index">
                <span> <img :src="'/documents/d/mysolutions/angle-right'" class="mx-1 opacity-60" alt="img" />
                  {{ item.label }}</span>
              </li>
            </p>
          </div>
        </div>
        <div class="col-md-6 d-flex justify-content-end z-index-9 mt-1" style="align-items: center;">
          <!--Data loader start here-->
          <div style="min-width: 10rem">

          </div>
          <!--Data Loader end here-->


          <a href="javascript:void(0)" class="btn btn-light align-center rounded-10 ml-2 text-default"><img
              :src="'/documents/d/mysolutions/headset'" alt="img" />
            <div  @click ="submitcontact" class="talk">Talk to us</div><img :src="'/documents/d/mysolutions/angle-right'" alt="img" />
          </a>
        </div>
      </div>
    </div>
  </div>
  <LoadingTalktoUs v-if="showPopupcontactSuccess"/>
</template>

<script setup>
import { inject, ref ,provide } from 'vue';
import LoadingTalktoUs from '../Global/LoadingTalktoUs.ce.vue';






const showPopupcontactSuccess = ref(false);
provide('showPopupcontactSuccess', showPopupcontactSuccess)
///////////////code for progress bar setting inject  start here ///////////


//////////////code for breadcrumbs start here ////////

/////////////code fot  breadcrumbs end here //////////

const submitcontact = async () => {
      
      showPopupcontactSuccess.value = true;
      console.log("worked");
      

}

</script>





