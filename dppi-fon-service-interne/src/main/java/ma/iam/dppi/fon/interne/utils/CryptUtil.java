package ma.iam.dppi.fon.interne.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import ma.iam.dppi.fon.interne.exception.DppiGcException;
import ma.iam.dppi.fon.interne.exception.ErrorCodeEnum;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class CryptUtil {
	
	private static final String ALGO = "AES";
	private static final String ALGO_MOD = "AES/CBC/PKCS5Padding";
	private static final byte[] keyValue = new byte[] { 'i', 'n', 'p', 's',
			'm', 't', 'g', 's', 'e', 'c', 'r', 'e', 't', 'k', 'e', 'y' };

	public CryptUtil(String pswdLdap) throws DppiGcException {
		pswLdap = decrypt(pswdLdap);
	}

	private String pswLdap;

	public void setPswLdap(String pswLdap) {
		this.pswLdap = pswLdap;
	}

	public String getPswLdap() {
		return pswLdap;
	}

	public CryptUtil() {
		
	}

	public static String encrypt(String data) throws DppiGcException {
		String encryptedValue = "";
		try {
			byte[] iv = { 17, -15, 20, 85, 55, 02, 40, -90, -5, -78, 15, 15,
					40, 85, -9, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO_MOD);

			c.init(Cipher.ENCRYPT_MODE, key, ivspec);

			byte[] encVal = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
			encryptedValue = DatatypeConverter.printBase64Binary(encVal);
		} catch (Exception e) {

			e.getMessage();
			throw new DppiGcException(ErrorCodeEnum.ERROR_PSW, "Probleme encrypt password");

		}
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws DppiGcException {

		String decryptedValue = "";

		Cipher c;
		try {
			Key key = generateKey();
			c = Cipher.getInstance(ALGO_MOD);
			byte[] iv = { 17, -15, 20, 85, 55, 02, 40, -90, -5, -78, 15, 15,
					40, 85, -9, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			c.init(Cipher.DECRYPT_MODE, key, ivspec);
			byte[] decordedValue = DatatypeConverter
					.parseBase64Binary(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		} catch (Exception e) {
			e.getMessage();
			throw new DppiGcException(ErrorCodeEnum.ERROR_PSW, "Probleme decrypt Password");

		}

		return decryptedValue;
	}

	private static Key generateKey() {
		return new SecretKeySpec(keyValue, ALGO);
	}

	public static void main(String ... a) throws DppiGcException {
//		System.out.println(decrypt(""));
	}
}
