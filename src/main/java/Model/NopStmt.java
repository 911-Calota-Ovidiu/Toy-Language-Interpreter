package Model;

public class NopStmt implements IStatement {
    public ProgramState execute(ProgramState state)
    {
        return null;
    }
    public IStatement deepCopy() {
        return new NopStmt();
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        return typeEnv;
    }
    public String toString() {
        return "NopStatement";
    }
}

