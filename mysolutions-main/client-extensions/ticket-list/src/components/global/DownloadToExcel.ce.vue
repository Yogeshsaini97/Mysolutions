<template>
    <a @click="downloadFile()" href="javascript:void(0)" class="downloadexcel btn btn-primary align-center rounded-10 ml-2" ><img
        src="http://localhost:8080/documents/d/guest/cloud-download-white" style="" alt="img" /></a>
</template>


<script setup>
import exportFromJSON from "export-from-json";
import { inject } from "vue";

const userList = inject('userList');

// let DataForExcel= userList.value;


 
   const downloadFile=()=> {
    // console.log(DataForExcel);
    //   let data = DataForExcel;
    let data=userList.value
      const fileName = "Projects";
      const exportType = exportFromJSON.types.csv;

      if (data) exportFromJSON({ data, fileName, exportType });

   }
  

</script>
