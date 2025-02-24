package fr.remi.jegard.demojpa;

import fr.remi.jegard.demojpa.bo.Emprunt;
import fr.remi.jegard.demojpa.bo.Livre;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa")) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();


            Emprunt emprunt = em.find(Emprunt.class, 1);
            if (emprunt != null) {
                System.out.println("Emprunt trouvé, ID : " + emprunt.getId());
                System.out.println("Livres empruntés : ");
                for (Livre livre : emprunt.getLivres()) {
                    System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
                }
            }

            // Exemple : récupérer tous les emprunts d'un client donné (id=1)
            TypedQuery<Emprunt> query = em.createQuery(
                    "SELECT e FROM Emprunt e WHERE e.client.id = :clientId", Emprunt.class);
            query.setParameter("clientId", 1);
            query.getResultList().forEach(e ->
                    System.out.println("Emprunt ID : " + e.getId() + ", Date début : " + e.getDateDebut())
            );

            em.getTransaction().commit();
            em.close();
        }
    }
}
