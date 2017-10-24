package model.player;

import java.util.ArrayList;

import model.card.ICardPilesManager;
import model.player.type.IPlayer;

public class PlayerManager implements IPlayerManager {
	ArrayList<IPlayer> jugadores; 
	Direction d;
	int numPlayers, actualPlayer, nextPlayer;
	
	public PlayerManager(IPlayerListBuilder playerBuilder, ICardPilesManager cpManager) {
		jugadores = playerBuilder.buildPlayerList();
		d = Direction.CLOCKWISE;
		numPlayers = jugadores.size();
		actualPlayer = 0;
		nextPlayer = 1;
		for(IPlayer j : jugadores) {
			cpManager.addCardsToPlayer(j, 7);
		}
	}
	
	@Override
	public IPlayer getCurrentPlayer() {
		return jugadores.get(actualPlayer);
	}

	@Override
	public ArrayList<IPlayer> getPlayers() {
		return this.jugadores;
	}

	@Override
	public void invertDirection() {
		if (d.getValue()==-1)
			d = Direction.COUNTERCLOCKWISE;
		else {
			d = Direction.CLOCKWISE;
		}
			
	}

	@Override
	public void startTurn() {
		this.actualPlayer = this.nextPlayer;
		this.nextPlayer = (d.getValue()+this.actualPlayer)%numPlayers;
		
	}

	@Override
	public void skipPlayer() {
		nextPlayer = (nextPlayer+d.getValue())%numPlayers;
		
	}

}
