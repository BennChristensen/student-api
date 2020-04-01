package dk.eaaa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

	private final SchoolRepository repository;
	private final StudentRepository studentRepo; 
	
	public SchoolController(SchoolRepository repository, StudentRepository studentRepo) {
		this.repository = repository;
		this.studentRepo = studentRepo;
	}
	
	@GetMapping("schools")
	Iterable<School> getAllStudents() {
		return repository.findAll();
	}
	
	@PostMapping("schools")
	School newStudent(@RequestBody School newSchool) {
		return repository.save(newSchool);
	}
	
	@GetMapping("schools/{id}")
	School getStudent(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new SchoolNotFoundException(id));
	}
	
	@GetMapping("schools/{id}/students") 
	List<Student> getStudentsFromSchool(@PathVariable Long id) {
		return repository.findById(id)
				.map(School::getStudents)
				.orElseThrow(() -> new SchoolNotFoundException(id));
	}

}
