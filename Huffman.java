
import java.util.Scanner;

public class Huffman {

	public static void main(String[] args) {

		String txtFile=args[0];

		LetterFrequencyGenerator lfgen=new LetterFrequencyGenerator(txtFile);

		//lfgen.freqTable(); //not sorted
		lfgen.sort();
		//lfgen.freqTable();//sorted


		Node []n=new Node[lfgen.getNumberOfUniqueLetters()];
		for(int i=0;i<n.length;i++)
		{
			n[i]=new Node();
			n[i].setKey(i);
			n[i].setOccurence(lfgen.getOccurences()[i]);
			n[i].setValue(lfgen.getUniqueLettersFreq()[i]);
			n[i].setSymbol(String.valueOf(lfgen.getUniqueLetters()[i]));
		}

		HuffmanTree tr=new HuffmanTree(n);
		tr.buildTree();


		Scanner input=new Scanner(System.in);

		System.out.print("Enter text: ");
		String line=input.nextLine();
		tr.HuffmanEncoder(line);

		input.close();



	}//end main method

}//end class Huffman





































