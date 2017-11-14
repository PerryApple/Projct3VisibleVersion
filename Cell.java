//import java.util.Random;

public class Cell {
	private boolean flag;//flag to indicate that has this cell been visited.
	public int x, y;
	//Create 4 walls for each cell.
	//This four parameters are used to tell Swing how to draw the Maze.
	public boolean up;
	public boolean right;
	public boolean down;
	public boolean left;
	
	//every Node may have four children. This four parameters are used to store the children of Cell C.
	public Cell upCell;
	public Cell rightCell;
	public Cell downCell;
	public Cell leftCell;
	
	Cell (int i, int j){
		//initial flag to unvisited and walls all exist.
		this.flag = false;
		this.up = false;
		this.right = false;
		this.down = false;
		this.left = false;
		this.x = i;
		this.y = j;
		this.upCell=null;
		this.rightCell=null;
		this.downCell=null;
		this.leftCell=null;
	}
	public boolean getFlag() {
		return flag;
	}
	public void visit() {
		this.flag = true;
	}
	public void cleanFlag() {
		this.flag = false;
	}
	
}
