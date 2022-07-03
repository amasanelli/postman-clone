package postmanClone.DA.Objects;

import java.util.ArrayList;
import java.util.List;

public class URL {

	private String baseUrl = "";
	private String parameters = "";
	private String newParameter = "";
	private String newValue = "";

	public URL(String fullUrl) {
		String[] splitted = fullUrl.split("\\?");
		this.baseUrl = splitted[0];
		this.parameters = splitted.length > 1 ? splitted[1] : "";
	}
	
	public URL(URL url) {
		this.baseUrl = url.getBaseUrl();
		this.parameters = url.getParameters();
	}

	public URL() {
		
	}

	public void setParameters(List<Parameter> parameters) {

		if (parameters.size() == 0) {
			this.parameters = "";
			return;
		}

		StringBuilder query = new StringBuilder();

		query.append(parameters.get(0).getName() + "=" + parameters.get(0).getValue());

		for (int i = 1; i < parameters.size(); i++) {
			query.append("&" + parameters.get(i).getName() + "=" + parameters.get(i).getValue());
		}

		this.parameters = query.toString();
	}

	public List<Parameter> getParemeters() {
		List<Parameter> parameters = new ArrayList<Parameter>();

		if (this.parameters.length() > 1) {
			String[] rawParameters = this.parameters.split("&");

			for (String rawParameter : rawParameters) {
				String[] splittedParameter = rawParameter.split("=");
				parameters.add(new Parameter(splittedParameter[1], splittedParameter[0]));
			}
		}

		return parameters;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl.split("\\?")[0];
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		String[] splitted = baseUrl.split("\\?");
		this.parameters = splitted.length > 1 ? splitted[1] : "";
	}

	@Override
	public String toString() {
		String fullUrl = this.baseUrl;

		if (this.parameters.length() > 0) {
			fullUrl += "?" + this.parameters;
		}

		if (this.newParameter.length() > 0 | this.newValue.length() > 0) {
			fullUrl += (this.parameters.length() > 0 ? "&" : "?") + this.newParameter + "=" + this.newValue;
		}

		return fullUrl;
	}

	public String getNewParameter() {
		return newParameter;
	}

	public void setNewParameter(String newParameter) {
		this.newParameter = newParameter;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}
