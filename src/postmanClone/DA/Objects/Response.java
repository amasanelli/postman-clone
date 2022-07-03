package postmanClone.DA.Objects;

public class Response {
	
	private final ResponseType type;
	private final byte[] data;
	
	public Response(ResponseType type, byte[] data) {
		this.type = type;
		this.data = data;
	}
	
	public byte[] getData() {
		return data;
	}

	public ResponseType getType() {
		return type;
	}

}
