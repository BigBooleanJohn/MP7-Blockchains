package driver.commands;
import driver.BlockChainDriver;

public class DefaultCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    throw new UnsupportedOperationException("Command not found!");
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
  }
  
}
