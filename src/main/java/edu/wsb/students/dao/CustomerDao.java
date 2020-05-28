package edu.wsb.students.dao;

import edu.wsb.students.model.Customer;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

	public void addCustomer(Customer customer) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
										   .openSession();
			transaction = session.beginTransaction();
			session.save(customer);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			customers = session.createQuery("from Customer", Customer.class).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return customers;
	}

	public void deleteCustomer(int customerId) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Customer customer = session.load(Customer.class, customerId);
			session.delete(customer);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (e instanceof EntityNotFoundException) {
				System.out.println("Unknown customer!(check customer id)");
			}
			if (e.getCause() instanceof ConstraintViolationException) {
				System.out.println("The customer did not return car!");
			}
		}
	}

}
