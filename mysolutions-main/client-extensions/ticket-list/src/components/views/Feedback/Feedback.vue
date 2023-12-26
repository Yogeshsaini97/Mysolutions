<script>
import { onMounted,inject,ref,reactive} from 'vue';
import { ChangeDateFormat,fetchData,updateData,formatDate,AddDataPost } from '../../../Utils/Utils';
export default {
setup(props, context) {
const ProjectGetOneData =ref('');
const formData =reactive({question1:'',question2:null,question3:null,question4:null,question5:null});
const ProjectApiId = inject('ProjectApiId');
const BackButton = inject('BackButton');
const ChangePage = inject('ChangePage');
const CurrentPage =inject('CurrentPage');
const hostUrl="/o/c/supporttickets/"+`${ProjectApiId?.id}`+"?p_auth="+`${Liferay.authToken}`;

  const loadData=async () => {
        try {
          const response = await fetchData(hostUrl);
          ProjectGetOneData.value = response;
          console.log("ProjectGetOneData",ProjectGetOneData);
        }
        catch (error) {
          console.error(error);
        }
      }


      onMounted(() => {
      loadData();
    });

const replyClick=async()=>{
  const dataToUpdate = {
      "completionDate": formatDate(),
        };
 const updatedData = await updateData(hostUrl, dataToUpdate);
 CurrentPage.fileName='ListPage'
 CurrentPage.key='ListPage'
 ChangePage({ fileName: 'ListPage', key: 'ListPage'})
}

const submitForm=async()=>{
  const dataToUpdate = {"question1":formData.question1,
    "question2":{key:formData.question2},
    "question3":{key:formData.question3},
    "question4":{key:formData.question4},
    "question5":{key:formData.question5},"r_supportTicketFeedBack_c_supportTicketId":ProjectApiId?.id}
 const updatedData = await AddDataPost('/o/c/supportticketfeedbacks'+"?p_auth="+`${Liferay.authToken}`, dataToUpdate);
  formData.question1='';
  formData.question2=null;
  formData.question3=null;
  formData.question4=null;
  formData.question5=null;
  replyClick();
}
return {
  ProjectGetOneData,
  ProjectApiId,
  ChangeDateFormat,
  replyClick,
  submitForm,
  formData
}
}

}
</script>
<template>
    <div class="row">
      <div class="col-md-3 border-right">
        <h2 class="my-4 line-height-15">Support<br />feedback</h2>
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
      </div>
      <div class="col-md-9">
        <div class="mt-3">
          <!---Feedback start ----->
          <form @submit.prevent="submitForm">
          <div class="row mt-12">
            <div class="col-md-12">
              <div class="card mnh-140p mb-40">
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-12">
                      <p class="mb-0 font-12 text-default">How satisfied are you with your experience at the Solution Support Team?</p>
                      <p class="mb-0"><textarea cols="50" rows="5" v-model.trim="formData.question1"></textarea></p>
                      <br/>
                    </div>
                    <div class="col-md-12">
                      <p class="mb-0 font-10 text-default">Did you get the necessary help from our team?</p>
                      <p class="mb-0"> 
                        <label>
                        <input type="radio"  value="yes"  v-model="formData.question2"/>Yes
                        </label>  <label>
                        <input type="radio"  value="no" v-model="formData.question2"/>No
                        </label></p>
                    </div>
                    <div class="col-md-12">
                      <p class="mb-0 font-10 text-default">Would you want us to arrange for a callback?</p>
                      <p class="mb-0"> 
                        <label>
                        <input type="radio"  value="yes" v-model="formData.question3"/>Yes
                        </label>  <label>
                        <input type="radio"  value="no" v-model="formData.question3"/>No
                        </label></p>
                    </div>
                    <div class="col-md-12">
                      <p class="mb-0 font-10 text-default">How easy was it to get your query or concern resolved by the solutions team?</p>
                      <p class="mb-0"> 
                        <label>
                        <input type="radio"  value="yes" v-model="formData.question4" />Extremely easy
                        </label>  <label>
                        <input type="radio"  value="no" v-model="formData.question4"/>Extremely difficulty
                        </label></p>
                    </div>
                    <div class="col-md-12">
                      <p class="mb-0 font-10 text-default">Based on your most recent intersection with us, how likely  are you to recommend our services?</p>
                      <p class="mb-0"> 
                        <label>
                        <input type="radio"  value="yes" v-model="formData.question5"/>Very Likely
                        </label>  <label>
                        <input type="radio"  value="no" v-model="formData.question5" />Very Unlikely
                        </label></p>
                    </div>

                    <div class="mt-30 row">
                  
                        <button class="btn btn-default">Submit my feedback</button>&nbsp;
        <button class="btn btn-primary" type="button" @click="replyClick">Close & Skip feedback</button>
              </div>
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
         <!---Feedback start end----->
        </form>
        </div>
      </div>
    </div>
  </template>