
<script setup>
import { inject, provide, ref } from 'vue';
import { ChangeDateFormat,fetchData } from '../../../Utils/Utils';
import DownloadToExcel from '../../Global/DownloadToExcel.ce.vue';





const props = defineProps({
  hostUrl: { type: Object, required: true }
 
})

const dropdownOptions= ["All",'Approved','Rejected'];





const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');
const userList = inject('userList');
const ProjectApiId = inject("ProjectApiId");

console.log(userList)


const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);
  

    function toggleDropdown() {
      isOpen.value = !isOpen.value;
    }

    async function selectOption(option) {
      selectedOption.value = option;
    
      let newUrl;
      newUrl=props.hostUrl + ` and documentStatus eq '${option}'`;
     
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







<template>
  <div><div class="beautiful-dropdown" style="position: absolute; right:65px;">
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
            <th>Category</th>
            <th>Title & ID</th>
            <th>Added by</th>
            <th>Added on</th>
            <th></th>
          </tr>
        </thead>
        <tbody v-if="userList.length > 0">
          <tr  v-for="item in userList" :key="item.id">
            <td><span class="badge rounded-pill bg-light-success text-dark font-10"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.documentStatus }}</span></td>
            <td>{{ item.category }}</td>
            <td>{{ item.documentName }}<br /><span class="font-weight-400">STCS - {{ item.id }}</span></td>
            <td>{{ item.creator.name }}<br /><span class="font-weight-400">STCS - {{ item.creator.contentType }}</span></td>
            <td>{{ ChangeDateFormat(item.dateCreated) }}</td>
            <td><a :href="`http://localhost:8080/${item.attachment.link.href}`"><img src="http://localhost:8080/documents/d/guest/arrow-up" alt="img" /></a></td>
          </tr>
          
        </tbody>
      </table>
      </div>
   
</template>


