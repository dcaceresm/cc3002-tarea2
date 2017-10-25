package model.card.type;

import controller.IController;
import model.IGameLogic;

public class WildDrawCard extends Card {

	public WildDrawCard(Color aColor, Symbol aSymbol) {
		super(aColor, aSymbol);
	}
	
	@Override
	public boolean isPlayableOver(ICard otherCard) {
		return true;
	}
	
	@Override
	public void executeAction(IGameLogic game, IController ctrl) {
		game.addToDrawWell(4);
		game.getCurrentPlayer().selectColor(game, ctrl);
	}

}
