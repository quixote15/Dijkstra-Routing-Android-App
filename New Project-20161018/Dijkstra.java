/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import model.Aresta;
import model.Grafo;
import model.Vertice;
import javafx.util.Pair;
/**
 *
 * @author aluno
 */
public class Dijkstra {
    
    public static int[] dijkstra(Grafo grafo,int s){
         final int[] distancia = new int[grafo.size()];
         final boolean[] visitados = new boolean[grafo.size()]; 
         final LinkedList<Integer> prev = new LinkedList<Integer>();
        
  
        for(int i=0; i < grafo.size(); i++){ //INICIALIZA DISTANCIAS PARA INFINITO E TODOS VERTICES COMO NAO VISITADOS
            distancia[i] = Integer.MAX_VALUE;
            visitados[i] = false;
            prev.add(i, -1);
        }
        
        distancia[s] = 0; //DISTANCIA DO VERTICE RAIZ ZERO
        
        for(int i =0; i < grafo.size(); i++){
          
            int u = minVertice(distancia,visitados);
            if(u != -1){
                visitados[u] = true;
                int tam = grafo.getNumberOfEdgesFromVertex(u);
                LinkedList<Pair<Integer,Integer>> vizinhos = grafo.getAdjacencyList(u);
                for(Pair<Integer,Integer> edge : vizinhos){

                     int d = distancia[u] + edge.getValue();
                     int w = edge.getKey();
                     if(distancia[w] > d && !visitados[w] ){ //a aresta (u,w) tem distancia menor
                         distancia[w] = d;
                         prev.add(w, u);
                     }

                }
            }
        }

         
        grafo.setPrevious(prev);
        return distancia;
    }

    private static int minVertice(int[] distancia, boolean[] visitados) {
       int x = Integer.MAX_VALUE;
       int y = -1;
       for(int i = 0; i < distancia.length; i++){
           if(!visitados[i] && distancia[i] < x){
               y = i;
               x = distancia[i];
           }
       }
       return y;
    }
    
    
}

