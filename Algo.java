package tp;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.*;

import jdk.net.SocketFlow;

import org.graphstream.algorithm.flow.FlowAlgorithmBase;
import java.util.stream.*;
import java.util.LinkedList;
import java.util.List;

public class FordFulkersonAlgorithm extends FlowAlgorithmBase{

  public void compute(){
    Node source = flowGraph.getNode("s");
    Node puit = flowGraph.getNode("t");

    initialiserLesFlux();

    int F = 0; //Initialisation du flot

    LinkedList<Node> chaineAmeliorante = new LinkedList<Node>();
    double delta; //L'augmentation possible des flux
    int i = 0;
    do{
      //Calcul de delta et de la chaîne améliorante par récursivité
      delta = rechercheChaineAmelioranteEtCalculDelta(chaineAmeliorante, source, puit);

      //Affichage de la chaine améliorante
      System.out.println("\n La chaine ameliorante contient : ");
      for (Node node : chaineAmeliorante) {
        System.out.println(node.toString());
      }

      //Augmentation et affichage de F grâce à delta
      F+=delta;
      System.out.println("Le flot F a été augmenté de :"+delta);
      System.out.println("Il vaut désormais: "+F);

      //Mise à jour des flux
      miseAJourFlux(delta, chaineAmeliorante);
      chaineAmeliorante.clear();
    } while(delta > 0);

    System.out.println("Le flot maximal de ce graphe est "+F);

  }

  protected void initialiserLesFlux(){
    for (int i = 0; i < flowGraph.getEdgeCount(); i++) {
			Edge e = flowGraph.getEdge(i);
			setFlow(e.getNode0(), e.getNode1(), 0);
			setFlow(e.getNode1(), e.getNode0(), 0);
    }
  }

  protected double rechercheChaineAmelioranteEtCalculDelta(LinkedList<Node> chaineAmeliorante, Node source, Node puit){
    chaineAmeliorante.addLast(source);
    double delta;
    if (source == puit)
    	return Double.MAX_VALUE; // Quand la source est égal au puit car on fait de la récursivité, alors on retourne un immense nombre qui sera forcément plus grand que l'autre valeur à laquelle il est comparé
	  for (int i = 0; i < source.getDegree(); i++){
    	Edge e = source.getEdge(i);
    	Node n = e.getOpposite(source);
    	if (!(chaineAmeliorante.contains(n)) && (getCapacity(source, n)-getFlow(source, n) > 0)){
        delta = rechercheChaineAmelioranteEtCalculDelta(chaineAmeliorante, n, puit);
    		if (delta > 0){
    			return Math.min(delta, getCapacity(source, n)-getFlow(source, n));
        }
    	}
    }
    chaineAmeliorante.removeLast();
    return 0;
  }

  protected void miseAJourFlux(double delta, LinkedList<Node> chaineAmeliorante){
    for (int i = 1; i < chaineAmeliorante.size(); i++) {
      Node u = chaineAmeliorante.get(i - 1);
      Node v = chaineAmeliorante.get(i);

      setFlow(u, v, getFlow(u, v) + delta);
    }
  }
}
