package aed;

import java.util.ArrayList;

public class SistemaCNE {
    // Completar atributos privados

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

    //private int[] votospresidenciales;

    private ArrayList<Integer> mesasregistradas; // esta mal

    private int[][] votosdippordist;

    private int votostotalespresidencial = 0;

    private VotosPartido[] misvotos;
    private class avl{
        
    }

    private avl nombrespartidos;

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        minombredistritos= nombresDistritos;
        midiputadospordistrito = diputadosPorDistrito;
        minombrespartidos = nombresPartidos;
        miultimamesasdistritos = ultimasMesasDistritos;

        misvotos = new VotosPartido[nombresPartidos.length];
        mesasregistradas = new ArrayList<>();
        votosdippordist = new int[nombresDistritos.length][nombresPartidos.length];

    }

    public String nombrePartido(int idPartido) {
        return minombrespartidos[idPartido];
    }

    public String nombreDistrito(int idDistrito) {
        return minombredistritos[idDistrito];
    }

    public int diputadosEnDisputa(int idDistrito) {
        return midiputadospordistrito[idDistrito];
    }

    public String distritoDeMesa(int idMesa){
        int low=0;
        int high= minombredistritos.length-1;
        int mipunto = high / 2 ;

        while (true){
            if(miultimamesasdistritos[mipunto]==idMesa){
                return minombredistritos[mipunto+1];
            }
            if(miultimamesasdistritos[mipunto] < idMesa){
                if(miultimamesasdistritos[mipunto+1] > idMesa){
                    return minombredistritos[mipunto+1];
                }
                else{ // osea digamos es mas grande
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

        
    }
    private int verdistrito(int idMesa){
        int low=0;
        int high= minombredistritos.length-1;
        int mipunto = high / 2 ;

        while (true){
            if(miultimamesasdistritos[mipunto]==idMesa){
                return mipunto+1;
            }
            if(miultimamesasdistritos[mipunto] < idMesa){
                if(miultimamesasdistritos[mipunto+1] > idMesa){
                    return mipunto+1;
                }
                else{ // osea digamos es mas grande
                    low=mipunto;
                    mipunto = (high+ low) /2;
                    continue;
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
                    continue;
                }
            }
        }
    }
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        int distrito = verdistrito(idMesa);

        if(mesasregistradas.isEmpty()){ // osea digamos es la primera mesa registrada
            for(int i=0;i < actaMesa.length ; i++){
                misvotos[i] = new VotosPartido(actaMesa[i].votosPresidente() , actaMesa[i].votosDiputados());
                votostotalespresidencial = votostotalespresidencial + actaMesa[i].votosPresidente();
                votosdippordist[distrito][i] = actaMesa[i].votosDiputados();
            }
        }
        else{
            for(int i=0;i < actaMesa.length ; i++){
                misvotos[i] = new VotosPartido(misvotos[i].votosPresidente()+actaMesa[i].votosPresidente() ,misvotos[i].votosDiputados()+ actaMesa[i].votosDiputados());
                votostotalespresidencial = votostotalespresidencial + actaMesa[i].votosPresidente();
                votosdippordist[distrito][i] = votosdippordist[distrito][i] +actaMesa[i].votosDiputados();
            }
        }

        //tengo que registrar la mesa?
        mesasregistradas.add(idMesa); // hago esta q esta mal
            ;
    }

    public int votosPresidenciales(int idPartido) {
        return misvotos[idPartido].presidente;
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        return votosdippordist[idDistrito][idPartido];
    }

    private int posdelmasvotado(int[] votos){
        int pos=0;
        for(int i=0;i<votos.length-1;i++){
            if(votos[i]>votos[pos]){
                pos=i;
            }
        }
        return pos; // esta mal esto porq es O(P)
    }
    public int[] resultadosDiputados(int idDistrito){
        int bancasarepartir = midiputadospordistrito[idDistrito];
        int[] bancasacadauno = new int[minombrespartidos.length-1];
        int[] votosdeldistoriginal = votosdippordist[idDistrito];
        int[] votosdeldist = votosdippordist[idDistrito];
        int masvotado;

        while(bancasarepartir>0){ // O(D_d)
            // tengo que buscar el q tiene mas votos de forma o(log p) !!
            masvotado= posdelmasvotado(votosdeldist);
            bancasacadauno[masvotado] =bancasacadauno[masvotado] +1;
            votosdeldist[masvotado] = votosdeldistoriginal[masvotado] / (1+bancasacadauno[masvotado]);
            bancasarepartir--;
        }
        // todo esto esta mal porque estoy haciendo aliasing al tocar mis nnuevas variables
        return bancasacadauno;
    }

    public boolean hayBallotage(){
        // votos totales presidenciales
        return true;
    }
}

