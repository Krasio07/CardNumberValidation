

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CardNumberValidation {

	
	public static ArrayList<int[]> cardNumbers = new ArrayList<int[]>();
	public static ArrayList<int[]> cardNumbersDoubled = new ArrayList<int[]>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String file = args[0];
		String file = "/Users/krasimirbonchev/Documents/javaworkspace/CNV/untitled folder/src/test";
		// TODO Auto-generated method stub
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				   // process the line.
//				System.out.println(line);
				cardNumbers.add(convertToArray(line));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i <cardNumbers.size(); i++) {
			cardNumbersDoubled.add(doubleEverySecond(cardNumbers.get(i)));
		}
		for(int i = 0; i <cardNumbersDoubled.size(); i++) {
			int result = 0;
			System.out.println("\nSUM: "+addAllDigits(cardNumbersDoubled.get(i)));
			if(addAllDigits(cardNumbersDoubled.get(i))%10 == 0) {
				result = 1;
			} else {
				result = 0;
			}
			System.out.println("Result: "+result);
		}
		
	}
	
	private static int addAllDigits(int [] array) {
		int sum = 0;
		int l = array.length-1;
		int count = 1;
		
		for(int k = l; k >= 0; k--) {
			if(count%2 == 1) {
				System.out.print(array[k] + " ");
				sum = sum+array[k];
			} else {
				System.out.print(checkForNine(array[k]) + " ");
				sum = sum + checkForNine(array[k]); 
			}
			count++;
		}
		
		return sum;
	}
	
	private static int checkForNine(int number) {
		if(number > 9) {
//			System.out.print(">9");
			String digits = ""+number;
			number = Integer.parseInt(digits.substring(0, 1)) + Integer.parseInt(""+digits.subSequence(1, 2));
		}
		
		return number;
	}
	
	private static int[] doubleEverySecond(int[] array) {
		int l = array.length-1;
		int[] doubledArray = new int[array.length];
		int count = 1;
		
		for(int j = l; j >= 0; j--) {
			if(count%2 == 1) { 
				doubledArray[j] = array[j];
			} else {
				doubledArray[j] = array[j]*2;
			}
			count++;
		}
		
		System.out.println("ORIGINAL: ");
		printIntArray(array);
		System.out.println("NEW: ");
		printIntArray(doubledArray);
		return doubledArray;
	}
	
	private static void printIntArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(" "+array[i]);
		}
		System.out.println("\n====");
	}
	
	private static int[] convertToArray(String inputLine) {
		int[] array = null;
		inputLine = inputLine.replaceAll("\\s","");
		array = new int[inputLine.length()];
		for(int i = 0; i < inputLine.length(); i++) {
			
//			System.out.println(Integer.parseInt(""+inputLine.charAt(i)));
			array[i] = Integer.parseInt(""+inputLine.charAt(i));
		}
		
		return array;
	}

}
