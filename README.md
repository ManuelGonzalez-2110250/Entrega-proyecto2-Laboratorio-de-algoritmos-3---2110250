>Manuel González_21-10250

## Para ejecutar:
Antes de nada, se debe crear un archivo de nombre **"deck.csv"** que se encuentre en la misma carpeta dónde se encuentren los archivos descargados. Luego se debe ejecutar el CMD en la carpeta dónde está descargado el archivo, luego se usa el comando **"make"** para compilar el archivo. Una vez se compile se ejecuta usando el comando **./runMundoChiquito.sh**. El archivo *"deck.csv"* es una colección de string compuesto con el formato Nombre,Nivel,Atributo,Poder un ejemplo sería:

>mostroUno,5,LUZ,2100

>mostroDos,7,LUZ,2400

>mostroTres,7,VIENTO,2500

## Para el diseño de la funciones:

**CartaMostro** Clase principal para ayudar el como se almacenan las cartas, se sigue el esquema presentado, junto con un init para verificar las precondiciones de "nivel de 1 a 12, poder múltiplo de 50 y atributo entre AGUA, FUEGO, VIENTO, TIERRA, LUZ, OSCURIDAD o DIVINO." Adicionalmente cuenta con "toString()" que imprime el nombre, para el "joinToString()" del main al imprimir las cartas.

**sePuedeConectar**: Funcion auxiliar para verificar la cantidad de elementos en común de una carta, si es difertente a 1 retorna false, si es igual a 1 retorna true. Hace uso de un medidor para medir cuantas características en común hay.

**ConectarMostro:** Funcion auxiliar para conectar los mostros, verifica la lista entera y los conecta solo con aquellos que tengan un elemento en común, se diseñó pensando en la realización de un grafo tal que conecte los vértices si es que comparte un solo elemento.

**cargarDatos:** Funcion para verificar la entrada, se revisa si la entrada tiene el formato adecuado, si no lo tiene falla, ya sea por el if(elementos.size != 4) o por el init de la clase CartaMostro. Si tiene el formato adecuado verifica si fue añadido previamente una carta con el nombre proporcionado, de ser asì falla la ejecución. Finalmente si nada falla se añade la carta al grafo y se conecta con todas aquellas que comparta solo una característica. el programa continúa la ejecución hasta que se lea el .csv. Para ello, se sigue un esquema similar a previos cargarDatos con File y BufferedReader para leer el archivo.

**UsarMundoChiquito:** La función principal. Usa backtracking recursivo para verificar cada posible combinación, Dado que el grafo solo conecta si tiene una característica en común, el backtracking garantiza que todos los resultados se vean, incluso repetidos. Para evitar la repetición, se hace uso de sets para almacenar el conjunto de soluciones, dado que en los sets no importa el orden, ni tampoco tienen repetición, así el conjunto de salida es una combinación única. Para almacenar soluciones se usa listas, ya que ahí si importa el orden. Finalmente el criterio de parada para el backtraking es conseguir 3 elementos, una vez el iterador i llega a 3, se añade la solución y se retorna por el backtracking. La solución fue diseñada pensando tanto que no se repitiesen salidas, como que los elementos son únicos.

**nodos:** clases auxiliares para el funcionamiento del grafo, explicados en el proyecto 1

**ListaAdyacenciaGrafo:** Clase utlizada en el proyecto 1 con una modificación menor, se implementa "elementos()" para obtener los vertices que pertenecen al gráfo, de tal forma que las función auxiliar "sePuedeConectar" y la funcion "main" tengan acceso a la lista de cartas que contiene el grafo. Para la función se tiene un while que opera en todos los elementos y los añade a una lista si son distintos a null. retorna la lista una vez se verifican todos los vértices.


**main:** el main asume la posición del "deck.csv" puesto que pide que se encuentre en la raiz del programa, si no se encuentra, retorna error. Si existe el archivo, se usa cargarDatos para obtener el grafo y de ahì para cada elemento del grafo, se usa "UsarMundoChiquito" para ver con cada carta cuantas posibles combinaciones hay, al usarse sets, se filtran aquellas repetidas. finalmente imprime todos los elementos con un espacio de separación.
