<template>
   <nav aria-label="Page navigation example" v-if="totalPages>1">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="javascript:void(0)" aria-label="Previous" @click="prevPage()" :disabled="currentPage === 1">
          <img :src="'/documents/d/mysolutions/pagination-prev'" alt="Previous" />
        </a>
      </li>
      <li  :class="{ 'page-item': true, 'activenew': 1 === currentPage }">
        <a class="page-link" href="#" @click="goToPage(1)" >{{ i18n.global.t('FIRST') }}</a>
      </li>
       <li v-for="page in displayedPages" :key="page" :class="{ 'page-item': true, 'active': page === currentPage }">
        <a class="page-link" href="#" @click="page!='...'?goToPage(page):''" >{{ page }}</a>
      </li> 
      <li  :class="{ 'page-item': true, 'activenew': totalPages === currentPage }">
        <a class="page-link" href="#" @click="goToPage(totalPages)" >{{ i18n.global.t('LAST') }}</a>
      </li>
      <li class="page-item">
        <a class="page-link" href="javascript:void(0)" aria-label="Next" @click="nextPage()" :disabled="currentPage === totalPages">
          <img :src="'/documents/d/mysolutions/pagination-next'" alt="Next" />
        </a>
      </li>
    </ul>
  </nav>
  </template>
  
  <script>
  import { ref, computed, watch,inject } from 'vue';
  
  export default {
    props: {
      totalItems: Number,
      itemsPerPage: Number,
      currentPage: {
      type: Number,
      required: true,
    },
    },
    setup(props, { emit }) {
    /////////////langugage defined start here///////
    const i18n = inject('i18n');
    /////////////langugage defined end here/////// 
      const currentPage = ref(props.currentPage);
  
      const totalPages = computed(() => {
        return Math.ceil(props.totalItems / props.itemsPerPage);
      });
  
      function prevPage() {
        if (currentPage.value > 1) {
          currentPage.value--;
        }
      }
  
      function nextPage() {
        if (currentPage.value < totalPages.value) {
          currentPage.value++;
        }
      }

      function goToPage(pageNumber) {
       currentPage.value = pageNumber; 
       }
  
      // Watch for changes in totalItems and itemsPerPage and reset currentPage
      watch([() => props.totalItems, () => props.itemsPerPage], () => {
        currentPage.value = 1;
      });
  
      // Emit the current page to the parent component whenever it changes
      watch(currentPage, (newPage) => {
        emit('update:currentPage', newPage);
      });

      const displayedPages = computed(() => {
            const startPage = Math.max(1, currentPage.value - Math.floor(props.totalItems / 2));
            const endPage = Math.min(totalPages.value, startPage + props.totalItems - 1);
            const differencePage=endPage-startPage;
            const differenceCurrentPage=endPage-currentPage.value;
            const pages = [];
            if(currentPage.value>0 && currentPage.value!=endPage && (differencePage>4 && differenceCurrentPage>4) )
            {
              for (let i = currentPage.value; i <= currentPage.value+2; i++) {
                pages.push(i);
              }
              pages.push("...");
            }
           else if(currentPage.value>0 && currentPage.value!=endPage && (differencePage> 4 && (differenceCurrentPage<= 4 || differenceCurrentPage==0)) )
            {
              pages.push("...");
              for (let i = currentPage.value; i <= currentPage.value+2; i++) {
                if(i<=endPage)
                {
                  pages.push(i);
                }
              }
            }
            else if(currentPage.value>0  && currentPage.value==endPage && (differencePage> 4 && (differenceCurrentPage<= 4 || differenceCurrentPage==0)) )
            {
              pages.push("...");
              for (let i = currentPage.value-2; i <= currentPage.value; i++) {
                if(i<=endPage)
                {
                  pages.push(i);
                }
              }
            }
            else{
              for (let i = startPage; i <= endPage; i++) {
                pages.push(i);
            }
            }
            
            return pages;
            });
  
      return {
        currentPage,
        totalPages,
        prevPage,
        nextPage,
        displayedPages,
        goToPage,
        i18n
      };
    },
  };
  </script>
  