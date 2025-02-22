package p6_Package;


public class QueueClassLL {
    /**
     * queue data
     */
    public IteratorClassLL queueData;
    
    /**
     * default constructor
     */
    QueueClassLL()
    {
        queueData = new IteratorClassLL();
    }
    
    /**
     * copies a QueueClass object
     * 
     * @param copied QueueClass object to be copied
     */
    QueueClassLL(QueueClassLL copied)
    {
        queueData = new IteratorClassLL(copied.queueData);
    }
    
    /**
     * Puts data at the end of the queue
     * 
     * @param value integer data to be enqueued
     */
    public void enqueue(int value)
    {
        queueData.setToEnd();
        queueData.insertAfterIterator(value);
        queueData.setToBeginning();
    }
    
    /**
     * views data at front of queue
     * 
     * @return integer value found at front of queue
     */
    public int peekFront()
    {
        return queueData.retrieveAtCurrent();
    }
    
    /**
     * Removes data at the front of a queue
     * 
     * @return integer value removed from queue
     */
    public int dequeue()
    {
        int result = queueData.removeAtCurrent();
        queueData.setToBeginning();
        return result;
    }
    
    /**
     * clears queue data
     */
    public void clear()
    {
        queueData.clear();
    }
    
    /**
     * provides array data as a string
     */
    @Override
    public String toString()
    {
        return queueData.toString();
    }
}
