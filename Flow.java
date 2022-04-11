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
        graph.addNode("Usine1" );
        graph.addNode("Usine2" );
        graph.addNode("PF1" );
        graph.addNode("PF2" );
        graph.addNode("PF3" );
        graph.addNode("PF4" );
        graph.addNode("PF5" );
        graph.addNode("Client1" );
        graph.addNode("Client2" );
        graph.addNode("Client3" );
        graph.addNode("t" );
        graph.addNode("s" );


        //Création des arcs
        graph.addEdge("s1","s", "Usine1",true);
        graph.addEdge("s2","s", "Usine2",true);

        graph.addEdge("Usine1_1","Usine1", "PF1",true);
        graph.addEdge("Usine1_2","Usine1", "PF2",true);
        graph.addEdge("Usine1_3","Usine1", "PF3",true);

        graph.addEdge("Usine2_1","Usine2", "PF2",true);
        graph.addEdge("Usine2_2","Usine2", "PF3",true);

        graph.addEdge("PF1_1","PF1", "PF4",true);
        graph.addEdge("PF1_2","PF1", "PF2",true);

        graph.addEdge("PF2_1","PF2", "Client1",true);
        graph.addEdge("PF2_2","PF2", "Client2",true);
        graph.addEdge("PF2_3","PF2", "Client3",true);

        graph.addEdge("PF3_1","PF3", "PF5",true);

        graph.addEdge("PF4_1","PF4", "Client1",true);
        graph.addEdge("PF4_2","PF4", "Client2",true);

        graph.addEdge("PF5_1","PF5", "Client2",true);
        graph.addEdge("PF5_2","PF5", "Client3",true);

        graph.addEdge("Client1_1","Client1", "t",true);

        graph.addEdge("Client2_1","Client2", "t",true);

        graph.addEdge("Client3_1","Client3", "t",true);

        //Affichage du graphe graphique
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();

        FordFulkersonAlgorithm ford = new FordFulkersonAlgorithm();
        ford.init(graph, "s", "t");

        //On attribue à chaque arc sa capacité
        ford.setCapacity(graph.getNode("s"),graph.getNode("Usine1"),35);
        ford.setCapacity(graph.getNode("s"),graph.getNode("Usine2"),25);

        ford.setCapacity(graph.getNode("Usine1"),graph.getNode("PF1"),20);
        ford.setCapacity(graph.getNode("Usine1"),graph.getNode("PF2"),15);
        ford.setCapacity(graph.getNode("Usine1"),graph.getNode("PF3"),12);

        ford.setCapacity(graph.getNode("Usine2"),graph.getNode("PF2"),6);
        ford.setCapacity(graph.getNode("Usine2"),graph.getNode("PF3"),22);

        ford.setCapacity(graph.getNode("PF1"),graph.getNode("PF4"),15);
        ford.setCapacity(graph.getNode("PF1"),graph.getNode("PF2"),10);

        ford.setCapacity(graph.getNode("PF2"),graph.getNode("Client1"),10);
        ford.setCapacity(graph.getNode("PF2"),graph.getNode("Client2"),15);
        ford.setCapacity(graph.getNode("PF2"),graph.getNode("Client3"),15);

        ford.setCapacity(graph.getNode("PF3"),graph.getNode("PF5"),22);

        ford.setCapacity(graph.getNode("PF4"),graph.getNode("Client1"),7);
        ford.setCapacity(graph.getNode("PF4"),graph.getNode("Client2"),10);

        ford.setCapacity(graph.getNode("PF5"),graph.getNode("Client2"),10);
        ford.setCapacity(graph.getNode("PF5"),graph.getNode("Client3"),10);

        ford.setCapacity(graph.getNode("Client1"),graph.getNode("t"),15);

        ford.setCapacity(graph.getNode("Client2"),graph.getNode("t"),15);

        ford.setCapacity(graph.getNode("Client3"),graph.getNode("t"),20);


        ford.compute();
    }

}
