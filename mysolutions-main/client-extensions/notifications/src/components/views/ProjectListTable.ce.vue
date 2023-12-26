<template>
  <div> 
    
        <!-- <DownloadToExcel style="position: absolute;
        top: -8.5rem;
        right: 12rem;
        z-index: 10;
        "/> -->

        <table class="table font-weight-600 p-0">
          <thead>
            <tr>
              <th>{{ i18n.global.t('STATUS') }}</th>
                <th>{{ i18n.global.t('NOTIFICATIONS') }}</th>
                <th>{{ i18n.global.t('DATE') }}</th>
                <th>{{ i18n.global.t('TIME') }}</th>
                <th></th>
               
            </tr>
            </thead >

        <tbody v-if="userList?.length > 0">
            <tr v-for="(item) in userList" :key="item.id">
                 <td><span :class="'badge rounded-pill font-10 ' + (item?.isRead == false
            ? ' bg-light-success '

            : ' StatusCanceled ')"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>
              {{ item?.isRead == false ? "Unread" : "Read" }}</span></td>
              <td  >{{ item?.title  }}  .</td>
             
                <td class="font-weight-400">{{ item ? ChangeDateFormat(item?.dateCreated) : "N/A" }}</td>
                <td class="font-weight-400" >{{ item ? formatTimeTo12HourFormat(item?.dateCreated) : "N/A" }}</td>
                <td data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"><a
                  href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" alt="img" class="img-flip"
                    @click="() => toggleSidebar(item)" /></a>
              </td>
                
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
              opacity: 0.2;">{{ i18n.global.t('NO_NOTIFICATIONS_RIGHT_NOW') }}</td>
          </tr>
      </tbody>
      <tbody v-else-if="userList==null">
        <tr>
            <td colspan="7"><tableskeletonloader/></td>
        </tr>
    </tbody>
    </table>
    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebar && notificationId != null">
            <showNotificationsDetails :id="notificationId" notificationData="details">
            </showNotificationsDetails>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
      </div>
    </div>
    </div>
</template>



  
  

<script setup>
import { inject,  provide,  ref } from 'vue';
import { ChangeDateFormat,fetchData,formatTimeTo12HourFormat } from '../../Utils/Utils';
import tableskeletonloader from '../global/table-skeleton-loader.ce.vue';
import showNotificationsDetails from './show-notifications-details.ce.vue';
// import DownloadToExcel from '../Global/DownloadToExcel.ce.vue';


const i18n = inject('i18n');

const props = defineProps({
  hostUrl: { type: Object, required: true }
 
})

const dropdownOptions= ["All",'Active','on hold', 'completed'];


function toggleSidebar(notificationValue) {
  isOpenSidebar.value = !isOpenSidebar.value;
  notificationId.value = notificationValue.id;
  // selectStakeHolderData.value = notificationValue;
};
provide("toggleSidebar",toggleSidebar)


const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');
const userList = inject('userList');
const isOpenSidebar = ref(false);
const notificationId = ref(null);
const selectnotificationData = ref(null);


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