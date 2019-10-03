package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import location.Barricade;
import location.Direction;
import location.Player;

public class RoadblockCard extends Card {

	public RoadblockCard() {
	}

	public String getInformation() {
		return "put a block which will stop player";
	}

	public String getName() {
		return "RoadblockCard";
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		List<String> choices = new ArrayList<>();
		for (int i = -8; i < 9; i++) {
			choices.add(String.valueOf(i));
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(String.valueOf(1), choices);
		dialog.setTitle("Choice");
		dialog.setHeaderText(null);
		dialog.setContentText("Where do you want to place the roadblock");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			int choice = Integer.parseInt(result.get());
			Point cell = player.getPoint().getPointAt(points, player.getPoint(), player.getDirection(), choice);
			if (choice < 0) {
				cell = player.getPoint().getPointAt(points, player.getPoint(),
						Direction.negative(player.getDirection()), -choice);
			}
			Barricade ba = new Barricade("", cell);
			cell.addLocation(ba);
			cell.setIcon();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("RoadblockCard added successfully!");

		}
		return true;
	}

}
