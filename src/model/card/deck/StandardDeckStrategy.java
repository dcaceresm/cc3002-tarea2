package model.card.deck;

import model.card.*;
import model.card.type.*;

public class StandardDeckStrategy implements IDeckStrategy {
	
	private static Color[] C = Color.getColors();
	private static Symbol[] S = Symbol.getNumeric();
	
	@Override
	public ICardPile createDeck() {
		ICardPile deck = new CardPile();
		for(int i = 0; i<2; i++) {
			for(Color c : C) {
				for(Symbol s: S) {
					if(s != Symbol.ZERO)
						deck.pushCard(new Card(c,s));
				}
				deck.pushCard(new SkipCard(c,Symbol.SKIP));
				deck.pushCard(new DrawTwoCard(c,Symbol.DRAW_TWO));
				deck.pushCard(new InvertCard(c,Symbol.INVERT));
				for(int j = 0; j<2; j++) {
					deck.pushCard(new WildColorCard(Color.NONE,Symbol.WILD));
					deck.pushCard(new WildDrawCard(Color.NONE,Symbol.WILD_DRAW_FOUR));
				}
			}
		}
		for(Color c : C) {
			deck.pushCard(new Card(c,Symbol.ZERO));
		}
		return deck;
	}

}
