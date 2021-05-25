/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\r\nvar __importDefault = (this && this.__importDefault) || function (mod) {\r\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\r\n};\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nvar electron_1 = __webpack_require__(/*! electron */ \"electron\");\r\nvar path_1 = __importDefault(__webpack_require__(/*! path */ \"path\"));\r\nvar win = null;\r\nvar createWindow = function () {\r\n    win = new electron_1.BrowserWindow({\r\n        width: 800,\r\n        height: 600,\r\n        webPreferences: {\r\n            nodeIntegration: true,\r\n            worldSafeExecuteJavaScript: true,\r\n            plugins: true,\r\n        },\r\n    });\r\n    win.loadFile(\"index.html\");\r\n};\r\nelectron_1.app.on(\"ready\", createWindow);\r\nelectron_1.app.whenReady().then(function () {\r\n    electron_1.protocol.registerFileProtocol('rapidtunes', function (request, callback) {\r\n        var code = request.url.split('?code=')[1];\r\n        var url = path_1.default.normalize(__dirname + \"/index.html\");\r\n        win === null || win === void 0 ? void 0 : win.loadFile(url, { query: { \"spotify_code\": code } });\r\n    });\r\n});\r\n\n\n//# sourceURL=webpack://RapidTunes/./src/main.ts?");

/***/ }),

/***/ "electron":
/*!***************************!*\
  !*** external "electron" ***!
  \***************************/
/***/ ((module) => {

module.exports = require("electron");;

/***/ }),

/***/ "path":
/*!***********************!*\
  !*** external "path" ***!
  \***********************/
/***/ ((module) => {

module.exports = require("path");;

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		if(__webpack_module_cache__[moduleId]) {
/******/ 			return __webpack_module_cache__[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module is referenced by other modules so it can't be inlined
/******/ 	var __webpack_exports__ = __webpack_require__("./src/main.ts");
/******/ 	
/******/ })()
;