class CartaMostro(val nombre: String, val nivel: Int, val atributo: String, val poder: Int){
    init{
        require(nivel in 1..12) 
        require(poder % 50 == 0)
        val atributos = listOf("AGUA", "FUEGO", "VIENTO", "TIERRA", "LUZ", "OSCURIDAD", "DIVINO")
        require(atributo.uppercase() in atributos)
    }
    override fun toString(): String {
    	return "${nombre}"
    }
    fun obtenerNivel(): Int{
        return nivel
    }
    fun obtenerAtributo(): String{
        return atributo
    }
    fun obtenerPoder(): Int{
        return poder
    }

}
