import java.util.Stack;
//import javax.swing.*;
//import java.awt.Color;
//import java.awt.Graphics;
import java.util.Random;

// Find the path of a maze use DFS
public class MazePath{
	
	private MazeGenerator mazeGenerator;
	private Maze maze;	
	public void findPathDFS(MazeGenerator mazeGenerator) {
		this.mazeGenerator = mazeGenerator;
		this.maze = mazeGenerator.getMaze();
		long start = System.nanoTime();
		Cell currentPosition = maze.maze[0][0];
		Stack<Cell> mazeCells = new Stack<Cell>();
		mazeCells.push(currentPosition);
		currentPosition.visit();
		Cell nextPosition = null;
		//find the Path use DFS
		while(!mazeCells.isEmpty()) {
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				
			}
			
			currentPosition = mazeCells.pop();
			nextPosition = this.randomChildSelectrion(currentPosition);

			//If there exist an unvisited child, push the current node and the child node into the Stack.
			//If there is no unvisited child, current node will be poped out of the Stack.
			if(nextPosition!=null) {
				if(nextPosition.x == maze.size - 1 && nextPosition.y == maze.size - 1) {
					//System.out.println("Found the Path");
					//System.out.println(nextPosition.x + " . " + nextPosition.y);
					break;
				}else {
					mazeGenerator.setPathCell(nextPosition);
					//System.out.println(nextPosition.x + " . " + nextPosition.y);
					mazeCells.push(currentPosition);
					mazeCells.push(nextPosition);
					nextPosition.visit();
				}
			}
		}
		long end = System.nanoTime();
		System.out.println(end-start);
	}
	
	private Cell randomChildSelectrion(Cell c) {
		Cell randomChild = null;		
		//Generate a random number for randomly child selection
		Random random = new Random();
		int randomSelectFactor = Math.abs(random.nextInt());
		//select a random child of current cell
		for(int i = 0 ; i < 4 ; i++) {
			switch(randomSelectFactor % 4) {
			case 0 : randomChild = (c.upCell == null) ? null : (c.upCell.getFlag() == true) ? null : c.upCell;
			break;
			case 1 : randomChild = (c.rightCell == null) ? null : (c.rightCell.getFlag() == true) ? null : c.rightCell;
			break;
			case 2 : randomChild = (c.downCell == null) ? null : (c.downCell.getFlag() == true) ? null : c.downCell;
			break;
			case 3 : randomChild = (c.leftCell == null) ? null : (c.leftCell.getFlag() == true) ? null : c.leftCell;
			break;
			}
			if (randomChild == null) {
				randomSelectFactor++;
			}else {
				return randomChild;
			}
		}//end of selection
		return randomChild;
	}
}
