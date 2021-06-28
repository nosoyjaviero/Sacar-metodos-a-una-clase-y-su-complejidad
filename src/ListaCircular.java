





public class ListaCircular {
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    public Nodo inicio;
    // Puntero que indica el final de la lista o el ultimo nodo.
    public Nodo ultimo;
    // Variable para registrar el tamaño de la lista.
    private int tamanio;
    /**
     * Constructor por defecto.
     */
    public void Lista(){
        inicio = null;
        ultimo = null;
        tamanio = 0;
    }
    /**
     * Consulta si la lista esta vacia.
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
    public boolean esVacia(){
        return inicio == null;
    }
    /**
     * Consulta cuantos elementos (nodos) tiene la lista.
     * @return numero entero entre [0,n] donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getTamanio(){
        return tamanio;
    }

    
    
    public void agregarAlFinal(String valor){
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // De igual forma el ultimo nodo sera el nuevo.
            ultimo = nuevo;
            // Y el puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
        // Caso contrario el nodo se agrega al final de la lista.
        } else{
            // Apuntamos con el ultimo nodo de la lista al nuevo.
            ultimo.setSiguiente(nuevo);
            // Apuntamos con el nuevo nodo al inicio de la lista.
            nuevo.setSiguiente(inicio);
            // Como ahora como el nuevo nodo es el ultimo se actualiza
            // la variable ultimo.
            ultimo = nuevo;
        }
        // Incrementa el contador de tamaño de la lista
        tamanio++;
    }

    
    
    public String getValor(int posicion) throws Exception{
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion<tamanio){
            // Consulta si la posicion es el inicio de la lista.
            if (posicion == 0) {
                // Retorna el valor del inicio de la lista.
                return inicio.getValor();
            }else{
                // Crea una copia de la lista.
                Nodo aux = inicio;
                // Recorre la lista hasta la posición ingresada.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                // Retorna el valor del nodo.
                return aux.getValor();
            }
        // Crea una excepción de Posicion inexistente en la lista.
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    
    
    
    public void eliminar(){
        // Elimina el valor y la referencia a los demas nodos.
        inicio = null;
        // Elimina el valor y la referencia al primer nodo.
        ultimo = null;
        // Reinicia el contador de tamaño de la lista a 0.
        tamanio = 0;
    }  
    
    
    
    
    /**
     * Mustra en pantalla los elementos de la lista.
     */
    public void listar(){
        // Verifica si la lista contiene elementoa.
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            System.out.print("-> ");
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do{
                // Imprime en pantalla el valor del nodo.
                System.out.print(i + ".[ " + aux.getValor() + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.getSiguiente();
                // Incrementa el contador de la posión.
                i++;
                
            }while(aux != inicio);
        }
    }
    
    
    
    
    
    
    
    
       
    /**
     * Obtiene el valor de un nodo en una determinada posición.
     * @param posicion del nodo que se desea obtener su valor.
     * @return un numero entero entre [0,n-1] n = numero de nodos de la lista.
     * @throws Exception
     */
}