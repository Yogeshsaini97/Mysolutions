import axios from 'axios';
import exportFromJSON from "export-from-json";
import { ref } from 'vue';

const authToken =
  `Bearer ` +
  'eyJ0eXAiOiJhdCtqd3QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJiNGJkNWFiNDEzYmQ4MDMzNzBmMDNiMWE1YzViNDhiZTM4YTlmNzA4Nzg0OWJhMGEzZmJiNjJkMzdmODI1MmYiLCJjbGllbnRfaWQiOiJpZC1kMDk1ZDFkYy1kOTFmLTcyZTMtODBlYS01OGM5N2I5ZDVkNWYiLCJpYXQiOjE3MDA0MDYxNDQsImV4cCI6MTcwNjQwNjE0NCwic3ViIjoiMjAxMjMiLCJ1c2VybmFtZSI6Im11aGFtbWFkIiwiaXNzIjoiMTcyLjE5LjUzLjIzNCIsInNjb3BlIjoiQ19Vc2VyTm90aWZpY2F0aW9uLmV2ZXJ5dGhpbmcgQ19Vc2VyTm90aWZpY2F0aW9uLmV2ZXJ5dGhpbmcucmVhZCBDX1VzZXJOb3RpZmljYXRpb24uZXZlcnl0aGluZy53cml0ZSBDX0FjdGl2aXR5TG9nLmV2ZXJ5dGhpbmcucmVhZCBDX0FjdGl2aXR5TG9nLmV2ZXJ5dGhpbmcud3JpdGUgQ19BY3Rpdml0eUxvZy5ldmVyeXRoaW5nIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyJ9.K6jARKJh9vQDIweJFKXvgyvBzLAgncEPIi6ECuSuwp9OW5yalN7WScpQgXOCuH29u44X1FmpLCi3T4crNlDLJbGXBexTI7RwMbowOm21EsPdnI9vYYumGdx1mzezVEqtyU5IoS3_7DgbJJvNmJ7JOydEejrtbSWbONaoLeDUGu2bgFjDtgYdbBTkiA1gXIt-xkY-OjrRBVwXDGulLLZxud4ba92Le4crxybgpyLamliaEnhqurq9T8opfLXdm_qV8TTaXDxsS7DK9YsVgM1E3YSZXewAnskp6FJIQEVBI5vx0ZExNmhN2kZo1d6uX5DPrF_f68_gFyDAbLFVY_45-Q'

  export async function fetchData(url) {
   
    let response = await fetch(url, {
        method: 'GET'
        ,
        headers: {
          Authorization: authToken,
          'Content-Type': 'application/json'
        }
      })
         
      response=await response.json();
   
  
  
  return response;    
  }

export async function makeRequestFetch (url) {
let axiosConfiglocal={
  method: 'GET',
    url,
    headers: {
    Authorization:authToken,
      'Content-Type': 'application/json'
    },
}

  let response = await axios(axiosConfiglocal);
  console.log("inside utils",response)
  // response = await response.json()

  // console.log("inside utils 2",response)

  return response.data;
}




export async function makeRequest(url, method, data) {

  let headers;
  
  if(url.split("/").includes("approvals"))
  {
     headers = {
      'Content-Type': 'application/json',
      'Client-Id':'Takyah',
      'api_key':'twrk932m4jvmsfe9hzy2avgr',
      'Sender-Msg-Id':'bf47adc6-7dc2-4572-a3a1-f2a0319ce930',
      'Connection':'Keep-Alive',
      // 'Access-Control-Allow-Origin': '*',
      'Host':'apitest.stcs.com.sa',
      'changeOrigin': true,
      // Authorization:authToken 
   
    };
  }
  else{

    headers = {
      'Content-Type': 'application/json',
      'Client-Id':'MySolutions',
      'api_key':'atkbua9v6u7yh9xmkxdeh6w8',
      'Sender-Msg-Id':'fd390d9-229b-4ad9-925a-d3lkb488a4ae',
      'Connection':'Keep-Alive',
      // 'Access-Control-Allow-Origin': '*',
      'Host':'apitest.stcs.com.sa',
      'changeOrigin': true,
      // Authorization:authToken 
   
    };
  

  }


  
  const axiosConfig = {
    method,
    url,
    headers,
    data: data ? JSON.stringify(data) : undefined,
  };

  try {
    const response = await axios(axiosConfig)
    

    if (method === 'GET' || method === 'DELETE' || method ==='POST') {
      const payload=await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload:payload
      };
      
      return responseData;
    
    }

   
  } catch (error) {
    console.log('inside catch')
    console.log(error)
    //  console.log("inside onmount")
    console.error('Error:', error);
    // const errorMessage = getErrorMessage(error.response.status);
     const responseData = {
       status:error.response?error.response.status:null,
        message: error.message,
        payload:null
      };
      return responseData
    // throw error; // Re-throw the error for further handling if needed
  }
}

export function ChangeDateFormat(date) {
  const months = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
  ];

  const formattedDate = new Date(date);
  const day = formattedDate.getDate();
  const month = months[formattedDate.getMonth()];
  const year = formattedDate.getFullYear();

  const daySuffix = getDaySuffix(day);

  return `${day} ${month} ${year}`;
}

function getDaySuffix(day) {
  if (day >= 11 && day <= 13) {
    return 'th';
  }
  switch (day % 10) {
    case 1:
      return 'st';
    case 2:
      return 'nd';
    case 3:
      return 'rd';
    default:
      return 'th';
  }
}

export function formatTimeTo12HourFormat (dateString) {
  const date = new Date(dateString)
  const hours = date.getHours()
  const minutes = date.getMinutes()

  const amOrPm = hours >= 12 ? 'PM' : 'AM'
  const formattedHours = hours % 12 || 12
  const formattedMinutes = minutes.toString().padStart(2, '0')

  const formattedTime = `${formattedHours}:${formattedMinutes} ${amOrPm}`
  return formattedTime
}

export function GiveDayandmonthseperate (inputDate) {
  // Convert the input date string to a Date object
  const dateObj = new Date(inputDate)

  // Get the day of the month in the format "dd"
  const day = String(dateObj.getDate()).padStart(2, '0')

  // Get the month abbreviation (e.g., "Jul") and the year in 2-digit format (e.g., "'23")
  const monthAbbreviation = new Intl.DateTimeFormat('en', {
    month: 'short'
  }).format(dateObj)
  const yearTwoDigits = new Intl.DateTimeFormat('en', {
    year: '2-digit'
  }).format(dateObj)

  // Construct the formatted date string
  const formattedDate = `${monthAbbreviation} '${yearTwoDigits}`

  return { day, formattedDate }
}

export function find_num_of_risksnissues (apiGot) {
  no_of_Risks = 0
  no_of_Issues = 0

  console.log(apiGot)
  apiGot.items.forEach(element => {
    if (element.type == 'Issue') {
      no_of_Issues++
    } else if (element.type == 'Risk') {
      no_of_Risks++
    }
  })
}



export function formatCurrency (value, currency){
  if(currency=="SAR"){
    const formatter = new Intl.NumberFormat('ar-SA', {
      style: 'currency',
      currency: currency,
      minimumFractionDigits: 2,
    });
  
    return formatter.format(value);
  }
  else{
    return new Intl.NumberFormat(currency,{ minimumFractionDigits: 2 }).format(value);
  }
  
};


export function applyColorToStatus(status){


  if(status == 'In Progress' || status == 'Active' || status == 'On Time' || status=="HO-In Progress")
  {
    return "bg-light-success";
  }
  
  else if(status == 'Closed' || status == 'Closed' || status == 'Late' )
  {
    return "bg-light-danger";
  }
  else if(status== 'Completed') 
  {
    return "colorPurple";
  }

  else if(status == 'Cancelled')
  {
    return "StatusCanceled";
  }
  else if(status=="Critical")
  {
    return "redtext";
  }
  else if(status == 'High'  )
  {
    return "yellowtext";
  }
  else if(status== 'Medium') 
  {
    return "colorPurple ";
  }

  
  else if(status=="Low")
  {
    return "greytext";
  }
  
  if(status == ' In Progress' || status == ' Active' || status == ' On Time' || status==" HO-In Progress")
  {
    return "bg-light-success";
  }
  
  else if(status == ' Closed' || status == ' Closed' || status == ' Late' )
  {
    return "bg-light-success";
  }
  else if(status== ' Completed') 
  {
    return "colorPurple ";
  }

  else if(status == ' Cancelled')
  {
    return "StatusCanceled";
  }
  else if(status==" Critical")
  {
    return "redtext";
  }
  else if(status == ' High'  )
  {
    return "yellowtext";
  }
  else if(status== ' Medium') 
  {
    return "colorPurple ";
  }

  
  else if(status==" Low")
  {
    return "greytext";
  }


 
    

}

export function applyColorToStatustext(status){


  if(status == 'In Progress' || status == 'Active' || status == 'On Time' || status=="HO-In Progress")
  {
    return "bg-light-success";
  }
  
  else if(status == 'Closed' || status == 'Closed' || status == 'Late' )
  {
    return "bg-light-danger";
  }
  else if(status== 'Completed') 
  {
    return "bg-default";
  }

  else if(status == 'Cancelled')
  {
    return "StatusCanceled";
  }
  else if(status=="Critical")
  {
    return "redtext";
  }
  else if(status == 'High'  )
  {
    return "yellowtext";
  }
  else if(status== 'Medium') 
  {
    return "colorPurple ";
  }

  
  else if(status=="Low")
  {
    return "greytext";
  }
  if(status == ' In Progress' || status == ' Active' || status == ' On Time' || status==" HO-In Progress")
  {
    return "text-purple";
  }
  
  else if(status == ' Closed' || status == ' Closed' || status == ' Late' )
  {
    return "bg-light-danger";
  }
  else if(status== ' Completed') 
  {
    return "colorPurple ";
  }

  else if(status == ' Cancelled')
  {
    return "StatusCanceled";
  }
  else if(status==" Critical")
  {
    return "redtext";
  }
  else if(status == ' High'  )
  {
    return "yellowtext";
  }
  else if(status== ' Medium') 
  {
    return "colorPurple ";
  }

  
  else if(status==" Low")
  {
    return "greytext";
  }


}
    
export function downloadExcel (CRandProjectnumber,data1,fileName1){
        let data = data1;
        const fileName = CRandProjectnumber + '_' + fileName1 +
            '_' + new Date().toISOString().slice(0, 10).replace(/-/g, '');
        const exportType = exportFromJSON.types.csv;
        if (data) exportFromJSON({
            data,
            fileName,
            exportType
        });

}




export function find_num_of_stakeholderM (apiGot,finalorganisationValue)
{
  finalorganisationValue.value=0;
       const organisationCount = ref(0);
       const showOrganisation = apiGot.map(element => {
        return element.organization;
      });
      console.log("organisation",showOrganisation)
      showOrganisation.forEach(p => {
        if( p === 'Solutions by STC' || p==='stcs.com.sa' )
        {
               organisationCount.value++;
        }
      })
        finalorganisationValue.value=organisationCount.value;
        return finalorganisationValue;
}




export function risk_issue_priority_count (apiGot) {
 
  console.log(apiGot)
  const urlStatus = ref(null);
  urlStatus.value = apiGot;
  
  console.log(urlStatus.value)
  console.log("Debug")
 
  const array1 = ref([]);
  const array2 = ref([]);
  const array3 = ref([]);
  const array4 = ref([]);
 
  const showCritical = ref(null);
  const showHigh = ref(null);
  const showMedium = ref(null);
  const showLow = ref(null);
 
  const priorities1 = urlStatus.value.map(element => {
    return element.priority;
  });
 
  // console.log(priorities1[0].split(')')[1]);
 
 
 
 
  priorities1.forEach(p => {
   
       if (p?.split(')')[1] === ' Critical') {
 
          array1.value.push(p)
         
        }
 
        else if (p?.split(')')[1] === ' High') {
 
          array2.value.push(p)
           
        }
 
        else if (p?.split(')')[1] === ' Medium') {
 
          array3.value.push(p)
           
        }
 
        else if (p?.split(')')[1] === ' Low') {
 
          array4.value.push(p)
           
        }
       
      })
       
      console.log(array1.value.length);
      console.log(array2.value.length);
      console.log(array3.value.length);
      console.log(array4.value.length);
 
      showCritical.value = array1.value.length;
      console.log("mycriticalvalue",showCritical.value)
 
 
 
 
      showHigh.value = array2.value.length;
      console.log("myhighvalue",showHigh.value)
 
    
 
      showMedium.value = array3.value.length;
      console.log("mymediumvalue",showMedium.value)
 
   
 
 
 
      showLow.value = array4.value.length;
      console.log("mylowvalue",showLow.value)
 
      
 

      if(showCritical.value>0)
      {
      console.log({Count:array1.value.length, Name:"Critical"})
        return {Count:array1.value.length, Name:" Critical"}
       
      }
      else if(showHigh.value>0)
      {
     console.log( {Count:array2.value.length, Name:"High"})
        return {Count:array2.value.length, Name:" High"}
      }
      else if(showMedium.value>0)
      {
       console.log({Count:array3.value.length, Name:"Medium"} )
      
        return {Count:array3.value.length, Name:" Medium"} 
      }
      else if(showLow.value>0){
             console.log({Count:array4.value.length, Name:"Low"})
      
        return {Count:array4.value.length, Name:" Low"}
       
 
      }


      return;


    
     
     
    }


    export async function convertFileToViewableFormat(downloadingLoader)
    {

      console.log(downloadingLoader)
        downloadingLoader.value=true;
        console.log(downloadingLoader)
     
      
      
      const fileUrlToConvert={"URL": "https://epm.stcs.com.sa/PWA/097755-MEWA%20Network%20Infrastructure-Ministry%20of%20Environment,%20Water%20%20And%20%20Agriculture-2019/Shared%20Documents/7-Closing/COC1.jpg"}
        
      // Your file URL here
// let base64Data=;
  //  convertBase64ToPdf(base64Data)
      let response;
      try {
        response = await makeRequest( 
          "http://172.19.53.234:8080/o/external-api-headless/v1.0/post-epm-document?endpointURL=epm/getepmfile?api_key=twrk932m4jvmsfe9hzy2avgr" ,"POST",fileUrlToConvert);
          console.log(response);
        if (response && (response.status >=200 && response.status<300) && response?.payload?.ReturnMessage ) {
          if (fileUrlToConvert?.URL.toLowerCase().includes('.pdf')) {
            // If the URL contains .pdf, open it as a PDF
            console.log("mybase64",response);
            console.log("worked")
            downloadingLoader.value=false;
            convertBase64ToPdf(response?.payload?.ReturnMessage)
          } else if (fileUrlToConvert?.URL.toLowerCase().includes('.jpg') || fileUrlToConvert?.URL.toLowerCase().includes('.jpeg') || fileUrlToConvert?.URL.toLowerCase().includes('.png')) {
            // For image formats like .jpg, .jpeg, .png, etc.
            // convertBase64ToPdf(response?.payload?.ReturnMessage)
            console.log("worked")
            downloadingLoader.value=false;
           convertBase64ToImg(response?.payload?.ReturnMessage)
          } else {
            // For other file types, handling as a generic download
            downloadingLoader.value=false;
            downloadFile(fileUrlToConvert?.URL);
          }
        } else {
          // Handle error response
          console.error('Error: Unable to process the file.');
          downloadingLoader.value=false;
          alert("error")
          // Show an error message to the user
          // For example: display an error alert or message
        }
      } catch (error) {
        console.error('Error:', error);
        downloadingLoader.value=false;
        alert("error")
        // Handle error, e.g., network issue
        // Show an error message to the user
        // For example: display a network error message
      }
    }





    function convertBase64ToPdf(base64Data)
    {
      const base64DataPdf = ref(''); // Base64 data state

      // Assign your Base64 data here
      base64DataPdf.value = base64Data;
  
    
        if (base64DataPdf.value) {
          // Remove header from the base64 data (e.g., 'data:application/pdf;base64,')
          const base64WithoutHeader = base64DataPdf.value.replace(/^data:application\/pdf;base64,/, '');
  
          // Convert base64 to binary data
          const binaryData = atob(base64WithoutHeader);
  
          // Create a Uint8Array from the binary data
          const array = new Uint8Array(binaryData.length);
          for (let i = 0; i < binaryData.length; i++) {
            array[i] = binaryData.charCodeAt(i);
          }
  
          // Create a Blob from the Uint8Array
          const blob = new Blob([array], { type: 'application/pdf' });
  
          // Create a URL for the Blob
          const pdfURL = URL.createObjectURL(blob);
  
         // Create a temporary anchor element
const downloadLink = document.createElement('a');

// Set the href attribute to the Blob URL
downloadLink.href = pdfURL;

// Set the download attribute to specify the file name
downloadLink.download = 'generated_pdf.pdf';

// Simulate a click on the anchor element to trigger the download
downloadLink.click();
        } else {
          // Handle no base64 data available
          console.error('No base64 data available.');
        }
    
    }

    function convertBase64ToImg(base64Data)
    {
      const base64DataJPG = base64Data;

      // Convert base64 to a Blob
      const byteCharacters = atob(base64DataJPG);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      const blob = new Blob([byteArray], { type: 'image/jpeg' });
      
      // Create a URL for the Blob
      const url = URL.createObjectURL(blob);
      
    // Create a temporary anchor element
const downloadLink = document.createElement('a');

// Set the href attribute to the Blob URL
downloadLink.href = url;

// Set the download attribute to specify the file name
downloadLink.download = 'image.jpg';

// Simulate a click on the anchor element to trigger the download
downloadLink.click();
    }