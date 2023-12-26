package sa.com.mysolutions.audit.headless.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import sa.com.mysolutions.audit.headless.client.dto.v1_0.Audit;
import sa.com.mysolutions.audit.headless.client.json.BaseJSONParser;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class AuditSerDes {

	public static Audit toDTO(String json) {
		AuditJSONParser auditJSONParser = new AuditJSONParser();

		return auditJSONParser.parseToDTO(json);
	}

	public static Audit[] toDTOs(String json) {
		AuditJSONParser auditJSONParser = new AuditJSONParser();

		return auditJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Audit audit) {
		if (audit == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (audit.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(_escape(audit.getCreateDate()));

			sb.append("\"");
		}

		if (audit.getEventID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"eventID\": ");

			sb.append("\"");

			sb.append(_escape(audit.getEventID()));

			sb.append("\"");
		}

		if (audit.getResourceAction() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"resourceAction\": ");

			sb.append("\"");

			sb.append(_escape(audit.getResourceAction()));

			sb.append("\"");
		}

		if (audit.getResourceName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"resourceName\": ");

			sb.append("\"");

			sb.append(_escape(audit.getResourceName()));

			sb.append("\"");
		}

		if (audit.getTime() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"time\": ");

			sb.append("\"");

			sb.append(_escape(audit.getTime()));

			sb.append("\"");
		}

		if (audit.getUserID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userID\": ");

			sb.append("\"");

			sb.append(_escape(audit.getUserID()));

			sb.append("\"");
		}

		if (audit.getUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(audit.getUserName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AuditJSONParser auditJSONParser = new AuditJSONParser();

		return auditJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Audit audit) {
		if (audit == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (audit.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put("createDate", String.valueOf(audit.getCreateDate()));
		}

		if (audit.getEventID() == null) {
			map.put("eventID", null);
		}
		else {
			map.put("eventID", String.valueOf(audit.getEventID()));
		}

		if (audit.getResourceAction() == null) {
			map.put("resourceAction", null);
		}
		else {
			map.put(
				"resourceAction", String.valueOf(audit.getResourceAction()));
		}

		if (audit.getResourceName() == null) {
			map.put("resourceName", null);
		}
		else {
			map.put("resourceName", String.valueOf(audit.getResourceName()));
		}

		if (audit.getTime() == null) {
			map.put("time", null);
		}
		else {
			map.put("time", String.valueOf(audit.getTime()));
		}

		if (audit.getUserID() == null) {
			map.put("userID", null);
		}
		else {
			map.put("userID", String.valueOf(audit.getUserID()));
		}

		if (audit.getUserName() == null) {
			map.put("userName", null);
		}
		else {
			map.put("userName", String.valueOf(audit.getUserName()));
		}

		return map;
	}

	public static class AuditJSONParser extends BaseJSONParser<Audit> {

		@Override
		protected Audit createDTO() {
			return new Audit();
		}

		@Override
		protected Audit[] createDTOArray(int size) {
			return new Audit[size];
		}

		@Override
		protected void setField(
			Audit audit, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					audit.setCreateDate((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "eventID")) {
				if (jsonParserFieldValue != null) {
					audit.setEventID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "resourceAction")) {
				if (jsonParserFieldValue != null) {
					audit.setResourceAction((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "resourceName")) {
				if (jsonParserFieldValue != null) {
					audit.setResourceName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "time")) {
				if (jsonParserFieldValue != null) {
					audit.setTime((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userID")) {
				if (jsonParserFieldValue != null) {
					audit.setUserID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userName")) {
				if (jsonParserFieldValue != null) {
					audit.setUserName((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}