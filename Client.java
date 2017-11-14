
public class Client {
	
	
	public static void main(String[] args) {
		System.out.println("maze");
		MazeGenerator maze = new MazeGenerator(80,8);
		MazePath path = new MazePath();
		path.findPathDFS(maze);
	}
}
