package client;

import Game.Game;
import client.Team.Team_AI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    
    private AI team_ai;
    private Network network;
    private Game game;
    
    private void set_Network_Listener() {
        network.set_Net_Listener(new Network.Net_Listener() {
            
            @Override
            public void on_Rec(String msg) {
                try {
                    game.Update(msg);
                    team_ai.think(game);
                    network.send(game.Commands_to_String());
                } catch (Exception e) {
                    //network.Close();
                    logger.log(Level.SEVERE, null, e);
                }
            }
            
        });
    }
    
    private Client() {
        try {
            network = new Network();
            network.Connect_to(Constants.Config.Net.ip, Constants.Config.Net.port);
            team_ai = new Team_AI();
            game = new Game();
            set_Network_Listener();
            network.send("TEAM: " + team_ai.get_Team_Name());
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Constants.Project.to_String());
        System.out.println();
        new Client();
    }
    
}
