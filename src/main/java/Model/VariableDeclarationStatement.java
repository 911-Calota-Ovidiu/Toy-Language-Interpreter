package Model;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type;
    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ProgramState execute(ProgramState state) throws StatementExecutionException{
        MyIDictionary<String,Value> symTable=state.getSymTable();
        if(symTable.isDefined(this.name))
        {
            throw new StatementExecutionException("Variable " + name + " already exists in the symTable.");
        }
        symTable.put(this.name, type.defaultValue());
        state.setSymTable(symTable);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        typeEnv.put(name,type);
        return typeEnv;
    }
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }
}
