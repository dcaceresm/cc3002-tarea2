package model;

import controller.IController;
import model.card.ICardPilesManager;
import model.card.type.ICard;
import model.player.IPlayerManager;
import model.player.type.IPlayer;

public class GameLogic implements IGameLogic {
	
	IPlayerManager PlayerManager;
	ICardPilesManager CardPilesManager;
	int DrawWell;
	
	public GameLogic(IPlayerManager playerManager, ICardPilesManager cpManager) {
		DrawWell = 0;
		PlayerManager = playerManager;
		CardPilesManager = cpManager;
	}
	
	@Override
	public boolean hasEnded() {
		return PlayerManager.getCurrentPlayer().hasWon();
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return PlayerManager.getCurrentPlayer();
	}

	@Override
	public ICard getCurrentPlayedCard() {
		return CardPilesManager.getCurrentPlayedCard();
	}

	@Override
	public void autoShoutUNO(IController ctrl) {
		if(PlayerManager.getCurrentPlayer().hasOneCard() && !PlayerManager.getCurrentPlayer().hasSaidUNO()) {
			PlayerManager.getCurrentPlayer().setSaidUNO(true);
			ctrl.showMessage("UNO!");
		}
	}

	@Override
	public void startTurn(IController ctrl) {
		this.autoShoutUNO(ctrl);
		ctrl.showMessage("La carta actual en la pila de descarte es: "+CardPilesManager.getCurrentPlayedCard());
		if(!this.isDrawWellEmpty()) {
			this.drawCardsFromWell(PlayerManager.getCurrentPlayer(), ctrl);
			this.resetDrawWell();
			PlayerManager.startTurn();
		} else {
			PlayerManager.startTurn();
		}
	}

	@Override
	public void skipPlayer() {
		PlayerManager.skipPlayer();
		
	}

	@Override
	public void addToDrawWell(int number) {
		this.DrawWell += number;
		
	}

	@Override
	public void resetDrawWell() {
		this.DrawWell = 0;
		
	}

	@Override
	public boolean isDrawWellEmpty() {
		return this.DrawWell == 0;
	}

	@Override
	public void drawCardsFromWell(IPlayer player, IController ctrl) {
		int i = this.DrawWell;
		CardPilesManager.addCardsToPlayer(player, DrawWell);
		ctrl.showMessage("Robaste "+i+"Cartas!");
		
	}

	@Override
	public void invertDirection() {
		PlayerManager.invertDirection();
		
	}

	@Override
	public boolean playCard(ICard playedCard, IController ctrl) {
		playedCard.executeAction(this, ctrl);
		return playedCard.isPlayableOver(this.getCurrentPlayedCard());
	}

	@Override
	public ICard drawOneCard(IPlayer player) {
		//State variables? 
		return CardPilesManager.drawCard();
	}

	@Override
	public void announceWinner(IController ctrl) {
		ctrl.showMessage("El jugador "+PlayerManager.getPlayers().indexOf((this.getCurrentPlayer()))+"ha Ganado!");
		
	}

}
