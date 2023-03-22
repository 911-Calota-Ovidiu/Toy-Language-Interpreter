package Model;

public class WriteHeapStatement implements IStatement{
    String varName;
    IExpression expression;
    public WriteHeapStatement(String varName,IExpression expression)
    {
        this.varName=varName;
        this.expression=expression;
    }
    public ProgramState execute(ProgramState state)throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isDefined(varName))
            throw new StatementExecutionException(String.format("%s not present in the symTable", varName));
        Value value = symTable.lookUp(varName);
        if (!(value instanceof RefValue))
            throw new StatementExecutionException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.eval(symTable, heap);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new StatementExecutionException(String.format("%s not of %s", evaluated, refValue.getLocationType()));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        if(typeEnv.lookUp(varName).equals(new RefType(expression.typecheck(typeEnv))))
        {
            return typeEnv;
        }
        else throw new StatementExecutionException("WriteHeap:left and right have different types");
    }
    public IStatement deepCopy()
    {
        return new WriteHeapStatement(varName,expression.deepCopy());
    }
    public String toString()
    {
        return String.format("WriteHeap(%s,%s)",varName,expression);
    }
}
