import {CharacterRepository} from "./CharacterRepository";
import {Character, RawCharacter} from "./Character";

class App {

    repo: CharacterRepository;

    characterDisplay: HTMLDivElement;
    nameBox: HTMLInputElement;
    historyDisplay: HTMLUListElement;

    constructor(repo: CharacterRepository) {
        this.repo = repo;
        this.init();
    }

    private init() {
        this.characterDisplay = <HTMLDivElement> document.getElementById("character-display");
        this.nameBox = <HTMLInputElement> document.getElementById("by-name-name");
        this.historyDisplay = <HTMLUListElement> document.getElementById("history-list");
        this.setUpDaily();
        this.setUpRandom();
        this.setUpByName();
    }

    private setUpDaily() {
        let dailyButton = document.getElementById("daily");
        dailyButton.addEventListener("click", (e: Event) => {
            this.repo.getDailyCharacter().then((character: Character) => {
                this.setCharacterDisplay(character.toString(), "daily");
            });
        });
    }

    private setUpRandom() {
        let randomButton = document.getElementById("random");
        randomButton.addEventListener("click", (e: Event) => {
            this.repo.getRandomCharacter().then((character: Character) => {
                this.setCharacterDisplay(character.toString(), "random");
            });
        });
    }

    private setUpByName() {
        let byNameButton = document.getElementById("by-name");
        byNameButton.addEventListener("click", (e: Event) => {
            let name = this.nameBox.value;
            this.repo.getCharacterFromName(name).then((character: Character) => {
                this.setCharacterDisplay(character.toString(), name);
            });
        });
    }

    private setCharacterDisplay(value: string, caller: string) {
        this.characterDisplay.innerText = value;
        this.appendToHistory(caller + ": " + value);
    }

    private appendToHistory(entry: string) {
        let li = <HTMLLIElement> document.createElement("li");
        li.innerText = entry;
        this.historyDisplay.insertAdjacentElement('afterbegin', li);
    }
}

new App(new CharacterRepository("/"));