package postmanClone.UI.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.StorageServices;
import postmanClone.UI.Components.BottomPanel;
import postmanClone.UI.Components.LeftPanel;
import postmanClone.UI.Components.TopPanel;
import postmanClone.UI.Components.CenterPanel;

public class MainFrame implements ActionListener {

	private JMenuItem cleanHistory;
	private JMenuItem cleanBookmarks;

	public MainFrame() {
		try {
			StorageServices.createContainers();
			this.show();
		} catch (BusinessLogicLayerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void show() {
		JFrame frame = new JFrame("Postman Clone");
		frame.setLayout(new BorderLayout());

		LeftPanel leftPanel = new LeftPanel();
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		CenterPanel centerPanel = new CenterPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		frame.add(new TopPanel(), BorderLayout.NORTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(new BottomPanel(), BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");

		this.cleanHistory = new JMenuItem("Clean history");
		cleanHistory.addActionListener(this);
		menuFile.add(cleanHistory);

		this.cleanBookmarks = new JMenuItem("Clean bookmarks");
		cleanBookmarks.addActionListener(this);
		menuFile.add(cleanBookmarks);

		menuBar.add(menuFile);

		frame.setJMenuBar(menuBar);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int optionSelected = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete data",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (optionSelected == JOptionPane.NO_OPTION) {
			return;
		}

		if (evt.getSource() == this.cleanBookmarks) {
			try {
				StorageServices.cleanBookmarks();
				MainFrameRecordsHandler.bookmarksListChanged();
			} catch (BusinessLogicLayerException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (evt.getSource() == this.cleanHistory) {
			try {
				StorageServices.cleanHistory();
				MainFrameRecordsHandler.historiesListChanged();
			} catch (BusinessLogicLayerException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
