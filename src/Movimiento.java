/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rolando mamani salas
 */
public enum Movimiento {

    ARRIBA(0), IZQUIERDA(1), DERECHA(2), ABAJO(3);
    public int valor;

    Movimiento(int valor) {
        this.valor = valor;
    }

    public int getValorMovimiento() {
        return valor;
    }

    public String movimientoString() {
        String movimiento = "";
        switch (valor) {
            case 0:
                movimiento = "Arriba";
                break;
            case 1:
                movimiento = "Izquierda";
                break;
            case 2:
                movimiento = "Derecha";
                break;
            case 3:
                movimiento = "Abajo";
                break;
        }
        return movimiento;
    }

}
