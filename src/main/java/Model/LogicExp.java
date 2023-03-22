package Model;

import java.util.Objects;

public class LogicExp implements IExpression {
    IExpression e1;
    IExpression e2;
    String op;
    public LogicExp(IExpression e1,IExpression e2,String op)
    {
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.e1.eval(symTable, heap);
        if (value1.getType().equals(new BoolType())) {
            value2 = this.e2.eval(symTable, heap);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.op, "and")) {
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.op, "or")) {
                    return new BoolValue(b1 || b2);
                }
            } else {
                throw new ExpressionEvaluationException("Second operand is not a boolean.");
            }
        } else {
            throw new ExpressionEvaluationException("First operand is not a boolean.");
        }
        return null;
    }
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException
    {
        Type t1,t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if(t1.equals(new BoolType()))
        {
            if(t2.equals(new BoolType()))
                return new BoolType();
            else throw new ExpressionEvaluationException("second operand not bool");
        }
        else throw new ExpressionEvaluationException("first operand not bool");
    }
    public IExpression deepCopy()
    {
        return new LogicExp(this.e1.deepCopy(),this.e2.deepCopy(),this.op);
    }
    public String toString()
    {
        return this.e1.toString() + " " + this.op + " " + this.e2.toString();
    }
}
