package location;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import application.Point;
import card.*;
import card.RoadblockCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class News extends Location {

    public News(String name, Point point) {
        super(name, point);
    }

    @Override
    public void operation(Player player) {
        // do nothing

    }

    public void operation(Player player, ArrayList<Player> players) {
        String con = "";

        int chance = (int) (Math.random() * 4);
        switch (chance) {
            case 0:
                con += "The bank pays a bonus on the deposits,\nso everyone gets 10% of the deposits";
                Consumer<Player> giveMoney = e -> e.addDeposit(e.getDeposit() * 0.1);
                players.forEach(giveMoney);
                break;
            case 1:
                con += "Actively paying taxes,\neveryone pays 10% of the property tax";
                Consumer<Player> giveMoney1 = e -> e.addDeposit(-e.getDeposit() * 0.1);
                players.forEach(giveMoney1);
                break;
            case 2:
                Card card = new BuyCard();
                String str = "Card-Master gave everyone a BuyCard.";
                int random = (int) (Math.random() * 3);
                switch (random) {
                    case 0:
                        break;
                    case 1:
                        card = new DiceCard();
                        str = "Card-Master gave everyone a DiceCard";
                        break;
                    case 2:
                        card = new RoadblockCard();
                        str = "Card-Master gave everyone a RoadblockCard";
                        break;
                }
                con += str;
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).addCard(card);
                }
                break;
            case 3:
                con += "Oh, no.\nForced into the hospital for two rounds, " +
                        "two rounds along the counterclockwise to continue the game";
                player.setHurt(true);
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Welcome " + name);
        alert.setHeaderText("Big news!!!");
        alert.setContentText(con);
        alert.showAndWait();
    }
}
