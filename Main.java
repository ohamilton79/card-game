package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane layout = new StackPane();
        //Set up scene and stage
        primaryStage.setTitle("Card Game");
        primaryStage.setHeight(380);
        primaryStage.setWidth(1000);
        primaryStage.setScene(new Scene(layout));

        //Menu items
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem showLeaderboard = new MenuItem("Show Leaderboard");
        MenuItem newGame = new MenuItem("New Game");
        //Create a new VBox for the menu and window contents
        VBox menuFrame = new VBox(menuBar);
        fileMenu.getItems().addAll(showLeaderboard, newGame);
        menuBar.getMenus().add(fileMenu);

        //Set up action for 'Show leaderboard' button
        showLeaderboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent argo0) {
                //Create a new window to show the leaderboard
                Leaderboard leaderboard = new Leaderboard();
                //Get the leaderboard window VBox
                VBox leaderboardFrame = leaderboard.createLeaderboard();
                //Get the contents of the status variable
                String status = leaderboard.status;
                //If the status variable is not an empty string
                if (!status.equals("")) {
                    //Present the error to the user through a label
                    Label statusLabel = new Label();
                    statusLabel.setText(status);
                    //Add the label to the VBox
                    leaderboardFrame.getChildren().add(statusLabel);

                }
                //Create a new window to display the leaderboard
                Stage leaderboardStage = new Stage();
                //Modify stage
                leaderboardStage.setTitle("Leaderboard");
                leaderboardStage.setScene(new Scene(leaderboardFrame));
                leaderboardStage.show();

            }

        });
        //Set up action for 'New Game' button
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent argo0) {
                VBox gameFrame = new MainFrame().createMainFrame();
                //If a 'game frame' has already been added to the window, it will need to be removed so a new one can be added - in this case the menu frame will have 2 items
                if (menuFrame.getChildren().size() == 2) {
                    menuFrame.getChildren().remove(1);
                }
                menuFrame.getChildren().add(gameFrame);

            }
        });

        //Add the menu VBox to the window
        layout.getChildren().add(menuFrame);

        //Show window
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
