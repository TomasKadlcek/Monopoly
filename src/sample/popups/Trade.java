package sample.popups;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.objects.Player;
import sample.objects.Property;
import sample.popups.Bank;

import java.util.*;


public class Trade extends Bank {

    // All auction functions

    public void auction(Property property, ArrayList<Player> allPlayers){
        Stage popUpWindow = setupPup("Auction");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Auction property: " + property.getName() + ". Input only numbers!");

        GridPane gridPane = new GridPane();
        Map<Player, Integer> results = new HashMap<>();
        TextField[] tfs = new TextField[allPlayers.size()];

        for (int j = 0; j < tfs.length; j++) {
            tfs[j] = new TextField();
            String message = allPlayers.get(j).getName() + " input your bid here.";
            Text text = new Text(message);
            gridPane.add(tfs[j], 0, j);
            gridPane.add(text, 1, j);
        }

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            auctionSubmit(property, allPlayers, popUpWindow, results, tfs);
        });

        border.setTop(label);
        border.setLeft(gridPane);
        border.setBottom(submit);
        BorderPane.setAlignment(label, Pos.CENTER);

        Scene scene = new Scene(border);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }


    private void auctionSubmit(Property property, ArrayList<Player> allPlayers, Stage popUpWindow, Map<Player, Integer> results, TextField[] tfs) {
        for (int i = 0; i < tfs.length; i++){
            try {
                int a = Integer.parseInt(tfs[i].getText());
                results.put(allPlayers.get(i), a);
            }
            catch (NumberFormatException ignored){
            }
        }
        try{
            Map.Entry<Player, Integer> max = getMaxInput(results);
            max.getKey().ownedProperties.add(property);
            max.getKey().money -= max.getValue();
            property.setOwner(max.getKey().getName());
            setText(max.getKey());
            popUpWindow.close();
        }
        catch (NoSuchElementException ignored){
            popUpWindow.close();
        }
    }

    private <K, V extends Comparable<V>> Map.Entry<K, V> getMaxInput(Map<K, V> map) {
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
    }


    public void trade(Player currentPlayer, ArrayList<Player> allPlayers){
        Stage popUpWindow = setupPup("Trading");

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Choose a player to trade with: ");

        GridPane gridPane = new GridPane();

        for (int i = 0;  i < allPlayers.size(); i++){
            Player opponent = allPlayers.get(i);
            if (opponent != currentPlayer) {
                Button button = new Button(allPlayers.get(i).getName());
                gridPane.add(button, i, 0);
                button.setOnAction(e -> {
                    tradeWithPlayer(currentPlayer, opponent, allPlayers);
                    popUpWindow.close();
                });
            }
        }

        pupFinish(popUpWindow, border, label, gridPane);
    }

    private void tradeWithPlayer(Player current, Player opponent, ArrayList<Player> allPlayers){
        Stage popUpWindow = setupPup("Trading");
        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Choose a player to trade with: ");

        GridPane gridPane = new GridPane();

//        Map<Player, Property> results = new HashMap<>();
        CheckBox[] currentBox = new CheckBox[current.ownedProperties.size()];
        CheckBox[] opponentBox = new CheckBox[opponent.ownedProperties.size()];
        Text currentText = new Text(current.getName());
        Text opponentText = new Text(opponent.getName());
        TextField currentMoney = new TextField();
        TextField opponentMoney = new TextField();

        gridPane.add(currentText, 0, 0);
        gridPane.add(opponentText, 1, 0);
        gridPane.add(currentMoney, 0, 1);
        gridPane.add(opponentMoney, 1, 1);

        for (int j = 0; j < currentBox.length; j++) {
            currentBox[j] = new CheckBox(current.ownedProperties.get(j).getName());
            gridPane.add(currentBox[j], 0, j+2);
        }

        for (int j = 0; j < opponentBox.length; j++) {
            opponentBox[j] = new CheckBox(opponent.ownedProperties.get(j).getName());
            gridPane.add(opponentBox[j], 1, j+2);
        }

        Button transaction = new Button("Make Transaction");
        transaction.setOnAction(e -> {
            makeTrade(current, opponent, currentBox, opponentBox, currentMoney, opponentMoney);
            popUpWindow.close();
            trade(current, allPlayers);
        });

        border.setBottom(transaction);
        pupFinish(popUpWindow, border, label, gridPane);
    }

    private void makeTrade(Player current, Player opponent, CheckBox[] currentBox, CheckBox[] opponentBox, TextField currentMoney, TextField opponentMoney) {
        try {
            int a = Integer.parseInt(currentMoney.getText());
            current.money -= a;
            opponent.money += a;
        }catch (NumberFormatException ignored) {
        }
        try {
            int a = Integer.parseInt(opponentMoney.getText());
            current.money += a;
            opponent.money -= a;
        }catch (NumberFormatException ignored) {
        }

        transactProperties(current, opponent, currentBox);

        transactProperties(opponent, current, opponentBox);
    }

    private void transactProperties(Player p1, Player p2, CheckBox[] currentBox) {
        ArrayList<Property> deleteFromList = new ArrayList<>();
        for (int i = 0; i < currentBox.length; i++) {
            if (currentBox[i].isSelected()) {
                p1.ownedProperties.get(i).setOwner(p2.getName());
                deleteFromList.add(p1.ownedProperties.get(i));
            }
        }
        p1.ownedProperties.removeAll(deleteFromList);
        p2.ownedProperties.addAll(deleteFromList);
        setText(p1);
        setText(p2);
    }
}
