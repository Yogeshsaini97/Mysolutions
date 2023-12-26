<template>

    <td>{{userAccountdata.value &&   userAccountdata.value.phoneNumber }}</td>
    <td>{{userAccountdata.value &&   userAccountdata.value.emailAddress }}</td>

  


   
</template>
<script setup>
import { onMounted, reactive } from "vue";
import { fetchData } from "../../../Utils/Utils";

let userAccountdata=reactive({});

const props = defineProps({
    userAccntid: { type: Number, required: true },
    urlName: { type: Number, required: true }
 
})

let userAccountUrl= import.meta.env.VITE_APP_ROOT_API+`/${props.urlName}/${props.userAccntid}?p_auth=${Liferay.authToken}`;

onMounted(async () => {
  
      try {
        console.log(userAccountUrl)
        const response = await fetchData(userAccountUrl);
        console.log(response)
        userAccountdata.value = response;
       
      }
      catch (error) {
        console.error(error);
      }
    });



</script>