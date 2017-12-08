package spheres.his.pojo;

import java.util.Arrays;

public class Traveler {

	int index;
	String name;
	String lastname;
	String salutation;
	int age;
	int[] shared_index;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int edad) {
		this.age = edad;
	}
	public int[] getShared_index() {
		return shared_index;
	}
	public void setShared_index(int[] shared_index) {
		this.shared_index = shared_index;
	}
	@Override
	public String toString() {
		return "Traveler [index=" + index + ", name=" + name + ", lastname=" + lastname + ", salutation=" + salutation
				+ ", edad=" + age + ", shared_index=" + Arrays.toString(shared_index) + "]";
	}
	
	
}
