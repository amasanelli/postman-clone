package postmanClone.UI.Components.Bottom;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class ResponseImage extends CustomPanel {

	private JLabel label;

	public ResponseImage() {
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.label = new JLabel();

		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
			@Override
			public void responseUpdate(Record record) {
				Response response = record.getResponse();

				switch (response.getType()) {
				case IMAGE:
					label.setIcon(new ImageIcon(response.getData()));
					break;
				case JSON:
				case XML:
				default:
					label.setIcon(null);
					break;
				}
			}

			@Override
			public void recordUpdate(Record record) {
				label.setText(null);
			}
		});

		this.add(this.label);
	}

}
