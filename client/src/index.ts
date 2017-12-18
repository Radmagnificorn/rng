import {CharacterRepository} from "./CharacterRepository";
import {Character, RawCharacter} from "./Character";

class App {

    repo: CharacterRepository;

    dailyDisplay: HTMLDivElement;

    constructor(repo: CharacterRepository) {
        this.repo = repo;
        this.init();
    }

    private init() {
        let dailyDisplay = document.getElementById("dailyCharacter");
        this.repo.getDailyCharacter().then(data => {
            dailyDisplay.innerText = new Character(data).toString();
        });
    }
}

new App(new CharacterRepository("/"));