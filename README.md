Proyecto Final (ICC-211): Sistema de Gestión de Rutas

Luis Suarez Beato - 10150193

# Clases que Armé:
Location:
Es básicamente una ubicación con un nombre.
Le agregué métodos para manejar el nombre de la ubicación.

Connection:
Representa una conexión entre dos lugares osea(la arista)
Tiene info como la distancia y el tiempo entre esos lugares.

DisjointSet:
Este es un poco más técnico. Lo usé para el algoritmo de Kruskal

RouteManagementSystem:
Es el corazón del programa. Aquí guardo todas las ubicaciones y conexiones.
Aquí están los métodos para agregar ubicaciones, conectarlas, calcular rutas y otras cosas útiles.
Aqui estan todos los algoritmos, osea prim, kruskal, dijsktra y floyd-Warshall
Verificaciones y metodos que apoyen el programa en si

Ejecucion:
Este es el que pone todo en marcha.
Tiene un menú donde el usuario puede elegir qué quiere hacer: conectar lugares, calcular rutas, modificar cosas, etc...
el cual le permite al usuario hacer todas las opciones infinitamente hasta que este decida salir del programa en si.


# Lo que hice en el proyecto:

Agregar y Modificar Lugares:
Puedo agregar nuevos lugares al mapa y cambiarles el nombre si quiero.

Conectar Lugares:
Puedo conectar dos lugares y decirle al programa cuánto tiempo y distancia hay entre ellos.

Calcular Rutas:
Usando el algoritmo de Dijkstra, puedo encontrar la ruta más corta entre un lugar y todos los demás.

Árboles de Expansión Mínima:
También puedo calcular el árbol de expansión mínima usando los algoritmos de Prim o Kruskal.

Camino más Corto entre Todos:
Con el algoritmo de Floyd-Warshall, puedo encontrar el camino más corto entre todos los lugares.

Cómo Funciona:
Cuando inicia el programa, se muestra un menú con varias opciones.

Conectar lugares: Me permite agregar una conexión entre dos lugares y establecer la distancia y el tiempo.

Calcular ruta más corta: Usando el algoritmo de Dijkstra, me muestra la ruta más corta desde un lugar específico a todos los demás.

Árbol de expansión mínima: Puedo calcular el árbol de expansión mínima usando Prim o Kruskal.

Camino más corto entre todos: Con el algoritmo de Floyd-Warshall, puedo encontrar el camino más corto entre todos los lugares.

Modificar o Eliminar lugares: Puedo cambiar el nombre de un lugar, actualizar su distancia o tiempo, o incluso eliminar un lugar si ya no me interesa.

Cosas Extras:
Implementé una forma de guardar y cargar mis datos para que no tenga que empezar desde cero cada vez que cierro y vuelvo a abrir el programa, aunque este me ha dado problemas, no funciona en su totalidad, la intencion era guardarlos para operar ahi mismo, pero no me lo ha permitido

Se me complico lo que era utilizar mapas, al prinicipio todo rondaba bien, y me compilaba y todo se guardaba pero no se que cambio en el programa hice el cual provoco que se complicara mas de lo que deberia, el guardado del grafo se hace correctamente pero no se porque hay operaciones que no se realizan con exactitud incluso con problemas. Intente hacerlo inicialmente sin mapas, pero se me complico la implementacion, estoy convencido que fue mi falta de empeno en el proyecto, pero realmente a la hora de ir con mapas, inicialmente me funcionaba todo con fluidez.

A la hora de lo que es el funcionamiento, primeramente intente hacerlo visual con el mismo IDE eclipse, pero me daba errores no se si era por las claes que utilizaba o que, pero al final me incline por usar la consola y en esta funciona todo bien, pero a la hora de printear la ruta me da errores, especialmente la de dijsktra intente varias cosas para que esta funcionara, cambie el grafo, lo intente hacer visual, verifique variables, verifique metodos y no encontre cual era el problema. El floyd me funcionaba, hasta que no se que cambio realize que hizo que todo mi progreso se fuera.

El grafo que utilize para poder operar fue:

0 2 (16)

0 4 (18)
0 5 (10)
1 4 (12)
1 5 (8)
2 3 (4)
2 4 (3)
4 5 (15)
3 0 (20)




