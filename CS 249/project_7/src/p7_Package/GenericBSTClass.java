package p7_Package;

/**
 * 
 * @author daniel_rustrum
 *
 * @param <GenericData>
 */
public class GenericBSTClass<GenericData extends java.lang.Comparable<GenericData>> {

    /**
     * Root of BST
     */
    private BST_Node BST_Root;
    /**
     * Traverse code - preorder
     */
    public static final int PREORDER_TRAVERSE = 101;
    /**
     * Traverse code - inorder
     */
    public static final int INORDER_TRAVERSE = 102;
    /**
     * Traverse code - postorder
     */
    public static final int POSTORDER_TRAVERSE = 103;
    
    /**
     * Default Constructor
     */
    public GenericBSTClass()
    {
        BST_Root = new BST_Node(null);
        BST_Root.leftChildRef = new BST_Node(null);
        BST_Root.rightChildRef = new BST_Node(null);
    }
    
    /**
     * Insert method for BST 
     * 
     * @param inData GenericData data to be added to BST
     */
    public void insert(GenericData inData)
    {
        insertHelper(BST_Root, inData);
    }
    
    /**
     * Insert helper method for BST insert action
     * 
     * @param localRoot BST_Node tree root reference at the current recursion level
     * @param inData GenericData data to be added to BST
     */
    private void insertHelper(BST_Node localRoot, GenericData inData)
    {
        if(localRoot.nodeData != null)
        {
            if(localRoot.nodeData.compareTo(inData) < 0)
            {
                insertHelper(localRoot.leftChildRef, inData);
            }
            else if(localRoot.nodeData.compareTo(inData) > 0)
            {
                insertHelper(localRoot.rightChildRef, inData);
            }
        }
        else
        {       
            localRoot.nodeData = inData;
            localRoot.leftChildRef = new BST_Node(null);
            localRoot.rightChildRef = new BST_Node(null);
        }
    }
    
    /**
     * Removes data node from tree using given key 
     * 
     * @param inData GenericData data to be added to BST
     * @return GenericData - result of remove action
     */
    public GenericData removeNode(GenericData inData)
    {
        GenericData searched = search(inData);
        BST_Node removed;
        if(searched != null)
        {
            removed = removeNodeHelper(BST_Root, inData);
            return removed.nodeData;
        }
        return null;
    }

    /**
     * Remove helper for BST remove action 
     * 
     * @param localRoot BST_Node tree root reference at the current recursion level
     * @param outData GenericData item that includes the necessary key
     * @return BST_Node - reference result of remove helper action
     */
    private BST_Node removeNodeHelper(BST_Node localRoot, GenericData outData)
    {
        BST_Node removedNode = localRoot;
        if(localRoot.nodeData.compareTo(outData) < 0)
        {
            removeNodeHelper(localRoot.leftChildRef, outData);
        }
        if(localRoot.nodeData.compareTo(outData) > 0)
        {
            removeNodeHelper(localRoot.rightChildRef, outData);
        }
        removeNodeFromTree(localRoot);
        return removedNode;
    }
    
    /**
     * Removes node and repairs tree
     * 
     * @param localRoot - root to be removed from tree
     * @return BST_Node - returns Removed Node
     */
    private BST_Node removeNodeFromTree(BST_Node localRoot)
    {
        BST_Node maxLocalNode;

        // No Children
        if(localRoot.leftChildRef == null && localRoot.rightChildRef == null)
        {
            return localRoot = null;
        }
        // Single Child
        if(localRoot.leftChildRef == null)
        {
            return localRoot = localRoot.rightChildRef;
        }
        if(localRoot.rightChildRef == null)
        {
            return localRoot = localRoot.leftChildRef;
        }
        
        // Two Children
        maxLocalNode = removeFromMax(localRoot, localRoot.leftChildRef);
        localRoot = maxLocalNode;
        return localRoot;
         
    }
    
    /**
     * Searches tree from given node to maximum value node below it, stores data value found, and then unlinks the node
     * 
     * @param maxParent BST_Node reference to current node
     * @param maxLoc BST_Node reference to child node to be tested
     * @return BST_Node - reference containing removed node
     */
    private BST_Node removeFromMax(BST_Node maxParent, BST_Node maxLoc)
    {
        if(maxLoc.leftChildRef.rightChildRef != null)
        {
            return maxLoc.leftChildRef.rightChildRef;
        }
        return maxLoc.leftChildRef;
    }
    
    /**
     * Searches for data in BST given GenericData with necessary key
     * 
     * @param searchData GenericData item containing key
     * @return GenericData - reference to found data
     */
    public GenericData search(GenericData searchData)
    {
        return searchHelper(BST_Root, searchData);
    }
    
    /**
     * Helper method for BST search action 
     * 
     * @param localRoot BST_Node tree root reference at the current recursion level
     * @param searchData GenericData item containing key
     * @return GenericData - item found
     */
    private GenericData searchHelper(BST_Node localRoot, GenericData searchData)
    {
        if(localRoot.nodeData == null)
        {
            return null;
        }
        if(localRoot.nodeData.compareTo(searchData) < 0)
        {
            return searchHelper(localRoot.leftChildRef, searchData);
        }
        if(localRoot.nodeData.compareTo(searchData) > 0)
        {
            return searchHelper(localRoot.rightChildRef, searchData);
        }
        return localRoot.nodeData;
    }
    
    /**
     * Provides user with three ways to display BST data
     * 
     * @param traverseCode integer code for selecting BST traversal method, accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
     */
    public void displayTree(int traverseCode)
    {
        if(traverseCode == PREORDER_TRAVERSE)
        {
            displayPreOrder(BST_Root);
        }
        else if(traverseCode == INORDER_TRAVERSE)
        {
            displayInOrder(BST_Root);
        }
        else if(traverseCode == POSTORDER_TRAVERSE)
        {
            displayPostOrder(BST_Root);
        }
        else
        {
           System.out.println("You entered the wrong print code.");
        }
    }
    
    /**
     * Provides preOrder traversal action
     * 
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayPreOrder(BST_Node localRoot)
    {
        if(localRoot.nodeData != null)
        {
            System.out.println("" + localRoot.nodeData);
            displayPreOrder(localRoot.leftChildRef);
            displayPreOrder(localRoot.rightChildRef);
        }
    }
    
    /**
     *Provides postOrder traversal action
     *
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayPostOrder(BST_Node localRoot)
    {
        if(localRoot.nodeData != null)
        {
            displayPostOrder(localRoot.leftChildRef);
            displayPostOrder(localRoot.rightChildRef);
            System.out.println("" + localRoot.nodeData);
        }
    }
    
    /**
     * Provides inOrder traversal action
     * 
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayInOrder(BST_Node localRoot)
    {
        if(localRoot.nodeData != null)
        {
            displayInOrder(localRoot.leftChildRef);
            System.out.println("" + localRoot.nodeData);
            displayInOrder(localRoot.rightChildRef);
        }
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = new BST_Node(null);
        BST_Root.leftChildRef = new BST_Node(null);
        BST_Root.rightChildRef = new BST_Node(null);
    }
    
    /**
     * Test for empty tree
     * 
     * @return Boolean - result of test
     */
    public boolean isEmpty()
    {
        return BST_Root.nodeData == null;
    }

    /**
     * Binary Search Tree node class for managing generic data
     * 
     * @author daniel_rustrum
     *
     */
    private class BST_Node {
        public BST_Node leftChildRef = null;
        private GenericData nodeData = null;
        public BST_Node rightChildRef = null;
        
        BST_Node(GenericData inData)
        {
            nodeData = inData;
        }
    }
    
}
