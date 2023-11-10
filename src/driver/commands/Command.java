package driver.commands;
import driver.BlockChainDriver;

public interface Command {
  /**
   * Runs the command on the current BlockChainDriver instance
   */
  public void run(BlockChainDriver instance);

  /**
   * Returns the description of this command.
   */
  public String getDescription();
}
