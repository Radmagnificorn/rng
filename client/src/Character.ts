interface RawCharacter {
    name: String;
    keyFeature: String;
}
class Character implements RawCharacter {
    name: String;
    keyFeature: String;
    constructor(rawCharacter: RawCharacter) {
        this.name = rawCharacter.name;
        this.keyFeature = rawCharacter.keyFeature;
    }

    toString(): string {
        return this.name.toUpperCase() + "\nknown for their " + this.keyFeature;
    }
}

export {Character, RawCharacter}