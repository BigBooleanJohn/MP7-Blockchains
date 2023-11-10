package blockchain;

public class BlockChain {
    Node first;
    Node last;
    int CurAlexisMoney;
    int CurBlakeMoney;

    /* this is an inner class to represent the nodes of the blockChain */
    class Node {
        Block block;
        Node nextNode;

        public Node(Block B) {
            this.block = B;
            this.nextNode = null;
        }
    }

    /*
     * creates a BlockChain that possess a single block the starts with the given
     * initial amount. Note that to create this block, the prevHash field should be
     * ignored when calculating the block’s own nonce and hash.
     */
    public BlockChain(int initial) {
        this.first = new Node(new Block(0, initial, null));
        this.last = this.first;// setting the end and front to point to the same node
        this.CurAlexisMoney = 0;
        this.CurBlakeMoney = 0;
        if (initial < 0) {
            this.CurBlakeMoney -= initial;
        } else {
            this.CurAlexisMoney += initial;
        }
    }

    /*
     * mines a new candidate block to be added to the end of the chain. The returned
     * Block should be valid to add onto this chain.
     * 
     * TODO: throwing an IllegalArgumentException if this block cannot be added
     * to the chain (because it is invalid wrt the rest of the blocks).
     */
    public void append(Block blk) throws IllegalArgumentException {
        // make sure that the hash of the new block is valid
        if (!blk.getHash().isValid()) {
            throw new IllegalArgumentException("Block has invalid hash!");
        }

        // check if the prevHash of the new block is equal to the hash of our current
        // last block

        if (!blk.getPrevHash().equals(this.last.block.getHash())) {
            throw new IllegalArgumentException("Block is not consistent with previous block!");
        }

        // and also check if the balances make sense
        int BlakeTemp = this.CurBlakeMoney;
        int AlexisTemp = this.CurAlexisMoney;
        BlakeTemp -= blk.getAmount();
        AlexisTemp += blk.getAmount();
        if (BlakeTemp < 0 || AlexisTemp < 0) {
            throw new IllegalArgumentException("Block's transactions are illegal!");
        }
        this.CurBlakeMoney = BlakeTemp;
        this.CurAlexisMoney = AlexisTemp;
        Node newBlockNode = new Node(blk);
        this.last.nextNode = newBlockNode;// setting the new node to be the last
        this.last = newBlockNode;
    }

    public int getSize() {
        int counter = 0;
        Node curr = this.first;
        while (curr != null) {
            curr = curr.nextNode;
            counter++;
        }
        return counter;
    }

    public Block mine(int amount) {
        return new Block(this.getSize(), amount, this.last.block.getHash());
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
        return this.last.block.getHash();
    }

    public boolean isValidBlockChain() {
        Node temp = this.first;
        boolean result = true;
        while (temp != null && result != false) {
            result = temp.block.getHash().isValid();
            temp = temp.nextNode;
        }
        return result;
    }

    /*
     * print alexis and blake's balances on a single line
     * TODO: finish implementation
     */
    public void printBalances() {
        // Node temp = this.first;
        // while (temp != null) {
        // System.out.println(temp.block.toString());
        // }
    }

    /* returning a string representation of the chain */
    public String toString() {
        String s = ""; // empty string to start with
        Node temp = this.first;
        while (temp != null) {
            s = s + temp.block.toString();
            s = s + "\n";

            temp = temp.nextNode;
        }
        return s;
    }

    public Block getLast() {
        return this.last.block;
    }

}
