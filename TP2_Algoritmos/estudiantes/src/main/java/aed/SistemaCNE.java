package aed;

public class SistemaCNE {
/*

/pred InvRepresentacion:
        |minombresdistrito|       = |midiputadospordistrito|
        |minombrespartido|        = |miultimamesasdistritos|
        |votosdippordist|         = |minombresdistritos|
        |votostotalesdippordistrito| = |minombresdistritos|
        |votosPresidente|            = |minombrespartido|
        |votostotalesdippordistr| = |midiputadospordistrito|
        |bancardasacadauno| = |minombrespartido| -1
        |bancasyapuestas| = |bancardasacadauno|

    -forall i in Z :: 0 <= i <= |votosdippordist| ==> |votosdippordist[i]| = |minombrespartidos|
    -Todos los elementos de miultimamesasdistritos son distintos unos con otros y estan ordenados de menor a mayor.
    -forall i in Z :: 0 <= i <= |votostotalespordistrito| ==> votostotalespordistrito[i] = sumaDeTodosLosElementosDeLaLista(votosdippordistrito[i]) && sumaDeTodosLosElementosDeLaLista(votosdippordistrito[i]) = votostotalespordistrito[i]
    -La suma de todos los elementos de todas las listas de votosdippordist es igual a votostotalespresidencial y viceversa.
    -todos elementos de los votosPresidente son mayores a 0
    -La suma de los elementos de votosPresidentes es menor o igual a votostotalespresidencial.
    -forall i in Z :: 0<= i <= |VotosPartido| ==> 0 <= VotosPartido[i]_1 <= sumaDelIndice_i_DeTodasLasListas(i , votosdipppordist)

    - En votospresidente y votosdippordist todos los elementos son mayores o iguales a 0
    -votosprimero es el mayor elemento de votosPresidente y posicionprimero es la posicion de este.
    -votossegundo es el segundo mayor elemento de votospresidente.
    -las suma de todos los elementos de votos presidente es igual a la suma de todos los elemtnos de votostotalesdippordistr, votostotalesdippordistrito,votosdippordist.
    -la suma de todos los elementos de votosdippordistHeap es igual a la suma de todos los elementos de votostotalesdippordist menos la ultima columna de cada fila de votostotalesdippordist
    - En votosdippordist, en cualquier distrito del rango, todos los elementos estan en la primera posicion de un nodo de votosdippordistHeap del mismo distrito, menos la ultima posicion, excepto si el elemento representa menos del 3% de votostotalesdippordist
    - En votospordippordistHeap, en cualquier distrito del rango, en cualquier nodo su primera posicion es igual a algun elemento de votosdippordist en un mismo distrito, y su segunda posicion es igual a la posicion de ese mismo elemento.
   excepto si el valor es -1
    - en votospordipdistHeap, en cualquier distrito del rango, su size es igual a |votosdipordist|-1
    - En votospordippordistHeap, en cualquier distrito del rango, El elemento de la pos 0 de p su primera posicion es mayor al primer elemento de la posicion 1 y 2
    - En votospordippordistHeap, en cualquier distrito del rango, la primera posicion de cualquier nodo del heap es mayor a la posicion 2k+1 y 2k+2 del heap en un  si es que existe
    - En bancardasacadauno, en cualquier distrito del rango, todos los elementos son mayores o iguales a 0, y la suma de todos los elementos es menor o igual a la de midiputadospordistrioto en ese distrito dado
    - En bancasyapuestas, en cualquier distrito del rango, es true si la cantidad de elementos de bancardasacadauno es mayor a 0, sino es false




*/

    public class VotosPartido{
        private int presidente;
        private int diputados;
        VotosPartido(int presidente, int diputados){this.presidente = presidente; this.diputados = diputados;}
        public int votosPresidente(){return presidente;}
        public int votosDiputados(){return diputados;}

    }

    private String[] minombredistritos;
    private int[] midiputadospordistrito;
    private String[] minombrespartidos;
    private int[] miultimamesasdistritos;

    private int[][] votosdippordist;
    private HeapDiputados[] votosdippordistheap;

    private int[][] bancardasacadauno;
    private boolean[] bancasyapuestas;

    private int votosprimero=0;
    private int partidoprimero;

    private int votossegundo=0;

    private int[] votostotalesdippordist;
    private int votostotalespresidencial = 0;

    private int[] votosPresidente;

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        minombredistritos= nombresDistritos;
        midiputadospordistrito = diputadosPorDistrito;
        minombrespartidos = nombresPartidos;
        miultimamesasdistritos = ultimasMesasDistritos;

        votosPresidente = new int[nombresPartidos.length]; // O(P)

        votosdippordist = new int[nombresDistritos.length][nombresPartidos.length]; // O(D*P)
        votosdippordistheap = new HeapDiputados[nombresDistritos.length]; // O(D)
        votostotalesdippordist=new int[nombresDistritos.length]; // O(D)
        bancardasacadauno = new int[nombresDistritos.length][nombresPartidos.length-1]; // O(D*P)
        bancasyapuestas = new boolean[nombresDistritos.length]; // O(D)
        // O(P) + O(D*P) + O(D) + O(D) + O(D*P) + O(D) + O(D) = O(D*P)
    }

    public String nombrePartido(int idPartido) {
        return minombrespartidos[idPartido]; //O(1)
    }

    public String nombreDistrito(int idDistrito) {
        return minombredistritos[idDistrito]; //O(1)
    }

    public int diputadosEnDisputa(int idDistrito) {
        return midiputadospordistrito[idDistrito]; //O(1)
    }

    public String distritoDeMesa(int idMesa){
        int low=0;
        int high= minombredistritos.length-1;
        int mipunto = high / 2 ;

        while (true){ // O(log n), busqueda binaria
            if(miultimamesasdistritos[mipunto]==idMesa){
                return minombredistritos[mipunto+1];
            }
            if(miultimamesasdistritos[mipunto] < idMesa){
                if(miultimamesasdistritos[mipunto+1] > idMesa){
                    return minombredistritos[mipunto+1];
                }
                else{
                    low=mipunto;
                    mipunto = (high+ low) /2;
                }
            }
            else if(miultimamesasdistritos[mipunto] > idMesa){
                if(mipunto-1<0){
                    return minombredistritos[mipunto];
                }
                else if(miultimamesasdistritos[mipunto-1] < idMesa){
                    return minombredistritos[mipunto];
                }
                else{
                    high=mipunto;
                    mipunto = (high+ low) /2;
                }
            }
        }
        // O(log n), busqueda binaria
        
    }
    private int verdistrito(int idMesa){
        int low=0;
        int high= minombredistritos.length-1;
        int mipunto = high / 2 ;

        while (true){ // O(log n), busqueda binaria
            if(miultimamesasdistritos[mipunto]==idMesa){
                return mipunto+1;
            }
            if(miultimamesasdistritos[mipunto] < idMesa){
                if(miultimamesasdistritos[mipunto+1] > idMesa){
                    return mipunto+1;
                }
                else{
                    low=mipunto;
                    mipunto = (high+ low) /2;
                }
            }
            else if(miultimamesasdistritos[mipunto] > idMesa){
                if(mipunto-1<0){
                    return mipunto;
                }
                else if(miultimamesasdistritos[mipunto-1] < idMesa){
                    return mipunto;
                }
                else{
                    high=mipunto;
                    mipunto = (high+ low) /2;
                }
            }
        }
        // O(log n), busqueda binaria
    }

    private int[] votosCumplenUmbral(int[] votosPorDist, int votosTotales) {
        int[] resultado = new int[votosPorDist.length];
        System.arraycopy(votosPorDist, 0, resultado, 0, votosPorDist.length);
        for (int i = 0; i < votosPorDist.length-1; i++) {
            if (resultado[i] < votosTotales / 100 * 3)
                resultado[i] = -1;
        }
        return resultado;
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        int distrito = verdistrito(idMesa); // O(log D)
        bancasyapuestas[distrito]=false;


            for(int i=0;i < actaMesa.length ; i++){ // O(P), lo de adentro es todo O(1)
                votosPresidente[i] = votosPresidente[i] + actaMesa[i].votosPresidente();
                votostotalespresidencial = votostotalespresidencial + actaMesa[i].votosPresidente();
                votosdippordist[distrito][i] = votosdippordist[distrito][i] +actaMesa[i].votosDiputados();
                votostotalesdippordist[distrito] += actaMesa[i].votosDiputados();
                if(i != actaMesa.length-1) {
                    if (votosPresidente[i]>votosprimero){

                        if(i!=partidoprimero){
                            votossegundo=votosprimero;
                        }
                        votosprimero=votosPresidente[i];
                        partidoprimero= i;
                    } else if (votosPresidente[i]>votossegundo && i!=partidoprimero) {
                        votossegundo=votosPresidente[i];
                    }
                }

        }

        int[] votosCumplenUmbral = votosCumplenUmbral(votosdippordist[distrito], votostotalesdippordist[distrito]); //O(P)
        
        votosdippordistheap[distrito] = new HeapDiputados(votosCumplenUmbral); // O(P) por heapify
        // O(P) + O(P) + O(P) + O(log D) = O(P) + O(log D)
    }

    public int votosPresidenciales(int idPartido) {
        return votosPresidente[idPartido];// O(1)
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        return votosdippordist[idDistrito][idPartido];// O(1)
    }

    public int[] resultadosDiputados(int idDistrito){
        if (bancasyapuestas[idDistrito]){
            return bancardasacadauno[idDistrito];// O(1)
        }
        bancardasacadauno[idDistrito] = new int[minombrespartidos.length-1];
        int bancasarepartir = midiputadospordistrito[idDistrito];
        while(bancasarepartir>0){ // O(Dd)
            bancasyapuestas[idDistrito]=true;
            Nodo partidoconmasvotos=votosdippordistheap[idDistrito].desencolar(); // O(log P)
            //votos totales por dist 
            bancardasacadauno[idDistrito][partidoconmasvotos.partido]+=1;
            votosdippordistheap[idDistrito].encolar(votosdippordist[idDistrito][partidoconmasvotos.partido]/(1+bancardasacadauno[idDistrito][partidoconmasvotos.partido]), partidoconmasvotos.partido);
            // O(log P)
            bancasarepartir--;
        }
        // O(Dd) * (O(log P) + O(log P)) = O(Dd * log P)
        return bancardasacadauno[idDistrito];

    }

    public boolean hayBallotage(){ // O(1)
        double porcentajePrimero = (votosprimero * 100.0) / votostotalespresidencial;
        double porcentajeSegundo = (votossegundo * 100.0) / votostotalespresidencial;
        if(porcentajePrimero >= 45) {
            return false;
        }
        if (porcentajePrimero >= 40 ) {
            if (porcentajePrimero - porcentajeSegundo >10) {
                return false;
            }
        }
        return true;
    }
}
