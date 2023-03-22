package View;

import Controller.Controller;
import Model.ADTException;
import Model.ExpressionEvaluationException;
import Model.StatementExecutionException;

import java.io.IOException;
import java.util.Scanner;

public class RunExampleCommand extends Command {
    Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        } catch (ExpressionEvaluationException | ADTException | StatementExecutionException | IOException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
