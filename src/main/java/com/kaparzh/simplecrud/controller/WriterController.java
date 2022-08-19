package com.kaparzh.simplecrud.controller;

import com.kaparzh.simplecrud.model.Post;
import com.kaparzh.simplecrud.model.Writer;
import com.kaparzh.simplecrud.repository.WriterRepository;
import com.kaparzh.simplecrud.repository.gson.GsonWriterRepositoryImpl;

import java.util.List;

public class WriterController {

    private final WriterRepository writers = new GsonWriterRepositoryImpl();

    public Writer addWriter(String firstName, String lastName, List<Post> postList) {
        return writers.save(new Writer(1, firstName, lastName, postList));
    }

    public Writer updateWriter(int id, String firstName, String lastName, List<Post> postList) {
        return writers.update(new Writer(id, firstName, lastName, postList));
    }

    public void deleteById(int id) {
        writers.deleteById(id);
    }

    public Writer getById(int id) {
        return writers.getById(id);
    }

    public List<Writer> getAll() {
        return writers.getAll();
    }
}
