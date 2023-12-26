<template>


  <!-- ----------------   -->
  <header ref="header" :class="{ 'sticky': isSticky }" id="myHeader" class="breadcrumb bg-default text-white pt-3">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../assets/images/favicon.ico" type="image">
    <link href="../assets/css/style.css" rel="stylesheet">
    <div class="container-fluid">
      <div class="row" style="align-items: flex-start;justify-content: space-between;">
        <div class=" backbuttonfix d-flex">
          <a href="./dashboard" class="btn btn-primary align-center rounded-10"
           ><img class="img-fluid" :src="'/documents/d/mysolutions/angle-right-white'"
              alt="img" /></a>
          <div class="ml-3">
            <h4 class="mb-0 font-weight-600">{{
              breadcrumbs[1] ? breadcrumbs[1].label : i18n.global.t('NOTIFICATIONS') }}</h4>

            <p class="breadcrumbmain mb-0 font-10">
              <li data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DASHBOARD')" class="mx-1 opacity-60"> <a style="    text-decoration: none;
                color: white;" href="./dashboard">{{ i18n.global.t('DASHBOARD') }}</a></li>
              <li  v-for="(item, index) in breadcrumbs" :key="index">
                <a style="    text-decoration: none;
               color: white;" :href="item.Link"> <span> <img :src="'/documents/d/mysolutions/angle-right'"
                      class="mx-1 opacity-60" alt="img" />
                      
                   <span data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_NOTIFICATION')"> {{ i18n.global.t(item.label) }}</span></span></a>
              </li>
            </p>
          </div>
        </div>
        <div class="col-md-6 d-flex justify-content-end z-index-9 mt-1" style="align-items: center;">
          <!--Data loader start here-->
          <div style="min-width: 10rem">
            <div v-if="ProcessBarReactive.display">
              <!-- <ProgressBarCe :actualValue="ProcessBarReactive.actualValue" :plannedValue="ProcessBarReactive.plannedValue.split(' ')[0]" /> -->
            </div>
          </div>
          <!--Data Loader end here-->
          <!-- <button>dummy  buton</button> -->
          <!-- <a type="button" class="btn btn-primary align-center rounded-10"><img :src="'/documents/d/mysolutions/filter-white'" alt="img" @click="updateDownloadFilter({'value':true,'type':'filter'})" v-if="selectPageAndFilterAndDownload.download > 0 && selectPageAndFilterAndDownload.page!='no'" /></a>
          <a type="button" class="btn btn-primary align-center rounded-10 ml-4"><img :src="'/documents/d/mysolutions/cloud-download-white'" alt="img" @click="updateDownloadFilter({'value':true,'type':'download'})"  v-if="selectPageAndFilterAndDownload.download>0 && selectPageAndFilterAndDownload.page!='no'"/></a> -->
          <a  class="btn btn-light align-center rounded-10 ml-2 text-default talktousrtl"><img
              :src="'/documents/d/mysolutions/headset'" alt="img" />
            <div @click ="submitcontact" style="min-width: 5rem" class="talk">{{ i18n.global.t('TALK_TO_US') }}</div><img class="img-fluid"
           :src="'/documents/d/mysolutions/angle-right'" alt="img" />
          </a>
        </div>
      </div>
    </div>
    <LoadingTalktoUs v-if="showPopupcontactSuccess"/>
  </header>
</template>

<script setup>
import { inject, onMounted, onUnmounted, provide, ref } from 'vue';
import ProgressBarCe from './progress-bar.ce.vue';
import LoadingTalktoUs from './LoadingTalktoUs.ce.vue';

console.log(window.location.origin)

const showPopupcontactSuccess = ref(false);
provide('showPopupcontactSuccess', showPopupcontactSuccess)
///////////////code for progress bar setting inject  start here ///////////




const ProjectApiId = inject("ProjectApiId");
const CurrentPage = inject('CurrentPage');
const ChangePage = inject('ChangePage');








///////////////code for progress bar setting inject  start here ///////////
const ProcessBarReactive = inject('ProcessBarReactive');
///////////////code for progress bar setting inject  end here ///////////

//////////////code for breadcrumbs start here ////////
const breadcrumbs = inject('breadcrumbs');
const BackButton = inject('BackButton');
/////////////code fot  breadcrumbs end here //////////
/////////////langugage defined start here///////
const i18n = inject('i18n');
console.log("i18n", i18n);
/////////////langugage defined end here///////

///////////////filter and download start here //////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
console.log("selectPageAndFilterAndDownloadheader",selectPageAndFilterAndDownload);
//////////////filter and download end here/////////



const submitcontact = async () => {
      
      showPopupcontactSuccess.value = true;
      console.log("worked");
      

}
</script>





