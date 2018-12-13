package p2_Package;

public class GenericArithmeticClass<GenericData extends Comparable<GenericData>>
{
    
    /**
     * holds default capacity of array if it is not specified
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * holds a local array of generic items
     */
    private Object[] localArray;
    
    /**
     * holds the capacity of the array
     */
    private int arrayCapacity;
    
    /**
     * Default constructor, Initializes array to specified capacity
     */
    public GenericArithmeticClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;
        localArray = new Object[arrayCapacity];
    }
    
    /**
     * Initializing constructor, Initializes array to specified capacity
     * 
     * @param capacity - integer maximum capacity specified for the array
     */
    public GenericArithmeticClass(int capacity)
    {
        arrayCapacity = capacity;
        localArray = new Object[arrayCapacity];
    }
    
    /**
     * Copy constructor, initializes array to capacity of copied array, then copies
     * the elements up to the given capacity
     * 
     * @param copied - ArrayClass object to be copied
     */
    public GenericArithmeticClass(GenericArithmeticClass<GenericData> copied)
    {
        arrayCapacity = copied.arrayCapacity;
        localArray = copyArray(copied.localArray, copied.arrayCapacity);
    }
    
    /**
     * Copies an array for the copy constructor
     * <p>
     * @param copiedArray takes in the array to be copied
     * @return arrayToBeCopied - returns a copied array for the copy constructor
     */
    private Object[] copyArray(Object[] copiedArray, int length)
    {
        int index;
        Object[] arrayToBeCopiedToo = new Object[length];
        for(index = 0;index < length;index++)
        {
            arrayToBeCopiedToo[index] = copiedArray[index];
        }
        return arrayToBeCopiedToo;
    }
    
    /**
     * Sets element into array at index
     * 
     * @param index - location in index to place data
     * @param newValue - value to be appended to array
     * 
     * @return boolean - success if element set at index, false index outside boundaries
     */
    public boolean setValueAt(int index, GenericData newValue)
    {
        if(index < 0 || index > arrayCapacity)
        {
            return false;
        }
        localArray[index] = newValue;
        return true;
    }
    
    /**
     * Acquires data at specified element
     * 
     * @param index - index of element into which value is to be placed
     * 
     * @return GenericData - item retrieved from array
     * @return null - if index is out of bounds
     */
    @SuppressWarnings("unchecked")
    public GenericData getValueAt(int index)
    {
        if(index < 0 || index > arrayCapacity)
        {
            return null;
        }
        return (GenericData)localArray[index];
    }
    
    /**
     * Resets array capacity, and current array capacity value
     * <p>
     * Exception: Methods will not resize capacity below current array capacity,
     * returns false if this is attempted, true otherwise
     * 
     * @param newCapacity - new capacity to be set; must be larger than current capacity
     * @return boolean - resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        if(arrayCapacity < newCapacity)
        {
            arrayCapacity = newCapacity;
            return true;
        }
        return false;
    }
    
    /**
     * Sorts elements using the bubble sort algorithm
     * 
     * @param size - indicates how many items to sort; method will sort all items between 0 and index -1
     */
    @SuppressWarnings("unchecked")
    public void runBubbleSort(int size)
    {
        int index;
        int tempComparison;
        boolean swapped = true;
        
        // Algorithm
        while(swapped)
        {
            swapped = false;
            for( index = 0; index < size-1; index++)
            {
                tempComparison = ((Comparable<GenericData>)localArray[index]).compareTo((GenericData) localArray[index + 1]);
                if(tempComparison > 0)
                {
                    swapElements(index, index + 1);
                    swapped = true;
                }
            }
            runBubbleSort(size-1);
        }
    }
    
    /**
     * Sorts element using Selection sort algorithm
     * 
     * @param size - indicates how many items to sort; method will sort all items between 0 and index -1
     */
    @SuppressWarnings("unchecked")
    public void runSelectionSort(int size)
    {
        int index, smallestIndex, sorter, tempComparison;
        
        // Algorithm
        for (index = 0; index < size - 1; index++)
        {
            sorter = index;
            for (smallestIndex = index + 1; smallestIndex < size; smallestIndex++)
            {
                tempComparison = ((Comparable<GenericData>)localArray[smallestIndex]).compareTo((GenericData) localArray[sorter]);
                if (tempComparison < 0) 
                {
                    index = smallestIndex;
                }
            }
            swapElements(sorter,index);
        }
    }
    
    /**
     * Sorts element using the Insertion sort method
     * 
     * @param size - indicates how many items to sort; method will sort all items between 0 and index -1
     *
     */
    @SuppressWarnings("unchecked")
    public void runInsertionSort(int size)
    {
        int outerIndex, innerIndex, tempComparison;
        int beginningOfArray = -1;
        GenericData value;
        
        // Algorithm
        for(outerIndex = 1; outerIndex < size- 1; outerIndex++)
        {
            value = (GenericData) localArray[outerIndex];
            innerIndex = outerIndex;
            tempComparison = ((Comparable<GenericData>)localArray[innerIndex]).compareTo((GenericData) localArray[innerIndex -1]);
            //
            while(innerIndex > beginningOfArray && tempComparison < 0)
            {
                innerIndex --;
            }
            localArray[innerIndex] = value;
        }
    }
    
    /**
     * Gets current capacity of array
     * 
     * @return int - capacity of array
     */
    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }
    
    /**
     * Swaps one element in the local array at a given index with another element
     * in the array at the other given element
     * 
     * @param oneIndex - index of one of two elements to be swapped
     * @param otherIndex - index of second of two elements to be swapped
     */
    @SuppressWarnings("unchecked")
    private void swapElements(int oneIndex, int otherIndex)
    {
        GenericData elementOne = (GenericData) localArray[oneIndex];
        localArray[oneIndex] = localArray[otherIndex];
        localArray[otherIndex] = elementOne;
    }
}
