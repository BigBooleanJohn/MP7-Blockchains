
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

public class Block {
  private int num;
  private int amount;
  private Hash prevHash;
  private long nonce;
  private Hash hash;

  /**
   * Creates a new block from the specified parameters, performing the mining
   * operation to discover the nonce and hash for this block given these parameters.
   */
  public Block (int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this(num, amount, prevHash, calculateNonce(num, amount, prevHash));
  }

  /**
   * Creates a new block from the specified parameters, using the provided nonce
   * and additional parameters to generate the hash for the block. Because the
   * nonce is provided, this constructor does not need to perform the mining
   * operation; it can compute the hash directly.
   */
  public Block (int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    this.hash = new Hash(Hash.calculateHash(getHashed()));
  }

  private static long calculateNonce(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    int dataSize = Integer.BYTES + Integer.BYTES + prevHash.getSize() + Long.BYTES;
    ByteBuffer blockData = ByteBuffer.allocate(dataSize);
    long possibleNonce = -1;  // start at -1 because do while loop will change it to 0

    do {
      possibleNonce++;
      blockData = ByteBuffer.allocate(dataSize);
      blockData = blockData.putInt(num).putInt(amount).put(prevHash.getData()).putLong(possibleNonce);
    } while (!(new Hash(Hash.calculateHash(blockData.array())).isValid()));

    return possibleNonce;
  }

  private byte[] getHashed() {
    return getHashed(this.num, this.amount, this.prevHash, this.nonce);
  }

  private static byte[] getHashed(int num, int amount, Hash prevHash, long nonce) {
    int dataSize = Integer.BYTES + Integer.BYTES + prevHash.getSize() + Long.BYTES;
    ByteBuffer blockData = ByteBuffer.allocate(dataSize);
    blockData.putInt(num).putInt(amount).put(prevHash.getData()).putLong(nonce);
    return blockData.array();
  }

  /**
   * Returns the number of this block.
   */
  public int getNum() {
    return this.num;
  }

  /**
   * Returns the amount transferred that is recorded in this block.
   */
  public int getAmount() {
    return this.amount;
  }

  /**
   * Returns the nonce of this block.
   */
  public long getNonce() {
    return this.nonce;
  }

  /**
   * Returns the hash of the previous block in the blockchain.
   */
  public Hash getPrevHash() {
    return this.prevHash;
  }

  /**
   * Returns the hash of this block.
   */
  public Hash getHash() {
    return this.hash;
  }

  /**
   * Returns a string representation of the block.
   */
  @Override
  public String toString() {
    return "Block " + getNum() + "(Amount: " + getAmount() + ", Nonce: "
      + getNonce() + ", prevHash: " + getPrevHash() + ", hash: " + getHash();
  }

}