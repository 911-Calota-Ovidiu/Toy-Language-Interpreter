package Model;


public class ReadHeapExpression implements IExpression{
    IExpression expression;

    public ReadHeapExpression(IExpression expression)
    {
        this.expression=expression;
    }
    public Value eval(MyIDictionary<String,Value> symTable,MyIHeap heap) throws ExpressionEvaluationException,ADTException{
        Value value=expression.eval(symTable,heap);
        if(!(value instanceof RefValue))
            throw new ExpressionEvaluationException(String.format("%s is not RefValue",value));
        RefValue refValue=(RefValue) value;
        return heap.get(refValue.getAddress());
    }
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException
    {
        Type t=expression.typecheck(typeEnv);
        if(t instanceof RefType)
        {
            RefType reft= (RefType) t;
            return reft.getInner();
        }
        else throw new ExpressionEvaluationException("the RH argument is not a RefType");
    }
    public IExpression deepCopy()
    {
        return new ReadHeapExpression(expression.deepCopy());
    }
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}
