package dk.eaaa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	
	private @Id @GeneratedValue Long id;
	private String name;
	//@ManyToOne(cascade = CascadeType.ALL)
	//private School school;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	public School getSchool() {
//		return school;
//	}
//	
//	public void setSchool(School school) {
//		this.school = school;
//	}
//	
//	public Student(String name, School school) {
//		super();
//		this.name = name;
//		this.school = school;
//	}
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student() {}
	
}
