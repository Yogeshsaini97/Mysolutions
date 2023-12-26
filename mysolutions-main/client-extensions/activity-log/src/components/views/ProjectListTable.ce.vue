<template>
  <div> 
    
        <!-- <DownloadToExcel style="position: absolute;
        top: -8.5rem;
        right: 12rem;
        z-index: 10;
        "/> -->

        <table class="table font-weight-600 p-0">
 

        <tbody v-if=" userList!=null && userList.length > 0">
            <tr v-for="(item) in userList" :key="item.id">
                <td  >You  {{ item?.creator?.name }} {{ item?.description  }}  .</td>
                <td class="font-weight-400" >{{ item?ChangeDateFormat(item.dateCreated):"N/A" }}</td>
                <td class="font-weight-400" >{{ item?formatTimeTo12HourFormat( item.dateCreated):"N/A" }}</td>

                
              </tr>
        </tbody>
        <tbody v-else-if="userList!=null && userList.length ==0">
            <tr>
                <td colspan="7" style="display: flex;
                align-items: center;
                justify-content: center;
                align-content: center;
                height: 10rem;
                color: grey;
                opacity: 0.2;">{{ i18n.global.t('NO_ACTIVITY_TO_SHOW') }}</td>
            </tr>
        </tbody>
        <tbody v-else-if="userList==null">
          <tr>
              <td colspan="7"><tableskeletonloader/></td>
          </tr>
      </tbody>
    </table>
    </div>
</template>



  
  

<script setup>
import { inject,  ref } from 'vue';
import { ChangeDateFormat,fetchData, formatTimeTo12HourFormat } from '../../Utils/Utils';
// import DownloadToExcel from '../Global/DownloadToExcel.ce.vue';
import tableskeletonloader from '../Global/table-skeleton-loader.ce.vue'

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////


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
      console.log(option)
    
      isOpen.value = false;
    }





</script>