
public class MergeSort {

	private int[] arr; //sorted array
	private int[] copyArr; //unsorted array
	private int[] index; //original Index of array


	public MergeSort(int[] val)//constructor
	{
		arr=val;

		copyUnsortedArr(val);
		sort(arr,0,arr.length-1);
		sortIndex();		

	}


	public void copyUnsortedArr(int[] val) //copy of not sorted array
	{
		copyArr=new int[arr.length];

		for(int i=0;i<arr.length;i++)
			copyArr[i]=val[i];

	}//end method copyUnsortedArr


	public void sort(int[] val, int low, int high)
	{	    
		if (low < high)
		{
			int mid = (low+high)/2;

			sort(val, low, mid); //sort first half
			sort(val , mid+1, high);//sort second half
			merge(val, low, mid, high); //merge both halves    
		}//end if

	} //end method sort


	public void merge(int[] val, int low, int mid, int high)
	{
		int size1 = mid - low + 1;
		int size2 = high - mid;
		int lowTemp[] = new int [size1]; //temp array
		int highTemp[] = new int [size2]; //temp array

		int pos1 = 0;
		int pos2 = 0;
		int posM = low;

		for (int i=0; i<size1; ++i)
			lowTemp[i] = val[low + i];
		for (int j=0; j<size2; ++j)
			highTemp[j] = val[mid + 1+ j]; 

		while (pos1 < size1 && pos2 < size2)
		{
			if (lowTemp[pos1] <= highTemp[pos2])
			{
				val[posM] = lowTemp[pos1];
				pos1++;
			}//end if
			else
			{
				val[posM] = highTemp[pos2];
				pos2++;
			}//end else
			posM++;
		}//end while

		while (pos1 < size1)
		{
			val[posM] = lowTemp[pos1];
			pos1++;
			posM++;
		}//end while

		while (pos2 < size2)
		{
			val[posM] = highTemp[pos2];
			pos2++;
			posM++;
		}//end while

	}//end method merge

	public void sortIndex() //get original index of sorted array 
	{ 
		index=new int[arr.length];
		int[] indexTemp=new int[arr.length];

		/* get index by comparing unsorted and sorted array */
		for(int pos=0;pos<arr.length;pos++)
		{	
			for(int ptr2=0;ptr2<arr.length;ptr2++)
			{
				if(copyArr[pos]==arr[ptr2]) //array same values 
				{		 		 
					indexTemp[pos]=ptr2; //get the index
					break;
				}//end if

			}//end inner for

		}//end outer for

		/* eliminate repeating index*/
		for(int pos=0;pos<arr.length;pos++)
		{	
			int c=0;
			for(int ptr2=0;ptr2<pos;ptr2++)
			{	
				if(indexTemp[pos]==indexTemp[ptr2])
				{
					c++;
					if(c>=1)
						indexTemp[pos]++;

				}//end if

			}//end inner for

		}//end outer for

		for(int i=0;i<arr.length;i++)
		{
			index[i]=SequentialSearch.findPos(i,indexTemp);  
		}//end for
	
	}//end method findIndex


	public int[] getSortArray()
	{
		return arr;
	}//end method getArray


	public int[] getArrayOriginalIndex()
	{
		return index;
	}//end method getIndex

}//end class MergeSort



















//ex.values  [2 66 23 61 38 193 31 33 37 30 21 18 36 33 61 80 16 53 6 26 2 21 5 4 8 16 7 11 10 3 1 3 1 1]

/* sort index method
 * part1: repeating index
 * [3 31 19 29 27 33 22 23 26 21 17 16 25 23 29 32 14 28 9 20 3 17 8 7 11 14 10 13 12 5 0 5 0 0]
 * part2: eliminate repeating index:
 * [3 31 19 29 27 33 22 23 26 21 17 16 25 24 30 32 14 28 9 20 4 18 8 7 11 15 10 13 12 5 0 6 1 2]
 * last part: match index to sorted array frequency 
 * 30 32 33 0 20 29 31 23 22 18 26 24 28 27 16 25 11 10 21 2 19 9 6 7 13 12 8 4 17 3 14 1 15 5
 */
