package A2_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import GKA_A1.AIGraph;
import GKA_A1.IAIGraph;
import GKA_A2.BellmanFord;
import GKA_A2.FloydWarshall;
import GraphUtils.JavaParser;

public class OptimalPathTest {

	public OptimalPathTest() {
	}

	@Test
	public void testFloydWarshall() {
		System.out.println("----- testFloydWarshall -----");
		IAIGraph yolo = new AIGraph(true);

		long v1 = yolo.addVertex("v1");
		long v2 = yolo.addVertex("v2");
		long v3 = yolo.addVertex("v3");
		long v4 = yolo.addVertex("v4");

		long v1v2 = yolo.addEdgeD(v1, v2);
		yolo.setValE(v1v2, "km", 1);

		long v1v4 = yolo.addEdgeD(v1, v4);
		yolo.setValE(v1v4, "km", 3);

		long v2v3 = yolo.addEdgeD(v2, v3);
		yolo.setValE(v2v3, "km", 2);

		long v2v4 = yolo.addEdgeD(v2, v4);
		yolo.setValE(v2v4, "km", 1);

		long v3v2 = yolo.addEdgeD(v3, v2);
		yolo.setValE(v3v2, "km", 2);

		long v4v3 = yolo.addEdgeD(v4, v3);
		yolo.setValE(v4v3, "km", 2);

		long v4v1 = yolo.addEdgeD(v4, v1);
		yolo.setValE(v4v1, "km", 2);

		System.out.println(yolo);

		FloydWarshall algo = new FloydWarshall(yolo, "km");
		algo.printCount();
		algo.start();
		algo.printCount();

		System.out.println("v1: " + v1 + "; v1: " + v1);
		System.out.println(algo.getPath(v1, v1));

		System.out.println("v1: " + v1 + "; v2: " + v2);
		System.out.println(algo.getPath(v1, v2));

		System.out.println("v3: " + v3 + "; v1: " + v1);
		System.out.println(algo.getPath(v3, v1));

		assertEquals("v2 -> v1 -> v3 -> v0", algo.getPath(v3, v1));

		System.out.println(algo.getDist());
		System.out.println(algo.getTrans());
	}

	@Test
	public void testBellmanFord() {
		System.out.println("----- testBellmanFord -----");
		
		IAIGraph yolo = new AIGraph(true);

		long v1 = yolo.addVertex("v1");
		long v2 = yolo.addVertex("v2");
		long v3 = yolo.addVertex("v3");
		long v4 = yolo.addVertex("v4");

		long v1v2 = yolo.addEdgeD(v1, v2);
		yolo.setValE(v1v2, "km", 1);

		long v1v4 = yolo.addEdgeD(v1, v4);
		yolo.setValE(v1v4, "km", 3);

		long v2v3 = yolo.addEdgeD(v2, v3);
		yolo.setValE(v2v3, "km", 2);

		long v2v4 = yolo.addEdgeD(v2, v4);
		yolo.setValE(v2v4, "km", 1);

		long v3v2 = yolo.addEdgeD(v3, v2);
		yolo.setValE(v3v2, "km", 2);

		long v4v3 = yolo.addEdgeD(v4, v3);
		yolo.setValE(v4v3, "km", 2);

		long v4v1 = yolo.addEdgeD(v4, v1);
		yolo.setValE(v4v1, "km", 2);

		BellmanFord algo = new BellmanFord(yolo, "km", v3);

		algo.printCount();
		algo.start();
		algo.printCount();

		System.out.println(algo.getDist());
		System.out.println(algo.getPred());

		System.out.println(algo.getPath(v3)); // path zu sich selber

		System.out.println(algo.getPath(v1)); // path nach v1
	}

	private String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
	private String matzepath = "/Users/matthias/dev/WS1314/GKA/graphs/";
	private String path = matzepath;
	private String graphname = "graph2.graph";
	
	@Test
	public void testBoth() {
		System.out.println("----- testBoth -----");
		JavaParser jp = new JavaParser(path + graphname, "km");
		IAIGraph yolo = jp.createGraph();
		IAIGraph swag = jp.createGraph();
		
		FloydWarshall algoF = new FloydWarshall(yolo, "km");
		algoF.printCount();
		algoF.start();
		algoF.printCount();
		
		BellmanFord algoB = new BellmanFord(swag, "km", 0);

		algoB.printCount();
		algoB.start();
		algoB.printCount();
	}

}
