package card;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import location.Location;
import location.Player;

public class GiveCard extends Location {

	public GiveCard(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		String str = "";

		int random = (int) (Math.random() * 3);
		switch (random) {
		case 0:
			player.addCard(new BuyCard());
			str = "Card-Master gives you a BuyCard!";
			break;
		case 1:
			player.addCard(new DiceCard());
			str = "Card-Master gives you a DiceCard!";
			break;
		case 2:
			player.addCard(new RoadblockCard());
			str = "Card-Master gives you a RoadblockCard!";
			break;
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Welcome " + name);
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}

}
