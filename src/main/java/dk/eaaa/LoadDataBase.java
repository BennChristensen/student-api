package dk.eaaa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {

	@Bean
	CommandLineRunner initDataBase(StudentRepository repository, SchoolRepository schoolRepo) {
		return args -> {
			School eaaa = new School("EAAA");
			Student tom = new Student("Tom");
			eaaa.addStudent(tom);
			schoolRepo.save(eaaa);
			repository.save(tom);
			//repository.save(new Student("Steve Jobs", new School("Reed College")));
			//repository.save(new Student("Bill Gates", new School("Harvard College")));
		};
	}
}
