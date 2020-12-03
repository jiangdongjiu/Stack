import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/// <summary>
/// StackTest - A class for testing the Stack class
/// Stack - A class that is linked list of Nodes.
///         Contains the methods to treat the linked list as a Stack
/// Assignment:     #2
/// Course:         ADEV-3001
/// Date Created:   Sept. 18th, 2019
///
/// Revision Log
/// Who         When        Reason
/// ----------- ----------- ---------------
///
/// @author: Scott Wachal
/// @version 1.0
/// </summary>
public class StackTests
{
    //region Constructor Tests
    /// <summary>
    /// Test the constructor to ensure the default values are set properly.
    /// </summary>
    @Test
    public void ConstructorTest()
    {
        Stack<Point> stack = new Stack<Point>();
        assertNull(stack.getHead());
    }
    // endregion

    //region Public Methods Test
    //region getSize()
    /// <summary>
    /// Test getSize() to ensure it returns zero on empty stack.
    /// </summary>
    @Test
    public void getSizeOnEmptyStackTest()
    {
        Stack<Point> stack = new Stack<Point>();

        assertEquals(stack.getSize(),0);
    }
    // endregion


    //region push()
    /// <summary>
    /// Test push() to ensure node is added to stack and as the head
    /// </summary>
    @Test
    public void push_increases_size_by_1_Test()
    {
        Point newPoint = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();

        assertEquals(stack.getSize(),0);

        stack.push(newPoint);

        assertEquals(stack.getSize(),1);
    }

    /// <summary>
    /// Test push() to ensure node is added to head of stack.
    /// </summary>
    @Test
    public void push_Inserts_To_Head_Test()
    {
        Point newPoint = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();

        stack.push(newPoint);

        Point headPoint = stack.getHead().getElement();
        assertEquals(headPoint, newPoint);
        assertNull(stack.getHead().getPrevious());
    }

    /// <summary>
    /// Test push() to ensure node is added to head of stack.
    /// </summary>
    @Test
    public void push_Inserts_To_Head_when_list_is_larger_Test()
    {
        Point point01 = new Point(3, 5);
        Point point02 = new Point(6, 7);
        Stack<Point> stack = new Stack<Point>();

        stack.push(point02);
        stack.push(point01);

        Point headPoint = stack.getHead().getElement();
        Point bottomPoint = stack.getHead().getPrevious().getElement();
        assertEquals(headPoint, point01);
        assertEquals(bottomPoint, point02);

        assertEquals(stack.getSize(),2);
    }
    // endregion

    //region IsEmpty()
    /// <summary>
    /// Test IsEmpty() returns true on empty stack.
    /// </summary>
    @Test
    public void IsEmptyOnEmptyStackTest()
    {
        Stack<Point> stack = new Stack<Point>();

        assertTrue(stack.isEmpty());
    }

    /// <summary>
    /// Test IsEmpty() returns false on a stack with elements.
    /// </summary>
    @Test
    public void IsEmptyOnStackWithElements()
    {
        Point point01 = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point01);

        assertFalse(stack.isEmpty());
    }
    // endregion

    //region top()
    /// <summary>
    /// Test top() throws an exception when called on an empty stack.
    /// </summary>
    @Test
    public void top_Throws_Exception_On_EmptyStack_Test()
    {
        Stack<Point> stack = new Stack<Point>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> stack.top());
        assertEquals("top() not allowed on Empty Stack!", exception.getMessage());
    }
    /// <summary>
    /// Test top() to ensure it returns the top node.
    /// </summary>
    @Test
    public void top_returns_head_in_list_of_1_Test()
    {
        Point point01 = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point01);

        Point returnedPoint = stack.top();
        Point headPoint = stack.getHead().getElement();

        assertEquals(returnedPoint, point01);
        assertEquals(headPoint, returnedPoint);
    }

    /// <summary>
    /// Test top() to ensure it returns the top node.
    /// </summary>
    @Test
    public void top_returns_head_in_larger_list_Test()
    {
        Point point01 = new Point(3, 5);
        Point point02 = new Point(3, 5);
        Point point03 = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point03);
        stack.push(point02);
        stack.push(point01);

        Point returnedPoint = stack.top();
        Point headPoint = stack.getHead().getElement();
        Point secondPoint = stack.getHead().getPrevious().getElement();
        Point thirdPoint = stack.getHead().getPrevious().getPrevious().getElement();
        Node<Point> lastNode = stack.getHead().getPrevious().getPrevious();

        assertEquals(returnedPoint, point01);
        assertEquals(headPoint, returnedPoint);
        assertEquals(secondPoint, point02);
        assertEquals(thirdPoint, point03);

        // check that the last node still points to null!
        assertNull(lastNode.getPrevious());
    }

    /// <summary>
    /// Test top() to make sure it only returns the element and does not remove the element.
    /// </summary>
    @Test
    public void top_Does_Not_Remove_an_Element_Test()
    {
        Point newPoint = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(newPoint);

        Point returnedPoint = stack.top();

        assertEquals(stack.getSize(),1);
    }
    // endregion

    //region pop()
    /// <summary>
    /// Test pop() to ensure it throws and exception when called on an empty stack.
    /// </summary>
    @Test
    public void pop_Throws_Exception_On_EmptyStack_Test()
    {
        Stack<Point> stack = new Stack<Point>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> stack.pop());
        assertEquals("pop() not allowed on Empty Stack!", exception.getMessage());
    }

    /// <summary>
    /// Test pop() to ensure it reduces the size by 1
    /// </summary>
    @Test
    public void pop_decreases_size_by_1_Test()
    {
        Point point01 = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point01);

        Point returnedPoint = stack.pop();

        assertEquals(stack.getSize(),0);
    }

    /// <summary>
    /// Test pop() to ensure it returns the top element.
    /// </summary>
    @Test
    public void pop_returns_top_element_in_list_of_1_Test()
    {
        Point point01 = new Point(3, 5);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point01);

        Node<Point> oldHead = stack.getHead();
        Point oldHeadPoint = oldHead.getElement();
        Point returnedPoint = stack.pop();
        Node<Point> newHead = stack.getHead();

        assertEquals(oldHeadPoint, returnedPoint);
        assertEquals(returnedPoint, point01);

        // list of 1 after a remove is an empty list
        assertTrue(stack.isEmpty());
    }

    /// <summary>
    /// Test pop() to ensure it returns the top element, in a bigger list.
    /// </summary>
    @Test
    public void pop_returns_top_element_in_larger_list_Test()
    {
        Point point01 = new Point(3, 5);
        Point point02 = new Point(2, 4);
        Point point03 = new Point(1, 3);

        Stack<Point> stack = new Stack<Point>();
        stack.push(point03);
        stack.push(point02);
        stack.push(point01);

        Node<Point> oldHead = stack.getHead();
        Point oldHeadPoint = oldHead.getElement();
        Point returnedPoint = stack.pop();
        Node<Point> newHead = stack.getHead();
        Node<Point> lastNode = newHead.getPrevious();

        assertEquals(oldHeadPoint, returnedPoint);
        assertEquals(returnedPoint, point01);
        assertEquals(newHead.getElement(), point02);
        assertEquals(lastNode.getElement(), point03);
        assertNull(lastNode.getPrevious());

        assertEquals(stack.getSize(),2);
    }
    // endregion

    // region clear()
    /// <summary>
    /// Test clear() to ensure it returns size of zero and null head.
    /// </summary>
    @Test
    public void clear_on_populated_stack_sets_size_to_0_head_becomes_null_Test()
    {
        Stack<Point> stack = new Stack<Point>();
        stack.push(new Point(3, 5));
        stack.push(new Point(2, 4));
        stack.clear();
        assertNull(stack.getHead());
        assertTrue(stack.isEmpty());
    }
    //endregion

    //endregion
}
