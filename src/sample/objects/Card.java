package sample.objects;

public class Card{
    private final String name;
    private final String message;
    private final int money;
    private final boolean special;

    public Card(String name, String message, int money, boolean special){
        this.name = name;
        this.message = message;
        this. money = money;
        this.special = special;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public int getMoney() {
        return money;
    }

    public boolean isSpecial() {
        return special;
    }

}
