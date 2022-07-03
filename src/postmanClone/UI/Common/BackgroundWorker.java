package postmanClone.UI.Common;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public abstract class BackgroundWorker extends SwingWorker<Void, Integer> {

	private JProgressBar progressBar;
	
	public BackgroundWorker(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	public abstract void toDo() throws Exception;

	@Override
	protected Void doInBackground() throws Exception {
		try {
			this.progressBar.setVisible(true);
			this.toDo();
		} catch (Exception e) {
			throw e;
		} finally {
			this.progressBar.setVisible(false);
		}
		return null;
	}
	
	@Override
	protected void done() {
		try {
			get();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.progressBar.getParent(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
