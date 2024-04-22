package logico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

//Clase principal del Sistema de Gestión de Rutas
public class RouteManagementSystem {
 private Map<Location, List<Connection>> graph;
 private static final String PROYECTOALGORIT = "pruebas.dat";

 // Constructor
 public RouteManagementSystem() {
     this.graph = new HashMap<>();
     loadFromFile();
 }

//Método para cargar información desde el archivo
private void loadFromFile() {
  File file = new File(PROYECTOALGORIT);
  if (file.exists()) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
          graph = (Map<Location, List<Connection>>) ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
          System.out.println("No se pudo leer el archivo " + PROYECTOALGORIT);
      }
  } else {
      System.out.println("El archivo " + PROYECTOALGORIT + " no existe. Se creará uno nuevo.");
      graph = new HashMap<>();
  }
}

//Método para guardar información en el archivo
public void saveToFile() {
  try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PROYECTOALGORIT))) {
      oos.writeObject(graph);
      System.out.println("Datos guardados exitosamente en " + PROYECTOALGORIT);
  } catch (IOException e) {
      System.out.println("No se pudo escribir en el archivo " + PROYECTOALGORIT);
  }
}


 
 // Agregar una ubicación al grafo
 public void addLocation(Location location) {
     graph.putIfAbsent(location, new ArrayList<>());
 }

 // Agregar una conexión entre ubicaciones
 public void addConnection(Location start, Location end, int distance, int time) {
	    graph.putIfAbsent(start, new ArrayList<>());
	    graph.putIfAbsent(end, new ArrayList<>());
	    
	    if (connectionExists(start, end)) {
	        System.out.println("Estas ubicaciones ya fueron digitadas, desea modificar el peso y el tiempo?");
	        System.out.print("Ingrese 'si' para modificar o cualquier otra cosa para continuar: ");
	        Scanner scanner = new Scanner(System.in);  // Crear un nuevo Scanner para leer la respuesta
	        String response = scanner.nextLine();
	        
	        if ("si".equalsIgnoreCase(response)) {
	            modifyConnection(start, end, distance, time);
	        }
	        scanner.close();  // Cerrar el Scanner después de usarlo
	        return;
	    }
	    
	    graph.get(start).add(new Connection(start, end, distance, time));
	    graph.get(end).add(new Connection(end, start, distance, time));
	    System.out.println("Conexión agregada exitosamente.");
	}



	private boolean connectionExists(Location start, Location end) {
	    return graph.containsKey(start) && graph.get(start).stream().anyMatch(connection -> connection.end.equals(end));
	}

	private void modifyConnection(Location start, Location end, int distance, int time) {
	    // Modificar la conexión existente
	    for (Connection connection : graph.get(start)) {
	        if (connection.end.equals(end)) {
	            connection.distance = distance;
	            connection.time = time;
	            break;
	        }
	    }
	    for (Connection connection : graph.get(end)) {
	        if (connection.end.equals(start)) {
	            connection.distance = distance;
	            connection.time = time;
	            break;
	        }
	    }
	    System.out.println("Conexión modificada exitosamente.");
	}



 // Algoritmo de Dijkstra para encontrar la ruta más corta entre dos ubicaciones
	public void dijkstra(Location start) {
	    Map<Location, Integer> distances = new HashMap<>();
	    Map<Location, Location> previous = new HashMap<>();
	    PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

	    // Inicializar distancias
	    for (Location location : graph.keySet()) {
	        distances.put(location, location == start ? 0 : Integer.MAX_VALUE);
	        queue.offer(location);
	    }

	    while (!queue.isEmpty()) {
	        Location current = queue.poll();
	        for (Connection connection : graph.get(current)) {
	            int newDistance = distances.get(current) + connection.distance;
	            if (newDistance < distances.get(connection.end)) {
	                distances.put(connection.end, newDistance);
	                previous.put(connection.end, current);
	                queue.remove(connection.end);
	                queue.offer(connection.end);
	            }
	        }
	    }

	    printShortestPath(start, distances, previous);
	}

	private void printShortestPath(Location start, Map<Location, Integer> distances, Map<Location, Location> previous) {
	    System.out.println("Se calculó la ruta/camino exitosamente, el camino fue:");
	    
	    for (Location location : distances.keySet()) {
	        if (location.equals(start)) continue;
	        
	        List<Location> path = new ArrayList<>();
	        Location current = location;
	        while (current != null) {
	            path.add(current);
	            current = previous.get(current);
	        }
	        Collections.reverse(path);
	        
	        System.out.println(path.stream().map(Location::toString).collect(Collectors.joining(" - ")));
	        System.out.println("El tiempo total fue: " + distances.get(location) + ", el peso total fue: " + computeTotalWeight(path));
	    }
	}

 // Algoritmo de Prim para encontrar un árbol de expansión mínima
	public void prim() {
	    Set<Location> visited = new HashSet<>();
	    List<Connection> mst = new ArrayList<>();
	    PriorityQueue<Connection> edges = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));

	    Location start = graph.keySet().iterator().next();
	    visited.add(start);

	    for (Connection connection : graph.get(start)) {
	        edges.offer(connection);
	    }

	    while (!edges.isEmpty() && visited.size() < graph.size()) {
	        Connection minEdge = edges.poll();
	        if (!visited.contains(minEdge.end)) {
	            visited.add(minEdge.end);
	            mst.add(minEdge);
	            for (Connection connection : graph.get(minEdge.end)) {
	                if (!visited.contains(connection.end)) {
	                    edges.offer(connection);
	                }
	            }
	        }
	    }

	    printMST("Prim", mst);
	}

	private void printMST(String algorithm, List<Connection> mst) {
	    System.out.println("Se calculó exitosamente el árbol de expansión mínima (" + algorithm + "), el cual fue:");
	    System.out.println(mst.stream().map(conn -> conn.start + " - " + conn.end).collect(Collectors.joining("\n")));
	}

 // Algoritmo de Kruskal para encontrar un árbol de expansión mínima
	public void kruskal() {
	    List<Connection> mst = new ArrayList<>();
	    DisjointSet disjointSet = new DisjointSet(graph.keySet());
	    PriorityQueue<Connection> edges = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));

	    for (List<Connection> connections : graph.values()) {
	        for (Connection connection : connections) {
	            edges.offer(connection);
	        }
	    }

	    while (!edges.isEmpty() && mst.size() < graph.size() - 1) {
	        Connection minEdge = edges.poll();
	        if (!disjointSet.connected(minEdge.start, minEdge.end)) {
	            disjointSet.union(minEdge.start, minEdge.end);
	            mst.add(minEdge);
	        }
	    }

	    printMST("Kruskal", mst);
	}


 // Algoritmo de Floyd-Warshall para encontrar el camino más corto entre todas las ubicaciones
	public void floydWarshall() {
	    int n = graph.size();
	    int[][] distances = new int[n][n];
	    Location[] locations = graph.keySet().toArray(new Location[0]);
	    Map<Location, Integer> indexMap = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	        indexMap.put(locations[i], i);
	    }

	    for (int i = 0; i < n; i++) {
	        Arrays.fill(distances[i], Integer.MAX_VALUE);
	    }

	    for (Location location : graph.keySet()) {
	        distances[indexMap.get(location)][indexMap.get(location)] = 0;
	        for (Connection connection : graph.get(location)) {
	            distances[indexMap.get(location)][indexMap.get(connection.end)] = connection.distance;
	        }
	    }

	    for (int k = 0; k < n; k++) {
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE && 
	                    distances[i][k] + distances[k][j] < distances[i][j]) {
	                    distances[i][j] = distances[i][k] + distances[k][j];
	                }
	            }
	        }
	    }

	    printFloydWarshallResult(distances, locations);
	}

	private void printFloydWarshallResult(int[][] distances, Location[] locations) {
	    System.out.println("Se calculó la ruta/camino exitosamente, el camino fue:");
	    
	    for (int i = 0; i < distances.length; i++) {
	        for (int j = 0; j < distances[i].length; j++) {
	            if (i != j && distances[i][j] != Integer.MAX_VALUE) {
	                List<Location> path = new ArrayList<>();
	                path.add(locations[i]);
	                path.add(locations[j]);
	                
	                System.out.println(path.stream().map(Location::toString).collect(Collectors.joining(" - ")));
	                System.out.println("El tiempo total fue: " + distances[i][j] + ", el peso total fue: " + computeTotalWeight(path));
	            }
	        }
	    }
	}

	private int computeTotalWeight(List<Location> path) {
	    int totalWeight = 0;
	    for (int i = 0; i < path.size() - 1; i++) {
	        Location start = path.get(i);
	        Location end = path.get(i + 1);
	        for (Connection connection : graph.get(start)) {
	            if (connection.end.equals(end)) {
	                totalWeight += connection.distance;
	                break;
	            }
	        }
	    }
	    return totalWeight;
	}

	public boolean isConnected() {
		Set<Location> visited = new HashSet<>();
	    dfs(graph.keySet().iterator().next(), visited);

	    // Verificar si todas las ubicaciones han sido visitadas
	    return visited.size() == graph.size();
	}

	private void dfs(Location current, Set<Location> visited) {
	    visited.add(current);
	    for (Connection connection : graph.get(current)) {
	        if (!visited.contains(connection.end)) {
	            dfs(connection.end, visited);
	        }
	    }
	}

	
	public void displayLocations() {
	    System.out.println("Ubicaciones:");
	    for (Location location : graph.keySet()) {
	        System.out.println(location.getName() + " conectado con:");
	        List<Connection> connections = graph.get(location);
	        for (Connection connection : connections) {
	            System.out.println("- " + connection.getEnd().getName() + " (Distancia: " + connection.getDistance() + ", Tiempo: " + connection.getTime() + ")");
	        }
	        System.out.println("------");
	    }
	}

}