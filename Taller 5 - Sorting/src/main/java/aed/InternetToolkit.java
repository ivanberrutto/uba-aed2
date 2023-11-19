package aed;

import java.util.Arrays;
import java.util.PriorityQueue;

public class InternetToolkit {
    public InternetToolkit() {
    }


    public class MaxHeap {
        private Router[] heap;
        private int size;

        public MaxHeap(Router[] array) {
            this.heap = Arrays.copyOf(array, array.length);
            this.size = array.length;
            buildHeap();
        }

        private void buildHeap() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                heapifyDown(i);
            }
        }

        public Router dequeue() {
            if (size <= 0) {
                throw new IllegalStateException("Heap is empty");
            }

            Router root = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);

            return root;
        }

        private void heapifyDown(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap[left].compareTo(heap[largest]) > 0) {
                largest = left;
            }

            if (right < size && heap[right].compareTo(heap[largest]) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                heapifyDown(largest);
            }
        }

        private void swap(int i, int j) {
            Router temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private Router[] devolverarray(){
            return heap;
        }

        }

    public Fragment[] tcpReorder(Fragment[] fragments) {
    //merge sort
    mergeSort(fragments, 0, fragments.length - 1);
    return fragments;
    
    

    }

    private void mergeSort(Fragment[] fragments, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2; // Evita desbordamiento de enteros

            mergeSort(fragments, low, mid);
            mergeSort(fragments, mid + 1, high);

            // Solo realiza la fusión si el último elemento del primer subarreglo
            // es mayor que el primer elemento del segundo subarreglo.
            if (fragments[mid].compareTo(fragments[mid + 1]) > 0) {
                merge(fragments, low, mid, high);
            }
        }
    }
    private void merge(Fragment[] fragments, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        Fragment[] leftArray = new Fragment[n1];
        Fragment[] rightArray = new Fragment[n2];

        // Copia los datos a los subarreglos temporales
        System.arraycopy(fragments, low, leftArray, 0, n1);
        System.arraycopy(fragments, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                fragments[k] = leftArray[i];
                i++;
            } else {
                fragments[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copia los elementos restantes de leftArray (si los hay)
        while (i < n1) {
            fragments[k] = leftArray[i];
            i++;
            k++;
        }

        // Copia los elementos restantes de rightArray (si los hay)
        while (j < n2) {
            fragments[k] = rightArray[j];
            j++;
            k++;
        }
    }

    

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        /*
        MaxHeap miheap= new MaxHeap(routers);

        Router[] arrayadevolver= new Router[routers.length];
        int i = k;
        int pos = 0;
        while(i>0 && pos < routers.length){
            arrayadevolver[0]=miheap.dequeue();
            if(arrayadevolver[0].getTrafico()>umbral){
                i--;
            }
            pos++;
        }

        return arrayadevolver;

         */
        // Filtrar routers que superan el umbral
        // Filtrar routers que superan el umbral
        Router[] filteredRouters = Arrays.stream(routers)
                .filter(router -> router.getTrafico() > umbral)
                .toArray(Router[]::new);

        // Construir un Max Heap con los routers filtrados
        MaxHeap maxHeap = new MaxHeap(filteredRouters);

        // Extraer los k routers con mayor tráfico del heap
        int resultSize = Math.min(k, maxHeap.size);
        Router[] result = new Router[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = maxHeap.dequeue();
        }

        return result;

    }

    private static PriorityQueue<Router> arrayToHeap(Router[] routers) {
        PriorityQueue<Router> heap = new PriorityQueue<>(routers.length);
        for (Router router : routers) {
            heap.add(router);
        }
        return heap;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        if (ipv4 == null || ipv4.length == 0) {
            return new IPv4Address[0];
        }

        IPv4Address[] addresses = new IPv4Address[ipv4.length];

        // Convertir direcciones IPv4 a objetos IPv4Address
        for (int i = 0; i < ipv4.length; i++) {
            addresses[i] = new IPv4Address(ipv4[i]);
        }

        // Ordenar direcciones IPv4 utilizando el algoritmo de ordenamiento de burbuja
        for (int i = 0; i < addresses.length - 1; i++) {
            for (int j = 0; j < addresses.length - i - 1; j++) {
                if (compareIPv4(addresses[j], addresses[j + 1]) > 0) {
                    // Intercambiar direcciones si están en el orden incorrecto
                    IPv4Address temp = addresses[j];
                    addresses[j] = addresses[j + 1];
                    addresses[j + 1] = temp;
                }
            }
        }

        return addresses;
    }
    private static int compareIPv4(IPv4Address a, IPv4Address b) {
        for (int i = 0; i < 4; i++) {
            int octetComparison = Integer.compare(a.getOctet(i), b.getOctet(i));
            if (octetComparison != 0) {
                return octetComparison;
            }
        }
        return 0; // Las direcciones son iguales
    }

}
