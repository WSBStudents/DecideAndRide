package edu.wsb.students.dao;

import edu.wsb.students.model.Customer;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Dao - która zawiera metody dotyczące Customera, służy do komunikacji z bazą
public class CustomerDao {

	/* Dodanie nowego Customer`a do bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Save z obiektem customer oraz komit na bazie
	 * 3. Gdy wystąpi błąd zostanie wyświetlony */
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

	/* Pobieranie wszystkich Customer`ów z bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Pobieranie listy klientów
	 * 3. Gdy wystąpi błąd zostanie wyświetlony */
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

	/* Usunięcie Customer`a z bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Pobieranie po customerId klienta z bazy
	 * 3. Usunięcie klienta z bazy
	 * 3. Gdy nie istnieje klienta o danym customerId lub nie zakonczył wszystkie zlecenia,
	 *    wyświetli odpowiedni komunikat */
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

	/* Pobieranie wszystkich Customer`ów z bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Pobieranie listy klientów
	 * 3. Gdy wystąpi błąd zostanie wyświetlony */
	public boolean checkAvailableDocumentId(String documentId) {
		List<Customer> customers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			customers = session.createQuery("from Customer c where c.documentId = :documentId", Customer.class).setParameter("documentId", documentId).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return customers.isEmpty();
	}

	// Sprawdzenie czy występuje driverLicenceId w bazie
	public boolean checkAvailableDriverLicenceId(String drivingLicenceId) {
		List<Customer> customers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			customers = session.createQuery("from Customer c where c.drivingLicenceId = :drivingLicenceId", Customer.class).setParameter("drivingLicenceId", drivingLicenceId).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return customers.isEmpty();
	}

}
