public abstract class Player {
    abstract PlayerMove chooseMove(PlayerMove otherMove);
    abstract PlayerMove getLastMove();
    abstract void setLastMove(PlayerMove lastMove);
    abstract void addPoints(int points);
    abstract int getPoints();
    abstract void resetScore();

}
