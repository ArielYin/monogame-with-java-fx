package location;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Space extends Location {

	public Space(Point point) {
		super(" Space ", point);
	}

	@Override
	public void operation(Player player) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Welcome " + name);
		alert.setHeaderText(null);
		alert.setContentText("Came to the vacant lot");
		alert.showAndWait();
	}

}
