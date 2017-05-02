package base;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersonDomainModel p = new PersonDomainModel();
		p.setFirstName("Matthew");
		p.setLastName("Jones");
		p.setBirthday(LocalDate.of(1995, 10, 29));
		p.setCity("MiddleofNowhereville");
		p.setPostalCode(46290);
		p.setStreet("157 N. South St.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per1;
		PersonDAL.deletePerson(p.getPersonID());
		per1 = PersonDAL.getPerson(p.getPersonID());
		assertNull(pers);
	}

	@Test
	public void TestAddPerson() {
		PersonDomainModel per1;
		PersonDomainModel per2;
		per1 = PersonDAL.getPerson(p.getPersonID());
		assertNull(per1);
		PersonDAL.addPerson(p);
		per2 = PersonDAL.getPerson(p.getpersonID());
		assertNotNull(per2);
	}

	@Test
	public void TestDeletePerson() {
		PersonDomainModel per1;
		PersonDomainModel per2;
		PersonDomainModel per3;
		per1 = PersonDAL.getPerson(p.getPersonID());
		PersonDAL.addPerson(p);
		per2 = PersonDAL.getPerson(p.getPersonID());
		PersonDAL.deletePerson(p.getPersonID());
		per3 = PersonDAL.getPerson(p.getPersonID());
		assertNull(per1);
		assertNotNull(per2);
		assertNull(per3);
	}

}
