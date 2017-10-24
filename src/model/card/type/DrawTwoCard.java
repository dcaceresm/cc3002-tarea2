package model.card.type;

import controller.IController;
import model.IGameLogic;

public class DrawTwoCard extends Card {

	public DrawTwoCard(Color aColor, Symbol aSymbol) {
		super(aColor, aSymbol);
	}
	
	@Override
	public void executeAction(IGameLogic game, IController ctrl) {
		game.addToDrawWell(2);
	}

}
