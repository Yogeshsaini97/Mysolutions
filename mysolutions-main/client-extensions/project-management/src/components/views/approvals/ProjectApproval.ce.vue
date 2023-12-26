<script setup>
    import {
        inject,
        onMounted
    } from "vue";
    import listpage from "../../Global/list-page.ce.vue"
    /////////////langugage defined start here///////
    const i18n = inject('i18n');
    /////////////langugage defined end here///////  
    const ProjectOverviewData = inject("ProjectOverviewData");
    const no_of_Issues = inject("no_of_Issues");
    const no_of_Risks = inject("no_of_Risks");
    const ProjectApiId = inject("ProjectApiId");
    const pageSizeNum = inject('pageSizeNum');
    // const ApprovalsUrl =import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `epm/v1/approvals/?projectNumber=${ProjectApiId.value}`;
    const ApprovalsUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API_APPROVALS + `epm/v1/approvals/?projectNumber=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;
</script>
<template>
    <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
        <div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
            <div class="col-md-3 border-right">
              
                <h2 class="my-3 line-height-15"><h2 class="m-0">  {{ i18n.global.t('PROJECT_APPROVALS.PROJECT')}}</h2><h2>{{ i18n.global.t('PROJECT_APPROVALS.APPROVALS')}}</h2></h2>
                <div class="mt-4">
                    <p class="mb-1 font-weight-600 text-default">
                        {{ i18n.global.t('PROJECT_APPROVALS.PROJECT_TITLE_&_ID')}}
                    </p>
                    <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
                ProjectOverviewData.projectName : i18n.global.t('N/A')  }}</p>
                    <p class="text-grey">
                        {{ i18n.global.t('ID')}} - {{ ProjectOverviewData.id ?
                ProjectOverviewData.id : i18n.global.t('N/A')  }}</p>
                </div>
            </div>
            <div class="col-md-9">
                <div class="table-responsive">
                    <listpage :url="ApprovalsUrl" />
                </div>
            </div>
        </div>
    </div>
</template>