import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class BlockTest {
  @Test
  public void firstBlockCreationTest() {
    try {
      Block testBlock = new Block(1, 2, null);
      assertEquals(1, testBlock.getNum());
      assertEquals(2, testBlock.getAmount());
      assertTrue(testBlock.getHash().isValid());
      assertTrue(testBlock.getNonce() >= 0);
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  @Test
  public void blockCreationTest() {
    try {
      Block firstBlock = new Block(1, 2, null);
      Block testBlock = new Block(2, 1, firstBlock.getHash());
      assertEquals(2, testBlock.getNum());
      assertEquals(1, testBlock.getAmount());
      assertTrue(testBlock.getHash().isValid());
      assertTrue(testBlock.getPrevHash().isValid());
      assertTrue(testBlock.getNonce() >= 0);
    } catch (Exception e) {
      fail(e.toString());
    }
  }
}
