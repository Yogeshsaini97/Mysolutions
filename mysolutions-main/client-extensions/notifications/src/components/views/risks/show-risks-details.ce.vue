<template >
  <div class="addScroll paginationDown">

    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('PROJECT_RISKS.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_RISKS.PRIORITY') }}</th>
          <th>{{ i18n.global.t('PROJECT_RISKS.TITLE_&_ID') }}</th>
          <th>{{ i18n.global.t('PROJECT_RISKS.REPORTED_BY') }}</th>
          <th>{{ i18n.global.t('PROJECT_RISKS.RAISED_ON') }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="riskDataExcel && riskDataExcel" fileName="Risks"
                style="    
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

      <tbody v-if="riskData != null && riskData.length > 0 && riskData != 'Success' && riskData != 'N/A'">
        <tr v-for="item in paginatedData" :key="item.id">
          <td><span :class="'badge rounded-pill font-10 ' + applyColorToStatus(item?.status?.split(')')[1])"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.status ?
        item?.status?.split(")")[1] : 'N/A' }}</span></td>
          <td class="font-weight-800" :class="'' +  applyColorToStatus(item?.priority?.split(')')[1])">{{ item?.priority ?
            item?.priority?.split(")")[1] : 'N/A' }}<br /></td>


          <td>{{ item.title ?
            item.title : 'N/A' }}<br /><span class="font-weight-400">STCS - {{ item.riskID ?
    item.riskID : 'N/A' }}</span></td>
          <td>{{ item.owner ?
            item.owner : 'N/A' }}<br /><span class="font-weight-400">STCS - {{ item.category ?
    item.category : 'N/A' }}</span></td>
          <td>{{ item.dateRaised != null ? ChangeDateFormat(item.dateRaised) : 'N/A' }}</td>

          <td data-toggle="tooltip" data-placement="bottom" title="Click to view details"><a
              href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" class="img-flip" alt="img"
                @click="toggleSidebar(item)" /></a>
          </td>
        </tr>
        <tr>
          <td colspan="6">
            <!-- <pagination :totalItems="riskData.length" :itemsPerPage="itemsPerPage" v-model:currentPage="currentPage" /> -->
          </td>
        </tr>
      </tbody>
      <tbody v-else-if="riskData != null && riskData.length == 0 && riskData != 'Success' && riskData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="riskData == 'Success' || riskData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_RISKS._THAT'S_GREAT!`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="riskData != null && riskData.key == 'server error'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>
      <tbody v-else-if="riskData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <pagination :totalItems="riskData && riskData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:riskData?.length"
      @current-change="handleCurrentChange"
      v-if="riskData?.length>1"
    >
    </el-pagination>
    <!-- sidebar starts  -->
    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
      
          <div class="" style="    overflow: auto;" v-if="isOpenSidebar && riskID != null">
            <ShowRisksDetailsID :id="riskID">
            </ShowRisksDetailsID>
          </div>



        </div>
        <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
      </div>
    </div>
    <!-- sidebar ends  -->

     <!-- sidebar starts Filter -->
   <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebarFilter }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
            <showRisksFilterCeVue :ProjectsDataPass="riskData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
  </div>
</template>


<script setup>
import { inject, ref, watch, computed, provide } from 'vue';
import { ChangeDateFormat, applyColorToStatus, makeRequest ,downloadExcel} from '../../../utils/utils';
import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';

import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import ShowRisksDetailsID from './show-risks-detailsID.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';
import showRisksFilterCeVue from './show-risks-filter.ce.vue';
const showHideHeader = ref(true);


const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
provide('toggleSidebar', toggleSidebar)

const showDownloadExcelbtn = ref(false);



const props = defineProps({
  hostUrl: { type: Object, required: true }

})

const dropdownOptions = ["All", 'resolved', 'Escalated', 'Open'];


const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');



const userList = inject('userList');



const riskData = ref(null);
const riskDataExcel = ref(null);
const riskWatch = (val) => {
  const riskValue = val;

  if (riskValue.status >= 200 && riskValue.status < 300 && riskValue.message == "ok") {
    if ( riskValue.payload.message == "Success") {
      console.log("inside success")
      riskData.value = "Success";
      return;
    }
    else if ( Object.keys(riskValue.payload).length === 0) {
      console.log("inside {}")
      riskData.value = 'N/A';
      return;

    }
    else if ( riskValue.payload.risks && riskValue.payload.risks.length > 0) {
      console.log(riskValue.payload.risks);
      riskData.value = riskValue.payload.risks;
      const formattedData = computed(() => riskData.value.map(risk => excelSheetFormate(risk)));
      riskDataExcel.value = formattedData.value;
      updateDownloadFilter({value:riskData?.value?.length,"type":"totalcountDownload"})
      showDownloadExcelbtn.value = true;
      console.log(riskData.value.length)
      return;

    }
    else {

console.log("inside main error")
riskData.value = { key: "server error", message: riskValue.message };
return;

}

  }

  else {

    console.log("inside main error")
    riskData.value = { key: "server error", message: riskValue.message };
    return;

  }
}


const excelSheetFormate = (val) => {
  const keyMappings = {
    riskID: "Risk ID",
    priority: "Priority",
    projectID: "Project ID",
    assignedTo: "Assigned To",
    category: "Category",
    closingDate: "Closing Date",
    contingencyPlan: "Contingency Plan",
    created: "Created",
    createdBy: "Created By",
    dateRaised: "Date Raised",
    description: "Description",
    title: "Title",
    dueDate: "Due Date",
    escalationLevel: "Escalation Level",
    impactedArea: "Impacted Area",
    latestUpdate: "Latest Update",
    mitigationPlan: "Mitigation Plan",
    modified: "Modified",
    originalDueDate: "Original Due Date",
    owner: "Owner",
    phase: "Phase",
    age: "Age",
    probability: "Probability",
    riskCostExposure: "Risk Cost Exposure",
    impact: "Impact",
    responseStrategy: "Response Strategy",
    severity: "Severity",
    severityDescription: "Severity Description",
    status: "Status",
    showToTheCustomer: "Show To The Customer",
    showToTheExecutive: "Show To The Executive",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "closingDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "created") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "dateRaised") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "modified") {
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
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):riskData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////

watch(userList, (newValue, oldValue) => {
  if (newValue) {
    riskWatch(newValue);
  }
})

const isOpenSidebar = ref(false);
const isOpenSidebarFilter = ref(false);
const riskID = ref(null);

function toggleSidebar(riskIdGet) {
  isOpenSidebar.value = !isOpenSidebar.value;
  riskID.value = riskIdGet.riskID;
};


const ProjectApiId = inject("ProjectApiId");


///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if(newValue?.downloadAble && newValue?.page=="risk")
  {
    downloadExcel (ProjectApiId.value,riskDataExcel.value,'Risks');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="risk")
  {
    
    console.log("newValueiiiii",newValue);
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="risk"){
    riskWatch(userList.value);
  }
})

const toggleSidebarFilter=()=> {
  isOpenSidebarFilter .value = !isOpenSidebarFilter.value;
  };
  provide('toggleSidebarFilter', toggleSidebarFilter);

const updatefilterFun=(filter)=>{
  let Projectslistvalue = riskData.value.filter((item) => {
  return Object.values(filter).includes(item.status);
  });
  filteredData.value=Projectslistvalue;
}

//////////////filter and download end here ////////








</script>