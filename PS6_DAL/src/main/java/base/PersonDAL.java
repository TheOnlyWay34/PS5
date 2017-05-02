package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = ses.beginTransaction();
			ses.save(per);
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
		return per;
	}

	public static ArrayList<PersonDomainModel> getPersons() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;		
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		
		try {
			t = ses.beginTransaction();	
			
			List persons = ses.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				pers.add(per);

			}
			
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
		return pers;

	}

	public static PersonDomainModel getPerson(UUID perID) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		PersonDomainModel perGet = null;		
		
		try {
			t = ses.beginTransaction();	
									
			Query query = ses.createQuery("from PersonDomainModel where personId = :id ");
			query.setParameter("id", perID.toString());
			
			List<?> list = query.list();
			perGet = (PersonDomainModel)list.get(0);
			
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
		return perGet;
	}

	public static void deletePerson(UUID perID) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;		
		
		try {
			t = ses.beginTransaction();	
									
			PersonDomainModel per = (PersonDomainModel) ses.get(PersonDomainModel.class, perID);
			ses.delete(per);
		
			
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;		
		
		try {
			t = ses.beginTransaction();	
									
			ses.update(per);
	
			
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
		
		return per;
	}
}