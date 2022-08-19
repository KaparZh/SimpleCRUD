package com.kaparzh.simplecrud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kaparzh.simplecrud.model.Post;
import com.kaparzh.simplecrud.model.Writer;
import com.kaparzh.simplecrud.repository.WriterRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonWriterRepositoryImpl implements WriterRepository {

    private static final String JSON_PATH = "src/main/resources/writers.json";
    private final Gson GSON = new Gson();

    @Override
    public Writer save(Writer value) {
        List<Writer> writerList = getAllWritersInternal();
        value.setId(generateId(writerList));
        writerList.add(value);

        writeWriterList(writerList);
        return value;
    }

    @Override
    public Writer update(Writer value) {
        List<Writer> writerList = getAllWritersInternal();
        writerList.forEach(w -> {
            if (w.getId() == value.getId()) {
                w.setFirstName(value.getFirstName());
                w.setLastName(value.getLastName());
                w.setPosts(value.getPosts());
            }
        });

        writeWriterList(writerList);
        return value;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writerList = getAllWritersInternal();
        writerList.removeIf(writer -> writer.getId().equals(id));
        writeWriterList(writerList);
    }

    @Override
    public Writer getById(Integer id) {
        return getAllWritersInternal().stream()
                .filter(w -> w.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return getAllWritersInternal();
    }

    private Integer generateId(List<Writer> writers) {
        Writer maxIdWriter = writers.stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.nonNull(maxIdWriter) ? maxIdWriter.getId() + 1 : 1;
    }

    private List<Writer> getAllWritersInternal() {
        try {
            Type listType = new TypeToken<ArrayList<Writer>>() {}.getType();
            return GSON.fromJson(new JsonReader(new FileReader(JSON_PATH)), listType);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
//            return Collections.emptyList();
            return new ArrayList<>();
        }
    }

    private void writeWriterList(List<Writer> writerList) {
        String json = GSON.toJson(writerList);
        try (FileWriter fw = new FileWriter(JSON_PATH)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
