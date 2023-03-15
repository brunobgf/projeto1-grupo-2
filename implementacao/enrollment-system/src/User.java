import java.util.*;

public class User {

	private int accessLevel;
	private List<Course> courses;
	private String user;
	private int keyAccess;
	private int age;
	private String gender;
	private String password;

	User(String[] dados, List<Course> courses) {
		user = dados[0];
		keyAccess = Integer.parseInt(dados[1]);
		age = Integer.parseInt(dados[2]);
		gender = dados[3];
		password = dados[4];
		this.courses = courses;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getKeyAccess() {
		return keyAccess;
	}

	public void setKeyAccess(int keyAccess) {
		this.keyAccess = keyAccess;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Course getCourse(String strName) {
		try {
			return courses.stream()
					.filter(c -> c.getName().equals(strName))
					.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "--> " + getUser();
	}

}
