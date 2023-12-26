<template>
  <div>
    <HeaderList />
    <component :is="CurrentPage.fileName"  />
  </div>
</template>

<script setup>
import { provide, reactive, ref } from 'vue';
import HeaderList from '../Global/HeaderList.ce.vue';
import ProjectTabs from './ProjectTabs.ce.vue';
console.log("check")

const hy=import.meta.env.VITE_APP_ROOT_API;
console.log(import.meta.env.VITE_APP_ROOT_API)

console.log(hy)

const datacheck=reactive([])
provide('datacheck', datacheck);

console.log(datacheck)

const projectHostUrl = `${import.meta.env.VITE_APP_ROOT_API}/projectts/?p_auth=${Liferay.authToken}`;


///////////////

const CurrentPage = reactive({ fileName: ProjectTabs, key: "ProjectTabs" });
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
  { label: 'Team members' },
  { label: 'Add new' }
]);

provide('breadcrumbs', breadcrumbs);

///////////////code for breadcrumbs setting ending here /////////////////////




///////////////code for backbuton  start here /////////////////////

const BackButton = () => {
  console.log("CurrentPage.key.back", CurrentPage.key)
  // ChangePage({fileName:ListPage,key:'ListPage'})
  if (CurrentPage.key === "MemberAccess") {

    console.log("hey buddy")
    //kept for further chnages
  }
  else if (CurrentPage.key === "ProjectTabs") {
    ChangePage({ fileName: MemberAccess, key: 'MemberAccess' })
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

  ///////////////setting of ProcessBarReactive end here/////////

  //////////////breadcrumbs changes end here//////////
}

provide('ChangePage', ChangePage);


///



</script>


