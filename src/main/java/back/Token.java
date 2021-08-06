package back;
public class Token {

    private String cadena;
    private TipoToken tipo;
    
    public Token(String cadena, TipoToken tipo){
        this.cadena = cadena;
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return tipo.getDescripcion() + " " + this.cadena;
    }
}
