<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/ListPage.ce.vue"


onMounted(async () => {
    console.log("hello i am risk")
    });

const ProjectGetOneData = inject("ProjectGetOneData");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");

const ProjectApiId = inject("ProjectApiId");

const stakeholdersUrl = `${import.meta.env.VITE_APP_ROOT_API}/stakeholders/?p_auth=${Liferay.authToken}&filter=r_withStakeholder_c_projecttId    eq '${ProjectApiId.value}'`;




</script>

<template>
  <div class="row">
    <div class="col-md-3 border-right">
      <h2 class="mt-4 line-height-15">Project<br />stakeholders</h2>
      <div class="mt-4">
        <p class="mb-1 text-default">Project title & ID</p>
        <p class="mb-0 font-weight-600">{{ ProjectGetOneData.projectTitle ?
          ProjectGetOneData.projectTitle : '<p>No Data Found</p>' }}</p>
        <p>STCS - {{ ProjectGetOneData.id ?
          ProjectGetOneData.id : '<p>No Data Found</p>' }}</p>
      </div>

      <div class="card bg-light shadow-none rounded-0 mnh-140p mt-4">
        <div class="card-body">
          <p class="mb-0 font-40 text-default">{{ no_of_Issues }}</p>
          <p class="mb-0">Members<img src="http://localhost:8080/documents/d/guest/arrow-up" class="ml-1" alt="img" /></p>
          <p class="mb-0 font-8 opacity-60">You have <span class="text-danger"> {{ no_of_Issues }}severe</span> issues</p>
        </div>
      </div>
      <div class="card bg-light shadow-none rounded-0 mnh-140p mt-4">
        <div class="card-body">
          <p class="mb-0 font-40 text-default">{{ no_of_Risks }}</p>
          <p class="mb-0">Members<img src="http://localhost:8080/documents/d/guest/arrow-up" class="ml-1" alt="img" /></p>
          <p class="mb-0 font-8 opacity-60">You have <span class="text-danger">{{ no_of_Risks }} extreme</span> risks</p>
        </div>
      </div>
      <div class="mt-4 mb-4">
        <button class="btn btn-default btn-block" type="button">Add a new stakeholder</button>
      </div>
    </div>
    
    <div class="col-md-9">
      <div class="table-responsive mt-3">
    
        <listpage :url="stakeholdersUrl " />
      </div>
    </div>
  </div>
</template>