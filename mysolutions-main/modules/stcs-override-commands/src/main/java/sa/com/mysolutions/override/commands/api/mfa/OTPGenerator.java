package sa.com.mysolutions.override.commands.api.mfa;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class OTPGenerator {

	public static final String KEY1 = "0123456789";

	public static String getPassword() {
		return getPassword(8, _KEYS);
	}

	public static String getPassword(int length) {
		return getPassword(length, _KEYS);
	}

	public static String getPassword(int length, String... keys) {
		if (keys == null) {
			throw new IllegalArgumentException("Keys are null");
		}

		String fullKey = String.valueOf(new StringBundler(keys));

		int fullKeyLength = fullKey.length();

		int refreshPeriod = (int)(_MULTIPLIER / Math.log(fullKeyLength));

		long secureLong = 0;

		StringBundler sb = new StringBundler(length);

		for (int i = 0; i < length; i++) {
			if ((i % refreshPeriod) == 0) {
				secureLong = SecureRandomUtil.nextLong();
			}

			int pos = Math.abs((int)(secureLong % fullKeyLength));

			secureLong = secureLong / fullKeyLength;

			sb.append(fullKey.charAt(pos));
		}

		return sb.toString();
	}

	public static String getPassword(String key, int length) {
		int keysCount = 0;

		if (key.contains(KEY1)) {
			keysCount++;
		}

		if (keysCount > length) {
			if (_log.isWarnEnabled()) {
				_log.warn("Length is too short");
			}

			length = keysCount;
		}

		while (true) {
			String password = getPassword(length, key);

			if (key.contains(KEY1) &&
				Validator.isNull(StringUtil.extractDigits(password))) {

				continue;
			}

			return password;
		}
	}

	public static String getPassword(
		String key, int length, boolean useAllKeys) {

		if (useAllKeys) {
			return getPassword(key, length);
		}

		return getPassword(length, key);
	}

	public static String getPinNumber() {
		return getPassword(4, KEY1);
	}

	private static final String[] _KEYS = {KEY1};

	private static final double _MULTIPLIER = Long.SIZE * Math.log(2);

	private static final Log _log = LogFactoryUtil.getLog(OTPGenerator.class);

}
