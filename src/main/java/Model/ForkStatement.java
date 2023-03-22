package Model;

import java.util.Map;

public class ForkStatement implements IStatement{
    IStatement statement;
    public ForkStatement(IStatement statement)
    {
        this.statement=statement;
    }

    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }

        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
    public IStatement deepCopy()
    {
        return new ForkStatement(statement.deepCopy());
    }
    public String toString()
    {
        return String.format("Fork(%s)",statement.toString());
    }
}

