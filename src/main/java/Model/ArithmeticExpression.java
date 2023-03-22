package Model;

public class ArithmeticExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    char operation;

    public ArithmeticExpression(char operation, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable, heap);
            if (value2.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1, n2;
                n1 = int1.getVal();
                n2 = int2.getVal();
                if (this.operation == '+')
                    return new IntValue(n1 + n2);
                else if (this.operation == '-')
                    return new IntValue(n1 - n2);
                else if (this.operation == '*')
                    return new IntValue(n1 * n2);
                else if (this.operation == '/')
                    if (n2 == 0)
                        throw new ExpressionEvaluationException("Division by zero.");
                    else
                        return new IntValue(n1 / n2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand is not an integer.");
        return null;
    }
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException,ADTException,StatementExecutionException
    {
        Type t1,t2;
        t1=expression1.typecheck(typeEnv);
        t2=expression2.typecheck(typeEnv);
        if (t1.equals(new IntType()))
        {
            if(t2.equals(new IntType()))
            {
                return new IntType();
            }
            else throw new ExpressionEvaluationException("Second operand not an Integer");
        }
        else throw new ExpressionEvaluationException("First operand not an Integer");
    }

    public IExpression deepCopy()
    {
        return new ArithmeticExpression(this.operation,this.expression1.deepCopy(),this.expression2.deepCopy());
    }
    public String toString(){
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
