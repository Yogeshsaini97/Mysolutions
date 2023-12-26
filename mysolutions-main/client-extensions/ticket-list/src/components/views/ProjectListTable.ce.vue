<template>
  <div> 
    <div class="beautiful-dropdown" style="position: absolute;
    top: -8.5rem;
    right: 18rem;

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
        top: -8.5rem;
        right: 12rem;
        z-index: 10;
        "/>

        <table class="table font-weight-600">
 <thead>
            <tr>
                <th>Status</th>
                <th>Type</th>
                <th>Support ID</th>
                <th>Support title</th>
                <th>Priority</th>
                <th>Raised by</th>
                <th>Raised on</th>
                <th>Closed on</th>
                <th></th>
            </tr>
        </thead>

        <tbody v-if="userList.length > 0">
            <tr v-for="item in userList" :key="item.id">
                <td><span class="badge rounded-pill bg-light-success font-10"><i
                            class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item?.status?.label_i18n }}</span></td>
                <td>{{ item?.ticketType }}</td>
                <td>ID - {{ item.id }}</td>
                <td>{{ item?.issuseTitle }}</td>
                <td>{{ item?.priority ?? 'Low' }}</td>
                <td>{{ item?.creator?.familyName }}</td>
                <td>{{ ChangeDateFormat(item?.dateCreated) }}</td>
                <td>{{ item?.completionDate ? ChangeDateFormat(item.completionDate):'' }}</td>
                <td><a href="javascript:void(0)"><img src="http://localhost:8080/documents/d/guest/arrow-up" alt="img"
                            @click="() => { ChangePage({ fileName: 'Overview', key: 'ProjectTabs',item}); 
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
import { inject,  ref } from 'vue';
import { ChangeDateFormat,fetchData } from '../../Utils/Utils';
import DownloadToExcel from '../Global/DownloadToExcel.ce.vue';




const props = defineProps({
  hostUrl: { type: Object, required: true }
 
})

const dropdownOptions= ["All",'Active','on hold', 'completed'];





const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');
const userList = inject('userList');


const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);
const pageSizeNum=inject('pageSizeNum');

    function toggleDropdown() {
      isOpen.value = !isOpen.value;
    }

    async function selectOption(option) {
      selectedOption.value = option;
      let newUrl;
      newUrl=props.hostUrl + `&pageSize=${pageSizeNum}&filter=statusActive eq '${option}'`;
     
      if(option=="All")
      {
        newUrl= props.hostUrl + `&pageSize=${pageSizeNum}&filter=`;
        console.log("heyy")
      }
      const response = await fetchData(newUrl);
      userList.value = response.items;
      console.log("option", option)
      
    
      isOpen.value = false;
    }





</script>