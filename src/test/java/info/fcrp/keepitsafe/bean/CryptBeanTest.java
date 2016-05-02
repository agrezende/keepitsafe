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

package info.fcrp.keepitsafe.bean;

import static org.junit.Assert.*;
import info.fcrp.keepitsafe.util.Crypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class CryptBeanTest {

    @Test
    public void crypt() {
        String plain = "plain";
        String crypted = Crypt.crypt(plain);
        String decrypted = Crypt.decrypt(crypted);
        
        assertEquals(plain, decrypted);
    }
    
	@Test
	public void assymetric() throws NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024, new SecureRandom());
		KeyPair kp = kpg.generateKeyPair();
		PrivateKey priKey = kp.getPrivate();
		PublicKey pubKey = kp.getPublic();
		
		Cipher c = Cipher.getInstance("RSA");
		String plain = "plain";
		byte[] plainBytes = plain.getBytes();
		
		c.init(Cipher.ENCRYPT_MODE, pubKey);
		c.update(plainBytes);
		
		byte[] encBytes = c.doFinal();
		String enc = Base64.encodeBase64String(encBytes);
		assertNotSame(plain, enc);
		
		c.init(Cipher.DECRYPT_MODE, priKey);
		c.update(encBytes);
		byte[] decBytes = c.doFinal();
		String dec = new String(decBytes);
		
		assertEquals(plain, dec);
	}
	
	@Test
	public void symetric() throws NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256, new SecureRandom());
		Key k = kg.generateKey();
		
		Cipher c = Cipher.getInstance("AES");
		String plain = "plain";
		byte[] plainBytes = plain.getBytes();
		
		c.init(Cipher.ENCRYPT_MODE, k);
		c.update(plainBytes);
		
		byte[] encBytes = c.doFinal();
		String enc = Base64.encodeBase64String(encBytes);
		assertNotSame(plain, enc);
		
		c.init(Cipher.DECRYPT_MODE, k);
		c.update(encBytes);
		byte[] decBytes = c.doFinal();
		String dec = new String(decBytes);
		
		assertEquals(plain, dec);
	}
}
