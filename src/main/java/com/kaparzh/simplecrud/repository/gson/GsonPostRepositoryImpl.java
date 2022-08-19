package com.kaparzh.simplecrud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.model.Post;
import com.kaparzh.simplecrud.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonPostRepositoryImpl implements PostRepository {

    private static final String JSON_PATH = "src/main/resources/posts.json";
    private final Gson GSON = new Gson();

    @Override
    public Post save(Post value) {
        List<Post> postList = getAllPostsInternal();
        value.setId(generateId(postList));
        postList.add(value);

        writePostList(postList);
        return value;
    }

    @Override
    public Post update(Post value) {
        List<Post> postList = getAllPostsInternal();
        postList.forEach(p -> {
            if (p.getId() == value.getId()) {
                p.setContent(value.getContent());
                p.setLabels(value.getLabels());
                p.setUpdated(value.getUpdated());
            }
        });

        writePostList(postList);
        return value;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> postList = getAllPostsInternal();
        postList.removeIf(post -> post.getId().equals(id));
        writePostList(postList);
    }

    @Override
    public Post getById(Integer id) {
        return getAllPostsInternal().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return getAllPostsInternal();
    }

    private Integer generateId(List<Post> posts) {
        Post maxIdPost = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.nonNull(maxIdPost) ? maxIdPost.getId() + 1 : 1;
    }

    private List<Post> getAllPostsInternal() {
        try {
            Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
            return GSON.fromJson(new JsonReader(new FileReader(JSON_PATH)), listType);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
//            return Collections.emptyList();
            return new ArrayList<>();
        }
    }

    private void writePostList(List<Post> postList) {
        String json = GSON.toJson(postList);
        try (FileWriter fw = new FileWriter(JSON_PATH)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
