package com.example.gui;
import Controller.*;
import Model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class Pair<T1,T2>
{
    T1 first;
    T2 second;
    public Pair(T1 t1, T2 t2)
    {
        this.first=t1;
        this.second=t2;
    }
}
public class ProgramExecutorController {
    private Controller controller;
    @FXML
    private TextField numberOfProgramStatesTextField;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;

    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, Value>, String> valueColumn;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<Integer> programStateIdentifiersListView;

    @FXML
    private TableView<Pair<String, Value>> symbolTableView;

    @FXML
    private TableColumn<Pair<String, Value>, String> variableNameColumn;

    @FXML
    private TableColumn<Pair<String, Value>, String> variableValueColumn;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private Button runOneStepButton;

    public void setController(Controller controller) {
        this.controller=controller;
        populate();
    }
    @FXML
    public void initialize()
    {
        programStateIdentifiersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addressColumn.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().second.toString()));
        variableNameColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().first));
        variableValueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().second.toString()));

    }
    private ProgramState getCurrentProgramState()
    {
        if(controller.getProgramStates().size()==0)
        {
            return null;
        }
        else{
            int cId=programStateIdentifiersListView.getSelectionModel().getSelectedIndex();
            if(cId==-1)
            {
                return controller.getProgramStates().get(0);
            }
            else return controller.getProgramStates().get(cId);
        }
    }
    private void populate()
    {
        populateHeapTableView();
        populateOutputListView();
        populateFileTableListView();
        populateProgramStateIdentifiersListView();
        populateSymbolTableView();
        populateExecutionStackListView();

    }
    @FXML
    private void changeProgramState()
    {
        populateExecutionStackListView();
        populateSymbolTableView();
    }
    private void populateNumberOfProgramStatesTextField() {
        List<ProgramState> programStates = controller.getProgramStates();
        numberOfProgramStatesTextField.setText(String.valueOf(programStates.size()));
    }
    private void populateHeapTableView()
    {
        ProgramState state=getCurrentProgramState();
        MyIHeap heap= Objects.requireNonNull(state).getHeap();
        ArrayList<Pair<Integer,Value>> heapEntries=new ArrayList<>();
        for(Map.Entry<Integer, Value> entry: heap.getContent().entrySet()) {
            heapEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        heapTableView.setItems(FXCollections.observableArrayList(heapEntries));
    }
    private void populateOutputListView()
    {
        ProgramState programState = getCurrentProgramState();
        List<String> output = new ArrayList<>();
        List<Value> outputList = Objects.requireNonNull(programState).getOut().getList();
        int index;
        for (index = 0; index < outputList.size(); index++){
            output.add(outputList.get(index).toString());
        }
        outputListView.setItems(FXCollections.observableArrayList(output));
    }
    private void populateFileTableListView()
    {
        ProgramState programState = getCurrentProgramState();
        List<String> files = new ArrayList<>(Objects.requireNonNull(programState).getFileTable().getContent().keySet());
        fileTableListView.setItems(FXCollections.observableList(files));
    }
    private void populateProgramStateIdentifiersListView()
    {
        List<ProgramState> programStates = controller.getProgramStates();
        List<Integer> idList = programStates.stream().map(ProgramState::getId).collect(Collectors.toList());
        programStateIdentifiersListView.setItems(FXCollections.observableList(idList));
        populateNumberOfProgramStatesTextField();
    }
    private void populateSymbolTableView()
    {
        ProgramState programState = getCurrentProgramState();
        MyIDictionary<String, Value> symbolTable = Objects.requireNonNull(programState).getSymTable();
        ArrayList<Pair<String, Value>> symbolTableEntries = new ArrayList<>();
        for (Map.Entry<String, Value> entry: symbolTable.getContent().entrySet()) {
            symbolTableEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symbolTableView.setItems(FXCollections.observableArrayList(symbolTableEntries));
    }
    private void populateExecutionStackListView()
    {
        ProgramState programState = getCurrentProgramState();
        List<String> executionStackToString = new ArrayList<>();
        if (programState != null)
            for (IStatement statement: programState.getExeStack().getReversed()) {
                executionStackToString.add(statement.toString());
            }
        executionStackListView.setItems(FXCollections.observableList(executionStackToString));
    }
    @FXML
    private void runOneStep(MouseEvent mouseEvent)
    {
        if (controller!=null)
        {
            try{
                List<ProgramState> states=Objects.requireNonNull(controller.getProgramStates());
                if(states.size()>0)
                {
                    controller.oneStep();
                    populate();
                    states=controller.removeCompletedPrg(controller.getProgramStates());
                    controller.setProgramStates(states);
                    populateProgramStateIdentifiersListView();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("An error has occurred!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }
            }catch(InterpreterException | InterruptedException | ExpressionEvaluationException | ADTException |
                   IOException | StatementExecutionException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Execution error!");
                alert.setHeaderText("An execution error has occurred!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error has occurred!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }
    }
}
