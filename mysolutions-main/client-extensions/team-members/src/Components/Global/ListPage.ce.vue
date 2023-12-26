<script>
import { ref, onMounted, computed, inject, provide } from "vue";
import { ChangeDateFormat, fetchData } from "../../Utils/Utils";
import ProjectTabs from "../Views/ProjectTabs.ce.vue"
import HeaderList from "./HeaderList.ce.vue";
import ProjectListTable from "../Views/ProjectListTable.ce.vue";
import RiskIssuesListTable from "../Views/RiskandIssues/RiskIssuesListTable.ce.vue";
import ScheduleListTable from "../Views/Schedule/ScheduleListTable.ce.vue";
import ProjectDocumentsListTable from "../Views/Documents/ProjectDocumentsListTable.ce.vue";
import MemberAccessListTableme from "../Views/MemberAccess/MemberAccessListTableme.ce.vue";
import MemberAccessListTableRoles from "../Views/MemberAccess/MemberAccessListTableRoles.ce.vue";
import ProjectInvoiceListTable from "../Views/Invoices/ProjectInvoiceListTable.ce.vue";

import StakeholdersListTable from "../Views/Stakeholders/StakeholdersListTable.ce.vue";

import MilestonesListTable from "../Views/Milestones/MilestonesListTable.ce.vue";
export default {

props: {
    keyName: {
      type: Object,
      required: true,
    },
    url: {
      type: String,
      required: true,
    },
    childVariable: {
      type: String,
      required: true,
    },
    totalItems: {
      type: Number,
      required: true,
    },
    itemsPerPage: {
      type: Number,
      default: 10,
    },
    maxDisplayedPages: {
      type: Number,
      default: 5,
    },
  },
  setup(props, context) {
    const userList = ref([]);
    const searchTerm = ref("");
    const pageSize = ref(6);
    const currentPage = ref(1);
    const hostUrl = props.url;
    const keyName = props.keyName;
    const openprojectList = hostUrl.split("/").includes("projectts");
    const openRiskList = hostUrl.split("/").includes("risksandissues");
    const openScheduleList = hostUrl.split("/").includes("projectschedules");
    const openDocumentList = hostUrl.split("/").includes("documents");
    const openApprovalList = hostUrl.split("/").includes("approvals");
    const openInvoiceList = hostUrl.split("/").includes("invoices");
    const openStakeholdersList = hostUrl.split("/").includes("stakeholders");
    const openMilestoneList = hostUrl.split("/").includes("milestones");
    const openassignedtome = hostUrl.split("/").includes("assignedtomes");
    const openassignedtoRole = hostUrl.split("/").includes("assignedtomyroles");
    const breadcrumbs = inject('breadcrumbs');
    const ChangePage = inject("ChangePage");
    const datacheck = inject('datacheck');

    onMounted(async () => {
      try {
        const response = await fetchData(hostUrl + `&pageSize=${pageSize.value}`);
        userList.value = response.items;
      }
      catch (error) {
        console.error(error);
      }
    });

    datacheck.value=userList
    console.log(userList)
    provide("userList", userList);
    
    async function handleSearch() {
      const response = await fetchData(hostUrl + `&search=${searchTerm.value}`);
      userList.value = response.items;
    }
    // ------------------
    const totalPages = computed(() => Math.ceil(20 / 2));
    async function urlEmbed(CurrentPage) {
      const response = await fetchData(hostUrl + `&page=${CurrentPage}&pageSize=${pageSize.value}&search=${searchTerm.value}`);
      userList.value = response.items;
    }
    const displayedPages = computed(() => {
      const pages = [];
      if (totalPages.value <= props.maxDisplayedPages) {
        // If the total number of pages is less than or equal to the max displayed pages,
        // display all pages without any dots
        for (let page = 1; page <= totalPages.value; page++) {
          pages.push(page);
        }
      }
      else {
        // Calculate the start and end page based on the current page and maxDisplayedPages
        let startPage = Math.max(1, currentPage.value - Math.floor(props.maxDisplayedPages / 2));
        let endPage = Math.min(totalPages.value, startPage + props.maxDisplayedPages - 1);
        // Adjust the start and end page to include dots if necessary
        if (endPage - startPage + 1 < props.maxDisplayedPages) {
          if (currentPage.value <= Math.ceil(props.maxDisplayedPages / 2)) {
            endPage = props.maxDisplayedPages;
          }
          else if (currentPage.value >=
            totalPages.value - Math.floor(props.maxDisplayedPages / 2)) {
            startPage = totalPages.value - props.maxDisplayedPages + 1;
          }
          else {
            startPage =
              currentPage.value - Math.floor(props.maxDisplayedPages / 2);
            endPage =
              currentPage.value + Math.floor(props.maxDisplayedPages / 2);
          }
        }
        // Add the page numbers and dots to the array
        for (let page = startPage; page <= endPage; page++) {
          pages.push(page);
        }
        if (startPage > 1) {
          pages.unshift("...");
        }
        if (endPage < totalPages.value) {
          pages.push("...");
        }
      }
      return pages;
    });
    async function changePage(page) {
      if (page >= 1 && page <= totalPages.value) {
        currentPage.value = page;
        //   const response = await fetchData(
        //     `/o/c/projects/?p_auth=${Liferay.authToken}&page=${currentPage.value}&pageSize=${pageSize.value}&search=${searchTerm.value}`
        //   );
        //   userList.value = response.items;
        // }
        urlEmbed(currentPage.value);
      }
    }
    async function previousPage() {
      if (currentPage.value > 1) {
        currentPage.value--;
        // const response = await fetchData(
        //   `/o/c/projects/?p_auth=${Liferay.authToken}&page=${currentPage.value}&pageSize=${pageSize.value}&search=${searchTerm.value}`
        // );
        // userList.value = response.items;
        urlEmbed(currentPage.value);
      }
    }
    async function nextPage() {
      if (currentPage.value < totalPages.value) {
        currentPage.value++;
        // const response = await fetchData(
        //   `/o/c/projects/?p_auth=${Liferay.authToken}&page=${currentPage.value}&pageSize=${pageSize.value}&search=${searchTerm.value}`
        // );
        // userList.value = response.items;
        urlEmbed(currentPage.value);
      }
    }
    // -----------
    return {
      userList,
      searchTerm,
      ChangeDateFormat,
      handleSearch,
      currentPage,
      totalPages,
      displayedPages,
      changePage,
      previousPage,
      nextPage,
      keyName,
      ChangePage,
      ProjectTabs,
      openRiskList,
      HeaderList,
      breadcrumbs,
      openprojectList,
      openScheduleList,
      openDocumentList,
      openApprovalList,
      openInvoiceList,
      openStakeholdersList,
      openMilestoneList,
      openassignedtome,
      openassignedtoRole,
      hostUrl,
      ProjectDocumentsListTable,
      MemberAccessListTableme,
      ProjectInvoiceListTable,
      MilestonesListTable,
      MemberAccessListTableRoles
    };
  },
  components: { HeaderList, ProjectListTable, RiskIssuesListTable, ScheduleListTable, ProjectDocumentsListTable, MemberAccessListTableme, ProjectInvoiceListTable, StakeholdersListTable, MilestonesListTable,MemberAccessListTableRoles }
};
</script>


<template>
  <div>
    <div class="">
      <div class="Searchbar mr-3">
        <input type="text" v-model="searchTerm" @input="handleSearch" placeholder="Search..." class="search-input" />
      </div>
      <div class="table-border">
        <div class="container-fluid my-3 mnh-375p ">
          <div class="row">
            <div class="col-md-12">
              <div class="table-responsive">

                <!-- --------- table for  project list-  -->
                <div >

                 
                
               
                    <MemberAccessListTableme  />
                  
                
                 
                </div>
 <!-- --------- table for  risk & Issues  list-  -->
              </div>
            </div>
          </div>
        </div>

      </div>
      <!-- -------------------- -->




    </div>

    <!-- '''''' pagination  -->


    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 1" style="    background-color: white;
      border: none;">
        Prev
      </button>
      <div v-for="page in displayedPages" :key="page">
        <template v-if="page === '...'">
          <span class="dots">...</span>
        </template>
        <template v-else>
          <button @click="changePage(page)" :class="{ active: currentPage === page }" style="    background-color: white;
          border: none;">
            {{ page }}
          </button>
        </template>
      </div>
      <button @click="nextPage" :disabled="currentPage === totalPages" style="    background-color: white;
      border: none;">
        Next
      </button>
    </div>
  </div>
</template>
