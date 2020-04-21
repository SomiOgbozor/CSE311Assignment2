public class Tit4Tat extends Player {
    private PlayerMove lastMove;
    private boolean madeFirstMove;
    private int points;

    public Tit4Tat() {
        this.madeFirstMove = false;
    }


    @Override
    public PlayerMove chooseMove(PlayerMove otherMove) {
        if(!madeFirstMove){
            madeFirstMove = true;
            return PlayerMove.COOPERATE;
        }
        return otherMove != null ? otherMove : PlayerMove.COOPERATE;
    }

    @Override
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
