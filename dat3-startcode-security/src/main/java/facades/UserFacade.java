package facades;

import dtos.UserDTO;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

import security.errorhandling.AuthenticationException;
import callables.ApiFetchCallable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.WeatherDTO;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;
    private Gson gson = new Gson();

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public UserDTO createUserDTO(UserDTO user) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        User u = new User(user);
        try {
            em.getTransaction().begin();
            if (user == null) {
                throw new WebApplicationException("Id does not exist");
            }
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(u);
    }

    public void removeUser(String userName) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            em.getTransaction().begin();
            user = em.find(User.class, userName);
            if (user == null) {
                throw new WebApplicationException("user name does not exist");
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }




}

       
