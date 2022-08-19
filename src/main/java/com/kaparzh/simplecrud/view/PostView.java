package com.kaparzh.simplecrud.view;

import com.kaparzh.simplecrud.controller.LabelController;
import com.kaparzh.simplecrud.controller.PostController;
import com.kaparzh.simplecrud.model.Label;
import com.kaparzh.simplecrud.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {

    public static final String POST_MENU = "Select action:\n" +
            "1.Create post\n" +
            "2.Update post\n" +
            "3.Delete post\n" +
            "4.Get post by Id\n" +
            "5.Get list of all posts\n" +
            "0.Back to main menu\n";

    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();

    public void postViewRunner(int value) {
        switch (value) {
            case 0:
                break;
            case 1:
                createPost();
                break;
            case 2:
                updatePost();
                break;
            case 3:
                deletePost();
                break;
            case 4:
                getPostById();
                break;
            case 5:
                getAll();
                break;
        }
    }

    private void createPost() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter post content:");
        StringBuilder content = new StringBuilder();
        String line = sc.nextLine();
        while (!(line.equals("endPost"))) {
            content.append(line);
            line = sc.nextLine();
        }

        List<Label> postLabels = getPostLabels(sc);

        Post post = postController.createPost(content.toString(), postLabels);
        System.out.println("Created label: " + post);
    }

    private void updatePost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter post id");
        int id = Integer.parseInt(sc.nextLine());

        List<Post> postList = postController.getAll();
        for (Post p : postList) {
            if (p.getId() == id) {
                long created = p.getCreated();

                System.out.println("Enter new post content:");
                StringBuilder content = new StringBuilder();
                String line = sc.nextLine();
                while (!(line.equals("endPost"))) {
                    content.append(line);
                    line = sc.nextLine();
                }

                List<Label> postLabels = getPostLabels(sc);

                Post post = postController.updatePost(id, content.toString(), postLabels, created);
                System.out.println("Updated post: " + post);
            }
        }
    }

    private List<Label> getPostLabels(Scanner sc) {
        List<Label> allLabels = labelController.getAll();
        List<Label> postLabels = new ArrayList<>();
        System.out.println("Choose labels id (enter: 0, if you done):");
        allLabels.forEach(System.out::println);

        int labelId = sc.nextInt();
        while (labelId != 0) {
            for (Label label : allLabels) {
                if (label.getId() == labelId) {
                    postLabels.add(label);
                }
            }
            labelId = sc.nextInt();
        }
        return postLabels;
    }

    private void deletePost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter post id for delete:");
        int id = Integer.parseInt(sc.nextLine());
        postController.deleteById(id);
    }

    private void getPostById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter post id:");
        int id = Integer.parseInt(sc.nextLine());
        Post post = postController.getById(id);
        System.out.println("Post: " + post);
    }

    private void getAll() {
        List<Post> posts = postController.getAll();
        posts.forEach(System.out::println);
    }
}
