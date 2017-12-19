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
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__CharacterRepository__ = __webpack_require__(1);

var App = /** @class */ (function () {
    function App(repo) {
        this.repo = repo;
        this.init();
    }
    App.prototype.init = function () {
        this.characterDisplay = document.getElementById("character-display");
        this.nameBox = document.getElementById("by-name-name");
        this.historyDisplay = document.getElementById("history-list");
        this.setUpDaily();
        this.setUpRandom();
        this.setUpByName();
    };
    App.prototype.setUpDaily = function () {
        var _this = this;
        var dailyButton = document.getElementById("daily");
        dailyButton.addEventListener("click", function (e) {
            _this.repo.getDailyCharacter().then(function (character) {
                _this.setCharacterDisplay(character.toString(), "daily");
            });
        });
    };
    App.prototype.setUpRandom = function () {
        var _this = this;
        var randomButton = document.getElementById("random");
        randomButton.addEventListener("click", function (e) {
            _this.repo.getRandomCharacter().then(function (character) {
                _this.setCharacterDisplay(character.toString(), "random");
            });
        });
    };
    App.prototype.setUpByName = function () {
        var _this = this;
        var byNameButton = document.getElementById("by-name");
        byNameButton.addEventListener("click", function (e) {
            var name = _this.nameBox.value;
            _this.repo.getCharacterFromName(name).then(function (character) {
                _this.setCharacterDisplay(character.toString(), "byName");
            });
        });
    };
    App.prototype.setCharacterDisplay = function (value, caller) {
        this.characterDisplay.innerText = caller + ": " + value;
        this.appendToHistory(value);
    };
    App.prototype.appendToHistory = function (entry) {
        var li = document.createElement("li");
        li.innerText = entry;
        this.historyDisplay.insertAdjacentElement('afterbegin', li);
    };
    return App;
}());
new App(new __WEBPACK_IMPORTED_MODULE_0__CharacterRepository__["a" /* CharacterRepository */]("/"));


/***/ }),
/* 1 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CharacterRepository; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__Character__ = __webpack_require__(2);

var CharacterRepository = /** @class */ (function () {
    function CharacterRepository(baseUrl) {
        this.baseUrl = baseUrl;
    }
    CharacterRepository.prototype.getDailyCharacter = function () {
        return this.get(CharacterRepository.DAILY);
    };
    CharacterRepository.prototype.getRandomCharacter = function () {
        return this.get(CharacterRepository.RANDOM);
    };
    CharacterRepository.prototype.getCharacterFromName = function (name) {
        var normName = name.toLowerCase().trim();
        if (normName.length > 0) {
            return this.get(CharacterRepository.BY_NAME_BASE_URL + normName);
        }
    };
    CharacterRepository.prototype.get = function (url) {
        return fetch(this.baseUrl + url)
            .then(function (value) { return value.json(); })
            .then(function (rawChar) { return new __WEBPACK_IMPORTED_MODULE_0__Character__["a" /* Character */](rawChar); });
    };
    CharacterRepository.RANDOM = "character/";
    CharacterRepository.DAILY = "character/daily/";
    CharacterRepository.BY_NAME_BASE_URL = "character/name/";
    return CharacterRepository;
}());



/***/ }),
/* 2 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Character; });
var Character = /** @class */ (function () {
    function Character(rawCharacter) {
        this.name = rawCharacter.name;
        this.keyFeature = rawCharacter.keyFeature;
    }
    Character.prototype.toString = function () {
        return this.name + " with the " + this.keyFeature;
    };
    return Character;
}());



/***/ })
/******/ ]);