
<script setup>
import { inject, provide, ref, watch, computed } from 'vue';
import { ChangeDateFormat, makeRequest,downloadExcel,convertFileToViewableFormat } from '../../../utils/utils';

import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';

import showDocumentsFilterCeVue from './show-documents-filter.ce.vue';

import LoadingPopup from '../../global/LoadingPopup.ce.vue';


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

const showHideHeader = ref(true);

const downloadingLoader=ref(false);


const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

const showDownloadExcelbtn = ref(false);


const props = defineProps({
  hostUrl: { type: Object, required: true }

})

const dropdownOptions = ["All", 'Approved', 'Rejected'];





const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');
//const userList = inject('userList');
const ProjectApiId = inject("ProjectApiId");

const userListData = inject('userList');
const userArray = [...userListData.value];



const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);


function toggleDropdown() {
  isOpen.value = !isOpen.value;
}

async function selectOption(option) {
  selectedOption.value = option;

  let newUrl;
  newUrl = props.hostUrl + ` and documentStatus eq '${option}'`;

  if (option == "All") {
    newUrl = props.hostUrl;
    console.log("heyy")
  }
  const response = await makeRequest(newUrl);
  userList.value = response.items;
  console.log(option)

  isOpen.value = false;
}



const DocumentData = ref(null);



const docDataFun = (val) => {
  DocumentData.value = val;
  console.log(DocumentData.value)

  if (DocumentData.value.status >= 200 && DocumentData.value.status < 300   && DocumentData.value.message == "ok" ) {
    if ( DocumentData.value.payload.message == "Success") {
      DocumentData.value = "Success";
      return;
    }
    else if ( Object.keys(DocumentData.value.payload).length === 0) {
      console.log("inside {}")
      DocumentData.value = 'N/A';
      return;

    }
    else if (DocumentData.value.payload.documents && DocumentData.value.payload.documents.length > 0) {
      DocumentData.value = DocumentData.value.payload.documents;
      showDownloadExcelbtn.value = true;
      updateDownloadFilter({value:DocumentData?.value?.length,"type":"totalcountDownload"})
      return;
    }
    else {
    console.log("inside main error")
    DocumentData.value = { key: "server error", message: DocumentData.message };
    console.log(DocumentData.value)
    return;

  }

  }

  else {
    console.log("inside main error")
    DocumentData.value = { key: "server error", message: DocumentData.message };
    console.log(DocumentData.value)
    return;

  }
}

///////////Pagnation start here///////
const pageSizeNum = inject('pageSizeNum');
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):DocumentData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////


const HandleFileDownload=()=>
{
  convertFileToViewableFormat(downloadingLoader)
}


watch(userListData, (newValue, oldValue) => {
  if (newValue) {
    docDataFun(newValue);
  }
});





///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  console.log("newValuedocument",newValue);
  if(newValue?.downloadAble && newValue?.page=="document")
  {
    downloadExcel (ProjectApiId.value,DocumentData.value,'Documents');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="document")
  {
    
    console.log("newValueiiiii",newValue);
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="document"){
    docDataFun(userListData.value);
  }
})

const isOpenSidebarFilter = ref(false);
const toggleSidebarFilter=()=> {
  isOpenSidebarFilter .value = !isOpenSidebarFilter.value;
  };
  provide('toggleSidebarFilter', toggleSidebarFilter);

const updatefilterFun=(filter)=>{
  let Projectslistvalue = DocumentData.value.filter((item) => {
  return Object.values(filter).includes(item.createdBy);
  });
  filteredData.value=Projectslistvalue;
}

//////////////filter and download end here ////////





</script>







<template>
  
  <div class="addScroll paginationDown">
    <LoadingPopup v-if="downloadingLoader"/>
    <!-- <div class="beautiful-dropdown" style="position: absolute; right:65px;">
    <a  @click="toggleDropdown" href="javascript:void(0)" class="btn dropdown-button btn-primary align-center rounded-10"><img
        :src="'/documents/d/mysolutions/filter-white" alt="img" /></a>
 
  <ul v-if="isOpen" class="dropdown-list">
    <li v-for="option in dropdownOptions" :key="option" @click="selectOption(option)">
        {{ option }}
        <hr>
    </li>
  </ul>

</div> -->
    <!-- <DownloadToExcel style="position: absolute;
right:20px;" :data="DocumentData && DocumentData" fileName="Documents" /> -->
    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          
          <th>{{ i18n.global.t('DOCUMENTS.TITLE_&_ID') }}</th>
          <th>{{ i18n.global.t('DOCUMENTS.ADDED_BY') }}</th>
          <th>{{ i18n.global.t('DOCUMENTS.ADDED_ON') }}</th>
          <!-- <th>Added by</th> -->
          <!-- <th>Added on</th> -->
          <!-- <th>{{ i18n.global.t('DOCUMENTS.LINK')}}</th> -->
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :data="DocumentData && DocumentData" fileName="Documents" style="width: 35px;
            position: absolute;
            top: 2px;
            right: 9px;
            /* scale: 0.2; */
            height: 35px;
          " />
            </div> -->
          </th>
        </tr>
      </thead>
      <tbody v-if="DocumentData != null && DocumentData.length > 0 && DocumentData != 'Success' && DocumentData != 'N/A'">
        <tr v-for="item in paginatedData" :key="item.id">
          <!-- <td><span class="badge rounded-pill bg-light-success text-dark font-10"><i
                class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.documentStatus ? item.documentStatus : 'N/A' }}</span></td> -->

       
          <td data-toggle="tooltip" data-placement="bottom" :title="item.documentName">{{ item.documentName ?  item.documentName.length>30 ? item.documentName.substring(0, 25)+ '...' : item.documentName: 'N/A' }}<br /><span class="font-weight-400">STCS - {{ item.tpID ?
            item.tpID : 'N/A' }}</span></td>
          <td>{{ item.createdBy ?
            item.createdBy : 'N/A' }}<br /></td>
          <td>{{ item.creationDate != null ? ChangeDateFormat(item.creationDate) : 'N/A' }}</td>
          <!-- <td>{{ item.creator.name }}<br /><span class="font-weight-400">STCS - {{ item.creator.contentType }}</span></td> -->
          <!-- <td>{{ ChangeDateFormat(item.dateCreated) }}</td> -->
          <td data-toggle="tooltip" data-placement="bottom" title="Click to view details"><a target="_blank"
              ><img
                :src="'/documents/d/mysolutions/arrow-up '" @click="HandleFileDownload()" class="img-flip" alt="img" /></a></td>
        </tr>


      </tbody>

      <tbody v-else-if="DocumentData == 'Success' || DocumentData == 'N/A'">
        <tr>
          {{ console.log("inside success") }}
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('HELLO')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_THERE_ARE_NO_DOCUMENTS_ASSOCIATED_WITH_THIS_PROJECT_YET._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="DocumentData != null && DocumentData.key == 'server error'">
        <tr>
          {{ console.log("inside error") }}
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>
      <tbody v-else-if="DocumentData == null">
        <tr>
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>

    <!-- <pagination :totalItems="DocumentData && DocumentData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:DocumentData?.length"
      @current-change="handleCurrentChange"
      v-if="filteredData!=null?filteredData.length>pageSizeNum:DocumentData?.length>pageSizeNum"
    >
    </el-pagination>

  </div>
   <!-- sidebar starts Filter -->
   <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebarFilter }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
            <showDocumentsFilterCeVue :ProjectsDataPass="DocumentData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


