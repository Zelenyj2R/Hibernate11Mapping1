package Servise;

import entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;




public class ClientCrudService {
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public void create(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(client);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Optional<Client> get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Client.class, id));
        }
    }

    public void update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(client);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public void delete(Long clientId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Client client = session.get(Client.class, clientId);
                if (client != null) {
                    session.delete(client);
                    transaction.commit();
                } else {
                    throw new IllegalArgumentException("Client not found");
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public List<Client> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM entity.Client", Client.class).list();
        }
    }
}