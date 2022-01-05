package pokemon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Pokemon extends Application {

    ArrayList<Integer> tileNum = new ArrayList<>(Arrays.asList(
                0,1,2,3,4,5,6,7,8,9,
                0,1,2,3,4,5,6,7,8,9
    ));
    ArrayList<WritableImage> tileSet;
    ArrayList<Image> imageTestSet;
    String direction = "DOWN";
    ImageView tileSprite;
    boolean walk = false;
    boolean run = false;
    int Xspeed = 0;
    int Yspeed = 0;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        //player sprites are 22 pixels tall, 16 pixels wide,
        //3 pixel buffer vertically, 2 pixels horizontally
        //standstill sprites are 1 pixel higher
        //tiles are 16 pixels square
        //current multiplyer is 4x

        StackPane root = new StackPane();
        GridPane test = new GridPane();

        test.setHgap(48);
        test.setVgap(48);
        test.setMinSize(800, 640);

        Image tilemap = new Image(new FileInputStream(".\\images\\Tileset2.png"));
        WritableImage tileFilter = new WritableImage((int)tilemap.getWidth(), (int)tilemap.getHeight());
        PixelWriter tilePixelWriter = tileFilter.getPixelWriter();
        ridBackground(tilePixelWriter, tilemap);
        tileSet = createTileDatabase(tileFilter);

        int x = 0;

        /*int x = 0;
        int y = 0;
        for (Integer i : tileNum) {
            tileSprite = new ImageView(tileSet.get(i));
            tileSprite.setScaleX(4);
            tileSprite.setScaleY(4);
            test.add(tileSprite, x, y);

            x++;
            if (x > 9) {
                x = 0;
                y++;
            }
        }*/

        Image sprite = new Image(new FileInputStream(".\\images\\Spritev2.png"));
        WritableImage spriteFilter = new WritableImage((int) sprite.getWidth(), (int) sprite.getHeight());
        PixelWriter spritePixelWriter = spriteFilter.getPixelWriter();
        ridBackground(spritePixelWriter, sprite);
        ImageView visableSprite = new ImageView(spriteFilter);

        root.getChildren().add(test);
        root.getChildren().add(visableSprite);

        //alignCamera(root, visableSprite, 6, 2);
        Scene scene = new Scene(root, 800, 640);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (checkAlign64(test)) {
                    switch (event.getCode()) {
                        case UP:
                            walk = true;
                            direction = "UP";
                            break;
                        case DOWN:
                            walk = true;
                            direction = "DOWN";
                            break;
                        case LEFT:
                            walk = true;
                            direction = "LEFT";
                            break;
                        case RIGHT:
                            walk = true;
                            direction = "RIGHT";
                            break;
                        case PERIOD:
                            //add sprite selector
                        default:
                    }
                }

                if (event.getCode() == KeyCode.SHIFT) {
                    run = true;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().toString().equals(direction)) {
                    walk = false;
                }

                if (event.getCode() == KeyCode.SHIFT) {
                    run = false;
                }
            }
        });

        AnimationTimer backGround = new AnimationTimer() {
            long nanot = System.nanoTime();

            @Override
            public void handle(long tLast) {
                double t = (tLast - nanot) / 225000000;
                int runMult = 1;

                if (walk && checkAlign64(test)) {
                    switch (direction) {
                        case "RIGHT":
                            Xspeed = -1;
                            Yspeed = 0;
                            break;
                        case "LEFT":
                            Xspeed = 1;
                            Yspeed = 0;
                            break;
                        case "UP":
                            Xspeed = 0;
                            Yspeed = 1;
                            break;
                        case "DOWN":
                            Xspeed = 0;
                            Yspeed = -1;
                            break;
                    }
                }
                else if (checkAlign64(test)) {
                    Xspeed = 0;
                    Yspeed = 0;
                    walkAnimation(visableSprite, 1);
                }
                else {
                    walkAnimation(visableSprite, t);
                }

                if (run && walk && checkAlign4(test)){
                    runMult = 2;
                    runAnimation(visableSprite, t*2);
                }
                else if (run && checkAlign64(test)){
                    walkAnimation(visableSprite, 1);
                }
                else if (run && checkAlign4(test)){
                    runMult = 2;
                    runAnimation(visableSprite, t*2);
                }
                test.setTranslateX(test.getTranslateX() + Xspeed * runMult * 2);
                test.setTranslateY(test.getTranslateY() + Yspeed * runMult * 2);
            }
        };

        backGround.start();
        primaryStage.setTitle("Pokemon: Harmony");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void alignCamera(StackPane root, ImageView visableSprite, int x, int y){
        visableSprite.setTranslateX(-64*x+32);
        visableSprite.setTranslateY(-64*y+22);

        root.setLayoutX(64*x-32);
        root.setLayoutY(64*y-22);
    }

    public boolean checkAlign64(GridPane test){
        return (test.getTranslateY() % 64 == 0.0 && test.getTranslateX() % 64 == 0.0);
    }

    public boolean checkAlign4(GridPane test){
        return (test.getTranslateY() % 4 == 0.0 && test.getTranslateX() % 4 == 0.0);
    }

    public void walkAnimation(ImageView visableSprite, double t){
        moveAnimation(visableSprite, t, 0);
    }

    public void runAnimation(ImageView visableSprite, double t){
        moveAnimation(visableSprite, t, 192);
    }

    public void moveAnimation(ImageView visableSprite, double t, int x) {
        if (visableSprite.getScaleX() == -1) {
            visableSprite.setScaleX(1);
        }

        switch (direction) {
            case "DOWN":
                moveGeneral(visableSprite, t, x, 0);
                break;
            case "UP":
                moveGeneral(visableSprite, t, x, 172);
                break;
            case "LEFT":
                moveGeneral(visableSprite, t, x, 92);
                break;
            case "RIGHT":
                visableSprite.setScaleX(-1);
                moveGeneral(visableSprite, t, x, 92);
                break;
        }
    }

    public void moveGeneral(ImageView visableSprite, double t, int x, int y) {
        switch ((int) (t % 4)) {
            case 0:
                visableSprite.setViewport(new Rectangle2D(4 + x, y, 64, 84));
                break;
            case 1:
                visableSprite.setViewport(new Rectangle2D(68 + x, y, 64, 84));
                break;
            case 2:
                visableSprite.setViewport(new Rectangle2D(132 + x, y, 64, 84));
                break;
            case 3:
                visableSprite.setViewport(new Rectangle2D(68 + x, y, 64, 84));
                break;
        }
    }

    public void ridBackground(PixelWriter spritePixelWriter, Image sprite) {
        Color firstPixel = sprite.getPixelReader().getColor(0, 0);

        for (int x = 0; x < (int) sprite.getWidth(); x++) {
            for (int y = 0; y < (int) sprite.getHeight(); y++) {
                Color currentPixel = sprite.getPixelReader().getColor(x, y);
                if (currentPixel.equals(firstPixel)) {
                    spritePixelWriter.setColor(x, y, Color.rgb(255, 0, 0, 0));
                }
                else {
                    spritePixelWriter.setColor(x, y, sprite.getPixelReader().getColor(x, y));
                }
            }
        }
    }

    public ArrayList<WritableImage> createTileDatabase(WritableImage tileFilter) {
        ArrayList<WritableImage> cool = new ArrayList<>();

        for (int x = 0; x < (int) tileFilter.getWidth() - 17; x += 17) {
            for (int y = 0; y < (int) tileFilter.getHeight() - 17; y += 17) {
                WritableImage u = new WritableImage(16, 16);
                PixelWriter v = u.getPixelWriter();
                for (int xOffset = 0; xOffset < u.getWidth(); xOffset++) {
                    for (int yOffset = 0; yOffset < u.getHeight(); yOffset++) {
                        v.setColor(xOffset, yOffset, tileFilter.getPixelReader().getColor(x + xOffset+1, y + yOffset+1));
                    }
                }

                cool.add(u);
            }
        }
        return cool;
    }
}
