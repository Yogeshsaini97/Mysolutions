<template>
  <div>
    <HeaderList />
    <component :is="CurrentPage.fileName" :url="projectHostUrl" />
  </div>
</template>

<script setup>
import { provide, reactive, ref } from 'vue';
import HeaderList from '../Global/HeaderList.ce.vue';
import Raise_a_ticket_form from './Raise_a_ticket_form/Raise_a_ticket_form.ce.vue';

const datacheck=reactive([])
provide('datacheck', datacheck);

console.log(datacheck)

const projectHostUrl = `/o/c/projectts/?p_auth=${Liferay.authToken}`;


/////////////////////////

const CurrentPage = reactive({ fileName: Raise_a_ticket_form, key: "Raise_a_ticket_form" });
provide('CurrentPage', CurrentPage);

//////////////

//////////
const ProjectApiId = ref(null);
provide('ProjectApiId', ProjectApiId);
/////////

///////////////code for progress bar setting defined start here ///////////
const ProcessBarReactive = reactive({ actualValue: null, plannedValue: null, display: false });
provide('ProcessBarReactive', ProcessBarReactive);
///////////////code for progress bar setting end here /////////////////////



///////////////code for breadcrumbs setting start here /////////////////////
const breadcrumbs = ref([
  { label: 'Raise a ticket' }
]);

provide('breadcrumbs', breadcrumbs);

///////////////code for breadcrumbs setting ending here /////////////////////
///////////////code for backbuton  start here /////////////////////

const BackButton = () => {
  console.log("CurrentPage.key.back", CurrentPage.key)
  // ChangePage({fileName:ListPage,key:'ListPage'})
  if (CurrentPage.key === "ListPage") {

    console.log("hey buddy")
    //kept for further chnages
  }
  else if (CurrentPage.key === "ProjectTabs") {
    ChangePage({ fileName: ListPage, key: 'ListPage' })
    console.log(breadcrumbs.value)
    breadcrumbs.value.pop();

  }
}
provide("BackButton", BackButton)


///////////////code for backbuton  ends here /////////////////////


////

const ChangePage = (PageToRender) => {
  console.log("changepage worked,", PageToRender)
  CurrentPage.fileName = PageToRender.fileName;
  CurrentPage.key = PageToRender.key;
  ///////////////setting of ProcessBarReactive start here/////////
  if (CurrentPage.key == "ProjectTabs") {
    ProcessBarReactive.actualValue = PageToRender?.item.progressActual;
    ProcessBarReactive.plannedValue = PageToRender?.item.progressPlanned;
    ProcessBarReactive.display = true;
  }
  ///////////////setting of ProcessBarReactive end here/////////
  //////////////breadcrumbs changes start here//////////
  if (CurrentPage.key == "ListPage") {
    ProcessBarReactive.display = false;
  }
  //////////////breadcrumbs changes end here//////////
}

provide('ChangePage', ChangePage);


///










</script>


