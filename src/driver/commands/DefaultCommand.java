package driver.commands;
import driver.BlockChainDriver;

public class DefaultCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    throw new UnsupportedOperationException("Command not found!");
  }
  
}