package org.tim.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.tim.ui.dto.NoteBookDto;
import org.tim.ui.dto.NoteDto;

import java.util.*;

public class MainController {
    private static final Map<NoteBookDto, List<NoteDto>> notebooksMap = new HashMap<NoteBookDto, List<NoteDto>>() {{
        put(new NoteBookDto(1l, "notebook_1"), new ArrayList<NoteDto>() {{
                add(new NoteDto(1l, "note_header_11", "note_body_11"));
                add(new NoteDto(2l, "note_header_12", "note_body_12"));
                add(new NoteDto(3l, "note_header_13", "note_body_13"));
        }});
        put(new NoteBookDto(2l, "notebook_2"), new ArrayList<NoteDto>() {{
                add(new NoteDto(4l, "note_header_21", "note_body_21"));
                add(new NoteDto(5l, "note_header_22", "note_body_22"));
        }});
        put(new NoteBookDto(3l, "notebook_3"), new ArrayList<NoteDto>() {{
                add(new NoteDto(6l, "note_header_31", "note_body_31"));
                add(new NoteDto(7l, "note_header_32", "note_body_32"));
                add(new NoteDto(8l, "note_header_33", "note_body_33"));
                add(new NoteDto(9l, "note_header_34", "note_body_34"));
                add(new NoteDto(10l, "note_header_35", "note_body_35"));
        }});
    }};

    @FXML
    private ComboBox<NoteBookDto> notebooksCombo;
    @FXML
    private Button createNotebookButton;
    @FXML
    private ListView<NoteDto> notesList;
    @FXML
    private TextField noteHeaderText;
    @FXML
    private TextArea noteBodyText;
    @FXML
    private Button createNoteButton;
    @FXML
    private Button saveNoteButton;
    @FXML
    private Button deleteNoteButton;
    @FXML
    private Button cancelNoteButton;

    @FXML
    public void initialize() {
        notebooksCombo.getItems().addAll(notebooksMap.keySet());
    }

    public void onSelectNotebook(ActionEvent actionEvent) {
        hideNote();
        loadNotebook();
    }

    public void onSelectNote(MouseEvent mouseEvent) {
        NoteDto selectedItem = notesList.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            return;
        }
        noteHeaderText.setText(selectedItem.getHeader());
        noteBodyText.setText(selectedItem.getBody());
        noteHeaderText.setVisible(true);
        noteBodyText.setVisible(true);

        hideNode(createNoteButton);
        showNode(deleteNoteButton);
    }

    public void onCreateNote(ActionEvent actionEvent) {
        noteHeaderText.setVisible(true);
        noteBodyText.setVisible(true);
    }

    public void onDeleteNote(ActionEvent actionEvent) {
        NoteBookDto selectedNotebook = notebooksCombo.getSelectionModel().getSelectedItem();
        NoteDto selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNotebook ==null || selectedNote == null) {
            return;
        }

        notebooksMap.get(selectedNotebook).remove(selectedNote);

        hideNote();
        loadNotebook();
    }

    public void onCancelNote(ActionEvent actionEvent) {
        hideNote();

        showNode(createNoteButton);
    }

    public void onSaveNote(ActionEvent actionEvent) {
        NoteBookDto selectedNotebook = notebooksCombo.getSelectionModel().getSelectedItem();
        NoteDto selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNotebook != null) {
            if (selectedNote != null) { // update
                selectedNote.setHeader(noteHeaderText.getText());
                selectedNote.setBody(noteBodyText.getText());
            } else { // create
                NoteDto newNote = new NoteDto(null, noteHeaderText.getText(), noteBodyText.getText());
                notebooksMap.get(selectedNotebook).add(newNote);
            }
        }

        hideNote();
        loadNotebook();
    }

    private void loadNotebook() {
        NoteBookDto selectedNotebook = notebooksCombo.getSelectionModel().getSelectedItem();
        notesList.getItems().clear();
        notesList.getItems().addAll(notebooksMap.get(selectedNotebook));

        showNode(createNoteButton);
    }

    private void hideNote() {
        noteHeaderText.setVisible(false);
        noteBodyText.setVisible(false);
        noteHeaderText.clear();
        noteBodyText.clear();

        hideNode(deleteNoteButton);
    }

    private void hideNode(Node node) {
        node.setVisible(false);
        node.setManaged(false);
    }

    private void showNode(Node node) {
        node.setVisible(true);
        node.setManaged(true);
    }
}
