package model.card;

import java.util.ArrayList;

import model.card.deck.IDeckStrategy;
import model.card.type.Color;
import model.card.type.ICard;
import model.player.type.IPlayer;

public class CardPilesManager implements ICardPilesManager {
	
	ICardPile deck, discardPile;
	
	public CardPilesManager(IDeckStrategy deckStrategy) {
		
		deck = deckStrategy.createDeck();
		deck.shuffle();
		discardPile = new CardPile();
		this.discard(this.drawCard());
		while(this.getCurrentPlayedCard().getColor() == Color.NONE) {
			deck.pushCards(discardPile);
			deck.shuffle();
			this.discard(this.drawCard());	
		}
	}
	
	@Override
	public void rebuildDeck() {
		ICard c = deck.popCard();
		deck.pushCards(discardPile);
		deck.shuffle();
		discardPile.pushCard(c);
		
	}

	@Override
	public ICard drawCard() {
		return deck.popCard();
	}

	@Override
	public int getDrawableCardsNumber() {
		return deck.getSize()+discardPile.getSize()-1;
	}

	@Override
	public ArrayList<ICard> drawCards(int cardsNumber) {
		ArrayList<ICard> ret = new ArrayList<ICard>();
		for(int i = 0; i<cardsNumber; i++){
			if(deck.getSize() == 0)
				rebuildDeck();
			ret.add(deck.popCard());
		}
		return ret;
	}

	@Override
	public ICard getCurrentPlayedCard() {
		return discardPile.peekCard();
	}

	@Override
	public void discard(ICard newCard) {
		discardPile.pushCard(newCard);
		
	}

	@Override
	public ArrayList<ICard> addCardsToPlayer(IPlayer player, int number) {
		ArrayList<ICard> arr = this.drawCards(number);
		player.addToHand(arr);
		return arr;
	}

}
