package modularity.Console;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tommaso Garuglieri on 12/07/2016.
 */
public class Console {

    private List<Command> commandList;

    public Console(Command... commands) {
        this.commandList = new ArrayList<>();
        this.addCommands(commands);
    }

    public void addCommands(Command... commands) {
        Collections.addAll(this.commandList, commands);
    }

    public void removeCommand(Command command) {
        this.commandList.remove(command);
    }

    public void removeCommand(String name) {
        Command command = getCommand(name);
        if (command != null)
            this.commandList.remove(command);
    }

    @Nullable
    private Command getCommand(String name) {
        for (Command command : commandList)
            if (command.getName().equals(name))
                return command;
        return null;
    }

    public void run() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!input.equals("exit")) {

            input = scanner.nextLine().toLowerCase();

            String[] splittedInput = input.split(" ");

            Command command = getCommand(splittedInput[0]);
            if (command != null) {
                command.execute(splittedInput);
            } else {
                System.out.println(splittedInput[0] + " not a valid command");
            }

        }
    }

}
