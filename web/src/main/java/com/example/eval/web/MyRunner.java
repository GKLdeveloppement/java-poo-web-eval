package com.example.eval.web;

import com.example.eval.web.repository.AlbumRepository;
import com.example.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner{
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public void run(String ... strings) throws Exception {
        String url = "jdbc:mysql://localhost:3306/audio?serverTimezone=UTC";
        String user= "root"; String pwd = "";
        java.sql.Connection conn = null;
        try {
            //Partie connexion bdd
            conn = java.sql.DriverManager.getConnection(url, user, pwd);
            System.out.println("Accès bdd ok");

            //Partie requetes test code
            //System.out.println(artistRepository.count());
            System.out.println(albumRepository.findById(2));
            //System.out.println(artistRepository.findByNameIgnoreCase("accept"));
            //System.out.println(artistRepository.findByContainName("rosmi"));
            System.out.println(artistRepository.findByContainName("ABCDEF"));

            //System.out.println(albumRepository.findAll());

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

        if(conn != null) {
            try {
                conn.close();
            }
            catch (java.sql.SQLException e) {

            }
        }
    }
}
