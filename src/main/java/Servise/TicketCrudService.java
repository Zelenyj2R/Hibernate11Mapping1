package Servise;

import entity.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TicketCrudService {



    public final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    public Optional<Ticket> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Ticket.class, id));
        }
    }

    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
            Root<Ticket> root = criteriaQuery.from(Ticket.class);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public void create(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                if (validTicket(ticket)) {
                    session.save(ticket);
                    transaction.commit();
                } else {
                    transaction.rollback();
                    throw new IllegalArgumentException("Invalid ticket data");
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                if (validTicket(ticket)) {
                    session.update(ticket);
                    transaction.commit();
                } else {
                    transaction.rollback();
                    throw new IllegalArgumentException("Invalid ticket data");
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public void delete(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    private boolean validTicket(Ticket ticket) {
        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        return ticket.getClient() != null && ticket.getFromPlanet() != null && ticket.getToPlanet() != null
                && clientCrudService.get(ticket.getClient().getId()).isPresent()
                && planetCrudService.get(ticket.getFromPlanet().getId()).isPresent()
                && planetCrudService.get(ticket.getToPlanet().getId()).isPresent();
    }
}

