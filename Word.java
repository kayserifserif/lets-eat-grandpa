import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * Displays one word of each sentence.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Word extends Actor
{
    //  REFERENCES
    private GameWorld gw = null;
    private Counter counter = null;
    
    //  DATA
    private String word = "";
    private String answer = "";
    private boolean isAnswer;
    
    //  IMAGE
    private GreenfootImage image = null;
    private Font font = new Font("Lato", Font.PLAIN, 36);
    private FontRenderContext frc = null;
    private Rectangle2D bounds = null;
    private LineMetrics lm = null;
    private boolean mouseOver = false;
    
    //  SOUND
    // SOUND FILE FROM http://www.freesound.org/people/lukechalaudio/sounds/151568
    private GreenfootSound correct = new GreenfootSound("sounds/correct.wav");
    // SOUND FILE FROM http://www.freesound.org/people/Splashdust/sounds/67454
    private GreenfootSound incorrect = new GreenfootSound("sounds/incorrect.wav");
    
    /**
     * Constructor for objects of class Word.
     * 
     * @param   word    The word to be displayed.
     * @param   answer  The correct version of the word.
     * @param   isAnswer    If the word is the answer in the sentence.
     */
    public Word(String word, String answer, boolean isAnswer)
    {
        this.word = word;
        this.answer = answer;
        this.isAnswer = isAnswer;
        
        getImage().setFont(font);
        frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        
        // Set volume of both sounds to 80%
        correct.setVolume(80);
        incorrect.setVolume(80);
    }
    
    /**
     * When the actor has been inserted into the world, create image.
     * 
     * @param   world   The world the object was added to.
     */
    protected void addedToWorld(World world)
    {
        bounds = font.getStringBounds(word, frc);
        image = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
        image.setFont(font);
        lm = font.getLineMetrics(word, frc);
        
        if(getWorld() instanceof GameWorld)
        {
            image.setColor(Utility.pale);
        }
        else if(getWorld() instanceof ReviewWorld)
        {
            image.setColor(Utility.blue);
        }
        image.drawString(word, 0, Math.round(image.getHeight() + 
            lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
        setImage(image);
        
        if(getWorld() instanceof ReviewWorld && isAnswer)
        {
            bounds = font.getStringBounds(word, frc);
            image = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
            lm = font.getLineMetrics(word, frc);
            image.setFont(font);
            image.setColor(new Color(0, 0, 0, 10));
            image.fill();
            image.setColor(Utility.blue);
            image.drawString(word, 0, Math.round(image.getHeight() + 
                lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
        }
    }
    
    /**
     * Act - do whatever the Word wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // If in GameWorld, highlight on hover and listen for mouse clicks
        if(getWorld() instanceof GameWorld)
        {
            // FROM BUSCH2207 AT http://www.greenfoot.org/topics/250
            // If mouse moves, set mouseOver to if it moved on this
            if(Greenfoot.mouseMoved(null))
            {
                mouseOver = Greenfoot.mouseMoved(this);
            }
            // If hovered over, highlight
            if(mouseOver)
            {
                setImage(image);
                image.clear();
                image.setColor(new Color(0, 0, 0, 50));
                image.fill();
                image.setColor(Utility.pale);
                image.drawString(word, 0, Math.round(image.getHeight() + 
                    lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
            }
            // If not hovered over, remove highlight
            else
            {
                image.clear();
                image.setColor(Utility.pale);
                image.drawString(word, 0, Math.round(image.getHeight() + 
                    lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
            }
            
            // If clicked, increase score and play sound
            if(Greenfoot.mouseClicked(this))
            {
                gw = (GameWorld) getWorld();
                counter = gw.getCounter();
                if(isAnswer)
                {
                    counter.increase();
                    correct.play();
                }
                else
                {
                    counter.decrease();
                    incorrect.play();
                }
            }
        }
        
        if(getWorld() instanceof ReviewWorld && isAnswer)
        {
            if(Greenfoot.mouseMoved(null))
            {
                mouseOver = Greenfoot.mouseMoved(this);
            }
            // If hovered over, repaint with original word
            if(mouseOver)
            {
                image.clear();
                image.setColor(new Color(0, 0, 0, 10));
                image.fill();
                image.setColor(Utility.blue);
                lm = font.getLineMetrics(answer, frc);
                image.drawString(answer, 0, Math.round(image.getHeight() + 
                    lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
                setImage(image);
            }
            // If not hovered over, repaint with correct answer
            else
            {
                image.clear();
                image.setColor(new Color(0, 0, 0, 10));
                image.fill();
                image.setColor(Utility.blue);
                lm = font.getLineMetrics(word, frc);
                image.drawString(word, 0, Math.round(image.getHeight() + 
                    lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
                setImage(image);
            }
        }
    }
}