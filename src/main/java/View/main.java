package View;

import Controller.Controller;
import Model.*;
import Model.ProgramState;
import Repo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
    }/*
        Integer option,exit=0;
        Scanner sc=new Scanner(System.in);
        while(exit!=1)
        {
            printMenu();
            option=sc.nextInt();
            if (option==0) exit=1;
            else if (option==1) {
               runProgram1();
            }
            else if (option==2)
            {
                runProgram2();
            } else if (option == 3)
            {
                runProgram4();
            }

        }
        System.out.println("Program stopped successfully");
    }
    public static void runProgram1() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        runStatement(ex1);
    }
    public static void runProgram2() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement example2=new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStmt("a",new ArithmeticExpression('+',new ValueExp(new IntValue(2)),
                                new ArithmeticExpression('*',new ValueExp(new IntValue(3)),
                                        new ValueExp(new IntValue(5))))),
                                new CompoundStatement(new AssignStmt("b",new ArithmeticExpression('+',new VarExp("a"),
                                        new ValueExp(new IntValue(1)))),new PrintStmt(new VarExp("b"))))));
        runStatement(example2);
    }
    public static void runProgram3() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement example3=new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new AssignStmt("a",new ValueExp(new BoolValue(true))),
                                new CompoundStatement(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v",new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        runStatement(example3);
    }
    public static void runProgram4() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\Ovidiu\\Lab3\\src\\Model\\test.in"))),
                        new CompoundStatement(new OpenReadFile(new VarExp("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompoundStatement(new PrintStmt(new VarExp("varc")),
                                                        new CompoundStatement(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompoundStatement(new PrintStmt(new VarExp("varc")),
                                                                        new CloseFile(new VarExp("varf"))))))))));
        runStatement(ex4);
    }

    public static void runStatement(IStatement statement) throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIlist<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable=new MyDictionary<>();
        ProgramState state = new ProgramState(executionStack, symbolTable, output, statement,fileTable);
        String path="C:\\Users\\Ovidiu\\Desktop\\log.log";
        //Scanner sc=new Scanner(System.in);
        //path=sc.nextLine();
        IRepo repository = new Repo(state,path);
        System.out.println(repository.getPath());
        Controller controller = new Controller(repository);
        controller.allSteps();
    }
    public static void printMenu()
    {
        System.out.println("Input option:\n1.Example 1:\nint v;\nv=2;\nPrint(v)\n\n2.Example 2:\nint a;\nint b;\na=2+3*5;\nb=a+1;\nPrint(b)\n\n3.Example 3:\nbool a;\nint v;\na=true;\n(If a Then v=2 Else v=3);\nPrint(v)\n\n0.Exit\n");
    }*/
}
