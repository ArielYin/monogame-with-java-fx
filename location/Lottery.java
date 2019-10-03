package location;

import java.util.Optional;

import application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class Lottery extends Location {

    public Lottery(String name, Point point) {
        super(name, point);
    }

    @Override
    public void operation(Player player) {
        int money = 1000;
        String header = "";
        header = "One note lottery fixed price 10 pounds, winning amount is " + money + " pounds\n";
        header += "The following tickets are available for purchase today. Each ticket number is as follows:\n";
        String[] lotArray = new String[10];
        for (int i = 0; i < 10; i++) {
            String lotNum = String.valueOf((int) (Math.random() * 10000) + 1)
                    + String.valueOf((int) (Math.random() * 10)) + String.valueOf((int) (Math.random() * 10000));
            lotArray[i] = lotNum;
            header += "Ticket no." + i + lotNum + "\n";
        }

        int win = (int) (Math.random() * 10);

        core(header, win, money, lotArray, player);
    }

    private void core(String header, int win, int money, String[] lotArray, Player player) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome " + name);
        dialog.setHeaderText(header);
        dialog.setContentText("Please select which number to buy and enter (0-9). If you do not buy, please exit");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            try {
                int chose = Integer.parseInt(choice);
                if (chose >= 0 && chose <= 9) {
                    player.addCash(-10);
                    if (chose == win) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Congratulations on winning the lottery, you got " + money + " pounds!");
                        alert.showAndWait();
                        player.addCash(money);
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Message");
                        alert.setHeaderText(null);
                        alert.setContentText("The winning number is " + lotArray[win]
                                + "\nI'm sorry that you didn't win the prize. Thank you for your participation!");
                        alert.showAndWait();
                    }
                } else {
                    Alert warn = new Alert(AlertType.WARNING);
                    warn.setTitle("Error");
                    warn.setHeaderText(null);
                    warn.setContentText("Your input is incorrect, please retype it");
                    warn.showAndWait();
                    core(header, win, money, lotArray, player);
                }
            } catch (Exception ex) {
                Alert warn = new Alert(AlertType.WARNING);
                warn.setTitle("Error");
                warn.setHeaderText(null);
                warn.setContentText("Your input is incorrect, please retype it");
                warn.showAndWait();
                core(header, win, money, lotArray, player);
            }
        }
    }

}
