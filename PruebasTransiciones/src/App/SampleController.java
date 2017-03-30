package App;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SampleController implements Initializable {
	
	@FXML
	private Button boton;
	
	@FXML
	private ProgressBar progress; 
	
	private Main mainApp;
	
	@SuppressWarnings("unchecked")
	public void pulsar(ActionEvent event){
		//ir a la siguiente vista
		System.out.println("Botón pulsado");

		Stage dialogStage = new Stage();
		
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("loadingBar.fxml"));
		try {
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			progressController pc = loader.getController();
			progress = pc.getProgressBar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dialogStage.setAlwaysOnTop(true);
		dialogStage.show();

		Task tarea = new Task<Void>(){

		@Override
		protected Void call() throws Exception {
			System.out.println("Ejecutando tarea");
			reportGenerator report = new reportGenerator();
			System.out.println("Fin tarea");
			return null;
		}};

		tarea.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {            
            	dialogStage.close();
            }
        });
		
		Thread th = new Thread(tarea);

		th.setDaemon(true);

		progress.progressProperty().bind(tarea.progressProperty());
		th.start();
	}
	
	public void SetMainApp(Main mainApp){

		this.mainApp = mainApp;
		System.out.println(mainApp);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boton.setOnAction(this::pulsar);
	
	}
	
}
