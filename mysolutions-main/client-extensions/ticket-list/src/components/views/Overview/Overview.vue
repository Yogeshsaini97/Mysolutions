<script>
import { onMounted,inject,ref,reactive} from 'vue';
import { ChangeDateFormat,fetchData,updateData,formatDate,AddDataPost } from '../../../Utils/Utils';
export default {
setup(props, context) {
const ProjectGetOneData =ref('');
const CommentGetOneData =ref('');
const reply=ref('');
const ProjectApiId = inject('ProjectApiId');
const Div_MB=reactive({mb:"col-md-8",show:false});
const ChangePage = inject('ChangePage');
const CurrentPage =inject('CurrentPage');
const hostUrl="/o/c/supporttickets/"+`${ProjectApiId?.id}`+"?p_auth="+`${Liferay.authToken}`;

  const loadData=async () => {
        try {
          const response = await fetchData(hostUrl);
          ProjectGetOneData.value = response;
        }
        catch (error) {
          console.error(error);
        }
      }

      const loadDataComment=async () => {
        try {
          const hostUrlComment=`/o/c/supportticketcomments/?p_auth=${Liferay.authToken}&filter=r_withSupportTicketComment_c_supportTicketId eq '${ProjectApiId?.id}'`;
          const res = await fetchData(hostUrlComment);
          CommentGetOneData.value = res.items;
        }
        catch (error) {
          console.error(error);
        }
      }

      onMounted(() => {
      loadData();
      loadDataComment();
    });

  const onChangeNav=()=>{
        Div_MB.show=!Div_MB.show;
        if(Div_MB.show==true){
          Div_MB.mb="col-md-8"
        }
        else{
          Div_MB.mb="col-md-4"
        }
       }

const closeClick=async()=>{
 CurrentPage.fileName='Feedback'
 CurrentPage.key='Feedback'
 ChangePage({ fileName: 'Feedback', key: 'Feedback'})
  }
const replyClick=async()=>{
  const dataToUpdate = { comment: reply.value,r_withSupportTicketComment_c_supportTicketId:ProjectApiId?.id
        };
 const updatedData = await AddDataPost('/o/c/supportticketcomments'+"?p_auth="+`${Liferay.authToken}`, dataToUpdate);
 loadDataComment()
 reply.value='';
}
return {
  onChangeNav,
  ProjectGetOneData,
  ProjectApiId,
  Div_MB,
  ChangeDateFormat,
  closeClick,
  replyClick,
  reply,
  CommentGetOneData
}
}

}
</script>


<template>
  <div class="row">
    <div class="col-md-3 border-right">
      <h2 class="my-4 line-height-15">Ticket<br />details</h2>
      <p class="text-default font-12">Project title & ID</p>
      <p class="font-12">{{ ProjectGetOneData?.issuseTitle ?
        ProjectGetOneData?.issuseTitle+"  "+ProjectGetOneData?.id: '<p>No Data Found</p>' }}.</p>
       <p class="text-default font-12">Circuit ID</p>
      <p class="font-12">{{ ProjectGetOneData?.id ?
        ProjectGetOneData?.id : '<p>No Data Found</p>' }}.</p>
         <p class="text-default font-12">Raised by</p>
      <p class="font-12">{{ ProjectGetOneData?.creator?.name ?
        ProjectGetOneData?.creator?.name : '<p>No Data Found</p>' }}.</p>
         <p class="text-default font-12">Raised on</p>
      <p class="font-12">{{ ProjectGetOneData?.dateCreated ?
        ChangeDateFormat(ProjectGetOneData.dateCreated) : '<p>No Data Found</p>' }}.</p>
         <p class="text-default font-12">Closed On</p>
      <p class="font-12">{{ ProjectGetOneData?.completionDate ?
       ChangeDateFormat(ProjectGetOneData?.completionDate): '<p>No Data Found</p>' }}.</p>

<div class="mt-50 mb-4">
              <button class="btn btn-default btn-block" type="button" @click="closeClick" v-if="!ProjectGetOneData.completionDate">Close Ticket</button>
            </div>
    </div>
    <div class="col-md-9">
      <div class="mt-3">
         <table class="table table-sm table-borderless">
          <thead>
            <tr>
              <th><button type="button" class="btn btn-default btn-lg" v-if=(!Div_MB.show) @click="onChangeNav">
                <span class="glyphicon glyphicon-nav" aria-hidden="true"></span>
               </button>
               <button type="button" class="btn btn-default btn-lg" v-if=(Div_MB.show) @click="onChangeNav">
                X
               </button>
                
              </th>
              <th>Status</th>
              <th>Support type</th>
              <th>Priority</th>
              <th>Response time</th>
              <th colspan="3"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td></td>
              <td>{{ ProjectGetOneData?.status?.label_i18n }}</td>
              <td>{{ ProjectGetOneData?.ticketType ? ProjectGetOneData?.ticketType : '<p>No Data Found</p>' }}
              </td>
              <td>{{ ProjectGetOneData?.priority ?
                ProjectGetOneData?.priority : '<p>No Data Found</p>' }}</td>
                <td>{{ ProjectGetOneData?.priority ?
                ProjectGetOneData?.priority : '<p>No Data Found</p>' }}</td>
                <td colspan="3"></td>
            </tr>
          </tbody>
        </table> 
        <hr />
        <!---Ticket Description and comment  start ----->
        <div class="row mt-4">
          <div class="col-md-4" v-if=(Div_MB.show)>
            <div class="card mnh-140p mb-40">
              <div class="card-body">
                <div class="row">
                  <div class="col-md-12">
                    <p class="mb-0 font-10 text-default">Ticket Description</p>
                    <p class="mb-0">{{ ProjectGetOneData?.issusDescriptions }}</p>
                    <br/>
                  </div>
                  <div class="col-md-12">
                    <p class="mb-0 font-10 text-default">Attachment</p>
                    <p class="mb-0">{{ ProjectGetOneData?.issusDescriptions }}</p>
                  </div>
                </div>
                <div class="mt-200 row">
                    <div class="col-md-6">
                      <p class="mb-0 font-10 text-default">Project Manager</p>
                      <p class="font-12">{{ ProjectGetOneData?.creator?.familyName ?
        ProjectGetOneData?.creator?.familyName : '<p>No Data Found</p>' }}.</p>
                    </div>
                    <div class="col-md-6">
                      <p class="mb-0 font-10 text-default">Account Manager</p>
                      <p class="font-12">{{ ProjectGetOneData?.creator?.familyName ?
        ProjectGetOneData?.creator?.familyName : '<p>No Data Found</p>' }}.</p>
                    </div>
                  </div>
              </div>
            </div>
          </div>
          <div :class=Div_MB.mb>
            <div class="card mnh-140p mb-40">
              <div class="card-body">
                <div class="card " v-if="CommentGetOneData.length > 0" v-for="item in CommentGetOneData" :key="item.id">
                  <div class="card-body">
                <p >
                  <img src="https://media.istockphoto.com/id/1419410282/photo/silent-forest-in-spring-with-beautiful-bright-sun-rays.jpg?s=1024x1024&amp;w=is&amp;k=20&amp;c=K8yBJVB-TtpPF1O2zOhVlzXECDxJsadlRrLf4gXXNkk=" height="10">
                  {{ item?.creator?.name }}</p>
                <p>{{ item?.comment }}</p>
                <p>{{ ChangeDateFormat(item?.dateCreated) }}</p>
              </div>
              </div>

              <div class="mt-200 row" v-if="!ProjectGetOneData.completionDate">
                    <div class="col-md-9">
                      Reply
                      <textarea v-model.trim="reply"></textarea>
                      
                    </div>
                    <div class="col-md-3">
                      <button style="position: absolute;
    bottom: 0;" @click="replyClick">Send</button>
                    </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
        <!---Ticket Description and comment  end----->
      </div>
    </div>
  </div>
</template>