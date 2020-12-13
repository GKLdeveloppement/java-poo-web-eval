package com.example.eval.web.controller;

import com.example.eval.web.model.Album;
import com.example.eval.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    //Add album
    @RequestMapping(value="",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Album addAlbum(
            @RequestBody Album album){
        return albumRepository.save(album);
    }

    //Suppression d'un album
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void supprAlbum (@PathVariable("id") Integer id) {
        albumRepository.deleteById(id);
    }
}
