package driver.commands;
import java.io.PrintWriter;
import driver.BlockChainDriver;

public class HelpCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    PrintWriter pen = instance.getPen();
    pen.println("Valid commands:");
    for (String key : instance.getCommands().keySet()) {
      pen.println("\t" + key + ": " + instance.getCommands().get(key).getDescription());
    }
  }

  @Override
  public String getDescription() {
    return "prints this list of commands";
  }
  
}
