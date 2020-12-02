package sample.service;


import javafx.scene.control.Label;
import sample.objects.Card;
import sample.objects.Player;
import sample.objects.Property;
import sample.popups.PopUpPrompt;

import java.util.ArrayList;

public class LogicOnImpact extends TopOfBoard {

    public void setCurrentTileAction(Player player, Card chestCard, Card chanceCard, ArrayList<Property> pArray, ArrayList<Player> allPl, Label gameinfo){
        // Sets action when figure steps on given tile.... possible refactoring using better class and object system
        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-1)){
            // Mediterranean Avenue
            propertyAction(pArray.get(0), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-2)){
            // Community chest
            gameinfo.setText(chestCard.getMessage());
            chestAction(chestCard, player);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-3)){
            // Baltic Avenue
            propertyAction(pArray.get(1), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-4)){
            // Income Tax. Final.
            gameinfo.setText("Income Tax. Pay $200.");
            player.changeMoney(-200);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-5)){
            // Reading Railroad
            railAction(pArray.get(2), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-6)){
            // Oriental Avenue
            propertyAction(pArray.get(3), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-7)){
            // Chance Card.
            gameinfo.setText(chanceCard.getMessage());
            chanceAction(chanceCard, player, 7, pArray, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-8)){
            // Vermont Avenue
            propertyAction(pArray.get(4), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-9)){
            // Connecticut Avenue
            propertyAction(pArray.get(5), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            if (player.inJail)
                gameinfo.setText("You are in Jail. Throw double, pay $50 or use Get out of jail card.");
            else {
                gameinfo.setText("Just visiting.");
            }
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-1) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // St. Charles Place
            propertyAction(pArray.get(6), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-2) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // Electric Company.
            utilityAction(pArray.get(7), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-3) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // States Avenue
            propertyAction(pArray.get(8), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-4) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // Virginia Avenue
            propertyAction(pArray.get(9), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-5) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // Pennsylvania Railroad
            railAction(pArray.get(10), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-6) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // St. James Place
            propertyAction(pArray.get(11), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-7) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // Community Chest
            gameinfo.setText(chestCard.getMessage());
            chestAction(chestCard, player);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-8) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // Tennessee Avenue
            propertyAction(pArray.get(12), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-9) && player.figure.getX() == getTileSize()*(getDimension()-10)){
            // New Your Avenue
            propertyAction(pArray.get(13), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-9)){
            // Kentucky Avenue
            propertyAction(pArray.get(14), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-8)){
            // Chance
            gameinfo.setText(chanceCard.getMessage());
            chanceAction(chanceCard, player, 22, pArray, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-7)){
            // Indiana Avenue
            propertyAction(pArray.get(15), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-6)){
            // Illinois Avenue
            propertyAction(pArray.get(16), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-5)){
            // B. & O. Railroad
            railAction(pArray.get(17), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-4)){
            // Atlantic Avenue
            propertyAction(pArray.get(18), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-3)){
            // Vendor Avenue
            propertyAction(pArray.get(19), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-2)){
            // Water works
            utilityAction(pArray.get(20), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension()-1)){
            // Marvin Gardens
            propertyAction(pArray.get(21), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-10) && player.figure.getX() == getTileSize()*(getDimension())){
            // Go to jail
            player.inJail = true;
            player.figure.setY(getTileSize()*(getDimension()));
            player.figure.setX(getTileSize()*(getDimension()-10));
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-9) && player.figure.getX() == getTileSize()*(getDimension())){
            // Pacific Avenue
            propertyAction(pArray.get(22), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-8) && player.figure.getX() == getTileSize()*(getDimension())){
            // North Carolina Avenue
            propertyAction(pArray.get(23), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-7) && player.figure.getX() == getTileSize()*(getDimension())){
            // Chest
            gameinfo.setText(chestCard.getMessage());
            chestAction(chestCard, player);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-6) && player.figure.getX() == getTileSize()*(getDimension())){
            // Pennsylvania Avenue
            propertyAction(pArray.get(24), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-5) && player.figure.getX() == getTileSize()*(getDimension())){
            // Short Line
            railAction(pArray.get(25), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-4) && player.figure.getX() == getTileSize()*(getDimension())){
            // Chance
            gameinfo.setText(chanceCard.getMessage());
            chanceAction(chanceCard, player, 36, pArray, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-3) && player.figure.getX() == getTileSize()*(getDimension())){
            // Park Place
            propertyAction(pArray.get(26), player, allPl);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-2) && player.figure.getX() == getTileSize()*(getDimension())){
            // Luxury Tax. Final
            gameinfo.setText("Luxury tax. Pay $100.");
            player.changeMoney(-100);
        }

        if (player.figure.getY() == getTileSize()*(getDimension()-1) && player.figure.getX() == getTileSize()*(getDimension())){
            // Boardwalk
            propertyAction(pArray.get(27), player, allPl);
        }
        setText(player);
    }

    // Method sorting action if player steps on property
    private void propertyAction(Property property, Player player, ArrayList<Player> allPlayers){
        PopUpPrompt pup = new PopUpPrompt();
        // If tile is not owned by anyone...
        if (property.getOwner().equals("")){
            pup.unownedProperty("Unowned property", player, property, "street", allPlayers);
        }

        // If tile is owned by another player...
        else if (!property.getOwner().equals(player.getName())){
            // Check for property rent, pay amount to player....
            if (!property.isInMort()) {
                for (Player owner : allPlayers) {
                    if (property.getOwner().equals(owner.getName())) {
                        int x = 0;
                        for (int i = 0; i < owner.ownedProperties.size(); i++) {
                            if (owner.ownedProperties.get(i).getGroup().equals(property.getGroup())) {
                                x = x + 1;
                            }
                        }
                        if (x == property.getGroupSize()) {
                            if (property.getNumOfHouses() == 0) {
                                sendRent(player, owner, property.getInitRent() * 2);
                            }
                            if (property.getNumOfHouses() == 1) {
                                sendRent(player, owner, property.getRent1());
                            }
                            if (property.getNumOfHouses() == 2) {
                                sendRent(player, owner, property.getRent2());
                            }
                            if (property.getNumOfHouses() == 3) {
                                sendRent(player, owner, property.getRent3());
                            }
                            if (property.getNumOfHouses() == 4) {
                                sendRent(player, owner, property.getRent4());
                            }
                            if (property.getNumOfHouses() == 5) {
                                sendRent(player, owner, property.getRentHotel());
                            }
                        } else {
                            sendRent(player, owner, property.getInitRent());
                        }
                    }
                }
            }
        }
    }

    // Method sorting if player steps on Community card tile
    private void chestAction(Card chestCard, Player player){
        player.changeMoney(chestCard.getMoney());
        if (chestCard.isSpecial()){
            if (chestCard.getMessage().equals("Advance to Go (Collect $200)")){
                player.figure.setX(getTileSize()* getDimension());
                player.figure.setY(getTileSize()* getDimension());
            }
            if (chestCard.getMessage().equals("Go to Jail–Go directly to jail–Do not pass Go–Do not collect $200")){
                player.inJail = true;
                player.figure.setY(getTileSize()*(getDimension()));
                player.figure.setX(getTileSize()*(getDimension()-10));
            }
            if (chestCard.getMessage().equals("Get Out of Jail Free")){
                player.outOfJailCard += 1;
            }
            delay();
        }
    }

    // Method sorting if player steps on Chance card tile
    private void chanceAction(Card chanceCard, Player player, int position, ArrayList<Property> pArray, ArrayList<Player> allPl){
        player.changeMoney(chanceCard.getMoney());
        // Scenarios for each special card...
        if (chanceCard.isSpecial()){
            if (chanceCard.getMessage().equals("Advance to Go (Collect $200)")){
                player.figure.setX(getTileSize()* getDimension());
                player.figure.setY(getTileSize()* getDimension());
            }
            if (chanceCard.getMessage().equals("Go Back 3 Spaces")){
                Movement move = new Movement();
                int moveX = move.moveX((int) player.figure.getX(), (int) player.figure.getY(), -3, player);
                int moveY = move.moveY((int) player.figure.getX(), (int) player.figure.getY(), -3, player);
                player.figure.setY(moveY);
                player.figure.setX(moveX);
            }
            if (chanceCard.getMessage().equals("Get Out of Jail Free")){
                player.outOfJailCard += 1;
            }
            if (chanceCard.getMessage().equals("Go to Jail–Go directly to Jail–Do not pass Go, do not collect $200")){
                player.inJail = true;
                player.figure.setX(getTileSize()*(getDimension()-10));
                player.figure.setY(getTileSize()*(getDimension()));
            }
            if (chanceCard.getMessage().equals("Advance to Illinois Ave—If you pass Go, collect $200")){
                if (position > 24){
                    player.money += 200;
                }
                player.figure.setY(getTileSize()*(getDimension()-10));
                player.figure.setX(getTileSize()*(getDimension()-6));
                propertyAction(pArray.get(16), player, allPl);
            }
            if (chanceCard.getMessage().equals("Advance to St. Charles Place – If you pass Go, collect $200")){
                if (position > 11){
                    player.money += 200;
                }
                player.figure.setY(getTileSize()*(getDimension()-1));
                player.figure.setX(getTileSize()*(getDimension()-10));
                propertyAction(pArray.get(6), player, allPl);
            }
            delay();
        }

    }

    // Method sorting if player steps on Rail tile
    private void railAction(Property property, Player player, ArrayList<Player> allPlayers){
        PopUpPrompt pup = new PopUpPrompt();
        // If tile is not owned by anyone...
        if (property.getOwner().equals("")){
            pup.unownedProperty("Unowned property", player, property, "railroad", allPlayers);
        }

        // If tile is owned by another player...
        else if (!property.getOwner().equals(player.getName())){
            // Check for property rent, pay amount to player....
            for (Player owner : allPlayers) {
                if (property.getOwner().equals(owner.getName())) {
                    int x = 0;
                    for (int i = 0; i < owner.ownedProperties.size(); i++){
                        if (owner.ownedProperties.get(i).getGroup().equals(property.getGroup())){
                            if (!owner.ownedProperties.get(i).isInMort()) {
                                x = x + 1;
                            }
                        }
                    }
                    switch (x) {
                        case 1 -> sendRent(player, owner, property.getInitRent());
                        case 2 -> sendRent(player, owner, property.getRent1());
                        case 3 -> sendRent(player, owner, property.getRent2());
                        case 4 -> sendRent(player, owner, property.getRent3());
                    }
                }
            }
        }
    }

    // Method sorting if player steps on Utility tile
    private void utilityAction(Property property, Player player, ArrayList<Player> allPlayers){
        PopUpPrompt pup = new PopUpPrompt();

        if (property.getOwner().equals("")){
            pup.unownedProperty("Unowned property", player, property, "utility", allPlayers);
        }

        // If tile is owned by another player...
        else if (!property.getOwner().equals(player.getName())){
            // Check for property rent, pay amount to player....
            for (Player owner : allPlayers) {
                if (property.getOwner().equals(owner.getName())) {
                    int x = 0;
                    for (int i = 0; i < owner.ownedProperties.size(); i++){
                        if (owner.ownedProperties.get(i).getGroup().equals(property.getGroup())){
                            if (!owner.ownedProperties.get(i).isInMort()) {
                                x = x + 1;
                            }
                        }
                    }
                    if (x == 1){
                        sendRent(player, owner, pup.utilityAction(4));
                    }
                    if (x == 2){
                        sendRent(player, owner, pup.utilityAction(10));
                    }
                }
            }
        }
    }

    // Method sends rent when property is owned.
    private void sendRent(Player player, Player owner, int rent) {
        player.money -= (rent);
        owner.money += (rent);
    }
}
