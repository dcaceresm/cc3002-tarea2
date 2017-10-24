package model.card;

import java.util.Collections;
import java.util.Stack;

import model.card.type.ICard;

public class CardPile implements ICardPile {
	
	Stack<ICard> cardStack;
	
	public CardPile() {
		cardStack = new Stack<ICard>();
	}
	@Override
	public int getSize() {
		return cardStack.size();
	}

	@Override
	public ICard pushCard(ICard newCard) {
		return cardStack.push(newCard);
	}

	@Override
	public ICard popCard() {
		return cardStack.pop();
	}

	@Override
	public ICard peekCard() {
		return cardStack.peek();
	}

	@Override
	public void shuffle() {
		Collections.shuffle(cardStack);
		
	}

	@Override
	public boolean isEmpty() {
		return cardStack.isEmpty();
	}

	@Override
	public void pushCards(ICardPile otherPile) {
		while(! otherPile.isEmpty()) {
			this.pushCard(otherPile.popCard());
		}		
	}

}
