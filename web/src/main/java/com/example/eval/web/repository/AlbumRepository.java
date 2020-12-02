package com.example.eval.web.repository;

import com.example.eval.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    //Album findByTitle(String title);

    List<Album> deleteByArtistId(Integer idArtist);
}
