<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/list-page.ce.vue"

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
onMounted(async () => {
  console.log("hello i am document")
});

const ProjectOverviewData = inject("ProjectOverviewData");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");

const ProjectApiId = inject("ProjectApiId");
const pageSizeNum = inject('pageSizeNum');

const documentsUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/document/document-list/?projectID=${ProjectApiId.value}`;




</script>

<template>
  <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
    <div class="row">
      <div class="col-md-3 border-right">
     
        <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('DOCUMENTS.PROJECT') }}</h2><h2>{{ i18n.global.t('DOCUMENTS.DOCUMENTS') }}</h2></h2>
        <div class="mt-4">
          <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('DOCUMENTS.PROJECT_TITLE_&_ID') }}</p>
          <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
            ProjectOverviewData.projectName : i18n.global.t('N/A') }}</p>
          <p class="text-grey">STCS - {{ ProjectOverviewData.id ?
            ProjectOverviewData.id : i18n.global.t('N/A') }}</p>
        </div>
        <div class="mt-250 mb-4">
          <!-- <button class="btn btn-default btn-block" type="button">{{ i18n.global.t('DOCUMENTS.UPLOAD_A_DOCUMENT')}}</button> -->
        </div>
      </div>
      <div class="col-md-9">
        <div class="table-responsive">
          <listpage :url="documentsUrl" />

        </div>
      </div>
    </div>
  </div>
</template>