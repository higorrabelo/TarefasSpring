package com.app.programa.libs;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class HashMD5 { 
    public static String getMD5(String msg){
        MessageDigest md;
            try{
                md = MessageDigest.getInstance("MD5");
            }
            catch(NoSuchAlgorithmException ex){
                throw new RuntimeException(ex);
            }
            BigInteger hash = new BigInteger(1, md.digest(msg.getBytes()));
            return hash.toString(16);
    }
}
