package back;

public enum TipoToken {
    ENTERO("ENTERO"), IDENTIFICADOR("IDENTIFICADOR"), SIGNO("SIGNO"), ERROR("ERROR"), DECIMAL("DECIMAL"), DEFAULT("DEFAULT");
    
    private final String descripcion;

    private TipoToken(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
}
