package A4_test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import GKA_A1.IAIGraph;
import GKA_A4.Hierholzer;
import GKA_A4.NearestInsertion;
import GraphUtils.JavaParser;
import GraphUtils.WhichPath;

public class TrailProblemTest {

	private String cmp = "km";
	private IAIGraph yolo;

	public TrailProblemTest() {
	}

	// NearestInsertion
	// @Test
	public void NearestInsertionTest1() {
		loadGraph("graph30.graph");
		// this graph is the graph the algorithm is presented in the book with.
		// write here call of nearest insertion algorithm and its test case

		NearestInsertion algo = new NearestInsertion(yolo, cmp);
		List<Long> bestCycle = algo.nearestInsertion();

		System.out.println("BestCycle: " + bestCycle);
		System.out.println("Minimum: " + algo.getMinimum());

		assertTrue(algo.getMinimum() <= 120);
	}

	// @Test
	public void NearestInsertionTest2() {
		loadGraph("graph31.graph");
		// this graph is the graph used in the exercises for the nearest
		// insertion algorithm
		// write here call of nearest inserteion algorithm and its test case

		NearestInsertion algo = new NearestInsertion(yolo, cmp);
		List<Long> bestCycle = algo.nearestInsertion();

		System.out.println("BestCycle: " + bestCycle);
		System.out.println("Minimum: " + algo.getMinimum());

		assertTrue(algo.getMinimum() <= 33);
	}

	// Hierholzer
	@Test
	public void TestIsEulerianPath() {
		loadGraph("graph35.graph");
		List<Long> eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 3L));
		assertTrue(isEulerianPath(eulerpath));
		eulerpath = new ArrayList<>(Arrays.asList(0L, 1L, 2L, 1L, 3L));
		assertTrue(!isEulerianPath(eulerpath));
	}

	//@Test
	public void TestIsEulerianPath1() {
		loadGraph("graph35.graph");
		Hierholzer algo = new Hierholzer(yolo, cmp);
		List<Long> result = algo.hierholzeEs();
		assertTrue(isEulerianPath(result));
	}

	//@Test
	public void TestIsEulerianPath2() {
		loadGraph("graph36.graph");
		Hierholzer algo = new Hierholzer(yolo, cmp);
		List<Long> result = algo.hierholzeEs();
		assertTrue(!isEulerianPath(result));
	}

	//@Test
	public void TestIsEulerianPath3() {
		loadGraph("graph37.graph");
		Hierholzer algo = new Hierholzer(yolo, cmp);
		List<Long> result = algo.hierholzeEs();
		assertTrue(isEulerianPath(result));
	}

	//@Test
	public void TestIsEulerianPath4() {
		loadGraph("graph38.graph");
		Hierholzer algo = new Hierholzer(yolo, cmp);
		List<Long> result = algo.hierholzeEs();
		assertTrue(!isEulerianPath(result));
	}

	private boolean isEulerianPath(List<Long> edges) {
		if (new HashSet<Long>(edges).size() != edges.size())
			return false;
		
		Long head = yolo.getSource(edges.get(0));
		Long last = yolo.getTarget(edges.get(edges.size() - 1));
		if (head != last)
			return false;

		Set<Long> vertices = new HashSet<>();
		for (Long eid : edges) {
			Long source = yolo.getSource(eid);
			if (yolo.getIncident(source).size() % 2 != 0) {
				return false;
			}
		}
		return true;
	}

	private void loadGraph(String graphname) {
		String path = WhichPath.getPath();
		JavaParser jp = new JavaParser(path + graphname, cmp);
		this.yolo = jp.createGraph();
		System.out.println("\n<======= New Test =======>\n Input Graph: "
				+ yolo);
	}
}
