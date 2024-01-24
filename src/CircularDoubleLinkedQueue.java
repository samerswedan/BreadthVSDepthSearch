/*

Samer Swedan 



 */

import java.util.NoSuchElementException;

/**
 * Implementation of an unbounded queue ADT using linking
 *
 * Implementation is by contract and by reference
 *
 *
 * 
 */


public class CircularDoubleLinkedQueue<Cell extends Comparable>
{
    private class Node<T extends Comparable>
    {

        private T value;
        private Node<T> next;
        private Node<T> prev;
    }

    private Node<Cell> front, rear;

    /**
     * Constructor for objects of class LinkedQueue
     */
    public CircularDoubleLinkedQueue()
    {
        rear = null;
        front = null;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if queue is empty
     */
    public boolean isEmpty()
    {
        return (front == null);
    }

    /**
     * Precondition: None
     * Postcondition: returns false
     */
    public boolean isFull()
    {
        return false;
    }


    /**
     * Precondition: None
     * Postcondition: Adds a new element to the queue
     */
    public void enqueue(Cell item)
    {
        Node<Cell> newNode = new Node<>();
        newNode.value = item;

        if (front == null) {

            front = newNode;
            rear = newNode;

            front.next = rear;
            rear.prev = front;

        } else {

            newNode.next = front;
            newNode.prev = rear;

            front.prev = newNode;
            rear.next = newNode;
            rear = newNode;
        }
    }


    /**
     * Precondition: Qeueue is not empty
     * Postcondition: removes and retuens the front item from the queue
     */
    public Cell dequeue()
    {
        if (isEmpty()) {

            throw new NoSuchElementException("Queue is empty");
        }

        Cell tmp = front.value;

        if (front == rear) {
            front = null;
            rear = null;

        } else {
            front = front.next;
            front.prev = rear;
            rear.next = front;
        }

        return tmp;
    }



}
