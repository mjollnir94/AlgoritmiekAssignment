package com.marcelANDevertjan.main.Assignment.Scenario3.BinTree;

import com.marcelANDevertjan.main.Assignment.Scenario3.Data.Customer;

import java.util.ArrayList;

/**
 * This Class was created by marcel on 9-2-2015
 * Time of creation : 16:26
 *
 * todo: removal of nodes!
 */
public class Tree {

    private Node root;

    /**
     * Add a single node to the tree
     *
     * @param customer a single created customer
     * @return boolean, false when there was an error in adding the customer. Two customers share the same ID.
     *                  true when the customer in the list were added to the tree with no errors.
     */
    public boolean addNode(Customer customer){

        Node addedNode = new Node(customer);

        /**
         *  if root is equal to null,
         *  the added node becomes the root node
         */
        if(root == null) {
            root = addedNode;

            return true;
        }

        /**
         * else,
         * the new node needs a place in the tree
         */
        else {

            Node hover = root, parent;

            /**
             * no place has been found
             *
             * when an empty spot has found the
             * function returns out of the while
             */
            while (true) {
                parent = hover;

                /**
                 * if the added node's Id is the same as the checked node's id,
                 * notify and do not add
                 */
                if(addedNode.getID().compareToIgnoreCase(hover.getID()) == 0) {
                    System.out.println("node already exists");
                    return false;
                }

                /**
                 * if the added node's id has a lower value than the checked node,
                 * go left
                 */
                else if(addedNode.getID().compareToIgnoreCase(hover.getID()) < 0) {
                    hover = hover.getLeft();

                    /**
                     * when the spot is empty,
                     * add the newer node in this place
                     * and return
                     */
                    if(hover == null){
                        parent.setLeft(addedNode);
                        return true;
                    }
                }

                /**
                 * else, it should be a bigger value,
                 * so go to the right
                 */
                else {
                    hover = hover.getRight();

                    /**
                     * when the spot is empty,
                     * add the newer node in this place
                     * and return
                     */
                    if(hover == null){
                        parent.setRight(addedNode);
                        return true;
                    }
                }
            }
        }
    }

    /**
     * Adding a list of customers to the tree.
     *
     * @param customers list of created customers
     * @return boolean, false when there was an error in adding the customers. Two customers share the same ID.
     *                  true when all customers in the list were added to the tree with no errors.
     */
    public boolean addNodes(ArrayList<Customer> customers){

        /**
         * for each item in the arraylist,
         * create and add a node to the tree
         */
        for (Customer customer : customers) {

            /**
             * if a customer cannot be added to the tree
             * return false and exit.
             */
            if(!addNode(customer)){
                return false;
            }
        }
        /**
         * if all nodes were successfully added
         * return true and exit.
         */
        return true;
    }

    /**
     * Show all nodes in the tree ordered from lowest value to highest.
     *
     * @param node the node from where so start, most of the times this would be root.
     */
    public void inOrderTraverse(Node node) {
        if(node != null){
            inOrderTraverse(node.getLeft());

            System.out.println(node);

            inOrderTraverse(node.getRight());
        }
    }

    /**
     * Removes a specific node from the tree by its ID
     *
     * @param id
     * @return
     */
    public boolean removeNode(String id){

        /**
         * TODO LIST :
         *
         * > FIND THE NODE
         * > CHECK IF IT HAS CHILDREN
         *      > DELETE WITH NO OTHER ACTIONS IF IT HAS NOT
         *      > IF IT HAS ONLY ONE CHILD MOVE THE CHILD TO NODE-TO-REMOVE AND REMOVE THE NODE
         *      > IF IT HAS TWO CHILDREN START MOVING OTHER NODES TO MAKE REMOVAL POSSIBLE
         *
         */

        Node hover = root;
        Node parent = root;

        /**
         * Finding the node.
         */
        while(!hover.getID().equals(id)) {

            parent = hover;

            /**
             * If the ID of the hover is bigger then the one that is checked.
             * It means the node to be deleted is on the left side of the current node
             *
             */
            if(hover.getID().compareToIgnoreCase(id) < 0) {
                hover = hover.getLeft();
            }

            /**
             * Else, the node is on the right side, so move to the right
             */
            else {
                hover = hover.getRight();
            }

            /**
             * if the current node is null
             * the node did not exist
             * so it can not be deleted
             */
            if(hover == null) {
                System.out.println(id+" was not found, check if id was right.");
                return false;
            }
        }

        /**
         * If both children of the node-to-remove are null,
         * remove the node with no further ado
         */
        if(hover.getLeft() == null && hover.getRight() == null){

            if(hover.equals(root)){
                root = null;
            } else {
                if(parent.getLeft().getID().equals(id)){
                    parent.setLeft(null);
                    return true;
                } else {
                    parent.setRight(null);
                    return true;
                }
            }
        }

        /**
         * if RIGHT is NULL
         * and LEFT isnot NULL
         *
         *
         */
        else if (hover.getRight() == null && hover.getLeft() != null) {


        }

        /**
         * if RIGHT isnot NULL
         * and LEFT is NULL
         *
         *
         */
        else if (hover.getRight() != null && hover.getLeft() == null) {


        }




        return false;
    }

    public Node find(String id){
        Node hover = root;

        System.out.println("---------------- \nSearch Started;");
        System.out.println("Searching for "+id);
        while(!id.equals(hover.getID())){
            if(id.compareToIgnoreCase(hover.getID()) < 0) {
                hover = hover.getLeft();
            }
            else {
                hover = hover.getRight();
            }

            if(hover == null){
                System.out.println(id+" was not found, returning null.\n----------------\n");
                return null;
            }
        }

        System.out.println("Node found, user is : "+hover+"\n----------------\n");
        return hover;
    }

    public Node getRoot() {
        return root;
    }
}