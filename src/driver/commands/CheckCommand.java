package driver.commands;
import driver.BlockChainDriver;

public class CheckCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

  @Override
  public String getDescription() {
    return "checks that the block chain is valid";
  }
  
}
