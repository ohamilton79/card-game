package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.*;
import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

public class Leaderboard {

    public ArrayList<String> getLeaderboardRows() {
        ArrayList<String> leaderboardRows = new ArrayList<String>();

        try {
            //Open leaderboard file
            File file = new File("leaderboard.txt");

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                //Append line to leaderboard rows array
                leaderboardRows.add(line);
            }

            //Return the leaderboard rows
            return leaderboardRows;

        } catch (FileNotFoundException e) {
            return null;
        }
    }

    //Add a row to the leaderboard from the usernames and scores stored in the leaderboard file
    public void addRow(VBox mainVBox, String leaderboardEntry) {
        //Separate string at commas to get name and score
        String[] entryProperties = leaderboardEntry.split(",");
        String userName = entryProperties[0];
        String userScore = entryProperties[1];

        //Create new HBox to store both labels
        HBox rowBox = new HBox();
        //Add padding around row so they aren't too close together
        rowBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        //Create labels for each property
        Label nameLabel = new Label();
        Label scoreLabel = new Label();

        nameLabel.setText(userName);
        scoreLabel.setText(userScore);

        //Add spacing between text boxes so they aren't right next to each other
        nameLabel.setPadding(new Insets(0.0, 50.0, 0.0, 0.0));

        //Add labels to row
        rowBox.getChildren().addAll(nameLabel, scoreLabel);

        //Add row to main VBox so it is visible in the window
        mainVBox.getChildren().add(rowBox);
    }

    //Setup global status variable that can be accessed from other classes
   String status = "";

    public VBox createLeaderboard() {
        //Create a main VBox
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        ArrayList<String> leaderboardRows = getLeaderboardRows();
            //If any leaderboard rows were returned, output them in the window
            if (leaderboardRows != null) {
                //Add an initial row with headings
                addRow(mainVBox, "Name,Score");
                //For each entry in the leaderboard, add a new row to the main VBox
                for (String leaderboardEntry : leaderboardRows) {
                    addRow(mainVBox, leaderboardEntry);
                }
            } else {
                status = "The leaderboard file does not yet exist - try playing a game first.";
            }


        //Return the VBox
        return mainVBox;


    }
}
