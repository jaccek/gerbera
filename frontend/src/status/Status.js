import React, { useState } from 'react';
import axios from 'axios';
import './Status.css'

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function Status(props) {

  return (
    <div className={ (props.status.toLowerCase() === "up" ? "Status-up" : "Status-down") + " Status" }>
        <p className="Status-service">{props.serviceName}</p>
        <p className="Status-environment">{props.environment}</p>
        <p className="Status-version">{props.version}</p>
    </div>
  );
}

export default Status;
