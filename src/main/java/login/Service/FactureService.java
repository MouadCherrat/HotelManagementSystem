package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.Facture;

import java.util.Optional;

public class FactureService {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public FactureService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HMS");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public Optional<Facture> save(Facture facture) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(facture);
            entityManager.getTransaction().commit();
            return Optional.of(facture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
