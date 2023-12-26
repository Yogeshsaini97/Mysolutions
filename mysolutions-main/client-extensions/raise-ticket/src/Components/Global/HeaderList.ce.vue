<template>
  <!-- ----------------   -->
  <div class="breadcrumb bg-default text-white pt-3">
    <div class="container-fluid">
      <div class=" row" style="align-items: flex-start;justify-content: space-between;">
        <div class="backbuttonfix d-flex">
          <a  style="    width: 4rem;" class="btn img-fluid btn-primary align-center rounded-10" href="./dashboard"><img
              :src="'/documents/d/mysolutions/angle-right-white'" alt="img" /></a>
          <div class="ml-3">
            <h4 class="mb-0">{{ i18n.global.t('CREATE_CASE') }}</h4>

         
              <div style="display: flex;margin-left: 3px;
              ">
              <li class="breadcrumbmain mb-0 font-10 opacity-60" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DASHBOARD')" > <a style="    text-decoration: none;
                color: white;" href="./dashboard">{{ i18n.global.t('DASHBOARD') }}</a></li>
              <li  >
                <span class="breadcrumbmain mb-0 font-10 "> <img :src="'/documents/d/mysolutions/angle-right'" class="mx-1 opacity-60 img-fluid" alt="img" />
                  {{ i18n.global.t('CREATE_CASE') }}</span>
              </li>
            </div>
          
          </div>
        </div>
        <div class="col-md-6 d-flex justify-content-end z-index-9 mt-1" style="align-items: center;">
          <!--Data loader start here-->
          <div style="min-width: 10rem">

          </div>
          <!--Data Loader end here-->


          <a  class="btn btn-light align-center rounded-10 ml-2 text-default talktousrtl"><img class="mr-1"
              :src="'/documents/d/mysolutions/headset'" alt="img" />
              <div  @click ="submitcontact" class="talk">{{ i18n.global.t('TALK_TO_US') }}</div><img class="ml-1" :src="'/documents/d/mysolutions/angle-right'" alt="img" />
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

const i18n = inject('i18n');



const showPopupcontactSuccess = ref(false);
provide('showPopupcontactSuccess', showPopupcontactSuccess)
///////////////code for progress bar setting inject  start here ///////////


const submitcontact = async () => {
      
      showPopupcontactSuccess.value = true;
      console.log("worked");
      }

</script>





