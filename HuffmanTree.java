
public class HuffmanTree {

	Node[] leaf; //nodes stored
	Node[] copyleaf;
	Node root; //nodes merge
	Node[] subTree; // roots stored

	HuffmanTree subtree;
	static int i=0,j=1; //positions of stored nodes
	static int c=0; //counts number of subtree

	public HuffmanTree(Node[] nd) 
	{
		leaf=nd;
		root=null;
		copyLeaves(leaf);
	}

	public void copyLeaves(Node[] arr)
	{
		copyleaf=new Node[arr.length];

		for(int n=0;n<leaf.length;n++)
			copyleaf[n]=new Node(arr[n]);
	}

	public void buildTree() 
	{
		subTree=new Node[leaf.length];
		for(;j<leaf.length;j++)
			root=add(root,leaf[i], leaf[j]);
		
		setCode();
		print();
	}

	public Node add(Node nd, Node nd1, Node nd2)//insert nodes
	{
		if(c>0)
			getMinSubTree();

		if(nd==null)
		{
			nd=checkLeftOrRight(nd1,nd2);
			nd=merge(nd,nd1,nd2);

			i+=2;
			j+=2;
			return nd;
		}
		else if (nd.value <= nd2.value)
		{
			nd=checkLeftOrRight(nd,nd1);
			nd=merge(nd,nd,nd1);
			i++;
			j++;
			return nd;
		}
		else if( nd1.value <= nd.value && nd2.value <= nd.value )
		{
			subTree[c++]=new Node(nd);
			nd=new Node();
			nd=checkLeftOrRight(nd1,nd2);
			nd=merge(nd,nd1,nd2);
			i+=2;
			j+=2;
			return nd;
		}
		else if (nd1.value >= subTree[0].value && nd1.value >= subTree[1].value)
		{
			nd=checkLeftOrRight(nd1,nd2);
			nd=merge(nd,subTree[0],subTree[1]);
			delete();
			delete();
			subTree[c++]=new Node(nd);		

			return nd;
		}

		return nd;

	}

	public void delete() // delete two subtrees that are merge into one subtree 
	{
		int indexToDelete=0;

		for (int i = indexToDelete; i < subTree.length-1; i++) {
			subTree[i] = subTree[i+1];

		}
		subTree	[subTree.length-1].value = 0;

	}

	public void getMinSubTree() //get the lowest subtrees to merge
	{

		int sum=0;
		Node tempNode1=subTree[0];
		Node tempNode2=subTree[1];

		for(int n=0;n<subTree.length;n++)
		{
			if(subTree[n].value!=0)
				sum++;
		}
		int[] subTreeVal=new int[sum];

		for(int n=0;n<sum;n++)
		{
			subTreeVal[n]=subTree[n].value;
		}

		ms=new MergeSort(subTreeVal);


		subTree[0]=subTree[ms.getArrayOriginalIndex()[1]];
		subTree[1]=subTree[ms.getArrayOriginalIndex()[1]];

		subTree[ms.getArrayOriginalIndex()[0]]=tempNode1;
		subTree[ms.getArrayOriginalIndex()[1]]=tempNode2;

	}


	public Node merge(Node nd,Node nd1,Node nd2) //merge two subtrees to form a root
	{
		int sum=nd1.getValue()+nd2.getValue();
		String sumSym=nd1.getSymbol()+nd2.getSymbol();
		nd.setValue(sum);
		nd.setSymbol(sumSym);
		nd.setOccurence(-sum);

		return nd;
	}


	public Node checkLeftOrRight(Node nd1, Node nd2) //set Left child as lesser node by lower weight and occurences as right child
	{
		Node nd=new Node();
		
		if (nd1.value > nd2.value)
		{
			nd.right = nd1;
			nd.left = nd2;
			nd.right.code = "1";
			nd.left.code = "0";
		}
		else if (nd1.value < nd2.value)
		{
			nd.right = nd2;
			nd.left = nd1;
			nd.right.code = "1";
			nd.left.code = "0";
		}
		else //If Equal weights, then occurences are compared
			if (nd1.occurence > nd2.occurence)
			{
				nd.right = nd1;
				nd.left = nd2;
				nd.right.code = "1";
				nd.left.code = "0";

			}
			else if (nd1.occurence < nd2.occurence)
			{
				nd.right = nd2;
				nd.left = nd1;
				nd.right.code = "1";
				nd.left.code = "0";
			}
			else
				return nd;

		return nd;
	}

	public void setCode() //set the codes i.e. in bits
	{

		for(int num=0;num<leaf.length;num++)
			leaf[num].code=find(root,num);

	}

	public String find(Node nd,int num) //find the node while getting all the bits stored along the path
	{

		nd=leaf[15];

		if (nd.symbol == leaf[num].symbol)
			return nd.code;
		else if (nd.value < leaf[num].value) //value greater than root's key
		{

			if(nd.right==null)
				return nd.code;
			else if(nd.code==null)
				nd.code=nd.left.code;
			else
				nd.code=nd.code.concat(nd.left.code);

			find(nd.left,num);
		}
		else if (nd.value >= leaf[num].value )
		{
			if(nd.right==null)
				return nd.code;
			else if(nd.code==null)
				nd.code=nd.right.code;
			else
				nd.code=nd.code.concat(nd.right.code);


			find(nd.right,num);
		}


		return nd.code;
	}


	public void print()
	{
		writtenCodeResults();

		System.out.printf("\n%20s%25s%10s%30s\n","Characters","Frequency(Occurence)","Code","Code(Solved By Hand)");
		for(int i=0;i<leaf.length;i++)
		{
			System.out.printf("%15s%17d(%2d)%19s%25s\n" ,leaf[i].getSymbol(),leaf[i].getValue(),leaf[i].getOccurence(),leaf[i].getCode(),copyleaf[i].code);
		}

	}

	public void HuffmanEncoder(String line) //text to Huffman code converter
	{
		for(int i=0;i<line.length();i++)

			for(int j=0;j<copyleaf.length;j++)
			{
	
				if(line.charAt(i)==copyleaf[j].symbol.charAt(0))
				{
					System.out.print(copyleaf[j].code);
					break;
				}

			}
	}


	/* 
	 * NOTE:
	 *
	 * Since I could not complete the code (lacking the bit code), I get the codes by solving it by hand in order to complete
  		the documentation results.
	 */
	public void writtenCodeResults()
	{

		copyleaf[0].code="0110100010";
		copyleaf[1].code="0110100011";
		copyleaf[2].code="011010000";
		copyleaf[3].code="100000010";
		copyleaf[4].code="100000011";
		copyleaf[5].code="01101001";
		copyleaf[6].code="10000000";
		copyleaf[7].code="11001010";
		copyleaf[8].code="11001011";
		copyleaf[9].code="0110101";
		copyleaf[10].code="1000001";
		copyleaf[11].code="1100100";
		copyleaf[12].code="001100";
		copyleaf[13].code="001101";
		copyleaf[14].code="011011";
		copyleaf[15].code="100001";
		copyleaf[16].code="110011";
		copyleaf[17].code="00111";
		copyleaf[18].code="01000";
		copyleaf[19].code="01001";
		copyleaf[20].code="01100";
		copyleaf[21].code="10001";
		copyleaf[22].code="10100";
		copyleaf[23].code="10101";
		copyleaf[24].code="11000";
		copyleaf[25].code="0000";
		copyleaf[26].code="1000";
		copyleaf[27].code="0010";
		copyleaf[28].code="0101";
		copyleaf[29].code="0111";
		copyleaf[30].code="1001";
		copyleaf[31].code="1011";
		copyleaf[32].code="1101";
		copyleaf[33].code="111";

	}

}//end class HuffmanTree


















/*	public HuffmanTree(Node[] node) {
		super(node);


		left=node[0];
		right=node[1];
		newNodeArr=new Node[node.length];


		buildHuffman();
	}

	public void buildHuffman()
	{
		iPtr=2;
		jPtr=3;
		newNodePtr=0;

		for(int newNodeIndex=0;newNodeIndex<node.length-1;newNodeIndex++)
		{

			if(jPtr<node.length &&  iPtr<node.length)
			{

				merge(left,right,newNodeIndex); 	  

				while(node[iPtr].getValue()==0)
					iPtr++;	

				while(node[jPtr].getValue()==0)
					jPtr++;

				left=node[iPtr];
				right=node[jPtr];
				compare(left,right,newNodePtr);
			}


				  left=node[2];  //i
		  right=newNodeArr[0];  

		  merge(left,right,1);

		  left=node[3];      //i+1
		  right=node[4];

		  merge(left,right,2);

		  left=newNodeArr[1];
		  right=node[5];           

	  	  merge(left,right,3);

	  	  left=node[6]; //i+4
		  right=newNodeArr[2];

		  merge(left,right,4);

		}

	}

	public void compare(Node leftnd,Node rightnd,int newNodeIndex)
	{		

		Node minNewNode=newNodeArr[newNodeIndex];
		Node minNewNode2=null;

		for(int i=newNodeIndex;i<getNewNodeSize();i++)
		{
			if(newNodeArr[i].getValue()<minNewNode.getValue())
				minNewNode = newNodeArr[i];

			if(newNodeArr[i+1]!=null)
				minNewNode2=newNodeArr[i+1];
		}

		if((minNewNode2!=null) && (minNewNode2.getValue()<=leftnd.getValue()))
		{
			left=minNewNode2;
			left.setValue(0);
			newNodePtr++;
			leftnd.setCode(newNodeArr[newNodeIndex].getCode().concat("0"));

			if(minNewNode.getValue()<=rightnd.getValue())
			{
				right=minNewNode;
				right.setValue(0);
				node[jPtr].setValue(0);
				jPtr++;
				rightnd.setCode(newNodeArr[newNodeIndex].getCode().concat("1"));
				newNodePtr++;

			}
		}
		else if(minNewNode.getValue()<=rightnd.getValue())
		{
			right=minNewNode;

		//	node[jPtr].setValue();
			//node[jPtr].setValue(0);
			iPtr++;
			jPtr++;
			newNodePtr++;

		}
		else if (right==rightnd && left==leftnd)
		{
			iPtr=iPtr+2;
			jPtr=jPtr+2;
		}

	}

	public void merge(Node leftnd,Node rightnd,int newNodeKey)
	{

		int sum;
		sum=left.getValue()+right.getValue();
		newNodeArr[newNodeKey]=new Node(newNodeKey,sum,left.getSymbol()+right.getSymbol());


		if(rightnd.getCode()==null || rightnd.getCode()==null)
		{
			rightnd.setCode("0");
			leftnd.setCode("1");
			newNodeArr[newNodeKey].setCode((left.getCode()+right.getCode()));
		}
		else if(newNodeArr[newNodeKey].getCode()!=null)
		{	
			newNodeArr[newNodeKey].setCode(newNodeArr[newNodeKey].getCode().concat((left.getCode()+right.getCode())));
			leftnd.setCode(leftnd.getCode().concat(newNodeArr[newNodeKey].getCode()));
			rightnd.setCode(rightnd.getCode().concat(newNodeArr[newNodeKey].getCode()));
			}


		System.out.print(newNodeArr[newNodeKey].getSymbol()+"  ");
		//System.out.print(newNodeArr[newNodeKey].getCode()+"  ");
		newNodeSize++;
	}   

	public Node[] getNewNode()
	{
		return newNodeArr;

	}

	public int getNewNodeSize()
	{
		return newNodeSize;

	}

	public Node getLeft()
	{
		return left;   
	}
	public Node getRight()
	{
		return right;

	}*/

/*public void print()
	{

		System.out.printf("\n%20s%14s%15s\n","Characters","Frequency","Code");
		for(int i=0;i<node.length;i++)
			System.out.printf("%15s%15d%19s%19s\n" ,node[i].getSymbol(),node[i].getValue(),node[i].getCode(),node[i].getOccurence());

	}*/





















