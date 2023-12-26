<template>
  <div>

    <div class="">
      <div class="">

        <h4 class="mb-30 ml-3 mt-2">{{ i18n.global.t('OVERVIEW') }}</h4>
        <div class="row" v-if="(projectStatus != 'server error' && crNumber != 'NoData')">


          <!-- 
          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                
                <p class="mb-0 font-40 text-default">{{ projectStatus != 'server error' ? ProjectsJSON.inProgress :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('IN_PROGRESS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
             
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div> -->

          <!-- <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                
                <p class="mb-0 font-40 text-default">{{ projectStatus != 'server error' ? ProjectsJSON.hoInProgress :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('HO_IN_PROGRESS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
             
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

        
        

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                
                <p class="mb-0 font-40 text-default">{{ projectStatus != 'server error' ? ProjectsJSON.onHold :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('ON_HOLD') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
             
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>
          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                
                <p class="mb-0 font-40 text-default">{{ projectStatus != 'server error' ? ProjectsJSON.Completed :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('COMPLETED') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
             
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>
          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                
                <p class="mb-0 font-40 text-default">{{ projectStatus != 'server error' ? ProjectsJSON.closed :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('CLOSED') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
             
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div> -->





          <!-- ----- new dashboard --------------- -->


          <div class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loader == false">
                <p class="mb-0 font-40 text-default">0</p>
                <p class="mb-0">{{ i18n.global.t('APPROVALS') }}
                  <img :src="'/documents/d/mysolutions/arrow-up '" class="ml-1 w-15p" alt="img" />
                </p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('YOU_HAVE') + " " }}<span
                    class="text-danger opacity-100 font-weight-900">0
                    pending</span>{{ " " + i18n.global.t('APPROVALS') }}</p>
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>
            </div>
          </div>


          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                <div style="display: flex;
      align-content: center;
      align-items: stretch;
      justify-content: space-between;">
                  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error'
                    ? ProjectsJSON.inProgress :
                    'N/A' }}</p>
                  <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
                    <div class="rectangle-green  justify-content-end" style="transform: rotate(90deg);  width: 30px;
          height: 30px;">

                    </div>
                  </div>
                </div>


                <p class="mb-0">{{ i18n.global.t('IN_PROGRESS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>

              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                <div style="display: flex;
      align-content: center;
      align-items: stretch;
      justify-content: space-between;">
                  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error'
                    ? ProjectsJSON.hoInProgress :
                    'N/A' }}</p>
                  <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
                    <div class="rectangle-lightgreen  justify-content-end" style="transform: rotate(90deg);  width: 30px;
          height: 30px;">

                    </div>
                  </div>
                </div>


                <p class="mb-0">{{ i18n.global.t('HO_IN_PROGRESS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>

              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                <div style="display: flex;
      align-content: center;
      align-items: stretch;
      justify-content: space-between;">
                  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error'
                    ? ProjectsJSON.onHold :
                    'N/A' }}</p>
                  <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
                    <div class="rectangle-orange  justify-content-end" style="transform: rotate(90deg);  width: 30px;
          height: 30px;">

                    </div>
                  </div>
                </div>


                <p class="mb-0">{{ i18n.global.t('ON_HOLD') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>

              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                <div style="display: flex;
      align-content: center;
      align-items: stretch;
      justify-content: space-between;">
                  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error'
                    ? ProjectsJSON.Completed :
                    'N/A' }}</p>
                  <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
                    <div class="rectangle-blue  justify-content-end" style="transform: rotate(90deg);  width: 30px;
          height: 30px;">

                    </div>
                  </div>
                </div>


                <p class="mb-0">{{ i18n.global.t('COMPLETED') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>

              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40">

              <div class="card-body" v-if="loader == false">
                <div style="display: flex;
      align-content: center;
      align-items: stretch;
      justify-content: space-between;">
                  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error'
                    ? ProjectsJSON.closed :
                    'N/A' }}</p>
                  <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
                    <div class="rectangle-grey  justify-content-end" style="transform: rotate(90deg);  width: 30px;
          height: 30px;">

                    </div>
                  </div>
                </div>


                <p class="mb-0">{{ i18n.global.t('CLOSED') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>

              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>





        </div>


        <div class="row p-3" v-else-if="crNumber == 'NoData'">
          <div class="col-md-12">
            <div class="card shadow mb-40 bg-wts">
              <div class="row" style="    justify-content: center;">
                <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;">
                  <h2 class="text-default font-weight-600">CR Number Not exist</h2>
                  <p class="mb-4">CR Number Not exist<br /></p>
                  <div class="text-right pt-4 pr-40">

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row p-3" v-else-if="projectStatus = 'server error'">
          <div class="col-md-12">
            <div class="card shadow mb-40 bg-wts">
              <div class="row" style="    justify-content: center;">
                <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;">
                  <h2 class="text-default font-weight-600">Server Error</h2>
                  <p class="mb-4">Please try again!!<br /></p>
                  <div class="text-right pt-4 pr-40">

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>


      </div>

    </div>

  </div>
</template>

<script>
import { onMounted, ref, computed, inject } from 'vue';
import { makeRequest } from '../../utils/Utils';
import Loader from '../global/loader.ce.vue';
import DashboardSkeletonLoaderCe from '../DashboardSkeletonLoader/DashboardSkeletonLoader.ce.vue';

export default {
  components: { Loader, DashboardSkeletonLoaderCe },
  computed: {
    elementStyle() {
      return {
        display: this.showData ? 'block' : 'none',
        opacity: this.showData ? 1 : 0,
        transition: 'opacity 0.3s ease-in-out',

      };
    },
  },
  setup(props) {
    const projectStatus = ref(null);


    const loader = ref(false);
    const loadertechnical = ref(false);

    const final = ref(null);
    const crNumber = inject("crNumber")


    console.log(crNumber.value)
    const i18n = inject('i18n');
    let showData = ref(false);
    const showVue = ref(true);
    const totalCount = ref(null);
    const ticketstotalCount = ref(null);
    const averagePercetageResolved = ref(null);
    const openTicketsCount = ref(null);


    const ProjectsJSON = ref({ inProgress: 0, Completed: 0, onHold: 0, hoInProgress: 0, closed: 0 })








    const hostUrlOverview = `http://172.19.53.234:8080/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-overview?crNumber=${crNumber.value}`
    // const projectListUrl = `http://172.19.53.234:8080/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-list?crNumber=${crNumber.value}`;



    // const hostUrlOverview = `/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-overview?crNumber=${crNumber.value}`
    // const projectListUrl = `/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-list?crNumber=${crNumber.value}`;
    // const technicalSupportUrl = `/o/external-api-headless/v1.0/get-technical-support?endpointURL=ods/azer/case-list?crNumber=${crNumber.value}`;


    // const hostUrlOverview = `https://apitest.stcs.com.sa/tibco/ods/epm/project/project-overview?crNumber=${crNumber.value}`

    // const projectListUrl = `https://apitest.stcs.com.sa/tibco/ods/epm/project/project-list?crNumber=${crNumber.value}`;




    const GoToPage = (path) => {

      window.location.href = window.location.origin + `/group/mysolutions/${path}`;
    }


    const loadDataOverview = async () => {
      loadertechnical.value = true;
      loader.value = true;

      let responsestack = await makeRequest(hostUrlOverview, 'GET', null);
      console.log(responsestack)

      if (responsestack?.status >= 200 && responsestack?.status < 300) {
        projectStatus.value = responsestack?.payload?.projectStatus;
        console.log("overview data", projectStatus.value);

        projectStatus.value.forEach((item) => {
          if (item.status == "In Progress") {
            ProjectsJSON.value.inProgress = item.count;
          }
          if (item.status == "HO-In Progress") {
            ProjectsJSON.value.hoInProgress = item.count;
          }
          if (item.status == "Completed") {
            ProjectsJSON.value.Completed = item.count;
          }
          if (item.status == "Closed") {
            ProjectsJSON.value.closed = item.count;
          }
          if (item.status == "onHOld") {
            ProjectsJSON.value.onHold = item.count;
          }

        })

        // totalCount.value = computed(() => {
        //   if (!projectStatus.value) return 0;

        //   return projectStatus.value.reduce((sum, statusData) => {
        //     // Parse "count" values to integers and add them to the sum
        //     return sum + parseInt(statusData.count, 10);
        //   }, 0);
        // });


        loader.value = false;


      }
      else {
        console.log("error")
        projectStatus.value = "server error";

        loader.value = false;
      }



    }



    // const progressBarstatusShow = async () => {
    //   try {
    //     loader.value = true;
    //     let responsestack = await makeRequest(projectListUrl, 'GET', null);
    //     console.log("responsestack", responsestack)
    //     projectListData.value = responsestack?.payload?.Projects;
    //     console.log(projectListData.value)
    //     projectListData.value.forEach((ele) => {
    //       console.log(ele.progressActual);

    //       arry1.value.push(ele.progressActual);
    //     });
    //     console.log(arry1.value)

    //     console.log(arry1.value.length)

    //     for (let t of arry1.value) {
    //       total = total + parseInt(t)
    //     }
    //     console.log(total);
    //     final.value = total / arry1.value.length
    //     if (final.value > 100) {
    //       final.value = 100;
    //     }
    //     console.log(final.value)
    //     // console.log(final);
    //     loader.value = false;

    //     // console.log(final.value)

    //   }
    //   catch (error) {
    //     console.error(error);
    //   }
    // }


    // const technicalSupportCall = async () => {


    //   loadertechnical.value = true;
    //   let responsestack = await makeRequest(technicalSupportUrl, 'GET', null);
    //   console.log(responsestack)

    //   if (responsestack?.status >= 200 && responsestack?.status < 300) {
    //     technicalSupportList.value = responsestack?.payload?.case;
    //     console.log("overview data", technicalSupportList.value);
    //     ticketstotalCount.value = computed(() => {
    //       if (!technicalSupportList.value) return 0;

    //       else {
    //         let totalvalues = technicalSupportList.value.length;
    //         let openTickets = technicalSupportList.value.filter((item) => { return item.status == 'Open' }).length;

    //         console.log('openTickets', openTickets);



    //         openTicketsCount.value=openTickets;


    //         return (totalvalues);

    //       }
    //     });


    //     loadertechnical.value = false;


    //   }
    //   else {
    //     ticketstotalCount.value = "server error";
    //     // console.log(projectStatus.value)
    //     loadertechnical.value = false;
    //   }

    // }



    //   projectListData.value.forEach((ele) => {
    //     console.log(ele);
    // //     // arry1.push()
    //  });


    //   const extractValues = (responsestack) => {
    //   const extractedArray = [];

    //   if (responsestack && responsestack.payload && responsestack.payload.projects) {
    //     extractedArray.push(...responsestack.payload.projects);
    //   }

    //   return extractedArray;

    // }





    onMounted(() => {
      loadDataOverview();
      // progressBarstatusShow().then(() => {
      //   technicalSupportCall();

      // })


    });



    return {
      showData,
      projectStatus,
      totalCount,
      showVue,
      loader,
      GoToPage,
      crNumber,
      i18n,
      final,
      ticketstotalCount,
      averagePercetageResolved,
      loadertechnical,
      openTicketsCount,
      ProjectsJSON


    }
  }
}

</script>


