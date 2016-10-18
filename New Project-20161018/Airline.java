/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author CreuzaFDR
 */
public class Airline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
          
        Grafo adjacencyList = new Grafo(16);
          
        int i = 0;
          
     
        adjacencyList.printAdjacencyList();
        Dijkstra dk = new Dijkstra();
        int[] r = Dijkstra.dijkstra(adjacencyList, 0);
        adjacencyList.setDistancia(r);
        int[] path = adjacencyList.getPath(10);
        for(int x: path){
            System.out.println(x);
        }
    }
    
  
    
}
