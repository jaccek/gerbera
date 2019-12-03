import React, { useState } from 'react';
import axios from 'axios';

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
    <div className="status">
        <h1>Status of {props.serviceName}</h1>
        <p>It's working {count}</p>
        <button onClick={() => setCount(count + 1)}>
          Click me
        </button>
    </div>
  );
}

export default Status;
