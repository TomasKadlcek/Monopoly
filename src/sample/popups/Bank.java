package sample.popups;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.objects.Player;
import sample.objects.Property;
import sample.popups.PopUpPrompt;

import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Bank extends PopUpPrompt {

    // All Functions and popup windows for trading with bank

    // method for buying properties on streets you own...
    public void buyProperties(Player player) {
        Stage popUpWindow = setupPup("Buy properties from bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("You can buy properties on these streets: ");

        GridPane gridPane = new GridPane();

        Map<String, Long> counts = player.ownedProperties.stream()
                .collect(groupingBy(Property::getGroup, counting()));

        int counter = 0;
        for (int i = 0; i < player.ownedProperties.size(); i++) {
            for (String key : counts.keySet()) {
                if (player.ownedProperties.get(i).getGroup().equals(key)) {
                    if (player.ownedProperties.get(i).getGroupSize() == counts.get(key)) {
                        Property property = player.ownedProperties.get(i);
                        if (!property.isInMort()) {
                            Button button = new Button(property.getName());
                            button.setOnAction(e -> {
                                if (property.getNumOfHouses() < 5) {
                                    popUpWindow.close();
                                    buyButtonLogic(player, property);
                                } else {
                                    button.setDisable(true);
                                }
                            });
                            String message = property.getNumOfHouses() + " houses on this street. Upgrade cost: "
                                    + property.getHousePrice();
                            Text text = new Text(message);
                            gridPane.add(button, 0, counter);
                            gridPane.add(text, 1, counter);
                            counter += 1;
                        }
                    }
                }
            }
        }
        pupFinish(popUpWindow, border, label, gridPane);
    }

    // Selling properties back to the bank...
    public void sellProperties(Player player) {
        Stage popUpWindow = setupPup("Sell properties to bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("You can sell properties on these streets: ");

        GridPane gridPane = new GridPane();

        Map<String, Long> counts = player.ownedProperties.stream()
                .collect(groupingBy(Property::getGroup, counting()));

        int counter = 0;
        for (int i = 0; i < player.ownedProperties.size(); i++) {
            for (String key : counts.keySet()) {
                if (player.ownedProperties.get(i).getGroup().equals(key)) {
                    if (player.ownedProperties.get(i).getGroupSize() == counts.get(key)) {
                        Property property = player.ownedProperties.get(i);
                        if (property.getNumOfHouses() > 0) {
                            Button button = new Button(property.getName());
                            button.setOnAction(e -> {
                                popUpWindow.close();
                                sellButtonLogic(player, property);
                            });
                            String message = property.getNumOfHouses() + " houses on this street. Sell house for: "
                                    + (property.getHousePrice() / 2);
                            Text text = new Text(message);
                            gridPane.add(button, 0, counter);
                            gridPane.add(text, 1, counter);
                            counter += 1;
                        }
                    }
                }
            }
        }
        pupFinish(popUpWindow, border, label, gridPane);
    }

    // Putting street to mortgage
    public void mortgageStreet(Player player) {
        Stage popUpWindow = setupPup("Mortgage streets to bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("You can mortgage there streets: ");

        GridPane gridPane = new GridPane();

        int counter = 0;
        for (Property property : player.ownedProperties) {
            if (property.getNumOfHouses() == 0) {
                if (!property.isInMort()) {
                    Button button = new Button(property.getName());
                    button.setOnAction(e -> {
                        popUpWindow.close();
                        mortgageButtonLogic(player, property);
                    });
                    String message = "Mortgage house for: " + (property.getMortPrice());
                    Text text = new Text(message);
                    gridPane.add(button, 0, counter);
                    gridPane.add(text, 1, counter);
                    counter += 1;
                }
            }
        }
        pupFinish(popUpWindow, border, label, gridPane);
    }

    // Unmortgage streets
    public void unMortgageStreet(Player player){
        Stage popUpWindow =  setupPup("Unmortgage streets");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("You can unmortgage there streets: ");

        GridPane gridPane = new GridPane();

        int counter = 0;
        for (Property property : player.ownedProperties) {
            if (property.isInMort()) {
                Button button = new Button(property.getName());
                button.setOnAction(e -> {
                    popUpWindow.close();
                    unMortgageButtonLogic(player, property);
                });
                String message = "Unmortgage house for: " + (property.getMortPrice());
                Text text = new Text(message);
                gridPane.add(button, 0, counter);
                gridPane.add(text, 1, counter);
                counter += 1;
            }
        }
        pupFinish(popUpWindow, border, label, gridPane);
    }

    // Confirmation buttons Logical part
    private void buyButtonLogic(Player player, Property property) {
        boolean canBuy = checkGroup(player, property, 1);
        Stage popUpWindow = setupPup("Buy Properties from Bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Do you want to buy a house on " + property.getName() + "?" + " You currently own " + property.getNumOfHouses() + " houses on this street.");

        Label descr = new Label();
        descr.setText(property.toString());

        Button yes = new Button("Yes");
        if (canBuy) {
            yes.setOnAction(e -> {
                if (property.getNumOfHouses() == 4) {
                    if (currentHotelBoard >= maxHotels) {
                        yes.setDisable(true);
                    } else {
                        popUpWindow.close();
                        player.money = player.money - property.getHousePrice();
                        property.setNumOfHouses(property.getNumOfHouses() + 1);
                        currentHotelBoard += 1;
                        currentHouseBoard -= 4;
                        buyProperties(player);
                    }
                } else {
                    if (currentHouseBoard >= maxHouses) {
                        yes.setDisable(true);
                    } else {
                        popUpWindow.close();
                        player.money = player.money - property.getHousePrice();
                        property.setNumOfHouses(property.getNumOfHouses() + 1);
                        currentHouseBoard += 1;
                        buyProperties(player);
                    }
                }
            });
        } else {
            yes.setDisable(true);
        }

        Button no = new Button("No");
        no.setOnAction(e -> {
            popUpWindow.close();
            buyProperties(player);
        });
        pupLogicButtonFinish(popUpWindow, border, label, descr, yes, no);
    }

    // Logic of sell button
    private void sellButtonLogic(Player player, Property property) {
        boolean canSell = checkGroup(player, property, -1);
        Stage popUpWindow = setupPup("Sell Properties to bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Do you want to sell a house on " + property.getName() + "?" + " You currently own " + property.getNumOfHouses() + " houses on this street.");

        Label descr = new Label();
        descr.setText(property.toString());

        Button yes = new Button("Yes");

        if (canSell) {
            yes.setOnAction(e -> {
                if (property.getNumOfHouses() == 0) {
                    yes.setDisable(true);
                } else {
                    if (property.getNumOfHouses() == 5) {
                        currentHotelBoard -= 1;
                        currentHouseBoard += 4;
                        player.money = player.money + (property.getHousePrice() / 2);
                        property.setNumOfHouses(property.getNumOfHouses() - 1);
                        popUpWindow.close();
                        sellProperties(player);
                    }
                    if (property.getNumOfHouses() < 5) {
                        currentHouseBoard -= 1;
                        player.money = player.money + (property.getHousePrice() / 2);
                        popUpWindow.close();
                        sellProperties(player);
                    }
                }
            });
        }
        else{
            yes.setDisable(true);
        }

        Button no = new Button("No");
        no.setOnAction(e -> {
            popUpWindow.close();
            sellProperties(player);
        });
        pupLogicButtonFinish(popUpWindow, border, label, descr, yes, no);
    }

    // logic of mortgage button
    private void mortgageButtonLogic(Player player, Property property) {
        Stage popUpWindow = setupPup("Mortgage street to bank");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Do you want to mortgage " + property.getName() + "?");

        Label descr = new Label();
        descr.setText(property.toString());

        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            popUpWindow.close();
            property.setInMort(true);
            player.money = player.money + property.getMortPrice();
            mortgageStreet(player);
        });

        for (Property group : player.ownedProperties) {
            if (group.getGroup().equals(property.getGroup())) {
                if (group.getNumOfHouses() > 0) {
                    yes.setDisable(true);
                }
            }
        }

        Button no = new Button("No");
        no.setOnAction(e -> {
            popUpWindow.close();
            mortgageStreet(player);
        });
        pupLogicButtonFinish(popUpWindow, border, label, descr, yes, no);
    }

    // logic of unmortgage button
    private void unMortgageButtonLogic(Player player, Property property){
        Stage popUpWindow = setupPup("Unmortgage street");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Do you want to unmortgage " + property.getName() + "?");

        Label descr = new Label();
        descr.setText(property.toString());

        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            popUpWindow.close();
            property.setInMort(false);
            player.money = player.money - (property.getMortPrice()+(property.getMortPrice()/10));
            unMortgageStreet(player);
        });

        Button no = new Button("No");
        no.setOnAction(e -> {
            popUpWindow.close();
            mortgageStreet(player);
        });

        pupLogicButtonFinish(popUpWindow, border, label, descr, yes, no);
    }

    // Method to check if all properties in group meet requirements.
    private boolean checkGroup(Player player, Property property, int var) {
        String group = property.getGroup();
        int x = 0;
        for (Property p : player.ownedProperties) {
            if (p.getGroup().equals(group)) {
                if (p.getNumOfHouses() + var > (property.getNumOfHouses())) {
                    x += 1;
                } else if (p.getNumOfHouses() == property.getNumOfHouses()) {
                    x += 1;
                }
            }
        }
        return property.getGroupSize() == x;
    }

    // Refactored code for repeating settings....
    public void pupFinish(Stage popUpWindow, BorderPane border, Label label, GridPane gridPane) {
        border.setTop(label);
        border.setLeft(gridPane);
        BorderPane.setAlignment(label, Pos.CENTER);

        Scene scene = new Scene(border);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    // Refactored code for repeating settings....
    private void pupLogicButtonFinish(Stage popUpWindow, BorderPane border, Label label, Label descr, Button yes, Button no) {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(yes, no);

        border.setTop(label);
        border.setBottom(hBox);
        border.setCenter(descr);
        BorderPane.setAlignment(label, Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(border);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }
}
