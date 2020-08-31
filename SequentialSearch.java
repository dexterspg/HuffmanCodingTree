	
public class SequentialSearch {

	public static int findPos(int search, int[] arr) 
	{
		for(int i=0;i<arr.length;i++) 
		{
			if(arr[i]==search) 
			{
				return i;
			}//end if

		}//end for

		return -1;
	}//end method LSearch

}//end class SequentialSearch
