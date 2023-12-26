<template>
    <div class="app">
        {{ props.isPopupVisible }}


        <div s v-if="isPopupVisible" class="popup-overlay">

            <img style="height: 18rem;" src="http://localhost:8080/documents/d/guest/supportticketsuccess" alt="error">

            <button @click="closePopup">Close</button>

        </div>
    </div>
</template>
  
<script setup>
import { inject, ref } from 'vue';
const props = defineProps({
    Content: { type: String, required: true }

})

const isPopupVisible = inject('isPopupVisible');




const closePopup = () => {
    isPopupVisible.value = false;
    window.location.href = '/tickets';
};



</script>
  
