package sauro022.weights;

import java.util.Map.Entry;

/*
 * Class to represent a weighted node
 */
public class WeightedNode {

	private WeightedNode right = null;
	private Integer occurance = null; //How many time the token occurs in the text
	private String token = null;
	private WeightedNode left = null;
	private Integer min = null; // minimum value of and node beneath this node in the tree
	
	public Integer getOccurance() {
		return occurance;
	}

	public void setOccurance(Integer occurance) {
		this.occurance = occurance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public WeightedNode getRight() {
		return right;
	}

	public void setRight(WeightedNode right) {
		this.right = right;
	}

	public void setLeft(WeightedNode left) {
		this.left = left;
	}
	
	public WeightedNode getLeft() {
		return left;
	}

	
	public WeightedNode(Entry<String, Integer> entry) {
		this.right = null;
		this.left = null;
		this.token = entry.getKey();
		this.occurance = entry.getValue();
	}

	public WeightedNode(Integer occurance, WeightedNode leftNode, WeightedNode rightNode) {
		this.right = rightNode;
		this.left = leftNode;
		this.token = null; // branch node has no token
		this.occurance = occurance;
	}

	public void setMin(Integer min) {
		this.min=min;
	}

	public Integer getMin() {
		return this.min;
	}
}
