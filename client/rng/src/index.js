import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';

fetch("/character").then(function (response) {
    response.then(function (data) {
        alert(data);
    })
});

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
