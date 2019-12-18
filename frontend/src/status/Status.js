import React, { useState } from 'react';
import axios from 'axios';
import './Status.css'

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function Status(props) {
  const [count, setCount] = useState(0);

  async function getdata() {
      axios.get('http://localhost:8080/status')
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
  }

  function handleResponse() {
    setCount(count + 1)
  }

  if (count === 0) {
    getdata()
  }
  return (
    <div className="Status">
        <p className="Status-service">{props.serviceName}</p>
        <p className="Status-environment">prod</p>
        <p className="Status-version">v1.1.3</p>
    </div>
  );
}

export default Status;
