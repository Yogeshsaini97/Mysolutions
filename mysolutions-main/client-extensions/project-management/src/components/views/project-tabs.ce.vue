<script setup>
import { inject, onMounted, provide, reactive, ref } from 'vue'
import Tabs from "../tabs/tabs.vue"
import Tab from "../tabs/tab.vue"
import Overview from "./Overview/Overview.vue"
import { makeRequest } from '../../utils/utils';
import RiskIssues from "./risks/risks.ce.vue";
import Schedule from './schedule/Schedule.vue';
import ProjectDocuments from './documents/ProjectDocuments.ce.vue';
import ProjectApproval from './approvals/ProjectApproval.ce.vue';
import ProjectInvoice from './invoices/invoices.ce.vue';
import Stakeholders from './stakeholders/stakeholders.ce.vue';
import Milestone from './milestones/Milestone.ce.vue';
import Loader from '../Global/Loader.ce.vue';
import Issues from './issues/issues.ce.vue';


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

// function to limit the api starts here 

const showTabOnCLick=reactive([{id:0,value:false},{id:1,value:false},{id:2,value:false},{id:3,value:false},{id:4,value:false},{id:5,value:false},{id:6,value:false},{id:7,value:false},{id:8,value:false}]);

provide("showTabOnCLick",showTabOnCLick)


// function to limit the api ends here 


const ProjectApiId = inject('ProjectApiId');
const loader = ref(false)
const ProjectOverviewData = ref(null);


console.log("inside projects tabs show tabs",showTabOnCLick[0]);


const fetchMultipleApi = async () => {
  try {
    loader.value=true;
    const [response1, response2, response3, response4] = await Promise.all([
      makeRequest(import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/project?projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`, 'GET', null)
      // fetch('api3-url'),
      // fetch('api4-url')
    ]);

    if(response1.message!='ok')
        {
          alert(response1.message);
          return;
        }




  console.log(response1.payload)

  loader.value=false;
  const data1 = response1.payload;
    
 // Update the reactive variables with the fetched data
  ProjectOverviewData.value = data1;

  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

onMounted(async () => {
  await fetchMultipleApi();

})


provide("ProjectOverviewData", ProjectOverviewData)





const arr=[{one:false},{two:false},{three:false}]



</script>



<template>
  <Tabs>


    <Tab active="true" id="Overview" :title="i18n.global.t('PROJECT_TABS.OVERVIEW')">

      <div >
        
        <Overview />
      </div>
      
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.SCHEDULE')" id="Schedule" >
      <div v-if="ProjectOverviewData && showTabOnCLick[1].value==true">
        <Schedule/>
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.MILESTONES')" id="Milestones" >
      <div v-if="ProjectOverviewData && showTabOnCLick[2].value==true" >
        <Milestone />
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.RISKS')" id="Risks">
      <div v-if="ProjectOverviewData && showTabOnCLick[3].value==true">
        <RiskIssues />
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.ISSUES')" id="Issues">
      <div v-if="ProjectOverviewData && showTabOnCLick[4].value==true">
        <Issues />
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.DOCUMENTS')" >
      <div v-if="ProjectOverviewData && showTabOnCLick[5].value==true">
        <ProjectDocuments/>
      </div>
     
    </Tab>
    
    <Tab :title="i18n.global.t('PROJECT_TABS.APPROVALS')">
      <div v-if="ProjectOverviewData && showTabOnCLick[6].value==true">
        <ProjectApproval />
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.INVOICES')">
      <div v-if="ProjectOverviewData && showTabOnCLick[7].value==true">
        <ProjectInvoice />
      </div>
    </Tab>
    <Tab :title="i18n.global.t('PROJECT_TABS.STAKEHOLDERS')">
      <div v-if="ProjectOverviewData && showTabOnCLick[8].value==true">
        <Stakeholders />
      </div>
      
    </Tab>
   



  </Tabs>
</template>



