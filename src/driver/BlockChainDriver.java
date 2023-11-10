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
  private boolean isRunning;
  private BlockChain blockChain;
  private HashMap<String, Command> commandRegistry;
  private PrintWriter pen;
  private BufferedReader inputReader;

  static HashMap<String, Command> defaultCommandRegistry = getDefaultCommandRegistry();

  public BlockChainDriver(int initial) {
    this.isRunning = true;
    this.blockChain = new BlockChain(initial);
    this.commandRegistry = defaultCommandRegistry;
    this.pen = new PrintWriter(System.out, true);
    this.inputReader = new BufferedReader(new InputStreamReader(System.in));
  }

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("ERROR: Missing initial amount!");
      return;
    }
    BlockChainDriver instance = new BlockChainDriver(Integer.parseInt(args[0]));
    BufferedReader input = instance.getInputReader();
    PrintWriter pen = instance.getPen();

    while (instance.isRunning) {
      pen.println(instance.blockChain);
      pen.print("Command? ");
      pen.flush();
      String command = input.readLine();
      try {
        instance.getCommands().getOrDefault(command, new DefaultCommand()).run(instance);
      } catch (UnsupportedOperationException e) {
        pen.println("ERROR: " + e.getMessage());
      }
      pen.println();
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

  public PrintWriter getPen() {
    return this.pen;
  }

  public HashMap<String, Command> getCommands() {
    return this.commandRegistry;
  }

  public BufferedReader getInputReader() {
    return this.inputReader;
  }

  public BlockChain getBlockChain() {
    return this.blockChain;
  }
}
