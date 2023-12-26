<template>
    <a @click="downloadFile()" href="javascript:void(0)"
        class="downloadexcel btn btn-primary font-12 align-center rounded-10 ml-2"><img
            :src="'/documents/d/mysolutions/download_excel-1'"
            style="scale: 0.6;" alt="img" /></a>
</template>
<script setup>
    import exportFromJSON from "export-from-json";
    import {
        inject
    } from "vue";
    const props = defineProps({
        CRandProjectnumber: {
            type: Object,
            required: true
        },
        data: {
            type: Object,
            required: true
        },
        fileName: {
            type: String,
            required: true
        }
    })
    
    const userList = inject('userList');
    // let DataForExcel= userList.value;
    const downloadFile = () => {
        console.log(props.CRandProjectnumber + "ri")
        let data = props.data;;
        const fileName = props.CRandProjectnumber + '_' + props.fileName +
            '_' + new Date().toISOString().slice(0, 10).replace(/-/g, '');
        const exportType = exportFromJSON.types.csv;
        if (data) exportFromJSON({
            data,
            fileName,
            exportType
        });
    }
</script>