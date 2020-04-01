package dk.eaaa;

import java.util.List;

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
	
	@PostMapping("students")
	Student newStudent(@RequestBody Student newStudent) {
		return repository.save(newStudent);
	}
	
	@GetMapping("students/{id}")
	Student getStudent(@PathVariable long id) {
		return repository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
	}
	
}
