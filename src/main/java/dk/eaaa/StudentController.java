package dk.eaaa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	private final StudentRepository repository;
	
	public StudentController(StudentRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("students")
	Iterable<Student> getAllStudents() {
		return repository.findAll();
	}
		
	@GetMapping("students/{id}")
	Student getStudent(@PathVariable long id) {
		return repository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
	}
	
}
