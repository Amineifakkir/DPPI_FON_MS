package ma.iam.dppi.fon.interne.utils;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import ma.iam.dppi.fon.interne.exception.DppiGcException;
import ma.iam.dppi.fon.interne.exception.ErrorCodeEnum;


public class CryptEmail {
	
	private static final String UNICODE_FORMAT  = "UTF8";
	private static final String ALGO = "AES";
	private static final String ALGO_MOD = "AES/CBC/PKCS5Padding";
	private static final byte[] keyValue =
			new byte[]{'i', 'n', 'p', 's', 'm', 't', 'g',
		's', 'e', 'c', 'r', 'e', 't', 'k', 'e', 'y'};

	private String pswdEmail;

	public CryptEmail() {
	}

	public CryptEmail(String encryptedData) throws Exception {
		this.pswdEmail = decrypt(encryptedData);
	}

	public String getPswdEmail() {
		return pswdEmail;
	}

	public void setPswdEmail(String pswdEmail) {
		this.pswdEmail = pswdEmail;
	}

	public String encrypt(String data) throws DppiGcException {
    	String encryptedValue = "";
    	try {
    		
	   	    byte[] iv =   { 17, -15, 20, 85, 55, 02, 40, -90, -5, -78, 15, 15, 40, 85, -9, 0 };
	    	IvParameterSpec ivspec = new IvParameterSpec(iv);
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO_MOD);
	
	        c.init(Cipher.ENCRYPT_MODE, key,ivspec);
	        
	        byte[] encVal = c.doFinal(data.getBytes(UNICODE_FORMAT));
	        encryptedValue = DatatypeConverter.printBase64Binary(encVal);
    	} catch (Exception e) {
			e.printStackTrace();
			throw new DppiGcException(ErrorCodeEnum.ERROR_PSW, "Probleme encrypt passwors");
		} 
        return encryptedValue;
    }

	public String decrypt(String encryptedData) throws DppiGcException {

		String decryptedValue="";    	

		Cipher c;
		try {
			Key key = generateKey();
			c = Cipher.getInstance(ALGO_MOD);
			byte[] iv =   { 17, -15, 20, 85, 55, 02, 40, -90, -5, -78, 15, 15, 40, 85, -9, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			c.init(Cipher.DECRYPT_MODE, key,ivspec);
			byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DppiGcException(ErrorCodeEnum.ERROR_PSW, "Probleme decrypt Password");
		} 
		return decryptedValue;
	}


	private Key generateKey() throws DppiGcException {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	
	public static void main(String ... a) throws DppiGcException {
	}
 
}