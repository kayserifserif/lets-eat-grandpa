import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;

import java.util.List;

/**
 * Displays the answer to the sentence.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Answer extends Actor
{
    private Font font = new Font("Lato", Font.PLAIN, 20);
    
    /**
     * Constructor for objects of class Answer.
     * 
     * @param   answerStrings   The answer in a list, separated into lines based on the width of the iamge.
     */
    public Answer(List answerStrings)
    {
        // Create new image 550 pixels wide and height based on font size at a 1.4 line height
        GreenfootImage image = new GreenfootImage(550, answerStrings.size() * (int)Math.round(font.getSize() * 1.4));
        
        image.setFont(font);
        image.setColor(Utility.purple);
        
        // Draw each String at a line height of 1.2
        for(int i = 0; i < answerStrings.size(); i++)
        {
            image.drawString(answerStrings.get(i) + "",
                0, (int)Math.round((i + 1) * font.getSize() * 1.2));
        }
        
        setImage(image);
    }
}