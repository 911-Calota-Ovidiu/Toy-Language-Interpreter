package Model;

public class NewStatement implements IStatement{

    String varName;
    IExpression expression;
    public NewStatement(String varName,IExpression expression)
    {
        this.varName=varName;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String,Value> symTable=state.getSymTable();
        MyIHeap heap=state.getHeap();
        if(!symTable.isDefined(varName))
            throw new StatementExecutionException(String.format("%s not in symTable", varName));
        Value varValue=symTable.lookUp(varName);
        if(!(varValue.getType() instanceof RefType))
            throw new StatementExecutionException(String.format("%s in not of RefType", varName));
        Value evaluated=expression.eval(symTable,heap);
        Type locationType=((RefValue)varValue).getLocationType();
        if(!locationType.equals(evaluated.getType()))
            throw new StatementExecutionException(String.format("%s not of %s", varName, evaluated.getType()));
        int newPosition= heap.add(evaluated);
        symTable.put(varName,new RefValue(newPosition,locationType));
        state.setSymTable(symTable);
        state.setHeap(heap);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        Type typevar=typeEnv.lookUp(varName);
        Type typexp=expression.typecheck(typeEnv);
        if(typevar.equals(new RefType(typexp)))
        {
            return typeEnv;
        }
        else throw new StatementExecutionException("New: right and left have different types");
    }
    public IStatement deepCopy()
    {
        return new NewStatement(varName,expression.deepCopy());
    }
    public String toString() {
        return String.format("new(%s,%s)",varName,expression);
    }
}
