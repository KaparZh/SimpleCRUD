package com.kaparzh.simplecrud.controller;

import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.model.Post;
import com.kaparzh.simplecrud.repository.PostRepository;
import com.kaparzh.simplecrud.repository.gson.GsonPostRepositoryImpl;

import java.util.Date;
import java.util.List;

public class PostController {

    private final PostRepository posts = new GsonPostRepositoryImpl();

    public Post createPost(String content, List<Label> labels) {
        long created = new Date().getTime();
        return posts.save(new Post(1, content, created, created, labels));
    }

    public Post updatePost(int id, String content, List<Label> labels, long created) {
        long updated = new Date().getTime();
        return posts.update(new Post(id, content, created, updated, labels));
    }

    public void deleteById(int id) {
        posts.deleteById(id);
    }

    public Post getById(int id) {
        return posts.getById(id);
    }

    public List<Post> getAll() {
        return posts.getAll();
    }
}
