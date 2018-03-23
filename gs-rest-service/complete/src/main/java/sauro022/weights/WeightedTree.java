package sauro022.weights;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

/*
 * Class to create a weighted tree and retrieve it with associated binary weights
 */

public class WeightedTree {
	WeightedNode rootNode=null;

	public WeightedTree(HashMap<String, Integer> weightedMap) {
		for (Entry<String, Integer> entry : weightedMap.entrySet()) {
			rootNode = addNode(rootNode, entry);
		}
	}
	
	private WeightedNode addNode(WeightedNode current, Entry<String, Integer> entry) {
		final Integer entryOccurance = entry.getValue();

	    if (current == null) {
	        return new WeightedNode(entry);
	    }
	    
	    final Integer currentOccurance = current.getOccurance();

	    if (current.getLeft() == null){
	    	// We are at a leaf node
	    	if (currentOccurance < entryOccurance) { 		
	    		WeightedNode leftNode = new WeightedNode(entry);
	    		return new WeightedNode(entryOccurance,leftNode,current );
	    	} else {
	    		WeightedNode rightNode = new WeightedNode(entry);
	    		return new WeightedNode(currentOccurance, current, rightNode);
	    	}	
	    }
	 
	    // min is the the lowest value in this branch of the tree
	    final Integer min= current.getMin()==null ? currentOccurance : current.getMin();

	    if (currentOccurance >= entryOccurance && min >= entryOccurance ) {	 
	        current.setRight(addNode(current.getRight(), entry));
	    } else {
	    	current.setMin(Math.min(currentOccurance, entryOccurance));
	    	current.setOccurance(entryOccurance);
	        current.setLeft(addNode(current.getLeft(), entry));
	    } 
	    return current;
	}
	
	// Non recursive tree traverse, calculating current binary code for each leaf
	public HashMap<String, String> traverseByLowestWeight() {
		WeightedNode currentNode = rootNode;
		Stack<WeightedNode> nodeStack = new Stack<WeightedNode>();
		
		StringBuffer binaryCode = new StringBuffer();
		HashMap<String, String> binaryWeightedMap = new HashMap<>();

		while(!nodeStack.empty() || currentNode != null){
	        if(currentNode != null){
	        	// tail node so add result to map
	        	if (currentNode.getToken() !=null ) {        		
	        		//Edge case - only one word in tree
	        		String binaryCodeString = binaryCode.length() == 0 ? "0" : binaryCode.toString();
	        		binaryWeightedMap.put(currentNode.getToken(), binaryCodeString);
	        		if (binaryCode.charAt(binaryCode.length()-1) == '1'){
	        			binaryCode.setLength(Math.max(binaryCode.length() - 1, 0));
	        		}
	        		currentNode = null;
	        		continue;
	        	}
	        	nodeStack.push(currentNode);
	        	binaryCode.append('0');
	            currentNode = currentNode.getLeft();
	        } else{
	        	WeightedNode parent=nodeStack.pop();
	        	currentNode = parent.getRight(); 

	        	// Started processing RHS of tree
	        	if (parent == rootNode) {
	        		binaryCode.setLength(0);
	        		binaryCode.append('1');
	        	}
	        	
	        	binaryCode.setLength(Math.max(binaryCode.length() - 1, 0));
	        	if (currentNode != null) {
	        		binaryCode.append('1');
	        	}
	        }
		}
		return binaryWeightedMap;
	}

}
