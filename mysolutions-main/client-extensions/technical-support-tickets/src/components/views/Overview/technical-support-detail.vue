<script>
import { watch } from 'vue';
import { applyColorToStatus, applyColorToStatustext, formatTimeTo12HourFormat } from '../../../utils/utils';
import { onMounted, ref, inject } from 'vue';
import { ChangeDateFormat, makeRequest } from '../../../utils/utils';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
export default {
  props: {
    id: {
      type: [String, Array, Number, Object, null],
      default: null
    }
  },
  setup(props) {
    const technicalSupportData = ref(null);
    /////////////langugage defined start here///////
    const currentSupportID = inject('currentSupportID');

    const IssueId = props.id;
    const ProjectApiId = inject("ProjectApiId");
    const i18n = inject('i18n');
    const toggleSidebar = inject('toggleSidebar')
    /////////////langugage defined end here///////

    const isDescriptionVisible = ref(true); // Initialize as true if you want it to be visible by default

    const toggleDescription = () => {
      isDescriptionVisible.value = !isDescriptionVisible.value;
    };

    const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/azer/case-details/?supportId=${currentSupportID && currentSupportID.value}&p_auth=${Liferay.authToken}`;
    const loadData = async () => {

      let responsestack = await makeRequest(hostUrl, 'GET', null);
      technicalSupportData.value = responsestack.payload;
      console.log(responsestack);
      HandleErrors(responsestack);


    };
    const HandleErrors = (data) => {
      //  technicalSupportData.value= data;
      console.log(data);
      if (data.status >= 200 && data.status < 300) {
        if (data.message == "ok" && data.payload.message == "Success") {
          console.log("inside success");
          technicalSupportData.value = "Success";
          return;
        }
        else if (data.message == "ok" && Object.keys(data.payload).length === 0) {
          console.log("inside {}");
          technicalSupportData.value = 'N/A';
          return;
        }
        else if (data.message == "ok" && data.payload) {

          technicalSupportData.value = data.payload;
          console.log(technicalSupportData.value.length);
          return;
        }
      }
      else {
        console.log("inside main error");
        technicalSupportData.value = { key: "server error", message: data.message };
        console.log(technicalSupportData.value)
        return;
      }
    };
    onMounted(() => {
      loadData();
    });
    return {
      technicalSupportData,
      ChangeDateFormat,
      i18n,
      IssueId,
      toggleSidebar,
      currentSupportID,
      formatTimeTo12HourFormat,
      applyColorToStatus,
      applyColorToStatustext,
      isDescriptionVisible,
      toggleDescription,
    };
  },
  components: { Loader, ErrorMessageDialogBox }
}

</script>


<template>
  <div class="container-fluid mnh-375p">
    <div class="row">
      <div class="col-md-3 border-right mnh-md-400p">
        <h2 class="text-default mt-4 line-height-15">{{i18n.global.t('CASE')}}<br />{{ i18n.global.t('DETAIL') }}</h2>
        <div class="mt-4">
          <p class="mb-1 text-default">{{i18n.global.t('TITLE')}}</p>
          <p class="mb-0">{{ technicalSupportData? technicalSupportData.ticketTitle :"--"}}</p>
       
        </div>
        <div class="mt-4">

          <p class="mb-1 text-default">{{i18n.global.t('CASE_ID')}}</p>
          <p class="mb-0">{{ technicalSupportData? technicalSupportData.supportId :"--"}}</p>
        </div>
        <div class="mt-4">
          <p class="mb-1 text-default">{{i18n.global.t('RAISED_BY')}}</p>
          <p class="mb-0">{{ technicalSupportData? technicalSupportData.raisedBy :"--"  }}</p>
       
        </div>
        <div class="mt-4">
          <p class="mb-1 text-default">{{i18n.global.t('RAISED_ON')}}</p>
          <p class="mb-0">{{ technicalSupportData? ChangeDateFormat(technicalSupportData.raisedOn) :"--"  }}</p>
          <p class="mb-0">{{ technicalSupportData? formatTimeTo12HourFormat(technicalSupportData.raisedOn) :"--"  }}</p>

        </div>
        <!-- <div class="mt-4">
          <p class="mb-1 text-default">{{i18n.global.t('CLOSED_ON')}}</p>
          <p class="mb-0">-- --</p>
        </div> -->
        <div style="    margin-top: 12rem;"  >
          <a  class="btn btn-light btn-block" type="button">{{i18n.global.t('THIS_TICKET_IS')}} {{ technicalSupportData?.status }}</a>
        </div>

   
        

      </div>
      <div class="col-md-9" id="TicketDetails">
        <div class="row py-4 border-bottom">
          <div class="col-auto mpaddingx">
            <button @click="toggleDescription" class="btn btn-light px-3 py-10 rounded-12 btnTicketDescription"
              type="button">
              <img :src="'/documents/d/mysolutions/icon-menu'" alt="img" />
              <img :src="'/documents/d/mysolutions/icon-menu'" class="d-none" alt="img" />
            </button>
          </div>
          <div class="col-auto mpaddingx">

            <p class="mb-0 font-10 text-default">{{i18n.global.t('STATUS')}}</p>
            <span :class="'badge rounded-pill font-10 ' + applyColorToStatus(technicalSupportData?.status)"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{
              technicalSupportData? technicalSupportData.status :"--" }}</span>

          </div>
          <div class="col-auto mpaddingx">
            <p class="mb-0 font-10 text-default">{{i18n.global.t('SERVICE/PROJECT')}}</p>
            <p class="mb-0">{{
              technicalSupportData ? technicalSupportData.supportType : "--" }}</p>
          </div>
          <div class="col-auto mpaddingx">
            <p class="mb-0 font-10 text-default">{{i18n.global.t('SEVERITY')}}</p>
            <p class="mb-0 text-danger">{{
              technicalSupportData ? technicalSupportData.severity?.split('-')[1] : "--" }}</p>
          </div>
          <!-- <div class="col mpaddingx">
            <p class="mb-0 font-10 text-default">Respond time</p>
            <p class="mb-0">12hrs</p>
          </div> -->
        </div>
        <div class="row ticket-chat">

          <div class="col-12 col-md-6 border-right mnh-md-500p py-3 description d-none">

            <p>Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos
              ut non enim error. Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint
              blanditiis dignissimos ut non enim error.</p>
            <p class="text-default">{{i18n.global.t('ATTACHMENTS')}}</p>
            <div class="row">
              <div class="col-3">
                <img :src="'/documents/d/mysolutions/videoImage'" alt="img" class="img-fluid rounded-10">
              </div>
              <div class="col-3">
                <img :src="'/documents/d/mysolutions/videoImage'" alt="img" class="img-fluid rounded-10">
              </div>
              <div class="col-3">
                <img :src="'/documents/d/mysolutions/videoImage'" alt="img" class="img-fluid rounded-10">
              </div>
            </div>
            <div class="positionBottom">
              <div class="row">
                <div class="col-6">
                  <p class="mb-1 text-default">Project manager</p>
                  <p class="mb-0">John Doe</p>
                  <p class="mb-0">mail@example.com</p>
                </div>
                <div class="col-6">
                  <p class="mb-1 text-default">Account manager</p>
                  <p class="mb-0">John Doe</p>
                  <p class="mb-0">mail@example.com</p>
                </div>
              </div>
            </div>
          </div>
          <div class="col-6" v-if="isDescriptionVisible">
            <div style="margin-top: 1rem;">
              <h6 style="font-size: .8rem;color: blue;">Ticket Description</h6>
              <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Beatae laborum ut provident, nulla accusamus,
                minus natus sapiente est soluta reiciendis, atque modi ea. Voluptate, quas magni ad necessitatibus debitis.</p>
              <br>
            </div>
            <div>
              <p style="font-size: .8rem;color: blue;">Attachements</p>
              <div style="display: flex;margin-bottom: 8rem;">
                <p style="width: 3rem;height: 3rem;background-color: gray;margin-right: 1rem;"></p>
                <p style="width: 3rem;height: 3rem;background-color: gray;margin-right: 1rem;"></p>
                <p style="width: 3rem;height: 3rem;background-color: gray;margin-right: 1rem;"></p>
              </div>
            </div>
            <div style="display: flex;">
              <div style="margin-right: 2rem;line-height: 0;">
                <p style="font-size: .8rem;color: blue;">Project Manager</p>
                <p>John Doe</p>
                <p>mail@example.com</p>
              </div>
              <div style="line-height: 0;">
                <p style="font-size: .8rem;color: blue;">Account Manager</p>
                <p>John Doe</p>
                <p>mail@example.com</p>
              </div>
            </div>
          </div>
          <div class="col-6 py-3 px-md-100 chat" :class="{ 'col-12': !isDescriptionVisible }">
            <div class="mb-30">
              <img :src="'/documents/d/mysolutions/icon-chat'" class="mr-2" alt="img" />Darren Adams
            </div>
            <div class="mb-30">
              <ul class="list-unstyled">
                <li class="mb-4">
                  <div class="d-flex justify-content-start">
                    <div class="iconChatIn mr-2">D</div>
                    <div class="chatInBox">
                      <p class="m-0">Lorem ipsum dolor sit amet</p>
                    </div>
                  </div>
                  <div class="w-325p mt-1 font-12 text-right"><span>Mar 12th, 2023</span><span class="ml-2">12.35
                      pm</span></div>
                </li>
                <li class="mb-4">
                  <div class="d-flex justify-content-start">
                    <div class="iconChatIn mr-2">D</div>
                    <div class="chatInBox">
                      <p class="m-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                        incididunt ut labore et</p>
                    </div>
                  </div>
                  <div class="w-325p mt-1 font-12 text-right"><span>Mar 12th, 2023</span><span class="ml-2">12.35
                      pm</span></div>
                </li>
                <li class="mb-4">
                  <div class="d-flex justify-content-end">
                    <img :src="'/documents/d/mysolutions/icon-chat-out'" alt="img"
                      class="rounded-circle w-20p h-20p mr-2">
                    <div class="chatOutBox">
                      <p class="m-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                        incididunt ut labore et</p>
                    </div>
                  </div>
                  <div class="mt-1 font-12 text-right"><span>Mar 12th, 2023</span><span class="ml-2">12.35 pm</span></div>
                </li>
              </ul>
            </div>
            <div class="mb-30 border rounded px-15 py-2">
              <div class="row">
                <div class="col-6 border-bottom reply-border">
                  <p class="mb-2 text-default">Reply</p>
                </div>
                <div class="col-6 text-right border-bottom">
                  <label for="attachment" type="button">
                    <img :src="'/documents/d/mysolutions/icon-attachment'" alt="img" />
                  </label>
                  <input type="file" id="attachment" hidden />
                </div>
                <div class="col-12 py-3">
                  <form class="d-flex">
                    <div class="input-group">
                      <textarea class="form-control border-0" placeholder="Type your message ..."
                        rows="2">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</textarea>
                    </div>
                    <button type="submit" class="btn btn-danger px-4 h-30p ml-40 mt-4">Send</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  
  </div>
</template>

