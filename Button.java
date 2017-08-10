import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * When clicked, changes world.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Button extends Actor
{
    //  REFERENCES
    private Utility utility = null;
    
    //  IMAGE
    private GreenfootImage image = null;
    
    private String text = "";
    
    private Color textColor;
    private Color backgroundColor;
    
    private FontRenderContext frc;
    private Rectangle2D bounds;
    
    private boolean mouseOver;
    private boolean pressed;
    
    /**
     * Constructor for objects of class Button.
     * 
     * @param   text    The text to be displayed.
     * @param   fontSize    The size of the font.
     * @param   backgroundColor The background color of the button.
     */
    public Button(String text, int fontSize, Color textColor, Color backgroundColor)
    {
        this.text = text;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        
        // Create Font
        Font font = new Font("Lato", Font.PLAIN, fontSize);
        // Set the font of the current image
        getImage().setFont(font);
        
        // TEXT CENTERING CODE FROM ZAMOHT AT http://www.greenfoot.org/scenarios/9027
        // Get the FontRenderContext of the font
        frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        // Create a Rectangle2D object from the StringBounds of the word, using the FontRenderContext
        bounds = font.getStringBounds(text, frc);
        image = new GreenfootImage(200, 80);
        
        image.setColor(backgroundColor);
        image.fill();
        
        image.setColor(textColor);
        image.setFont(font);
        image.drawString(text, image.getWidth()/2 - (int)bounds.getWidth()/2,
            image.getHeight()/2 + (int)bounds.getHeight()/4);
        setImage(image);
    }
    
     /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // FROM BUSCH2207 AT http://www.greenfoot.org/topics/250
        // If mouse moves, set mouseOver to if it moved on this
        if(Greenfoot.mouseMoved(null))
        {
            mouseOver = Greenfoot.mouseMoved(this);
        }
        if(Greenfoot.mousePressed(this))
        {
            pressed = Greenfoot.mousePressed(this);
        }
        
        // If hovered over but not pressed, fill with a lighter color
        if(mouseOver && !pressed)
        {
            image.clear();
            image.setColor(backgroundColor.brighter());
            image.fill();
            image.setColor(textColor);
            image.drawString(text, image.getWidth()/2 - (int)bounds.getWidth()/2,
                image.getHeight()/2 + (int)bounds.getHeight()/4);
            setImage(image);
        }
        // If pressed, fill with a darker color
        else if(pressed)
        {
            image.clear();
            image.setColor(backgroundColor.darker());
            image.fill();
            image.setColor(textColor);
            image.drawString(text, image.getWidth()/2 - (int)bounds.getWidth()/2,
                image.getHeight()/2 + (int)bounds.getHeight()/4);
            setImage(image);
            pressed = Greenfoot.mouseClicked(this);
        }
        // If not hovered over and not pressed, fill with normal color
        else if(!mouseOver && !pressed)
        {
            image.clear();
            image.setColor(backgroundColor);
            image.fill();
            image.setColor(textColor);
            image.drawString(text, image.getWidth()/2 - (int)bounds.getWidth()/2,
                image.getHeight()/2 + (int)bounds.getHeight()/4);
        }
        
        // If clicked, change to new world
        if(Greenfoot.mouseClicked(this))
        {
            if(text == "start")
            {
                Greenfoot.setWorld(new HelpWorld());
            }
            if(text == "play")
            {
                Greenfoot.setWorld(new GameWorld());
            }
            if(text == "review")
            {
                EndWorld ew = (EndWorld) getWorld();
                utility = ew.getUtility();
                Greenfoot.setWorld(new ReviewWorld(utility));
            }
            if(text == "play again")
            {
                Greenfoot.setWorld(new TitleWorld());
            }
        }
    }
}