package data_manager.handlePassCipher;

import org.jetbrains.annotations.NotNull;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encrypt {
	String key = "whegqwegqogqwl";
	public Encrypt(){
	}
	public Encrypt(String key){
		this.key = key;
	}
	public SecretKeySpec createKey(String key){
		try {
			byte [] bytes = key.getBytes(StandardCharsets.UTF_8);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(bytes);
			bytes = Arrays.copyOf(bytes, 16);
			return new SecretKeySpec(bytes, "AES");
		} catch(Exception e){
			return null;
		}
	}
	public String encodeString(@NotNull String pass){
		try {
			SecretKeySpec secretKeySpec = createKey(key);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte [] chain = Base64.getEncoder().encode(pass.getBytes(StandardCharsets.UTF_8));
			byte [] encryptedChain = cipher.doFinal(chain);
			return Base64.getEncoder().encodeToString(encryptedChain);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
		         InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
	public String decryptString(String decrypt_pass){
		try {
			SecretKeySpec secretKeySpec = createKey(key);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			byte [] chain = Base64.getDecoder().decode(decrypt_pass);
			byte [] decryptedChain = cipher.doFinal(chain);
			return new String(decryptedChain);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
