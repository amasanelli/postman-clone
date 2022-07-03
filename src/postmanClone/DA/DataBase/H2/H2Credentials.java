package postmanClone.DA.DataBase.H2;

import postmanClone.DA.DataBase.DataBaseCredentials;

public class H2Credentials implements DataBaseCredentials {

	private static final String DRIVER = "org.h2.Driver";
	private static final String URI = "jdbc:h2:~/db3";
	private static final String USER = "sa";
	private static final String PASS = "";

	@Override
	public String getDriver() {
		return H2Credentials.DRIVER;
	}

	@Override
	public String getURI() {
		return H2Credentials.URI;

	}

	@Override
	public String getUser() {
		return H2Credentials.USER;
	}

	@Override
	public String getPass() {
		return H2Credentials.PASS;
	}

}
