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