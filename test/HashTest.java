import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import blockchain.Hash;

public class HashTest {
  @Test
  public void isEqualTest() {
    try {
      Hash hash1, hash2;
      hash1 = new Hash(new byte[] {0, 1, 2, 3, 4});
      hash2 = new Hash(new byte[] {0, 1, 2, 3, 4});
      assertTrue(hash1.equals(hash2));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void calculateHashTest() {
    try {
      Hash hash1, hash2;
      hash1 = new Hash(Hash.calculateHash(new byte[] {0, 1, 2, 3, 4}));
      hash2 = new Hash(Hash.calculateHash(new byte[] {0, 1, 2, 3, 4}));
      assertTrue(hash1.equals(hash2));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void isValidTest() {
    try {
      Hash hash = new Hash(new byte[] {0, 0, 0, 1});
      assertTrue(hash.isValid());
    } catch (Exception e) {
      fail();
    }
  }
}
