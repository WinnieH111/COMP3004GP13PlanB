package core;

import java.util.Arrays;
import java.util.regex.Pattern;

import javafx.scene.image.Image;

public class Card implements Comparable<Card> {
	private String color = null;
	private int rank = 0;
	
	//O = Orange; R = Red; B = Black/Blue; G = Green
    public static String[] colors = {"O", "R", "B", "G"};
    public static int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	


	Card(String color, int rank){
		this.color = color;
		this.rank = rank;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getRank() {
		return rank;
	}

	public String getRankString() {
		return Integer.toString(rank);
	}
	
	public String getCardString() {
		return getColor()+getRankString();
	}
	
	public Boolean isValidCard() {
		Pattern pattern = Pattern.compile("([OBRG])([1-13])$");
        return pattern.matcher(getCardString()).matches();
  
	}
	
    public int compareTo(Card card2) {
    	 if (getColorValue(getColor()) < getColorValue(card2.getColor()))
             return -1;
         else if (getColorValue(getColor()) > getColorValue(card2.getColor()))
             return 1;
         else {
             if (this.rank< card2.rank)
                 return -1;
             else if (this.rank > card2.rank)
                 return 1;
             else
                 return 0;
         }
    }
    
    public int getColorValue(String color) {
    	return Arrays.asList(colors).indexOf(color);

    }
	
	//The last three function are here for JavaFX user interface, 
	//to get the image of card 
    public Image getCardFace() {
        return new Image(cardFilePath());
    } 
    
    //The file path can be changed later. 
    public String cardFilePath () {
        return "file:src/main/resources/core/cards/" + getColor().toLowerCase() + getRankString().toLowerCase()  + ".png";
    }
    
    public Image getCardBack() {
        return new Image("file:src/main/resources/core/cards/back.png");
    }

    
}
