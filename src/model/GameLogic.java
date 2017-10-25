package model;

import controller.IController;
import model.card.ICardPilesManager;
import model.card.type.Card;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerManager;
import model.player.type.IPlayer;

public class GameLogic implements IGameLogic {
	
	IPlayerManager PlayerManager;
	ICardPilesManager CardPilesManager;
	int DrawWell;
	ICard wildSavedColor;
	
	
	public GameLogic(IPlayerManager playerManager, ICardPilesManager cpManager) {
		DrawWell = 0;
		PlayerManager = playerManager;
		CardPilesManager = cpManager;
		wildSavedColor = new Card(Color.NONE,Symbol.NONE);
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
		PlayerManager.startTurn();
		if(!this.isDrawWellEmpty()) {
			this.drawCardsFromWell(PlayerManager.getCurrentPlayer(), ctrl);
			this.resetDrawWell();
			PlayerManager.startTurn();
		} /*else {
			boolean needs = PlayerManager.getCurrentPlayer().needsToDrawCard(CardPilesManager.getCurrentPlayedCard());
			
				
		}*/
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
		boolean isPlayable = playedCard.isPlayableOver(this.getCurrentPlayedCard()) || playedCard.isPlayableOver(wildSavedColor);
		if(isPlayable) {
		playedCard.executeAction(this, ctrl);
		CardPilesManager.discard(playedCard);
		if(playedCard.getColor() != Color.NONE)
			wildSavedColor = new Card(playedCard.getColor(),Symbol.NONE);
		}
		PlayerManager.getCurrentPlayer().getHand().remove(playedCard);
		return isPlayable;
	}

	@Override
	public ICard drawOneCard(IPlayer player) {
		ICard c = CardPilesManager.drawCard();
		player.getHand().add(c);
		return c;
		
	}

	@Override
	public void announceWinner(IController ctrl) {
		ctrl.showMessage("El jugador "+PlayerManager.getPlayers().indexOf((this.getCurrentPlayer()))+"ha Ganado!");
		
	}

	@Override
	public void changeColor(IController ctrl,Color c) {
		wildSavedColor = new Card(c,Symbol.NONE);
		ctrl.showMessage("Se ha cambiado el color a"+wildSavedColor.getColor().getName());
		
	}

}
