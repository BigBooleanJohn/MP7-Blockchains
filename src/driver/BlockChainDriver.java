package driver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import blockchain.BlockChain;
import driver.commands.AppendCommand;
import driver.commands.CheckCommand;
import driver.commands.Command;
import driver.commands.DefaultCommand;
import driver.commands.HelpCommand;
import driver.commands.MineCommand;
import driver.commands.QuitCommand;
import driver.commands.RemoveCommand;
import driver.commands.ReportCommand;

public class BlockChainDriver {
  boolean isRunning;
  BlockChain blockChain;
  HashMap<String, Command> commandRegistry;

  static HashMap<String, Command> defaultCommandRegistry = getDefaultCommandRegistry();

  public BlockChainDriver(int initial) {
    this.isRunning = true;
    this.blockChain = new BlockChain(initial);
    this.commandRegistry = defaultCommandRegistry;
  }

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("ERROR: Missing initial amount!");
      return;
    }
    BlockChainDriver instance = new BlockChainDriver(Integer.parseInt(args[0]));
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    while (instance.isRunning) {
      pen.println(instance.blockChain);
      pen.print("Command? ");
      pen.flush();
      String command = input.readLine();
      try {
        instance.commandRegistry.getOrDefault(command, new DefaultCommand()).run(instance);
      } catch (UnsupportedOperationException e) {
        pen.println("ERROR: " + e);
      }
    }
  }

  public static HashMap<String, Command> getDefaultCommandRegistry() {
    HashMap<String, Command> registry = new HashMap<>();

    registry.put("mine", new MineCommand());
    registry.put("append", new AppendCommand());
    registry.put("remove", new RemoveCommand());
    registry.put("check", new CheckCommand());
    registry.put("report", new ReportCommand());
    registry.put("help", new HelpCommand());
    registry.put("quit", new QuitCommand());

    return registry;
  }

  public void stop() {
    this.isRunning = false;
  }
}
