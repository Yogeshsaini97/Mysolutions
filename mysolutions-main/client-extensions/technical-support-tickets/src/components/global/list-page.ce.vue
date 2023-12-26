<script>
import { ref, onMounted, computed, inject, provide } from "vue";
import { ChangeDateFormat, makeRequest } from "../../utils/utils";
import ProjectTabs from "../views/project-tabs.ce.vue"
import headerlist from "./header-list.ce.vue";
import ProjectListTable from "../views/show-project-details.ce.vue";


import TableSkeletonLoader from "./table-skeleton-loader.ce.vue";
import Loader from './Loader.ce.vue';
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
      default: 1,
    },
    maxDisplayedPages: {
      type: Number,
      default: 5,
    },
  },
  setup(props, context) {
    const loader = ref(false)
    const pageSizeNum=inject('pageSizeNum');
    const userList = ref([]);
    const searchTerm = ref("");
    const pageSize = ref(pageSizeNum);
    const currentPage = ref(1);
    const hostUrl = props.url;
    const keyName = props.keyName;
    const openprojectList = hostUrl.split("/").includes("project-list");
    const openRiskList = hostUrl.split("/").includes("risk-list");
    const openIssueList = hostUrl.split("/").includes("issue-list");
    const openScheduleList = hostUrl.split("/").includes("task-list");
    const openDocumentList = hostUrl.split("/").includes("document-list");
    const openApprovalList = hostUrl.split("/").includes("approvals");
    const openInvoiceList = hostUrl.split("/").includes("invoice-list");
    const openStakeholdersList = hostUrl.includes("stakeholder");
    const openMilestoneList = hostUrl.split("/").includes("milestone-list");
    const breadcrumbs = inject('breadcrumbs');
    const ChangePage = inject("ChangePage");
    const datacheck = inject('datacheck');
    const lastPageNumber=ref(1);
    console.log("openScheduleList",openScheduleList);
    onMounted(async () => {
      loader.value=true;
        const response = await makeRequest(hostUrl, 'GET', null);
        console.log("onMounted",response);
        console.log(response)
        
        if(response.message!='ok')
        {
          // alert(response.message);
          userList.value = await response;
          loader.value=false;
          return;
        }

        userList.value = await response;
        console.log(userList.value)
        loader.value=false;
        lastPageNumber.value=response.lastPage?response.lastPage:1;
      
      
    });

    datacheck.value=userList
    provide("userList", userList);
    
    async function handleSearch() {
      const response = await makeRequest(hostUrl + `&pageSize=${pageSize.value}&search=${searchTerm.value}`);
      userList.value = response.items;
      lastPageNumber.value=response.lastPage?response.lastPage:1;

    }
    // ------------------

    const totalPages = computed(() => Math.ceil(20 / 2));
    async function urlEmbed(CurrentPage) {
      const response = await makeRequest(hostUrl + `&page=${CurrentPage}&pageSize=${pageSize.value}&search=${searchTerm.value}`);
      userList.value = response.items;
    }
    const displayedPages = computed(() => {
      const pages = [];
      if (lastPageNumber.value <= props.maxDisplayedPages) {
        // If the total number of pages is less than or equal to the max displayed pages,
        // display all pages without any dots
        for (let page = 1; page <= lastPageNumber.value; page++) {
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
          urlEmbed(currentPage.value);
      }
    }
    async function previousPage() {
      if (currentPage.value > 1) {
        currentPage.value--;
        // const response = await makeRequest(
        //   `/o/c/projects/?p_auth=${Liferay.authToken}&page=${currentPage.value}&pageSize=${pageSize.value}&search=${searchTerm.value}`
        // );
        // userList.value = response.items;
        urlEmbed(currentPage.value);
      }
    }
    async function nextPage() {
      if (currentPage.value < totalPages.value) {
        currentPage.value++;
        // const response = await makeRequest(
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
      openIssueList,
      headerlist,
      breadcrumbs,
      openprojectList,
      openScheduleList,
      openDocumentList,
      openApprovalList,
      openInvoiceList,
      openStakeholdersList,
      openMilestoneList,
      hostUrl,
     
      lastPageNumber,
      Loader,
      loader,
      TableSkeletonLoader
    };
  },
  components: { headerlist, ProjectListTable,Loader,TableSkeletonLoader }
};
</script>


<template>
  <div>
    <div class="">
      <!-- <div class="Searchbar mr-3">
        <input type="text" v-model="searchTerm" @input="handleSearch" placeholder="Search..." class="search-input" />
      </div> -->
      <div class="table-border">
        <div class="container-fluid mnh-375p p-0 ">
          <div class="row">
            <div class="col-md-12 p-0">
              <div class="table-responsive">

                <!-- --------- table for  project list-  -->
                <div >

                  <div>
                    <div v-if="loader">
                      <TableSkeletonLoader/>
                    </div>
                    <div v-else>
                      <ProjectListTable :hostUrl="hostUrl" />
                    </div>
                   
                   
                  </div>
                  
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


    <div class="pagination" v-if="lastPageNumber!==1">
      <button @click="previousPage" :disabled="currentPage === 1" :class="currentPage === 1?'colorgrey':'colorRed'" style="    background-color: white;
      border: none;">
        Prev
      </button>
      <div v-for="page in displayedPages" :key="page">
        <template v-if="page === '...'">
          <span class="dots">...</span>
        </template>
        <template v-else>
          <button @click="changePage(page)"  :class="currentPage === page?'colorBlue':''"  style="    background-color: white;
          border: none;">
            {{ page }}
          </button>
        </template>
      </div>
      <button @click="nextPage" :disabled="currentPage === lastPageNumber" :class="currentPage === lastPageNumber?'colorgrey':'colorRed'"  style="    background-color: white;
      border: none;">
        Next
      </button>
    </div>
  </div>
</template>
