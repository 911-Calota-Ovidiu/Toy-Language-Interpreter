package Model;

public class WhileStatement implements IStatement{
    IExpression expression;
    IStatement statement;
    public WhileStatement(IExpression expression, IStatement statement)
    {
        this.expression=expression;
        this.statement=statement;
    }
    public ProgramState execute(ProgramState state) throws ADTException, ExpressionEvaluationException, StatementExecutionException{
        Value value=expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStatement> stack=state.getExeStack();
        if(!value.getType().equals(new BoolType()))
            throw new StatementExecutionException(String.format("%s is not BoolType",value));
        BoolValue boolValue=(BoolValue) value;
        if( boolValue.getValue())
        {
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        Type typexp=expression.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new StatementExecutionException("While condition is not bool");
    }
    public IStatement deepCopy()
    {
        return new WhileStatement(expression.deepCopy(),statement.deepCopy());
    }
    public String toString()
    {
        return String.format("while(%s){%s}",expression,statement);
    }
}
