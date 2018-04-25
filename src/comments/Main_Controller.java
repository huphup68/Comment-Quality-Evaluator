package comments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Main_Controller{
	FileChoose fc = new FileChoose();
	private Comment comment;
	
	@FXML
	public Button cal_Readability;
	@FXML
	public Button selct;
	@FXML
	public TextArea comment_Area;
	@FXML
	public TextArea con_method;
	@FXML
	public Button comment_Format;
	@FXML
	public Button cal_Concurrency;
	@FXML
	public Text con;
	@FXML
	public Text loc;
	@FXML
	public Text dcs;
	
	

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setText() {
		comment_Area.setText("working as expected...");
	}
	
    public void filterComment() {
    	comment = new Comment(comment_Area.getText());
    	comment.filter();
    	comment.getLength();
    	loc.setText(Integer.toString(comment.getLength()));
    }
    
    public void calReadability() {
    	dcs.setText(Double.toString(Readability.daleChall(comment.getComment())));
    }
    
    public void calConcurrency() {
    	con.setText(Double.toString(Concurrency.concurrencyCalc(comment.getComment(), con_method.getText())));
    }
    
    public void select() {
    	fc.selectFile();
    	if (fc.file != null) {comment_Area.setText(fc.readFile(fc.file));}
    }
}
