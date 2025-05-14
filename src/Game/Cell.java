package Game;

public class Cell {
    
    private int Row;
    private int Col;
    
    private Balloon balloon_inside = null;
    private Player player_inside = null;
    
    private boolean is_Block = false;
    
    public Cell(int Row, int Col) {
        Reset(Row, Col);
    }

    public int get_Row() {
        return Row;
    }

    public int get_Col() {
        return Col;
    }

    public void set_Balloon_Inside(Balloon b) {
        this.balloon_inside = b;
    }
    
    public Balloon get_Balloon_Inside() {
        return balloon_inside;
    }
    
    public boolean is_Block() {
        return is_Block;
    }
    
    protected void Reset(int row, int col) {
        this.balloon_inside = null;
        this.player_inside = null;
        this.is_Block = false;
        this.Row = row;
        this.Col = col;
    }

    protected void set_Block() {
        is_Block = true;
    }
        
    protected void set_Player_Inside(Player p) {
        this.player_inside = p;
    }

    public Player get_Player_Inside() {
        return player_inside;
    }

    @Override
    public String toString() {
        return "Cell("  + Row + ", " + Col + ")";
    }
    
}
