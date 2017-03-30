package App;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class progressController implements Initializable{

	
	@FXML
	private ProgressBar progress;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.err.println("Mostrando progress bar");
		progress.setProgress(0.5);
		System.err.println("Mostrando progress bar");		
	}
	
	public ProgressBar getProgressBar(){
		return progress;
	}

}
