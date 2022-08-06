package com.kaparzh.simplecrud.controller;

import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.repository.GsonLabelRepositoryImpl;
import com.kaparzh.simplecrud.repository.LabelRepository;

public class LabelController {

    private static final LabelRepository labels = new GsonLabelRepositoryImpl();

    public void createLabel(int id, String labelName) {
        labels.save(new Label(id, labelName));
    }

    public void getAll() {
        labels.getAll();
    }

    public void deleteById(int id) {
        labels.deleteById(id);
    }

    public void updateLabel(int id, String labelName) {
        labels.update(new Label(id, labelName));
    }

    public void getById(int id) {
        labels.getById(id);
    }
}
