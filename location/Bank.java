package location;

import java.util.Optional;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Bank extends Location {

	public Bank(String name, Point point) {
		super(name, point);
	}

	public void operation(Player player) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Welcome " + name);
		alert.setHeaderText(null);
		alert.setContentText("Please select operation, exit and select \"cancel\"");

		ButtonType buttonTypeOne = new ButtonType("Deposit");
		ButtonType buttonTypeTwo = new ButtonType("Withdrawal");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			saveMoney(player);
			operation(player);
		} else if (result.get() == buttonTypeTwo) {
			withdrawalMoney(player);
			operation(player);
		} else {
			Alert quit = new Alert(AlertType.INFORMATION);
			quit.setTitle("Message");
			quit.setHeaderText(null);
			quit.setContentText(name + ", We look forward to seeing you again!");
			quit.showAndWait();
		}
	}

	private void saveMoney(Player player) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Welcome " + name);
		dialog.setHeaderText(null);
		dialog.setContentText("Your cash is " + player.getCash() + "\nPlease enter the amount you want " +
				"to deposit(0-" + player.getCash() + "), The amount will be kept as two decimal places");

		Optional<String> result = dialog.showAndWait();
		String save1 = "";
		if (result.isPresent()) {
			save1 = result.get();
			try {
				double save = Double.valueOf(save1);
				save1 = String.format("%.2f", save);
				save = Double.valueOf(save1);
				if (save <= player.getCash() && save >= 0) {
					player.addDeposit(save);
					player.addCash(-save);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Deposit successfully");
					alert.setHeaderText(null);
					alert.setContentText("Your deposit from " + (player.getDeposit() - save) + " pounds increased to " + player.getDeposit() + " pounds.\n"
							+ "Your cash from " + (player.getCash() + save) + " pounds reduced to " + player.getCash() + " pounds.");
					alert.showAndWait();
				} else {
					Alert warn = new Alert(AlertType.WARNING);
					warn.setTitle("Error");
					warn.setHeaderText(null);
					warn.setContentText("Your input is incorrect, please retype it");
					warn.showAndWait();
					saveMoney(player);
				}
			} catch (Exception ex) {
				Alert warn = new Alert(AlertType.WARNING);
				warn.setTitle("Error");
				warn.setHeaderText(null);
				warn.setContentText("Your input is incorrect, please retype it");
				warn.showAndWait();
				saveMoney(player);
			}
		}

	}

	private void withdrawalMoney(Player player) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Welcome " + name);
		dialog.setHeaderText(null);
		dialog.setContentText(
				"Your deposit is " + player.getDeposit() + "\nPlease enter the amount you want" +
						" (0-" + player.getDeposit() + "),The amount will be kept as two decimal places");

		Optional<String> result = dialog.showAndWait();
		String withdrawal1 = "";
		if (result.isPresent()) {
			withdrawal1 = result.get();
			try {
				double withdrawal = Double.valueOf(withdrawal1);
				withdrawal1 = String.format("%.2f", withdrawal);
				withdrawal = Double.valueOf(withdrawal1);
				if (withdrawal <= player.getDeposit() && withdrawal >= 0) {
					player.addDeposit(-withdrawal);
					player.addCash(withdrawal);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Withdraw successfully");
					alert.setHeaderText(null);
					alert.setContentText("Your deposit from " + (player.getDeposit() + withdrawal) + " pounds reduced to " + player.getDeposit() + " pounds.\n"
							+ "Your cash from " + (player.getCash() - withdrawal) + " pounds increased to " + player.getCash() + " pounds.");
					alert.showAndWait();
				} else {
					Alert warn = new Alert(AlertType.WARNING);
					warn.setTitle("Error");
					warn.setHeaderText(null);
					warn.setContentText("Your input is incorrect, please retype it");
					warn.showAndWait();
					withdrawalMoney(player);
				}
			} catch (Exception ex) {
				Alert warn = new Alert(AlertType.WARNING);
				warn.setTitle("Error");
				warn.setHeaderText(null);
				warn.setContentText("Your input is incorrect, please retype it");
				warn.showAndWait();
				withdrawalMoney(player);
			}
		}
	}

}
