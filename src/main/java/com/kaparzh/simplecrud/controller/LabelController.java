package com.kaparzh.simplecrud.controller;

import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.repository.gson.GsonLabelRepositoryImpl;
import com.kaparzh.simplecrud.repository.LabelRepository;

import java.util.List;

public class LabelController {

    private final LabelRepository labels = new GsonLabelRepositoryImpl();

    public Label createLabel(int id, String labelName) {
        return labels.save(new Label(id, labelName));
    }

    public List<Label> getAll() {
        return labels.getAll();
    }

    public void deleteById(int id) {
        labels.deleteById(id);
    }

    public Label updateLabel(int id, String labelName) {
        return labels.update(new Label(id, labelName));
    }

    public Label getById(int id) {
        return labels.getById(id);
    }
}
