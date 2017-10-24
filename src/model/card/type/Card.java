package model.card.type;

import controller.IController;
import model.IGameLogic;

public class Card implements ICard {
	Color c;
	Symbol s;
	
	public Card(Color aColor, Symbol aSymbol) {
		this.c = aColor;
		this.s = aSymbol;
	}
	@Override
	public boolean isPlayableOver(ICard otherCard) {
		return this.c.toString().equals(otherCard.getColor().toString())
				|| this.s.toString().equals(otherCard.getSymbol().toString())
				|| this.c.toString().equals("Sin Color");
	}

	@Override
	public boolean isFirstPlayable() {
		return !(this.c.toString().equals("Sin Color"));
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public Symbol getSymbol() {
		return s;
	}

	@Override
	public void executeAction(IGameLogic game, IController ctrl) {
	}

	@Override
	public boolean isDiscardable() {
		return true;
	}
	@Override
	public String toString() {
		return "("+this.getSymbol().toString()+","+this.getColor().toString()+")";
	}

}
