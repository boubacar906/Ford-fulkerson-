package tp;

import java.util.LinkedList;
import java.util.stream.Collectors;

import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import org.graphstream.algorithm.util.Result;
import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import java.util.ArrayList;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.*;

import tp.FordFulkersonAlgorithm;


public class flow{
    public static void main(String[] args) {
        //création_du_graphe

        Graph graph = new SingleGraph("G");

        //Création des noeuds
        graph.addNode("n1" );
        graph.addNode("n2" );
        graph.addNode("n3" );
        graph.addNode("n4" );
        graph.addNode("n5" );
        graph.addNode("n6" );
        graph.addNode("n7" );
        graph.addNode("n8" );
        graph.addNode("n9" );
        graph.addNode("n10" );
        graph.addNode("t" );
        graph.addNode("s" );


        //Création des arcs
        graph.addEdge("s1","s", "n1",true);
        graph.addEdge("s2","s", "n2",true);
        
        graph.addEdge("n1_n3","n1", "n3",true);
        graph.addEdge("n1_n5","n1", "n5",true);
        graph.addEdge("n1_n6","n1", "n5",true);

        graph.addEdge("n2_n5","n2", "n5",true);
        graph.addEdge("n2_n6","n3", "n6",true);

        graph.addEdge("n3_n4","n3", "n4",true);
        graph.addEdge("n3_n5","n3", "n5",true);

        graph.addEdge("n5_n8","n5", "n8",true);
        graph.addEdge("n5_n9","n5", "n9",true);
        graph.addEdge("n5_n10","n5", "n10",true);

        graph.addEdge("n6_n7","n6", "n7",true);

        graph.addEdge("n4_n8","n4", "n8",true);
        graph.addEdge("n4_n9","n4", "n9",true);

        graph.addEdge("n7_n9","n7", "n9",true);
        graph.addEdge("n7_n10","n7", "n10",true);

        graph.addEdge("n8_t","n8", "t",true);

        graph.addEdge("n9_t","n9", "t",true);

        graph.addEdge("n10_t","n10", "t",true);

        //Affichage du graphe graphique
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();

        FordFulkersonAlgorithm ford = new FordFulkersonAlgorithm();
        ford.init(graph, "s", "t");

        //On attribue à chaque arc sa capacité
        ford.setCapacity(graph.getNode("s"),graph.getNode("n1"),35);
        ford.setCapacity(graph.getNode("s"),graph.getNode("n2"),25);

        ford.setCapacity(graph.getNode("n1"),graph.getNode("n3"),20);
        ford.setCapacity(graph.getNode("n1"),graph.getNode("n5"),15);
        ford.setCapacity(graph.getNode("n1"),graph.getNode("n6"),12);

        ford.setCapacity(graph.getNode("n2"),graph.getNode("n5"),6);
        ford.setCapacity(graph.getNode("n2"),graph.getNode("n6"),22);

        ford.setCapacity(graph.getNode("n3"),graph.getNode("n4"),15);
        ford.setCapacity(graph.getNode("n3"),graph.getNode("n5"),10);

        ford.setCapacity(graph.getNode("n5"),graph.getNode("n8"),10);
        ford.setCapacity(graph.getNode("n5"),graph.getNode("n9"),15);
        ford.setCapacity(graph.getNode("n5"),graph.getNode("n10"),15);

        ford.setCapacity(graph.getNode("n6"),graph.getNode("n7"),22);

        ford.setCapacity(graph.getNode("n4"),graph.getNode("n8"),7);
        ford.setCapacity(graph.getNode("n4"),graph.getNode("n9"),10);

        ford.setCapacity(graph.getNode("n7"),graph.getNode("n9"),10);
        ford.setCapacity(graph.getNode("n7"),graph.getNode("n10"),10);

        ford.setCapacity(graph.getNode("n8"),graph.getNode("t"),15);

        ford.setCapacity(graph.getNode("n9"),graph.getNode("t"),15);

        ford.setCapacity(graph.getNode("n10"),graph.getNode("t"),20);


        ford.compute();
    }

}
