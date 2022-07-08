package postmanClone.UI.Common;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class CustomPanel extends JPanel {

	public void showError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
}

