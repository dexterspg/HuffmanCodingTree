import java.io.BufferedReader;
import java.io.FileReader;


public class LetterFrequencyGenerator {

	private String fileName; //text file input
	private int[] letterCounter;  //counting frequency helper
	private int[] uniqueLetterCounter; //store frequency
	private char[] letter; //array of repeated letters
	private char[] uniqueLetter; //array of non-repeated letters
	private int[] occurence;
	private String line; //line of text file
	private MergeSort ms;


	public LetterFrequencyGenerator(String fName)//constructor
	{
		fileName=fName; 
		Readfile(fileName);		
	}


	public void Readfile(String fName)//method to read the text file
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fName)))
		{
			while ((line = br.readLine()) != null) //read line by line
				letter=line.toCharArray(); //convert to char array
			charFreq(); //check letters' frequencies
			findUniqueLetters();//get non-repeated char
			
		}//end try

		catch(Exception e)
		{
			System.out.println("File not found!");
		}//end catch

	}//end method ReadFile


	private void findUniqueLetters() //get non-repeated char
	{		
		uniqueLetter=new char[getNumberOfUniqueLetters()];
		uniqueLetterCounter=new int[getNumberOfUniqueLetters()];

		int ptr1=0; //upper checking boundary

		for(int pos=0;pos<uniqueLetter.length;pos++) //set pos as index of all non-repeated char
		{
			uniqueLetter[pos]=letter[ptr1];  
			uniqueLetterCounter[pos]=letterCounter[ptr1]; 

			for(int ptr2=0;ptr2<ptr1;ptr2++) //ptr2 is lower checking boundary
			{
				if(uniqueLetter[pos]==letter[ptr2]) //check char repetition
				{		 		 
					pos--; //stay in same position when char repetition
					break;
				}//end if

			}//end inner for

			ptr1++;

		}//end outer for

	}//end method setUniqueLetters


	private void charFreq()  //count the number of occurences of each unique char characters
	{
		letterCounter=new int[letter.length];

		for(int i=0;i<letter.length;i++)
		{
			for(int j=i;j<letter.length;j++)
			{ 
				if(letter[j]==letter[i]) 
					++letterCounter[i];              
			}//end inner for 

		}//end outer for

	}//end method charFreq


	public int getTotalNumberOfLetters()
	{
		return letter.length;

	}//end method getTotalNumberOfLetters


	public int getNumberOfUniqueLetters() //get sum of all unique char in text file
	{
		int sum=0;

		for(int i=0;i<letter.length;i++)
		{     
			if(letterCounter[i]==1)
				sum++;
		}//end for

		return sum;
	}//end method uniqueChar


	public char[] getUniqueLetters()
	{
		return uniqueLetter;
	}//end method getUniqueLetters


	public int[] getUniqueLettersFreq()
	{
		return uniqueLetterCounter;
	}//end method getUniqueLetters
	
	private void setOccurences()
	{
		occurence=new int[getNumberOfUniqueLetters()];
		occurence=ms.getArrayOriginalIndex();		
	}//end method setOccurences
	
	
	public int[] getOccurences()
	{
		return occurence;
			
	}//end method setOccurences

	public void sort()
	{
			
		ms=new MergeSort(getUniqueLettersFreq()); //sort the frequencies
		char[] uniqueLetterCopy=new char[getUniqueLetters().length];
		
		for(int i=0;i<getUniqueLettersFreq().length;i++)
		uniqueLetterCopy[i]=getUniqueLetters()[ms.getArrayOriginalIndex()[i]];

		for(int i=0;i<getUniqueLettersFreq().length;i++)
		{
			uniqueLetter[i]=uniqueLetterCopy[i];
			uniqueLetterCounter[i]=ms.getSortArray()[i];
		}
		
		setOccurences();
			

	}

	public void freqTable() //print: sorted frequency table
	{

		System.out.printf("%20s%38s\n","Characters","Frequency");
		for(int i=0;i<getUniqueLetters().length;i++)
			System.out.printf("%15c     -----------------------------%5d\n" ,getUniqueLetters()[i],getUniqueLettersFreq()[i]);
			//System.out.printf("%15c     -----------------------------%5d\n" ,getUniqueLetters()[ms.getArrayOriginalIndex()[i]],ms.getSortArray()[i]);
	}//end method freqTable


	public void printLine()  //print: contents of text file
	{
		for(char c: letter)
			System.out.print(c);

		System.out.println();
	}//end method printLine


}//end class LetterFrequencyGenerator
























