package com.example.eval.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {

    //-------Instantiation des attributs (voir bdd)-------//
    //Clé primaire en auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Integer id;

    //Colonne Name
    @Column(name = "name")
    private String name;


    //Clé étrangère pour avoir plusieurs albums pour un artiste
    //On met en place les cascades pour les dépendances
    @OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("artist")
    private List<Album> albums;


    //Constructeur de base vide
    public Artist(){ }

    //Constructeur prenant en compte les attributs declarés précèdemment
    public Artist(String name) {
        this.name = name;
    }


    //----Getter&Setter pour la clé primaire (ArtistId)----//
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //----Getter&Setter pour le champ Name----//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //----Getter&Setter pour la clé étrangère----//
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }


    //Re-définition de la méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(albums, artist.albums) &&
                Objects.equals(id, artist.id) &&
                Objects.equals(name, artist.name);
    }

    //Re-définition de la méthode hashCode
    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name);
    }

    //Re-définition de la méthode toString
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }

}
