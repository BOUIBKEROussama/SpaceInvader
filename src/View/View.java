package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		
		Scene scene = new Scene(root,800,600);
		
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
