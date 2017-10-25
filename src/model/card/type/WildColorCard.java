package model.card.type;

import controller.IController;
import model.IGameLogic;

public class WildColorCard extends Card {

	public WildColorCard(Color aColor, Symbol aSymbol) {
		super(aColor, aSymbol);
	}
	
	@Override
	public boolean isPlayableOver(ICard otherCard) {
		return true;
	}
	@Override
	public void executeAction(IGameLogic game, IController ctrl) {
		game.getCurrentPlayer().selectColor(game, ctrl);
	}

}
