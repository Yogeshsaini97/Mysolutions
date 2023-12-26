<script setup>
import { inject, onMounted, provide, reactive, ref } from 'vue'

import Overview from "./Overview/technical-support-detail.vue"
import { makeRequest } from '../../utils/utils';


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



provide("ProjectOverviewData", ProjectOverviewData)





const arr=[{one:false},{two:false},{three:false}]



</script>



<template>

  <div>
    <Overview />
  </div>
  <!-- <Tabs>


    <Tab active="true" id="Overview" :title="i18n.global.t('PROJECT_TABS.OVERVIEW')">

      <div >
        
       
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
    <Tab :title="i18n.global.t('PROJECT_TABS.DOCUMENTS')">
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
   



  </Tabs> -->
</template>



