package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStatement{
    IExpression expression;
    public OpenReadFile(IExpression expression)
    {
        this.expression=expression;
    }
    public ProgramState execute(ProgramState state)throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value val=expression.eval(state.getSymTable(),state.getHeap());
        if(val.getType().equals(new StringType()))
        {
            StringValue fileName=(StringValue) val;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if(!fileTable.isDefined(fileName.getVal()))
            {
                BufferedReader br;
                try{
                    br=new BufferedReader(new FileReader(fileName.getVal()));
                } catch (FileNotFoundException e) {
                    throw new StatementExecutionException(fileName.getVal() +" could not pe opened!");
                }
                fileTable.put(fileName.getVal(),br);
                state.setFileTable(fileTable);
            }
            else{
                throw new StatementExecutionException(fileName.getVal() +" is open already!");
            }
        }
        else
        {
            throw new StatementExecutionException(expression.toString()+" is not a StringType");
        }
        return state;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException
    {
        if (expression.typecheck(typeEnv).equals(new StringType()))
        {
            return typeEnv;
        }
        else
            throw new StatementExecutionException("OpenReadFile: not string");
    }
    public IStatement deepCopy() {
        return new OpenReadFile(expression.deepCopy());
    }
    public String toString()
    {
        return "OpenReadFile("+expression.toString()+")";
    }
}
