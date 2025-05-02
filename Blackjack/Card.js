export class Card {
    constructor(suit, value, imageUrl) {
        this.suit = suit;
        this.value = value;
        this.imageUrl = imageUrl;
    }

    getCardValue(score) {
        if (this.value === "ace") {
            return 11;
        }
        if (this.value === "jack" || this.value === "queen" || this.value === "king") {
            return 10;
        }

        return parseInt(this.value)
    }
}
