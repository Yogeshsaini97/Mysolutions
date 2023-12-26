<template>
    <div>
        <div class="">
            <div class="">
                <h4 class="mb-30 ml-3 mt-2">{{ i18n.global.t('OVERVIEW') }}</h4>
                <div class="row"
                    v-if="(crNumber != 'NoData' && myRole!='NoData')">
                    <!-- ------ super user dashboard starts----------------- -->
                    <div v-if="myRole=='Super User'"
                        @click="GoToPage('projects')" class="col-md-4 pointer">
                        <div class="card mnh-140p mb-40"
                            @mouseover="showData = true"
                            @mouseout="showData = false">
                            <div class="card-body" v-if="loader == false">
                                <div v-if="projectStatus != null"
                                    :style="elementStyle"
                                    class="card hoverstats w-150p p-3 font-11"
                                    style="
                  position: absolute;
                  z-index: 1;
                  top: 40px;
                  left: 98%;
                  transform: translateX(10px);
                             ">
                                    <p class='mb-0 text-grey font-10 mt-3'>
                                        {{ i18n.global.t('PROJECT_STATS') }}</p>
                                  
                                    {{ console.log('ProjectsJSON',ProjectsJSON) }}
                                    <div class='row'
                                        style="align-items: center;"
                                        >
                                        {{ console.log('key value',key, value) }}
                                        <!-- <div v-if="value=='inProgress'" style="display: flex;
                    place-content: center;
                    align-items: flex-end;
                    align-content: center;
                    justify-content: flex-start;
                }" class='col-8 p-1'><i :class="'fa-solid fa-circle font-8 mr-2 ' + (value === 'inProgress'
                      ? ' text-success '
                      : value === 'Cancelled'
                        ? ' text-red '
                        : value === 'Planned'
                          ? ' text-orange '
                          : value === 'OnHold'
                            ? 'text-orange'
                            : value === 'closed'
                              ? ' text-red '
                              : value === 'Completed'
                                ? ' text-default '
                                : ' bg-light-success ')"></i><span class="mx-10" style="min-width: 75px;
                                ">In Progress</span><span>{{ key }}</span></div> -->
                                        <!-- --- -->
                                        <!-- <div v-if="value==''" style="display: flex;
                                place-content: center;
                                align-items: flex-end;
                                align-content: center;
                                justify-content: flex-start;
                            }" class='col-8 p-1'><i :class="'fa-solid fa-circle font-8 mr-2 ' + (value === 'inProgress'
                                  ? ' text-success '
                                  : value === 'Cancelled'
                                    ? ' text-red '
                                    : value === 'Planned'
                                      ? ' text-orange '
                                      : value === 'OnHold'
                                        ? 'text-orange'
                                        : value === 'closed'
                                          ? ' text-red '
                                          : value === 'Completed'
                                            ? ' text-default '
                                            : ' bg-light-success ')"></i><span class="mx-10" style="min-width: 75px;
                                            ">In Progress</span><span>{{ key }}</span></div> -->
                                        <!-- ----- -->
                                        <div class='row'
                                            style="align-items: center;">
                                            {{ console.log('key value',key, value) }}
                                            <!-- --- -->
                                            <!-- ----- -->
                                            <div style="display: flex;
                                            place-content: center;
                                            align-items: center;
                                            align-content: center;
                                            justify-content: flex-start;
                                        }" class='col-8 p-1'><i
                                                    :class="'fa-solid fa-circle font-8 mr-2 text-success ' "></i><span
                                                    class="mx-10" style="min-width: 75px;
                                                        ">{{i18n.global.t('IN_PROGRESS')}}
                                                    </span><span>{{ ProjectsJSON.inProgress }}</span>
                                            </div>
                                            <div
                                                class='col-4 text-right d-flex'>
                                                <!-- <img
                                                :src="'/documents/d/mysolutions/angle-right'" class='ml-1' alt='img' /> -->
                                            </div>
                                        </div>
                                        <div class='row'
                                            style="align-items: center;">
                                            {{ console.log('key value',key, value) }}
                                            <!-- --- -->
                                            <!-- ----- -->
                                            <div style="display: flex;
                                            place-content: center;
                                            align-items: center;
                                            align-content: center;
                                            justify-content: flex-start;
                                        }" class='col-8 p-1'><i
                                                    :class="'fa-solid fa-circle font-8 mr-2 text-default ' "></i><span
                                                    class="mx-10" style="min-width: 75px;
                                                        ">
                                                    {{i18n.global.t('COMPLETED')}}</span><span>{{ ProjectsJSON.Completed }}</span>
                                            </div>
                                            <div
                                                class='col-4 text-right d-flex'>
                                                                       <!-- <img
                                                :src="'/documents/d/mysolutions/angle-right'" class='ml-1' alt='img' /> -->
                                            </div>
                                        </div>
                                        <div class='row'
                                            style="align-items: center;">
                                            {{ console.log('key value',key, value) }}
                                            <!-- --- -->
                                            <!-- ----- -->
                                            <div style="display: flex;
                                            place-content: center;
                                            align-items: center;
                                            align-content: center;
                                            justify-content: flex-start;
                                        }" class='col-8 p-1'><i
                                                    :class="'fa-solid fa-circle font-8 mr-2 bg-light-success' "></i><span
                                                    class="mx-10" style="min-width: 75px;
                                                        ">{{i18n.global.t('ON_HOLD')}}</span><span>{{ ProjectsJSON.onHold }}</span>
                                            </div>
                                           
                                        </div>












                                        <div class='col-4 text-right d-flex'>
                                            <!-- <img
                        :src="'/documents/d/mysolutions/angle-right'" class='ml-1' alt='img' /> -->
                                        </div>
                                    </div>
                                </div>
                                {{ console.log('totalCount',totalCount) }}
                                {{ console.log('ProjectsJSON.cancelled',ProjectsJSON.cancelled) }}
                                <p class="mb-0 font-40 text-default">{{ totalCount != 'server error' ? (totalCount) :
                  '0' }}</p>
                                <p class="mb-0">
                                    {{ i18n.global.t('PROJECTS') }}<img
                                        :src="'/documents/d/mysolutions/arrow-up '"
                                        class="ml-1 w-15p" alt="img" /></p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Super User'" class="col-md-4">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body"
                                v-if="loaderinvoice == false">
                                <p class="mb-0 font-40 text-default">
                                    {{ invoiceTotal?invoiceTotal:0 }}</p>
                                <p class="mb-0">{{ i18n.global.t('INVOICES') }}
                                    <!-- <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /> -->
                                </p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('YOU_HAVE') + " " }}<span
                                        class="text-danger opacity-100 font-weight-900">{{openInvoices?openInvoices:0}}
                                        open</span>{{ " " + i18n.global.t('INVOICES') }}
                                </p>
                            </div>
                            <div v-else-if="loaderinvoice == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Super User'" class="col-md-4">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body" v-if="loader == false">
                                <p class="mb-0 font-40 text-default">0</p>
                                <p class="mb-0">{{ i18n.global.t('APPROVALS') }}
                                    <!-- <img :src="'/documents/d/mysolutions/arrow-up '"
                    class="ml-1 w-15p" alt="img" /> -->
                                </p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('YOU_HAVE') + " " }}<span
                                        class="text-danger opacity-100 font-weight-900">0
                                        pending</span>{{ " " + i18n.global.t('aPPROVALS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Super User'"
                        @click="GoToPage('tickets')" class="col-md-4">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body"
                                v-if="loadertechnical == false">
                                <p class="mb-0 font-40 text-default">
                                    {{ ticketstotalCount>0  ? ticketstotalCount : 0 }}
                                </p>
                                <p class="mb-0">
                                    {{ i18n.global.t('SUPPORT_TICKETS') }}
                                    <img :src="'/documents/d/mysolutions/arrow-up'"
                                        class="ml-1 w-15p" alt="img" />
                                </p>
                                <!-- <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('OVERALL_RESOLVED') }}</p> -->
                                <!-- <div>
                  <p class="mb-0 text-right font-10">{{ averagePercetageResolved!=null  ? Math.round(averagePercetageResolved) : '--'}}%</p>
                  <div class="progress h-5p">
                    <div class="progress-bar bg-default" role="progressbar" aria-valuenow="0" :style="{ width: `${averagePercetageResolved ? Math.round(averagePercetageResolved.value) : 0}%` }">
                    </div>
                  </div>
                </div> -->
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('YOU_HAVE') + " " }}<span
                                        class="text-danger opacity-100 font-weight-900">{{ openTicketsCount!=null? openTicketsCount :'0' }}
                                        open</span>{{ " " + i18n.global.t('Tickets') }}
                                </p>
                            </div>
                            <div v-else-if="loadertechnical == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Super User'" class="col-md-4">
                    </div>
                    <!-- ------ super user dashboard dashbboard ends------------- -->
                    <!-- ------ project manager dashboard starts------------- -->
                    <div v-if="myRole=='Project Manager' " class="col-md-4">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body" v-if="loader == false">
                                <p class="mb-0 font-40 text-default">0</p>
                                <p class="mb-0">{{ i18n.global.t('APPROVALS') }}
                                    <img :src="'/documents/d/mysolutions/arrow-up '"
                                        class="ml-1 w-15p" alt="img" />
                                </p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('YOU_HAVE') + " " }}<span
                                        class="text-danger opacity-100 font-weight-900">0
                                        pending</span>{{ " " + i18n.global.t('APPROVALS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Project Team Member' || myRole=='Project Manager'"
                        @click="GoToPage(`projects?filter=In Progress`)"
                        class="col-md-4 pointer">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body" v-if="loader == false">
                                <div style="display: flex;
        align-content: center;
        align-items: stretch;
        justify-content: space-between;">
                                    <p class="mb-0 font-40 text-default"
                                        style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error' ? ProjectsJSON.inProgress :
          'N/A' }}</p>
                                    <div
                                        class="rectangle-green-upper  d-flex justify-content-end ">
                                        <div class="rectangle-green  justify-content-end"
                                            style="transform: rotate(90deg);  width: 30px;
            height: 30px;">
                                        </div>
                                    </div>
                                </div>
                                <p class="mb-0">
                                    {{ i18n.global.t('IN_PROGRESS') }}<img
                                        :src="'/documents/d/mysolutions/arrow-up '"
                                        class="ml-1 w-15p" alt="img" /></p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <!-- <div v-if="myRole=='Project Team Member' || myRole=='Project Manager' " @click="GoToPage(`projects?filter=HO-In Progress`)" class="col-md-4 pointer">
    <div class="card mnh-140p mb-40">
      <div class="card-body" v-if="loader == false">
        <div style="display: flex;
        align-content: center;
        align-items: stretch;
        justify-content: space-between;" >  <p class="mb-0 font-40 text-default" style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error' ? ProjectsJSON.hoInProgress :
          'N/A' }}</p>
          <div class="col-md-6  d-flex justify-content-end " style="margin: -17px -31px 0px 0px;">
            <div class="rectangle-lightgreen  justify-content-end" style="transform: rotate(90deg);  width: 30px;
            height: 30px;">
            </div>
          </div></div>
        <p class="mb-0">{{ i18n.global.t('HO_IN_PROGRESS') }}<img :src="'/documents/d/mysolutions/arrow-up '"
            class="ml-1 w-15p" alt="img" /></p>
             <p class="mb-0 font-8 " style="color:#8e9aa0">{{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}</p>
      </div>
      <div v-else-if="loader == true"><dashboard-skeleton-loader-ce /></div>
    </div>
  </div> -->
                    <div v-if="myRole=='Project Team Member' || myRole=='Project Manager' "
                        @click="GoToPage(`projects?filter=onHOld`)"
                        class="col-md-4 pointer">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body" v-if="loader == false">
                                <div style="display: flex;
        align-content: center;
        align-items: stretch;
        justify-content: space-between;">
                                    <p class="mb-0 font-40 text-default"
                                        style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error' ? ProjectsJSON.onHold :
          'N/A' }}</p>
                                    <div
                                        class="  rectangle-orange-upper d-flex justify-content-end ">
                                        <div class="rectangle-orange  justify-content-end"
                                            style="transform: rotate(90deg);  width: 30px;
            height: 30px;">
                                        </div>
                                    </div>
                                </div>
                                <p class="mb-0">
                                    {{ i18n.global.t('ON_HOLD') }}<img
                                        :src="'/documents/d/mysolutions/arrow-up '"
                                        class="ml-1 w-15p" alt="img" /></p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <div v-if="myRole=='Project Team Member' || myRole=='Project Manager' "
                        @click="GoToPage(`projects?filter=Completed`)"
                        class="col-md-4 pointer">
                        <div class="card mnh-140p mb-40">
                            <div class="card-body" v-if="loader == false">
                                <div style="display: flex;
        align-content: center;
        align-items: stretch;
        justify-content: space-between;">
                                    <p class="mb-0 font-40 text-default"
                                        style="margin:0px 0px 0px -5px;">{{ projectStatus != 'server error' ? ProjectsJSON.Completed :
          'N/A' }}</p>
                                    <div
                                        class="rectangle-blue-upper  d-flex justify-content-end ">
                                        <div class="rectangle-blue  justify-content-end"
                                            style="transform: rotate(90deg);  width: 30px;
            height: 30px;">
                                        </div>
                                    </div>
                                </div>
                                <p class="mb-0">
                                    {{ i18n.global.t('COMPLETED') }}<img
                                        :src="'/documents/d/mysolutions/arrow-up '"
                                        class="ml-1 w-15p" alt="img" /></p>
                                <p class="mb-0 font-8 " style="color:#8e9aa0">
                                    {{ i18n.global.t('CLICK_TO_VIEW_DETAILS') }}
                                </p>
                            </div>
                            <div v-else-if="loader == true">
                                <dashboard-skeleton-loader-ce /></div>
                        </div>
                    </div>
                    <!-- ------ project manager dashboard ends------------- -->
                    <!-- ------Team member dashboard ends------------- -->
                    <div class="teammember"></div>
                    <!-- ------Team member dashboard ends------------- -->
                </div>
                <div class="row p-3" v-if="crNumber == 'NoData'">
                    <div class="col-md-12">
                        <div class="card shadow mb-40 bg-wts">
                            <div class="row"
                                style="    justify-content: center;">
                                <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;">
                                    <h2 class="text-default font-weight-600">CR
                                        Number Not exist</h2>
                                    <p class="mb-4">CR Number Not exist<br />
                                    </p>
                                    <div class="text-right pt-4 pr-40">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row p-3" v-if="myRole == 'NoData'">
                    <div class="col-md-12">
                        <div class="card shadow mb-40 bg-wts">
                            <div class="row"
                                style="    justify-content: center;">
                                <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;">
                                    <h2 class="text-default font-weight-600">
                                        Please Assign a role to the User</h2>
                                    <p class="mb-4">Role is not assigned to the
                                        user!!<br /></p>
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
    import {
        onMounted,
        ref,
        computed,
        inject
    } from 'vue';
    import {
        makeRequest
    } from '../../utils/Utils';
    import Loader from '../global/loader.ce.vue';
    import DashboardSkeletonLoaderCe from '../DashboardSkeletonLoader/DashboardSkeletonLoader.ce.vue';
    export default {
        components: {
            Loader,
            DashboardSkeletonLoaderCe
        },
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
            const technicalSupportList = ref(null);
            const loader = ref(false);
            const loadertechnical = ref(false);
            const loaderinvoice = ref(false);
            const final = ref(null);
            const crNumber = inject("crNumber");
            const myRole = inject('myRole');
            const i18n = inject('i18n');
            let showData = ref(false);
            const showVue = ref(true);
            const totalCount = ref(null);
            const invoiceTotalCount = ref(null);
            const ticketstotalCount = ref(0);
            const averagePercetageResolved = ref(null);
            const openTicketsCount = ref(null);
            const ProjectsJSON = ref({
                inProgress: 0,
                Completed: 0,
                onHold: 0,
                hoInProgress: 0,
                closed: 0,
                cancelled :0
            });
            const projectStatusNumbers = ref(null);
            const invoiceTotal = ref(null);
            const openInvoices = ref(null);
            const hostUrlOverview = import.meta.env
                .VITE_TESTING_LIFERAY_ROOT_API +
                `ods/epm/project/project-overview?crNumber=${crNumber.value}&p_auth=${Liferay.authToken}`
            const technicalSupportUrl = import.meta.env
                .VITE_TESTING_LIFERAY_TECHNICAL_SUPPORT_API +
                `ods/azer/case-list?crNumber=${crNumber.value}&p_auth=${Liferay.authToken}`;
            const invoiceListUrl = import.meta.env
                .VITE_TESTING_LIFERAY_ROOT_API +
                `ods/erp/invoice/invoice-list/?CRNumber=${crNumber.value}&p_auth=${Liferay.authToken}`;
            const GoToPage = (path) => {
                window.location.href = window.location.origin +
                    `/group/mysolutions/${path}`;
            }
            const loadDataOverview = async () => {
                let responsestack = await makeRequest(hostUrlOverview,
                    'GET', null);
                console.log(responsestack)
                if (responsestack?.status >= 200 && responsestack
                    ?.status < 300 && responsestack?.payload
                    ?.projectStatus) {
                    projectStatusNumbers.value = responsestack?.payload
                        ?.projectStatus;
                    console.log("overview data", projectStatusNumbers
                        .value);
                    let progressTempSum = 0;
                    let completedTempSum = 0;
                    projectStatusNumbers.value.forEach((item) => {
                        if (item?.status == "In Progress") {
                            progressTempSum = progressTempSum +
                                Number(item.count);
                        }
                        if (item?.status == "HO-In Progress") {
                            progressTempSum = progressTempSum +
                                Number(item.count);
                        }
                        if (item?.status == "Completed") {
                            completedTempSum =
                                completedTempSum + Number(item
                                    .count);
                        }
                        if (item?.status == "Closed") {
                            completedTempSum =
                                completedTempSum + Number(item
                                    .count);
                        }
                        if (item?.status == "Cancelled") {
                          ProjectsJSON.value.cancelled =Number( item
                                .count);
                        }
                        if (item?.status == "onHOld" || item
                            .status == 'On Hold') {
                            ProjectsJSON.value.onHold = item
                                .count;
                        }
                    })
                    ProjectsJSON.value.inProgress = progressTempSum;
                    ProjectsJSON.value.Completed = completedTempSum;
                    projectStatus.value = responsestack?.payload
                        ?.projectStatus;
                    console.log("overview data", projectStatus.value);
                    let jsonArray =  projectStatus.value.filter(item => item?.status !== 'Cancelled');
                    totalCount.value = computed(() => {
                        if (!jsonArray) return 0;
                        return jsonArray.reduce((sum,
                            statusData) => {
                            // Parse "count" values to integers and add them to the sum
                            console.log("statusData",statusData)

                            return sum + parseInt(
                            
                            statusData.count, 10
                            );
                           
                        }, 0);
                    });
                    loader.value = false;
                } else {
                    totalCount.value = "server error";
                }
            }
            const getallInvoices = async () => {
                let responsestack = await makeRequest(invoiceListUrl,
                    'GET', null);
                console.log('invoices', responsestack)
                if (responsestack?.status >= 200 && responsestack
                    ?.status < 300 && responsestack?.payload
                    ?.InvoiceList) {
                    let allInvoices = responsestack?.payload
                        ?.InvoiceList;
                    invoiceTotal.value = allInvoices?.length;
                    console.log("overview data", allInvoices);
                    openInvoices.value = allInvoices?.filter(invoice =>
                        invoice?.status == "OP").length;
                    console.log("openInvoices", openInvoices.value)
                    console.log('invoiceTotalCount', invoiceTotalCount)
                } else {
                    invoiceTotal.value = "0";
                }
            }
            const technicalSupportCall = async () => {
                loadertechnical.value = true;
                let responsestack = await makeRequest(
                    technicalSupportUrl, 'GET', null);
                console.log(responsestack)
                if (responsestack?.status >= 200 && responsestack
                    ?.status < 300) {
                    technicalSupportList.value = responsestack?.payload
                        ?.case;
                    console.log("overview data", technicalSupportList
                        .value);
                    ticketstotalCount.value = technicalSupportList.value
                        ?.length;
                    openTicketsCount.value = technicalSupportList.value
                        ?.filter((item) => {
                            return item?.status == 'Open'
                        }).length;
                } else {
                    ticketstotalCount.value = "error";
                    // console.log(projectStatus.value)
                }
            }
            onMounted(async () => {
                if (myRole.value == 'Super User') {
                    loader.value = true;
                    loadertechnical.value = true;
                    loaderinvoice.value = true;
                    // Call loadDataOverview regardless of the role
                    await loadDataOverview();
                    loader.value = false;
                    // Call technicalSupportCall only for Super User
                    await technicalSupportCall();
                    loadertechnical.value = false;
                    // Call getallInvoices only if both functions above have been successful
                    await getallInvoices();
                    loaderinvoice.value = false;
                } else {
                    loader.value = true;
                    await loadDataOverview();
                    loader.value = false;
                }
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
                myRole,
                ProjectsJSON,
                invoiceTotal,
                loaderinvoice,
                openInvoices,
            }
        }
    }
</script>