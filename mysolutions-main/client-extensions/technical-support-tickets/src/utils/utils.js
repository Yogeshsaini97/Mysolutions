import axios from 'axios';
import exportFromJSON from "export-from-json";
import { ref } from 'vue';



export async function makeRequestFetch (url) {
let axiosConfiglocal={
  method: 'GET',
    url,
    headers: {
      Authorization:localStorage.getItem('authToken')?localStorage.getItem('authToken'):'',
      'Content-Type': 'application/json'
    },
}

  let response = await axios(axiosConfiglocal);
  console.log("inside utils",response)
 
  return response.data;
}




export async function makeRequest(url, method, data) {

  let headers={
    Authorization: localStorage.getItem('authToken')?localStorage.getItem('authToken'):'',
    'Content-Type': 'application/json'
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
  let day = formattedDate.getDate();
  const month = months[formattedDate.getMonth()];
  const year = formattedDate.getFullYear();

  const daySuffix = getDaySuffix(day);

  if(day>0 && day<=9)
  {
    day = "0"+ day;
  }


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



export function countNonNullAllProperties(obj) {
  const selectedStatusHasItems =
    Array.isArray(obj.selectedStatus) && obj.selectedStatus.length > 0;
  const selectedRaisedItems =
    Array.isArray(obj.selectedRaised) && obj.selectedRaised.length > 0;
  const selectedStatusPriorityItems =
    Array.isArray(obj.selectedServices) && obj.selectedServices.length > 0;
  let result = null;

  if (selectedStatusHasItems && selectedRaisedItems && selectedStatusPriorityItems) {
    result = 3;
  } else if (
    (selectedStatusHasItems && selectedRaisedItems) ||
    (selectedStatusHasItems && selectedStatusPriorityItems) ||
    (selectedRaisedItems && selectedStatusPriorityItems)
  ) {
    result = 2;
  } else if (selectedStatusHasItems || selectedRaisedItems || selectedStatusPriorityItems) {
    result = 1;
  }

  return result;
}



export function applyColorToStatus(status){


  if(status == 'In Progress' || status == 'Active' || status == 'On Time' || status=="HO-In Progress" ||  status == 'Open')
  {
    return "bg-light-danger";
  }
  
  else if(status == 'Closed' || status == 'Closed' || status == 'Late' )
  {
    return "bg-light-success";
  }
  else if(status== 'Completed' || status== 'New' )  
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
  else if(status== 'Medium' || status== ' Moderate' ) 
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
  else if(status== 'Medium' || status== ' Moderate' ) 
  {
    return "colorPurpletext ";
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
        const fileName = CRandProjectnumber + '_' + 'tickets' +
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
       const organisationCount = ref(0);
       const showOrganisation = apiGot.map(element => {
        return element.organization;
      });
      console.log("organisation",showOrganisation)
      showOrganisation.forEach(p => {
        if( p === 'Solutions by STC' )
        {
               organisationCount.value++;
        }
      })
        finalorganisationValue.value=organisationCount.value;
        return finalorganisationValue;
}




export function risk_issue_priority_count (apiGot) {
 
  console.log(apiGot)

  let Count=0;
 

  apiGot.forEach((data)=>
  {
    if(data?.status?.split(")")[1]=="Active")
    {
Count++;
    }
  })

  return Count;


  
  // const urlStatus = ref(null);
  // urlStatus.value = apiGot;
  
  // console.log(urlStatus.value)
  // console.log("Debug")
 
  // const array1 = ref([]);
  // const array2 = ref([]);
  // const array3 = ref([]);
  // const array4 = ref([]);
 
  // const showCritical = ref(null);
  // const showHigh = ref(null);
  // const showMedium = ref(null);
  // const showLow = ref(null);
 
  // const priorities1 = urlStatus.value.map(element => {
  //   return element.priority;
  // });
 
  // // console.log(priorities1[0].split(')')[1]);
 
 
 
 
  // priorities1.forEach(p => {
   
  //      if (p?.split(')')[1] === ' Critical') {
 
  //         array1.value.push(p)
         
  //       }
 
  //       else if (p?.split(')')[1] === ' High') {
 
  //         array2.value.push(p)
           
  //       }
 
  //       else if (p?.split(')')[1] === ' Medium') {
 
  //         array3.value.push(p)
           
  //       }
 
  //       else if (p?.split(')')[1] === ' Low') {
 
  //         array4.value.push(p)
           
  //       }
       
  //     })
       
  //     console.log(array1.value.length);
  //     console.log(array2.value.length);
  //     console.log(array3.value.length);
  //     console.log(array4.value.length);
 
  //     showCritical.value = array1.value.length;
  //     console.log("mycriticalvalue",showCritical.value)
 
 
 
 
  //     showHigh.value = array2.value.length;
  //     console.log("myhighvalue",showHigh.value)
 
    
 
  //     showMedium.value = array3.value.length;
  //     console.log("mymediumvalue",showMedium.value)
 
   
 
 
 
  //     showLow.value = array4.value.length;
  //     console.log("mylowvalue",showLow.value)
 
      
 

  //     if(showCritical.value>0)
  //     {
  //     console.log({Count:array1.value.length, Name:"Critical"})
  //       return {Count:array1.value.length, Name:" Critical"}
       
  //     }
  //     else if(showHigh.value>0)
  //     {
  //    console.log( {Count:array2.value.length, Name:"High"})
  //       return {Count:array2.value.length, Name:" High"}
  //     }
  //     else if(showMedium.value>0)
  //     {
  //      console.log({Count:array3.value.length, Name:"Medium"} )
      
  //       return {Count:array3.value.length, Name:" Medium"} 
  //     }
  //     else if(showLow.value>0){
  //            console.log({Count:array4.value.length, Name:"Low"})
      
  //       return {Count:array4.value.length, Name:" Low"}
       
 
  //     }


      return;


    
     
     
    }


    export async function getTempAuth() {
      console.log("one")
      let url='http://172.19.53.234:8080/o/oauth2/token';
      console.log("two")
    
      let formData={  'client_secret':'secret-5bf34fb6-3bbe-3a2b-2d6e-78c7e1973be',
    'client_id':'id-d095d1dc-d91f-72e3-80ea-58c97b9d5d5f',
    'grant_type':'client_credentials'}
    
    
    const params = new URLSearchParams(formData);
    
    console.log("three")
      let axiosConfiglocal = {
        method: 'POST',
        url,
        data: params,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      };
    
      console.log('four')
      try {
        let response = await axios(axiosConfiglocal);
        console.log('inside auth', response);
        console.log('auth ',response?.data?.access_token)
        
       
    
        localStorage.setItem('authToken',`Bearer ${response?.data?.access_token}`);
    
      
        console.log('five')
    
        return response.data;
      } catch (error) {
        // Handle error appropriately
        console.log('fsix')
        console.error('Error in postTempAuth:', error);
        throw error;
      }
    }


    export async function convertFileToViewableFormat(downloadingLoader)
    {

      console.log(downloadingLoader)
        downloadingLoader.value=true;
        console.log(downloadingLoader)
     
      
      
    const fileUrlToConvert={"URL": "https://epm.stcs.com.sa/PWA/097755-MEWA%20Network%20Infrastructure-Ministry%20of%20Environment,%20Water%20%20And%20%20Agriculture-2019/Shared%20Documents/7-Closing/COC1.jpg"}
   
      
    let response;

      try {
        response = await makeRequest("http://172.19.53.234:8080/o/external-api-headless/v1.0/post-epm-document?endpointURL=epm/getepmfile?api_key=twrk932m4jvmsfe9hzy2avgr" ,"POST",fileUrlToConvert);
        
        if (response && (response.status >=200 && response.status<300) && response?.payload?.ReturnMessage ) {
          if (fileUrlToConvert?.URL.toLowerCase().includes('.pdf')) {
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
          alert("error")
          // Show an error message to the user
          // For example: display an error alert or message
        }
      } catch (error) {
        console.error('Error:', error);
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