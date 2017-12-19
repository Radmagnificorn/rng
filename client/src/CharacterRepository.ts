import {Character, RawCharacter} from "./Character";

class CharacterRepository {
    baseUrl: string;
    static readonly RANDOM = "character/";
    static readonly DAILY = "character/daily/";
    static readonly BY_NAME_BASE_URL = "character/name/";

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    getDailyCharacter(): Promise<Character> {
        return this.get(CharacterRepository.DAILY);
    }

    getRandomCharacter(): Promise<Character> {
        return this.get(CharacterRepository.RANDOM);
    }

    getCharacterFromName(name: string): Promise<Character> {
        let normName = name.toLowerCase().trim();
        if (normName.length > 0) {
            return this.get(CharacterRepository.BY_NAME_BASE_URL + normName);
        }
    }

    private get(url: string): Promise<Character> {
        return fetch(this.baseUrl + url)
            .then(value => value.json())
            .then((rawChar: RawCharacter) => new Character(rawChar));
    }
}

export {CharacterRepository};