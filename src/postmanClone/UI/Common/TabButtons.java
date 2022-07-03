package postmanClone.UI.Common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class TabButtons extends CustomPanel implements ActionListener {

	private final List<JButton> buttons = new ArrayList<>();

	public TabButtons(List<String> names) {
		super();
		this.build(names);
	}

	private void build(List<String> names) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		for (String name : names) {
			JButton button = new JButton(name);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
			button.addActionListener(this);
			
			this.buttons.add(button);
			this.add(button);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		buttonClicked(((JButton) evt.getSource()).getText());
	}
	
	public abstract void buttonClicked(String buttonName);
}
