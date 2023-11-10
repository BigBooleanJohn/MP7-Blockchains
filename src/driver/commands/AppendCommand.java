package driver.commands;
import driver.BlockChainDriver;

public class AppendCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

  @Override
  public String getDescription() {
    return "appends a new block onto the end of the chain";
  }
  
}
