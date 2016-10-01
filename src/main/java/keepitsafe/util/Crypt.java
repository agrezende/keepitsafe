/*
 * Copyright 2012 Felipe C. do R. P.
 *
 * This file is part of Keep It Safe.
 * 
 * Keep It Safe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Keep It Safe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Keep It Safe.  If not, see <http://www.gnu.org/licenses/>.
 */

package keepitsafe.util;

import static org.junit.Assert.assertNotSame;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypt {
    private static String password;
    private static Cipher cryptCipher;
    private static Cipher decryptCipher;

    private static void init() {
        if (cryptCipher == null || decryptCipher == null) {
            try {
                SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(),
                        "AES");
                cryptCipher = Cipher.getInstance("AES");
                cryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
                
                decryptCipher = Cipher.getInstance("AES");
                decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
              
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            }
            // output = cipher.doFinal(input)
            //
            // Cipher c = Cipher.getInstance("AES");
            // String plain = "plain";
            // byte[] plainBytes = plain.getBytes();
            //
            // c.init(Cipher.ENCRYPT_MODE, k);
            // c.update(plainBytes);
            //
            // byte[] encBytes = c.doFinal();
            // String enc = Base64.encodeBase64String(encBytes);
            // assertNotSame(plain, enc);
            //
            // c.init(Cipher.DECRYPT_MODE, k);
            // c.update(encBytes);
            // byte[] decBytes = c.doFinal();
            // String dec = new String(decBytes);
            catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized static String crypt(String plain) {
        init();
        
        try {  
            cryptCipher.update(plain.getBytes());
            byte[] crypted = cryptCipher.doFinal();
            byte[] crypted64 = Base64.encodeBase64(crypted);
            String cryptedString = new String(crypted64);
            return cryptedString;
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static String decrypt(String cryptedString) {
        init();
        
        try {
            byte[] crypted64 = Base64.decodeBase64(cryptedString);
            decryptCipher.update(crypted64);
            byte[] plain = decryptCipher.doFinal();
            String plainString = new String(plain);
            return plainString;
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        Crypt.password = password;
    }
}
