package dk.eaaa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class School {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	private String name;
	@OneToMany(mappedBy = "school")
	@JsonBackReference 
	private List<Student> students = new ArrayList<Student>();
	
	public School() {}
	public School(String name) {
		super();
		this.name = name;
	}
	
	public void addStudent(Student student) {
		students.add(student);
		student.setSchool(this);
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void moveStudent(Student student, School oldSchool) {
		oldSchool.getStudents().remove(student);
		this.addStudent(student);
	}	
}
