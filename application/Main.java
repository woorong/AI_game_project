package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import userdata.KeyPolling;

//import java.awt.*;
//import java.util.Hashtable;

import static javafx.geometry.Pos.CENTER;
import static javafx.scene.layout.CornerRadii.*;
//import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.TRANSPARENT;

//gui imports

public class Main extends Application {
    Font font1 = Font.font("Courier New", FontWeight.BOLD, 35);
    Font font4 = Font.font("Courier New", FontWeight.BOLD, 32);
    Font font2 = Font.font("Courier New", FontWeight.BOLD, 25);
    Font font3 = Font.font("Courier New", FontWeight.BOLD, 55);
    
    String[] colours = {"-fx-background-color: #7e00d9", "-fx-background-color: #0086ff", "-fx-background-color: #00c987", "-fx-background-color: #ff008e", "-fx-background-color: #eaff00"};
    static int[] colours_of_players = {0, 1, 2, 3, 4};
    
    String[] difficulties = {"No bot", "Easy", "Medium", "Hard"};
    int[] difficulties_of_bots = {1, 1, 1, 1};


    public static void main(String[] args) {
        launch(args);
    }
    
    public static int[] getColoursOfPlayers() {
    	return colours_of_players;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
		 /*try {
				ViewManager manager = new ViewManager();
				primaryStage = manager.getMainStage();
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		 */


        DropShadow dropshadow = shadow();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());



        Button button1 = createGameSceneSettings(dropshadow);
        Button button2 = aboutButtonSettings(dropshadow);
        Button button3 = settingButtonSettings(dropshadow);
        Button button4 = exitButtonSettings(dropshadow);
        Button buttonResume = resumeButton(dropshadow);
        Button buttonAbout = about2Button(dropshadow);
        Button buttonSettings = settings2Button(dropshadow);
        Button buttonPause = pauseGameButton(dropshadow);


        Scene mainMenuScene = firstMenu(primaryStage, dropshadow, buttonPause, button1, button2, button3, button4);
        Scene playScene = createPlayScene(primaryStage, dropshadow, mainMenuScene);
        Scene aboutScene = createAboutScene(primaryStage, dropshadow, mainMenuScene);
        Scene settingScene = createSettingScene(primaryStage, dropshadow, mainMenuScene);
        Scene pauseScene = createPauseScene(primaryStage, dropshadow, buttonResume, buttonAbout, buttonSettings);
        //Scene winScene = createWinScene(primaryStage, dropshadow);
        //Scene loseScene = createLoseScene(primaryStage, dropshadow);


        button1.setOnAction(e -> {
            primaryStage.setScene(playScene);
            primaryStage.show();
        });

        button2.setOnAction(e -> {
            primaryStage.setScene(aboutScene);
            primaryStage.show();
        });

        button3.setOnAction(e -> {
            primaryStage.setScene(settingScene);
            primaryStage.show();
        });

        button4.setOnAction(e -> {
            primaryStage.close();
        });

        buttonPause.setOnAction(e -> {
            primaryStage.setScene(pauseScene);
            primaryStage.show();
        });

        buttonAbout.setOnAction(e -> {
            primaryStage.setScene(aboutScene);
            primaryStage.show();
        });

        buttonSettings.setOnAction(e -> {
            primaryStage.setScene(settingScene);
        });


        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }



    private Button createGameSceneSettings(DropShadow dropShadow3) {
        Button button1 = new Button("Play");
        button1.setFont(font1);
        button1.setWrapText(true);
        button1.setMaxSize(400, 200);
        button1.setEffect(dropShadow3);
        button1.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");

        return button1;
    }

    private Button aboutButtonSettings (DropShadow dropShadow3) {
        Button button2 = new Button("About");
        button2.setFont(font1);
        button2.setWrapText(true);
        button2.setMaxSize(400, 200);
        button2.setEffect(dropShadow3);
        button2.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");

        return button2;
    }

    private Button settingButtonSettings (DropShadow dropShadow3) {
        Button button3 = new Button("Settings");
        button3.setFont(font1);
        button3.setWrapText(true);
        button3.setMaxSize(400, 200);
        button3.setEffect(dropShadow3);
        button3.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");


        return button3;
    }

    private Button exitButtonSettings (DropShadow dropShadow3) {
        Button button4 = new Button("Quit");
        button4.setFont(font1);
        button4.setWrapText(true);
        button4.setMaxSize(400, 200);
        button4.setEffect(dropShadow3);
        button4.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");


        return button4;
    }

    //gui menu part

    private DropShadow shadow (){
        DropShadow dropShadow3 = new DropShadow();
        dropShadow3.setRadius(10);
        dropShadow3.setOffsetX(5);
        dropShadow3.setOffsetY(5);
        dropShadow3.setColor(Color.web("#97839C"));

        return dropShadow3;
    }

    private Button pauseGameButton (DropShadow dropShadow3) {
        Button buttonPause = new Button();
        buttonPause.setText(" || ");
        buttonPause.setStyle("-fx-background-color: #ffae6a; -fx-textfill: #000000;");
        buttonPause.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        buttonPause.setWrapText(true);
        buttonPause.setAlignment(Pos.CENTER);
        buttonPause.setMinSize(120, 100);
        buttonPause.setEffect(dropShadow3);


        return buttonPause;
    }


    private Button resumeButton (DropShadow dropShadow3) {
        Button buttonResume = new Button("Resume");
        buttonResume.setFont(font1);
        buttonResume.setWrapText(true);
        buttonResume.setMaxSize(400, 200);
        buttonResume.setEffect(dropShadow3);
        buttonResume.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");


        return buttonResume;
    }

    private Button about2Button (DropShadow dropShadow3) {
        Button buttonAbout = new Button("About");
        buttonAbout.setFont(font1);
        buttonAbout.setWrapText(true);
        buttonAbout.setMaxSize(400, 200);
        buttonAbout.setEffect(dropShadow3);
        buttonAbout.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");


        return buttonAbout;
    }

    private Button settings2Button (DropShadow dropShadow3) {
        Button buttonSettings = new Button("Settings");
        buttonSettings.setFont(font1);
        buttonSettings.setWrapText(true);
        buttonSettings.setMaxSize(400, 200);
        buttonSettings.setEffect(dropShadow3);
        buttonSettings.setStyle("-fx-background-color: #ffc177; -fx-text-fill: BLACK;");


        return buttonSettings;
    }

    private Scene createPauseScene(Stage primaryStage , DropShadow dropShadow3, Button buttonResume, Button buttonAbout, Button buttonSettings){

        Label labelPause = new Label("Game Paused");
        labelPause.setTextFill(Color.MAROON);
        labelPause.setFont(Font.font("Courier New", FontWeight.BOLD, 70));
        labelPause.setAlignment(Pos.CENTER);

        GridPane gridPause = new GridPane();
        gridPause.setBackground(Background.EMPTY);
        gridPause.setAlignment(Pos.CENTER);
        gridPause.setVgap(50);
        gridPause.add(labelPause, 5, 0);
        //gridPause.setHalignment(labelPause, HPos.CENTER);
        gridPause.add(buttonResume, 5, 2);
        //gridPause.setHalignment(buttonResume, HPos.CENTER);
        gridPause.add(buttonAbout, 5, 3);
        //gridPause.setHalignment(buttonAbout, HPos.CENTER);
        gridPause.add(buttonSettings, 5, 4);
        //gridPause.setHalignment(buttonSettings, HPos.CENTER);


        Rectangle rectanglePause = new Rectangle();
        rectanglePause.setArcWidth(35.0);
        rectanglePause.setArcHeight(35.0);
        rectanglePause.setWidth(600.0f);
        rectanglePause.setHeight(600.0f);
        rectanglePause.setFill(Color.PEACHPUFF);
        rectanglePause.setEffect(dropShadow3);

        GridPane gridRecPlay = new GridPane();
        gridRecPlay.setStyle("-fx-background-color: rgba(110,37,0,0.56)");
        gridRecPlay.setMinSize(500, 500);
        gridRecPlay.setPadding(new Insets(250, 180, 200, 185));
        gridRecPlay.setAlignment(CENTER);
        gridRecPlay.add(rectanglePause, 1, 1);

        StackPane stackPause = new StackPane(gridRecPlay, gridPause);

        Scene pauseScene = new Scene(stackPause);



        return pauseScene;
    }


    private Scene createWinScene(Stage primaryStage, DropShadow dropshadow3){

        Label winlabel = new Label("Congratulations!");
        winlabel.setFont(font3);
        winlabel.setAlignment(CENTER);

        Label winlabel2 = new Label("You've won!");
        winlabel2.setFont(font3);
        winlabel2.setAlignment(CENTER);

        Rectangle rectangleWin = new Rectangle();
        rectangleWin.setArcWidth(35.0);
        rectangleWin.setArcHeight(35.0);
        rectangleWin.setWidth(600.0f);
        rectangleWin.setHeight(600.0f);
        rectangleWin.setFill(Color.PEACHPUFF);
        rectangleWin.setEffect(dropshadow3);

        GridPane gridRecPlay = new GridPane();
        gridRecPlay.setStyle("-fx-background-color: rgba(255,186,177,0.56)");
        gridRecPlay.setMinSize(500, 500);
        gridRecPlay.setPadding(new Insets(250, 180, 200, 185));
        gridRecPlay.setAlignment(CENTER);
        gridRecPlay.add(rectangleWin, 1, 1);

        GridPane gridWin = new GridPane();
        gridWin.setBackground(Background.EMPTY);
        gridWin.setAlignment(Pos.CENTER);
        gridWin.setVgap(50);
        gridWin.add(winlabel, 5, 0);
        gridWin.add(winlabel2, 5, 3);

        StackPane stackWin = new StackPane(gridRecPlay, gridWin);

        Scene winScene = new Scene(stackWin);



        return winScene;

    }


    private Scene createLoseScene(Stage primaryStage, DropShadow dropshadow3){

        Label loselabel = new Label("Oh :( ");
        loselabel.setFont(font3);
        loselabel.setAlignment(CENTER);

        Label loselabel2 = new Label("You've lost this time.");
        loselabel2.setFont(font3);
        loselabel2.setAlignment(CENTER);

        Rectangle rectangleLose = new Rectangle();
        rectangleLose.setArcWidth(35.0);
        rectangleLose.setArcHeight(35.0);
        rectangleLose.setWidth(800.0f);
        rectangleLose.setHeight(600.0f);
        rectangleLose.setFill(Color.PEACHPUFF);
        rectangleLose.setEffect(dropshadow3);

        GridPane gridRecPlay = new GridPane();
        gridRecPlay.setStyle("-fx-background-color: rgba(202,171,168,0.56)");
        gridRecPlay.setMinSize(500, 500);
        gridRecPlay.setPadding(new Insets(250, 180, 200, 185));
        gridRecPlay.setAlignment(CENTER);
        gridRecPlay.add(rectangleLose, 1, 1);

        GridPane gridWin = new GridPane();
        gridWin.setBackground(Background.EMPTY);
        gridWin.setAlignment(Pos.CENTER);
        gridWin.setVgap(50);
        gridWin.add(loselabel, 5, 0);
        gridWin.add(loselabel2, 5, 3);

        StackPane stackLose = new StackPane(gridRecPlay, gridWin);

        Scene loseScene = new Scene(stackLose);



        return loseScene;

    }


    private Scene firstMenu(Stage primaryStage, DropShadow dropShadow3, Button buttonPause, Button button1, Button button2, Button button3, Button button4) {
        primaryStage.setTitle("Game Republic");


        Label label = new Label("Main Menu");
        label.setTextFill(Color.MAROON);
        label.setFont(Font.font("Courier New", FontWeight.BOLD, 75));
        label.setAlignment(Pos.CENTER);

        GridPane gridimage = new GridPane();
        Image image = new Image("/resources/Menu2.png");
        gridimage.getChildren().add(new ImageView(image));


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(50);
        grid.add(label, 5, 0);
        //grid.add(buttonPause,5,1);
        grid.setHalignment(buttonPause, HPos.CENTER);
        grid.add(button1, 5, 2);
        grid.setHalignment(button1, HPos.CENTER);
        grid.add(button2, 5, 3);
        grid.setHalignment(button2, HPos.CENTER);
        grid.add(button3, 5, 4);
        grid.setHalignment(button3, HPos.CENTER);
        grid.add(button4, 5, 5);
        grid.setHalignment(button4, HPos.CENTER);


        Rectangle rectangleMain = new Rectangle();
        rectangleMain.setArcWidth(35.0);
        rectangleMain.setArcHeight(35.0);
        rectangleMain.setWidth(600.0f);
        rectangleMain.setHeight(800.0f);
        rectangleMain.setFill(Color.PEACHPUFF);
        rectangleMain.setEffect(dropShadow3);

        GridPane gridRecPlay = new GridPane();
        gridRecPlay.setMinSize(500, 500);
        gridRecPlay.setPadding(new Insets(250, 180, 200, 185));
        gridRecPlay.setAlignment(CENTER);
        gridRecPlay.add(rectangleMain, 1, 1);

        StackPane stack = new StackPane(gridimage, gridRecPlay, grid);

        Scene scene = new Scene(stack);
        return scene;
    }


    private Scene createPlayScene (Stage primaryStage, DropShadow dropShadow3, Scene mainMenuScene){

        GridPane gridimage1 = new GridPane();
        Image image1 = new Image("/resources/Menu2.png");
        gridimage1.getChildren().add(new ImageView(image1));
        
        //Hashtable<String, String> my_dict = new Hashtable<String, String>();
        //my_dict.put("Blue", "-fx-background-color: #0086ff");
        
        

        Label label1 = new Label("Player");
        label1.setTextFill(Color.MAROON);
        label1.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        label1.setAlignment(Pos.CENTER);

        Label label2 = new Label("Color");
        label2.setTextFill(Color.MAROON);
        label2.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        label2.setAlignment(Pos.CENTER);

        Label label3 = new Label("Dificulty");
        label3.setTextFill(Color.MAROON);
        label3.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        label3.setAlignment(Pos.CENTER);




        Button buttonp1 = new Button("PC");
        buttonp1.setFont(font4);
        buttonp1.setAlignment(Pos.CENTER);
        buttonp1.setWrapText(true);
        buttonp1.setMaxSize(210, 80);
        buttonp1.setEffect(dropShadow3);
        buttonp1.setStyle("-fx-background-color: rgba(255,250,155,0.91); -fx-text-fill: BLACK;");

        Button buttonp2 = new Button("AI Bot 1");
        buttonp2.setFont(font4);
        buttonp2.setAlignment(Pos.CENTER);
        buttonp2.setWrapText(true);
        buttonp2.setMaxSize(210, 80);
        buttonp2.setEffect(dropShadow3);
        buttonp2.setStyle("-fx-background-color: #ffd600; -fx-text-fill: BLACK;");

        Button buttonp3 = new Button("AI Bot 2");
        buttonp3.setFont(font4);
        buttonp3.setAlignment(Pos.CENTER);
        buttonp3.setWrapText(true);
        buttonp3.setMaxSize(210, 80);
        buttonp3.setEffect(dropShadow3);
        buttonp3.setStyle("-fx-background-color: #ffd600; -fx-text-fill: BLACK;");

        Button buttonp4 = new Button("AI Bot 3");
        buttonp4.setFont(font4);
        buttonp4.setAlignment(Pos.CENTER);
        buttonp4.setWrapText(true);
        buttonp4.setMaxSize(210, 80);
        buttonp4.setEffect(dropShadow3);
        buttonp4.setStyle("-fx-background-color: #ffd600; -fx-text-fill: BLACK;");

        Button buttonp5 = new Button("AI Bot 4");
        buttonp5.setFont(font4);
        buttonp5.setAlignment(Pos.CENTER);
        buttonp5.setWrapText(true);
        buttonp5.setMaxSize(210, 80);
        buttonp5.setEffect(dropShadow3);
        buttonp5.setStyle("-fx-background-color: #ffd600; -fx-text-fill: BLACK;");

        Button buttonBack3 = new Button("Back");
        buttonBack3.setStyle("-fx-background-color: #ffd0b5; -fx-textfill: #000000;");
        buttonBack3.setFont(font1);
        buttonBack3.setWrapText(true);
        buttonBack3.setMinSize(120, 70);
        buttonBack3.setEffect(dropShadow3);



        Button buttonC1 = new Button();
        buttonC1.setStyle("-fx-background-color: #7e00d9");
        buttonC1.setMinSize(100,70);
        buttonC1.setEffect(dropShadow3);

        Button buttonC2 = new Button();
        buttonC2.setStyle("-fx-background-color: #0086ff");
        buttonC2.setMinSize(100,70);
        buttonC2.setEffect(dropShadow3);

        Button buttonC3 = new Button();
        buttonC3.setStyle("-fx-background-color: #00c987");
        buttonC3.setMinSize(100,70);
        buttonC3.setEffect(dropShadow3);

        Button buttonC4 = new Button();
        buttonC4.setStyle("-fx-background-color: #ff008e");
        buttonC4.setMinSize(100,70);
        buttonC4.setEffect(dropShadow3);

        Button buttonC5 = new Button();
        buttonC5.setStyle("-fx-background-color: #eaff00");
        buttonC5.setMinSize(100,70);
        buttonC5.setEffect(dropShadow3);


        Button buttonD1 = new Button("Easy");
        buttonD1.setFont(font4);
        buttonD1.setAlignment(Pos.CENTER);
        buttonD1.setWrapText(true);
        buttonD1.setMaxSize(210, 80);
        buttonD1.setEffect(dropShadow3);
        buttonD1.setStyle("-fx-background-color: #ffae6a; -fx-text-fill: BLACK;");

        Button buttonD2 = new Button("Easy");
        buttonD2.setFont(font4);
        buttonD2.setAlignment(Pos.CENTER);
        buttonD2.setWrapText(true);
        buttonD2.setMaxSize(210, 80);
        buttonD2.setEffect(dropShadow3);
        buttonD2.setStyle("-fx-background-color: #ffae6a; -fx-text-fill: BLACK;");

        Button buttonD3 = new Button("Easy");
        buttonD3.setFont(font4);
        buttonD3.setAlignment(Pos.CENTER);
        buttonD3.setWrapText(true);
        buttonD3.setMaxSize(210, 80);
        buttonD3.setEffect(dropShadow3);
        buttonD3.setStyle("-fx-background-color: #ffae6a; -fx-text-fill: BLACK;");

        Button buttonD4 = new Button("Easy");
        buttonD4.setFont(font4);
        buttonD4.setAlignment(Pos.CENTER);
        buttonD4.setWrapText(true);
        buttonD4.setMaxSize(210, 80);
        buttonD4.setEffect(dropShadow3);
        buttonD4.setStyle("-fx-background-color: #ffae6a; -fx-text-fill: BLACK;");

        Button buttonStart = new Button("Start");
        buttonStart.setFont(font4);
        buttonStart.setAlignment(Pos.CENTER);
        buttonStart.setWrapText(true);
        buttonStart.setMinSize(220, 120);
        buttonStart.setEffect(dropShadow3);
        buttonStart.setStyle("-fx-background-color: #00f0ff; -fx-text-fill: BLACK;");



        Rectangle rectangle1 = new Rectangle();
        rectangle1.setArcWidth(35.0);
        rectangle1.setArcHeight(35.0);
        rectangle1.setWidth(950.0f);
        rectangle1.setHeight(900.0f);
        rectangle1.setFill(Color.PEACHPUFF);
        rectangle1.setEffect(dropShadow3);

        GridPane gridRec1 = new GridPane();
        gridRec1.setMinSize(300, 500);
        gridRec1.setPadding(new Insets(50, 180, 100, 450));
        gridRec1.setAlignment(Pos.TOP_LEFT);
        gridRec1.add(rectangle1, 1, 1);


        GridPane gridplay = new GridPane();
        gridplay.setPadding(new Insets(60, 280, 60, 0));
        gridplay.setAlignment(Pos.CENTER);
        gridplay.setVgap(30);
        gridplay.setHgap(100);
        //grid.setBackground(Background );
        gridplay.add(buttonBack3, 0, 0);
        gridplay.add(label1,1,0);
        gridplay.add(label2,2,0);
        gridplay.add(label3,3,0);
        gridplay.add(buttonp1, 1, 2);
        gridplay.add(buttonp2, 1, 3);
        gridplay.add(buttonp3, 1, 4);
        gridplay.add(buttonp4, 1, 5);
        gridplay.add(buttonp5, 1, 6);

        gridplay.add(buttonC1, 2, 2);
        gridplay.add(buttonC2, 2, 3);
        gridplay.add(buttonC3, 2, 4);
        gridplay.add(buttonC4, 2, 5);
        gridplay.add(buttonC5, 2, 6);

        gridplay.add(buttonD1, 3, 3);
        gridplay.add(buttonD2, 3, 4);
        gridplay.add(buttonD3, 3, 5);
        gridplay.add(buttonD4, 3, 6);
        gridplay.add(buttonStart,3, 9);



        gridplay.setHalignment(label1, HPos.CENTER);
        gridplay.setHalignment(label2, HPos.CENTER);
        gridplay.setHalignment(label3, HPos.CENTER);

        gridplay.setHalignment(buttonp1, HPos.CENTER);
        gridplay.setHalignment(buttonp2, HPos.CENTER);
        gridplay.setHalignment(buttonp3, HPos.CENTER);
        gridplay.setHalignment(buttonp4, HPos.CENTER);
        gridplay.setHalignment(buttonp5, HPos.CENTER);

        gridplay.setHalignment(buttonC1, HPos.CENTER);
        gridplay.setHalignment(buttonC2, HPos.CENTER);
        gridplay.setHalignment(buttonC3, HPos.CENTER);
        gridplay.setHalignment(buttonC4, HPos.CENTER);
        gridplay.setHalignment(buttonC5, HPos.CENTER);

        gridplay.setHalignment(buttonD1, HPos.CENTER);
        gridplay.setHalignment(buttonD2, HPos.CENTER);
        gridplay.setHalignment(buttonD3, HPos.CENTER);
        gridplay.setHalignment(buttonD4, HPos.CENTER);
        gridplay.setHalignment(buttonStart, HPos.CENTER);


        buttonBack3.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.show();
        });

        BackgroundFill background1 = new BackgroundFill(Color.CRIMSON, EMPTY, Insets.EMPTY);
        Background background = new Background(background1);

        buttonC1.setOnAction(e -> {
            buttonC1.setBackground(background);

        });


        StackPane stack1 = new StackPane(gridimage1, gridRec1, gridplay);

        Scene scene1 = new Scene(stack1, 200, 100);
        
        buttonC1.setOnAction(e -> {
        	colours_of_players[0] = (colours_of_players[0] + 1) % 5;
        	buttonC1.setStyle(colours[colours_of_players[0]]);
        });
        
        buttonC2.setOnAction(e -> {
        	colours_of_players[1] = (colours_of_players[1] + 1) % 5;
        	buttonC2.setStyle(colours[colours_of_players[1]]);
        });
        
        buttonC3.setOnAction(e -> {
        	colours_of_players[2] = (colours_of_players[2] + 1) % 5;
        	buttonC3.setStyle(colours[colours_of_players[2]]);
        });
        
        buttonC4.setOnAction(e -> {
        	colours_of_players[3] = (colours_of_players[3] + 1) % 5;
        	buttonC4.setStyle(colours[colours_of_players[3]]);
        });
        
        buttonC5.setOnAction(e -> {
        	colours_of_players[4] = (colours_of_players[4] + 1) % 5;
        	buttonC5.setStyle(colours[colours_of_players[4]]);
        });
        
        buttonD1.setOnAction(e -> {
        	difficulties_of_bots[0] = (difficulties_of_bots[0] + 1) % 4;
        	buttonD1.setText(difficulties[difficulties_of_bots[0]]);
        });
        
        buttonD2.setOnAction(e -> {
        	difficulties_of_bots[1] = (difficulties_of_bots[1] + 1) % 4;
        	buttonD2.setText(difficulties[difficulties_of_bots[1]]);
        });
        
        buttonD3.setOnAction(e -> {
        	difficulties_of_bots[2] = (difficulties_of_bots[2] + 1) % 4;
        	buttonD3.setText(difficulties[difficulties_of_bots[2]]);
        });
        
        buttonD4.setOnAction(e -> {
        	difficulties_of_bots[3] = (difficulties_of_bots[3] + 1) % 4;
        	buttonD4.setText(difficulties[difficulties_of_bots[3]]);
        });
		
        buttonStart.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
                Scene gameScene = new Scene(root);

                KeyPolling.getInstance().pollScene(gameScene);

                primaryStage.setScene(gameScene);
                primaryStage.setTitle("Demo");

                //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/EdenCodingIcon.png")));

                primaryStage.setScene(gameScene);

                primaryStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        buttonBack3.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
        });
        
        

        return scene1;
    }



    private Scene createAboutScene (Stage primaryStage, DropShadow dropShadow3, Scene mainMenuScene) {

        Label label3 = new Label("About");
        label3.setTextFill(Color.MAROON);
        label3.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        label3.setAlignment(CENTER);

        GridPane gridimage2 = new GridPane();
        Image image2 = new Image("/resources/Menu2.png");
        gridimage2.getChildren().add(new ImageView(image2));

        Rectangle rectangle = new Rectangle();
        rectangle.setArcWidth(35.0);
        rectangle.setArcHeight(35.0);
        rectangle.setWidth(765.0f);
        rectangle.setHeight(710.0f);
        rectangle.setFill(Color.PEACHPUFF);
        rectangle.setEffect(dropShadow3);

        Text aboutext = new Text("Instrusctions to the game to be added here...");
        aboutext.setFont(font2);
        aboutext.setStyle(String.valueOf(Color.BLACK));

        Button buttonBack = new Button("Back");
        buttonBack.setStyle("-fx-background-color: #ffc177; -fx-textfill: #000000;");
        buttonBack.setAlignment(CENTER);
        buttonBack.setFont(font1);
        buttonBack.setWrapText(true);
        buttonBack.setMinSize(120, 70);
        buttonBack.setEffect(dropShadow3);

        buttonBack.setLayoutX(20.0f);
        buttonBack.setLayoutY(20.0f);

        GridPane gridBack = new GridPane();
        gridBack.setMinSize(500, 500);
        gridBack.setPadding(new Insets(160, 180, 200, 220));
        gridBack.setAlignment(Pos.TOP_LEFT);
        gridBack.setVgap(30);
        gridBack.setHgap(45);
        gridBack.add(label3, 2, 0);
        gridBack.add(buttonBack, 0, 0);
        gridBack.add(aboutext, 2, 2);

        GridPane gridRec = new GridPane();
        gridRec.setMinSize(500, 500);
        gridRec.setPadding(new Insets(150, 180, 200, 400));
        gridRec.setAlignment(Pos.TOP_LEFT);
        gridRec.add(rectangle, 0, 0);

        StackPane stack3 = new StackPane(gridimage2, gridRec, gridBack);

        Scene scene2 = new Scene(stack3);

        buttonBack.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
        });

        return scene2;
    }



    private Scene createSettingScene(Stage primaryStage, DropShadow dropShadow3, Scene mainMenuScene){

        GridPane gridimage3 = new GridPane();
        Image image3 = new Image("/resources/Menu2.png");
        gridimage3.getChildren().add(new ImageView(image3));

        Label labeltr = new Label("Settings");
        labeltr.setTextFill(TRANSPARENT);
        labeltr.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        labeltr.setAlignment(CENTER);

        Label label4 = new Label("Settings");
        label4.setTextFill(Color.MAROON);
        label4.setFont(Font.font("Courier New", FontWeight.BOLD, 55));
        label4.setAlignment(CENTER);

        Text musicLabel = new Text("Music");
        musicLabel.setFont(font1);
        Slider musicSlider = new Slider(0, 100, 0);
        musicSlider.setShowTickLabels(true);
        musicSlider.setMaxWidth(Double.MAX_VALUE);
        musicSlider.setMajorTickUnit(10);

        Text soundLabel = new Text("Sounds");
        soundLabel.setFont(font1);
        Slider soundSlider = new Slider(0, 100, 0);
        soundSlider.setShowTickLabels(true);

        soundSlider.setMajorTickUnit(10);

        Text notificationsLabel = new Text("Notifications");
        notificationsLabel.setFont(font1);

        ToggleGroup notif = new ToggleGroup();
        RadioButton On = new RadioButton("On");
        On.setToggleGroup(notif);
        On.setFont(font2);
        RadioButton Off = new RadioButton("Off");
        Off.setToggleGroup(notif);
        Off.setFont(font2);

        Text languageLabel = new Text("Language");
        languageLabel.setFont(font1);

        ChoiceBox languagechoiceBox = new ChoiceBox();
        languagechoiceBox.getItems().addAll("English", "French", "Romanian", "Mandarin Chinese", "Japanese");


        Button buttonOk = new Button("OK");
        buttonOk.setStyle("-fx-background-color: #ffae6a; -fx-textfill: #000000;");
        buttonOk.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        buttonOk.setWrapText(true);
        buttonOk.setAlignment(Pos.CENTER);
        buttonOk.setMinSize(120, 100);
        buttonOk.setEffect(dropShadow3);


        Rectangle rectangleSet = new Rectangle();
        rectangleSet.setArcWidth(35.0);
        rectangleSet.setArcHeight(35.0);
        rectangleSet.setWidth(820.0f);
        rectangleSet.setHeight(700.0f);
        rectangleSet.setFill(Color.PEACHPUFF);
        rectangleSet.setEffect(dropShadow3);

        GridPane gridRecSet = new GridPane();
        gridRecSet.setMinSize(500, 500);
        gridRecSet.setPadding(new Insets(150, 180, 200, 460));
        gridRecSet.setAlignment(Pos.TOP_LEFT);
        gridRecSet.add(rectangleSet, 1, 1);


        GridPane gridPane = new GridPane();

        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(225, 180, 200, 20));
        gridPane.setVgap(46);
        gridPane.setHgap(200);
        gridPane.setAlignment(CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(labeltr, 1, 0);
        gridPane.add(label4, 0, 0);
        gridPane.add(musicLabel, 0, 2);
        gridPane.add(musicSlider, 1, 2);
        gridPane.add(soundLabel, 0, 3);
        gridPane.add(soundSlider, 1, 3);
        gridPane.add(notificationsLabel, 0, 4);
        gridPane.add(On, 1, 4);
        gridPane.add(Off, 1, 4);
        gridPane.setHalignment(Off, HPos.RIGHT);
        gridPane.add(languageLabel, 0, 5);
        gridPane.add(languagechoiceBox, 1, 5);
        gridPane.add(buttonOk, 1, 7);
        gridPane.setHalignment(buttonOk, HPos.RIGHT);

        StackPane stack2 = new StackPane(gridimage3, gridRecSet, gridPane);

        Scene scene2 = new Scene(stack2);

        buttonOk.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
        });

        return scene2;
    }
}
