package logico;

import java.util.Scanner;

public class Ejecucion {
    public static void main(String[] args) {
        RouteManagementSystem routeSystem = new RouteManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	  System.out.println("\nSistema de Gesti�n de Rutas");
              System.out.println("1. Conectar ubicaciones");
              System.out.println("2. Calcular ruta m�s corta (Dijkstra)");
              System.out.println("3. Calcular �rbol de expansi�n m�nima (Prim)");
              System.out.println("4. Calcular �rbol de expansi�n m�nima (Kruskal)");
              System.out.println("5. Calcular camino m�s corto entre todas las ubicaciones (Floyd-Warshall)");
              System.out.println("6. Mostrar ubicaciones y conexiones");
              System.out.println("7. Salir");
              System.out.print("Seleccione una opci�n: ");
              
            String input = scanner.nextLine();

            if (!isNumeric(input)) {
                clearScreen();
                System.out.println("Por favor, ingrese un n�mero v�lido.");
                continue;
            }

            int option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    connectLocations(routeSystem, scanner);
                    break;
                case 2:	
                    System.out.print("Ingrese el nombre de la ubicaci�n de inicio: ");
                    String startLocationName = scanner.nextLine();
                    routeSystem.dijkstra(new Location(startLocationName));
                    break;
                case 3:
                    routeSystem.prim();
                    break;
                case 4:
                    routeSystem.kruskal();
                    break;
                case 5:
                    routeSystem.floydWarshall();
                    break;
                case 6:
                    routeSystem.displayLocations();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    clearScreen();
                    System.out.println("Opci�n inv�lida. Por favor, seleccione una opci�n v�lida.");
            }
        }
    }

    private static void connectLocations(RouteManagementSystem routeSystem, Scanner scanner) {
        System.out.print("Ingrese el nombre de la primera ubicaci�n: ");
        String startLocationName = scanner.nextLine();
        System.out.print("Ingrese el nombre de la segunda ubicaci�n: ");
        String endLocationName = scanner.nextLine();
        System.out.print("Ingrese la distancia entre las ubicaciones: ");
        int distance = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el tiempo para llegar entre las ubicaciones: ");
        int time = Integer.parseInt(scanner.nextLine());
        routeSystem.addConnection(new Location(startLocationName), new Location(endLocationName), distance, time);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}


/*

if(!routeSystem.isConnected()){
System.out.println("El grafo no est� completamente conectado. Aseg�rese de que todas las ubicaciones est�n conectadas.");
break;
}

*/