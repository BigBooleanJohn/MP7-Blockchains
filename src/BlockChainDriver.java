

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

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
    BlockChainDriver instance = new BlockChainDriver(Integer.getInteger(args[0]));
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    while (instance.isRunning) {
      pen.println(instance.blockChain);
      pen.print("Command? ");
      String command = input.readLine();
      instance.commandRegistry.getOrDefault(command, new DefaultCommand()).run(instance);
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
}
