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
 * Displays which sentence the user is on.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Sentence extends Actor
{
    //  REFERENCES
    private GameWorld gw = null;
    private Utility utility = null;
    private Counter counter = null;
    
    //  DATA
    private int count = 1;
    private final static int LIMIT = 10;
    
    //  IMAGE
    private GreenfootImage image = null;
    private Font font = new Font("Oswald", Font.PLAIN, 48);
    private FontRenderContext frc = null;
    private Rectangle2D bounds = null;
    private LineMetrics lm = null;
    
    /**
     * Constructor for objects of class Sentence.
     * 
     */
    public Sentence()
    {
        updateImage();
    }
    
    /**
     * Update image based on the 
     */
    public void updateImage()
    {
        String countText = count + "";
        
        getImage().setFont(font);
        frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        bounds = font.getStringBounds(countText, frc);
        lm = font.getLineMetrics(countText, frc);
        image = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
        
        image.setFont(font);
        image.setColor(Utility.blue);
        
        image.drawString(countText, 0, Math.round(image.getHeight() + 
            lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
        setImage(image);
    }
    
    /**
     * Increases count and updates image.
     */
    public void next()
    {
        if(getWorld() instanceof GameWorld)
        {
            // If limit not reached, increment count and update image
            if(count < LIMIT)
            {
                count++;
                updateImage();
            }
            // If limit reached, set new world
            else
            {
                gw = (GameWorld) getWorld();
                counter = gw.getCounter();
                Greenfoot.setWorld(new EndWorld(counter.getScore(), gw.getUtility()));
            }
        }
        else if(getWorld() instanceof ReviewWorld)
        {
            count++;
            updateImage();
            // If limit reached, add Button and remove Arrow
            if(count == LIMIT)
            {
                getWorld().addObject(new Button("play again", 24, Utility.pale, Utility.purple),
                    getWorld().getWidth() - getWorld().getWidth() / 4, getWorld().getHeight() - getWorld().getHeight() / 7);
                getWorld().removeObjects(getWorld().getObjects(Arrow.class));
            }
        }
    }
}