package sample.service;

import sample.objects.Player;

public class Movement extends TopOfBoard {

    // Sets all possible movements on basic board... Gets current location, number on cube and returns new locX and locY.
    public int moveX(int x, int y, int roll, Player player){
        int numOfMoves = roll * getTileSize();

        if (y+numOfMoves >= (getTileSize()* getDimension()) && x == getTileSize()* getDimension()) {
            int restRoll = numOfMoves-((getTileSize()* getDimension())-y);
            return x - restRoll;
        }

        if (x + numOfMoves >= (getTileSize()* getDimension()) && y!=getTileSize() && y != getTileSize()* getDimension()){
            return getTileSize()* getDimension();
        }

        if (x + numOfMoves >= (getTileSize()* getDimension()) && y==getTileSize()){
            return getTileSize()* getDimension();
        }

        if (y-numOfMoves <= getTileSize()){
            int restRoll = numOfMoves-y+getTileSize();
            return x + restRoll;
        }

        if (x-numOfMoves <= getTileSize()){
            return getTileSize();
        }


        return x - numOfMoves;
    }

    public int moveY(int x, int y, int roll, Player player){
        int numOfMoves = roll * getTileSize();

        if (y+numOfMoves >= (getTileSize()* getDimension()) && x == getTileSize()* getDimension()) {
            // Get past Go
            player.changeMoney(200);
            return getTileSize() * getDimension();
        }

        if (x + numOfMoves >= (getTileSize()* getDimension()) && y!=getTileSize() && y != getTileSize()* getDimension()){
            int restRoll = numOfMoves-((getTileSize()* getDimension())-x);
            return y + restRoll;
        }

        if (x + numOfMoves >= (getTileSize()* getDimension()) && y==getTileSize()){
            int restRoll = numOfMoves-((getTileSize()* getDimension())-x);
            return y + restRoll;
        }

        if (y-numOfMoves <= getTileSize()){
            return getTileSize();
        }

        if (x-numOfMoves <= getTileSize()){
            int restRoll = numOfMoves-x+getTileSize();
            return y - restRoll;
        }

        return y;
    }
}
