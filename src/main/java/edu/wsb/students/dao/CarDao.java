package edu.wsb.students.dao;

import edu.wsb.students.model.Car;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

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

}
