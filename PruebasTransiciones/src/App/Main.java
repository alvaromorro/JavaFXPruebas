package App;
	
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}
	
	public void initRootLayout() {
		try {
			primaryStage.setHeight(600);
			primaryStage.setWidth(800);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			BorderPane root = (BorderPane) loader.load();
			root.getStyleClass().add("borderPane");
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			SampleController sc = loader.getController();
			sc.SetMainApp(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void ChangeScene(String vista){
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),primaryStage.getScene().getRoot());
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	try {
					BorderPane root;
					FXMLLoader loader = new FXMLLoader(getClass().getResource(vista+".fxml"));
					root = (BorderPane) loader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
				} catch (IOException e) {		
					e.printStackTrace();
				}	
		    }
		});
		
		fadeTransition.play();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
