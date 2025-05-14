package Game;

public class Player {
    
    public enum Gender {
        BOY,
        GIRL
    }
    
    private Command CMD = Command.NONE;
    private Integer Player_id = -1;
    private Gender gender;
    private int Team_ID = 0;
    private Cell cell;
    
    public void move_Up() {
        CMD = Command.UP;
    }
    
    public void move_Right() {
        CMD = Command.RIGHT;
    }

    public void move_Down() {
        CMD = Command.DOWN;
    }
    
    public void move_Left() {
        CMD = Command.LEFT;
    }
    
    public void pop() {
        CMD = Command.POP;
    }
    
    public void pooff() {
        CMD = Command.POOFF;
    }

    public Player() {
        Reset();
    }

    public Gender get_Gender() {
        return gender;
    }

    public int get_Team_ID() {
        return Team_ID;
    }

    public int get_ID() {
        return Player_id;
    }
    
    public Cell get_Cell() {
        return cell;
    }
    
    public Command get_Command() {
        return CMD;
    }
    
    public boolean is_Boy() {
        return gender == Gender.BOY;
    }

    public boolean is_Gril() {
        return gender == Gender.GIRL;
    }
    
    protected void Reset() {
        this.Team_ID = -1;
        this.cell = null;
        this.Player_id = -1;
        this.CMD = Command.NONE;
    }
    
    protected void Update(Cell cell, Gender gender, int id, int team) {
       this.cell = cell;
       this.gender = gender;
       this.Player_id = id;
       this.Team_ID = team;
       
    }
    
}
