package location;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Hospital extends Location {

	public Hospital(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Welcome " + name);
		alert.setHeaderText(null);
		alert.setContentText("You have no disease");
		alert.showAndWait();
	}

}
