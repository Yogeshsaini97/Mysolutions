<template>
  <div v-if="crNumber != null">
    <headerlist />
    <component :is="CurrentPage.fileName" :url="projectHostUrl" />
  </div>
</template>

<script setup>
import { inject, provide, reactive, ref,computed } from 'vue';
import listpage from '../global/list-page.ce.vue' ;
import headerlist from '../global/header-list.ce.vue';
import {  makeRequest, risk_issue_priority_count } from "../../utils/utils";

import ProjectTabs from './project-tabs.ce.vue';

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const showPageOnClick=ref(null);
const datacheck=reactive([])
provide('datacheck', datacheck);

const dataMilestoneApi=reactive({"payload":null,"totalMilestone":null,  "completedMilstone":null,"ProcessMilestone":null});
provide('dataMilestoneApi', dataMilestoneApi);


const dataRiskApi=reactive({"payload":null,"Count":null});
provide('dataRiskApi', dataRiskApi);

const dataissueApi=reactive({"payload":null,"Count":null});
provide('dataissueApi', dataissueApi);

const crNumber=inject("crNumber");

const updateDownloadFilter = inject("updateDownloadFilter");

// const projectHostUrl = import.meta.env.VITE_APP_ROOT_API+`/projectts/?p_auth=${Liferay.authToken}`;
// const projectHostUrl = 'https://apitest.stcs.com.sa/tibco/ods/epm/project/project-list?crNumber=4030062502';
// const projectHostUrl= 'https://dummyjson.com/products/1';
 //const projectHostUrl= 'http://localhost:3000/project-list';

const projectHostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/project/project-list/?crNumber=${crNumber.value}&p_auth=${Liferay.authToken}`;

///////////////

const CurrentPage = reactive({ fileName: listpage, key: "list-page" });
provide('CurrentPage', CurrentPage);

//////////////

//////////
const ProjectApiId = ref(null);
provide('ProjectApiId', ProjectApiId);
/////////

///////////////code for progress bar setting defined start here ///////////
const ProcessBarReactive = reactive({ actualValue: null, plannedValue: null, display: false });
provide('ProcessBarReactive', ProcessBarReactive);
///////////////code for progress bar setting end here /////////////////////

///////////////code for breadcrumbs setting start here /////////////////////
const breadcrumbs = ref([
  { label:  i18n.global.t('PROJECTS') ,Link:`${window.location.origin}/group/mysolutions/projects` }
]);

provide('breadcrumbs', breadcrumbs);

const BackButton = () => {
  console.log("CurrentPage.key.back", CurrentPage.key)
  showPageOnClick.value=null;
  // ChangePage({fileName:list-page,key:'list-page'})
  if (CurrentPage.key === "list-page") {
    window.location.href = './dashboard';
    //kept for further chnages
  }
  else if (CurrentPage.key === "ProjectTabs") {
    dataissueApi.payload=null;
    dataissueApi.Count=null;
    dataissueApi.priority=null;
    dataRiskApi.payload=null;
    dataRiskApi.Count=null;
    dataRiskApi.priority=null;
    ChangePage({ fileName: listpage, key: 'list-page' })
    breadcrumbs.value.pop();

  }
  else if (CurrentPage.key === "ShowInvoicesDetailsID") {
    ChangePage({ fileName: ProjectTabs, key: 'ProjectTabs' })
    breadcrumbs.value.pop();

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
    let hostUrlMilesone = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/milestone/milestone-list/?projectID=${id}&p_auth=${Liferay.authToken}`;
    
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
    let hostUrlRisks = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `/ods/epm/risk/risk-list?projectID=${id}&p_auth=${Liferay.authToken}`;
    let count;
  
    const response = await makeRequest(hostUrlRisks, 'GET', null);
    console.log("response new", response)
    if (response.status === 200) {
      console.log(dataRiskApi);
      console.log(dataRiskApi.Count);
      dataRiskApi.payload = response;
      console.log(dataRiskApi.payload)
      
      if (response?.payload?.risks) {
      
        count=risk_issue_priority_count(response?.payload?.risks);
        console.log("count is", count)
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
    let hostUrlIssues = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `/ods/epm/issue/issue-list?projectID=${id}&p_auth=${Liferay.authToken}`;
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
        console.log("count is",tempData)
         dataissueApi.Count=tempData;
    
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

  CurrentPage.fileName = PageToRender.fileName;
  CurrentPage.key = PageToRender.key;
  ///////////////setting of ProcessBarReactive start here/////////
  if (CurrentPage.key == "ProjectTabs") {
    ProcessBarReactive.actualValue = PageToRender?.item.progressActual;
    ProcessBarReactive.plannedValue = PageToRender?.item.progressPlanned;
    ProcessBarReactive.display = true;
  
    milestoneApicall(PageToRender?.item.id).then(()=>
    {
      riskPriorityCount(PageToRender?.item.id).then(()=>
      {
        issuePriorityCount(PageToRender?.item.id);
      })
    });

    updateDownloadFilter({value:null,"type":"no"})
   
   

   
  }
  ///////////////setting of ProcessBarReactive end here/////////
  //////////////breadcrumbs changes start here//////////
  if (CurrentPage.key == "list-page")
   {
    ProcessBarReactive.display = false;
    dataMilestoneApi.totalMilestone = null;
    dataMilestoneApi.completedMilstone =null;
    dataMilestoneApi.payload =null;
    updateDownloadFilter({value:null,"type":"project"})
  }
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
    breadcrumbs.value.push({ label: 'STCS - ' + PageToRender?.item.id });
    updateDownloadFilter({value:null,"type":pagetype})
  ///////////////setting of ProcessBarReactive end here/////////
  showPageOnClick.value=pagetype;
}

provide('showPageOnClick',showPageOnClick);
provide('ChangePage', ChangePage); //main change function
provide('ChangePageNoLink', ChangePageNoLink);


</script>