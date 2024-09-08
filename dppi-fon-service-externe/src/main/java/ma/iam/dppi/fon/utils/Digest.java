package ma.iam.dppi.fon.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {

	public static String encode(String string) {
		StringBuilder buffer = new StringBuilder(64);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] bytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
			for (int i = 0; i < bytes.length; i++) {
				int b = bytes[i] & 0xFF;
				if (b < 0x10) {
					buffer.append('0');
				}
				buffer.append(Integer.toHexString(b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
