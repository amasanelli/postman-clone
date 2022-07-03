package postmanClone.UI.Components.Bottom;

import java.awt.TextArea;

import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;

@SuppressWarnings("serial")
public class ResponseTextRaw extends ResponseText {

	@Override
	public void update(Record record) {
	
		Response response = record.getResponse();
		TextArea textArea = this.getTextArea();

		switch (response.getType()) {
		case JSON:
			//pretty.setText(StringServices.formatRawStringToJSON(new String(response.getData())));
			//image.setIcon(null);
			textArea.setText(new String(response.getData()));
			break;
		case XML:
			//pretty.setText(StringServices.formatRawStringToXML(new String(response.getData())));
			//image.setIcon(null);
			textArea.setText(new String(response.getData()));
			break;
		case IMAGE:
			//pretty.setText("binary");
			//image.setIcon(new ImageIcon(response.getData()));
			textArea.setText("binary");
			break;
		default:
			//pretty.setText(null);
			//image.setIcon(null);
			textArea.setText(new String(response.getData()));
			break;
		}
		
	}

}
