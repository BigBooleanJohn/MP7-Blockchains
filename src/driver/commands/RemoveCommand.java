package driver.commands;
import driver.BlockChainDriver;

public class RemoveCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    instance.getBlockChain().removeLast();
  }

  @Override
  public String getDescription() {
    return "removes the last block from the end of the chain";
  }
  
}
