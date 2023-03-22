package Model;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
    MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
    IStatement deepCopy();

}
