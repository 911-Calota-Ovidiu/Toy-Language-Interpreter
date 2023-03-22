package Model;

public class ValueExpression implements IExpression {
    Value e;
    public ValueExpression(Value e)
    {
        this.e=e;
    }
    public Value eval(MyIDictionary<String,Value> tbl,MyIHeap heap){return this.e;}
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException
    {
        return e.getType();
    }


    public IExpression deepCopy() {
        return new ValueExpression(this.e);
    }

    public String toString() {
        return this.e.toString();
    }
}
