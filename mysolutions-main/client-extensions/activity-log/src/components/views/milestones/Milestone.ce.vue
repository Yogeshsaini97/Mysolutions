<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/list-page.ce.vue"

const crNumber = inject("crNumber")


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

onMounted(async () => {
  console.log("hello i am milestone")
});


// const milestonesUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/milestone/milestone-list/?projectID=${ProjectApiId.value}`;


</script>

<template>
 <div>hiiii</div>
</template>