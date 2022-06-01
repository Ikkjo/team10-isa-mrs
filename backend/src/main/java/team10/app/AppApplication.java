package team10.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team10.app.model.Admin;
import team10.app.repository.AdminRepository;
import team10.app.service.AdminService;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
		// Add Main Admin TODO: VLADANEEEEEEE
//		Admin admin = new Admin(
//				"Admin",
//				"Adminovic",
//				"admin@rentr.com",
//				"$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS");
//		admin.setMain(true); admin.setInitialPasswordChanged(true);
//		AdminService.createMainAdmin(admin);
	}

}
