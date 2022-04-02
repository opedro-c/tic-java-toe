import game.GameManager;

public class Main {

	public static void main(String[] args) {
		GameManager game = new GameManager();

		while (!game.gameIsOver()) {
			game.processEvents();
			game.updateGameState();
			game.render();
		}
	}
}
