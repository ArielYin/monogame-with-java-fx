package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Pane;

// main
public class Monopoly extends Application {
	public static int playerNum = 0;

	@Override
	public void start(Stage primaryStage) {

		List<Integer> choices = new ArrayList<>();
		choices.add(2);
		choices.add(3);
		choices.add(4);

		ChoiceDialog<Integer> dialog = new ChoiceDialog<>(2, choices);
		dialog.setTitle("choice");
		dialog.setHeaderText(null);
		dialog.setContentText("Please select the number of players");

		Optional<Integer> result = dialog.showAndWait();
		if (result.isPresent()) {
			playerNum = result.get();
			try {
				primaryStage.setTitle("Monopoly");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
				Pane myPane = (Pane) fxmlLoader.load();
				Scene myScene = new Scene(myPane);
				primaryStage.setScene(myScene);
				primaryStage.setResizable(false);
				primaryStage.sizeToScene();
				primaryStage.show();
			} catch (IOException event) {
				event.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
