<template>


  <!-- ----------------   -->
  <header ref="header" :class="{ 'sticky': isSticky }" id="myHeader" class="breadcrumb bg-default text-white pt-3">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../assets/images/favicon.ico" type="image">
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="../assets/css/style.css" rel="stylesheet">
    <div class="container-fluid">
      <div class="row" style="align-items: flex-start;justify-content: space-between;">
        <div class="backbuttonfix d-flex">
          <a href="javascript:void(0)" class="btn btn-primary align-center rounded-10"
            @click="BackButton(ProjectApiId)"><img class="img-fluid" :src="'/documents/d/mysolutions/angle-right-white'"
              alt="img" /></a>
          <div class="ml-3">
            <h4 data-toggle="tooltip" data-placement="bottom" :title="  breadcrumbs[1] ? breadcrumbs[1].label : i18n.global.t('CASE_LIST') "  class="mb-0 font-weight-600">{{
              breadcrumbs[1] ? (breadcrumbs[1].label.length>30 ? breadcrumbs[1].label.substring(0, 30)+ '...' : breadcrumbs[1].label) : i18n.global.t('CASE_LIST') }}</h4>

            <p class="breadcrumbmain mb-0 font-10">
              <li data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DASHBOARD')" class="mx-1 opacity-60"> <a style="text-decoration: none;
                color: white;" href="./dashboard">{{ i18n.global.t('DASHBOARD') }}</a></li>
              <li  v-for="(item, index) in breadcrumbs" :key="index">
                <a style="    text-decoration: none;
               color: white;" :href="item.Link"> <span> <img :src="'/documents/d/mysolutions/angle-right'"
                      class="mx-1 opacity-60 img-flip" alt="img" />
                      
                   <span v-if="item.label!='Projects'" > {{ item.label.length>30 ? item.label.substring(0, 30)+ '...' : item.label }}</span> <span v-else data-toggle="tooltip" data-placement="bottom" title="Click to view Projects" >{{  i18n.global.t(item.label) }} </span></span></a>
                  
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
          <a  data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CREATE_A_CASE')" type="button"  class="plusbtnrtl btn btn-primary align-center rounded-10 " href="./create-case" ><img :src="'/documents/d/mysolutions/plus'" alt="img"/></a>
          <a type="button" style="position:relative;" class="btn btn-primary align-center rounded-10 ml-2" :title="i18n.global.t('FILTER')"  v-if="selectPageAndFilterAndDownload.download > 0 && selectPageAndFilterAndDownload.page!='no'">
            <span v-if="filterValue" class="badge rounded-circle red-circle-filter">{{  filterValue?filterValue:null }} </span>
            <img :src="'/documents/d/mysolutions/filter-white'" alt="img" @click="updateDownloadFilter({'value':true,'type':'filter'})"  /></a>
          <a data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('DOWNLOAD_EXCEL')" type="button" class="btn btn-primary align-center rounded-10 ml-2"  v-if="selectPageAndFilterAndDownload.download > 0 && selectPageAndFilterAndDownload.page!='no'"><img style="scale:0.8" :src="'/documents/d/mysolutions/download_excel-1'" alt="img" @click="updateDownloadFilter({'value':true,'type':'download'})"  /></a>
         
          <a  class="btn btn-light align-center rounded-10 ml-2 text-default"><img class="mr-1"
              :src="'/documents/d/mysolutions/headset'" alt="img" />
              <div  @click ="submitcontact" class="talk">{{ i18n.global.t('TALK_TO_US') }}</div><img class="ml-1" :src="'/documents/d/mysolutions/angle-right'" alt="img" />
        </a>
        </div>
      </div>
    </div>
    <LoadingTalktoUs v-if="showPopupcontactSuccess"/>
  </header>
</template>

<script setup>
import { inject, onMounted, onUnmounted, ref,provide,watch } from 'vue';
import ProgressBarCe from './progress-bar.ce.vue';
import LoadingTalktoUs from './LoadingTalktoUs.ce.vue';
import {countNonNullAllProperties}  from '../../utils/utils'

console.log(window.location.origin)




const ProjectApiId = inject("ProjectApiId");
const CurrentPage = inject('CurrentPage');
const ChangePage = inject('ChangePage');



const showPopupcontactSuccess = ref(false);
provide('showPopupcontactSuccess', showPopupcontactSuccess)
///////////////code for progress bar setting inject  start here ///////////

const header = ref(null);
    const isSticky = ref(false);

    // const handleScroll = () => {
    //   if (window.pageYOffset > header.value.offsetTop) {
    //     isSticky.value = true;
    //     console.log("inside scroll ")
    //   } else {
    //     isSticky.value = false;
    //     console.log("inside not scroll ")
    //   }
    // };

    // onMounted(() => {
    //   window.addEventListener('scroll', handleScroll);
    // });

    // onUnmounted(() => {
    //   window.removeEventListener('scroll', handleScroll);
    // });



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
const filterValue = ref(null);
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
console.log("selectPageAndFilterAndDownloadheader",selectPageAndFilterAndDownload);

watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if (newValue?.filterCount) {
    filterValue.value = countNonNullAllProperties(newValue?.filterCount);
  }
  if (newValue?.filterCount==null) {
    filterValue.value = null;
  }
});
//////////////filter and download end here/////////


const submitcontact = async () => {
      
      showPopupcontactSuccess.value = true;
      console.log("worked");
      

}
</script>





