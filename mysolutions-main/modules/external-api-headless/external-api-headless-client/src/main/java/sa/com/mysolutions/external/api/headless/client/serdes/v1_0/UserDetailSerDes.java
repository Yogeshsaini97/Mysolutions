package sa.com.mysolutions.external.api.headless.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import sa.com.mysolutions.external.api.headless.client.dto.v1_0.UserDetail;
import sa.com.mysolutions.external.api.headless.client.json.BaseJSONParser;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class UserDetailSerDes {

	public static UserDetail toDTO(String json) {
		UserDetailJSONParser userDetailJSONParser = new UserDetailJSONParser();

		return userDetailJSONParser.parseToDTO(json);
	}

	public static UserDetail[] toDTOs(String json) {
		UserDetailJSONParser userDetailJSONParser = new UserDetailJSONParser();

		return userDetailJSONParser.parseToDTOs(json);
	}

	public static String toJSON(UserDetail userDetail) {
		if (userDetail == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (userDetail.getCrNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"crNumber\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getCrNumber()));

			sb.append("\"");
		}

		if (userDetail.getDepartment() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"department\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getDepartment()));

			sb.append("\"");
		}

		if (userDetail.getEmailAddress() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddress\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getEmailAddress()));

			sb.append("\"");
		}

		if (userDetail.getEntityName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entityName\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getEntityName()));

			sb.append("\"");
		}

		if (userDetail.getEntityType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entityType\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getEntityType()));

			sb.append("\"");
		}

		if (userDetail.getFullName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fullName\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getFullName()));

			sb.append("\"");
		}

		if (userDetail.getPhoneNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"phoneNumber\": ");

			sb.append("\"");

			sb.append(_escape(userDetail.getPhoneNumber()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UserDetailJSONParser userDetailJSONParser = new UserDetailJSONParser();

		return userDetailJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(UserDetail userDetail) {
		if (userDetail == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (userDetail.getCrNumber() == null) {
			map.put("crNumber", null);
		}
		else {
			map.put("crNumber", String.valueOf(userDetail.getCrNumber()));
		}

		if (userDetail.getDepartment() == null) {
			map.put("department", null);
		}
		else {
			map.put("department", String.valueOf(userDetail.getDepartment()));
		}

		if (userDetail.getEmailAddress() == null) {
			map.put("emailAddress", null);
		}
		else {
			map.put(
				"emailAddress", String.valueOf(userDetail.getEmailAddress()));
		}

		if (userDetail.getEntityName() == null) {
			map.put("entityName", null);
		}
		else {
			map.put("entityName", String.valueOf(userDetail.getEntityName()));
		}

		if (userDetail.getEntityType() == null) {
			map.put("entityType", null);
		}
		else {
			map.put("entityType", String.valueOf(userDetail.getEntityType()));
		}

		if (userDetail.getFullName() == null) {
			map.put("fullName", null);
		}
		else {
			map.put("fullName", String.valueOf(userDetail.getFullName()));
		}

		if (userDetail.getPhoneNumber() == null) {
			map.put("phoneNumber", null);
		}
		else {
			map.put("phoneNumber", String.valueOf(userDetail.getPhoneNumber()));
		}

		return map;
	}

	public static class UserDetailJSONParser
		extends BaseJSONParser<UserDetail> {

		@Override
		protected UserDetail createDTO() {
			return new UserDetail();
		}

		@Override
		protected UserDetail[] createDTOArray(int size) {
			return new UserDetail[size];
		}

		@Override
		protected void setField(
			UserDetail userDetail, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "crNumber")) {
				if (jsonParserFieldValue != null) {
					userDetail.setCrNumber((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "department")) {
				if (jsonParserFieldValue != null) {
					userDetail.setDepartment((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				if (jsonParserFieldValue != null) {
					userDetail.setEmailAddress((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "entityName")) {
				if (jsonParserFieldValue != null) {
					userDetail.setEntityName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "entityType")) {
				if (jsonParserFieldValue != null) {
					userDetail.setEntityType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fullName")) {
				if (jsonParserFieldValue != null) {
					userDetail.setFullName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "phoneNumber")) {
				if (jsonParserFieldValue != null) {
					userDetail.setPhoneNumber((String)jsonParserFieldValue);
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