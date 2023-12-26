<template >
  <div>
   
    <div class="beautiful-dropdown" style="position: absolute; right:65px;">
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
right:20px;"/>
  <table class="table font-weight-600">
    <thead>
      <tr>
        <th>Status</th>
        <th>Team</th>
        <th>Access</th>
        <th>Name & designation</th>
        <th>Contact details</th>
        <th>Added on</th>
        <th></th>
      </tr>
    </thead>

    <tbody v-if="userList.length > 0">
      <tr v-for="item in userList" :key="item.id">
        <td><span class="badge rounded-pill bg-light-success font-10"><i
              class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.stakeholderStatus }}</span></td>
        <td class="font-weight-800">{{ item.team }}<br /></td>

        <td>{{ item.access }}</td>
        <td>{{ item.stakeholderName }}<br /><span class="font-weight-400">{{ item.stakeholderDesignation }}</span></td>
        <td>{{ item.phone }}<br /><span class="font-weight-400">{{ item.email }}</span></td>
        <td>{{ ChangeDateFormat(item.dateCreated) }}</td>

        <td><a href="javascript:void(0)"><img src="http://localhost:8080/documents/d/guest/arrow-up" alt="img"
              @click="() => { ChangePage({ fileName: ProjectTabs, key: 'ProjectTabs' }); ProjectApiId = item.id }" /></a>
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
import { ChangeDateFormat,fetchData } from '../../../Utils/Utils';
import ProjectTabs from "../ProjectTabs.ce.vue"
import DownloadToExcel from '../../Global/DownloadToExcel.ce.vue';


const props = defineProps({
  hostUrl: { type: Object, required: true }
 
})

const dropdownOptions= ["All",'Active','In active'];
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
      newUrl=props.hostUrl + ` and stakeholderStatus eq '${option}'`;
     
      if(option=="All")
      {
        newUrl= props.hostUrl;
        console.log("heyy")
      }
      const response = await fetchData(newUrl);
      userList.value = response.items;
      console.log(option)
    
      isOpen.value = false;
    }

</script>