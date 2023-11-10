package data_manager.handlePassCipher;

import org.jetbrains.annotations.NotNull;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Encrypt {

	public Encrypt(){
	}
	public String encodeString(@NotNull String pass) throws RuntimeException{
		try {
			byte[] chain = Base64.getEncoder().encode(pass.getBytes(StandardCharsets.UTF_8));
			return new String(chain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public String decryptString(String to_decrypt) throws RuntimeException{
		try {
			byte[] chain = Base64.getDecoder().decode(to_decrypt);
			return new String(chain);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
