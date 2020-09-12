package sample;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.io.*;

public class MainFrame {

    public VBox createMainFrame() {
        //Create a main frame for the window contents
        VBox mainVbox = new VBox();
        Label winnerLbl = new Label();
        winnerLbl.setFont(Font.font("Calibri", 24));
        //Create objects for Player 1 and Player 2
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        //Cards for Player 1
        Label player1Lbl = new Label();
        player1Lbl.setText("Player 1 cards:");
        player1Lbl.setFont(Font.font("Calibri", 18));
        CardSet cardSetp1 = new CardSet();
        HBox hboxp1 = cardSetp1.createCardSet(10);
        //Get the score for Player 1's cards
        player1.score = cardSetp1.getScore();

        //Cards for Player 2
        Label player2Lbl = new Label();
        player2Lbl.setText("Player 2 cards:");
        player2Lbl.setFont(Font.font("Calibri", 18));
        CardSet cardSetp2 = new CardSet();
        HBox hboxp2 = cardSetp2.createCardSet(10);
        //Get the score for Player 2's cards
        player2.score = cardSetp2.getScore();
        //Create new variables: one for the winner and the other for the loser
        Player winningPlayer;
        Player losingPlayer;
        //If Player 1's score is more than Player 2's, they are the winner. Else, Player 2 is the winner
        if (player1.score > player2.score) {
            //Update the winner label text
            winnerLbl.setText(player1.name + " is the winner, with a score of " + player1.score);
            winningPlayer = player1;
            losingPlayer = player2;
        } else {

            //Update the winner label text
            winnerLbl.setText(player2.name + " is the winner, with a score of " + player2.score);
            winningPlayer = player2;
            losingPlayer = player1;
        }
        //Get the contents of the leaderboard file
        try {
            //Reference for each line of the file
            String line = "";
            //List for whole leaderboard - max of 10 items
            String[] leaderboardList = new String[10];
            String fileName = "leaderboard.txt";
            FileReader fileReader = new FileReader(fileName);
            //Wrap FileReader in BufferedReader so can be read line by line
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Counter variable
            int count = 0;
            //Counts number of lines in file
            while ((line = bufferedReader.readLine()) != null && count < 10) {
                //Read lines into the leaderboard array
                leaderboardList[count] = line;
                //Increment counter
                count++;
                System.out.println(line);
            }
            //Move each item into a new, condensed list
            String[] condensedLeaderboard = new String[count];
            //Reset counter
            count = 0;
            while (count < condensedLeaderboard.length) {
                System.out.println(count);
                condensedLeaderboard[count] = leaderboardList[count];
                System.out.println(leaderboardList[count]);
                count++;
            }
            //Close file to prevent corruption
            bufferedReader.close();
            //Reset counter
            count = 0;
            //Construct a new list for the new players to add
            String player1Entry = player1.name + "," + player1.score;
            String player2Entry = player2.name + "," + player2.score;
            String[] newPlayers = {player1Entry, player2Entry};

            //Split the lists into two - one containing names and the other containing scores
            int[] scoreList1 = MergeSort.getScoreList(condensedLeaderboard);
            int[] scoreList2 = MergeSort.getScoreList(newPlayers);
            String[] nameList1 = MergeSort.getNameList(condensedLeaderboard);
            String[] nameList2 = MergeSort.getNameList(newPlayers);
            int[] sortedScores = MergeSort.mergeLists(scoreList1, scoreList2);
            String[] sortedNames = MergeSort.getSortedNames(sortedScores, scoreList1, scoreList2, nameList1, nameList2);

            //Begin writing entries to the leaderboard file
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter =  new BufferedWriter(fileWriter);
            //Counter variable for different leaderboard entries
            count = 0;
            while (count < sortedScores.length) {
                //Write the name and score of everyone to the leaderboard file, with a newline character at the end
                String writeString = sortedNames[count] + "," + sortedScores[count] + "\n";
                bufferedWriter.write(writeString);
                count++;
            }
            //Close file to prevent corruption
            bufferedWriter.close();
        }
        catch (FileNotFoundException ex) {
             System.out.println("Error - file not found");
        }

        catch (IOException ex) {
            System.out.println("I/O error");

        }
        //Add each HBox, its label description and the winner label to the VBox
        mainVbox.getChildren().add(player1Lbl);
        mainVbox.getChildren().add(hboxp1);
        mainVbox.getChildren().add(player2Lbl);
        mainVbox.getChildren().add(hboxp2);
        mainVbox.getChildren().add(winnerLbl);

        //Adjust the properties of the main VBox
        mainVbox.setAlignment(Pos.CENTER);

        //Return the main VBox
        return mainVbox;
    }
}
