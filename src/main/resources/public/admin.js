(function (doc) {

    let wordInput = doc.getElementById('word');
    let listInput = doc.getElementById('list');
    let addButton = doc.getElementById('addWord');
    let responseDisplay = doc.getElementById('responseDisplay');

    function sendWord(word, listName) {
        let wordData = {
            id: 0,
            word: word,
            type: listName
        };

        return fetch('administer/lists', {
            method: 'post',
            headers: {
                'accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(wordData)
        }).then(response => {return response.text()})
    }
    
    function addWord() {
        sendWord(wordInput.value, listInput.value).then(resp => {
            responseDisplay.innerText = resp;
        });
    }

    let keypressEvent = (e) => {
        if (e.code === 'Enter') {
            addWord();
        }
    };


    wordInput.addEventListener('keypress', keypressEvent);
    listInput.addEventListener('keypress', keypressEvent);
    addButton.addEventListener('click', (e) => addWord())


})(document);