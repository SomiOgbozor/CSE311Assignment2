public class AlwaysDefect extends Player {
    private PlayerMove lastMove = PlayerMove.DEFECT;
    private int points;

    public AlwaysDefect() {

    }

    public PlayerMove chooseMove(PlayerMove otherMove){
        return lastMove;
    }

    PlayerMove getLastMove() {
        return this.lastMove;
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
