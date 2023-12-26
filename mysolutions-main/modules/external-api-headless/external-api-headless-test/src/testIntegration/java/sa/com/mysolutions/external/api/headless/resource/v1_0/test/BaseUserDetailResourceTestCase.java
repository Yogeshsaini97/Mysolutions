package sa.com.mysolutions.external.api.headless.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import sa.com.mysolutions.external.api.headless.client.dto.v1_0.UserDetail;
import sa.com.mysolutions.external.api.headless.client.http.HttpInvoker;
import sa.com.mysolutions.external.api.headless.client.pagination.Page;
import sa.com.mysolutions.external.api.headless.client.resource.v1_0.UserDetailResource;
import sa.com.mysolutions.external.api.headless.client.serdes.v1_0.UserDetailSerDes;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public abstract class BaseUserDetailResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_userDetailResource.setContextCompany(testCompany);

		UserDetailResource.Builder builder = UserDetailResource.builder();

		userDetailResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		UserDetail userDetail1 = randomUserDetail();

		String json = objectMapper.writeValueAsString(userDetail1);

		UserDetail userDetail2 = UserDetailSerDes.toDTO(json);

		Assert.assertTrue(equals(userDetail1, userDetail2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		UserDetail userDetail = randomUserDetail();

		String json1 = objectMapper.writeValueAsString(userDetail);
		String json2 = UserDetailSerDes.toJSON(userDetail);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		UserDetail userDetail = randomUserDetail();

		userDetail.setCrNumber(regex);
		userDetail.setDepartment(regex);
		userDetail.setEmailAddress(regex);
		userDetail.setEntityName(regex);
		userDetail.setEntityType(regex);
		userDetail.setFullName(regex);
		userDetail.setPhoneNumber(regex);

		String json = UserDetailSerDes.toJSON(userDetail);

		Assert.assertFalse(json.contains(regex));

		userDetail = UserDetailSerDes.toDTO(json);

		Assert.assertEquals(regex, userDetail.getCrNumber());
		Assert.assertEquals(regex, userDetail.getDepartment());
		Assert.assertEquals(regex, userDetail.getEmailAddress());
		Assert.assertEquals(regex, userDetail.getEntityName());
		Assert.assertEquals(regex, userDetail.getEntityType());
		Assert.assertEquals(regex, userDetail.getFullName());
		Assert.assertEquals(regex, userDetail.getPhoneNumber());
	}

	@Test
	public void testGetUserDetails() throws Exception {
		Page<UserDetail> page = userDetailResource.getUserDetails(
			RandomTestUtil.randomString());

		long totalCount = page.getTotalCount();

		UserDetail userDetail1 = testGetUserDetails_addUserDetail(
			randomUserDetail());

		UserDetail userDetail2 = testGetUserDetails_addUserDetail(
			randomUserDetail());

		page = userDetailResource.getUserDetails(null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(userDetail1, (List<UserDetail>)page.getItems());
		assertContains(userDetail2, (List<UserDetail>)page.getItems());
		assertValid(page);
	}

	protected UserDetail testGetUserDetails_addUserDetail(UserDetail userDetail)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetUserDetails() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostUserDetails() throws Exception {
		Assert.assertTrue(false);
	}

	protected void assertContains(
		UserDetail userDetail, List<UserDetail> userDetails) {

		boolean contains = false;

		for (UserDetail item : userDetails) {
			if (equals(userDetail, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			userDetails + " does not contain " + userDetail, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		UserDetail userDetail1, UserDetail userDetail2) {

		Assert.assertTrue(
			userDetail1 + " does not equal " + userDetail2,
			equals(userDetail1, userDetail2));
	}

	protected void assertEquals(
		List<UserDetail> userDetails1, List<UserDetail> userDetails2) {

		Assert.assertEquals(userDetails1.size(), userDetails2.size());

		for (int i = 0; i < userDetails1.size(); i++) {
			UserDetail userDetail1 = userDetails1.get(i);
			UserDetail userDetail2 = userDetails2.get(i);

			assertEquals(userDetail1, userDetail2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<UserDetail> userDetails1, List<UserDetail> userDetails2) {

		Assert.assertEquals(userDetails1.size(), userDetails2.size());

		for (UserDetail userDetail1 : userDetails1) {
			boolean contains = false;

			for (UserDetail userDetail2 : userDetails2) {
				if (equals(userDetail1, userDetail2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				userDetails2 + " does not contain " + userDetail1, contains);
		}
	}

	protected void assertValid(UserDetail userDetail) throws Exception {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("crNumber", additionalAssertFieldName)) {
				if (userDetail.getCrNumber() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("department", additionalAssertFieldName)) {
				if (userDetail.getDepartment() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("emailAddress", additionalAssertFieldName)) {
				if (userDetail.getEmailAddress() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entityName", additionalAssertFieldName)) {
				if (userDetail.getEntityName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entityType", additionalAssertFieldName)) {
				if (userDetail.getEntityType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("fullName", additionalAssertFieldName)) {
				if (userDetail.getFullName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("phoneNumber", additionalAssertFieldName)) {
				if (userDetail.getPhoneNumber() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<UserDetail> page) {
		boolean valid = false;

		java.util.Collection<UserDetail> userDetails = page.getItems();

		int size = userDetails.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					sa.com.mysolutions.external.api.headless.dto.v1_0.
						UserDetail.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(UserDetail userDetail1, UserDetail userDetail2) {
		if (userDetail1 == userDetail2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("crNumber", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getCrNumber(), userDetail2.getCrNumber())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("department", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getDepartment(),
						userDetail2.getDepartment())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("emailAddress", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getEmailAddress(),
						userDetail2.getEmailAddress())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("entityName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getEntityName(),
						userDetail2.getEntityName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("entityType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getEntityType(),
						userDetail2.getEntityType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fullName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getFullName(), userDetail2.getFullName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("phoneNumber", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						userDetail1.getPhoneNumber(),
						userDetail2.getPhoneNumber())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_userDetailResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_userDetailResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, UserDetail userDetail) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("crNumber")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getCrNumber()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("department")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getDepartment()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("emailAddress")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getEmailAddress()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("entityName")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getEntityName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("entityType")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getEntityType()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("fullName")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getFullName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("phoneNumber")) {
			sb.append("'");
			sb.append(String.valueOf(userDetail.getPhoneNumber()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected UserDetail randomUserDetail() throws Exception {
		return new UserDetail() {
			{
				crNumber = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				department = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				emailAddress =
					StringUtil.toLowerCase(RandomTestUtil.randomString()) +
						"@liferay.com";
				entityName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				entityType = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				fullName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				phoneNumber = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected UserDetail randomIrrelevantUserDetail() throws Exception {
		UserDetail randomIrrelevantUserDetail = randomUserDetail();

		return randomIrrelevantUserDetail;
	}

	protected UserDetail randomPatchUserDetail() throws Exception {
		return randomUserDetail();
	}

	protected UserDetailResource userDetailResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = _getSuperClass(source.getClass());

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					sourceClass.getDeclaredFields()) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				Method setMethod = _getMethod(
					targetClass, field.getName(), "set",
					getMethod.getReturnType());

				setMethod.invoke(target, getMethod.invoke(source));
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Class<?> _getSuperClass(Class<?> clazz) {
			Class<?> superClass = clazz.getSuperclass();

			if ((superClass == null) || (superClass == Object.class)) {
				return clazz;
			}

			return superClass;
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseUserDetailResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		sa.com.mysolutions.external.api.headless.resource.v1_0.
			UserDetailResource _userDetailResource;

}