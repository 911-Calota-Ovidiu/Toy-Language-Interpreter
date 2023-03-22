package Repo;

import Model.ADTException;
import Model.ExpressionEvaluationException;
import Model.ProgramState;
import Model.StatementExecutionException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo{
    private List<ProgramState> programStates;
    private final String logFilePath;

    public Repo(ProgramState programState, String logFilePath) throws IOException {
        this.logFilePath = logFilePath;
        this.programStates = new ArrayList<>();
        this.addProgram(programState);
        this.emptyLogFile();
    }
    public void setProgramStates(List<ProgramState> list)
    {
        this.programStates=list;
    }
    public List<ProgramState> getProgramList()
    {
        return this.programStates;
    }
    public void addProgram(ProgramState program){
        this.programStates.add(program);
    }
    public void logProgramStateExec(ProgramState state) throws ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(state.programStateToString());
        logFile.close();
    }
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }
}
