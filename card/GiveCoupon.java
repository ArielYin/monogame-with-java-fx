package card;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import location.Location;
import location.Player;

public class GiveCoupon extends Location {

	public GiveCoupon(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		player.addCoupon(25);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Welcome " + name);
		alert.setHeaderText(null);
		alert.setContentText("Here are 25 coupons for you!");
		alert.showAndWait();
	}

}
