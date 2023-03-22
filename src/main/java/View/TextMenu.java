package View;

import Model.ADTException;
import Model.MyDictionary;
import Model.MyIDictionary;

import java.util.Scanner;

public class TextMenu {
    MyIDictionary<String,Command> commands;
    public TextMenu()
    {
        this.commands= new MyDictionary<>();
    }
    public void addCommand(Command comm)
    {
        this.commands.put(comm.getKey(),comm);
    }
    public void printMenu()
    {
        for(Command command: commands.values())
        {
            String line=(command.getKey()+":"+command.getDesc());
            System.out.println(line);
        }
    }
    public void show()
    {
        Scanner scanner= new Scanner(System.in);
        while(true)
        {
            printMenu();
            System.out.println("Input Option: ");
            String key=scanner.nextLine();
            try
            {
                Command command=commands.lookUp(key);
                command.execute();
            } catch (ADTException e) {
                System.out.println("Invalid option");
            }
        }
    }
}
