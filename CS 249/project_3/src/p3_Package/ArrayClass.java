package p3_Package;

import java.util.Random;

public class ArrayClass {
    /**
     * capacity of array
     */
    private int arrayCapacity;
    /**
     * size of array
     */
    private int arraySize;
    /**
     * capacity used for array if default constructor called
     */
    private static int DEFUALT_CAPACITY = 10;
    /**
     * constant value for returning failed access message
     */
    public static int FAILED_ACCESS = -999999;
    /**
     * array used for stored data
     */
    private int[] localArray;
    
    /**
     * Default constructor, initializes array to default capacity (10)
     */
    ArrayClass()
    {
        arrayCapacity = DEFUALT_CAPACITY;
        arraySize = DEFUALT_CAPACITY;
        localArray = new int[DEFUALT_CAPACITY];
    }
    
    /**
     * Initializing constructor, initializes array to specified capacity
     * 
     * 
     * @param copied maximum capacity specification for the array
     */
    ArrayClass(ArrayClass copied)
    {
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        copyArray(copied.localArray, localArray, arrayCapacity);
    }
    
    /**
     * Initializing constructor, initializes array to specified capacity, size to specified value, then fills all elements with specified size value
     * 
     * 
     * @param capacity maximum capacity specification for the array
     */
    ArrayClass(int capacity)
    {
        arrayCapacity = capacity;
        arraySize = capacity;
        localArray = new int[capacity];
    }
    
    /**
     * 
     * 
     * 
     * @param capacity maximum capacity specification for the array
     * @param size sets the number of items to be filled in array, and sets the size of the ArrayClass object
     * @param fillValue value to be placed in all elements of initialized array up to the size
     */
    ArrayClass(int capacity, int size, int fillValue)
    {
        arrayCapacity = capacity;
        arraySize = size;
        localArray = new int[capacity];
        fillArray(fillValue);
    }
    
    /**
    * Copies an array for the copy constructor
    *
    * @param copiedArray takes in the array to be copied
    * @param arrayToBeCopiedTo copies all data to new array
    * @param length size of the array to be copied
    */
   private void copyArray(int[] copiedArray, int[] arrayToBeCopiedTo, int length)
   {
       int index;
       for(index = 0;index < length;index++)
       {
           arrayToBeCopiedTo[index] = copiedArray[index];
       }
   }
   
   /**
    * Fill array with data
    * 
    * @param arrayToFill fills array with data
    * @param data data to fill array
    * @param length size of array
    */
   private void fillArray(int dataValue)
   {
       int index;
       for(index = 0; index < arraySize; index++)
       {
           localArray[index] = dataValue;
       }
   }
   
   
    /**
     * Accesses item in array at specified index if index within array size bounds
     * 
     * @param accessIndex index of requested element value
     * @return int - access value if successful
     * @return int - returns -999999 if failed
     */
    public int accessItemAt(int accessIndex)
    {
        if(accessIndex < arraySize){
            return localArray[accessIndex];
        }
        return FAILED_ACCESS;
    }
    
    /**
     * Appends item to end of array, if array is not full, e.g., no more values can be added
     * 
     * @param newValue value to be appended to array
     * @return boolean - true if appended
     */
    public boolean appendItem(int newValue)
    {
        if(arraySize < arrayCapacity)
        {
            localArray[arraySize] = newValue;
            arraySize++;
            return true;
        }
        return false;
    }
    
    /**
     * Clears array of all valid values by setting array size to zero, values remain in array but are not accessible
     */
    public void clear()
    {
        fillArray(0);
    }
    
    /**
     * dumps array data to the screen for diagnostic purposes
     */
    @SuppressWarnings("unused")
    private void dumpArrayData()
    {
        int index;
        for(index = 0; index < arraySize; index++)
        {
            System.out.println(localArray[index]);
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
     * Gets current size of array 
     * 
     * @return int - size of array
     */
    public int getCurrentSize()
    {
        return arraySize;
    }
    
    /**
     * Generates random number between given low and high values
     * 
     * @param low lowest value that will be generated by method
     * @param high highest value that will be generated by method
     * @return int - the generated random value
     * 
     */
    private int getRandBetween(int low, int high)
    {  
        Random randomNumber = new Random();
        int numDifference, randomInt, randomAlg, numDiffPlusOne;
        
        numDifference = high - low;
        numDiffPlusOne = numDifference + 1;
        randomAlg = randomNumber.nextInt(numDiffPlusOne);
        randomInt = randomAlg + low;
        return randomInt;   
        
    }
    
    /**
     *  Inserts item to array at specified index if array is not full, e.g., no more values can be added
     * 
     * 
     * @param insertIndex index of element into which value is to be inserted
     * @param newValue value to be inserted into array
     * @return boolean - true if inserted
     */
    public boolean insertItemAt(int insertIndex, int newValue)
    {
        if(insertIndex < arraySize && arraySize < arrayCapacity)
        {
            shuffleIndexRight(insertIndex);
            localArray[insertIndex] = newValue;
            return true;
        }
        return false;
    }
    
    /**
     * Tests for size of array equal to zero, no valid values stored in array
     * 
     * 
     * @return boolean - returns if empty
     * 
     */
    public boolean isEmpty()
    {
        return (arraySize <= 0);
    }
    
    /**
     * Tests for size of array equal to capacity, no more values can be added
     * 
     *  @return boolean - returns if full
     */
    public boolean isFull()
    {
        return (arraySize == arrayCapacity);
    }
    
    /**
     * Tests for value found in object array; returns true if value within array, false otherwise
     * 
     * 
     * @param testVal value to be tested
     * @return boolean - if value is in array
     */
    public boolean isInArray(int testVal)
    {
        int index = 0;
        while(index < arraySize)
        {
            if(localArray[index] == testVal)
            {
                return true;
            }
            index++;
        }
        return false;
    }
    
    /**
     * Loads a specified number of unique random numbers in object 
     * 
     * @param numRand
     * @param lowLimit
     * @param highLimit
     * @return boolean - true if method is successful
     */
    public boolean loadUniqueRandoms(int numRand, int lowLimit, int highLimit)
    {
        int index;
        int randomNumberHolder;
        if(numRand <= arrayCapacity)
        {
            for(index = 0; index < numRand; index++)
            {
                randomNumberHolder = getRandBetween(lowLimit, highLimit);
                localArray[index] = randomNumberHolder;
            }
            arraySize = numRand;
            return true;
        }
        return false;
    }
    
    /**
     * Removes item from array at specified index if index within array size bounds 
     * 
     * 
     * @param  removeIndex index of element value to be removed
     * @return int - removed value if successful
     * @return int - -999999 if not successful
     */
    public int removeItemAt(int removeIndex)
    {
        int removedValue;
        if(removeIndex < arraySize)
        {
            removedValue = localArray[removeIndex];
            shuffleIndexLeft(removeIndex);
            return removedValue;
        }
        return FAILED_ACCESS;
    }
    
    /**
     * Resets array capacity, copies current size and current size number of elements
     * 
     * @param newCapcity new capacity to be set; must be larger than current capacity
     * @return boolean - true if successful
     */
    public boolean resize(int newCapacity)
    {
        int[] tempArray = new int[newCapacity];
        if(newCapacity > arrayCapacity)
        {
            copyArray(localArray,tempArray,arraySize);
            arrayCapacity = newCapacity;
            localArray = new int[arrayCapacity];
            copyArray(tempArray,localArray,arraySize);
            return true;
        }
        return false;
    }
    
    /**
     * Merges values brought in between a low and high index segment of an array
     * 
     * 
     * @param lowIndex lowest index of array segment to be managed
     * @param middleIndex middle index of array segment to be managed
     * @param highIndex high index of array segment to be managed
     */
    private void runMerge(int lowIndex, int middleIndex, int highIndex)
    {
        
        int number1, number2, index, index2, index3;

        number1 = middleIndex - lowIndex + 1;
        number2 = highIndex - middleIndex;
        index = 0;
        index2 = 0;
        index3 = lowIndex;
 
        int LeftTempArray[] = new int [number1];
        int RightTempArray[] = new int [number2];
 
        for (index=0; index<number1; ++index)
        {
            LeftTempArray[index] = localArray[lowIndex + index];
        }
        
        for (index2=0; index2<number2; ++index2)
        {
            RightTempArray[index2] = localArray[middleIndex + 1+ index2];
        }
            
        while (index < number1 && index2 < number2)
        {
            if (LeftTempArray[index] <= RightTempArray[index2])
            {
                localArray[index3] = LeftTempArray[index];
                index++;
            }
            else
            {
                localArray[index3] = RightTempArray[index2];
                index2++;
            }
            index3++;
        }
 
        while (index < number1)
        {
            localArray[index3] = LeftTempArray[index];
            index++;
            index3++;
        }
 
        while (index2 < number2)
        {
            localArray[index3] = RightTempArray[index2];
            index2++;
            index3++;
        }
    }
    
    /**
     * Data sorted using merge sort algorithm 
     */
    public void runMergeSort()
    {
        runMergeSortHelper(0,arraySize-1);
    }
    
    /**
     * Merge sort helper, places low and high indices of array segment to be processed into recursive method, then sorts data using merge sort algorithm 
     * 
     * 
     * @param lowIndex lowest index of array segment to be managed; this varies as the segments are broken down recursively
     * @param highIndex highest index of array segment to be managed; this varies as the segments are broken down recursively
     * 
     */
    private void runMergeSortHelper(int lowIndex, int highIndex)
    {
        int middleIndex;
        if (lowIndex < highIndex)
        {
            middleIndex = (lowIndex + highIndex) / 2;
            runMergeSortHelper( lowIndex , middleIndex );
            runMergeSortHelper( middleIndex + 1 , highIndex );
            runMerge(lowIndex , middleIndex , highIndex );
        }
    }
    
    /**
     * partitions array using the first value as the partition; when this method is complete the partition value is in the correct location in the array
     * 
     * @param lowIndex low index of array segment to be partitioned
     * @param highIndex high index of array segment to be partitioned
     * @return int - index of partition pivot
     */
    private int runPartition(int lowIndex, int highIndex)
    {
        int pivot;
        
        pivot = localArray[lowIndex];

        while (lowIndex <= highIndex) 
        {
            while (localArray[lowIndex] < pivot) 
            {
                lowIndex++;
            }
            
            while (localArray[highIndex] > pivot) 
            {
                highIndex--;
            }

            if (lowIndex <= highIndex) 
            {
                swapValuesAtIndex(lowIndex, highIndex);
                lowIndex++;
                highIndex--;
            }
        }
        return lowIndex;
    }
    
    /**
     * Data sorted using quick sort algorithm
     */
    public void runQuickSort()
    {
        runQuickSortHelper(0,arraySize-1);
    }
    
    /**
     * helper method run with parameters that support recursive access
     * 
     * 
     * @param lowIndex low index of the segment of the array to be processed
     * @param highIndex high index of the segment of the array to be processed
     * 
     */
    private void runQuickSortHelper(int lowIndex, int highIndex)
    {
        int middleIndex; 
        
        middleIndex = runPartition(lowIndex, highIndex);

        if (lowIndex < middleIndex - 1) 
        {
            runQuickSortHelper( lowIndex, middleIndex - 1);
        }

        if (highIndex > middleIndex) 
        {
            runQuickSortHelper( middleIndex, highIndex);
        }

    }
    
    /**
     * Not Implemented
     */
    public void runShellSort()
    {
        
    }
   
    /**
     * swaps values in the object array by taking in the indices of the array locations 
     * 
     * 
     * @param oneIndex index of the of the values to be swapped
     * @param otherIndex index of the other value to be swapped
     * 
     */
    private void swapValuesAtIndex(int oneIndex, int otherIndex)
    {
        int elementOne = localArray[oneIndex];
        localArray[oneIndex] = localArray[otherIndex];
        localArray[otherIndex] = elementOne;    
    }
    
    private void shuffleIndexLeft(int indexToShuffleTo)
    {
        int index;
        for(index = indexToShuffleTo+1; index < arraySize; index++)
        {
            localArray[index-1] = localArray[index];
        }
        arraySize--;
    }
    
    private void shuffleIndexRight(int indexToShuffleTo)
    {
        int index;
        for(index = arraySize; index >= indexToShuffleTo; index--)
        {
            localArray[index+1] = localArray[index];
        }
        arraySize++;
    }
}
