package postmanClone.BL;

public class StringServices {

	public static String formatRawStringToJSON(String rawJSON, int indentWidth) throws BusinessLogicLayerException {

		if (rawJSON == null) {
			return "";
		}

		char[] chars = rawJSON.toCharArray();

		StringBuilder result = new StringBuilder();

		int i = 0;
		int indent = 0;

		try {
			while (i < chars.length) {
				char c = chars[i];

				switch (c) {
				case '\"':
					result.append(c);
					i++;
					while (i < chars.length & (c = chars[i]) != '\"') {
						result.append(c);
						i++;
					}
					result.append('\"');
					break;
				case ':':
					result.append(": ");
					break;
				case '{':
				case '[':
					result.append(c);
					result.append("\n\r");
					result.append(String.format("%" + (indent += indentWidth) + "s", ""));
					break;
				case '}':
				case ']':
					result.append("\n\r");
					result.append(((indent -= indentWidth) > 0 ? String.format("%" + indent + "s", "") : ""));
					result.append(c);
					break;
				case ',':
					result.append(c);
					result.append("\n\r");
					result.append(String.format("%" + (indent) + "s", ""));
					break;
				default:
					if (!Character.isWhitespace(c)) {
						result.append(c);
					}
					break;
				}

				i++;

			}
		} catch (Exception e) {
			throw new BusinessLogicLayerException(e);
		}

		return result.toString();
	}

	public static String formatRawStringToJSON(String rawJSON) throws BusinessLogicLayerException {
		return formatRawStringToJSON(rawJSON, 4);
	}

	public static String formatRawStringToXML(String rawXML, int indentWidth) throws BusinessLogicLayerException {

		if (rawXML == null) {
			return "";
		}

		char[] chars = rawXML.trim().replaceAll(" +", " ").replaceAll("\n", "").replaceAll("\r", "")
				.replaceAll("> <", "><").toCharArray();

		StringBuilder result = new StringBuilder();
		StringBuilder tag = null;

		int i = 0;
		int indent = 0;

		try {
			while (i < chars.length) {
				char c = chars[i];

				switch (c) {
				case '<':
					tag = new StringBuilder();
					while (i < chars.length && (c = chars[i]) != '>') {
						tag.append(c);
						i++;
					}
					tag.append('>');

					char start = tag.charAt(1);
					char end = tag.charAt(tag.length() - 2);
					
					if (start == '!' || start == '?' || end == '/') {
						result.append(indent > 0 ? String.format("%" + (indent) + "s", "") : "");
						result.append(tag);
						result.append("\n\r");
					} else if (start == '/') {
						indent -= indentWidth;
						result.append(indent > 0 ? String.format("%" + (indent) + "s", "") : "");
						result.append(tag);
						result.append("\n\r");
					} else {
						result.append(indent > 0 ? String.format("%" + (indent) + "s", "") : "");
						result.append(tag);
						result.append("\n\r");
						indent += indentWidth;
					}
					
					break;
				default:
					result.append(indent > 0 ? String.format("%" + (indent) + "s", "") : "");
					
					while (i < chars.length && (c = chars[i]) != '<') {
						result.append(c);
						i++;
					}
					i--;
					
					result.append("\n\r");
					
					break;
				}

				i++;

			}
		} catch (Exception e) {
			throw new BusinessLogicLayerException(e);
		}

		return result.toString();
	}

	public static String formatRawStringToXML(String rawXML) throws BusinessLogicLayerException {
		return formatRawStringToXML(rawXML, 4);
	}
}
