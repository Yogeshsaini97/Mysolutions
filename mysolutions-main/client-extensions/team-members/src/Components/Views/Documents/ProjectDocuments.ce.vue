<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/ListPage.ce.vue"


onMounted(async () => {
    console.log("hello i am document")
    });

const ProjectGetOneData = inject("ProjectGetOneData");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");

const ProjectApiId = inject("ProjectApiId");

const documentsUrl = `${import.meta.env.VITE_APP_ROOT_API}/documents/?p_auth=${Liferay.authToken}&filter=r_withDocument_c_projecttId eq '${ProjectApiId.value}'`;




</script>

<template>
    <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
        <div class="row">
          <div class="col-md-3 border-right">
            <h2 class="mt-4 line-height-15">Project<br />Documents</h2>
            <div class="mt-4">
              <p class="mb-1 text-default">Project title & ID</p>
              <p class="mb-0 font-weight-600">{{ ProjectGetOneData.projectTitle ?
                ProjectGetOneData.projectTitle : '<p>No Data Found</p>' }}</p>
              <p>STCS - {{ ProjectGetOneData.id ?
                ProjectGetOneData.id : '<p>No Data Found</p>' }}</p>
            </div>
            <div class="mt-250 mb-4">
              <button class="btn btn-default btn-block" type="button">Upload a document</button>
            </div>
          </div>
          <div class="col-md-9">
            <div class="table-responsive mt-3">
                <listpage :url="documentsUrl" />
        
            </div>
          </div>
        </div>
      </div>
</template>