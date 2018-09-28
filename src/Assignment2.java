
/**
 * Written by: Afnan Sultan (N01154597)
 * Date Last Modified: Monday 5/23/18 1:06AM 
 * This is an image processing program that can generate a random image,
 * convert an image to grayscale, or to sepia.
 */

import java.awt.Color;
import java.util.Scanner;
import java.io.File; //Used to write and read image file
import java.awt.image.BufferedImage; //Used to store an image in RAM
import java.io.IOException; //This is used to handle IO errors
import javax.imageio.ImageIO; //Perform image read write operation
//Has static methods to read and write an image

public class Assignment2 {
    
    public static void greetingMessage() {
        System.out.println("\nEnter an option");
        System.out.println("Option 0: Exit");
        System.out.println("Option 1: Generate random image");
        System.out.println("Option 2: Convert image to grayscale");
        System.out.println("Option 3: Apply Sepia Filter to image");
    }//greetingMessage
    
    public static void goodbyeMessage() {
        System.out.println("Goodbye!");
    }//goodbyeMessage
    
    public static void RandomImage() {
        // Image file dimensions
        int width = 640, height = 320;
 
        // Create buffered image object
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

 
        // create random values pixel by pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                /*int a = (int)(Math.random()*256); //generating
                int r = (int)(Math.random()*256); //values
                int g = (int)(Math.random()*256); //less than
                int b = (int)(Math.random()*256); //256
    
                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel*/
            		Color color = new Color( (int) (Math.random() * 0xff) , (int)(Math.random() * 0xff), (int) (Math.random() * 0xff));
                img.setRGB(x, y, color.getRGB() );
            }
        }
 
        // write image
        try
        {
            // file object
            File f = new File("random.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
    }//RandomImage
       
    public static void GrayScale() throws IOException {
    		
    		File input = new File("image.jpg");
    		BufferedImage image = ImageIO.read(input);   	
    	
    		//read image
    		try{
    		      input = new File("image.jpg");
    		      image = ImageIO.read(input);
    		    }catch(IOException e){
    		      System.out.println(e);
    		    }
    		
            //get image width and height		
			int width = image.getWidth();
			int height = image.getHeight();   	

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
                                           
                int p = image.getRGB(x,y);
                                        
                int alpha = (p>>24)&0xff;
                int red = (p>>24)&0xff;
                int green = (p>>8)&0xff;
                int blue = p&0xff;
                                        
                //grayscale is the average of rgb/3
                int grayscale =(red+green+blue)/3;
                                                
                //Convert to grayscale 
                                        
                p = (alpha<<24) | (grayscale<<16) | (grayscale<<8) | grayscale;
                                        
                image.setRGB(x,y,p);
                                      
                }//width
             }//height        
                        
             //write image
			try{
			      input = new File("grayscale.jpg");
			      ImageIO.write(image, "jpg", input);
			    }//try
			
				catch(IOException e){
			      System.out.println(e.getMessage());
			    }//catch
		
    }//grayscale
    
    public static void SepiaFilter() throws IOException {
    	
    	File input = new File("image.jpg");
    	BufferedImage image = ImageIO.read(input);
    	
    	try
        {
            input = new File("image.jpg");
            image = ImageIO.read(input);
        }
        catch(IOException e)
       {
            System.out.println(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        //convert to Sepia
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int R = (p>>16)&0xff;
                int G = (p>>8)&0xff;
                int B = p&0xff;
                int newRed = (int)(0.393*R + 0.769*G + 0.189*B);
                int newGreen = (int)(0.349*R + 0.686*G + 0.168*B);
                int newBlue = (int)(0.272*R + 0.534*G + 0.131*B);
                if (newRed > 255)
                    R = 255;
                else
                    R = newRed;
                if (newGreen > 255)
                    G = 255;
                else
                    G = newGreen;
                if (newBlue > 255)
                    B = 255;
               else
                   B = newBlue;
               p = (a<<24) | (R<<16) | (G<<8) | B;
                image.setRGB(x, y, p);
            }
        }
        try
        {
            input = new File("sepia.jpg");
            ImageIO.write(image, "jpg", input);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }//SepiaFilter
    
    public static void LoadImage(File input, BufferedImage image, int width, int height) throws IOException{
    	
    	input = new File("image.jpg");
		image = ImageIO.read(input);   	
	
		//read image
		try{
		      input = new File("image.jpg");
		      image = ImageIO.read(input);
		    }catch(IOException e){
		      System.out.println(e);
		    }
		
        //get image width and height		
		width = image.getWidth();
		height = image.getHeight(); 
				
    }//LoadImage
    
    //throws IOException is for IO operations 
    public static void main(String[] args)throws IOException {
        
        int selection;
        do {
             
        greetingMessage();
        Scanner scanner = new Scanner(System.in);
        selection = scanner.nextInt();
        
        switch(selection){
            case 0: goodbyeMessage();
            scanner.close();
            break;
            case 1: RandomImage();
            System.out.println("\nSuccesfully generated random image!");
            break;
            case 2: GrayScale();
            System.out.println("\nSuccesfully converted image to grayscale!");
            break;
            case 3: SepiaFilter();
            System.out.println("\nSuccesfully converted image to Sepia!");
            break;
            default: 
            System.out.println("\nIncorrect option.");
            break;
                    
                     }//switch
               } while (selection!=0); //do-while
        }//main
}//Assignment2
