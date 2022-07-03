package postmanClone.UI.Components.Bottom;

import java.awt.TextArea;
import javax.swing.BoxLayout;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public abstract class ResponseText extends CustomPanel {

	private TextArea textArea;

	public ResponseText() {
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.textArea = new TextArea();

		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
			@Override
			public void responseUpdate(Record record) {
				update(record);
			}

			@Override
			public void recordUpdate(Record record) {
				textArea.setText(null);
			}
		});

		this.add(this.textArea);
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public abstract void update(Record record);

}
