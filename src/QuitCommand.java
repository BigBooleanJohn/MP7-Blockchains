public class QuitCommand implements Command {

  @Override
  public void run(BlockChainDriver instance) {
    instance.isRunning = false;
  }
  
}
