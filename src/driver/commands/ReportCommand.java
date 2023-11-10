package driver.commands;
import driver.BlockChainDriver;

public class ReportCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    instance.getBlockChain().printBalances();
  }

  @Override
  public String getDescription() {
    return "reports the balances of Alexis and Blake";
  }
  
}
