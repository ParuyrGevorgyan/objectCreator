package com;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private VBox vBox;
    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    void CreateObject(ActionEvent event) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        vBox.getChildren().clear();
        String name = textField.getText().trim();
        Object object = Class.forName(name).getConstructor().newInstance();
        Field[] fields = object.getClass().getDeclaredFields();

        System.out.println(fields[0].getType());
        
        for(Field f:fields){
            String fieldName = f.getName();
            String capName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method fieldGetter = object.getClass().getMethod("get"+ capName);

            Object value = fieldGetter.invoke(object);

            Label label = new Label("<-"+f.getName());
            TextField textField = new TextField(String.valueOf(value));

            HBox hBox = new HBox();
            hBox.getChildren().add(textField);
            hBox.getChildren().add(label);

            vBox.getChildren().add(hBox);
        }
    }
    @FXML
    void SaveObject(ActionEvent event) throws Exception {
        textArea.clear();
        List<Node> nodes = vBox.getChildren().stream().toList();
        List<HBox> hBoxes = new ArrayList<>();
        List<TextField> textFields = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        nodes.stream().forEach(node -> {if(node instanceof HBox) {
            System.out.println("dgfdg");
            hBoxes.add((HBox) node);
        }});
        hBoxes.stream().forEach(i -> i.getChildren().stream().forEach(z -> {
            if(z instanceof Label) labels.add((Label) z);
            else if (z instanceof TextField) textFields.add((TextField) z);
        }));

        String name = textField.getText().trim();
        Object object = Class.forName(name).getConstructor().newInstance();
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field f:fields){
            String fieldName = f.getName();
            String capName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method fieldSetter = object.getClass().getMethod("set"+ capName, f.getType());
            if (f.getType().getName().equals("java.lang.String")){
                fieldSetter.invoke(object,f.getType().cast((textFields.remove(0).getText())));
            }
            else if (f.getType().getName().equals("char")) {
                char c = textFields.remove(0).getText().trim().charAt(0);
                fieldSetter.invoke(object, c);

            } else {
                Method m = parseType(f.getType().getName()).getDeclaredMethod("valueOf", String.class);
                if(textFields.get(0).getText().matches("^\\d+([.]\\d+)?$")) {
                    Object o = m.invoke(null, textFields.remove(0).getText());
                    fieldSetter.invoke(object, o);
                } else {
                    textFields.remove(0);
                    textArea.appendText("The property " + fieldName + " could not be changed.\n");
                }
            }
        }
        for(Field f:fields){
            String fieldName = f.getName();
            String capName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method fieldGetter = object.getClass().getMethod("get"+ capName);

            Object value = fieldGetter.invoke(object);

            textArea.appendText(f.getName() + " = " + value +"\n");

            System.out.println(value + "<-"+f.getName());
        }
    }

    private static Class<?> parseType(final String className) {
        switch (className) {
            case "boolean":
                return Boolean.class;
            case "byte":
                return Byte.class;
            case "short":
                return Short.class;
            case "int":
                return Integer.class;
            case "long":
                return Long.class;
            case "float":
                return Float.class;
            case "double":
                return Double.class;
            case "void":
                return void.class;
            default:
                try {
                    return Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Class not found: " + className);
                }
        }
    }
}