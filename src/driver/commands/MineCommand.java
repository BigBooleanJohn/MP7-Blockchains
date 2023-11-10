package driver.commands;
import java.io.BufferedReader;
import java.io.PrintWriter;
import blockchain.Block;
import blockchain.BlockChain;
import driver.BlockChainDriver;

public class MineCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    PrintWriter pen = instance.getPen();
    BufferedReader input = instance.getInputReader();

    pen.print("Amount transferred? ");
    pen.flush();

    int amount;

    try {
      amount = Integer.parseInt(input.readLine());
    } catch (Exception e) {
      pen.println("ERROR: Invalid amount given!");
      return;
    }

    BlockChain blockChain = instance.getBlockChain();
    Block discoveredBlock = (blockChain.mine(amount));
    
    pen.println("amount = " + discoveredBlock.getAmount() + ", nonce = " + discoveredBlock.getNonce());
  }

  @Override
  public String getDescription() {
    return "discovers the nonce for a given transaction";
  }
  
}
