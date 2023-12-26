<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/list-page.ce.vue"

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////  

const crNumber = inject("crNumber")

onMounted(async () => {
  console.log("hello i am risk")
});

const ProjectOverviewData = inject("ProjectOverviewData");

const ProjectApiId = inject("ProjectApiId");
const pageSizeNum = inject('pageSizeNum');
const InvoicesUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/erp/invoice/invoice-list/?CRNumber=${crNumber.value}%26projectId=${ProjectApiId.value}`;




</script>
<template>
  <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
    <div class="row">
      <div class="col-md-3 border-right">
     
               
        <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('PROJECT_INVOICES.PROJECT') }}</h2><h2>{{
          i18n.global.t('PROJECT_INVOICES.INVOICES') }}</h2></h2>
        <div class="mt-4">
          <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('PROJECT_INVOICES.PROJECT_TITLE_&_ID') }}</p>
          <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
            ProjectOverviewData.projectName : 'N/A' }}</p>
          <p class="text-grey">{{ i18n.global.t('PROJECT_INVOICES.STCS') }} - {{ ProjectOverviewData.id ?
            ProjectOverviewData.id : 'N/A' }}</p>
        </div>
      </div>
      <div class="col-md-9">
        <div class="table-responsive">
          <listpage :url="InvoicesUrl" />

        </div>
      </div>
    </div>
  </div>
</template>