package fr.remi.jegard.demojpa.bo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE_DEBUT")
    private String dateDebut;

    @Column(name = "DATE_FIN")
    private String dateFin;

    @Column(name = "DELAI")
    private Integer delai;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "emprunt_livre",
            joinColumns = @JoinColumn(name = "emprunt_id"),
            inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    private List<Livre> livres;

    public Emprunt() {}

    public Emprunt(String dateDebut, String dateFin, Client client, List<Livre> livres) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.livres = livres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", clientId=" + (client != null ? client.getId() : "null") +
                ", nombre de livres=" + (livres != null ? livres.size() : 0) +
                '}';
    }

}