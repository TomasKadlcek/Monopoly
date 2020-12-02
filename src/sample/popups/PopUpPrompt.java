package sample.popups;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.service.Dice;
import sample.service.TopOfBoard;
import sample.objects.Player;
import sample.objects.Property;

import java.util.ArrayList;


public class PopUpPrompt extends TopOfBoard {

    // Pop up if players steps on unowned property tile
    public void unownedProperty(String title, Player player, Property property, String type, ArrayList<Player> allPlayers){
        Stage popUpWindow = setupPup(title);
        Label label = new Label();
        label.setText("This " + type + " is not owned by anyone. Do you wish to buy it??" +
                "\nThe price is " + property.getTilePrice() +
                property.toString());

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            player.ownedProperties.add(property);
            property.setOwner(player.getName());
            player.money = player.money - property.getTilePrice();
            popUpWindow.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            popUpWindow.close();
            Trade trade = new Trade();
            trade.auction(property, allPlayers);
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    // Method call if Manage Turn button pressed.
    public void manageTurn(Player player, ArrayList<Player> allPlayers, ArrayList<Property> allProperties){
        Bank bank = new Bank();
        Stage popUpWindow = setupPup("Manage Turn");
        Label label = new Label();
        label.setText("Manage your buildings, streets, cards and trade with other players.");

        Button buyProperties = new Button("Buy Properties from bank");
        buyProperties.setOnAction(e -> {
            popUpWindow.close();
            bank.buyProperties(player);
        });
        Button sellProperties = new Button("Sell Properties to bank");
        sellProperties.setOnAction(e -> {
            popUpWindow.close();
            bank.sellProperties(player);
        });
        Button getMortgage = new Button("Get Mortgage");
        getMortgage.setOnAction(e -> {
            popUpWindow.close();
            bank.mortgageStreet(player);
        });
        Button unMortgage = new Button("Unmortgage street");
        unMortgage.setOnAction(e -> {
            popUpWindow.close();
            bank.unMortgageStreet(player);
        });
        Button tradePlayer = new Button("Trade with other players");
        tradePlayer.setOnAction(e -> {
            Trade trade = new Trade();
            trade.trade(player, allPlayers);
            popUpWindow.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buyProperties, sellProperties, getMortgage, unMortgage, tradePlayer);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
        setText(player);
    }

    // Pop up to inform player they stepped on Utility
    public int utilityAction(int price){
        Stage popUpWindow = setupPup("Utility action");
        Label label = new Label();
        label.setText("The rent for this field is " + price + " times the amount on dice throw.");

        Dice dice = new Dice();
        int rand = dice.randomDice();
        int rand2 = dice.randomDice();
        int total = price*(rand + rand2);

        Label result = new Label();
        result.setText("Your throw is " + rand + "+" + rand2 + ". You have to pay " + total );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, result);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
        return total;
    }

    // Final winner Announcement
    public void winnerAnnouncement(ArrayList<Player> allPlayers){
        Stage popUpWindow = setupPup("Game finished");
        Label label = new Label();
        label.setText("Winner Announcement: ");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);

        int totalValue = 0;
        for (Player pla : allPlayers){
            totalValue += pla.money;
            for (Property prop : pla.ownedProperties){
                if (prop.isInMort()){
                    totalValue += (prop.getTilePrice()/2);
                }
                else{
                    totalValue += prop.getTilePrice();
                    totalValue += (prop.getNumOfHouses() * prop.getHousePrice());
                }
            }
            Label playerLabel = new Label();
            playerLabel.setText("The total value of " + pla.getName() + " is " + totalValue);
            layout.getChildren().addAll(playerLabel);
        }

        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    // Refactored duplicate code
    public Stage setupPup(String s) {
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle(s);
        popUpWindow.setMinWidth(500);
        popUpWindow.setMinHeight(400);
        return popUpWindow;
    }
}
