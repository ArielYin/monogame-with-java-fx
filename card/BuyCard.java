package card;

import java.util.ArrayList;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import location.Land;
import location.Location;
import location.Player;

public class BuyCard extends Card {

	public BuyCard() {

	}

	public String getName() {
		return "Purchase card";
	}

	public String getInformation() {
		return "purchase the land at current price";
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		boolean re = true;
		boolean hasLand = false;
		int num = -1;

		ArrayList<Location> loc = player.getPoint().getLocations();

		for (int i = 0; i < loc.size(); i++) {
			if (loc.get(i) instanceof Land) {
				hasLand = true;
				num = i;
			}
		}
		if (hasLand) {
			Land land = (Land) loc.get(num);
			if (land.getOwner() == player) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Use failed! The current house is yourself!");
				alert.showAndWait();
				re = false;
			} else {
				if (land.getPrice() > player.getCash()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Use failed! Cash is not enough to buy a home!");
					alert.showAndWait();
					re = false;
				} else {
					if (land.getOwner() == null) {
						land.setLevel(1);
						land.setOwner(player);
						player.addHouseNum(1);
						player.addLands(land);
						player.addCash(-land.getPrice());
					} else {
						land.getOwner().getLands().remove(land);
						land.getOwner().addHouseNum(-1);
						land.setOwner(player);
						player.addHouseNum(1);
						player.addCash(-land.getPrice());
						player.addLands(land);
					}
					land.changeIcon();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Use success, you have obtained " + land.getName());
					alert.showAndWait();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Current location is not a house, you can not buy it!");
			alert.showAndWait();
			re = false;
		}

		return re;
	}

}
