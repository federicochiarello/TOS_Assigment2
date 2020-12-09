package it.unipd.tos.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {

	private User user;
	private String name = "Luca";
	private String surname = "Toni";
	private int age = 43;
	
	@Before
	public void setUp() {
		user = new User(name,surname,age);
	}
	
	@Test
	public void getId_SecondUser_HasDifferentId() {
		User secondUser = new User("Federico","Chiarello",21);
		assertNotEquals(secondUser.getId(), user.getId());
	}
	
	@Test
	public void getName_IsEqual() {
		assertEquals(name, user.getName());
	}
	
	@Test
	public void getSurname_IsEqual() {
		assertEquals(surname, user.getSurname());
	}
	
	@Test
	public void getAge_IsEqual() {
		assertEquals(age, user.getAge());
	}
}
