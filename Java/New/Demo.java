/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mwillis
 */
public class Demo extends Application {
    private static final double WINDOW_WIDTH = 500;
    private static final double WINDOW_HEIGHT = 500;
    private static final double SPEED = 200;
    
    private boolean upDown = false;
    private boolean downDown = false;
    private boolean leftDown = false;
    private boolean rightDown = false;
    
    private double clickX;
    private double clickY;
    
    @Override
    public void start(Stage primaryStage) {
        //Image brak = new Image(new File("images/Brak.png").toURI().toString());
        //ImageView brakView = new ImageView(brak);
        ImageView brakView = new ImageView("https://upload.wikimedia.org/wikipedia/en/7/70/Brak_%28character%29.png");
        brakView.setFitHeight(brakView.getImage().getHeight()/2);
        brakView.setPreserveRatio(true);
        
        Text hello = new Text("Hello");
        hello.setFont(new Font("Impact",40));
        hello.setStroke(Color.CHARTREUSE);
        hello.setStrokeWidth(3);
        hello.setFill(Color.AQUA);
        
        StackPane root = new StackPane();
        root.getChildren().add(brakView);
        root.getChildren().add(hello);
        
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getCode())
                {
                    case ESCAPE:
                        System.exit(0);
                        break;
                    case UP:
                        upDown = true;
                        //brakView.setTranslateY(brakView.getTranslateY() - SPEED);
                        break;
                    case DOWN:
                        downDown = true;
                        //brakView.setTranslateY(brakView.getTranslateY() + SPEED);
                        break;
                    case LEFT:
                        leftDown = true;
                        //brakView.setTranslateX(brakView.getTranslateX() - SPEED);
                        break;
                    case RIGHT:
                        rightDown = true;
                        //brakView.setTranslateX(brakView.getTranslateX() + SPEED);
                        break;
                    default:
                }
            }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getCode())
                {
                    case UP:
                        upDown = false;
                        break;
                    case DOWN:
                        downDown = false;
                        break;
                    case LEFT:
                        leftDown = false;
                        break;
                    case RIGHT:
                        rightDown = false;
                        break;
                    default:
                }
            }
        });
        
        brakView.setOnMousePressed(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {
               clickX = event.getX();
               clickY = event.getY();
           }
        });
        
        brakView.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                brakView.setTranslateX(brakView.getTranslateX() + (event.getX() - clickX));
                brakView.setTranslateY(brakView.getTranslateY() + (event.getY() - clickY));
            }
        
        });
        
        AnimationTimer aTimer = new AnimationTimer(){
            long lastNanoTime = System.nanoTime();
            @Override
            public void handle(long nanoTime)
            {
                double t = (nanoTime-lastNanoTime)/1000000000.0;
                //System.out.println(t);
                //brakView.setRotate(brakView.getRotate()+t*180);
                if(upDown)
                {
                    brakView.setTranslateY(brakView.getTranslateY() - (t * SPEED));
                }
                if(downDown)
                {
                    brakView.setTranslateY(brakView.getTranslateY() + (t * SPEED));
                }
                if(leftDown)
                {
                    brakView.setTranslateX(brakView.getTranslateX() - (t * SPEED));
                }
                if(rightDown)
                {
                    brakView.setTranslateX(brakView.getTranslateX() + (t * SPEED));
                }
                lastNanoTime = nanoTime;
            }
        };
        
        aTimer.start();
        
        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
