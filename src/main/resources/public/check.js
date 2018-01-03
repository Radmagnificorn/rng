(function (doc) {

    function checkIfWordExists(word, listName) {
        return fetch(listName + '/' + word + '/exists').then(response => {return response.text()})
    }

    function getRadioValue(radios) {
        for (let i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                return radios[i].value;
            }
        }
    }

    let checkInput = doc.getElementById("check-text");
    let resultDisplay = doc.getElementById("result");
    let listNames = document.getElementsByName('select-list');

    checkInput.addEventListener('keyup', e => {
        if (e.keyCode === 13) {
            checkIfWordExists(checkInput.value, getRadioValue(listNames)).then(data => {
                resultDisplay.innerText = data;
            });
        }
    });
})(document);