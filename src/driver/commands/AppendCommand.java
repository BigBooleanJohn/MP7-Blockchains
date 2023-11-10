package driver.commands;
import java.io.BufferedReader;
import java.io.PrintWriter;
import blockchain.Block;
import blockchain.BlockChain;
import driver.BlockChainDriver;

public class AppendCommand implements Command {

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

    pen.print("Nonce? ");
    pen.flush();

    long nonce;

    try {
      nonce = Integer.parseInt(input.readLine());
    } catch (Exception e) {
      pen.println("ERROR: Invalid nonce given!");
      return;
    }

    BlockChain blockChain = instance.getBlockChain();
    blockChain.append(new Block(blockChain.getSize(), amount, blockChain.getLast().getHash(), nonce));
  }

  @Override
  public String getDescription() {
    return "appends a new block onto the end of the chain";
  }
  
}
