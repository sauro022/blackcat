package sauro022.weights;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Test;
import sauro022.weights.WeightsController;


public class WeightsControllerUnitTest {
	@Test
	public void testToken() {	
		WeightsController weightsController = new WeightsController();
		String stringToParse="qqq1abc £$& DEF1)<>>,*";
		String [] result= weightsController.tokeniseStrings(stringToParse);
		String [] expected = {"qqq", "abc","DEF"};
		
		assertArrayEquals(expected,result);
	}
	
	@Test
	public void testWeightedMap() {
		WeightsController weightsController = new WeightsController();
		String [] tokens = {"ant", "boat", "row", "ANT", "car", "ant",  "die", "ant", "BOat", "row", "ant" };
		
		HashMap<String, Integer> weightedMap = weightsController.createWeightedMap(tokens);

		assertEquals(5, weightedMap.get("ant").intValue());
		assertEquals(2, weightedMap.get("boat").intValue());
		assertEquals(2, weightedMap.get("row").intValue());
		assertEquals(1, weightedMap.get("car").intValue());
		assertEquals(1, weightedMap.get("die").intValue());
	}
	
	@Test
	public void testWeightedTree1() {
		WeightsController weightsController = new WeightsController();
		String [] expected =  {"ant", "boat", "row", "ant", "car", "ant",  "die", "ant", "boat", "row", "ant" };
		HashMap<String, Integer> weightedMap = weightsController.createWeightedMap(expected);
    	WeightedTree weightedTree = new WeightedTree(weightedMap);
    	HashMap<String, String> traversedTree =weightedTree.traverseByLowestWeight();
    	assertEquals("00-10-010-00-11-00-011-00-10-010-00", weightsController.createResultString(expected, traversedTree));
	}
	
	@Test
	public void testWeightedTree2() {
		WeightsController weightsController = new WeightsController();
		String [] expected =  {"a", "nation", "of", "the", "people", "by",  "the", "people", "and", "for", "the", "people"};
		HashMap<String, Integer> weightedMap = weightsController.createWeightedMap(expected);
    	WeightedTree weightedTree = new WeightedTree(weightedMap);
    	HashMap<String, String> traversedTree =weightedTree.traverseByLowestWeight();
    	assertEquals("101-110-11110-0-100-111110-0-100-1110-111111-0-100", weightsController.createResultString(expected, traversedTree));
	}
}
