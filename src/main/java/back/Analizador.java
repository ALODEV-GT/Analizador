package back;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Analizador {

    private final String cadena;
    private final JTextArea taIdentificados;
    private String token = "";
    private final ArrayList<Token> tokens = new ArrayList<>();
    private TipoToken tipo = TipoToken.DEFAULT;
    private boolean inicioToken = false;

    public Analizador(String cadena, JTextArea taIdentificados) {
        this.cadena = cadena + " ";
        this.taIdentificados = taIdentificados;
    }

    private void imprimirTokens() {
        for (int i = 0; i < tokens.size(); i++) {
            this.taIdentificados.append(tokens.get(i).toString() + "\n");
        }
    }

    public void analizar() {

        String valActual;
        for (int i = 0; i < this.cadena.length(); i++) {
            valActual = this.cadena.substring(i, i + 1);
            evaluarTipo(valActual);
            cambioToken(valActual);
        }

        imprimirTokens();
    }

    public void cambioToken(String valActual) {
        if (valActual.equals(" ") || valActual.equals(",")) {
            if (!this.token.equals("")) {
                Token nuevoToken = new Token(this.token, this.tipo);
                tokens.add(nuevoToken);
                this.tipo = TipoToken.DEFAULT;
            }
            this.token = "";
        } else {
            this.token += valActual;

        }
    }

    public void evaluarTipo(String caracter) {

        if (this.tipo.equals(TipoToken.DEFAULT)) {
            asignarTipoInicio(caracter);
        }

        TipoCaracter tipo = identificarCaracter(caracter);
        if (!tipo.equals(TipoCaracter.SEPARADOR)) {

            if (this.tipo.equals(TipoToken.IDENTIFICADOR) && tipo.equals(TipoCaracter.PUNTO)) {
                this.tipo = TipoToken.ERROR;
            } else if (this.tipo.equals(TipoToken.IDENTIFICADOR) && tipo.equals(TipoCaracter.SIGNO)) {
                this.tipo = TipoToken.ERROR;
            }

        }
    }

    public void asignarTipoInicio(String caracter) {
        TipoCaracter tipo = identificarCaracter(caracter);
        switch (tipo) {
            case LETRA:
                this.tipo = TipoToken.IDENTIFICADOR;
                break;
            case NUMERO:
                this.tipo = TipoToken.ENTERO;
                break;
            case PUNTO:
                this.tipo = TipoToken.ERROR;
                break;
            case SIGNO:
                this.tipo = TipoToken.SIGNO;
                break;
        }
    }

    private TipoCaracter identificarCaracter(String caracter) {
        TipoCaracter tipo = null;
        if (esLetra(caracter) == true) {
            tipo = TipoCaracter.LETRA;
        } else if (esNumero(caracter) == true) {
            tipo = TipoCaracter.NUMERO;
        } else if (esPunto(caracter) == true) {
            tipo = TipoCaracter.PUNTO;
        } else if (esSeparador(caracter) == true) {
            tipo = TipoCaracter.SEPARADOR;
        } else {
            tipo = TipoCaracter.SIGNO;
        }

        return tipo;
    }

    public boolean esLetra(String caracter) {
        boolean existe = false;
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String letra : letras) {
            if (caracter.equalsIgnoreCase(letra)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean esNumero(String caracter) {
        boolean existe = false;
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String numero : numeros) {
            if (caracter.equals(numero)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean esSeparador(String caracter) {
        boolean existe = false;
        if (caracter.equals(" ") || caracter.equals(",")) {
            existe = true;
        }
        return existe;
    }

    public boolean esSigno(String caracter) {
        boolean existe = false;
        String[] signos = {"(", ")", "{", "}", "[", "]"};
        for (String signo : signos) {
            if (caracter.equals(signo)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean esPunto(String caracter) {
        boolean existe = caracter.equals(".");
        return existe;
    }
}
