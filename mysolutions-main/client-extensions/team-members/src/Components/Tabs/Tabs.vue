<script setup>
  import { ref, onMounted, reactive } from 'vue';
  const props = defineProps([ 'customClass' ]);
  let tabContainer = ref(null);
  let tabHeaders = ref(null);
  let tabs = ref(null);
  let activeTabIndex = ref(0);

  onMounted(() => {
    tabs.value = [ ...tabContainer.value.querySelectorAll('.tab') ];
		for(let x of tabs.value) {
	    if(x.classList.contains('active')) {
				activeTabIndex = tabs.value.indexOf(x);
			}
    }
  })
  const changeTab = (index) => {
 
    activeTabIndex = index;
    for(let x of [...tabs.value, ...tabHeaders.value]) {
   		x.classList.remove('active')
    }
		tabs.value[activeTabIndex].classList.add('active')  
		tabHeaders.value[activeTabIndex].classList.add('active')  
  }
</script>

<template>
  <div id="tabs-container" :class="customClass" ref="tabContainer">
    <div id="tab-headers">
      <ul class="Tab-header" style="height: 2.5rem">
        <!-- this shows all of the titles --> 
        <li v-for="(tab, index) in tabs" :key="index" :class="activeTabIndex == index ? 'active' : ''" @click="changeTab(index)" ref="tabHeaders">{{ tab.title }}</li>
      </ul>
    </div>
    <!-- this is where the tabs go, in this slot -->
    <div id="active-tab">
    	<slot></slot>
    </div>
  </div>
</template>
