package dk.eaaa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class School {
	
	private @Id @GeneratedValue Long id;
	private String name;
	@OneToMany
	@JoinColumn(name = "fk_school")
	private List<Student> students = new ArrayList<Student>();
	
	public School() {}
	public School(String name) {
		super();
		this.name = name;
	}
	
	public void addStudent(Student student) {
		students.add(student);
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
}
