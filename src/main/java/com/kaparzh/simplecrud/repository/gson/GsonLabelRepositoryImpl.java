package com.kaparzh.simplecrud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.repository.LabelRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private static final String JSON_PATH = "src/main/resources/labels.json";
    private final Gson GSON = new Gson();

    @Override
    public Label save(Label value) {
        List<Label> labelList = getAllLabelsInternal();
        value.setId(generateId(labelList));
        labelList.add(value);

        writeLabelList(labelList);
        return value;
    }

    @Override
    public Label update(Label value) {
        List<Label> labelList = getAllLabelsInternal();
        labelList.forEach(l -> {
            if (l.getId().equals(value.getId())) {
                l.setName(value.getName());
            }
        });

        writeLabelList(labelList);
        return value;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labelList = getAllLabelsInternal();
        labelList.removeIf(label -> label.getId().equals(id));
        writeLabelList(labelList);
    }

    @Override
    public Label getById(Integer id) {
        return getAllLabelsInternal().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Label> getAll() {
        return getAllLabelsInternal();
    }


    private Integer generateId(List<Label> labels) {
        Label maxIdLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.nonNull(maxIdLabel) ? maxIdLabel.getId() + 1 : 1;
    }

    private List<Label> getAllLabelsInternal() {
        try {
            Type listType = new TypeToken<ArrayList<Label>>() {
            }.getType();
            return GSON.fromJson(new JsonReader(new FileReader(JSON_PATH)), listType);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
//            return Collections.emptyList();
            return new ArrayList<>();
        }
    }

    private void writeLabelList(List<Label> labelList) {
        String json = GSON.toJson(labelList);
        try (FileWriter fw = new FileWriter(JSON_PATH)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
