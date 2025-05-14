package client.Team;

import Game.Player;
import Game.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static sun.audio.AudioPlayer.player;

public class Team_AI extends client.AI {
    
    ArrayList<Integer> goals = new ArrayList<Integer>();

    @Override
    public String get_Team_Name() {
        return "(: *best friends* :)";
    }
    
    private Command BFS(Cell[][] map, Cell from_cell, boolean isBoy) 
    // from_cell mabadae! magsad ra isBoy tarif kardim.
    {
        Queue<Cell> que = new LinkedList();
        int delta_ij[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        // false dar in barname b mani ok budan makan ast! yani mitune bazikon b un khune bere
        if (false || map == null
                || map[0] == null //                 || (true && from_cell.get_Row() == to_cell.get_Row()
                //                          && from_cell.get_Col() == to_cell.get_Col())
                ) {
            return Command.NONE;
        }
        from_cell = map[from_cell.get_Row()][from_cell.get_Col()];    // exception?!
        Cell to_cell = null; //		= map[to_cell.get_Row()][to_cell.get_Col()];        // exception?!
        // megdare avalieye magsad ya to_cell ra null tarif kardim!
        int height = map.length;
        int width = map[0].length;
        int i, j;
        Cell from[][] = new Cell[height][width];
        Cell q_cell = from_cell;
        // q_cell parametri ast k khaneye mabda ra dar khod negah midarad!
        from[q_cell.get_Row()][q_cell.get_Col()] = from_cell;
        // dar in khat satr o sotun map gerefte shode dar mabda zakhire mishavad!
        que.add(q_cell); // hala an nogte ra k shenasaie karde b list bfs ezafe mikone!
        while (que.isEmpty() == false)
            // koll in dastur b hamrahe sharthayash b in manas k tazamani k khane ma khali has uno b onvane magsad peak (entekhab-gozinesh)kone
        {
            if (que.peek().equals(to_cell)) 
            {
                break;
            }
            q_cell = que.poll();
            //chon q_cell darhale taghier ast ba to_cell k magsad ast tafavot darad . hardo magsad and vali yki sabet digary moteghaier!!
            if (q_cell.get_Balloon_Inside() != null)// agar magsade moteghaier yani q_cell por bud
            {
                if ( !goals.contains(q_cell.get_Balloon_Inside().get_ID())//agar hadaf goal shamele ID badkonak bud
                        && ((!isBoy && q_cell.get_Balloon_Inside().get_Size() > 4 )|| // va agar pesar nabud va size badkonak bozorgtar az4 bud ya  
                       (isBoy && q_cell.get_Balloon_Inside().get_Size() < 7)))//pesar bud va va size badkonak hadaf kuchektar az7 bud 
                
                {
                    to_cell = q_cell;// magsad moteghaier tabdil shavad b magsade sabet o nahayee!
                    goals.add(q_cell.get_Balloon_Inside().get_ID());// in khane jadid ra b list ahdaf ezafe kon
                    break;
                }
            }
            for (int d = 0; d < 4; d++) 
            {
                i = q_cell.get_Row() + delta_ij[d][0];
                j = q_cell.get_Col() + delta_ij[d][1];
                if (false || ((i >= 0 && i < height && j >= 0 && j < width) == false)
                        || map[i][j].is_Block()
                        || from[i][j] != null) {
                    continue;
                }
                que.add(map[i][j]);
                from[i][j] = q_cell;
            }
        }
        Cell next_cell = to_cell;
        Cell temp;
        while (next_cell != null && (temp = from[next_cell.get_Row()][next_cell.get_Col()]) != null
                && (temp.equals(from_cell) == false)) {
            next_cell = temp;
        }
        if(next_cell != null && next_cell.get_Balloon_Inside() != null)
            if(! isBoy)
                goals.add(next_cell.get_Balloon_Inside().get_ID());
        if (next_cell != null) {
            int di = from_cell.get_Row() - next_cell.get_Row();
            int dj = from_cell.get_Col() - next_cell.get_Col();
            switch (2 * di + 3 * dj) {
                case +2:
                    return Command.UP;
                case -3:
                    return Command.RIGHT;
                case -2:
                    return Command.DOWN;
                case +3:
                    return Command.LEFT;
                default:
                    return Command.NONE;
            }
        } else {
            Cell hadafe_fari;

            hadafe_fari = map[0][0];

            return BFS2(map, from_cell, hadafe_fari);
        }
    }

    private Command BFS2(Cell[][] map, Cell from_cell, Cell to_cell) {
        Queue<Cell> que = new LinkedList();
        int delta_ij[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        if (false || map == null
                || map[0] == null
                || (true && from_cell.get_Row() == to_cell.get_Row()
                && from_cell.get_Col() == to_cell.get_Col())) {
            return Command.NONE;
        }
        from_cell = map[from_cell.get_Row()][from_cell.get_Col()];  // exception?!
        to_cell = map[to_cell.get_Row()][to_cell.get_Col()];        // exception?!
        int height = map.length;
        int width = map[0].length;
        int i, j;
        Cell from[][] = new Cell[height][width];
        Cell q_cell = from_cell;
        from[q_cell.get_Row()][q_cell.get_Col()] = from_cell;
        que.add(q_cell);
        while (que.isEmpty() == false) {
            if (que.peek().equals(to_cell)) {
                break;
            }
            q_cell = que.poll();
            for (int d = 0; d < 4; d++) {
                i = q_cell.get_Row() + delta_ij[d][0];
                j = q_cell.get_Col() + delta_ij[d][1];
                if (false || ((i >= 0 && i < height && j >= 0 && j < width) == false)
                        || map[i][j].is_Block()
                        || from[i][j] != null) {
                    continue;
                }
                que.add(map[i][j]);
                from[i][j] = q_cell;
            }
        }
        Cell next_cell = to_cell;
        Cell temp;
        while (true && (temp = from[next_cell.get_Row()][next_cell.get_Col()]) != null
                && (temp.equals(from_cell) == false)) {
            next_cell = temp;
        }
        int di = from_cell.get_Row() - next_cell.get_Row();
        int dj = from_cell.get_Col() - next_cell.get_Col();
        switch (2 * di + 3 * dj) {
            case +2:
                return Command.UP;
            case -3:
                return Command.RIGHT;
            case -2:
                return Command.DOWN;
            case +3:
                return Command.LEFT;
            default:
                return Command.NONE;
        }
    }

    @Override
    public void think(Game game) {
        goals.clear();
        System.out.println("turn: " + game.get_Turn_Number());
        Cell map[][] = game.get_Map();
        //  Player p = game.get_My_Players().get(0);
        for (Player p : game.get_My_Players()) {
            Cell destination_cell = p.get_Cell();
          //  if (game.get_Balloons().size() > 0) {
            //    destination_cell = game.get_Balloons().get(0).get_Cell();
            //  }
            Command dir = BFS(map, p.get_Cell(), p.is_Boy());
            if (dir == Command.DOWN) {
                p.move_Down();
            } else if (dir == Command.UP) {
                p.move_Up();
            } else if (dir == Command.LEFT) {
                p.move_Left();
            } else if (dir == Command.RIGHT) {
                p.move_Right();
            }
            Cell cell;
            //  int r = game.get_Number_of_Rows();
            //int c = game.get_Number_of_Columns();
            ArrayList<Player> my_players;
            my_players = game.get_My_Players();
     //   for(Player p: game.get_My_Players())
            //  {

            cell = p.get_Cell();
            if (p.is_Boy()) {
                if (p.get_Cell().get_Balloon_Inside() != null && p.get_Cell().get_Balloon_Inside().get_Size() < 7) {
                    p.pooff();
                    continue;
                }
            }
            if (p.is_Gril()) {
                if (p.get_Cell().get_Balloon_Inside() != null &&p.get_Cell().get_Balloon_Inside().get_Size() > 4 ) {
                        p.pop();
                    //    continue;
                    }
                }
            }

            //   switch((int) (Math.random() * 4)) {
//                case 0: p.move_Down(); break;
//                case 1: p.move_Up(); break;
//                case 2: p.move_Left(); break;
//                case 3: p.move_Right(); break;    
        }
    }

//             
//                        if(map[i][j].get_Player_Inside().get_Team_ID()==game.get_My_Team_ID())
//                         System.out.print("p");
//                }else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//        for(Player p: my_players) 
//        {
//            Cell cell;
//            cell=p.get_Cell();
//            p.move_Left();
//           // System.out.println(cell.get_Row()+","+ cell.get_Col());
//        }

