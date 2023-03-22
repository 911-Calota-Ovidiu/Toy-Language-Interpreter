package Repo;

import Model.*;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    List<ProgramState> getProgramList();
    void setProgramStates(List<ProgramState> programStates);
    void addProgram(ProgramState program);
    void logProgramStateExec(ProgramState programState) throws IOException, ADTException;
    void emptyLogFile() throws IOException;
}
