package driver.commands;
import java.io.PrintWriter;
import driver.BlockChainDriver;

public class CheckCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    PrintWriter pen = instance.getPen();
    if (instance.getBlockChain().isValidBlockChain()) {
      pen.println("Chain is valid!");
      return;
    }

    pen.println("Chain is invalid!");
  }

  @Override
  public String getDescription() {
    return "checks that the block chain is valid";
  }
  
}
