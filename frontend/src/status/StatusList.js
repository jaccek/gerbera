import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Status.css'
import Status from './Status'

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function StatusList(props) {
  const [response, setResponse] = useState(null);

  async function getData() {
      axios.get('http://localhost:8080/status')
        .then(function (response) {
          console.log(response);
          handleResponse(response);
        })
        .catch(function (error) {
          console.log(error);
          // TODO: handle error
        });
  }

  function handleResponse(response) {
    setResponse(response)
  }

  useEffect(() => {
    if (response === null) {
      getData()
    }
    const timer = setInterval(() => getData(), 5000)
    return () => {
      clearInterval(timer)
    }
  })

  var items = []
  if (response !== null) {
    response.data.forEach((status) => {
      items.push(<Status serviceName={status.name}
              environment={status.environment}
              version={status.version}
              status={status.status}/>)
    })
  }

  return (
    <div className="statuses">
      {items}
    </div>
  );
}

export default StatusList;
