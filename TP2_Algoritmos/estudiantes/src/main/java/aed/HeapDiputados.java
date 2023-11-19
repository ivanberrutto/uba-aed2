package aed;
public class HeapDiputados {
    Nodo[] heap;
    int quedistrito;
    int size;



    public HeapDiputados(int[] arr) {
        this.size = arr.length-1;
        this.heap = new Nodo[this.size];
        
        /* 
        this.heap = new int[size];
        System.arraycopy(arr, 0, heap, 0, size);
        */
        //hago el "array" pero con nodos con su correspondiente votos y posicion
        for(int j=0;j < size;j++){
            heap[j] = new Nodo(arr[j],j);
        }
        // Hago el array to heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && heap[left].votos > heap[largest].votos) {
            largest = left;
        }

        if (right < size && heap[right].votos > heap[largest].votos) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        Nodo temp = new Nodo(heap[i].votos,heap[i].partido);
        heap[i] = heap[j];
        heap[j] = temp;
    }
    // public void encolar(int votos,int partido){
    //     if (size == heap.length) {
    //         //Significa que esta lleno, entonces para encolar deberia agregar mas espacio
    //         //No obstante en nuestro programa nunca deberia pasar esto
    //         return;
    //     }

    //     // Agregar el nuevo elemento al final del heap
    //     heap[size].votos = votos;
    //     heap[size].partido = partido;
    //     size++;
    //     // Ajustar el heap después de agregar el nuevo elemento
    //     int actual = size - 1;
    //     int padreactual = (actual - 1) / 2;

    //     while (actual > 0 && heap[actual].votos > heap[padreactual].votos) {
    //         swap(actual, padreactual);
    //         actual = padreactual;
    //         padreactual = (actual - 1) / 2;
    //     }
    // }

    public void siftUp(int pos) {
        if (pos == 0)
            return;
        
        int parent = (pos - 1) / 2;
        if (heap[pos].votos > heap[parent].votos) {
            swap(pos, parent);
            siftUp(parent);
        }
    }


    public void encolar(int votos, int partido) {
        if (size >= heap.length) {
            throw new RuntimeException("Heap is full");
        }
        heap[size].partido = partido;
        heap[size].votos = votos;
        size++;
        siftUp(size-1);
    }

    public Nodo desencolar(){
        if (size == 0) {
            // esto nunca deberia pasar en nuestro codigo
        }

        // Guardar los votos ypos del máximo antes de desencolar
        int maxvotos = heap[0].votos;
        int maxpartido = heap[0].partido;

        // Mover el último elemento al inicio y reducir el tamaño del heap
        heap[0] = heap[size - 1];
        size--;
        //heap=heap.;

        // Ajustar el heap después de desencolar
        heapify(0);

        return new Nodo(maxvotos,maxpartido);
    }
    /* 
    private int[] arr(){
        return heap;
    }
    */

    public String toString() {
        String result = "";
        for (int i=0;i<size;i++){
            result += heap[i].votos + " ";
        }
        return result;
    }

    public int size(){
        return size;
    }

    public int votosdelmayor(){
        return heap[0].votos;
    }
}