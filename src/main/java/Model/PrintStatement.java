package Model;



public class PrintStatement implements IStatement {
    IExpression exp;
    public PrintStatement(IExpression exp){
        this.exp=exp;
    }
    public String toString(){ return "print(" +this.exp.toString()+")";}

    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, ADTException {
        MyIlist<Value> out=state.getOut();
        out.add(this.exp.eval(state.getSymTable(),state.getHeap()));
        state.setOut(out);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
    public IStatement deepCopy() {
        return new PrintStatement(this.exp.deepCopy());
    }
}
