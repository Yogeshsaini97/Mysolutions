<template >
  <div class="addScroll paginationDown">
    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t("PROJECT_RISKS.STATUS") }}</th>
          <th>{{ i18n.global.t("PROJECT_RISKS.PRIORITY") }}</th>
          <th>{{ i18n.global.t("PROJECT_RISKS.TITLE_&_ID") }}</th>
          <th>{{ i18n.global.t("PROJECT_RISKS.REPORTED_BY") }}</th>
          <th>{{ i18n.global.t("PROJECT_RISKS.RAISED_ON") }}</th>
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
         (
            item.status === 'CL'
              ? ' StatusCanceled '
               
              : item.status === 'OP'
              ? ' StatusOpen '
              : 'StatusCanceled'

          )"
          applyColorToStatus(item?.status)
            </div> -->
          </th>
        </tr>
      </thead>

      <tbody
        v-if="
          riskData != null &&
          riskData.length > 0 &&
          riskData != 'Success' &&
          riskData != 'N/A'
        "
      >
        <tr
          v-for="item in paginatedData"
          :key="item.id"
          v-if="paginatedData.length > 0"
        >
          <td>
            <span
              :class="
                'badge rounded-pill font-10 ' + applyColorToStatus(item?.status)
              "
              ><i class="fa-solid fa-circle font-5 align-middle mr-2"></i
              >{{ item.status ? item?.status : i18n.global.t("N/A") }}</span
            >
          </td>
          <td
            class="font-weight-800"
            :class="'' + applyColorToStatustext(item?.priority)"
          >
            {{ item?.priority ? item?.priority : i18n.global.t("N/A") }}<br />
          </td>

          <td data-toggle="tooltip" data-placement="bottom" :title="item.title">
            {{
              item.title
                ? item.title > 30
                  ? item.title.substring(0, 25) + "..."
                  : item.title
                : i18n.global.t("N/A")
            }}<br /><span class="font-weight-400"
              >{{ i18n.global.t("ID") }} -
              {{ item.riskID ? item.riskID : i18n.global.t("N/A") }}</span
            >
          </td>
          <td>
            {{ item.owner ? item.owner : "N/A" }}<br /><span
              class="font-weight-400"
              >{{ item.category ? item.category : "N/A" }}</span
            >
          </td>
          <td>
            {{
              item.dateRaised != null
                ? ChangeDateFormat(item.dateRaised)
                : "N/A"
            }}
          </td>

          <td
            data-toggle="tooltip"
            data-placement="bottom"
            :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"
          >
            <a href="javascript:void(0)"
              ><img
                :src="'/documents/d/mysolutions/arrow-up '"
                class="img-flip"
                alt="img"
                @click="toggleSidebar(item)"
            /></a>
          </td>
        </tr>
        <tr v-if="paginatedData.length === 0">
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="
              i18n.global.t(
                `HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_RISKS._THAT'S_GREAT!`
              )
            "
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>
      <tbody
        v-else-if="
          riskData != null &&
          riskData.length == 0 &&
          riskData != 'Success' &&
          riskData != 'N/A'
        "
      >
        <tr>
          {{
            console.log("hyyyyy")
          }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="riskData == 'Success' || riskData == 'N/A'">
        <tr>
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="
              i18n.global.t(
                `HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_RISKS._THAT'S_GREAT!`
              )
            "
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>

      <tbody v-else-if="riskData != null && riskData.key == 'server error'">
        <tr>
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('OOPS')"
            :message="
              i18n.global.t(
                'AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER'
              )
            "
            btnmsg="contact admin"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>
      <tbody v-else-if="riskData == null">
        <tr>
          {{
            console.log("hyyyyy")
          }}
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
      :page-size="pageSizeNum"
      :total="filteredData != null ? filteredData.length : riskData?.length"
      @current-change="handleCurrentChange"
      v-if="
        (filteredData != null ? filteredData.length : riskData?.length) >
        pageSizeNum
      "
    >
    </el-pagination>
    <!-- sidebar starts  -->
    <div>
      <div class="container-fluid mt-3">
        <div :class="['sidebar', { active: isOpenSidebar }]">
          <div
            class=""
            style="overflow: auto"
            v-if="isOpenSidebar && riskID != null"
          >
            <ShowRisksDetailsID :id="riskID"> </ShowRisksDetailsID>
          </div>
        </div>
        <div
          class="sidebar-overlay"
          @click="toggleSidebar(null)"
          :class="{ active: isOpenSidebar }"
        ></div>
      </div>
    </div>
    <!-- sidebar ends  -->

    <!-- sidebar starts Filter -->
    <div>
      <div class="container-fluid mt-3">
        <div :class="['sidebar', { active: isOpenSidebarFilter }]">
          <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
            <showRisksFilterCeVue
              :ProjectsDataPass="riskData"
              @updatefilter="updatefilterFun"
            />
          </div>
        </div>
        <div
          class="sidebar-overlay"
          @click="toggleSidebarFilter(null)"
          :class="{ active: isOpenSidebarFilter }"
        ></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
  </div>
</template>


<script setup>
import { inject, ref, watch, computed, provide } from "vue";
import {
  ChangeDateFormat,
  applyColorToStatus,
  makeRequest,
  downloadExcel,
  applyColorToStatustext,
  trimString,
} from "../../../utils/utils";
import DownloadToExcel from "../../global/download-to-excel.ce.vue";
import Loader from "../../Global/Loader.ce.vue";

import ErrorMessageDialogBox from "../../Global/error-message-dialog-box.ce.vue";
import ShowRisksDetailsID from "./show-risks-detailsID.ce.vue";
import Pagination from "../../Global/Pagination.ce.vue";
import TableSkeletonLoader from "../../Global/table-skeleton-loader.ce.vue";
import showRisksFilterCeVue from "./show-risks-filter.ce.vue";
const showHideHeader = ref(true);

const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;
};

/////////////langugage defined start here///////
const i18n = inject("i18n");
/////////////langugage defined end here///////
provide("toggleSidebar", toggleSidebar);

const showDownloadExcelbtn = ref(false);

const props = defineProps({
  hostUrl: { type: Object, required: true },
});

const dropdownOptions = ["All", "resolved", "Escalated", "Open"];

const ChangePage = inject("ChangePage");
const breadcrumbs = inject("breadcrumbs");

const userList = inject("userList");

const riskData = ref(null);
const riskDataExcel = ref(null);
const riskWatch = (val) => {
  const riskValue = val;
  if (
    riskValue.status >= 200 &&
    riskValue.status < 300 &&
    riskValue.message == "ok"
  ) {
    if (riskValue.payload.message == "Success") {
      riskData.value = "Success";
      return;
    } else if (riskValue.payload.code == 404) {
      riskData.value = "Success";
      return;
    } else if (Object.keys(riskValue.payload).length === 0) {
      console.log("inside {}");
      riskData.value = i18n.global.t("N/A");
      return;
    } else if (riskValue.payload.risks && riskValue.payload.risks.length > 0) {
      riskData.value = riskValue.payload.risks;
      riskData.value.forEach((risk) => {
        let riskStatus = risk?.status?.split(")");
        let riskPriority = risk?.priority?.split(")");

        if (risk.status && riskStatus.length > 1) {
          risk.status = trimString(risk.status.split(")")[1]);
        }

        if (risk.priority && riskPriority.length > 1) {
          risk.priority = trimString(risk.priority.split(")")[1]);
        }

        if(risk.createdBy) {
          risk.reported_by = risk.createdBy
        }

        if(risk.dateRaised) {
          risk.created = risk.dateRaised
        }
        
      });

      const formattedData = computed(() =>
        riskData.value.map((risk) => excelSheetFormate(risk))
      );

      riskDataExcel.value = formattedData.value;
      updateDownloadFilter({
        value: riskData?.value?.length,
        type: "totalcountDownload",
      });

      showDownloadExcelbtn.value = true;

      return;
    } else {
      console.log("inside main error");
      riskData.value = { key: "server error", message: riskValue.message };
      return;
    }
  } else {
    console.log("inside main error");
    riskData.value = { key: "server error", message: riskValue.message };
    return;
  }
};

const excelSheetFormate = (val) => {
  val = {
    status: val?.status,
    priority: val?.priority,
    title: val?.title,
    modifiedBy: val?.reported_by,
    raisedOn: ChangeDateFormat(val?.created),
  };

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
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "created") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "dateRaised") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "modified") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "originalDueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else {
      updatedJsonData[newHeader] = value;
    }
  }
  return updatedJsonData;
};

///////////Pagnation start here///////
const pageSizeNum = inject("pageSizeNum");
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue =
    filteredData.value != null
      ? filteredData.value.slice(startIndex, endIndex)
      : riskData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange = (val) => {
  currentPage.value = val;
};

/////////pagination end here //////

watch(userList, (newValue, oldValue) => {
  if (newValue) {
    riskWatch(newValue);
  }
});

const isOpenSidebar = ref(false);
const isOpenSidebarFilter = ref(false);
const riskID = ref(null);

function toggleSidebar(riskIdGet) {
  isOpenSidebar.value = !isOpenSidebar.value;
  riskID.value = riskIdGet.riskID;
}

const ProjectApiId = inject("ProjectApiId");

///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData = ref(null);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if (newValue?.downloadAble && newValue?.page == "risk") {
    downloadExcel(ProjectApiId.value, riskDataExcel.value, "Risks");
    updateDownloadFilter({ value: null, type: "downloadAble" });
  }
  if (newValue?.filter && newValue?.page == "risk") {
    toggleSidebarFilter();
    updateDownloadFilter({ value: null, type: "filter" });
  }
  if (newValue?.page == "risk" && newValue.filterCount == null) {
    riskWatch(userList.value);
    filteredData.value = null;
  }
});

const toggleSidebarFilter = () => {
  isOpenSidebarFilter.value = !isOpenSidebarFilter.value;

  if (isOpenSidebarFilter.value) {
    document.body.style.setProperty("overflow", "hidden", "important");
  } else {
    document.body.style.setProperty("overflow", "auto", "important");
  }
};
provide("toggleSidebarFilter", toggleSidebarFilter);

const filterJsonData = (data, selectedStatus, selectedPriority) => {
  console.log("selectedPriority", selectedPriority);
  return data.filter((item) => {
    const isStatusMatch =
      selectedStatus === null ||
      Object.values(selectedStatus).includes(item.status);
    const isPriorityMatch =
      selectedPriority === null ||
      Object.values(selectedPriority).includes(item.priority);
    return isStatusMatch && isPriorityMatch;
  });
};

const updatefilterFun = (filter) => {
  updateDownloadFilter({ value: filter, type: "filterTypeApply" });
  let Projectslistvalue = filterJsonData(
    riskData.value,
    filter?.selectedStatus.length > 0 ? filter.selectedStatus : null,
    filter.selectedPriority.length > 0 ? filter.selectedPriority : null
  );
  filteredData.value = Projectslistvalue;
  showHideHeader.value = true;
  if (Projectslistvalue.length > 0) {
    const formattedDataValue = computed(() =>
      Projectslistvalue.map((project) => excelSheetFormate(project))
    );
    riskDataExcel.value = formattedDataValue.value;
  }
};

//////////////filter and download end here ////////
</script>