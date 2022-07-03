package postmanClone.BL;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import postmanClone.DA.Objects.BodyType;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;
import postmanClone.DA.Objects.ResponseType;

public class HTTPServices {

	public static Response sendRequest(Record record) throws BusinessLogicLayerException {
		HttpURLConnection connection = null;
		BufferedWriter bufferedWriter = null;
		BufferedInputStream bufferedInputStream = null;

		try {

			URL url = new URL(record.getUrl().toString());

			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(record.getMethod().toString());

			for (Header header : record.getHeaders()) {
				connection.setRequestProperty(header.getName(), header.getValue());
			}

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			if (record.getMethod() != HTTPVerb.GET) {

				if (record.getBody().getType() == BodyType.JSON) {
					connection.setRequestProperty("Content-Type", "application/json");
				} else {
					connection.setRequestProperty("Content-Type", "text/plain");
				}

				bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				bufferedWriter.write(record.getBody().getValue());
				bufferedWriter.flush();
			}

			connection.connect();

			String contentType = connection.getContentType();
			if (!contentType.equals("application/json") && !contentType.equals("text/plain")
					&& !contentType.equals("application/xml") && !contentType.equals("image/jpeg")) {
				return new Response(ResponseType.PLAIN, "Content type not allowed!".getBytes());
			}

			bufferedInputStream = new BufferedInputStream(connection.getInputStream());
			byte[] data = new byte[connection.getContentLength()];
			byte[] buffer = new byte[1024];
			int totalBytes = 0;
			int readBytes;
			
			while ((readBytes = bufferedInputStream.read(buffer)) != -1) {
				for (int i = 0; i < readBytes; i++) {
					data[totalBytes + i] = buffer[i];
				}
				totalBytes += readBytes;
			}
			
			switch (contentType) {
			case "application/json":
				return new Response(ResponseType.JSON, data);
			case "application/xml":
				return new Response(ResponseType.XML, data);
			case "image/jpeg":
				return new Response(ResponseType.IMAGE, data);
			default:
				return new Response(ResponseType.PLAIN, data);
			}			

		} catch (IOException e) {
			throw new BusinessLogicLayerException(e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					throw new BusinessLogicLayerException(e);
				}
			}
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					throw new BusinessLogicLayerException(e);
				}
			}
		}
	}
}
