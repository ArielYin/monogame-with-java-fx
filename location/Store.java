package location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.Point;
import card.*;
import card.RoadblockCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

public class Store extends Location {

    public Store(String name, Point point) {
        super(name, point);
    }

    @Override
    public void operation(Player player) {
        Card[] buyCard = new Card[3];
        buyCard[0] = new RoadblockCard();
        buyCard[1] = new BuyCard();
        buyCard[2] = new DiceCard();

        if (player.getCoupon() < 5) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Welcome " + name);
            alert.setHeaderText(null);
            alert.setContentText("The balance of your coupon is " + player.getCoupon() + "!\n"
                    + "Each item needs 5 coupon, your coupon is insufficient, welcome again!");
            alert.showAndWait();
        } else {
            String info = "";
            List<String> choices = new ArrayList<>();
            for (Card c : buyCard) {
                choices.add(c.getName());
                info += c.getName() + ":" + c.getInformation() + "\n";
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(buyCard[0].getName(), choices);
            dialog.setTitle("Welcome " + name);
            dialog.setHeaderText("The balance of your coupon is " + player.getCoupon() + "!\n" + "Each item needs 5 coupon");
            dialog.setContentText(info + " Please select the items to be purchased:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String choice = result.get();
                for (Card c : buyCard) {
                    if (choice.equals(c.getName())) {
                        player.addCard(c);
                        player.addCoupon(-5);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Welcome " + name);
                        alert.setHeaderText(null);
                        alert.setContentText("Bought " + c.getName() + " successfully!" +
                                "The balance of your coupon is " + player.getCoupon());
                        alert.showAndWait();
                        break;
                    }
                }
                operation(player);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Welcome " + name);
                alert.setHeaderText(null);
                alert.setContentText(name + ", We are looking forward to seeing you again");
                alert.showAndWait();
            }

        }

    }

}
