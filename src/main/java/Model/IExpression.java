package Model;

public interface IExpression {
    Value eval(MyIDictionary<String, Value> symTable,MyIHeap heap) throws ExpressionEvaluationException, ADTException;
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException;
    IExpression deepCopy();}
