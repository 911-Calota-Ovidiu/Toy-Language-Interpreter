package Model;

import java.util.Objects;

public class RelationalExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    String operation;
    public RelationalExpression(String operation, IExpression expression1, IExpression expression2){
        this.operation=operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable, heap);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getVal();
                v2 = val2.getVal();
                if (Objects.equals(this.operation, "<"))
                    return new BoolValue(v1 < v2);
                else if (Objects.equals(this.operation, "<="))
                    return new BoolValue(v1 <= v2);
                else if (Objects.equals(this.operation, "=="))
                    return new BoolValue(v1 == v2);
                else if (Objects.equals(this.operation, "!="))
                    return new BoolValue(v1 != v2);
                else if (Objects.equals(this.operation, ">"))
                    return new BoolValue(v1 > v2);
                else if (Objects.equals(this.operation, ">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand in not an integer.");
        return null;
    }
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException{
        Type t1,t2;
        t1=expression1.typecheck(typeEnv);
        t2=expression2.typecheck(typeEnv);
        if(t1.equals(new IntType()))
        {
            if (t2.equals(new IntType()))
            {
                return new IntType();
            }
            else throw new ExpressionEvaluationException("second operand not an integer");
        }
        else throw new ExpressionEvaluationException("second operand not an integer");
    }
    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(this.operation,this.expression1.deepCopy(),this.expression2.deepCopy());
    }
}
