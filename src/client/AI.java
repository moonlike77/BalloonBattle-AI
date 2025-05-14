package client;

import Game.Game;

public abstract class AI {
    
    public abstract String get_Team_Name();
    public abstract void think(Game game);

}
