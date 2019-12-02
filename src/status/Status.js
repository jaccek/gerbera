import React, { useState } from 'react';

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function Status(props) {
  const [count, setCount] = useState(0);

  async function getdata() {
      await sleep(2000);

      // create a new XMLHttpRequest
      var xhr = new XMLHttpRequest()

      // get a callback when the server responds
      xhr.addEventListener('load', () => {
        // update the state of the component with the result here
        console.log(xhr.responseText)
        handleResponse()
      })
      // open the request with the verb and the url
      xhr.withCredentials = false;
      xhr.open('GET', 'http://localhost:3000/status')
      xhr.setRequestHeader('Content-Type', 'application/json')
      // send the request
      xhr.send()
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
