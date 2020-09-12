package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.text.*;

public class CardSet {
    //List of possible colours for each card
    String[] colourList = {"red", "orange", "yellow", "green", "blue", "pink", "purple"};

    //Score and colour array for a card set
    String[] cardColours = {};
    //Create a variable to store the total score of all the cards
    int totalScore = 0;

    public StackPane createCard(String colour, int score) {
        StackPane cardFrame = new StackPane();
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(80);
        rectangle.setWidth(60);
        rectangle.setFill(Paint.valueOf(colour));

        Text lbl1 = new Text();
        lbl1.setText(String.valueOf(score));
        lbl1.setFont(Font.font("Calibri", 20));
        cardFrame.getChildren().addAll(rectangle, lbl1);
        //Set padding around the cardFrame
        cardFrame.setPadding(new Insets(10, 10, 10, 10));
        return cardFrame;
    }
    //Create a set of cards with length n
    public HBox createCardSet(int n) {
        //Create HBox for Player 1's cards
        HBox hbox = new HBox();
        //Modify the list to be of length n to store all the colours of the cards
        cardColours = new String[n];
        for (int i = 0; i < n; i++) {

            Random rand = new Random();
            //Create a new random number based on the number of colours remaining
            int randNumb = rand.nextInt(colourList.length);
            //Select the colour for this card by using the random number as an index
            String currentColour = colourList[randNumb];

            //Generate a NEW random number from 0 to 9 - this is the 'score' of the card
            int randScore = rand.nextInt(10);

            //Add the colour of this card to the array, at the index position i
            cardColours[i] = currentColour;

            //Add this card's score to the total score variable
            totalScore = totalScore + randScore;

            //Create a StackPane object for this card, and add it to the HBox
            StackPane newCardObject = createCard(currentColour, randScore);
            hbox.getChildren().add(newCardObject);
        }

        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    int countColours(String colourChecked) {
        //Create a counter for the number of duplicates
        int colourCount = 0;
        for (String currentColour : cardColours) {

            if (currentColour == colourChecked) {

                colourCount++;
            }
        }

        return colourCount;
    }
    public int getScore() {

        for (String colour : colourList) {

            //Count the number of this colour in this list
            int colourCount = countColours(colour);

            //If there are 2 or 3 cards of the same colour, the total score is increased by 5
            if ((colourCount >= 2) && (colourCount <= 3)) {

                totalScore += 5;
            }
            //If there are 4 or 5 cards of the same colour, the total score is increased by 15
            if ((colourCount >= 4) && (colourCount <= 5)) {

                totalScore += 15;
            }
            //If there are 6 or more cards of the same colour, the total score is increased by 50
            if (colourCount >= 5) {

                totalScore += 50;
            }

        }
        return totalScore;
    }

}
