package edu.wsb.students.dao;

import edu.wsb.students.model.Car;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Dao - która zawiera metody dotyczące Car, służy do komunikacji z bazą
public class CarDao {

	/* Dodanie nowego Car`a do bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Save z obiektem customer oraz komit na bazie
	 * 3. Gdy wystąpi błąd zostanie wyświetlony */
	public void addCar(Car car) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.openSession();
			transaction = session.beginTransaction();
			session.save(car);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/* Pobieranie wszystkich Car`ów z bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Pobieranie listy samochodów
	 * 3. Gdy wystąpi błąd zostanie wyświetlony */
	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			cars = session.createQuery("from Car", Car.class).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return cars;
	}

	/* Usunięcie Car`a z bazy
	 * 1. Utworzenie sesji oraz tranzakcji
	 * 2. Pobieranie po carId car z bazy
	 * 3. Usunięcie samochodu z bazy
	 * 3. Gdy nie istnieje samochód o danym carId lub jeszcze jest wynajęty,
	 *    wyświetli odpowiedni komunikat */
	public void deleteCar(int carId) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.openSession();
			transaction = session.beginTransaction();
			Car car = session.load(Car.class, carId);
			session.delete(car);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (e instanceof EntityNotFoundException) {
				System.out.println("The car is not in system!");
			}
			if (e.getCause() instanceof ConstraintViolationException) {
				System.out.println("The car is borrowed!(check car id)");
			}
		}
	}

	// Sprawdzenie czy występuje wpis samochodu z podanym vinNumber w bazie
	public boolean checkAvailableVinNumber(String vinNumber) {
		List<Car> cars = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			cars = session.createQuery("from Car c where c.vinNumber = :vinNumber", Car.class).setParameter("vinNumber", vinNumber).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return cars.isEmpty();
	}

}
