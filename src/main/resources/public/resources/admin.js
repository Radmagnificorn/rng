/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 3);
/******/ })
/************************************************************************/
/******/ ({

/***/ 3:
/***/ (function(module, exports) {

(function (doc) {

    let wordInput = doc.getElementById('word');
    let listInput = doc.getElementById('list');
    let addButton = doc.getElementById('addWord');
    let removeButton = doc.getElementById('removeWord');
    let responseDisplay = doc.getElementById('responseDisplay');

    function sendWord(word, listName) {
        let wordData = {
            id: 0,
            word: word,
            type: listName
        };

        return fetch('/admin/lists', {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                'accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(wordData)
        }).then(response => {return response.text()});
    }
    
    function deleteWord(listName, word) {
        if (listName && word) {
            let deleteUrl = '/admin/lists/' + listName + "/" + word;
            return fetch(deleteUrl, {
                method: 'delete',
                credentials: 'same-origin',
                headers: {
                    'accept': 'application/json',
                    "Content-Type": 'application/json'
                }
            }).then(response => {
                return response.text()
            });
        } else {
            return Promise.resolve("please provide both a list and a word");
        }
    }
    
    function addWord() {
        sendWord(wordInput.value, listInput.value).then(resp => {
            responseDisplay.innerText = resp;
            wordInput.value = "";
        });
    }

    let keypressEvent = (e) => {
        if (e.code === 'Enter') {
            addWord();
        }
    };


    wordInput.addEventListener('keypress', keypressEvent);
    listInput.addEventListener('keypress', keypressEvent);
    addButton.addEventListener('click', (e) => addWord());
    removeButton.addEventListener('click', (e) => {
        deleteWord(listInput.value, wordInput.value).then(respText => {
            responseDisplay.innerText = respText;
        });
    });


})(document);

/***/ })

/******/ });