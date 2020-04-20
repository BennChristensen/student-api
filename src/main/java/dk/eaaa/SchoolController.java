package dk.eaaa;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	Iterable<School> getAllSchools() {
		return repository.findAll();
	}

	@PostMapping("schools")
	ResponseEntity<School> newSchool(@RequestBody School newSchool) {
		repository.save(newSchool);
		return new ResponseEntity<School>(newSchool, HttpStatus.CREATED);
	}

	@GetMapping("schools/{id}")
	School getSchool(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
	}

	@GetMapping("schools/{id}/students")
	List<Student> getStudentsFromSchool(@PathVariable Long id) {
		return repository.findById(id).map(School::getStudents).orElseThrow(() -> new SchoolNotFoundException(id));
	}

	@PostMapping("schools/{id}/students")
	ResponseEntity<Student> createNewStudent(@PathVariable Long id, @RequestBody Student newStudent) {
		School school = repository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
		school.addStudent(newStudent);
		studentRepo.save(newStudent);
		return new ResponseEntity<Student>(newStudent, HttpStatus.CREATED);
	}
	
	@PatchMapping("schools/{schoolId}/students/{studentId}")
	Student moveSchool(@PathVariable Long schoolId, @PathVariable Long studentId) {
		School newSchool = repository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		School oldSchool = repository.findById(student.getSchool().getId()).orElseThrow(() -> new SchoolNotFoundException(student.getSchool().getId()));
		newSchool.moveStudent(student, oldSchool);
		//TODO: add database transaction
		studentRepo.save(student);
		repository.save(newSchool);
		repository.save(oldSchool);
		return student;
	}

//	@PutMapping("schools/{id}/students/{studentId}")
//	Student addStudentToSchool(@PathVariable Long id, @PathVariable Long studentId) {
//		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
//		School school = repository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
//		school.addStudent(student);
//		return student;
//	}

}
