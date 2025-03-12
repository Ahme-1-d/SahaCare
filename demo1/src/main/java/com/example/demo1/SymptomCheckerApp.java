package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class SymptomCheckerApp extends Application {

    private static final String BUTTON_STYLE = "-fx-background-color: #ecf0f1; -fx-border-radius: 8; -fx-background-radius: 8; " +
            "-fx-font-size: 14; -fx-padding: 8 20; -fx-border-color: #bdc3c7;";
    private static final String PRIMARY_COLOR = "#2c3e50";
    private static final String SECONDARY_COLOR = "#34495e";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Symptom Checker");
        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.show();
    }

    private Scene createMainScene(Stage primaryStage) {
        VBox mainLayout = new VBox(25);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: #f5f7fa;");

        Text title = createTitle("Symptom Checker");
        Label instruction = createInstruction("Identify possible conditions and treatments based on your symptoms.");
        HBox ageBox = createAgeInput();
        HBox sexBox = createSexSelection();
        Button continueButton = createContinueButton(primaryStage);

        mainLayout.getChildren().addAll(title, instruction, ageBox, sexBox, continueButton);
        return new Scene(mainLayout, 500, 450);
    }

    private Text createTitle(String text) {
        Text title = new Text(text);
        title.setFont(Font.font("Arial", 28));
        title.setFill(Color.web(PRIMARY_COLOR));
        return title;
    }

    private Label createInstruction(String text) {
        Label instruction = new Label(text);
        instruction.setFont(Font.font("Arial", 14));
        instruction.setTextFill(Color.web(SECONDARY_COLOR));
        return instruction;
    }

    private HBox createAgeInput() {
        HBox ageBox = new HBox(10);
        ageBox.setAlignment(Pos.CENTER);
        Label ageLabel = new Label("Age:");
        ageLabel.setFont(Font.font(14));
        TextField ageField = new TextField();
        ageField.setPromptText("Enter age");
        ageField.setMaxWidth(80);
        ageField.setStyle("-fx-background-radius: 8; -fx-padding: 5;");
        ageBox.getChildren().addAll(ageLabel, ageField);
        return ageBox;
    }

    private HBox createSexSelection() {
        HBox sexBox = new HBox(15);
        sexBox.setAlignment(Pos.CENTER);
        Label sexLabel = new Label("Sex:");
        sexLabel.setFont(Font.font(14));

        ToggleGroup sexGroup = new ToggleGroup();
        ToggleButton maleButton = createToggleButton("Male", "#3498db", sexGroup);
        ToggleButton femaleButton = createToggleButton("Female", "#e74c3c", sexGroup);

        sexBox.getChildren().addAll(sexLabel, maleButton, femaleButton);
        return sexBox;
    }

    private ToggleButton createToggleButton(String text, String color, ToggleGroup group) {
        ToggleButton button = new ToggleButton(text);
        button.setToggleGroup(group);
        button.setStyle(BUTTON_STYLE);
        button.setOnAction(e -> {
            button.setStyle(BUTTON_STYLE + "-fx-background-color: " + color + "; -fx-text-fill: white;");
            group.getToggles().forEach(toggle -> {
                if (toggle != button) {
                    ((ToggleButton) toggle).setStyle(BUTTON_STYLE);
                }
            });
        });
        return button;
    }

    private Button createContinueButton(Stage primaryStage) {
        Button continueButton = new Button("Continue");
        continueButton.setFont(Font.font("Arial", 16));
        continueButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 10 20;");
        continueButton.setOnAction(e -> showBodyDiagram(primaryStage));
        return continueButton;
    }

    private void showBodyDiagram(Stage primaryStage) {
        BorderPane diagramLayout = new BorderPane();
        diagramLayout.setPadding(new Insets(20));

        Text header = createTitle("Click on a body part to diagnose");
        BorderPane.setAlignment(header, Pos.CENTER);
        diagramLayout.setTop(header);

        VBox bodyLayout = new VBox(10);
        bodyLayout.setAlignment(Pos.CENTER);

        // 1) Face layout (forehead, eyes, nose, mouth, ears)
        VBox faceLayout = createFaceLayout();

        // 2) Neck
        Rectangle neck = createBodyPart("Neck", 40, 20);

        // 3) Shoulders (left and right)
        HBox shoulders = new HBox(20);
        shoulders.setAlignment(Pos.CENTER);
        Rectangle leftShoulder = createBodyPart("Left Shoulder", 30, 20);
        Rectangle rightShoulder = createBodyPart("Right Shoulder", 30, 20);
        shoulders.getChildren().addAll(leftShoulder, rightShoulder);

        // 4) Chest
        Rectangle chest = createBodyPart("Chest", 80, 100);

        // 5) Arms row
        HBox arms = new HBox(20);
        arms.setAlignment(Pos.CENTER);
        Rectangle leftArm = createBodyPart("Left Arm", 20, 80);
        Rectangle rightArm = createBodyPart("Right Arm", 20, 80);
        arms.getChildren().addAll(leftArm, chest, rightArm);

        // 6) Abdominal regions (epigastric, upper abs, lower abs, pelvis)
        VBox absLayout = new VBox(5);
        absLayout.setAlignment(Pos.CENTER);
        Rectangle epigastric = createBodyPart("Epigastric Region", 70, 20);
        Rectangle upperAbs = createBodyPart("Upper Abs", 70, 20);
        Rectangle lowerAbs = createBodyPart("Lower Abs", 70, 20);
        Rectangle pelvis = createBodyPart("Pelvis", 70, 20);
        absLayout.getChildren().addAll(epigastric, upperAbs, lowerAbs, pelvis);

        // 7) Legs
        HBox legs = new HBox(20);
        legs.setAlignment(Pos.CENTER);
        Rectangle leftLeg = createBodyPart("Left Leg", 30, 100);
        Rectangle rightLeg = createBodyPart("Right Leg", 30, 100);
        legs.getChildren().addAll(leftLeg, rightLeg);

        // Add everything in order
        bodyLayout.getChildren().addAll(faceLayout, neck, shoulders, arms, absLayout, legs);
        diagramLayout.setCenter(bodyLayout);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 8 20;");
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene(primaryStage)));
        diagramLayout.setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER);

        Scene diagramScene = new Scene(diagramLayout, 500, 750);
        primaryStage.setScene(diagramScene);
    }

    /**
     * Creates a VBox containing separate shapes for the forehead, eyes, nose, mouth, and ears.
     */
    private VBox createFaceLayout() {
        VBox faceLayout = new VBox(5);
        faceLayout.setAlignment(Pos.CENTER);

        // Forehead
        Rectangle forehead = createBodyPart("Forehead", 60, 10);

        // Eyes & Ears in one row
        HBox eyesAndEars = new HBox(5);
        eyesAndEars.setAlignment(Pos.CENTER);
        Rectangle leftEar = createBodyPart("Left Ear", 10, 20);
        Rectangle leftEye = createBodyPart("Left Eye", 20, 10);
        Rectangle rightEye = createBodyPart("Right Eye", 20, 10);
        Rectangle rightEar = createBodyPart("Right Ear", 10, 20);
        eyesAndEars.getChildren().addAll(leftEar, leftEye, rightEye, rightEar);

        // Nose
        Rectangle nose = createBodyPart("Nose", 10, 20);

        // Mouth
        Rectangle mouth = createBodyPart("Mouth", 30, 10);

        // Add them in vertical order
        faceLayout.getChildren().addAll(forehead, eyesAndEars, nose, mouth);

        return faceLayout;
    }

    private Rectangle createBodyPart(String partName, double width, double height) {
        Rectangle part = new Rectangle(width, height, Color.LIGHTGRAY);
        part.setArcHeight(10);
        part.setArcWidth(10);
        part.setOnMouseClicked(e -> showSymptoms(partName));
        Tooltip.install(part, new Tooltip("Click to diagnose " + partName));
        return part;
    }

    private void showSymptoms(String partName) {
        List<String> symptoms = getSymptomsForBodyPart(partName);
        StringBuilder message = new StringBuilder("Symptoms for " + partName + ":\n");
        for (String symptom : symptoms) {
            message.append("- ").append(symptom).append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Symptoms");
        alert.setHeaderText("Symptoms for " + partName);
        alert.setContentText(message.toString());
        alert.showAndWait();
    }

    private List<String> getSymptomsForBodyPart(String partName) {
        switch (partName) {
            case "Forehead":
                return Arrays.asList("Forehead pain", "Sweating", "Headache near the temples");

            case "Left Eye":
            case "Right Eye":
                return Arrays.asList("Eye redness", "Blurred vision", "Dryness");

            case "Nose":
                return Arrays.asList("Runny nose", "Congestion", "Sneezing");

            case "Mouth":
                return Arrays.asList("Toothache", "Sore throat", "Mouth ulcers");

            case "Left Ear":
            case "Right Ear":
                return Arrays.asList("Ear pain", "Ringing in ear", "Hearing loss");

            case "Neck":
                return Arrays.asList("Neck pain", "Stiffness", "Swelling");

            case "Left Shoulder":
            case "Right Shoulder":
                return Arrays.asList("Shoulder pain", "Limited range of motion", "Muscle strain");

            case "Chest":
                return Arrays.asList("Chest pain", "Shortness of breath", "Cough");

            case "Left Arm":
            case "Right Arm":
                return Arrays.asList("Upper arm pain", "Biceps shaking", "Triceps hyporeflexia");

            case "Epigastric Region":
                return Arrays.asList("Epigastric pain", "Acid reflux", "Burning sensation");
            case "Upper Abs":
                return Arrays.asList("Upper abdominal pain", "Indigestion", "Gas");
            case "Lower Abs":
                return Arrays.asList("Lower abdominal pain", "Cramping", "Bloating");
            case "Pelvis":
                return Arrays.asList("Pelvic pain", "Urinary issues", "Discomfort during movement");

            case "Left Leg":
            case "Right Leg":
                return Arrays.asList("Leg pain", "Swelling", "Numbness");

            default:
                return Arrays.asList("No symptoms found for this area");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}