package driver.commands;
import driver.BlockChainDriver;

public class QuitCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    instance.stop();
  }

  @Override
  public String getDescription() {
    return "quits the program";
  }
  
}
