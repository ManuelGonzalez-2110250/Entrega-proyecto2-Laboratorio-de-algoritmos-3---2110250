import java.io.File
import java.io.BufferedReader
fun sePuedeConectar(carta1: CartaMostro, carta2: CartaMostro ): Boolean{
    var MedidorConexiones: Int = 0
    if(carta1.obtenerNivel() == carta2.obtenerNivel()){
        MedidorConexiones++
    }
    if(carta1.obtenerPoder() == carta2.obtenerPoder()){
        MedidorConexiones++
    }
    if(carta1.obtenerAtributo() == carta2.obtenerAtributo()){
        MedidorConexiones++
    }
    //si no tiene 1 conexion exactamente, retorna false
    if(MedidorConexiones != 1) return false
    return true
}
fun ConectarMostro(Grafo: Grafo<CartaMostro>, carta: CartaMostro ){
    //lista de elementos del grafo
    val elementosGrafo: List<CartaMostro> = Grafo.elementos()
    for (elem in elementosGrafo){
        //si hay una sola característica común
        if(sePuedeConectar(carta, elem)){
            //se conecta
            Grafo.conectar(carta, elem)
        }
    }

}

fun cargarDatos(file: File): Grafo<CartaMostro>{
    val bufferedReader: BufferedReader = file.bufferedReader() 
    var linea: String? = bufferedReader.readLine()
    var Datos : Grafo<CartaMostro> = ListaAdyacenciaGrafo()
    var nombres: Set<String> = mutableSetOf<String>()
    if (linea == null) return Datos
    var elementos: List<String> = linea.split(",".toRegex())

    while (linea != null) {
        if(elementos.size != 4){
            println("Error: formato de deck.csv no adecuado")
            return ListaAdyacenciaGrafo<CartaMostro>()
        }
        val nomMostro: String = elementos.get(0)
        val nivel: Int = elementos.get(1).toInt()
        val atributo: String = elementos.get(2)
        val poder: Int = elementos.get(3).toInt()
        val carta: CartaMostro = CartaMostro(nomMostro, nivel, atributo, poder)
        if(Datos.contiene(carta)){ //si el elemento ya estaba NOTA: 
        //modificar esta parte del programa con la respuesta a la duda "se pueden elementos repetidos?"
            linea = bufferedReader.readLine()
            if(linea == null){
	    	return Datos
	    }
	    elementos = linea.split(",".toRegex()) 
        }
        else if(nombres.contains(nomMostro)){ //verificar la unicidad de los nombres
             println("Error, $nomMostro está repetido")
             return ListaAdyacenciaGrafo<CartaMostro>()
        }else{  //si el elemento no está y no tiene nombre repetido
            Datos.agregarVertice(carta)
            ConectarMostro(Datos, carta)
	    linea = bufferedReader.readLine()
	    if(linea == null){
                continue
	    }
	    elementos = linea.split(",".toRegex()) 
	}
    }
    //si el tamaño no es el adecuado
    if(Datos.tamano() < 0 || Datos.tamano() > 60){
         println("Error, el tamaño del mazo no está entre 40 y 60 cartas")
         return ListaAdyacenciaGrafo<CartaMostro>()
    }
    return Datos
}
fun UsarMundoChiquito(grafo: Grafo<CartaMostro> ,v: CartaMostro ,i: Int, solucion: MutableList<CartaMostro>, Combinaciones: MutableSet<List<CartaMostro>>): Boolean{
    if(i == 3){
        //si ya estaba retorna false
        if(Combinaciones.contains(solucion.toList())) return false
        //añadir la solucion a la lista de soluciones
        Combinaciones.add(solucion.toList()) 
        return true
    }
    val listaSucesores = grafo.obtenerArcosSalida(v)
    for(sucesor in listaSucesores){
        //se asigna el sucesor seleccionado a la solucion
        solucion.add(sucesor)
        //se itera recursivamente con i+1
        UsarMundoChiquito(grafo, sucesor,i+1,solucion,Combinaciones )
        //backtracking
        solucion.remove(sucesor)
    }
    return false
    
}

fun main(){
    //la ruta está "hardcodeada" en el archivo, por lo que si se cambia de nombre o de lugar el programa falla
    val ruta: String = "deck.csv"
    val file: File = File(ruta)
    if(!file.exists()){
         println("Error: No existe deck.csv en la carpeta raiz, intente de nuevo")
         return
    }
    //se crea el grafom cuyas conexiones
    val grafoEntrada: Grafo<CartaMostro> = cargarDatos(file)
    if(grafoEntrada == ListaAdyacenciaGrafo<CartaMostro>()){
        println("Error")
        return
    }else{
        //inicializacion variables
        var i = 0
        var solLocal: MutableList<CartaMostro> = mutableListOf<CartaMostro>()
        var listaSoluciones = mutableSetOf<List<CartaMostro>>()
        //se obtienen los elementos del arreglo
        val listaElementosGrafo = grafoEntrada.elementos()
        for(elem in listaElementosGrafo ){
            //se usa mundo chiquito para todo elemento del grafo
            UsarMundoChiquito(grafoEntrada,elem,i,solLocal,listaSoluciones)
        }
        listaSoluciones.forEach{println(it.joinToString(" "))}
    }
}
