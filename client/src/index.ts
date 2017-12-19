import {CharacterRepository} from "./CharacterRepository";
import {Character, RawCharacter} from "./Character";

class App {

    repo: CharacterRepository;

    characterDisplay: HTMLDivElement;
    nameBox: HTMLInputElement;

    constructor(repo: CharacterRepository) {
        this.repo = repo;
        this.init();
    }

    private init() {
        this.characterDisplay = <HTMLDivElement> document.getElementById("character-display");
        this.nameBox = <HTMLInputElement> document.getElementById("by-name-name");
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
                this.setCharacterDisplay(character.toString(), "byName");
            });
        });
    }

    private setCharacterDisplay(value: string, caller: string) {
        this.characterDisplay.innerText = caller + ": " + value;
    }
}

new App(new CharacterRepository("/"));