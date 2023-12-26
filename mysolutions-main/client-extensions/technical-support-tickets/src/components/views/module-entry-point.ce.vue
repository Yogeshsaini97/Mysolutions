<template>
  <div v-if="crNumber != null">
    <headerlist />
    <component :is="CurrentPage.fileName" :url="technicalSupportHostUrl" />
  </div>
</template>

<script setup>
import { inject, provide, reactive, ref,computed } from 'vue';
import listpage from '../global/list-page.ce.vue' ;
import headerlist from '../global/header-list.ce.vue';
import {  makeRequest, risk_issue_priority_count } from "../../utils/utils";

import ProjectTabs from './project-tabs.ce.vue';
import TechnicalSupportDetail from './Overview/technical-support-detail.vue';





/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const showPageOnClick=ref(null);
const datacheck=reactive([])
provide('datacheck', datacheck);

const currentSupportID=ref(null);
provide('currentSupportID',currentSupportID)

const dataMilestoneApi=reactive({"payload":null,"totalMilestone":null,  "completedMilstone":null,"ProcessMilestone":null});
provide('dataMilestoneApi', dataMilestoneApi);


const dataRiskApi=reactive({"payload":null,"Count":null});
provide('dataRiskApi', dataRiskApi);

const dataissueApi=reactive({"payload":null,"Count":null});
provide('dataissueApi', dataissueApi);

const crNumber=inject("crNumber");

const updateDownloadFilter = inject("updateDownloadFilter");

const technicalSupportHostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API +`%2Fods%2Fazer%2Fcase-list%3FcrNumber%3D${crNumber.value}%26dateFrom%3D2023-01-01T00%3A00%3A00%26dateTo%3D2023-12-10T23%3A59%3A59&p_auth=${Liferay.authToken}`;
const CurrentPage = reactive({ fileName: listpage, key: "list-page" });
provide('CurrentPage', CurrentPage);


const ProjectApiId = ref(null);
provide('ProjectApiId', ProjectApiId);



///////////////code for progress bar setting defined start here ///////////
const ProcessBarReactive = reactive({ actualValue: null, plannedValue: null, display: false });
provide('ProcessBarReactive', ProcessBarReactive);
///////////////code for progress bar setting end here /////////////////////



///////////////code for breadcrumbs setting start here /////////////////////
const breadcrumbs = ref([
  { label: i18n.global.t('CASE_LIST'),Link:`${window.location.origin}/group/mysolutions/tickets` }
]);


provide('breadcrumbs', breadcrumbs);

///////////////code for breadcrumbs setting ending here /////////////////////
///////////////code for backbuton  start here /////////////////////

const BackButton = () => {
  console.log("CurrentPage", CurrentPage.key)
  breadcrumbs.value.pop();
  showPageOnClick.value=null;
  // ChangePage({fileName:list-page,key:'list-page'})
  if (CurrentPage.key === "list-page") {
    window.location.href = './dashboard';
    console.log("1")
    //kept for further chnages
  }
  else if (CurrentPage.key === "ProjectTabs") {
   
    ChangePage({ fileName: listpage, key: 'list-page' })
    breadcrumbs.value.pop();
    updateDownloadFilter({'type':'Project',value:null})
    console.log("2")

  }
  else if (CurrentPage.key === "ShowInvoicesDetailsID") {
    ChangePage({ fileName: ProjectTabs, key: 'ProjectTabs' })
    breadcrumbs.value.pop();
    console.log("3")

  }
}
provide("BackButton", BackButton)

/////////////////////////milestone API call Here///////////

const totalCountCompleted = computed(() => {
     console.log("dataMilestoneApimilestones",dataMilestoneApi.payload?.payload?.milestones);
      return dataMilestoneApi.payload?.payload?.milestones.filter(task => task.status === 'Completed');

    });

    const milestoneApicall = async (id) => {
  try {
    let hostUrlMilesone = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/milestone/milestone-list/?projectID=${id}`;
    
    const response = await makeRequest(hostUrlMilesone, 'GET', null);

    if (response.status === 200) {
      dataMilestoneApi.payload = response;
      
      if (response.payload?.milestones) {
        dataMilestoneApi.totalMilestone = response.payload.milestones?response.payload.milestones?.length:0;
        dataMilestoneApi.completedMilstone =totalCountCompleted.value?totalCountCompleted.value?.length:0;
      }
      
      console.log("dataMilestoneApi", dataMilestoneApi);
    } else {
      console.error("API request failed with status:", response.status);
    }
  } catch (error) {
    console.error("Error:", error);
  }
};

const riskPriorityCount = async (id) => {
  try {
    let hostUrlRisks = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `/ods/epm/risk/risk-list?projectID=${id}`;
    let count;
  
    const response = await makeRequest(hostUrlRisks, 'GET', null);
    console.log("response", response)
    if (response.status === 200) {
      console.log(dataRiskApi);
      console.log(dataRiskApi.Count);
      dataRiskApi.payload = response;
      console.log(dataRiskApi.payload)
      
      if (response?.payload?.risks) {
      
        count=risk_issue_priority_count(response?.payload?.risks);
         dataRiskApi.Count=count;
        
       
        // dataRiskApi.totalMilestone = response.payload.milestones?response.payload.milestones?.length:0;
        // dataRiskApi.completedMilstone =totalCountCompleted.value?totalCountCompleted.value?.length:0;
      }
      
      console.log("dataRiskApi", dataRiskApi);
    } else {
      console.error("API request failed with status:", response.status);
    }
  } catch (error) {
    console.error("Error:", error);
  }
};

const issuePriorityCount = async (id) => {
  try {
    let hostUrlIssues = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `/ods/epm/issue/issue-list?projectID=${id}`;
    let tempData;
    
    const response = await makeRequest(hostUrlIssues, 'GET', null);
    console.log("response", response)
    if (response.status === 200) {
      console.log(dataissueApi);
      console.log(dataissueApi.Count);
      dataissueApi.payload = response;
      console.log(dataissueApi.payload)
      
      if (response?.payload?.issues) {
      
        tempData=risk_issue_priority_count(response?.payload?.issues);
         dataissueApi.Count=tempData.Count;
         dataissueApi.priority=tempData.Name;
        console.log("dataissueApiinutil",dataissueApi)
        // dataissueApi.totalMilestone = response.payload.milestones?response.payload.milestones?.length:0;
        // dataissueApi.completedMilstone =totalCountCompleted.value?totalCountCompleted.value?.length:0;
      }
      
      console.log("dataissueApi", dataissueApi);
    } else {
      console.error("API request failed with status:", response.status);
    }
  } catch (error) {
    console.error("Error:", error);
  }
};



const ChangePage = (PageToRender) => {
console.log(PageToRender)
  CurrentPage.fileName = PageToRender.fileName;
  CurrentPage.key = PageToRender.key;
  currentSupportID.value=PageToRender.item.supportId;
  ///////////////setting of ProcessBarReactive start here/////////
  if (CurrentPage.key == "ProjectTabs") {
    ProcessBarReactive.actualValue = PageToRender?.item.progressActual;
    ProcessBarReactive.plannedValue = PageToRender?.item.progressPlanned;
    ProcessBarReactive.display = true;
    updateDownloadFilter({value:null,"type":"no"})
  }
  ///////////////setting of ProcessBarReactive end here/////////
  //////////////breadcrumbs changes start here//////////
  if (CurrentPage.key == "ShowInvoicesDetailsID")
   {
   console.log(PageToRender?.item?.trxNumber)
  }
  //////////////breadcrumbs changes end here//////////
}

const ChangePageNoLink = (PageToRender,pagetype) => {
  CurrentPage.fileName = PageToRender.fileName;
  CurrentPage.key = PageToRender.key;
  ///////////////setting of ProcessBarReactive start here/////////
    ProcessBarReactive.actualValue = PageToRender?.item.progressActual;
    ProcessBarReactive.plannedValue = PageToRender?.item.progressPlanned;
    ProcessBarReactive.display = true;
    milestoneApicall(PageToRender?.item.id);
    ProjectApiId.value=PageToRender?.item.id;
    breadcrumbs.value.push({ label: 'ID - ' + PageToRender?.item.id });
  ///////////////setting of ProcessBarReactive end here/////////
  showPageOnClick.value=pagetype;
}

provide('showPageOnClick',showPageOnClick);
provide('ChangePage', ChangePage); //main change function
provide('ChangePageNoLink', ChangePageNoLink);


</script>