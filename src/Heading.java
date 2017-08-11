import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * The heading at the top of every screen.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heading extends Actor
{
    /**
     * Constructor for objects of class Heading.
     * 
     * @param   text    The text to be displayed.
     * @param   fontSize    The size of the font.
     * @param   textColor   The color of the text.
     * 
     */
    public Heading(String text, int fontSize, Color textColor)
    {
        // Create Font
        Font font = new Font("Lato", Font.BOLD, fontSize);
        // Set the font of the current image
        getImage().setFont(font);
        
        // TEXT CENTERING CODE FROM ZAMOHT: http://www.greenfoot.org/scenarios/9027
        // Get the FontRenderContext of the font
        FontRenderContext frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        // Create a Rectangle2D object from the StringBounds of the word, using the FontRenderContext
        Rectangle2D bounds = font.getStringBounds(text, frc);
        // Create GreenfootImage with the width and height of the Rectangle2D object with 2-pixel padding
        GreenfootImage image = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
        
        image.setColor(textColor);
        image.setFont(font);
        //LineMetrics lm = font.getLineMetrics(text, frc);
        image.drawString(text, 0, Math.round(image.getHeight()/* + lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1*/));
        setImage(image);
    }
}