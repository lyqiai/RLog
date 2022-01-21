import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import LogApp from './LogApp';

ReactDOM.render(
    <React.StrictMode>
        <LogApp/>
    </React.StrictMode>,
    document.getElementById('root')
);

console.log(process.env.REACT_APP_BASE_URL);

