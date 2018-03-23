package sauro022.weights;


import java.io.IOException;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WeightsController {
    private static final String WORD_FILTER = "[^a-zA-Z]+";

	@RequestMapping("/weights")
    public String weights(@RequestParam(value="stringToParse", required=true) final String stringToParse){   	
    	// Taken the input string and split into the words we wish to weigh
		final String[] tokenisedStrings= tokeniseStrings(stringToParse); 
		// Create a map with an entry for each token, together with the weight
    	final HashMap<String, Integer> weightedMap =createWeightedMap(tokenisedStrings);
    	// Insert the map into the tree
    	final WeightedTree weightedTree = new WeightedTree(weightedMap);
    	// Traverse the tree, creating a hash map with binary codes for each word
    	final HashMap<String, String> traversedTree =weightedTree.traverseByLowestWeight();
    	// Output the formatted binary codes in the order of the original text
    	return createResultString(tokenisedStrings, traversedTree);
    }

	String createResultString(String[] tokenisedStrings, HashMap<String, String> traversedTree) {
		StringBuffer result = new StringBuffer();
		for (String token : tokenisedStrings) {
			result.append(traversedTree.get(token.toLowerCase()));
			result.append("-");
		}
	    result.setLength(Math.max(result.length() - 1, 0));
	    return result.toString();
	}

	HashMap<String, Integer> createWeightedMap(final String[] tokenisedString) {
		final HashMap<String, Integer> weightedMap = new HashMap<>();
		for (String token : tokenisedString) {
    		final String tokenIgnoreCase =token.toLowerCase();;
    		if (weightedMap.containsKey(tokenIgnoreCase)) {
    			weightedMap.put(tokenIgnoreCase, weightedMap.get(tokenIgnoreCase).intValue() + 1);
    		} else {
    			if (!tokenIgnoreCase.isEmpty()) {
    				weightedMap.put(tokenIgnoreCase, 1);
    			}
    		}
    	}
	   	return weightedMap;
	}

	String[] tokeniseStrings(final String stringToParse) {
		final String[] result=stringToParse.split(WORD_FILTER);
		return result;
	}
}