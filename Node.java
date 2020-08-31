
public class Node {

	int value; // value i.e weight
	int	key; 
	String symbol; //char attached to node
	String code;
	int occurence;
	Node left,right;
	
		
	
	public Node() {
		
	}
	
	public Node(int k,int val,String sym) {
		
		key=k;
		value=val;
		symbol=sym;
		
	}
	
	public Node(Node nd) //copy constructor
	{
		this.key= nd.key;
		this.value =nd.value;
		this.left=nd.left;
		this.right=nd.right;
		this.symbol=nd.symbol;
		this.code=nd.code;
		this.occurence=nd.occurence;
	}
	

	public void setValue(int val) 
	{
		value = val;
	}
	
	
	public void setSymbol(String sym) 
	{
		symbol = sym;
	}


	public String getSymbol() 
	{
		return symbol;
	}

	
	public int getValue() 
	{
		return value;
	}

	
	public void setKey(int k) 
	{
		key = k;
	}

	
	public int getKey() 
	{
		return key;
	}
	
	
	public void setLeft(Node l)
	{
		left=l;
	}

	
	public void setRight(Node r)
	{
		right=r;
	}
	
	
	public Node getLeft()
	{
		return left;
		
	}
	
	
	public Node getRight()
	{
		return right;	
	}
	
	
	public String getCode()
	{
		return code;
	}
	
	
	public void setCode(String c)
	{	
		code=c;
	}

	
	public void setOccurence(int oc)
	{
		occurence=oc;	
	}
	
	
	public int getOccurence()
	{
		return occurence;
	}

}//end class Node
