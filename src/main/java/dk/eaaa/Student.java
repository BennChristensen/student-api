package dk.eaaa;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Student {
	
	private @Id @GeneratedValue 
	Long id;
	private String name;
	@ManyToOne()
	@JoinColumn(name ="school_id")
	private School school;
	
	@ManyToMany
	@JoinTable(name = "subject_student", 
				joinColumns = @JoinColumn(name = "subject_id"),
				inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Subject> subjects;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student() {}
	
}
