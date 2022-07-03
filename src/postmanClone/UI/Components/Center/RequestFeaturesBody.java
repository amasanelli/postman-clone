package postmanClone.UI.Components.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.StringServices;
import postmanClone.DA.Objects.BodyType;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RequestFeaturesBody extends CustomPanel {

	private TextArea textArea;
	private JComboBox<BodyType> comboBox;

	public RequestFeaturesBody() {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BorderLayout());

		this.textArea = new TextArea();
		this.textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				try {
					if (comboBox.getSelectedItem() == BodyType.JSON) {
						textArea.setText(StringServices.formatRawStringToJSON(textArea.getText()));
					}
					MainFrameRecord.setBody((BodyType) comboBox.getSelectedItem(), textArea.getText());
				} catch (BusinessLogicLayerException e) {
					showError(e);
				}
			}
		});

		this.comboBox = new JComboBox<BodyType>(BodyType.values());
		this.comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (comboBox.getSelectedItem() == BodyType.JSON) {
						textArea.setText(StringServices.formatRawStringToJSON(textArea.getText()));
					} 
					MainFrameRecord.setBody((BodyType) comboBox.getSelectedItem(), textArea.getText());
				} catch (BusinessLogicLayerException e) {
					showError(e);
				}
			}
		});

		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
			@Override
			public void bodyUpdate(Record record) {
				textArea.setText(record.getBody().getValue());
				comboBox.setSelectedItem(record.getBody().getType());
			}
			
			@Override
			public void recordUpdate(Record record) {
				bodyUpdate(record);
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(this.comboBox);
		
		this.add(panel, BorderLayout.NORTH);
		this.add(this.textArea);

	}
}