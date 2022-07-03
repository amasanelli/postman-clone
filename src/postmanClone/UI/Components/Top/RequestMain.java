package postmanClone.UI.Components.Top;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.HTTPServices;
import postmanClone.BL.StorageServices;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.BackgroundWorker;
import postmanClone.UI.Frames.MainFrameRecords;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RequestMain extends CustomPanel {

	private JComboBox<HTTPVerb> comboBox;
	private JTextField textField;
	private JProgressBar progressBar;

	public RequestMain(StorageServices services) {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.comboBox = new JComboBox<HTTPVerb>(HTTPVerb.values());
		this.comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MainFrameRecord.setMethod((HTTPVerb) comboBox.getSelectedItem());
			}
		});

		this.textField = new JTextField();
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				String url = textField.getText();

				MainFrameRecord.setUrl(url);
			}
		});

		JButton buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				BackgroundWorker worker = new BackgroundWorker(progressBar) {
					@Override
					public void toDo() throws BusinessLogicLayerException {
						Response response = HTTPServices.sendRequest(MainFrameRecord.getRecord());
						MainFrameRecord.setResponse(response);

						StorageServices.saveHistory(MainFrameRecord.getRecord());
						MainFrameRecords.historiesListChanged();
					}
				};

				worker.execute();
			}
		});

		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String name = JOptionPane.showInputDialog("Set a name");

				if (name == null) {
					return;
				}

				BackgroundWorker worker = new BackgroundWorker(progressBar) {
					@Override
					public void toDo() throws BusinessLogicLayerException {
						StorageServices.saveBookmark(name, MainFrameRecord.getRecord());
						MainFrameRecords.bookmarksListChanged();
					}
				};

				worker.execute();
			}
		});

		JButton buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!(MainFrameRecord.getRecord() instanceof Bookmark)) {
					JOptionPane.showMessageDialog(null, "Select a bookmark!", "INFO", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int optionSelected = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete bookmark",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (optionSelected == 2) {
					return;
				}

				BackgroundWorker worker = new BackgroundWorker(progressBar) {
					@Override
					public void toDo() throws BusinessLogicLayerException {
						StorageServices.deleteBookmark((Bookmark) MainFrameRecord.getRecord());
						MainFrameRecords.bookmarksListChanged();
					}
				};

				worker.execute();
			}
		});

		JButton buttonNew = new JButton("New");
		buttonNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameRecord.newRecord();
				clearAll();
			}
		});

		JButton buttonTest = new JButton("Add test bookmarks");
		buttonTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				BackgroundWorker worker = new BackgroundWorker(progressBar) {
					@Override
					public void toDo() throws BusinessLogicLayerException {
						StorageServices.addTestRecords();
						MainFrameRecords.bookmarksListChanged();
					}
				};

				worker.execute();
			}
		});

		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
			@Override
			public void urlUpdate(Record record) {
				textField.setText(record.getUrl().toString());
			}
			
			@Override
			public void recordUpdate(Record record) {
				urlUpdate(record);
				comboBox.setSelectedItem(record.getMethod());
			}
		});

		this.add(this.textField);

		this.progressBar = new JProgressBar();
		this.progressBar.setIndeterminate(true);
		this.progressBar.setVisible(false);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(this.comboBox);
		panel.add(buttonSend);
		panel.add(buttonNew);
		panel.add(buttonSave);
		panel.add(buttonDelete);
		panel.add(buttonTest);
		panel.add(this.progressBar);
		this.add(panel);

	}

	public void clearAll() {
		this.textField.setText("");
		this.comboBox.setSelectedItem(HTTPVerb.GET);
	}
}
