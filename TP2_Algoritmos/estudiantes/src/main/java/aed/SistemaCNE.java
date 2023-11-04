package aed;
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

    private class avl{
        
    }

    private avl nombrespartidos;

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        minombredistritos= nombresDistritos;
        midiputadospordistrito = diputadosPorDistrito;
        minombrespartidos = nombresPartidos;
        miultimamesasdistritos = ultimasMesasDistritos;
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

    public String distritoDeMesa(int idMesa) {

















        
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int votosPresidenciales(int idPartido) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int[] resultadosDiputados(int idDistrito){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public boolean hayBallotage(){
        throw new UnsupportedOperationException("No implementada aun");
    }
}

