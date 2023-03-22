package Model;


import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIlist<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIHeap heap;
    int id;
    static int lastId=0;
    IStatement originalProgram;

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String,Value> symTable, MyIlist<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, IStatement program) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = program.deepCopy();
        this.exeStack.push(this.originalProgram);
        this.id = setId();
    }

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String,Value> symTable, MyIlist<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }
    void setExeStack(MyIStack<IStatement> newStack)
    {
        this.exeStack=newStack;
    }
    public void setSymTable(MyIDictionary<String, Value> newSymTable) {
        this.symTable = newSymTable;
    }
    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable)
    {
        this.fileTable=fileTable;
    }
    public void setOut(MyIlist<Value> newOut) {
        this.out = newOut;
    }
    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }
    public synchronized int setId() {
        lastId++;
        return lastId;
    }
    public MyIHeap getHeap() {
        return heap;
    }

    public MyIDictionary<String, BufferedReader> getFileTable()
    {
        return fileTable;
    }
    public MyIlist<Value> getOut() {
        return out;
    }
    public int getId()
    {
        return id;
    }
    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getReversed();
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }
    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }
    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }
    public Boolean isNotCompleted()
    {
        return exeStack.isEmpty();
    }
    public String heapToString() throws ADTException
    {
        StringBuilder heapStringBuilder=new StringBuilder();
        for(int key: heap.keySet())
        {
            heapStringBuilder.append(String.format("%d -> %s, ",key,heap.get(key)));
        }
        return heapStringBuilder.toString();
    }
    public String toString() {
        try {
            return "Execution stack: \n" + this.exeStack.getReversed() + "\nSymbol table: \n" + this.symTable.toString() + "\nOutput list: \n" + this.out.toString()+"\nFile Table:\n"+this.fileTable.toString()+"\nHeap memory:\n" + heapToString()+"\nProgramID:\n"+id;
        } catch (ADTException e) {
            throw new RuntimeException(e);
        }
    }
    public ProgramState oneStep() throws StatementExecutionException, ADTException, ExpressionEvaluationException{
        if(exeStack.isEmpty())
        {
            throw new StatementExecutionException("Stack empty"+id);
        }
        IStatement currentStatement= exeStack.pop();
        return currentStatement.execute(this);
    }
    public String programStateToString() throws ADTException {
        return "Execution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString()+"\nFile Table:\n"+fileTableToString()+ "\nHeap memory:\n" + heapToString()+"\nProgramID:\n"+id;
    }
}

