package edu.wsb.students.dao;

import edu.wsb.students.model.Order;
import edu.wsb.students.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

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
