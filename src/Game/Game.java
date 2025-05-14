package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Cell Map[][] = null;
    private int My_Team_ID;
    private int Total_Turns = 0;
    private int Turn_Number = 0;
    private int My_score = 0;
    private int Rows = 0;
    private int Cols = 0;

    private HashMap<Integer, Player> players_hashmap = null;
    private HashMap<Integer, Balloon> balloons_hashmap = null;
    private ArrayList<Player> players_arr = null;
    private ArrayList<Balloon> balloons_arr = null;
    private ArrayList<Player> my_players_arr;
    private ArrayList<Player> opponent_players_arr;
    
    
    public Game() {
        players_hashmap = new HashMap<>();
        balloons_hashmap = new HashMap<>();
    }
    
    public ArrayList<Player> get_My_Players() {
        return my_players_arr;
    }
    
    public ArrayList<Player> get_Opponent_Players() {
        return opponent_players_arr;
    }
    
    public ArrayList<Player> get_All_Players() {
        return players_arr;
    }
    
    public ArrayList<Balloon> get_Balloons() {
        return balloons_arr;
    }

    public Cell[][] get_Map() {
        return Map;
    }

    public int get_My_Score() {
        return My_score;
    }

    public int get_Turn_Number() {
        return Turn_Number;
    }

    public int get_Total_Turns() {
        return Total_Turns;
    }

    public int get_My_Team_ID() {
        return My_Team_ID;
    }
    
    public int get_Opponent_Team_ID() {
        return 1 - My_Team_ID;
    }

    public int get_Number_of_Columns() {
        return Cols;
    }
    
    public int get_Number_of_Rows() {
        return Rows;
    }
    
    private void Update_Map(Scanner scn) throws Exception {
        int r, c;
        String line;
        char ch;
        r = scn.nextInt();
        c = scn.nextInt();
        scn.nextLine();
        this.Rows = r;
        this.Cols = c;
        if(Map == null || Map.length != Rows || Map[0] == null || Map[0].length != Cols) {
            Map = new Cell[r][c];
        }
        for(int i = 0; i < Rows; i++) {
            line = scn.nextLine();
            for(int j = 0; j < Cols; j++) {
                if(Map[i][j] == null) {
                    Map[i][j] = new Cell(i, j);
                }
                Map[i][j].Reset(i, j);
                ch = line.charAt(j);
                if(ch == '#') {
                    Map[i][j].set_Block();
                }
            }
        }
    }
    
    private void Update_Game_Info(Scanner scn) throws Exception {
        My_Team_ID = scn.nextInt();
        Turn_Number = scn.nextInt();
        Total_Turns = scn.nextInt();
        My_score = scn.nextInt();
        scn.nextLine();
    }
    
    private void Update_Players(Scanner scn) throws Exception {
        int r, c;
        int pc = scn.nextInt();
        scn.nextLine();
        String sex;
        int id, team;
        Player p;
        Reset_Players();
        for(int i = 0; i < pc; i++) {
            sex = scn.next();
            id = scn.nextInt();
            team = scn.nextInt();
            r = scn.nextInt();
            c = scn.nextInt();
            p = players_hashmap.get(id);
            if(p == null) {
                p = new Player();
                players_hashmap.put(id, p);
            }
            if(sex.equals("girl")) {
                p.Update(Map[r][c], Player.Gender.GIRL, id, team);
            } else {
                p.Update(Map[r][c], Player.Gender.BOY, id, team);
            }
            if(p.get_Team_ID() == My_Team_ID) {
                my_players_arr.add(p);
            } else {
                opponent_players_arr.add(p);
            }
            Map[r][c].set_Player_Inside(p);
            players_arr.add(p);
        }
    }
    
    private void Update_Balloons(Scanner scn) throws Exception {
        int bc = scn.nextInt();
        scn.nextLine();
        int size;
        int id;
        int r, c;
        Balloon b;
        Reset_Balloons();
        for(int i = 0; i < bc; i++) {
            id = scn.nextInt();
            size = scn.nextInt();
            r = scn.nextInt();
            c = scn.nextInt();
            b = balloons_hashmap.get(id);
            if(b == null) {
                b = new Balloon();
                balloons_hashmap.put(id, b);
            }
            b.Update(Map[r][c], size, id);
            Map[r][c].set_Balloon_Inside(b);
            balloons_arr.add(b);
        }
    }
    
    public void Update(String world_str) throws Exception {
        players_arr = new ArrayList<>();
        my_players_arr = new ArrayList<>();
        opponent_players_arr = new ArrayList<>();
        balloons_arr = new ArrayList<>();
        
        Scanner scn = new Scanner(world_str);
        Update_Game_Info(scn);
        Update_Map(scn);
        Update_Players(scn);
        Update_Balloons(scn);
    }
    
    private void Reset_Players() {
        for(Map.Entry<Integer, Player> en: players_hashmap.entrySet()) {
            en.getValue().Reset();
        }
    }
    
    private void Reset_Balloons() {
        for(Map.Entry<Integer, Balloon> en: balloons_hashmap.entrySet()) {
            en.getValue().Reset();
        }
    }
    
    public String Commands_to_String() {
        String res = "";
        for(Player p: get_My_Players()) {
            if(p.get_Command() != Command.NONE) {
                res = res + p.get_ID() + " " + p.get_Command().toString() + "\n";
            }
        }
        return res;
    }
    
}
