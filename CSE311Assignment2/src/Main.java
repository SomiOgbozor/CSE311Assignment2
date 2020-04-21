import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static String path = "docs/311_q3_avg_payoff_p50.csv";
    public static void playGame(Player player1, Player player2){
        PlayerMove p1Move = player1.chooseMove(player2.getLastMove());
        PlayerMove p2Move = player2.chooseMove(player1.getLastMove());

        player1.setLastMove(p1Move);
        player2.setLastMove(p2Move);

        if(p1Move.equals(PlayerMove.COOPERATE) && p2Move.equals(PlayerMove.COOPERATE)){
            player1.addPoints(3);
            player2.addPoints(3);
        }
        else if(p1Move.equals(PlayerMove.DEFECT) && p2Move.equals(PlayerMove.DEFECT)){
            player1.addPoints(1);
            player2.addPoints(1);
        }
        else if(p1Move.equals(PlayerMove.DEFECT)){
            player1.addPoints(5);
        }
        else if(p2Move.equals(PlayerMove.DEFECT)){
            player2.addPoints(5);
        }
    }

    public static void main(String[] args) {
        int numPlayers = 100;
        int gameIterations = 5;
        int generations = 20;
        int cutoff = 50;

        double numTit4Tat=0, numGrudger=0, numAlwaysCooperate=0, numAlwaysDefect = 0;
        double tit4TatScore=0, grudgerScore=0, alwaysCooperateScore=0, alwaysDefectScore=0;
        List<Player> listPlayers = new ArrayList<Player>();
        for(int i=0; i < numPlayers; i++){
            if(i % 4 == 0){
                listPlayers.add(new Tit4Tat());
            }
            else if(i % 4 == 1){
                listPlayers.add(new Grudger());
            }
            else if(i % 4 == 2){
                listPlayers.add(new AlwaysCooperate());
            }
            else{
               listPlayers.add(new AlwaysDefect());
            }
        }
        for(int a=1; a <= generations; a++) {
            for (int i = 0; i < listPlayers.size() - 1; i++) {
                for (int j = i + 1; j < listPlayers.size(); j++) {
                    for (int k = 0; k < gameIterations; k++) {
                        playGame(listPlayers.get(i), listPlayers.get(j));
                    }
                }
            }

            listPlayers.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
            for (Player player : listPlayers) {
                if (player instanceof Tit4Tat) {
                    numTit4Tat++;
                    tit4TatScore += player.getPoints();
                } else if (player instanceof Grudger) {
                    numGrudger++;
                    grudgerScore += player.getPoints();
                } else if (player instanceof AlwaysCooperate) {
                    numAlwaysCooperate++;
                    alwaysCooperateScore += player.getPoints();
                } else if (player instanceof AlwaysDefect) {
                    numAlwaysDefect++;
                    alwaysDefectScore += player.getPoints();
                }
                player.resetScore();
            }
            for(int i=0; i < cutoff; i++){
                listPlayers.remove(listPlayers.size() - 1 - i);
            }
            for(int i=0; i < cutoff; i++){
                Player player = listPlayers.get(i);
                if(player instanceof Tit4Tat){
                    Player newPlayer = new Tit4Tat();
                    listPlayers.add(newPlayer);
                }
                else if(player instanceof Grudger){
                    Player newPlayer = new Grudger();
                    listPlayers.add(newPlayer);
                }
                else if(player instanceof AlwaysCooperate){
                    Player newPlayer = new AlwaysCooperate();
                    listPlayers.add(newPlayer);
                }
                else if(player instanceof AlwaysDefect){
                    Player newPlayer = new AlwaysDefect();
                    listPlayers.add(newPlayer);
                }
            }
            try{
                File file = new File(path);
                CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
                DecimalFormat df2 = new DecimalFormat("#.##");
//                String[] entries = {df2.format(numTit4Tat / listPlayers.size()), df2.format(numGrudger / listPlayers.size()), df2.format(numAlwaysCooperate / listPlayers.size()), df2.format(numAlwaysDefect / listPlayers.size())};
                String[] entries = {df2.format(tit4TatScore / (numTit4Tat * ((listPlayers.size() - 1) * gameIterations))), df2.format(grudgerScore / (numGrudger * ((listPlayers.size()- 1) * gameIterations))), df2.format(alwaysCooperateScore / (numAlwaysCooperate * ((listPlayers.size() - 1) * gameIterations))), df2.format(alwaysDefectScore / (numAlwaysDefect * ((listPlayers.size() - 1) * gameIterations)))};
                csvWriter.writeNext(entries);
                csvWriter.close();
            } catch(Exception e){
                e.printStackTrace();
            }

            DecimalFormat df2 = new DecimalFormat("#.##");
//            System.out.println("Gen " + a + ": T4T: " + df2.format(numTit4Tat / listPlayers.size()) + " \tG: " + df2.format(numGrudger / listPlayers.size()) + " \tAC: " + df2.format(numAlwaysCooperate / listPlayers.size()) + " \tAD: " + df2.format(numAlwaysDefect / listPlayers.size()));
//            System.out.println("Gen " + a + ": T4T: " + (tit4TatScore) + " \tG: " + (grudgerScore) + " \tAC: " + (alwaysCooperateScore) + " \tAD: " + (alwaysDefectScore));
            System.out.println("Gen " + a + ": T4T: " + df2.format(tit4TatScore / (numTit4Tat * ((listPlayers.size() - 1) * gameIterations))) + " \tG: " + df2.format(grudgerScore / (numGrudger * ((listPlayers.size()- 1) * gameIterations))) + " \tAC: " + df2.format(alwaysCooperateScore / (numAlwaysCooperate * ((listPlayers.size() - 1) * gameIterations))) + " \tAD: " + df2.format(alwaysDefectScore / (numAlwaysDefect * ((listPlayers.size() - 1) * gameIterations))));
            numTit4Tat = numGrudger = numAlwaysCooperate = numAlwaysDefect = 0;
            tit4TatScore = grudgerScore = alwaysCooperateScore = alwaysDefectScore = 0;
        }
    }
}
