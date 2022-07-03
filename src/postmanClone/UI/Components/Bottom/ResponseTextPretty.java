package postmanClone.UI.Components.Bottom;

import java.awt.TextArea;

import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.StringServices;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;

@SuppressWarnings("serial")
public class ResponseTextPretty extends ResponseText {

	@Override
	public void update(Record record) {

		Response response = record.getResponse();
		TextArea textArea = this.getTextArea();

		try {
			switch (response.getType()) {
			case JSON:
				textArea.setText(StringServices.formatRawStringToJSON(new String(response.getData())));
				break;
			case XML:
				textArea.setText(StringServices.formatRawStringToXML(new String(response.getData())));
				break;
			case IMAGE:
				textArea.setText("binary");
				break;
			default:
				textArea.setText("plain");
				break;
			}
		} catch (BusinessLogicLayerException e) {
			this.showError(e);
		}

	}

}
