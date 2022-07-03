package postmanClone.UI.Components.Center;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import postmanClone.UI.Common.CustomPanel;

@SuppressWarnings("serial")
public abstract class RequestFeaturesGridActions extends CustomPanel implements ActionListener, DocumentListener {

	private JTextField itemName;
	private JTextField itemValue;
	private JButton add;
	private JButton delete;

	public RequestFeaturesGridActions() {
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.itemName = new JTextField();
		this.itemName.getDocument().addDocumentListener(this);
		
		this.itemValue = new JTextField();
		this.itemValue.getDocument().addDocumentListener(this);

		this.add = new JButton("Add");
		this.add.addActionListener(this);

		this.delete = new JButton("Delete");
		this.delete.addActionListener(this);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 2));
		textPanel.add(this.itemName);
		textPanel.add(this.itemValue);
		this.add(textPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(this.add);
		buttonPanel.add(this.delete);
		this.add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.delete) {
			this.deleteButtonClicked();
			this.clearAll();
		} else if (evt.getSource() == this.add) {
			this.addButtonClicked(this.itemValue.getText(), this.itemName.getText());
			this.clearAll();
		}
	}
	
	private void update(DocumentEvent evt) {
		if (evt.getDocument() == this.itemName.getDocument()) {
			this.nameItemChanged(this.itemName.getText());
		} else if (evt.getDocument() == this.itemValue.getDocument()) {
			this.valueItemChanged(this.itemValue.getText());
		}
	}
	
	@Override
	public void insertUpdate(DocumentEvent evt) {
		this.update(evt);
	}

	@Override
	public void removeUpdate(DocumentEvent evt) {
		this.update(evt);
	}

	@Override
	public void changedUpdate(DocumentEvent evt) {
		this.update(evt);
	}
	
	
	public void clearAll() {
		this.itemName.setText("");
		this.itemValue.setText("");
	}
	
	public abstract void nameItemChanged(String value);
	public abstract void valueItemChanged(String value);
	
	public abstract void deleteButtonClicked();
	public abstract void addButtonClicked(String value, String name);

}
