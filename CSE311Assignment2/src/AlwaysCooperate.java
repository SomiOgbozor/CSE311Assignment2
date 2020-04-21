public class AlwaysCooperate extends Player {
    private PlayerMove lastMove = PlayerMove.COOPERATE;
    private int points;

    public AlwaysCooperate() {

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
