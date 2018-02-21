/*
 * PhotoMagic.java
 * 
 * Implements a class to encrypt (and decrpyt, given correct code) an image
 * 
 * Compiles with "javac-introcs".
 * Executes with "java-introcs".
 */
import java.awt.Color;
    
public class PhotoMagic{
    private static Color colorSwitch(Color color, LFSR lfsr)
    {
        /**
         * Uses a given <code>LFSR</object> to modify the RGB values given as
         * a parameter.
         * 
         * @param  color  a <code>Color</code> object, to be randomized
         * @param  lfsr   a LFSR object, used to generate pseudo-random values
         * 
         * @return a Color object generated "encrypted" through the LFSR.
         */
        int rVal = color.getRed(), gVal = color.getGreen(),
            bVal = color.getBlue();
        
        rVal = (lfsr.generate(8) ^ rVal);
        gVal = (lfsr.generate(8) ^ gVal);
        bVal = (lfsr.generate(8) ^ bVal);
        
        Color newColor = new Color(rVal, gVal, bVal);
        
        return newColor;
    }
        
    public static Picture transform(Picture picture, LFSR lfsr)
    {
        /**
         * Makes use of <code>colorSwitch</code> to create an "encrypted"
         * version of a given Picture object using a <code>LFSR</code> object
         * to create (pseudo)random numbers.
         * 
         * @param picture  a Picture object, to be modified/encrypted
         * @param lfsr     a LFSR object, used to create random numbers
         */
        int column = picture.width(), row = picture.height();
        Picture afterPic = new Picture(column, row);
        
        for(int countCol = 0; countCol < column; countCol++)
        {
            for(int countRow = 0; countRow < row; countRow++)
            {
                Color holdColor = picture.get(countCol, countRow);
                holdColor = colorSwitch(holdColor, lfsr);
                afterPic.set(countCol, countRow, holdColor);
            }
        }
        
        return afterPic;
    }
    
    public static void main(String[] args)
    {
        Picture beforePic = new Picture(args[0]);
        LFSR lfsr = new LFSR(args[1], Integer.parseInt(args[2]) );
        Picture newPic = transform(beforePic, lfsr);
        newPic.show();
    }
}