<script>
import { ref, onMounted, computed, inject, provide } from "vue";
import { ChangeDateFormat, fetchData } from "../../Utils/Utils";
import ProjectListTable from "../Views/ProjectListTable.ce.vue";

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
    const pageSizeNum=inject('pageSizeNum');
    const userList = ref([]);
    const searchTerm = ref("");
    const pageSize = ref(pageSizeNum);
    const currentPage = ref(1);
    const hostUrl = props.url;
    const keyName = props.keyName;
    const openprojectList = hostUrl.split("/").includes("supporttickets");
    const openRiskList = hostUrl.split("/").includes("risksandissues");
    const breadcrumbs = inject('breadcrumbs');
    const ChangePage = inject("ChangePage");
    const datacheck = inject('datacheck');
    const lastPageNumber=ref(1);
    
    onMounted(async () => {
      try {
        const response = await fetchData(hostUrl + `&pageSize=${pageSize.value}`);
        userList.value = response.items;
        console.log("userList",userList.value);
        lastPageNumber.value=response.lastPage?response.lastPage:1;
      }
      catch (error) {
        console.error(error);
      }
    });

    datacheck.value=userList
    console.log(userList)
    provide("userList", userList);
    
    async function handleSearch() {
      const response = await fetchData(hostUrl + `&pageSize=${pageSize.value}&search=${searchTerm.value}`);
      userList.value = response.items;
      lastPageNumber.value=response.lastPage?response.lastPage:1;

    }
    // ------------------

    const totalPages = computed(() => Math.ceil(20 / 2));
    async function urlEmbed(CurrentPage) {
      const response = await fetchData(hostUrl + `&page=${CurrentPage}&pageSize=${pageSize.value}&search=${searchTerm.value}`);
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
      openRiskList,
      breadcrumbs,
      openprojectList,
      hostUrl,
      lastPageNumber
    };
  },
  components: { ProjectListTable }
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

                  <div v-if="openprojectList">
                   
                    <ProjectListTable :hostUrl="hostUrl" />
                  </div>
                  <div v-if="openRiskList">
                    <!-- <RiskIssuesListTable :hostUrl="hostUrl" /> -->
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
