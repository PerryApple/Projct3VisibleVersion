import java.util.Random;
import java.util.Stack;

public class Maze {
	public Cell[][] maze;
	public int size;
	public Maze(int n){
		//create a n*n maze
		maze = new Cell[n][n];
		this.size = n;
		for(int i = 0; i < n; i++ ) {
			for(int j = 0; j < n; j++) {
				maze[i][j] = new Cell(i,j);
			}
		}
	}
	
	public Cell getCell(int x, int y) {
		return maze[x][y];
	}
	
	public void generateMaze() {
		// Use DFS to traverse all cells and connect them into a maze
		Cell c = maze[0][0];
		Stack<Cell> stackCell = new Stack<Cell>();
		stackCell.push(c);//push the first cell to stack, the first cell is the start point.
		c.visit();
		while(!stackCell.isEmpty()) {
			c=stackCell.pop();
			Cell selectedNeighbor = getNeighbor(c);//get all the neighbor of cell c
			//Push the selected neighbor into stack
			if(selectedNeighbor != null) {
				stackCell.push(c);
				stackCell.push(selectedNeighbor);
				selectedNeighbor.visit(); //set flag to "visited"
			}
		}
		
		//clean all visiting flags
		for (Cell[] mazeRow : maze) {
			for (Cell cell : mazeRow) {
				cell.cleanFlag();
			}
		}
		
		maze[0][0].left = true;
		maze[size-1][size-1].right = true;
	}
	
	public Cell getNeighbor(Cell c) {
		Cell[] neighbor = new Cell[4];//0:up,1:right,2:down,3:left.
		Cell unvisitedNeighbor = null;
		//put all neighbors into an array
		neighbor[0] = (c.x==0) ? null : (maze[c.x-1][c.y].getFlag()==true) ? null : maze[c.x-1][c.y];
		neighbor[1] = (c.y==size-1) ? null : (maze[c.x][c.y+1].getFlag()==true) ? null : maze[c.x][c.y+1];
		neighbor[2] = (c.x==size-1) ? null : (maze[c.x+1][c.y].getFlag()==true) ? null : maze[c.x+1][c.y];
		neighbor[3] = (c.y==0) ? null: (maze[c.x][c.y-1].getFlag()==true) ? null : maze[c.x][c.y-1];
		
		//generate a random number to select a random neighbor
		Random random = new Random();
		int randomNeighborSelectFactor = Math.abs(random.nextInt()) % 4; 
		
		//select a random unvisited neighbor
		for(int i = 0 ; i < 4 ; i++) {
			if(neighbor[randomNeighborSelectFactor % 4]!=null) {
				unvisitedNeighbor = neighbor[randomNeighborSelectFactor % 4];
				break; //if meet an unvisited neighbor, end the loop.
			}
			randomNeighborSelectFactor++;
		}
		
		//Break the wall between two connected cells;
		//And set the selected unvisited neighbor to be c's child;
		
		if(neighbor[0]!=null&&randomNeighborSelectFactor % 4 == 0) {
			c.up = true;
			neighbor[0].down = true;
			c.upCell = neighbor[0];
		}else if(neighbor[1]!=null&&randomNeighborSelectFactor % 4 == 1) {
			c.right = true;
			neighbor[1].left = true;
			c.rightCell = neighbor[1];
		}else if(neighbor[2]!=null&&randomNeighborSelectFactor % 4 == 2) {
			c.down = true;
			neighbor[2].up = true;
			c.downCell = neighbor[2];
		}else if(neighbor[3]!=null&&randomNeighborSelectFactor % 4 == 3) {
			c.left = true;
			neighbor[3].right = true;
			c.leftCell = neighbor[3];
		}

		return unvisitedNeighbor;
	}
}
