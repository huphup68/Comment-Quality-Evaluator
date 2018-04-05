package comments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Main_Controller{
	FileChoose fc = new FileChoose();
	
	@FXML
	public Button buff_Read;
	@FXML
	public Button selct;
	@FXML
	public TextArea comment_Area;
	@FXML
	public TextArea con_method;
	

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setText() {
		comment_Area.setText("working as expected...");
	}
	
    public void select() {
    	fc.selectFile();
    	if (fc.file != null) {comment_Area.setText(fc.readFile(fc.file));}
    }
}
