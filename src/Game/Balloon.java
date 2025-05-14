package Game;

public class Balloon {
    
    private int id = -1;
    private int size = 0;
    private Cell cell = null;

    public Balloon() {
        Reset();
    }
    
    public int get_ID() {
        return id;
    }
    
    public int get_Size() {
        return size;
    }
    
    public Cell get_Cell() {
        return cell;
    }
    
    protected void Reset() {
        this.cell = null;
        this.size = -1;
        this.id = -1;
    }
    
    protected void Update(Cell cell, int size, int id) {
        this.cell = cell;
        this.size = size;
        this.id = id;
    }
    
}
