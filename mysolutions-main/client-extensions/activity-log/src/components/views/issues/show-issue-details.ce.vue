<template >
  <div class="addScroll paginationDown">

    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('PROJECT_ISSUES.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_ISSUES.PRIORITY') }}</th>
          <th>{{ i18n.global.t('PROJECT_ISSUES.TITLE_&_ID') }}</th>
          <th>{{ i18n.global.t('PROJECT_ISSUES.REQUESTED_BY') }}</th>
          <th>{{ i18n.global.t('PROJECT_ISSUES.RAISED_ON') }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="issueDataExcel && issueDataExcel"
                fileName="Issues" style="    
	   width: 35px;
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

      <tbody v-if="issueData != null && issueData.length > 0 && issueData != 'Success' && issueData != 'N/A'">

        <tr v-for="item in paginatedData" :key="item.id">
          <td><span :class="'badge rounded-pill font-10 ' + applyColorToStatus(item.status.split(')')[1])"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.status ?
        item.status.split(")")[1] : 'N/A' }}</span></td>




          <td class="font-weight-800" :class="'' +  applyColorToStatus(item?.priority?.split(')')[1])">{{ item.priority ?
            item.priority.split(")")[1] : 'N/A' }}<br /></td>


          <td data-toggle="tooltip" data-placement="bottom" :title="item.title">{{ item.title ?
            item.title.length>30 ? item.title.substring(0, 25)+ '...' : item.title : 'N/A' }}<br /><span class="font-weight-400">STCS - {{ item.issueID ?
    item.issueID : 'N/A' }}</span></td>
          <td>{{ item.owner ?
            item.owner : 'N/A' }}<br /><span class="font-weight-400">STCS - {{ item.category ?
    item.category : 'N/A' }}</span></td>
          <td>{{ item.creationDate != null ? ChangeDateFormat(item.creationDate) : 'N/A' }}</td>

          <td data-toggle="tooltip" data-placement="bottom" title="Click to view details"><a
              href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" class="img-flip" alt="img"
                @click="toggleSidebar(item)" /></a>
          </td>
        </tr>
        <tr>
          <td colspan="6">
            <!-- <pagination :totalItems="issueData.length" :itemsPerPage="itemsPerPage" v-model:currentPage="currentPage" /> -->
            <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:issueData?.length"
      @current-change="handleCurrentChange"
      v-if="issueData?.length>1"
    >
    </el-pagination>
          </td>
        </tr>
      </tbody>
      <tbody v-else-if="issueData != null && issueData.length == 0 && issueData != 'Success' && issueData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="issueData == 'Success' || issueData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_ISSUES._THAT'S_GREAT!`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="issueData != null && issueData.key == 'server error'  ">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="issueData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- sidebar starts  -->
    <!-- <pagination :totalItems="issueData && issueData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->

    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
          <!-- <div class="sd-header">
          <h5 class="mb-0">{{  i18n.global.t('PROJECT_ISSUE_DETAILS.TITLE')}}</h5>
        
            <i ><img data-toggle="tooltip" data-placement="bottom" title="Close" :src="'/documents/d/mysolutions/cross'" style="scale: 0.7;" class="ml-1 w-15p" alt="img"  @click="toggleSidebar(null)"/></i>
      
        </div> -->
          <div class="" style="overflow:auto:" v-if="isOpenSidebar && issueID != null">
            <ShowIssueDetailsID :id="issueID">
            </ShowIssueDetailsID>
          </div>



        </div>
        <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
      </div>
    </div>
    <!-- sidebar ends  -->
  </div>
   <!-- sidebar starts Filter -->
   <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebarFilter }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
            <showIssusesFilterCeVue :ProjectsDataPass="issueData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, ref, watch, computed, provide } from 'vue';
import { ChangeDateFormat, applyColorToStatus, makeRequest,downloadExcel } from '../../../utils/utils';

import ProjectTabs from "../project-tabs.ce.vue"
import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import ShowIssueDetailsID from './show-issue-detailsID.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';
import showIssusesFilterCeVue from './show-issuses-filter.ce.vue';

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////  



const showHideHeader = ref(true);


const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

provide('toggleSidebar', toggleSidebar)

const showDownloadExcelbtn = ref(false);


const props = defineProps({
  hostUrl: { type: Object, required: true }

})

const dropdownOptions = ["All", 'resolved', 'Escalated', 'Open'];


const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');



const userList = inject('userList');



const issueData = ref(null);
const issueDataExcel = ref(null);

const issuseWatch = (val) => {
  
  const issueValue = val;

  if (issueValue.status >= 200 && issueValue.status < 300 && issueValue.message == "ok") {
    if ( issueValue.payload.message == "Success") {
      console.log("inside success")
      issueData.value = "Success";
      return;
    }
    else if ( Object.keys(issueValue.payload).length === 0) {
      console.log("inside {}")
      issueData.value = 'N/A';
      return;

    }
    else if ( issueValue.payload.issues && issueValue.payload.issues.length > 0) {
      console.log(issueValue.payload.risks);
      issueData.value = issueValue.payload.issues;
      const formattedData = computed(() => issueData.value.map(issue => excelSheetFormate(issue)));
      issueDataExcel.value = formattedData.value;
      updateDownloadFilter({value:issueData?.value?.length,"type":"totalcountDownload"})
      showDownloadExcelbtn.value = true;
      console.log(issueData.value.length)
      return;

    }
    else {

console.log("inside main error")
issueData.value = { key: "server error", message: issueValue.message };
return;

}

  }

  else {

    console.log("inside main error")
    issueData.value = { key: "server error", message: issueValue.message };
    return;

  }

}


const excelSheetFormate = (val) => {
  const keyMappings = {
    issueID: "Issue ID",
    title: "Title",
    status: "Status",
    assignedTo: "Assigned To",
    category: "Category",
    closingDate: "Closing Date",
    creationDate: "Creation Date",
    createdBy: "Created By",
    dependency: "Dependency",
    discussion: "Discussion",
    dueDate: "Due Date",
    escalationLevel: "Escalation Level",
    issueAge: "Issue Age",
    issueProgressHistory: "Issue Progress History",
    modificationDate: "Modification Date",
    modifiedBy: "Modified By",
    originalDueDate: "Original Due Date",
    owner: "Owner",
    priority: "Priority",
    priorityDescription: "Priority Description",
    raisedBy: "Raised By",
    resolution: "Resolution",
    showToENDCustomer: "Show To End Customer",
    showToTheCustomer: "Show To The Customer",
    showToTheExecutive: "Show To The Executive",
    updates: "Updates",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "closingDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "creationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "modificationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "originalDueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else {
      updatedJsonData[newHeader] = value;
    }

  }
  return updatedJsonData;
};

///////////Pagnation start here///////
const pageSizeNum = inject('pageSizeNum');
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):issueData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////

watch(userList, (newValue, oldValue) => {
  if (newValue) {
    issuseWatch(newValue);
  }
})

console.log(userList)

const isOpenSidebar = ref(false);
const isOpenSidebarFilter = ref(false);
const issueID = ref(null);

function toggleSidebar(issueIdGet) {
  console.log("hy")
  isOpenSidebar.value = !isOpenSidebar.value;
  issueID.value = issueIdGet.issueID;
};


const ProjectApiId = inject("ProjectApiId");

///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if(newValue?.downloadAble && newValue?.page=="issuse")
  {
    downloadExcel (ProjectApiId.value,issueDataExcel.value,'Issues');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="issuse")
  {
    
    console.log("newValueiiiii",newValue);
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="issuse"){
    issuseWatch(userList.value);
  }
})


const toggleSidebarFilter=()=> {
  isOpenSidebarFilter.value = !isOpenSidebarFilter.value;
  };
  provide('toggleSidebarFilter', toggleSidebarFilter);

const updatefilterFun=(filter)=>{
  let Projectslistvalue = issueData.value.filter((item) => {
  return Object.values(filter).includes(item.status);
  });
  filteredData.value=Projectslistvalue;
}

//////////////filter and download end here ////////









</script>