package data_manager.handlePassCipher;

import org.jetbrains.annotations.NotNull;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Encrypt {
	String key = "holamundo";
	SecretKeySpec secretKey;
	public Encrypt(){
		secretKey = createKey(key);
	}
	public Encrypt(String key){
		this.key = key;
		secretKey = createKey(key);
	}

	public SecretKeySpec createKey(String key)throws RuntimeException{
		try {
			byte[] chain = key.getBytes(StandardCharsets.UTF_8);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			chain = md.digest(chain);
			chain = Arrays.copyOf(chain, 16);
			return new SecretKeySpec(chain, "AES");
		} catch(Exception e){
			throw new RuntimeException();
		}
	}
	public String encodeString(@NotNull String pass) throws RuntimeException{
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] chain = Base64.getEncoder().encode(pass.getBytes(StandardCharsets.UTF_8));
			byte[] encrypted = cipher.doFinal(chain);
			return new String(encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public String decryptString(String to_decrypt) throws RuntimeException{
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			byte[] chain = Base64.getDecoder().decode(to_decrypt);
			byte[] decrypted = cipher.doFinal(chain);
			return new String(decrypted);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
