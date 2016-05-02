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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class KeepBeanTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void creation() {
//		UserBean uBean = context.getBean(UserBean.class);
//		User ana = uBean.get("ana");
//		User bob = uBean.get("ana");
//		User charlie = uBean.get("ana");
//
//		KeepBean kpBean = context.getBean(KeepBean.class);
//		Keep computers = kpBean.create("computers");
//		Keep servers = kpBean.create("servers");
//
//		uBean.addRight(ana, computers);
//		uBean.addRight(bob, computers);
//		uBean.addRight(charlie, computers);
//
//		uBean.addRight(ana, servers);
//
//		assertEquals(2, ana.getRights().size());
//		assertEquals(1, bob.getRights().size());
//		assertEquals(1, charlie.getRights().size());
	}

}
