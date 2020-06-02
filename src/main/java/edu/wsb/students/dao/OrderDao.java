package edu.wsb.students.dao;

import edu.wsb.students.model.Order;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Dao - która zawiera metody dotyczące Order, służy do komunikacji z bazą
public class OrderDao {

    /* Dodanie nowego Order`a do bazy
     * 1. Utworzenie sesji oraz tranzakcji
     * 2. Save z obiektem order oraz komit na bazie
     * 3. Gdy wystąpi błąd zostanie wyświetlony */
    public void addOrder(Order order) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof ConstraintViolationException) {
                System.out.println("Check entered id`s!");
            }
        }
    }

    /* Pobieranie wszystkich Order`ów z bazy
     * 1. Utworzenie sesji oraz tranzakcji
     * 2. Pobieranie listy zleceń
     * 3. Gdy wystąpi błąd zostanie wyświetlony */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            orders = session.createQuery("from Order", Order.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return orders;
    }

    public Order getOrderById(int id) {
        Order order = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            order = session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return order;
    }

    /* Usunięcie Order`a z bazy
     * 1. Utworzenie sesji oraz tranzakcji
     * 2. Pobieranie po orderId zlecenia z bazy
     * 3. Usunięcie zlecenia z bazy
     * 3. Gdy nie istnieje zlecenie o danym orderId,
     *    wyświetli odpowiedni komunikat */
    public void deleteOrder(int orderId) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();
            Order order = session.load(Order.class, orderId);
            session.delete(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof EntityNotFoundException) {
                System.out.println("Unknown order!(check order id)");
            }
        }
    }

}
