package model.card.deck;

import model.card.*;
import model.card.type.*;

public class NumericDeckStrategy implements IDeckStrategy {
	
	private static Color[] C = Color.getColors();
	private static Symbol[] S = Symbol.getNumeric();
	
	@Override
	public ICardPile createDeck() {
		ICardPile deck = new CardPile();
		for(int i = 0; i<2; i++) {
			for(Color c : C) {
				for(Symbol s: S) {
					deck.pushCard(new Card(c,s));
				}
			}		
		}
		return deck;
	}
}