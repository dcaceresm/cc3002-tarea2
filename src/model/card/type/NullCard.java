package model.card.type;

import controller.IController;
import model.IGameLogic;

public class NullCard implements ICard {

	@Override
	public boolean isPlayableOver(ICard otherCard) {
		return false;
	}

	@Override
	public boolean isFirstPlayable() {
		return false;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public Symbol getSymbol() {
		return null;
	}

	@Override
	public void executeAction(IGameLogic game, IController ctrl) {}

	@Override
	public boolean isDiscardable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "NULL_CARD";
	}
	
}
