package driver.commands;
import driver.BlockChainDriver;

public class RemoveCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

  @Override
  public String getDescription() {
    return "removes the last block from the end of the chain";
  }
  
}
