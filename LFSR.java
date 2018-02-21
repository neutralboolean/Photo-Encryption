/*
 * LFSR.java
 * 
 * Implements a linear feedback shift register to create pseudo-random numbers.
 * 
 * Compiles with "javac-introcs".
 * Executes with "java-introcs".
 */

import java.awt.Color;
import java.util.Vector;

public class LFSR {
    private static int seedLen;     //holds the length of given binary string
    private static int tap;         //holds the position of number to be tapped
    public static String seed;      //holds the binary string
    
    public LFSR(String inputSeed, int inputTap)
    {
        seed = inputSeed;
        tap = inputTap;
        seedLen = inputSeed.length();
    }
    
    public static int step ()
    {
        /**
         * Simulates one step of the linear feedback shift register.
         * 
         * @return   an int, the pseudo-random bit generated
         */
        
        int holdTap = (int)seed.charAt( (seedLen - tap - 1) ),
            holdLeft = (int)seed.charAt(0);
        
        String holdStr = seed.substring(1);
        seed = holdStr + ((int)holdTap ^ (int)holdLeft) ;
        
        return ( (int)holdTap ^ (int)holdLeft) ;
    }
    
    public static int generate(int k)
    {
        /**
         * Simulates k steps and returns the k-bit integer
         * <p>
         * Makes use of <code>step()</code>.
         * 
         * @param    an int k, the number of steps to run through the LFSR
         * @return   an integer, the value of k-bit integer after simulation
         */
        int holdValue = 0, valueIncr = 1;
        
        for(int count = 0; count < k; count++)
        {
            int newBit = step();
            holdValue = ( (2 * holdValue) + newBit);
        }

        return holdValue;
    }
    
    public String toString()
    {
        return seed;
    }
    
    public static void main(String[] args)
    {
        LFSR lfsr1 = new LFSR("01101000010", 8);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }
        
        StdOut.println();
        
        lfsr1 = new LFSR("0110", 1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }
        
        StdOut.println();
        
        lfsr1 = new LFSR("01101000010", 8);
        for (int i = 0; i < 10; i++)
        {
            int r = lfsr1.generate(5);
            StdOut.println(lfsr1 + " " + r);
        }
        
        StdOut.println();
        
        lfsr1 = new LFSR("01101000010100010000", 16);
        for (int i = 0; i < 10; i++) {
            int ar = lfsr1.generate(8);
            StdOut.println(lfsr1 + " " + ar);
        }
    }
}