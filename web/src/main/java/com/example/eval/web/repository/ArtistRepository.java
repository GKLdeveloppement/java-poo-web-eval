package com.example.eval.web.repository;

import com.example.eval.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    //recahrche en ignorant la case (reponds pas parfaitement au tp)
    List<Artist> findByNameIgnoreCase(String name);

    //Recherche du name contenant le param (good)
    @Query(value = "SELECT * FROM artist WHERE artist.Name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Artist> findByContainName(@Param("name") String name);

    Artist findByName(String name);


}
