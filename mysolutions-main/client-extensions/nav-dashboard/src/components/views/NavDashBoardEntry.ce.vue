<template>
  <div>

    <div class="">
      <div class="">

        <h4 class="mb-30 ml-3 mt-2">{{ i18n.global.t('OVERVIEW') }}</h4>
 <div class="row" v-if="(totalCount != 0 && crNumber != 'NoData')">

          <div @click="GoToPage('projects')" class="col-md-4 pointer">

            <div class="card mnh-140p mb-40" @mouseover="showData = true" @mouseout="showData = false">

              <div class="card-body" v-if="loader == false">
                <div v-if="projectStatus != null" :style="elementStyle" class="card hoverstats w-150p p-3 font-11" style="
                  position: absolute;
                  z-index: 1;
                  top: 40px;
                  left: 98%;
                  transform: translateX(10px);
                             ">
                  <p class='mb-0 text-grey font-10 mt-3'>Projects stats</p>
                  <div class="arrow"></div>
                  <div class='row' style="align-items: center;" v-for="statusData in projectStatus"
                    :key="statusData.status">
                    <div class='col-8 p-1'><i :class="'fa-solid fa-circle font-8 mr-2 ' + (statusData.status === 'In Progress'
                      ? ' text-success '
                      : statusData.status === 'Cancelled'
                        ? ' text-red '
                        : statusData.status === 'Planned'
                          ? ' text-orange '
                          : statusData.status === 'On Hold'
                            ? 'text-orange'
                            : statusData.status === 'Closed'

                              ? ' text-red '
                              : statusData.status === 'Completed'

                                ? ' text-default '
                                : ' bg-light-success ')"></i><span>{{ statusData.status }}</span></div>
                    <div class='col-4 text-right d-flex'><span>{{ statusData.count }}</span>
                      <!-- <img
                        :src="'/documents/d/mysolutions/angle-right'" class='ml-1' alt='img' /> -->
                    </div>
                  </div>

                </div>
                <p class="mb-0 font-40 text-default">{{ totalCount != 'server error' ? totalCount :
                  'N/A' }}</p>

                <p class="mb-0">{{ i18n.global.t('PROJECTS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /></p>
                     <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
                <!-- <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('OVERALL_HEALTH') }}</p> -->
                <!-- <div>
                  <p class="mb-0 text-right font-10">{{ final ? Math.round(this.final) : 0 }}%</p>
                  <div class="progress h-5p">
                    <div :class="'progress-bar ' + (final >= 50
                      ? '  bg-default '
                      : final < 50
                        ? ' bg-orange ' : '  bg-default ')" role="progressbar" aria-valuenow="50"
                      :style="{ width: `${final ? final : 0}%` }"></div>
                  </div>
                </div> -->
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>

            </div>
          </div>

          <div class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loader == false">
                <p class="mb-0 font-40 text-default">0</p>
                <p class="mb-0">{{ i18n.global.t('ORDERS') }}
                  <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" />
                </p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>
            </div>
          </div>

          <div class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loader == false">
                <p class="mb-0 font-40 text-default">0</p>
                <p class="mb-0">{{ i18n.global.t('APPROVALS') }}
                  <!-- <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /> -->
                </p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('YOU_HAVE') + " " }}<span class="text-danger opacity-100 font-weight-900">0
                    pending</span>{{ " " + i18n.global.t('APPROVALS') }}</p>
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>
            </div>
          </div>
          <!-- <div class="col-md-4">

            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loader==false">
                <p class="mb-0 font-40 text-default">0</p>
                <p class="mb-0">{{ i18n.global.t('INVOICES') }}
                  <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" />
                  </p>
                <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('YOU_HAVE')+ " " }} <span class="text-danger">0
                    overdue</span>{{ " " + i18n.global.t('INVOICES') }}</p>
              </div>
              <div v-else-if="loader==true"><dashboard-skeleton-loader-ce/></div>
            </div>
          </div> -->

          <div class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loader == false">
                <p class="mb-0 font-40 text-default">0</p>
                <p class="mb-0">{{ i18n.global.t('RFQ') }}
                  <!-- <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /> -->
                </p>
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('YOU_HAVE') + " " }}<span class="text-danger opacity-100 font-weight-900">0
                  RFQ</span>{{ " " + i18n.global.t('SUBMITTED') }}</p>
              </div>
              <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>
            </div>
          </div>
          <div @click="GoToPage('tickets')" class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div class="card-body" v-if="loadertechnical == false">
                <p class="mb-0 font-40 text-default">{{ ticketstotalCount ? ticketstotalCount : "--" }}</p>
                <p class="mb-0">{{ i18n.global.t('SUPPORT_TICKETS') }}
                  <img :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" />
                </p>
                <!-- <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('OVERALL_RESOLVED') }}</p> -->
                <!-- <div>
                  <p class="mb-0 text-right font-10">{{ averagePercetageResolved!=null  ? Math.round(averagePercetageResolved) : '--'}}%</p>
                  <div class="progress h-5p">
                    <div class="progress-bar bg-default" role="progressbar" aria-valuenow="0" :style="{ width: `${averagePercetageResolved ? Math.round(averagePercetageResolved.value) : 0}%` }">
                    </div>
                  </div>
                   
                </div> -->
                <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('YOU_HAVE') + " " }}<span class="text-danger opacity-100 font-weight-900">{{ openTicketsCount!=null? openTicketsCount :'0' }}
                    Open</span>{{ " " + i18n.global.t('Tickets') }}</p>
              </div>
              <div v-else-if="loadertechnical == true"><dashboard-skeleton-loader-ce /></div>
            </div>
          </div>
        </div>

        <div>
          <div class="row p-3" v-if="crNumber == 'NoData'">
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
    const projectListData = ref(null);
    const technicalSupportList = ref(null);

    const loader = ref(false);
    const loadertechnical = ref(false);
    const arry1 = ref([]);
    var total = 0;
    const final = ref(null);
    const crNumber = inject("crNumber")


    console.log(crNumber.value)
    const i18n = inject('i18n');
    let showData = ref(false);
    const showVue = ref(true);
    const totalCount = ref(null);
    const ticketstotalCount = ref(null);
    const averagePercetageResolved = ref(null);
    const openTicketsCount=ref(null);








    const hostUrlOverview = `http://172.19.53.234:8080/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-overview?crNumber=${crNumber.value}`
    // const projectListUrl = `http://172.19.53.234:8080/o/external-api-headless/v1.0/fetch?endpointURL=ods/epm/project/project-list?crNumber=${crNumber.value}`;
    const technicalSupportUrl = `http://172.19.53.234:8080/o/external-api-headless/v1.0/get-technical-support?endpointURL=ods/azer/case-list?crNumber=${crNumber.value}`;


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
        totalCount.value = computed(() => {
          if (!projectStatus.value) return 0;

          return projectStatus.value.reduce((sum, statusData) => {
            // Parse "count" values to integers and add them to the sum
            return sum + parseInt(statusData.count, 10);
          }, 0);
        });
        loader.value = false;


      }
      else {
        totalCount.value = "server error";
        // console.log(projectStatus.value)
        loader.value = false;
      }



    }



    const progressBarstatusShow = async () => {
      try {
        loader.value = true;
        let responsestack = await makeRequest(projectListUrl, 'GET', null);
        console.log("responsestack", responsestack)
        projectListData.value = responsestack?.payload?.Projects;
        console.log(projectListData.value)
        projectListData.value.forEach((ele) => {
          console.log(ele.progressActual);

          arry1.value.push(ele.progressActual);
        });
        console.log(arry1.value)

        console.log(arry1.value.length)

        for (let t of arry1.value) {
          total = total + parseInt(t)
        }
        console.log(total);
        final.value = total / arry1.value.length
        if (final.value > 100) {
          final.value = 100;
        }
        console.log(final.value)
        // console.log(final);
        loader.value = false;

        // console.log(final.value)

      }
      catch (error) {
        console.error(error);
      }
    }


    const technicalSupportCall = async () => {


      loadertechnical.value = true;
      let responsestack = await makeRequest(technicalSupportUrl, 'GET', null);
      console.log(responsestack)

      if (responsestack?.status >= 200 && responsestack?.status < 300) {
        technicalSupportList.value = responsestack?.payload?.case;
        console.log("overview data", technicalSupportList.value);
        ticketstotalCount.value = computed(() => {
          if (!technicalSupportList.value) return 0;

          else {
            let totalvalues = technicalSupportList.value.length;
            let openTickets = technicalSupportList.value.filter((item) => { return item.status == 'Open' }).length;

            console.log('openTickets', openTickets);



            openTicketsCount.value=openTickets;
         

            return (totalvalues);

          }
        });


        loadertechnical.value = false;


      }
      else {
        ticketstotalCount.value = "server error";
        // console.log(projectStatus.value)
        loadertechnical.value = false;
      }

    }



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
      progressBarstatusShow().then(() => {
        technicalSupportCall();

      })


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
      openTicketsCount


    }
  }
}

</script>


