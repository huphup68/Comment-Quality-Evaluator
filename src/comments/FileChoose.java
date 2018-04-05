package comments;

import java.io.File;
import javafx.stage.FileChooser;

/* responsible from choosing a file dynamically from the system */
public class FileChoose extends Read{
	
public File file;
	
	public void selectFile() {
	Main_Controller m = new Main_Controller();
	
	 FileChooser fchooser = new FileChooser();
	 
		 //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
		//fchooser.getExtensionFilters().add(extFilter);
	     
	     try{
	    	file = fchooser.showOpenDialog(null);	 
	     }catch(Exception e){
	    	 
	     }
	}

}
