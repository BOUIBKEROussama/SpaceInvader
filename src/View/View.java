package View;

import java.util.ArrayList;
import java.util.Collections;

import Controller.GlobalController;
import Model.Minion;
import Model.Missile;
import Model.Spaceship;
import Tools.Direction;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class View extends Application {
	private Timeline timeline;
	private AnimationTimer timer;
	private static GlobalController gc;
	
	public static int width = 500;
	public static int height = 500;
	private static Spaceship spaceship;
	public Group getItems() {
		Group root = new Group();
		//listMinion.add(new Minion(40, 40, 10, 10, 10));
		
		Line line1 = new Line(0,0,width,0);
		Line line2 = new Line(width,0,width,height);
		Line line3 = new Line(0, height, width, height);
		Line line4 = new Line(0, 0, 0, height);
		root.getChildren().add(line1);
		root.getChildren().add(line2);
		root.getChildren().add(line3);
		root.getChildren().add(line4);
		
		
		Rectangle player =  new Rectangle(spaceship.getX(), spaceship.getY(), spaceship.getLongueur(), spaceship.getLargeur());
		player.setFill(Color.AQUAMARINE);

		root.getChildren().add(player);
		for(Minion e : gc.getMinions()) {
			Rectangle r = new Rectangle(e.getX(), e.getY(), e.getLongueur(), e.getLargeur());
			root.getChildren().add(r);
		}
		for(Missile missile : gc.getSpaceshipMissiles()) {
			Rectangle r2 = new Rectangle(missile.getX(), missile.getY(), missile.getLargeur(), missile.getLongueur());
			r2.setFill(Color.BLUE);
			root.getChildren().add(r2);
		}
		
		for(Missile missile : gc.getMinionsMissiles()) {
			Rectangle r3 = new Rectangle(missile.getX(), missile.getY(), missile.getLargeur(), missile.getLongueur());
			r3.setFill(Color.RED);
			root.getChildren().add(r3);
		}
		
		
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(getItems(),width,height);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		      @Override
		        public void handle(KeyEvent event) {
		          if (event.getCode()==KeyCode.LEFT) gc.setSpaceshipDirection(Direction.LEFT);
		          if (event.getCode()==KeyCode.RIGHT) gc.setSpaceshipDirection(Direction.RIGHT);
		          if (event.getCode()==KeyCode.SPACE) gc.setSpaceshipShoot();

		        }
		    });
		    scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		      @Override
		        public void handle(KeyEvent event) {
		          if (event.getCode()==KeyCode.LEFT) gc.setSpaceshipOnRelease(Direction.LEFT);
		          if (event.getCode()==KeyCode.RIGHT) gc.setSpaceshipOnRelease(Direction.RIGHT);
		          if (event.getCode()==KeyCode.SPACE) gc.setSpaceshipShootOnRelease();
		          event.consume();
		        }
		    });
		    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        @Override public void handle(WindowEvent event) {
		          gc.stop();
		        }
		      });

		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);

		timer = new AnimationTimer() {
			@Override public void handle(long l) {
			scene.setRoot(getItems());
			}
		};

		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10)));
		timeline.play();
		timer.start();
	}
	
	
	public static void main(String[]args) {
		spaceship = new Spaceship(width/2, height - 40, 30, 30, 3);
		gc = new GlobalController();
		gc.bindSpaceship(spaceship);
		for(int i = 0; i < 4; i++) {
			gc.addMinion(new Minion(100+(i*40), 100, 30, 30, 10));
		}
		
		gc.start();
		launch(args);
		
	}
	
}
