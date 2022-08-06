package com.kaparzh.simplecrud.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kaparzh.simplecrud.model.Label;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private static final String JSON_PATH = "src/main/resources/labels.json";
    private final List<Label> labelList;

    public GsonLabelRepositoryImpl() {
        try {
            Type listType = new TypeToken<ArrayList<Label>>() {}.getType();
            labelList = new Gson().fromJson(new JsonReader(new FileReader(JSON_PATH)), listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Label save(Label value) {
        labelList.add(value);
        writeLabelList();
        return value;
    }

    @Override
    public Label update(Label value) {
        Scanner sc = new Scanner(System.in);
        labelList.stream()
                .filter(label -> label.equals(value))
                .forEach(label -> {
                    System.out.println("Set new label name:");
                    label.setName(sc.nextLine());
                });
        writeLabelList();
        return value;
    }

    @Override
    public void deleteById(Integer id) {
        labelList.removeIf(label -> label.getId() == id);
        writeLabelList();
    }

    @Override
    public Label getById(Integer id) {
        for (Label label : labelList) {
            if (label.getId() == id) {
                System.out.println(label);
                return label;
            }
        }
        System.out.println("Nothing was found by this id");
        return null;
    }

    @Override
    public List<Label> getAll() {
        labelList.forEach(System.out::println);
        return labelList;
    }

    private void writeLabelList() {
        String json = new Gson().toJson(labelList);
        try (FileWriter fw = new FileWriter(JSON_PATH)) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
