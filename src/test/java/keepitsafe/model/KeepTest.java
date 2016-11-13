package keepitsafe.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import keepitsafe.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)//, loader = AnnotationConfigContextLoader.class)
public class KeepTest {

	@Autowired
	private KeepDAO keepDAO;
	
	@Test
	public void test() {
		Keep keep = new Keep("Teste");
		keepDAO.save(keep);
	}

}
