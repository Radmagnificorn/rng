import {Character, RawCharacter} from "./Character";

class CharacterRepository {
    baseUrl: String;

    constructor(baseUrl: String) {
        this.baseUrl = baseUrl;
    }

    static getHelloWorld(): String {
        return "Hello World"
    }

    getDailyCharacter(): Promise<RawCharacter> {
        return fetch(this.baseUrl + "character/daily").then((value => value.json()));
    }
}

export {CharacterRepository};