package sample.objects;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.service.TopOfBoard;

import java.util.ArrayList;

public class Player extends TopOfBoard {
    private int id;
    private boolean turn;
    private String name;
    public ImageView figure;
    public int money;
    public Text txt;
    public Text ownedCards;

    public boolean inJail;
    public int outOfJailCard = 0;
    public int timeInJail = 0;
    public ArrayList<Property> ownedProperties = new ArrayList<>();

    // Constructor
    public Player(int id, boolean turn, String name, ImageView figure, int money, Text txt, Text ownedCards) {
        this.id = id;
        this.turn = turn;
        this.name = name;
        this.figure = figure;
        this.money = money;
        this.txt = txt;
        this.ownedCards = ownedCards;

        this.inJail = false;
    }

    // Getters ans setters
    public String getName() {
        return name;
    }

    public void changeMoney(int x){
        this.money = money + x;
    }
}
