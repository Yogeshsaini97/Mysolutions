<template>
  <div>
    <HeaderList />
    <component :is="CurrentPage.fileName" :url="projectHostUrl" />
  </div>
</template>

<script setup>
import { provide, reactive, ref } from 'vue';
 import ListPage from '../Global/ListPage.ce.vue';

 import Overview from '../Views/Overview/Overview.vue';
 import HeaderList from '../Global/HeaderList.ce.vue';
 import Feedback from './Feedback/Feedback.vue';

const datacheck=reactive([])
provide('datacheck', datacheck);

const projectHostUrl = `/o/c/supporttickets/?p_auth=${Liferay.authToken}`;
//const userDetailApi = `http://localhost:8080/o/headless-admin-user/v1.0/my-user-account?p_auth=${Liferay.authToken}`;


///////////////

const CurrentPage = reactive({ fileName: ListPage, key: "ListPage" });
provide('CurrentPage', CurrentPage);

//////////////



//////////
const ProjectApiId = reactive({id:null});
provide('ProjectApiId', ProjectApiId);
/////////



///////////////code for breadcrumbs setting start here /////////////////////
const breadcrumbs = ref([
  { label: 'Ticket' }
]);

provide('breadcrumbs', breadcrumbs);

///////////////code for breadcrumbs setting ending here /////////////////////
///////////////code for backbuton  start here /////////////////////

const BackButton = () => {
 if (CurrentPage.key === "ProjectTabs") {
    ChangePage({ fileName: ListPage, key: 'ListPage' })
    console.log(breadcrumbs.value)
    breadcrumbs.value.pop();

  }
  if (CurrentPage.key === "Feedback") {
    ChangePage({ fileName: Overview, key: 'ProjectTabs' })
    console.log(breadcrumbs.value)
  }
}
provide("BackButton", BackButton)


///////////////code for backbuton  ends here /////////////////////


////

const ChangePage = (PageToRender) => {
  CurrentPage.key = PageToRender.key;
  ///////////////setting of ProcessBarReactive start here/////////
  if (CurrentPage.key == "ProjectTabs" ) {
    CurrentPage.fileName = Overview;
    ProjectApiId.id=PageToRender?.item.id;
  }
  if (CurrentPage.key == "Feedback" ) {
    CurrentPage.fileName = Feedback;
  }
  ///////////////setting of ProcessBarReactive end here/////////
  //////////////breadcrumbs changes start here//////////
  if (CurrentPage.key == "ListPage") {
    CurrentPage.fileName = ListPage;
  }
  //////////////breadcrumbs changes end here//////////
}

provide('ChangePage', ChangePage);


///










</script>


