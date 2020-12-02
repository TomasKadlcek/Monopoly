package sample.service;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.base.GameBoard;
import sample.objects.*;
import sample.popups.PopUpPrompt;
import sample.service.Dice;
import sample.service.LogicOnImpact;
import sample.service.Movement;

import java.util.ArrayList;
import java.util.Collections;

public class TopOfBoard extends GameBoard {
    // Arrays with all cards, players....
    private final ArrayList<Player> allPlayers = new ArrayList<>();
    private final ArrayList<Card> allCommunityCards = new ArrayList<>();
    private final ArrayList<Card> allChanceCards = new ArrayList<>();
    public final ArrayList<Property> allProperties = new ArrayList<>();
    public final Group topElementsGroup = new Group();


    // Global changing variables...
    private Label randResult;
    private Label currentPlayerLabel;
    private Label gameInfo;
    private ImageView carFigImg;
    private ImageView shipFigImg;
    private ImageView bootFigImg;
    private ImageView dogFigImg;
    private ImageView hatFigImg;
    private ImageView trolleyFigImg;
    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;
    private Text ownedCards1;
    private Text ownedCards2;
    private Text ownedCards3;
    private Text ownedCards4;
    private Button rollDice;
    private Button manageTurn;
    private Button outCard;
    private Button payOut;
    private int doubleCount;

    // Global final variables
    public final int maxHouses = 32;
    public final int maxHotels = 12;
    public int currentHouseBoard = 0;
    public int currentHotelBoard = 0;


    // Initial states of players. Player 1 starts true, others false...
    // set to player directly!!
    private boolean isPlayer1 = true;
    private boolean isPlayer2 = false;
    private boolean isPlayer3 = false;
    private boolean isPlayer4 = false;


    // main function for adding generated buttons, figures, labels etc.
    public Parent newScene(){
        Pane top = new Pane();
        top.setPrefSize((getDimension()* getTileSize())+(getTileSize()*4)+getMenuWidth(), (getDimension() * getTileSize())+(getTileSize()*4));
        top.getChildren().addAll(topElementsGroup);

        // Initializing methods for game functionality
        initializeCards();
        initializeProperties();
        Collections.shuffle(allChanceCards);
        Collections.shuffle(allCommunityCards);
        addLabel();
        addBtn();
        addFigures();
        addText();
        welcomeScreen();

        return top;
    }

    // Welcome screen. Choose number of players...
    private void welcomeScreen(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Welcome to monopoly");
        popUpWindow.setMinWidth(500);
        popUpWindow.setMinHeight(400);

        BorderPane border = new BorderPane();
        Label label = new Label();
        label.setText("Welcome to Monopoly. Please choose the number of players for this game.");

        GridPane gridPane = new GridPane();
        ComboBox<Integer> selectNumber = new ComboBox<>();
        selectNumber.setItems(FXCollections.observableArrayList(2,3,4));
        selectNumber.setValue(2);
        gridPane.add(selectNumber, 0,0);
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            popUpWindow.close();
            initializePlayers(selectNumber.getValue());
        });
        gridPane.add(submit, 0, 1);


        border.setTop(label);
        border.setCenter(gridPane);
        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(gridPane, Pos.BOTTOM_RIGHT);
        Scene scene = new Scene(border);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    // Logical methods for buttons etc.
    // Functions of roll dice
    private void actionButton (Player player){
        gameInfo.setText("");
        manageTurn.setOnAction(e -> {
            PopUpPrompt pup = new PopUpPrompt();
            pup.manageTurn(player, allPlayers, allProperties);
        });

        Dice dice = new Dice();
        int rand = dice.randomDice();
        int rand2 = dice.randomDice();
        int total = rand + rand2;
        boolean isDouble = rand == rand2;
        randResult.setText(rand + " + " + rand2);
        currentPlayerLabel.setText("Current player: " + player.getName());

        if (player.inJail){
            ifInJail(player, isDouble, total);
            doubleCount = 0;
        }
        else {
            if (!isDouble){
                moveAction(player, total);
                doubleCount = 0;
            }
            if (isDouble){
                doubleCount += 1 ;
                if (doubleCount == 3){
                    gameInfo.setText("In Jail for speeding.");
                    player.inJail = true;
                    player.figure.setY(getTileSize()*(getDimension()));
                    player.figure.setX(getTileSize()*(getDimension()-10));
                    doubleCount = 0;
                }
                else {
                    gameInfo.setText("Double");
                    moveAction(player, total);
                    actionButton(player);
                }
            }
        }
    }

    // Move action
    private void moveAction(Player player, int roll){
        Movement move = new Movement();

        int moveX = move.moveX((int) player.figure.getX(), (int) player.figure.getY(), roll, player);
        int moveY = move.moveY((int) player.figure.getX(), (int) player.figure.getY(), roll, player);
        player.figure.setY(moveY);
        player.figure.setX(moveX);

        LogicOnImpact logic = new LogicOnImpact();
        logic.setCurrentTileAction(player, drawChestCard(), drawChanceCard(), allProperties, allPlayers, gameInfo);
    }

    // Activates actionButton function. Changes active player to inactive and sets up next player...
    private void setNextPlayer(){
        rollDice.setDisable(true);
        if (isPlayer1) {
            actionButton(allPlayers.get(0));
            // set to a function in future...
            isPlayer1 = false;
            isPlayer2 = true;
            isPlayer3 = false;
            isPlayer4 = false;
        }

        else if (isPlayer2){
            actionButton(allPlayers.get(1));
            isPlayer1 = true;
            isPlayer2 = false;
            isPlayer3 = false;
            isPlayer4 = false;
        }

//        else if (isPlayer3){
//            actionButton(allPlayers.get(2));
//            isPlayer1 = false;
//            isPlayer2 = false;
//            isPlayer3 = false;
//            isPlayer4 = true;
//        }
//        else if (isPlayer4){
//            actionButton(allPlayers.get(3));
//            isPlayer1 = true;
//            isPlayer2 = false;
//            isPlayer3 = true;
//            isPlayer4 = false;
//        }
    }

    // Logic if player is in jail....
    private void ifInJail(Player player, boolean isDouble, int total){
        player.timeInJail += 1;

        if (isDouble){
            outCard.setDisable(true);
            payOut.setDisable(true);
            player.inJail = false;
            moveAction(player, total);

        }
        else if (player.timeInJail == 3){
            outCard.setDisable(true);
            payOut.setDisable(true);
            player.money -= 50;
            player.inJail = false;
            player.timeInJail = 0;
        }
        else {
            gameInfo.setText("You are in Jail. Pay $50 or use Out of jail card.");

            if (player.outOfJailCard > 0) {
                outCard.setDisable(false);
                outCard.setOnAction(e -> {
                    player.inJail = false;
                    actionButton(player);
                    outCard.setDisable(true);
                    payOut.setDisable(true);
                });
            }
            if (player.money >= 50){
                payOut.setDisable(false);
                payOut.setOnAction(e -> {
                    player.inJail = false;
                    actionButton(player);
                    payOut.setDisable(true);
                    outCard.setDisable(true);
                });
            }
        }
    }

    // Delays game for 1 second if needed
    public void delay(){
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Check if funds are not 0. If yes game ends
    public void checkFunds(){
        for (Player player : allPlayers){
            if (player.money < 0){
                PopUpPrompt pup = new PopUpPrompt();
                pup.winnerAnnouncement(allPlayers);            }
        }

    }

    // Refreshes money and property text on board
    public void setText(Player player){
        player.txt.setText("Money: " + player.money);

        StringBuilder ownedProps = new StringBuilder();
        ownedProps.append("Get out of Jail cards: ").append(player.outOfJailCard).append("\n");
        for (Property property : player.ownedProperties){
            ownedProps.append(property.getName()).append(" Houses: ").append(property.getNumOfHouses()).append("\n");
        }
        player.ownedCards.setText(String.valueOf(ownedProps));
    }

    // Only initializing down bellow!!

    // All functional game objects initialization. And Draw card functions

    // Function for adding playable buttons and their functionality
    private void addBtn (){
        // roll Dice button settings...
        rollDice = new Button("Next Player");
        rollDice.setTranslateX((getTileSize()* getDimension())+(getTileSize()*2.5));
        rollDice.setTranslateY(getTileSize());
        rollDice.setOnAction(actionEvent -> setNextPlayer());

        // Start again button settings
        Button finishMove = new Button("Finish Move");
        finishMove.setTranslateX((getTileSize()* getDimension())+(getTileSize()*2.5));
        finishMove.setTranslateY(getTileSize()*2);
        finishMove.setOnAction(actionEvent -> {
            rollDice.setDisable(false);
            checkFunds();
        });

        manageTurn = new Button("Manage Turn");
        manageTurn.setTranslateX((getTileSize()* getDimension())+(getTileSize()*4.5));
        manageTurn.setTranslateY(getTileSize()*2);

        outCard = new Button("Use out of jail card");
        outCard.setTranslateX((getTileSize()* getDimension())+(getTileSize()*2.5));
        outCard.setTranslateY(getTileSize()*11.5);
        outCard.setDisable(true);

        payOut = new Button("Pay $50 to get out of jail");
        payOut.setTranslateX((getTileSize()* getDimension())+(getTileSize()*2.5));
        payOut.setTranslateY(getTileSize()*12);
        payOut.setDisable(true);

        topElementsGroup.getChildren().addAll(rollDice, finishMove, manageTurn, outCard, payOut);
    }

    // Adding labels for user information
    private void addLabel (){
        randResult = new Label();
        randResult.setTranslateX((getTileSize()* getDimension())+(getTileSize()*4));
        randResult.setTranslateY(0);

        currentPlayerLabel = new Label();
        currentPlayerLabel.setTranslateX((getTileSize()* getDimension())+(getTileSize()*5));
        currentPlayerLabel.setTranslateY(0);

        gameInfo = new Label();
        gameInfo.setTranslateY(getTileSize()*6.3);
        gameInfo.setTranslateX(getTileSize()*3);

        topElementsGroup.getChildren().addAll(randResult, currentPlayerLabel, gameInfo);
    }

    // Sets basic text into each player rectangle/tile...
    private void addText(){
        // Add later texts for remaining players...
        ScrollPane sp1 = new ScrollPane();
        ScrollPane sp2 = new ScrollPane();
        ScrollPane sp3 = new ScrollPane();
        ScrollPane sp4 = new ScrollPane();

        text1 = new Text();
        setText(text1, 4);

        text2 = new Text();
        setText(text2, 6);

        text3 = new Text();
        setText(text3, 8);

        text4 = new Text();
        setText(text4, 10);

        ownedCards1 = new Text();
        setScroller(sp1, ownedCards1, getTileSize());

        ownedCards2 = new Text();
        setScroller(sp2, ownedCards2, getTileSize()*4);

        ownedCards3 = new Text();
        setScroller(sp3, ownedCards3, getTileSize()*7);

        ownedCards4 = new Text();
        setScroller(sp4, ownedCards4, getTileSize()*10);

        topElementsGroup.getChildren().addAll(text1, text2, text3, text4, sp1, sp2, sp3, sp4);
    }

    // Refactoring to get rid of duplicate code
    private void setText(Text text, int i) {
        text.setY(getTileSize() * i);
        text.setX(getTileSize() * (getDimension() + 4));
        text.setText("Money: ");
    }

    private void setScroller(ScrollPane sp, Text ownedCards, int yLocation) {
        sp.setContent(ownedCards);
        sp.setPrefSize(getTileSize() * 4, getTileSize() * 2);
        sp.setTranslateY((yLocation));
        sp.setTranslateX((getTileSize() * getDimension()) + (getTileSize() * 7));
    }

    // Function for adding basic playable game pieces (figures).
    private void addFigures (){

        // Add later remaining pieces
        Image carFig = new Image("sample/img/CarFig.png");
        carFigImg = new ImageView();
        carFigImg.setImage(carFig);
        carFigImg.setFitHeight(getTileSize());
        carFigImg.setFitWidth(getTileSize());
        carFigImg.setX(getTileSize()*(getDimension()));
        carFigImg.setY(getTileSize()*(getDimension()));

        Image shipFig = new Image("sample/img/shipFig.png");
        shipFigImg = new ImageView();
        shipFigImg.setImage(shipFig);
        shipFigImg.setFitHeight(getTileSize());
        shipFigImg.setFitWidth(getTileSize());
        shipFigImg.setX(getTileSize()*(getDimension()));
        shipFigImg.setY(getTileSize()*(getDimension()));

        Image bootFig = new Image("sample/img/bootFig.png");
        bootFigImg = new ImageView();
        bootFigImg.setImage(bootFig);
        bootFigImg.setFitHeight(getTileSize());
        bootFigImg.setFitWidth(getTileSize());
        bootFigImg.setX(getTileSize()*(getDimension()));
        bootFigImg.setY(getTileSize()*(getDimension()));

        Image dogFig = new Image("sample/img/dogFig.png");
        dogFigImg = new ImageView();
        dogFigImg.setImage(dogFig);
        dogFigImg.setFitHeight(getTileSize());
        dogFigImg.setFitWidth(getTileSize());
        dogFigImg.setX(getTileSize()*(getDimension()));
        dogFigImg.setY(getTileSize()*(getDimension()));

        Image hatFig = new Image("sample/img/hatFig.png");
        hatFigImg = new ImageView();
        hatFigImg.setImage(hatFig);
        hatFigImg.setFitHeight(getTileSize());
        hatFigImg.setFitWidth(getTileSize());
        hatFigImg.setX(getTileSize()*(getDimension()));
        hatFigImg.setY(getTileSize()*(getDimension()));

        Image trolleyFig = new Image("sample/img/trolleyFig.png");
        trolleyFigImg = new ImageView();
        trolleyFigImg.setImage(trolleyFig);
        trolleyFigImg.setFitHeight(getTileSize());
        trolleyFigImg.setFitWidth(getTileSize());
        trolleyFigImg.setX(getTileSize()*(getDimension()));
        trolleyFigImg.setY(getTileSize()*(getDimension()));

        // Add rest of figures...

//        topElementsGroup.getChildren().addAll(carFigImg, shipFigImg, bootFigImg, dogFigImg, hatFigImg, trolleyFigImg);
    }

    // init players... two for now. possible to ass more...
    private void initializePlayers(int num){
        int startMoney = 1300;
        if (num >= 2){
            Player player1 = new Player(1, true, "Player 1", carFigImg, startMoney, text1, ownedCards1);
            allPlayers.add(player1);
            Player player2 = new Player(2, false,"Player 2", shipFigImg, startMoney,text2, ownedCards2);
            allPlayers.add(player2);
            topElementsGroup.getChildren().addAll(carFigImg, shipFigImg);
            if(num > 3){
                Player player3 = new Player(3, false, "Player 3", hatFigImg, startMoney, text3, ownedCards3);
                allPlayers.add(player3);
                topElementsGroup.getChildren().addAll(hatFigImg);
                if (num == 4){
                    Player player4 = new Player(4, false, "Player 4", dogFigImg, startMoney, text4, ownedCards4);
                    allPlayers.add(player4);
                    topElementsGroup.getChildren().addAll(dogFigImg);
                }
            }
        }
    }

    // Initialising all cards. Not possible to make in separate class as throws Index out of bounds exception...
    private void initializeCards(){
        // Finish off specials
        // Community cards
        allCommunityCards.add(new Card("Chest card", "Doctors fee. Pay $50", -50, false));
        allCommunityCards.add(new Card("Chest card", "Pay school fees of $150", -200, false));
        allCommunityCards.add(new Card("Chest card", "Advance to Go (Collect $200)", 200, true));
        allCommunityCards.add(new Card("Chest card", "Bank error in your favor—Collect $200", 200, false));
        allCommunityCards.add(new Card("Chest card", "From sale of stock you get $50", 50, false));
        allCommunityCards.add(new Card("Chest card", "Get Out of Jail Free", 0, true));
        allCommunityCards.add(new Card("Chest card", "Go to Jail–Go directly to jail–Do not pass Go–Do not collect $200", 0, true));
        allCommunityCards.add(new Card("Chest card", "Holiday Fund matures—Receive $100", 100, false));
        allCommunityCards.add(new Card("Chest card", "Income tax refund–Collect $20", 20, false));
        allCommunityCards.add(new Card("Chest card", "It is your birthday—Collect $10", 10, false));
        allCommunityCards.add(new Card("Chest card", "Life insurance matures–Collect $100", 100, false));
        allCommunityCards.add(new Card("Chest card", "Pay hospital fees of $100", 100, false));
        allCommunityCards.add(new Card("Chest card", "Receive $25 consultancy fee", 25, false));
//        allCommunityCards.add(new Card("Chest card", "You are assessed for street repairs–$40 per house–$115 per hotel", 0, true));
        allCommunityCards.add(new Card("Chest card", "You have won second prize in a beauty contest–Collect $10", 10, false));
        allCommunityCards.add(new Card("Chest card", "You inherit $100", 100, false));

        // Chance cards... finish off specials...
        allChanceCards.add(new Card("Chance card", "Bank pays you dividend of $50", 50, false));
        allChanceCards.add(new Card("Chance card", "Pay poor tax of $15", -15, false));
        allChanceCards.add(new Card("Chance card", "Your building and loan matures—Collect $150", 150, false));
        allChanceCards.add(new Card("Chance card", "You have won a crossword competition—Collect $100", 100, false));
        allChanceCards.add(new Card("Chance card", "Advance to Go (Collect $200)", 200, true));
        allChanceCards.add(new Card("Chance card", "Advance to Illinois Ave—If you pass Go, collect $200", 0, true));
        allChanceCards.add(new Card("Chance card", "Advance to St. Charles Place – If you pass Go, collect $200", 0, true));
//        allChanceCards.add(new Card("Chance card", "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", 0, true));
//        allChanceCards.add(new Card("Chance card", "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", 0, true));
        allChanceCards.add(new Card("Chance card", "Get Out of Jail Free", 0, true));
        allChanceCards.add(new Card("Chance card", "Go Back 3 Spaces", 0, true));
        allChanceCards.add(new Card("Chance card", "Go to Jail–Go directly to Jail–Do not pass Go, do not collect $200", 0, true));
//        allChanceCards.add(new Card("Chance card", "Make general repairs on all your property–For each house pay $25–For each hotel $100", 0, true));
//        allChanceCards.add(new Card("Chance card", "Take a walk on the Boardwalk–Advance token to Boardwalk", 0, true));
//        allChanceCards.add(new Card("Chance card", "Take a trip to Reading Railroad–If you pass Go, collect $200", 0, true));
//        allChanceCards.add(new Card("Chance card", "You have been elected Chairman of the Board–Pay each player $50", 0, true));

    }


    // Method initializing all properties on map.
    public void initializeProperties(){
        // Pos 0 == Go, Pos 2 == Chest
        allProperties.add(new Property(1,"Mediterranean Avenue", 60,50, 2,10,30,90,160,250,30, "brown", false, 2));
        allProperties.add(new Property(3, "Baltic Avenue", 60,50, 4, 20, 60, 180, 320, 450,30, "brown", false, 2));

        // Pos 4 == Income Tax, Pos 7 == Chance
        allProperties.add(new RailRoad(5, "Reading Railroad"));
        allProperties.add(new Property(6, "Oriental Avenue", 100,50, 6, 30,90,270,400,550,50, "light blue", false, 3));
        allProperties.add(new Property(7, "Vermont Avenue", 100, 50, 6, 30,90,270,400,550,50, "light blue", false, 3));
        allProperties.add(new Property(9, "Connecticut Avenue",120, 50, 8, 40,100,300,450,600,60, "light blue", false, 3));

        // Pos 10 == Jail
        allProperties.add(new Property(11, "St. Charles Place",140, 100, 10, 50, 150, 450, 625, 750, 70, "pink", false, 3));
        allProperties.add(new Utility(12, "Electric Company"));
        allProperties.add(new Property(13, "States Avenue", 140, 100, 10, 50, 150, 450, 625, 750, 70, "pink", false, 3));
        allProperties.add(new Property(14, "Virginia Avenue", 160,100, 12, 60, 180, 500, 700, 900, 80, "pink", false, 3));

        // Pos 17 == Chest
        allProperties.add(new RailRoad(15, "Pennsylvania Railroad"));
        allProperties.add(new Property(16, "St. James Place", 180, 100, 14,70,200,550,750,950,90, "orange", false, 3));
        allProperties.add(new Property(18, "Tennessee Avenue", 180, 100, 14,70,200,550,750,950,90, "orange", false, 3));
        allProperties.add(new Property(19, "New York Avenue", 200, 100, 16,80,220,600,800,1000,100, "orange", false, 3));

        // Pos 20 == Free Parking, Pos 22 == Chance
        allProperties.add(new Property(21, "Kentucky Avenue", 220, 150, 18, 90, 250, 700, 875, 1050, 110, "red", false, 3));
        allProperties.add(new Property(23, "Indiana Avenue", 220, 150, 18, 90, 250, 700, 875, 1050, 110, "red", false, 3));
        allProperties.add(new Property(24, "Illinois Avenue", 240, 150, 20, 100, 300, 750, 925, 1100, 120, "red", false, 3));
        allProperties.add(new RailRoad(25, "B. & O. Railroad"));

        allProperties.add(new Property(26, "Atlantic Avenue", 260, 150, 22, 110, 330, 800, 975, 1150, 130, "yellow", false, 3));
        allProperties.add(new Property(27, "Vendor Avenue", 260, 150, 22, 110, 330, 800, 975, 1150, 130, "yellow", false, 3));
        allProperties.add(new Utility(28, "Water Works"));
        allProperties.add(new Property(29, "Marvin Gardens", 280, 150, 24, 120, 360, 850, 1025, 1200, 140, "yellow", false, 3));

        // Pos 30 == Go to Jail, Pos 33 == Chest
        allProperties.add(new Property(31, "Pacific Avenue", 300, 200, 26, 130, 390, 900, 1100, 1275, 150, "green", false, 3));
        allProperties.add(new Property(32, "North Carolina Avenue", 300, 200, 26, 130, 390, 900, 1100, 1275, 150, "green", false, 3));
        allProperties.add(new Property(34, "Pennsylvania Avenue", 320, 200, 28, 150, 450, 1000, 1200, 1400, 160, "green", false, 3));
        allProperties.add(new RailRoad(35, "Short Line"));

        // Pos 36 == Chance, Pos 38 == Luxury Tax
        allProperties.add(new Property(37, "Park Place", 350, 200, 35, 175, 500, 1100, 1300, 1500, 175, "blue", false, 2));
        allProperties.add(new Property(39, "Boardwalk", 400, 200, 50, 200, 600, 1400, 1700, 2000, 200, "blue", false, 2));

        // Total of 40 tiles
    }

    // Drawing a card and putting on end of list
    public Card drawChestCard(){
        Card drawn = allCommunityCards.get(0);
        allCommunityCards.remove(0);
        allCommunityCards.add(drawn);
        return drawn;
    }

    public Card drawChanceCard(){
        Card drawn = allChanceCards.get(0);
        allChanceCards.remove(0);
        allChanceCards.add(drawn);
        return drawn;
    }
}