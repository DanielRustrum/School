/**
 * 
 */
package p1_Package;

/**
 * @author daniel_rustrum
 *
 */
public class DigitClass
{
    
    /**
     * Holds the base of a given value
     */
    public int base;
    
    /**
     * An Array for holding the digits in a number
     */
    public int[] digitArray;
    
    /**
     * The maximum digits held for this value
     */
    public int maxDigits;
    
    /**
     * current number of digits held for this value
     */
    public int numDigits;
    
    /**
     * A flag that is set when an operation goes over the maxDigits allowed
     */
    public boolean overflow;
	
    /**
     * Copy Constructor For DigitClass
     * <p>
     * @param copied DigitClass object to be copied
     */
    public DigitClass(DigitClass copied)
    {
        base = copied.base;
        maxDigits = copied.maxDigits;
        numDigits = copied.numDigits;
        overflow = copied.overflow;
        digitArray = copyArray(copied.digitArray);
    }
	
    /**
     * Default Constructor for DigitClass
     * <p>
     * @param baseSet sets base
     * @param maxDigit sets maximum number of digit values
     * @param decValSet accepts a decimal value to be stored as the DigitClass Value
     */
    public DigitClass(int baseSet, int maxDigit, int decValSet)
    {
        base = baseSet;
        maxDigits = maxDigit;
        initalizeDigits();
        decToBase(decValSet);
    }
	
    /**
     * Copies an array for the copy constructor
     * <p>
     * @param copiedArray takes in the array to be copied
     * @return arrayToBeCopied - returns a copied array for the copy constructor
     */
    private int[] copyArray(int[] copiedArray)
    {
        int index;
        int[] arrayToBeCopiedToo = {0};
        for(index = 0;index < numDigits;index++)
        {
            arrayToBeCopiedToo[index] = copiedArray[index];
        }
        return arrayToBeCopiedToo;
    }

    /**
     * Convert Positive Integer into base
     * <p>
     * @param decValue decimal value to be converted
     * @return int[] - array holding converted value
     * @return null - returns if base is not between 1 and 10; it sets the overflow tag 
     */
    private int[] decToBase(int decValue)
    {
        int index = 0;
        if(base < 1 || base > 10)
        {
            overflow = true;
        }
        while(decValue >= 0)
        {
            digitArray[index] = decValue%base;
            decValue /= base;
            index++;
        }
        numDigits = index;
        return digitArray;
    }
      
    /**
     * Access the as a decimal value
     * <p>
     * @return int - decimal value
     */
    public int getValueAsDecimal()
    {
        // Variables For Overflow Check
        final int overflowedResult = 0;

        // Variables For Conversion
        int index = 0;
        int maxNumberAtPosition = 0;
        int productOfMaxAndDigitAtPosition = 0;
        int convertedValue = 0;

        // Check Overflow And Return Result
        if(overflow)
        {
            return overflowedResult;
        }
		
        // Convert Base To Decimal
        while(index < numDigits && !isZero())
        {
            maxNumberAtPosition = intToPower(base, index);
            productOfMaxAndDigitAtPosition = maxNumberAtPosition * digitArray[index];
            convertedValue += productOfMaxAndDigitAtPosition;
            index++;
        }

        //	Return Result
        return convertedValue;
    }

    /**
     * Accesses value directly
     * <p>
     * @return String - value in base form
     */
    public String getValueAsBase()
    {
        // Check For Overflow and Return Result
        final String overflowedResult = "Value has Overflowed";
        
        // Format Base As String	
        int index;
        String valueAsBase = "";
        String tempIntHolder = "";
        
        //Check For Overflow
        if(overflow)
        {
            return overflowedResult;
        }
       
        
        for(index = maxDigits; index>0; index--)
        {
            tempIntHolder = Integer.toString(digitArray[index]);
            valueAsBase += tempIntHolder;
        }
        
        // Return Formatted Result
        return valueAsBase;
    }
    
    /**
     * creates a new array full of zeros
     * <p>
     * @return int[] - returns array that is created and initalized
     */
    public int[] initalizeDigits()
    {
        int index;
        digitArray = new int[maxDigits];
        for(index = 0; index<maxDigits;index++)
        {
            digitArray[index] = 0;
        }
        return digitArray;
    }
	
    /**
     * calculates integer base value to exponent power
     * <p>
     * @param base value to multiple by itself
     * @param exponent number of times to be multiply base
     * @return int - result of calculation
     */
    int intToPower(int base, int exponent)
    {
        if(exponent > 0)
        {
            return base*intToPower(base,exponent-1);
        }
        return 1;
    }
	
    /**
     * checks base value is zero
     * <p>
     * @return boolean - If number of digits is 1 or less and LSD is zero
     */
    public boolean isZero()
    {
        return(numDigits <= 1 && digitArray[0] == 0);
    }
}