import { Card } from './Card.js';

const deck = [];
const suits = ["hearts", "diamonds", "clubs", "spades"];
const values = ["ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"];

const drawCardBtn = document.getElementById("draw-card-btn");
const standCardBtn = document.getElementById("stand-card-btn");
const replayBtn = document.getElementById("replay-btn")

const playerCardsContainer = document.getElementById("player-cards");
const botCardsContainer = document.getElementById("bot-cards");

const botLabel = document.getElementById("bot-cards-label");
const playerLabel = document.getElementById("player-cards-label");


let drawnPlayerCardsSum = 0;
let drawnBotCardsSum = 0;

let botTurn = false;
let hideCard = false;

let tmpCardImgUrl = "";
let tmpCardScore = 0;

for (let suit of suits) {
    for (let value of values) {
        const filename = `${value}_of_${suit}.png`;
        const imageUrl = `card_pngs/${filename}`
        const card = new Card(suit, value, imageUrl);
        deck.push(card);
    }
}

initGame()

function initGame() {
    playPlayer();
    playPlayer();
    playBot();
    hideCard = true;
    playBot();
    hideCard = false;
}

function printDeckCards() {
    for (let card of deck) {
        console.log(`${card.value} of ${card.suit}`);
    }
}

function removeCardFromDeck(index) {
    deck.splice(index, 1);
}


function endGame(isPlayerWinner) {

    if (isPlayerWinner) {
        botLabel.textContent += `, Loser 🥈`;
        playerLabel.textContent += `, Winner 🏆`;
    } else {
        botLabel.textContent += `, Winner 🏆`;
        playerLabel.textContent += `, Loser 🥈`;
    }

    handleButtons("end");
}

function handleButtons(action) {
    if (action === "end") {
        replayBtn.style.display = "block";
        drawCardBtn.style.display = "none";
        standCardBtn.style.display = "none";
    } else {
        drawnPlayerCardsSum = 0;
        drawnBotCardsSum = 0;
        playerCardsContainer.innerHTML = "";
        botCardsContainer.innerHTML = "";
        drawCardBtn.style.display = "block";
        standCardBtn.style.display = "block";
        replayBtn.style.display = "none";
    }
}

function checkScore() {

    botLabel.textContent = `Dealer | ${drawnBotCardsSum}`;
    playerLabel.textContent = `You | ${drawnPlayerCardsSum}`;
    
    if (drawnPlayerCardsSum == 21 || drawnBotCardsSum > 21 || ((drawnPlayerCardsSum > drawnBotCardsSum) && botTurn)) {
        endGame(true);
    }
    else if (drawnPlayerCardsSum > 21 || ((drawnBotCardsSum >= drawnPlayerCardsSum) && botTurn)) {
        endGame(false);
    }

    
}

function drawCard(isPlayer) {
    if (deck.length === 0) {
        console.warn("No more cards in the deck.");
        return null;
    }

    const randomIndex = Math.floor(Math.random() * deck.length);
    const card = deck[randomIndex];

    removeCardFromDeck(randomIndex);

    if (isPlayer) {
        drawnPlayerCardsSum += card.getCardValue(drawnPlayerCardsSum);
        console.log(drawnPlayerCardsSum);
    } else {
        if (hideCard) {
            tmpCardScore = card.getCardValue(drawnBotCardsSum);
        }
        else {
            drawnBotCardsSum += card.getCardValue(drawnBotCardsSum);
        }
        console.log(drawnBotCardsSum);
    }

    return card;

}

function setCardFields(card) {
    const cardImage = document.createElement('img');
    cardImage.src = card.imageUrl;  
    cardImage.alt = `${card.value} of ${card.suit}`;
    cardImage.style.width = '2em';  

    return cardImage;
}

function displayCard(isPlayer, card) {
    const cardImage = setCardFields(card);  

    if (isPlayer) {
        playerCardsContainer.appendChild(cardImage);
    } else {
        if (hideCard) {
            tmpCardImgUrl = cardImage.src;
            cardImage.src = `card_pngs/card_back.png`
        }

        botCardsContainer.appendChild(cardImage);
    }

    return cardImage;
}

function playPlayer() {
    let card = drawCard(true);
    checkScore(displayCard(true, card));
}

function playBot() {
    let card = drawCard(false);
    checkScore(displayCard(false, card));
}

function flipCard() {
    const botCardImages = botCardsContainer.querySelectorAll('img');
    drawnBotCardsSum += tmpCardScore;

    if (botCardImages.length >= 2) {
        botCardImages[1].src = tmpCardImgUrl;
    }
}

replayBtn.onclick = function(){
    handleButtons("replay");
    botTurn = false;
    initGame();
}

drawCardBtn.onclick = function() {
   playPlayer();
};

standCardBtn.onclick = function() {
    botTurn = true;

    flipCard();

    while (drawnBotCardsSum < drawnPlayerCardsSum) {
        let card = drawCard(false);    
        botCardsContainer.appendChild(displayCard(false, card)); 
    }

    checkScore();
};
