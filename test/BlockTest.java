import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import blockchain.Block;

public class BlockTest {
  @Test
  public void firstBlockCreationTest() {
    try {
      Block testBlock = new Block(0, 1, null);
      assertEquals(0, testBlock.getNum());
      assertEquals(1, testBlock.getAmount());
      assertTrue(testBlock.getHash().isValid());
      assertTrue(testBlock.getNonce() >= 0);
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  @Test
  public void blockCreationTest() {
    try {
      Block firstBlock = new Block(0, 1, null);
      Block testBlock = new Block(1, 2, firstBlock.getHash());
      assertEquals(1, testBlock.getNum());
      assertEquals(2, testBlock.getAmount());
      assertTrue(testBlock.getHash().isValid());
      assertTrue(testBlock.getPrevHash().isValid());
      assertTrue(testBlock.getNonce() >= 0);
    } catch (Exception e) {
      fail(e.toString());
    }
  }
}
