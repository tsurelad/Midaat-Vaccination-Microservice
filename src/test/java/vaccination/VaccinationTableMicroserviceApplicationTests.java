package vaccination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VaccinationTableMicroserviceApplication.class)
@WebIntegrationTest({
		"server.port=0",
		"management.port=0"
})
@Profile("test")
public class VaccinationTableMicroserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
