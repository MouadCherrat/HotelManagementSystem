package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.User;

import java.util.List;
import java.util.Optional;

public class LoginService {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public LoginService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HMS");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Optional<User> save (User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public User loginCheck(String mail, String password){

            List user = entityManager.createQuery("select u from User u where u.email=:mail and u.password=:password")
                    .setParameter("mail",mail)
                    .setParameter("password",password)
                    .getResultList();

            if (user.isEmpty()){
                System.out.println("user doenst exixst");
                return null;
            }

            return (User)user.get(0) ;
    }


}
