package sample.objects;

public class Property {
    private final int position;
    private final String name;
    private final int tilePrice;
    private final int housePrice;
    private final int initRent;
    private final int rent1;
    private final int rent2;
    private final int rent3;
    private final int rent4;
    private final int rentHotel;
    private final int mortPrice;
    private final String group;
    private final int groupSize;
    private boolean inMort;

    private int numOfHouses;
    private String owner;

    public Property(int position, String name, int tilePrice, int housePrice, int initRent, int rent1, int rent2, int rent3, int rent4, int rentHotel, int mortPrice, String group, boolean inMort, int groupSize){
        this.position = position;
        this.name = name;
        this.tilePrice = tilePrice;
        this.housePrice = housePrice;
        this.initRent = initRent;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.rentHotel = rentHotel;
        this.mortPrice = mortPrice;
        this.group = group;
        this.inMort = inMort;
        this.groupSize = groupSize;

        numOfHouses = 0;
        owner = "";
    }

    // Unmodifiable
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getTilePrice() {
        return tilePrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getInitRent() {
        return initRent;
    }

    public int getRent1() {
        return rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public int getRent3() {
        return rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public int getMortPrice() {
        return mortPrice;
    }

    public String getGroup() {
        return group;
    }

    public int getGroupSize() {
        return groupSize;
    }


    // Modifiable
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setNumOfHouses(int numOfHouses) {
        this.numOfHouses = numOfHouses;
    }

    public int getNumOfHouses() {
        return numOfHouses;
    }

    public boolean isInMort() {
        return inMort;
    }

    public void setInMort(boolean inMort) {
        this.inMort = inMort;
    }


    @Override
    public String toString() {
        return "\nname = \t\t\t\t\t" + name +
                "\nTile price = \t\t\t\t" + tilePrice +
                "\nHouse price = \t\t\t\t" + housePrice +
                "\nRent with no buildings = \t" + initRent +
                "\nRent with 1 building = \t\t" + rent1 +
                "\nRent with 2 buildings = \t\t" + rent2 +
                "\nRent with 3 buildings = \t\t" + rent3 +
                "\nRent with 4 buildings = \t\t" + rent4 +
                "\nRent with Hotel = \t\t\t" + rentHotel +
                "\nValue of Mortgage = \t\t" + mortPrice +
                "\nColor= \t\t\t\t\t" + group;
    }

}

