import React from 'react';
import logo from './logo.svg';
import './App.css';
import Status from './status/Status'

function App() {
  return (
    <div className="statuses">
        <Status serviceName="fcm-subscriber" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
        <Status serviceName="driver-tasks" />
    </div>
  );
}

export default App;
