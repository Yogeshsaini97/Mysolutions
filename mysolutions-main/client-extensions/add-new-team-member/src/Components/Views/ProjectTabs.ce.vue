<script setup>
import { createApp, inject, onMounted, provide, ref } from 'vue'
import Tabs from "../Tabs/Tabs.vue"
import Tab from "../Tabs/Tab.vue"
import Overview from "./Overview/Overview.vue"
import { fetchData } from '../../Utils/Utils';
import Milestone from './Milestones/Milestone.ce.vue';

const ProjectApiId = inject('ProjectApiId');
const ProjectGetOneData = ref(null);
const api1Data = ref(null);
const api2Data = ref(null);
const api3Data = ref(null);
const no_of_Risks = ref(0);
const no_of_Issues = ref(0);


const fetchMultipleApi = async () => {
  try {
    const [response1, response2, response3, response4] = await Promise.all([
      fetchData(`${import.meta.env.VITE_APP_ROOT_API}/projectts/${ProjectApiId.value}?p_auth=${Liferay.authToken}`),
      fetchData(`${import.meta.env.VITE_APP_ROOT_API}/risksandissues/?p_auth=${Liferay.authToken}&filter=r_withRiskAndIssues_c_projecttId eq '${ProjectApiId.value}'`),
      // fetch('api3-url'),
      // fetch('api4-url')
    ]);

    const data1 = response1;
    const data2 = response2;
    // const data3 = await response3.json();
    // const data4 = await response4.json();

    // Update the reactive variables with the fetched data
    ProjectGetOneData.value = data1;
    api2Data.value = data2;

    console.log(ProjectGetOneData.value)
    console.log(api2Data.value)

    // api3Data.value = data3;
    // api4Data.value = data4;
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

onMounted(async () => {
  await fetchMultipleApi();

})


provide("ProjectGetOneData", ProjectGetOneData)
provide("api2Data", api2Data)
// provide("ProjectGetOneData", ProjectGetOneData)





</script>



<template>
  <Tabs>


    <Tab active="true" id="Overview" title="Overview">

    
        <Overview />
     
    </Tab>
    <Tab title="Role & Privileges" id="Role & Privileges">
    
        <Milestone />
 
    </Tab>
    <!-- <Tab title="Risk & Issues" id="Risk_&_Issues">
      <div v-if="ProjectGetOneData">
        <RiskIssues />
      </div>
    </Tab> -->
    <!-- <Tab title="Documents">
      <div v-if="ProjectGetOneData">
        <ProjectDocuments/>
      </div>
     
    </Tab>
    
    <Tab title="Approvals">
      <div v-if="ProjectGetOneData">
        <ProjectApproval />
      </div>
    </Tab>
    <Tab title="Invoices">
      <div v-if="ProjectGetOneData">
        <ProjectInvoice />
      </div>
    </Tab>
    <Tab title="Stakeholders">
      <div v-if="ProjectGetOneData">
        <Stakeholders />
      </div>
      
    </Tab>
    <Tab title="Schedule">
      <div v-if="ProjectGetOneData">
        <Schedule/>
      </div>
     
    </Tab> -->



  </Tabs>
</template>



