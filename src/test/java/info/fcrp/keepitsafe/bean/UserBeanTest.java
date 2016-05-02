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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class UserBeanTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void create() throws NoSuchAlgorithmException,
			NoSuchProviderException {
//		UserBean uBean = context.getBean(UserBean.class);
//
//		User usr = uBean.get("ana");
//		assertNull(usr);
//
//		usr = uBean.create("ana");
//
//		assertTrue(usr.getId() > 0);
//
//		User usr2 = uBean.get("ana");
//		assertEquals(usr.getId(), usr2.getId());
//
//		uBean.create("bob");
//		uBean.create("charlie");
	}

	private String generatePublicKey() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024, new SecureRandom());
		KeyPair kp = kpg.generateKeyPair();
		PublicKey pubKey = kp.getPublic();

		return Base64.encodeBase64String(pubKey.getEncoded());
	}
}
