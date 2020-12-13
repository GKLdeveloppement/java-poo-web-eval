package com.example.eval.web.controller;

import com.example.eval.web.model.Artist;
import com.example.eval.web.repository.AlbumRepository;
import com.example.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    /*
    //Count (test)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long countArtist() {
        return artistRepository.count();
    }
    */

    //ID
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist artistInfo(@PathVariable(value = "id") Integer id)
    {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()){
            //gestion 404
            throw new EntityNotFoundException("L'artiste avec l'id : " + id + " n'existe pas.");
        }
        return artist.get();
    }

    //Name
    @RequestMapping(value="",
            method = RequestMethod.GET,
            produces =MediaType.APPLICATION_JSON_VALUE,
            params = "name")
    public List<Artist> ListingArtistByName(@RequestParam("name") String name){
        List<Artist> artist = artistRepository.findByContainName(name);
        return artist;
    }

    //Pagination artistes
    @RequestMapping(value="",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<Artist> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page ,
                                @RequestParam(value="size", defaultValue = "50")Integer size,
                                @RequestParam(value = "sortProperty", defaultValue = "name") String sProp,
                                @RequestParam(value = "sortDirection", defaultValue = "ASC") String sDir)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.fromString(sDir), sProp);
        return artistRepository.findAll(pageable);
    }


    //Add artist
    @RequestMapping(value="",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Artist addArtist(
            @RequestBody Artist artist){
        if(artistRepository.findByName(artist.getName())!=null){
            throw new EntityExistsException("Déjà présent dans la bdd");
        }else {
            return artistRepository.save(artist);
        }
    }

    //Update artist
    @RequestMapping(value="{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(@RequestBody Artist artist,@PathVariable Integer id){
        return artistRepository.save(artist);
    }

    //Delete artist
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void supprArtist (@PathVariable("id") Integer id) {
        artistRepository.deleteById(id);
        albumRepository.deleteByArtistId(id);
    }

}
