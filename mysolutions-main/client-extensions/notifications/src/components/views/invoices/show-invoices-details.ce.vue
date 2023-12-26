<template >
  <div class="addScroll paginationDown">

    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('PROJECT_INVOICES.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_INVOICES.REF_ID') }}</th>
          <th>{{ i18n.global.t('PROJECT_INVOICES.INVOICE_VALUE(SAR)') }}</th>
          <th>{{ i18n.global.t('PROJECT_INVOICES.ADDED_BY') }}</th>
          <th>{{ i18n.global.t('PROJECT_INVOICES.DUE_DATE') }}</th>
          <th>{{ i18n.global.t('PROJECT_INVOICES.ADDED_ON') }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="selectInvoiceData && selectInvoiceData"
                fileName="Invoices" style="    
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

      <tbody v-if="invoiceData != null && invoiceData.length > 0 && invoiceData != 'Success' && invoiceData != 'N/A'">

        <tr v-for="item in paginatedData" :key="item.id">
          <!-- <td><span class="badge rounded-pill bg-light-success text-dark font-10"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.projectNumber ?
          item.accountNumber : 'N/A' }}</span></td> -->
          <!-- <td>{{ item.accountNumber ?
            item.projectNumber : 'N/A' }}</td> -->
          <td><span :class="'badge rounded-pill font-10 ' + (
            item.status === 'Cancelled'
              ? ' StatusCanceled '
              : 'StatusCanceled'

          )"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{
  item.status == 'CL' ? 'Cancelled' : 'N/A' }}</span></td>
          <td>{{ item.trxNumber ?
            item.trxNumber : 'N/A' }}</td>
          <td>{{ item.amountApplied ?
            formatCurrency(item.amountApplied, i18n.global.locale == "ar_SA" ? 'SAR' : 'en-US') : 'N/A' }}</td>
          <td>{{ item.collectorName ? item.collectorName : 'N/A' }}<br /></td>
          <td>{{ item.dueDate ? ChangeDateFormat(item.dueDate) : 'N/A' }}</td>
          <td>{{ item.dueDate ? ChangeDateFormat(item.lastUpdateDate) : 'N/A' }}</td>
          <td data-toggle="tooltip" data-placement="bottom" title="Click to view details"><a
              href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" alt="img" class="img-flip"
                @click="toggleSidebar(item)" /></a>
          </td>
        </tr>

      </tbody>
      <tbody v-else-if="invoiceData != null && invoiceData.length == 0 && invoiceData != 'Success' && invoiceData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="invoiceData == 'Success' || invoiceData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('HELLO')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_THERE_ARE_NO_INVOICES_ASSOCIATED_WITH_THIS_PROJECT_NOW._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="invoiceData != null && invoiceData.key == 'server error'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>
      <tbody v-else-if="invoiceData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <pagination :totalItems="invoiceData && invoiceData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:invoiceData?.length"
      @current-change="handleCurrentChange"
      v-if="filteredData!=null?filteredData.length>pageSizeNum:invoiceData?.length>pageSizeNum"
    >
    </el-pagination>

    <!-- sidebar starts  -->
    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
         
          <div class="" style="overflow:auto:" v-if="isOpenSidebar && invoiceID != null">
            <ShowInvoicesDetailsID :id="invoiceID">
            </ShowInvoicesDetailsID>
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
            <invoicesFilterCeVue :ProjectsDataPass="invoiceData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, ref, watch, computed, provide } from 'vue';
import { ChangeDateFormat, formatCurrency,downloadExcel } from '../../../utils/utils';
import ProjectTabs from "../project-tabs.ce.vue"
import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import ShowRisksDetailsID from './show-invoices-detailsID.ce.vue';
import ShowInvoicesDetailsID from './show-invoices-detailsID.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';
import invoicesFilterCeVue from './invoices-filter.ce.vue';

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////  

//injecting page changing button///
const showHideHeader = ref(true);


const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

const ChangePage = inject("ChangePage");


//injecting page changing button ends///

const showDownloadExcelbtn = ref(false);
provide('toggleSidebar', toggleSidebar)


const props = defineProps({
  hostUrl: { type: Object, required: true }

})

const dropdownOptions = ["All", 'resolved', 'Escalated', 'Open'];

const userList = inject('userList');



const invoiceData = ref(null);
const selectInvoiceData = ref(null);

const excelSheetFormate = (val) => {
  const keyMappings = {
    projectNumber: "Project Number",
    amountApplied: "Amount Applied",
    collectorName: "Collector Name",
    createdBy: "Created By",
    dueDate: "Due Date",
    lastUpdateDate: "Last Update Date",
    accountNumber: "Account Number",
    oppNumber: "Opp Number",
    poNumber: "Po Number",
    paymentTerms: "Payment Terms",
    trxNumber: "Trx Number",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "createdBy") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "lastUpdateDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "amountApplied") {
      updatedJsonData[newHeader] = value ? formatCurrency(value, i18n.global.locale == "ar_SA" ? 'SAR' : 'en-US') : '';
    }
    else {
      updatedJsonData[newHeader] = value;
    }

  }
  return updatedJsonData;
};

const invoiceWatch = (val) => {
  const invoiceValue = val;
  console.log(invoiceValue);

  if (invoiceValue.status >= 200 && invoiceValue.status < 300  && invoiceValue.message == "ok") {
    if (invoiceValue.payload.message == "Success") {
      console.log("inside success")
      invoiceData.value = "Success";
      return;
    }
    else if (Object.keys(invoiceValue.payload).length === 0) {
      console.log("inside {}")
      invoiceData.value = 'N/A';
      return;

    }
    else if (invoiceValue.payload.InvoiceList && invoiceValue.payload.InvoiceList
      .length > 0) {
      console.log("inside invoice success", invoiceValue.payload.InvoiceList);
      invoiceData.value = invoiceValue.payload.InvoiceList;
      const formattedData = computed(() => invoiceData.value.map(invoice => excelSheetFormate(invoice)));
      selectInvoiceData.value = formattedData.value;
      showDownloadExcelbtn.value = true;
      updateDownloadFilter({value:selectInvoiceData?.value?.length,"type":"totalcountDownload"})
      return;

    }
    else {
    invoiceData.value = { key: "server error", message: invoiceValue.message };
    return;

  }

  }

  else {
    invoiceData.value = { key: "server error", message: invoiceValue.message };
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
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):invoiceData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////



watch(userList, (newValue, oldValue) => {
  if (newValue) {
    invoiceWatch(newValue);
  }
})

const isOpenSidebar = ref(false);
const invoiceID = ref(null);

function toggleSidebar(invoiceIdGet) {
  console.log("hy")
  isOpenSidebar.value = !isOpenSidebar.value;
  invoiceID.value = invoiceIdGet.trxNumber;
};


const ProjectApiId = inject("ProjectApiId");

///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  console.log("newValueginvoice",newValue);
  if(newValue?.downloadAble && newValue?.page=="invoice")
  {
    downloadExcel (ProjectApiId.value,selectInvoiceData.value,'Invoices');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="invoice")
  {
    console.log("newValueiiiii",newValue);
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="invoice"){
    invoiceWatch(userList.value);
  }
})

const isOpenSidebarFilter = ref(false);
const toggleSidebarFilter=()=> {
  isOpenSidebarFilter .value = !isOpenSidebarFilter.value;
  };
  provide('toggleSidebarFilter', toggleSidebarFilter);

const updatefilterFun=(filter)=>{
  let Projectslistvalue = invoiceData.value.filter((item) => {
  return Object.values(filter).includes(item.status);
  });
  filteredData.value=Projectslistvalue;
}

//////////////filter and download end here ////////

</script>