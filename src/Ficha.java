
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rolando mamani salas
 */
public class Ficha {

    private int coordenadaX;
    private int cordenadaY;
    private int valor;

    public Ficha(int x, int y, int valor) {
        this.coordenadaX = x;
        this.cordenadaY = y;
        this.valor = valor;
    }

    public String toString() {
        return "Pos:" + coordenadaX + "," + cordenadaY + " Valor:" + valor;
    }

    public String toStringPosicion() {
        return "fila " + coordenadaX + ", columna " + cordenadaY;
    }

    public void setValor(int v) {
        valor = v;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ficha)) {
            return false;
        }
        Ficha p = (Ficha) o;
        return (p.getValor() == this.getValor() && p.getCoordenadaX() == this.getCoordenadaX() && p.getCoordenadaY() == this.getCoordenadaY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenadaX, cordenadaY, valor);
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return cordenadaY;
    }

    public int getValor() {
        return valor;
    }

}
