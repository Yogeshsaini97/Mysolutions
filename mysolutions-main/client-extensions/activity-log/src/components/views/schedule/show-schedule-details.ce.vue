<template>
  <div>
   
    <div class="table-responsive mt-3">
      <table id="scheduleTable" class="table">
        <thead v-if="showHideHeader">
          <tr>
            <th >{{ i18n.global.t('PROJECT_SCHEDULE.SR_NO') }}</th>
            <th>{{ i18n.global.t('PROJECT_SCHEDULE.STATUS') }} </th>
            <th>{{ i18n.global.t('PROJECT_SCHEDULE.MILESTONES') }}</th>
            <th>{{ i18n.global.t('PROJECT_SCHEDULE.PREDECESSORS') }}</th>
            <th v-for="(week, index) in weeks" :key="index">
              {{ week?.split(': ')[0] }}<br />
              <span class="font-10 text-dark">{{ week?.split(': ')[1] }}</span>
            </th>
          </tr>
        </thead>
        <tbody v-if="taskData != null && taskData.length > 0 && taskData != 'Success' && taskData != 'N/A'">
          <tr v-for="(task, rowindex) in taskData" :key="rowindex" :title="setTooltip(task)">
            <td  class="mtitle mb-0 font-weight-800 user-select-none">{{ rowindex + 1 }} </td>
             <td><span :class="`badge rounded-pill font-10 ` + applyColorToStatus(task.status)" ><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ task.status ?
        task.status : 'N/A' }}</span></td>
            <td>
              <p class="mtitle mb-0 pb-1 user-select-none"  >{{ task.taskName }}</p>
              <div class="row">
                <div class="col-auto pr-0">
                  <span
                    :class="{ 'red-small-dot-top': getRowColorClass(task, rowindex) == 'red', 'lightgreen-small-dot-top': getRowColorClass(task, rowindex) == 'lightgreen', 'grey-small-dot-top': getRowColorClass(task, rowindex) == 'grey', 'green-small-dot-top': getRowColorClass(task, rowindex) == 'green', 'default-small-dot-top': getRowColorClass(task, rowindex) == 'default' }"></span>
                  <span class="vertical-line"></span>
                  <span
                    :class="{ 'red-small-dot-bottom': getRowColorClass(task, rowindex) == 'red', 'lightgreen-small-dot-bottom': getRowColorClass(task, rowindex) == 'lightgreen', 'grey-small-dot-bottom': getRowColorClass(task, rowindex) == 'grey', 'green-small-dot-bottom': getRowColorClass(task, rowindex) == 'green', 'default-small-dot-bottom': getRowColorClass(task, rowindex) == 'default' }"></span>
                </div>
                <div class="col pl-10">
                  <p class=" font-weight-800 mb-0 pb-1 font-12">{{ task.baselineStartDate ? ChangeDateFormat(task.baselineStartDate) : '' }}</p>
                  <p class=" font-weight-800 mb-0 font-12">{{ task.baselineFinishDate ? ChangeDateFormat(task.baselineFinishDate) : '' }}</p>
                </div>
              </div>
            </td>
            <td  class="mtitle mb-0 font-weight-800 user-select-none">{{ parentTaskId(task.parentTaskID) }} </td>
            <td v-for="(week, index) in weeks" :key="index">
              <a class="beautifullTooltip-container d-flex" href="#" >
                <div  @mouseover="showTooltip = true" @mouseleave="showTooltip = false"  v-for="(day, dayIndex) in weeksDateRange[index]" :key="dayIndex"
                  :class="{ 'square square-red': checkDateExist(task, day) && getRowColorClass(task, rowindex) == 'red', 'square square-green': checkDateExist(task, day) && getRowColorClass(task, rowindex) == 'green', 'square square-lightgreen': checkDateExist(task, day) && getRowColorClass(task, rowindex) == 'lightgreen', 'square square-grey': checkDateExist(task, day) && getRowColorClass(task, rowindex) == 'grey', 'square square-default': checkDateExist(task, day) && getRowColorClass(task, rowindex) == 'default', 'square': !checkDateExist(task, day) }"> 
                  <div v-if="showTooltip" class="beautifullTooltip">
                    {{ setTooltip(task) }}
                  </div>
                </div>
              </a>
            </td>

          </tr>
        </tbody>
        <tbody v-else-if="taskData != null && taskData.length == 0 && taskData != 'Success' && taskData != 'N/A'">
          <tr>
            <td colspan="2">End of Data</td>
          </tr>
        </tbody>
        <tbody v-else-if="taskData == 'Success' || taskData == 'N/A'">
          <tr>

            <ErrorMessageDialogBox :WarningMessage="i18n.global.t('HELLO')"
              :message="i18n.global.t('HELLO!_LOOKS_LIKE_THE_SCHEDULE_FOR_THIS_PROJECT_IS_NOT_AVAILABLE._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR.')"
              btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


          </tr>
        </tbody>

        <tbody v-else-if="taskData != null && taskData.key == 'server error'">
          <tr>

            <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
              :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
              btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


          </tr>
        </tbody>
        <tbody v-else-if="taskData == null">
          <tr>
            <td colspan="7">
              <TableSkeletonLoader />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script setup>
import { inject, ref, watch } from 'vue';
import { ChangeDateFormat, applyColorToStatus } from '../../../utils/utils';
import Loader from '../../global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const showHideHeader = ref(true);

const showTooltip = ref(false);

const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}


const userList = inject('userList');
const taskData = ref(null);
const weeks = ref([]);
const weeksDate = ref([]);
const weeksDateRange = ref([]);


const schudleWatch = (val) => {
  const taskValue = val;
  console.log("taskvalue",taskValue);
  console.log(taskValue.payload.tasks)

  if (taskValue.status >= 200 && taskValue.status < 300 && taskValue.message == "ok" ) {
    if ( taskValue.payload.code == "200") {
      console.log("success")
      taskData.value = "Success";
      return;
    }
    else if ( Object.keys(taskValue.payload).length === 0) {
      console.log("N/A")
      taskData.value = 'N/A';
      return;

    }
    else if (taskValue.payload.tasks && taskValue.payload.tasks.length > 0) {
      console.log(taskValue.payload.tasks);
      console.log("ok")
      taskData.value = taskValue.payload.tasks;
      lastAndFristDate(taskData.value);
      console.log(taskData.value.length)
      return;

    }
    else{
      console.log("inside main first error")
    taskData.value = { key: "server error", message: taskValue.message };
    return;

    }

  }

  else {

    console.log("inside main second error")
    taskData.value = { key: "server error", message: taskValue.message };
    return;

  }
}

const lastAndFristDate = (taskData) => {
  if (taskData.length === 0) {
    console.log('No task data provided.');
    return;
  }

  let smallestStartDate = new Date(taskData[0].baselineStartDate);
  let largestFinishDate = new Date(taskData[0].baselineFinishDate);

  taskData.forEach((task) => {
    const baselineStartDate = new Date(task.baselineStartDate);
    const baselineFinishDate = new Date(task.baselineFinishDate);

    if (baselineStartDate < smallestStartDate) {
      smallestStartDate = baselineStartDate;
    }

    if (baselineFinishDate > largestFinishDate) {
      largestFinishDate = baselineFinishDate;
    }
  });


  weekShow(smallestStartDate, largestFinishDate, weeks);

};

const weekShow = (smallestStartDate, largestFinishDate, weeks) => {
  const millisecondsInADay = 24 * 60 * 60 * 1000;

  let currentWeekStartDate = new Date(smallestStartDate);
  currentWeekStartDate.setUTCHours(0, 0, 0, 0);

  while (currentWeekStartDate.getDay() !== 1) {
    currentWeekStartDate.setDate(currentWeekStartDate.getDate() - 1);
  }

  const largestFinishDatePlusOne = new Date(largestFinishDate);
  largestFinishDatePlusOne.setDate(largestFinishDatePlusOne.getDate() + 1);

  let weekNumber = 1;

  while (currentWeekStartDate <= largestFinishDatePlusOne) {
    const currentWeekEndDate = new Date(currentWeekStartDate);
    currentWeekEndDate.setDate(currentWeekEndDate.getDate() + 6);

    const weekStartDateStr = currentWeekStartDate.toLocaleDateString('en-US', {
      day: 'numeric',
    });
    const weekEndDateStr = currentWeekEndDate.toLocaleDateString('en-US', {
      day: 'numeric',
    });
    const weekStartDateStrMonth = currentWeekStartDate.toLocaleDateString('en-US', {
      day: 'numeric',
      month: 'numeric',
      year: 'numeric'
    });
    const weekEndDateStrMonth = currentWeekEndDate.toLocaleDateString('en-US', {
      day: 'numeric',
      month: 'numeric',
      year: 'numeric'
    });
    if (i18n.global.locale == "en_US") {
      weeks.value.push(
        `Week ${weekNumber}: ${dateType(weekStartDateStr)} - ${dateType(weekEndDateStr)}`
      );
    } else {
      weeks.value.push(
        `${weekNumber} ${i18n.global.t('PROJECT_SCHEDULE.WEEK')}: ${weekStartDateStr} - ${weekEndDateStr}`);
    }

    weeksDate.value.push(`${weekStartDateStrMonth}-${weekEndDateStrMonth}`);
    dateRange(`${weekStartDateStrMonth}-${weekEndDateStrMonth}`);

    currentWeekStartDate.setDate(currentWeekStartDate.getDate() + 7);
    weekNumber++;
  }
}

const dateType = (day) => {
  let daySuffix = 'th';
  if (day >= 11 && day <= 13) {
    daySuffix = 'th';
  } else {
    switch (day % 10) {
      case 1:
        daySuffix = 'st';
        break;
      case 2:
        daySuffix = 'nd';
        break;
      case 3:
        daySuffix = 'rd';
        break;
      default:
        daySuffix = 'th';
        break;
    }
  }
  return day + " " + daySuffix;
};



const dateRange = (val) => {
  const [startDateStr, endDateStr] = val?.split('-');


  const startDate = new Date(startDateStr);
  const endDate = new Date(endDateStr);

  if (isNaN(startDate.getTime()) || isNaN(endDate.getTime())) {
    console.error('Invalid date range:', val);
    return;
  }

  const dateArray = [];

  let currentDate = new Date(startDate);
  while (currentDate <= endDate) {
    const month = currentDate.getMonth() + 1;
    const day = currentDate.getDate();
    const year = currentDate.getFullYear();
    dateArray.push(`${month}_${day}_${year}`);
    currentDate.setDate(currentDate.getDate() + 1);
  }

  weeksDateRange.value.push(dateArray);
};


const checkDateExist = (task, targetDate) => {
  const baselineStartDate = task.baselineStartDate;
  const baselineFinishDate = task.baselineFinishDate;
  const targetDateStr = targetDate.toString();

  const baselineStartDateObj = new Date(baselineStartDate);
  const baselineFinishDateObj = new Date(baselineFinishDate);
  const [targetMonth, targetDay, targetYear] = targetDateStr?.split('_');

  const targetDateObj = new Date(targetYear, targetMonth - 1, targetDay);
  baselineStartDateObj.setHours(0, 0, 0, 0);
  baselineFinishDateObj.setHours(0, 0, 0, 0);
  return (
    targetDateObj >= baselineStartDateObj &&
    targetDateObj <= baselineFinishDateObj
  );
};


const getRowColorClass = (Task, rowIndex) => {
  const colors = ["lightgreen", "green", "grey", "default", "red"];
  if (Task?.status == "Completed") {
    return colors[1];
  }
  else if (Task?.status == "Overdue") {
    return colors[4];
  }
  else if (Task?.status == "On Time") {
    return colors[0];
  }
  else if (Task?.status == "Not Started") {
    return colors[2];
  }
  else {
    return colors[3];
  }
}


const parentTaskId = (val) => {
  console.log("value finder", val);
  const idToFind = val;

  const result = findParentTaskIndexById(idToFind, taskData.value);

  if (result) {
    //console.log(`ID: ${idToFind}`);
    //console.log(`Parent Task ID: ${result.parentTaskID}`);
    //console.log(`Index: ${result.index}`);
    return `${result.index + 1}`;
  } else {
    //console.log(`ID ${idToFind} not found in the task data.`);
    return i18n.global.t('N/A');
  }

}

const findParentTaskIndexById = (idToFind, taskData) => {
  for (let i = 0; i < taskData.length; i++) {
    if (taskData[i].ID === idToFind) {
      // If the current task matches the ID, return its parentTaskID and index
      return { parentTaskID: taskData[i].parentTaskID, index: i };
    }
  }
  // Return null if the ID is not found
  return null;
}



const setTooltip = (task) => {
  return `${task.taskName}\n${task.baselineStartDate ? ChangeDateFormat(task.baselineStartDate) : ''} - ${task.baselineFinishDate ? ChangeDateFormat(task.baselineFinishDate) : ''
    }
  `;



}








watch(userList, (newValue, oldValue) => {
  if (newValue) {
    schudleWatch(newValue);
  }
})


</script>