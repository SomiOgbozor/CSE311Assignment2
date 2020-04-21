public class Grudger extends Player {
    private PlayerMove lastMove;
    private boolean madeFirstMove;
    private boolean hasDefected;
    private int points;

    public Grudger() {
        this.hasDefected = false;
        this.madeFirstMove = false;
    }

    public PlayerMove chooseMove(PlayerMove otherMove) {
        if(!madeFirstMove){
            madeFirstMove = true;
            return PlayerMove.COOPERATE;
        }
        if (otherMove == PlayerMove.DEFECT){
            hasDefected = true;
        }
        return hasDefected ? PlayerMove.DEFECT : PlayerMove.COOPERATE;
    }

    public PlayerMove getLastMove() {
        return lastMove;
    }

    public void setLastMove(PlayerMove lastMove) {
        this.lastMove = lastMove;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public void resetScore() {
        this.points = 0;
    }
}
