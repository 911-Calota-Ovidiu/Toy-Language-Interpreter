package Model;

public class VariableExpression implements IExpression {
    String key;
    public VariableExpression(String key)
    {
        this.key=key;
    }

    public Value eval(MyIDictionary<String,Value> tbl,MyIHeap hep) throws ADTException
    {return tbl.lookUp(this.key);}
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException{
        return typeEnv.lookUp(key);
    }

    public IExpression deepCopy() {
        return new VariableExpression(key);
    }

    public String toString() {
        return key;
    }
}
