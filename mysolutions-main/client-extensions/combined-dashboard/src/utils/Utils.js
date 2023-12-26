import axios from 'axios'


export async function makeRequestFetch (url) {
  let axiosConfiglocal = {
    method: 'GET',
    url,
    headers: {
      Authorization:  localStorage.getItem('authToken')?localStorage.getItem('authToken'):'',
      'Content-Type': 'application/json'
    }
  }

  let response = await axios(axiosConfiglocal)
  console.log('inside utils', response)

  return response.data
}

export async function makeRequest (url, method, data) {
  let headers={
    Authorization: localStorage.getItem('authToken')?localStorage.getItem('authToken'):'',
    'Content-Type': 'application/json'
  }

  const axiosConfig = {
    method,
    url,
    headers,
    data: data ? JSON.stringify(data) : undefined
  }

  try {
    const response = await axios(axiosConfig)

    if (method === 'GET' || method === 'DELETE') {
      const payload = await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload: payload
      }

      return responseData ;
    }
    else if (method === 'POST') {
      const payload = await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload: payload
      }

      return responseData ;
    }
  } catch (error) {
    console.log('inside catch')
    console.log(error)
    //  console.log("inside onmount")
    console.error('Error:', error)
   
    const responseData = {
      status: error.response.status,
      message: error.message,
      payload: null
    }
    return responseData
    // throw error; // Re-throw the error for further handling if needed
  }
}

export function ChangeDateFormat (date) {
  const originalDate = new Date(date)
  const options = { year: 'numeric', month: 'short', day: 'numeric' }

  const formattedDate = originalDate.toLocaleDateString('en-US', options)
  return formattedDate
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
