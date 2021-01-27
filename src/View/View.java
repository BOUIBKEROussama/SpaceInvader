package View;

import java.util.ArrayList;

import Model.Minion;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application {
	
	public ArrayList<Minion> listMinion  = new ArrayList<Minion>();
	
	public Group getItems() {
		Group root = new Group();
		for(int i = 0; i < 10; i++) {
			listMinion.add(new Minion(100+(i*40), 100, 30, 30, 10));
		}
		//listMinion.add(new Minion(40, 40, 10, 10, 10));
		for(Minion e : listMinion) {
			
			Rectangle r = new Rectangle(e.getX(), e.getY(), e.getLongueur(), e.getLargeur());
			root.getChildren().add(r);
		}
		
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		
		
		Scene scene = new Scene(getItems(),800,600);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		primaryStage.centerOnScreen();
		
		new Thread(()->{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
	
	
	public static void main(String[]args) {
		
		
		launch(args);
		
	}
	
}
