<script setup>
import { ref, onMounted, inject, provide } from 'vue';
const props = defineProps(['customClass']);
let tabContainer = ref(null);
let tabHeaders = ref(null);
let tabs = ref(null);
let activeTabIndex = ref(0);
const updateDownloadFilter = inject("updateDownloadFilter");
// injecting funtion to limit  api  starts here

const showTabOnCLick = inject('showTabOnCLick');


// injecting funtion to limit api ends here



onMounted(() => {
  tabs.value = [...tabContainer.value.querySelectorAll('.tab')];
  for (let x of tabs.value) {
    if (x.classList.contains('active')) {
      activeTabIndex = tabs.value.indexOf(x);
    }
  }
})
const changeTab = (index) => {
  const indexToTypeMap = {
  2: "milestones",
  3: "risk",
  4: "issuse",
  5: "document",
  6: "approval",
  7: "invoice",
  8: "stakeHolder",
};

if (indexToTypeMap.hasOwnProperty(index)) {
  updateDownloadFilter({ value: null, type: indexToTypeMap[index] });
}
else{
  updateDownloadFilter({value:null,"type":"no"})
}

  showTabOnCLick[index].value = true;
  activeTabIndex = index;
  if (Array.isArray(tabs.value)) {
  // It's an array, so you can safely iterate over it.
  for (let x of [...tabs.value, ...tabHeaders.value]) {
    x.classList.remove('active')
  }
  tabs.value[activeTabIndex].classList.add('active')
  tabHeaders.value[activeTabIndex].classList.add('active')
} else {
  console.error("tabs.value is not an array.");
}

}
provide("changeTab", changeTab)
</script>

<template>
  <div id="tabs-container" :class="customClass" ref="tabContainer">
    <div id="tab-headers">
      <ul class="Tab-header" style="display: flex;
      flex-wrap: wrap;border-bottom: 1px solid #ddd;">
        <!-- this shows all of the titles -->
        <li style="    transition: background-color 0.3s ease;" v-for="(tab, index) in tabs" :key="index"
          :class="activeTabIndex == index ? 'active font-weight-600 ' : ' font-weight-600'" @click="changeTab(index)"
          ref="tabHeaders">{{ tab.title }}</li>
      </ul>
    </div>
    <!-- this is where the tabs go, in this slot -->
    <div id="active-tab">
      <slot></slot>
    </div>
  </div>
</template>
