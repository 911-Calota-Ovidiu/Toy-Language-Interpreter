package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    IExpression expression;
    String var;
    public ReadFile(IExpression expression,String var)
    {
        this.expression=expression;
        this.var=var;
    }
    public ProgramState execute(ProgramState state)throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if(symTable.isDefined(var))
        {
            Value value=symTable.lookUp(var);
            if(value.getType().equals(new IntType()))
            {
                value=expression.eval(symTable,state.getHeap());
                if(value.getType().equals(new StringType()))
                {
                    StringValue cast=(StringValue) value;
                    if(fileTable.isDefined(cast.getVal()))
                    {
                        BufferedReader br=fileTable.lookUp(cast.getVal());
                        try{
                            String line=br.readLine();
                            if(line==null)
                                line="0";
                            symTable.put(var,new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementExecutionException("Could not read file "+cast);
                        }
                    }else{throw new StatementExecutionException("FileTable does not contain "+cast);}
                }else{throw new StatementExecutionException(value+" value does not evaluate to StringType");}
            }else{throw new StatementExecutionException(value+" is not IntType");}
        }else{throw new StatementExecutionException(var+" is not present in symTable");}
        return state;
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        if (expression.typecheck(typeEnv).equals(new StringType()))
            if (typeEnv.lookUp(var).equals(new IntType()))
                return typeEnv;
            else
                throw new StatementExecutionException("ReadFile requires an int as its variable parameter.");
        else
            throw new StatementExecutionException("ReadFile requires a string as es expression parameter.");
    }
    public IStatement deepCopy()
    {
        return new ReadFile(expression.deepCopy(),var);
    }
    public String toString()
    {
        return "ReadFile( "+expression.toString()+","+var+")";
    }
}
