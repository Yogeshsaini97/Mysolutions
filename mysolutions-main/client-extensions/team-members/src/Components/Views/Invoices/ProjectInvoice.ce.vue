<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/ListPage.ce.vue"




const ProjectGetOneData = inject("ProjectGetOneData");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");
const ProjectApiId = inject("ProjectApiId");

const InvoicesUrl = `${import.meta.env.VITE_APP_ROOT_API}/invoices/?p_auth=${Liferay.authToken}&filter=r_withInvoice_c_projecttId eq '${ProjectApiId.value}'`;




</script>

<template>
    <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
        <div class="row">
          <div class="col-md-3 border-right">
            <h2 class="mt-4 line-height-15">Project<br />Invoices</h2>
            <div class="mt-4">
              <p class="mb-1 text-default">Project title & ID</p>
              <p class="mb-0 font-weight-600">{{ ProjectGetOneData.projectTitle ?
                ProjectGetOneData.projectTitle : '<p>No Data Found</p>' }}</p>
              <p>STCS - {{ ProjectGetOneData.id ?
                ProjectGetOneData.id : '<p>No Data Found</p>' }}</p>
            </div>
          </div>
          <div class="col-md-9">
            <div class="table-responsive mt-3">
                <listpage :url="InvoicesUrl" />
        
            </div>
          </div>
        </div>
      </div>
</template>