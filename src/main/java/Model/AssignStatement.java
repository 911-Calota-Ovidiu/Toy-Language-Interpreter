package Model;

public class AssignStatement implements IStatement {
    String key;
    IExpression expression;
    public AssignStatement(String key, IExpression exp)
    {
        this.key=key;
        this.expression=exp;
    }
    public String toString(){ return String.format("%s=%s",this.key,this.expression);}
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        if(symTbl.isDefined(this.key))
        {
            Value value=expression.eval(symTbl, state.getHeap());
            Type typeId=(symTbl.lookUp(this.key).getType());
            if(value.getType().equals(typeId))
            {
                symTbl.update(key,value);
            }
            else throw new StatementExecutionException("Declared type of variable " + key + " and type of the assigned expression do not match.");
        }
        else throw new StatementExecutionException("The used variable " + key + " was not declared before.");
        state.setSymTable(symTbl);
        return null;
        }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        Type typeVar=typeEnv.lookUp(key);
        Type typexp= expression.typecheck(typeEnv);
        if(typeVar.equals(typexp))
            return typeEnv;
        else throw new ExpressionEvaluationException("Assignment: right and left have different types");
    }
    public IStatement deepCopy() {
        return new AssignStatement(key, expression.deepCopy());
    }
}
