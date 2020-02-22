
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
public class Tablero {

    Ficha[][] tablero;
    private int tamano;
    Ficha ficha;
    ArrayList<String> historial = new ArrayList<String>();

    public Tablero(int n) {
        if (verificarMatrizPerfecta(n)) {
            tamano = (int) Math.sqrt(n);
            armarTaleroRompecabeza();
        }
    }
        private void armarTaleroRompecabeza() {
        tablero = new Ficha[tamano][tamano];
        int index = 1;
        for (int x = 0; x < tamano; x++) {
            for (int y = 0; y < tamano; y++) {
                tablero[x][y] = new Ficha(x, y, index);
                index++;
            }
        }
    }
    @Override
    public String toString() {
        String espacio = " ";
        String res = "";
        for (int x = 0; x < tamano; x++) {
            for (int y = 0; y < tamano; y++) {
                Ficha temp = tablero[x][y];
                if (temp.getValor() < 10) {
                    res += espacio;
                }
                res += "" + temp.getValor();
                res += espacio;
            }
            res += "\n";
        }
        return res;
    }
    public String posiblesMovimientosFicha0() {
        String res = "";
        Ficha[] movimientos = mostrarFichasVecinas(ficha);
        for (Ficha p : movimientos) {
            if (p != null) {
                res += p.toStringPosicion() + "\n";
            }
        }
        return res;
    }
    public String[] posiblesMovimientosString() {
        String[] res;
        int index = 0;
        Ficha[] movimientos = mostrarFichasVecinas(ficha);
        for (Ficha p : movimientos) {
            if (p != null) {
                index++;
            }
        }
        res = new String[index];
        index = 0;
        for (int i = 0; i < 4; i++) {
            if (movimientos[i] != null) {
                res[index] = direccionString(i);
                index++;
            }
        }
        return res;
    }
    public void realizarMovimiento(String Direccion) {
        Ficha[] movimientosLegales = mostrarFichasVecinas(ficha);
        switch (Direccion) {
            case "Arriba":
                if (movimientosLegales[0] != null) {
                    intercambiarFichas(movimientosLegales[0], ficha);
                    historial.add(Direccion);
                }
                break;
            case "Izquierda":
                if (movimientosLegales[1] != null) {
                    intercambiarFichas(movimientosLegales[1], ficha);
                    historial.add(Direccion);
                }
                break;
            case "Derecha":
                if (movimientosLegales[2] != null) {
                    intercambiarFichas(movimientosLegales[2], ficha);
                    historial.add(Direccion);
                }
                break;
            case "Abajo":
                if (movimientosLegales[3] != null) {
                    intercambiarFichas(movimientosLegales[3], ficha);
                    historial.add(Direccion);
                }
                break;
        }

    }
    public void ingresarFicha(Ficha ingreso) {
        tablero[ingreso.getCoordenadaX()][ingreso.getCoordenadaY()] = ingreso;
        if (ingreso.getValor() == 0) {
            ficha = ingreso;
        }
    }
    private void intercambiarFichas(Ficha fichaDestino, Ficha fichaOrigen) {
        fichaOrigen.setValor(fichaDestino.getValor());
        fichaDestino.setValor(0);
        ingresarFicha(fichaDestino);
        ingresarFicha(fichaOrigen);
        if (fichaDestino.getValor() == 0) {
            ficha = fichaDestino;
        }
    }
    private Ficha[] mostrarFichasVecinas(Ficha actual) {
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
    private boolean movimientoValido(int x, int y) {
        if (x < 0 || x >= tamano) {
            return false;
        }
        if (y < 0 || y >= tamano) {
            return false;
        }
        return true;
    }
    public boolean verificarMatrizPerfecta(int n) {
        double sq = Math.sqrt(n);
        return ((sq - Math.floor(sq)) == 0);
    }



    //Segun una direccion literal segun un valor entero   
    private String direccionString(int n) {
        String resultado = "";
        switch (n) {
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
}
