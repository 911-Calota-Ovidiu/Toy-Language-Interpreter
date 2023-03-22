package Model;

public class IfStatement implements IStatement{
    IExpression exp;
    IStatement thenS;
    IStatement elseS;
    public IfStatement(IExpression e, IStatement t, IStatement el)
    {
        this.exp=e;
        this.thenS=t;
        this.elseS=el;
    }
    public String toString() {
        return String.format("if(%s){%s}else{%s}", this.exp.toString(), this.thenS.toString(), this.elseS.toString());
    }
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException{
        Value result=this.exp.eval(state.getSymTable(),state.getHeap());
        if (result instanceof BoolValue boolresult)
        {
            IStatement stmt;
            if(boolresult.getValue())
            {
                stmt=thenS;
            }
            else
            {
                stmt=elseS;
            }
            MyIStack<IStatement> stack=state.getExeStack();
            stack.push(stmt);
            state.setExeStack(stack);
            return state;
        }
        else throw new StatementExecutionException("Please provide a boolean expression in an if statement.");
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        Type typexp=exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            thenS.typecheck(typeEnv.deepCopy());
            elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new ExpressionEvaluationException("IF condition is not bool");
    }
    public IStatement deepCopy() {
        return new IfStatement(this.exp.deepCopy(), this.thenS.deepCopy(), this.elseS.deepCopy());
    }
}
