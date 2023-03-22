package Model;

public class CompoundStatement implements IStatement {
    IStatement first, snd;

    public CompoundStatement(IStatement first, IStatement snd) {
        this.first = first;
        this.snd = snd;
    }
    public ProgramState execute(ProgramState state) {
        MyIStack<IStatement> stk = state.getExeStack();
        stk.push(this.snd);
        stk.push(this.first);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException{
        return snd.typecheck(first.typecheck(typeEnv));
    }
    public IStatement deepCopy()
    {
        return new CompoundStatement(this.first.deepCopy(),this.snd.deepCopy());
    }
    public String toString()
    {
        return String.format("(%s|%s)",this.first.toString(),this.snd.toString());
    }
}
