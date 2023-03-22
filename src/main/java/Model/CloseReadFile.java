package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements IStatement{
    IExpression expression;
    public CloseReadFile(IExpression exp)
    {
        this.expression=exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value val=expression.eval(state.getSymTable(),state.getHeap());
        if(!val.getType().equals(new StringType()))
            throw new StatementExecutionException(expression+"not StringType");
        StringValue fileName = (StringValue) val;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getVal()))
            throw new StatementExecutionException(String.format("%s is not present in the FileTable", val));
        BufferedReader br = fileTable.lookUp(fileName.getVal());
        try {
            br.close();
        } catch (IOException e) {
            throw new StatementExecutionException(String.format("Unexpected error in closing %s", val));
        }
        fileTable.remove(fileName.getVal());
        state.setFileTable(fileTable);
        return null;
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        if (expression.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementExecutionException("CloseReadFile requires a string expression.");
    }
    public IStatement deepCopy()
    {
        return new CloseReadFile(expression.deepCopy());
    }
    public String toString()
    {
        return "CloseFile("+expression.toString()+")";
    }
}
