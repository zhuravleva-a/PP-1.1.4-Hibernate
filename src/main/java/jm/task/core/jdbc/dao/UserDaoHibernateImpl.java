package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            String SQLRequest = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age TINYINT)";
            Query query = session.createSQLQuery(SQLRequest).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            String SQLRequest = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(SQLRequest);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            String HQLRequest = "delete from User user where user.id = ?1";
            Query deleteQuery = session.createQuery(HQLRequest);
            deleteQuery.setParameter(1, id);
            deleteQuery.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try(Session session = Util.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Query query = session.createQuery(criteriaQuery);
            allUsers = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            String HQLRequest = "delete from User";
            Query deleteQuery = session.createQuery(HQLRequest);
            deleteQuery.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
