
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rolando mamani salas
 */
public final class Tablero {

    Ficha[][] tablero;
    private int numeroFichas;
    Ficha ficha;
    ArrayList<String> historial = new ArrayList<String>();

    //OBJETIVO 1 
    /*   Como primer objetivo debemos generar la siguiente estructura:
            1 2 3 4
            5 6 7 8
            9 10 11 12
            13 14 15 0
            Figura 1.
        El caracter 0 representa el huequito. Cada fila está representada por una lista.
        Adicionalmente se necesita un elemento que permita definir la posición del huequito. Escribe el
        código necesario para representar dicha estructura.
        Para comprobar que la estructura inicial es la apropiada, se necesita crear un método que devuelva
        constantemente el tablero ordenado y formateado, como se muestra en la figura 1.
     */
    public Tablero(int n) {
        if (verificarMatrizPerfecta(n)) {
            numeroFichas = (int) Math.sqrt(n);
            armarTalero();
        }
    }

    public void armarTalero() {
        tablero = new Ficha[numeroFichas][numeroFichas];
        int index = 1;
        for (int x = 0; x < numeroFichas; x++) {
            for (int y = 0; y < numeroFichas; y++) {
                tablero[x][y] = new Ficha(x, y, index);
                index++;
            }
        }
    }

    public boolean verificarMatrizPerfecta(int n) {
        double sq = Math.sqrt(n);
        return ((sq - Math.floor(sq)) == 0);
    }

    /*Objetivo 2. Identificar Posibles Movidas
        Cuáles son las posiciones donde se puede mover el huequito?
        Dada una posición inicial del huequito, crearemos un método que permita identificar las posibles
        posiciones, en las cuales puede terminar el huequito, considerando que puede moverse una
        posición a la vez.
        Por ejemplo, en el siguiente tablero, el huequito está en la fila 0, columna 1:
                1 0 3 4
                5 6 7 8
                9 10 11 12
                13 14 15 16
        Las posibles movidas serían:
                1. fila 0, columna 0
                2. fila 0, columna 2
                3. fila 1, columna 1
        Eso significa que el método debería devolver una colección de posiciones.
    
     */
    public String posiblesMovimientosFicha0() {                       //Ejemplo de clase  public ArrayList identificarMovidasDelHuequito(){
        String resultado = "";                                          //            calculas las posibles movidas del huquito
        Ficha[] movimientos = mostrarFichasVecinas(ficha);              //             cada posible movida se anade al array resultado
        for (Ficha p : movimientos) {
            if (p != null) {
                resultado += p.toStringPosicion() + "\n";
            }
        }
        return resultado;
    }

    public String[] posiblesMovimientosString() {                    // Cambiar este metodo 
        String[] resultado;
        int index = 0;
        Ficha[] movimientos = mostrarFichasVecinas(ficha);
        for (Ficha p : movimientos) {
            if (p != null) {
                index++;
            }
        }
        resultado = new String[index];
        index = 0;
        for (int i = 0; i < 4; i++) {
            if (movimientos[i] != null) {
                resultado[index] = direccionString(i);
                index++;
            }
        }
        return resultado;
    }

    /* Objetivo 3. Aplicar una Movida
        Dado una lista de posibles movidas y la posición del huequito, se requiere aplicar una movida
        cualquiera, de modo que el resultado es el tablero con la movida aplicada.
        Debes tener en cuenta que al aplicar una movida, no se debe salir del tablero.
     */
    public void realizarMovimiento(String direccion) {
        Ficha[] movimientosLegales = mostrarFichasVecinas(ficha);
        switch (direccion) {
            case "Arriba":
                if (movimientosLegales[0] != null) {
                    intercambiarFichas(movimientosLegales[0], ficha);
                    historial.add(direccion);
                }
                break;
            case "Izquierda":
                if (movimientosLegales[1] != null) {
                    intercambiarFichas(movimientosLegales[1], ficha);
                    historial.add(direccion);
                }
                break;
            case "Derecha":
                if (movimientosLegales[2] != null) {
                    intercambiarFichas(movimientosLegales[2], ficha);
                    historial.add(direccion);
                }
                break;
            case "Abajo":
                if (movimientosLegales[3] != null) {
                    intercambiarFichas(movimientosLegales[3], ficha);
                    historial.add(direccion);
                }
                break;
        }
    }

    public void ingresarFicha(Ficha ingreso) {
        ingreso = tablero[ingreso.getCoordenadaX()][ingreso.getCoordenadaY()];
        if (ingreso.getValor() == 0) {
            ficha = ingreso;
        }
    }

    public void intercambiarFichas(Ficha fichaDestino, Ficha fichaOrigen) {
        fichaOrigen.setValor(fichaDestino.getValor());
        fichaDestino.setValor(0);
        ingresarFicha(fichaDestino);
        ingresarFicha(fichaOrigen);
        if (fichaDestino.getValor() == 0) {
            ficha = fichaDestino;
        }
    }

    public Ficha[] mostrarFichasVecinas(Ficha actual) {
        Ficha[] movimientos = new Ficha[4];
        if (movimientoValido(actual.getCoordenadaX() - 1, actual.getCoordenadaY())) {
            movimientos[0] = tablero[actual.getCoordenadaX() - 1][actual.getCoordenadaY()];
        }
        if (movimientoValido(actual.getCoordenadaX(), actual.getCoordenadaY() - 1)) {
            movimientos[1] = tablero[actual.getCoordenadaX()][actual.getCoordenadaY() - 1];
        }
        if (movimientoValido(actual.getCoordenadaX(), actual.getCoordenadaY() + 1)) {
            movimientos[2] = tablero[actual.getCoordenadaX()][actual.getCoordenadaY() + 1];
        }
        if (movimientoValido(actual.getCoordenadaX() + 1, actual.getCoordenadaY())) {
            movimientos[3] = tablero[actual.getCoordenadaX() + 1][actual.getCoordenadaY()];
        }
        return movimientos;
    }

    public boolean movimientoValido(int cordenadaX, int cordenadaY) {
        if (cordenadaX < 0 || cordenadaX >= numeroFichas) {
            return false;
        }
        if (cordenadaY < 0 || cordenadaY >= numeroFichas) {
            return false;
        }
        return true;
    }

    public String direccionString(int direccion) {
        String resultado = "";
        switch (direccion) {
            case 0:
                resultado = "Arriba";
                break;
            case 1:
                resultado = "Izquierda";
                break;
            case 2:
                resultado = "Derecha";
                break;
            case 3:
                resultado = "Abajo";
                break;
        }
        return resultado;
    }

    //Formato
    @Override
    public String toString() {
        String espacio = " ";
        String resultado = "";
        for (int x = 0; x < numeroFichas; x++) {
            for (int y = 0; y < numeroFichas; y++) {
                Ficha temp = tablero[x][y];
                if (temp.getValor() < 10) {
                    resultado += espacio;
                }
                resultado += "" + temp.getValor();
                resultado += espacio;
            }
            resultado += "\n";
        }
        return resultado;
    }

    /*Objetivo 4
        -- Desarmar tablero de mandera aleatoria
        -- Crear un metodo que permita generar una lista de movidas aleatorias 
            el resultado de este metodo debe ser una lista de movidas 
            que se apliquen al tablero
        --Los parametros son cantidas de movidas y posicion inicial
    
    public DevolverLista movidasaleatorias(cantidasMovidas , posicionInicial)
        Lista lisraDeMovidasAleatorias = new Array.......
        lista
        return lisraDeMovidasAleatorias;
     */
    public String[] MovidasAleatorias(int cantidadMovimientos, Ficha posicionInicial) {
        String[] movidas = new String[cantidadMovimientos];
        ingresarFicha(posicionInicial);
        Ficha[] vecinos = mostrarFichasVecinas(ficha);
        int posicionAleatoria;
        for (int i = 0; i < cantidadMovimientos; i++) {
            Ficha temporal;
            do {
                posicionAleatoria = numeroAleatorio(0, 16);
                temporal = vecinos[posicionAleatoria];
            } while (temporal == null);
            String direccion = direccionString(posicionAleatoria);
            movidas[i] = direccion;
            vecinos = mostrarFichasVecinas(temporal);
        }
        return movidas;
    }
    //castear este metodo por que math debuelve double
    public int numeroAleatorio(int numeroMinimo, int numeroMaximo) {
        int numeroValido = (numeroMaximo - numeroMinimo) + 1;
        return (int) (Math.random() * numeroValido) + numeroMinimo;
    }

}