import java.awt.*;
import javax.swing.*;

public class MazeGenerator extends JPanel{

	public int width ;
	private static final int padding = 20;
	private final int cellWidth;
	private Maze maze;
	private Cell pathCell;
	private MazePath path;
	private int mazeSize;
	
	public MazeGenerator(int n, int cellWidth) {
		maze = new Maze(n);
		maze.generateMaze();
		this.cellWidth = cellWidth;
		this.width = n*cellWidth;
		this.showMaze();
		this.path = new MazePath();
		this.mazeSize = maze.size;
	}
	
	//Draw the maze in a Swing interface
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(padding, padding, width+padding, padding);
		g.drawLine(padding, padding+cellWidth, padding, width+padding);
		for(int i = 0; i < mazeSize; i++) {
			for(int j = 0; j < mazeSize; j++) {
				if(maze.getCell(i, j).down==false) {
					g.drawLine(padding+j*cellWidth, padding + (i+1)*cellWidth, padding+(j+1)*cellWidth, padding+(i+1)*cellWidth);
				}
				if(maze.getCell(i, j).right==false) {
					g.drawLine(padding+(j+1)*cellWidth, padding + i*cellWidth , padding+(j+1)*cellWidth, padding+(i+1)*cellWidth);
				}
			}
		}
		
		//Draw start point
		g.setColor(Color.ORANGE);
		g.fillRect(padding, padding, cellWidth, cellWidth);
		//Draw end point
		g.setColor(Color.GREEN);
		g.fillRect(width+padding-cellWidth, width+padding-cellWidth, cellWidth, cellWidth);
	}
	
	//initiate a Window.
	public void showMaze() {
		JFrame jFrame = new JFrame("Maze");
		jFrame.setBounds(padding, padding , width+padding+padding, width+padding+padding+padding);
		jFrame.add(this);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillOval(pathCell.y*cellWidth+padding, pathCell.x*cellWidth+padding, cellWidth, cellWidth);
	}
	
	
	public Maze getMaze() {
		return maze;
	}
	public int getCellWidth() {
		return cellWidth;
	}
	public int getPadding() {
		return padding;
	}
	
	public void setPathCell(Cell path) {
		pathCell = path;
		repaint();
	}
}
