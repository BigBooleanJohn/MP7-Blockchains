package driver.commands;
import driver.BlockChainDriver;

public interface Command {
  public void run(BlockChainDriver instance);

  public String getDescription();
}
