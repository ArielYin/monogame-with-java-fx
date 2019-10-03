package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import location.Player;

public class DiceCard extends Card {
	public DiceCard() {
	}

	public String getName() {
		return "Remote control dice";
	}

	public String getInformation() {
		return "control the next dice outcome.";
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		List<String> choices = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			choices.add(String.valueOf(i));
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(String.valueOf(1), choices);
		dialog.setTitle("Choice");
		dialog.setHeaderText(null);
		dialog.setContentText("How far do you want to go?");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String choice1 = result.get();
			int choice = Integer.parseInt(choice1);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("God bless you the next shake must be " + choice);
			alert.showAndWait();
			player.setStep(choice);
			player.setUseDice(true);
		}

		return true;
	}

}
