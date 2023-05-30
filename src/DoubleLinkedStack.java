/*
CPSC 331 Assignment 2
Samer Swedan 30098990

This code is based on the code provided by Professor Jalal Kawash from D2L
it has been modified to be a Double-Linked Stack

 */

public class DoubleLinkedStack<Cell extends Comparable> {

    private class Node<Cell extends Comparable> {
        private Cell value;
        private Node<Cell> next;
        private Node<Cell> previous;
    }

    private Node<Cell> topIndex;

    public DoubleLinkedStack() {
        topIndex = null;
    }

    public boolean isEmpty() {
        return topIndex == null;
    }


    public boolean isFull() {
        return false;
    }

    public void push(Cell element) {

        Node<Cell> newNode = new Node<>();

        newNode.value = element;
        newNode.next = topIndex;
        newNode.previous = null;

        if (topIndex != null) {
            topIndex.previous = newNode;
        }
        topIndex = newNode;
    }

    public Cell pop() throws UnderflowException {

        if (!isEmpty()) {

            Cell temp = topIndex.value;
            topIndex = topIndex.next;

            if (topIndex != null) {

                topIndex.previous = null;
            }
            return temp;

        } else {
            throw new UnderflowException("Cannot pop because the stack is empty");
        }
    }

    public Cell peek() throws UnderflowException {

        if (!isEmpty()) {
            return topIndex.value;

        } else {
            throw new UnderflowException("Cannot peek because the stack is empty");
        }
    }


}




