import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.FontFormatException;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Creates fonts and colours and controls the content.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Utility extends Actor
{
    //  REFERENCES
    private Counter counter = null;
    private Sentence sentence = null;
    
    //  DATA
    private boolean inGame;
    // Create an ArrayList to hold all sentences. The ArrayList contains Lists, which are each one sentence. The Lists contain
    // Strings, which are each one part of the sentence.
    private ArrayList<List<String>> items = new ArrayList();
    // Create an ArrayList to record which sentences have already been used, using integers that represent the line number in
    // the file.
    private static ArrayList<Integer> used = new ArrayList();
    private int position;
    private int positionUsed;
    
    // Create ArrayList containing all answers
    private ArrayList<String> answers = new ArrayList();
    // Create List containing the answer separated into lines based on width of the image
    private List<String> answerStrings = new ArrayList();
    
    //  IMAGE
    private Font mainFont = null;
    private Font secondaryFont = null;
    public static Color blue = new Color(0xbbccc3);
    public static Color purple = new Color(0x553445);
    public static Color pale = new Color(0xfaf1e0);
    
    private GreenfootImage image = null;
    private FontRenderContext frc = null;
    private Rectangle2D bounds = null;
    
    private int leftBoundary;
    private int sentenceCounterWidth;
    private int ypos;
    private int xpos;
    private int lineLength;
    private int limit;
    
    /**
     * Constructor for objects of class Utility.
     */
    public Utility()
    {
        readFile();
        
        // FONT CODE FROM ENRICO PALLAZZO ON SO: http://stackoverflow.com/questions/8364787/how-do-you-import-a-font
        // FILE CODE FROM DAVMAC: http://www.greenfoot.org/scenarios/5177
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            String[] files = {"Lato-Reg", "Lato-Bol", "Oswald-Regular", "Oswald-Bold"};
            InputStream is;
            for(int i = 0; i < files.length; i++)
            {
                is = getClass().getClassLoader().getResourceAsStream("images/" + files[i] + ".ttf");
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
        
        mainFont = new Font("Lato", Font.PLAIN, 36);
        secondaryFont = new Font("Oswald", Font.PLAIN, 48);
        
        // Set image to null
        setImage(image);
    }
    
    /**
     * When the actor has been inserted into the world, add Sentence object.
     * 
     * @param   world   The world the object was added to.
     */
    protected void addedToWorld(World world)
    {
        inGame = getWorld() instanceof GameWorld;
        
        if(inGame)
        {
            // Reset ArrayList of used numbers
            used.clear();
        }
        if(!inGame)
        {
            // Begin at beginning of ArrayList of used numbers
            positionUsed = -1;
        }

        // Create an image to position Sentence object
        image = new GreenfootImage(1, 1);
        // Set font
        image.setFont(secondaryFont);
        // TEXT CENTERING CODE FROM ZAMOHT AT http://www.greenfoot.org/scenarios/9027
        // Get the FontRenderContext of the font
        frc = image.getAwtImage().createGraphics().getFontRenderContext();
        // Create a Rectangle2D object from the StringBounds of the word, using the FontRenderContext
        bounds = secondaryFont.getStringBounds("1", frc);
        sentenceCounterWidth = (int)bounds.getWidth();
        // Set ypos
        ypos = getWorld().getHeight() / 4;
        if(inGame)
        {
            leftBoundary = 170;
        }
        else
        {
            leftBoundary = 140;
        }
        // Add Sentence object so the edge of the word is at leftBoundary and its y-position is at ypos
        sentence = new Sentence();
        getWorld().addObject(sentence, leftBoundary, ypos);
        
        populate();
    }
    
    /**
     * Read file and input the words into an ArrayList, and the answers into a List.
     */
    public void readFile()
    {
        try
        {
            String input = "";
            InputStream is = getClass().getClassLoader().getResourceAsStream("files/content.txt");
            BufferedReader file = new BufferedReader(new InputStreamReader(is));
            
            while((input = file.readLine()) != null)
            {
                items.add(Arrays.asList(input.split("/")));
            }
            
            for(int i = 0; i < items.size(); i++)
            {
                answers.add(items.get(i).get(items.get(i).size() - 1));
            }
        } 
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Create Word and Answer objects and typeset the text within a set boundary.
     */
    public void populate()
    {
        if(inGame)
        {
            // If the sentence on the random line number has not been used before, add the line number to the ArrayList of
            // used line numbers. Otherwise, pick again
            do {
                position = Greenfoot.getRandomNumber(items.size());
            } while(used.contains(position));
            used.add(position);
        }
        else
        {
            positionUsed++;
            // Go through used numbers instead of picking new ones
            position = used.get(positionUsed);
        }
        
        // Remove all Word objects in the world
        getWorld().removeObjects(getWorld().getObjects(Word.class));
        getWorld().removeObjects(getWorld().getObjects(Answer.class));
        
        // Set font
        image.setFont(mainFont);
        // TEXT CENTERING CODE FROM ZAMOHT AT http://www.greenfoot.org/scenarios/9027
        // Get the FontRenderContext of the font
        frc = image.getAwtImage().createGraphics().getFontRenderContext();
        
        // Set lineLength to the width of the text plus 15-pixel padding
        lineLength = sentenceCounterWidth + 15;
        if(inGame)
        {
            limit = 370;
        }
        else
        {
            limit = 570;
        }
        // Set xpos to leftBoundary + lineLength
        xpos = leftBoundary + lineLength;
        ypos = getWorld().getHeight() / 4;
        int previousWordWidth = 0;
        String word = "";
        String answer = "";
        boolean isAnswer;
        
        // Add and position new Word objects for each word in the specified sentence stored in the ArrayList of Lists
        for(int i = 1; i < items.get(position).size() - 3; i++)
        {
            // Set isAnswer to whether or not the position of the word is equal to the number (answer) listed at the
            // beginning of the sentence List
            isAnswer = i == Integer.parseInt(items.get(position).get(0));
            
            if(!inGame && isAnswer)
            {
                word = items.get(position).get(items.get(position).size() - 2);
                answer = items.get(position).get(i);
            }
            else
            {
                // Set word to the String stored in item number i in the sentence
                word = items.get(position).get(i);
            }
            
            // Create a Rectangle2D object from the StringBounds of the word, using the FontRenderContext
            bounds = mainFont.getStringBounds(word, frc);
            
            // Add the width of the word to lineLength; if the result is less than or equal to 370, increase xpos; else,
            // reset lineLength and move to a new line
            if((lineLength += bounds.getWidth()) <= limit)
            {
                // Set xpos so the new word is next to the previous word
                xpos += (previousWordWidth/2 + (int)bounds.getWidth()/2);
            }
            else
            {
                // Set ypos to a new line at a 1.125 line height
                ypos += (bounds.getHeight() + bounds.getHeight()/8);
                // Set xpos so the edge of the word is at leftBoundary
                xpos = leftBoundary + (int)bounds.getWidth()/2;
                // Set lineLength to the width of the word
                lineLength = (int)bounds.getWidth();
            }
            // Record the width of this word for the positioning of the next word
            previousWordWidth = (int)bounds.getWidth();
            
            if(!isAnswer)
            {
                // Add a Word object using the word String and the isAnswer boolean, at xpos x-position and ypos y-position
                getWorld().addObject(new Word(word, null, isAnswer), xpos, ypos);
            }
            else
            {
                getWorld().addObject(new Word(word, answer, isAnswer),
                    xpos, ypos);
            }
        }
        
        if(!inGame)
        {
            getAnswerStrings(position);
            getWorld().addObject(new Answer(answerStrings),
                getWorld().getWidth() / 2 + 15,
                ypos + (int)Math.round(mainFont.getSize() * 0.75) + ((answerStrings.size()) * (int)Math.round(24 * 1.3)) / 2);
        }
    }
    
    /**
     * Get the List of answer Strings.
     * 
     * @param   position    The position in the items ArrayList.
     */
    public void getAnswerStrings(int position)
    {
        int width = 0;
        List words = null;
        String answer = "";
        
        // Create a List that contains the words in the answer, split by spaces
        words = Arrays.asList(answers.get(position).split(" "));
        
        // Measure each word and insert "\n" at each line break
        for(int i = 0; i < words.size(); i++)
        {
            bounds = mainFont.deriveFont(Font.PLAIN, 24).getStringBounds(words.get(i) + "", frc);
            if((width += bounds.getWidth()) > limit)
            {
                answer += "\n";
                width = (int)bounds.getWidth();
            }
            answer += words.get(i) + " ";
        }
        
        // Create a List of Strings split by line breaks
        answerStrings = Arrays.asList(answer.split("\n"));
    }
    
    /**
     * Get Sentence object.
     * 
     * @return  Sentence object.
     */
    public Sentence getSentence()
    {
        return sentence;
    }
}