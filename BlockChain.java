import java.security.NoSuchAlgorithmException;

public class BlockChain {
    Node first;
    Node last;

    /* this is an inner class to represent the nodes of the blockChain */
    class Node {
        Block BlockInNode;
        Node nextNode;

        public Node(Block B) {
            this.BlockInNode = B;
            this.nextNode = null;
        }
    }

    /*
     * creates a BlockChain that possess a single block the starts with the given
     * initial amount. Note that to create this block, the prevHash field should be
     * ignored when calculating the block’s own nonce and hash.
     * 
     * NOTE: STILL NEEDS TO BE IMPLEMENTED
     */
    public BlockChain(int initial) {
        // this.first = new Node(initial, 0, null); make a new node with no previous
        // hash STILL NEEDS TO BE IMPLEMENTED
        this.last = this.first;// setting the end and front to point to the same node
    }

    /*
     * mines a new candidate block to be added to the end of the chain. The returned
     * Block should be valid to add onto this chain.
     */
    public void append(Block blk) {
        Node N = this.first;
        while (N.nextNode != null) {
            N = N.nextNode;// looping to get to the last node in the list
        }
        Node newBlockNode = new Node(blk);
        N.nextNode = newBlockNode;// setting the new node to be the
    }

    public int getSize() {
        int counter = 0;
        while (this.first != null) {
            this.first = this.first.nextNode;
            counter++;
        }
        return counter;
    }

    public Block mine(int amount) {
        Block B = null;
        try {
            B = new Block(this.getSize(), amount, this.last.BlockInNode.getHash());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return B;
    }

    public boolean removeLast() {
        if (this.first == this.last) {
            return false;
        }
        Node temp = this.first;
        while (temp.nextNode != this.last) {// looping to get to the value before the last value
            temp = temp.nextNode;
        }
        temp.nextNode = null; // setting the last node to be null, hence removing it
        this.last = temp;
        return true;
    }

    public Hash getHash() {
        return this.last.BlockInNode.getHash();
    }

    public boolean isValidBlockChain() {
        Node temp = this.first;
        boolean result = true;
        while (temp != null && result != false) {
            result = temp.BlockInNode.getHash().isValid();
            temp = temp.nextNode;
        }
        return result;
    }

    /* printing each hash in the chain */
    public void printBalances() {
        Node temp = this.first;
        while (temp != null) {
            System.out.println(temp.BlockInNode.toString());
        }
    }

    /* returning a string representation of the chain */
    public String toString() {
        String s = ""; // empty string to start with
        Node temp = this.first;
        while (temp != null) {
            s = s + temp.BlockInNode.toString();
            s = s + ",\n";
        }
        return s;
    }

}
