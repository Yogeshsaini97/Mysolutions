<template>

    <div> 
        <div class="beautiful-dropdown" style="position: absolute;
        top: -8.5rem;
        right: 13rem;
        z-index: 10;
        ">
            <a  @click="toggleDropdown" href="javascript:void(0)" class="btn dropdown-button btn-primary align-center rounded-10"><img
                src="http://localhost:8080/documents/d/guest/filter-white" alt="img" /></a>
         
          <ul v-if="isOpen" class="dropdown-list">
            <li v-for="option in dropdownOptions" :key="option" @click="selectOption(option)">
                {{ option }}
                <hr>
            </li>
          </ul>
       
        </div>
        <DownloadToExcel style="position: absolute;
        top: -7rem;
        right: 9rem;
        z-index: 10;
        "/>
  
       
    <table class="table font-weight-600">
 <thead>
            <tr>
                <th>Status</th>
                <th>Program title & ID</th>
                <th>Progress (actual vs planned)</th>
                <th>Issues</th>
                <th>Risks</th>
                <th>Started</th>
                <th>Expected completion</th>
                <th></th>
            </tr>
        </thead>

        <tbody v-if="userList.length > 0">
            <tr v-for="item in userList" :key="item.id">
                <td><span class="badge rounded-pill bg-light-success font-10"><i
                            class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.statusActive }}</span></td>
                <td>{{ item.projectTitle }}<br /><span class="font-weight-400">STCS - {{ item.id }}</span></td>
                <td>
                    <div class="row">
                        <div class="col-6">
                            <p class="mb-0 font-10">Actual<span class="ml-2 text-danger">{{ item.progressActual }}%</span>
                            </p>
                        </div>
                        <div class="col-6">
                            <p class="mb-0 text-right font-10">Planned<span class="ml-2 text-success">{{
                                item.progressPlanned }}%</span></p>
                        </div>
                        <div class="col-12">
                            <div class="progress h-5p">
                                <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="10"
                                    :style="{ width: `${item.progressActual}%` }"></div>
                                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="40"
                                    :style="{ width: `${item.progressPlanned}%` }"></div>
                            </div>
                        </div>
                    </div>
                </td>
                <td>{{ item.issues }}</td>
                <td>{{ item.risks }}</td>
                <td>{{ ChangeDateFormat(item.startedDate) }}</td>
                <td>{{ ChangeDateFormat(item.expectedCompletion) }}</td>
                <td><a href="javascript:void(0)"><img src="http://localhost:8080/documents/d/guest/arrow-up" alt="img"
                            @click="() => { ChangePage({ fileName: ProjectTabs, key: 'ProjectTabs',item}); ProjectApiId = item.id; 
                            breadcrumbs.push({ label: item.id }) }" /></a>
                </td>
            </tr>
        </tbody>
        <tbody v-else>
            <tr>
                <td colspan="7">End of Data</td>
            </tr>
        </tbody>
    </table>
    </div>
</template>



  
  

<script setup>
import { inject, provide, ref } from 'vue';
import { ChangeDateFormat,fetchData } from '../../Utils/Utils';
import ProjectTabs from "./ProjectTabs.ce.vue"
import DownloadToExcel from '../Global/DownloadToExcel.ce.vue';




const props = defineProps({
  hostUrl: { type: Object, required: true }
 
})

const dropdownOptions= ["All",'Active','on hold', 'completed'];





const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');
const userList = inject('userList');
const ProjectApiId = inject("ProjectApiId");


const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);
  

    function toggleDropdown() {
      isOpen.value = !isOpen.value;
    }

    async function selectOption(option) {
      selectedOption.value = option;
      let newUrl;
      newUrl=props.hostUrl + `&filter=statusActive eq '${option}'`;
     
      if(option=="All")
      {
        newUrl= props.hostUrl + `&filter=`;
        console.log("heyy")
      }
      const response = await fetchData(newUrl);
      userList.value = response.items;
      console.log(option)
    
      isOpen.value = false;
    }





</script>