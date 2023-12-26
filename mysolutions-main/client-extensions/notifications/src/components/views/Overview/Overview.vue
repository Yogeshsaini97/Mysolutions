<script setup>
import { inject,onMounted,ref,watch } from 'vue';
import listPageCeVue from '../../../Components/Global/list-page.ce.vue';
import { ChangeDateFormat, applyColorToStatus, applyColorToStatustext, formatTimeTo12HourFormat, makeRequest, makeRequestFetch } from '../../../utils/utils';



const userList=ref(null)
const loader=ref(false);
const i18n = inject('i18n');
// const ProjectOverviewData = inject("ProjectOverviewData");

// /////////////langugage defined start here///////
// const i18n = inject('i18n');
// const showPageOnClick=inject('showPageOnClick');
// const nearestTaskArray=ref({});
// /////////////langugage defined end here///////
// const dataMilestoneApi = inject('dataMilestoneApi');
// const changeTab = inject('changeTab');

// // ----


// const dataRiskApi=inject("dataRiskApi");

// const dataissueApi=inject("dataissueApi");




// // ----
// const changeTabClick = (index) => {
//   changeTab(index);
// }

// const pageLoad=()=>{
//   if(showPageOnClick.value=="risk")
//   {
//     changeTab(3);
//   }
//   if(showPageOnClick.value=="issuse")
//   {
//     changeTab(4);
//   }

// }

// const hostUrl=  `/o/external-api-headless/v1.0/user-notifications/${Liferay?.ThemeDisplay?.getUserId()}`;

const hostUrl= import.meta.env.VITE_DEV_BASE_URL +`/o/c/usernotifications/scopes/20119?fields=id,title,description,user,isRead,dateCreated&restrictFields=actions&filter=user eq 20124`;


// onMounted(async () => {

 
//                 // pageLoad();
//                 // dataMilestoneApi?.payload?.payload?.milestones?upcomingDate():'';.
//                 console.log("inside overview");
//                 loader.value=true;
//         const response = await makeRequestFetch(hostUrl, 'GET', null);
//         console.log("onMounted",response);
//         console.log(response)
        
//         // if(response.message!='ok')
//         // {
//         //   alert(response.message);
//         //   userList.value = await response;
//         //   loader.value=false;
//         //   return;
//         // }

//         userList.value =  await response?.items;
//         console.log(userList.value)
//         loader.value=false;
      
//             });


//             async function handleSearch() {
//       const response = await fetchData(hostUrl + `&pageSize=${pageSize.value}&search=${searchTerm.value}`);
//       userList.value = response.items;
//       lastPageNumber.value=response.lastPage?response.lastPage:1;

//     }

// const upcomingDate=()=>{
// const todayDate = new Date("2016-12-18T00:00:00+03:00");
// //console.log("nearestTask4",todayDate);
// //console.log("nearestTask3",dataMilestoneApi?.payload?.payload?.milestones);
// const filteredTasks = dataMilestoneApi?.payload?.payload?.milestones.filter((task) => {
//   const taskStartDate = new Date(task.taskBaseline0StartDate);
//   return (
//     taskStartDate > todayDate
//   );
// });
// //console.log("nearestTask2",filteredTasks)
// filteredTasks.sort((a, b) => new Date(a.taskBaseline0StartDate) - new Date(b.taskBaseline0StartDate));
// const nearestTask = filteredTasks[0];
// nearestTaskArray.value=nearestTask;
// //console.log("nearestTask",nearestTaskArray.value);
// }

// const formatDateToDay=(dateString)=> {
//   const date = new Date(dateString)
//   const day = date.getDate();
//   return `${day}`;
// }
// const formatDateToMonth=(dateString)=> {
//   const date = new Date(dateString)
//   const monthNames = [
//     'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
//     'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
//   ];
//   const month = monthNames[date.getMonth()];
//   return `${month}`;
// }
// const dateDifference=(date3)=>
// {
// const date1 = new Date("2016-12-18T00:00:00+03:00");
// const date2 = new Date(date3);
// const differenceInMilliseconds = date2 - date1;
// const differenceInDays = differenceInMilliseconds / (1000 * 60 * 60 * 24);
// return Math.round(differenceInDays);
// }
</script>


<template>
  <div class="row">
    <div class="col-md-3 border-right">
      <h2 class="my-4 line-height-15">{{ i18n.global.t('NOTIFICATIONS') }}<br /></h2>
      <!-- <p class="font-12 " style="color: rgba(142, 154, 160, 1);
      ">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p> -->
    </div>
    <div class="col-md-9">
     
          <!-- <table class="table table-sm">
            <tbody>
              <tr v-for="item in userList" :key="item.id">
                <td><img src="../assets/images/avatar1.svg" alt="img"></td>
                <td>David Elson was given member access by Muhammad Asad (Admin) and assigned a Project manager role.<br />Project manager</td>
                <td>Mar 12th, 2023</td>
                <td>4.38 pm</td>
              </tr>
             
            </tbody>
          </table> -->
          <listPageCeVue :url="hostUrl"/>
         
      
    </div>
  </div>
</template>