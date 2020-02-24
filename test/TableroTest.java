
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roland
 */
public class TableroTest {
    @Test
    public void creacionDelTableroTest() {
        Tablero tablero = new Tablero(15);
        String tableroEsperado = " 1  2  3  4 \n 5  6  7  8 \n 9 10 11 12\n 13 14 15  ";
        String tableroObtenido = tablero.toString();
        assertEquals(tableroEsperado, tableroObtenido);

    }

    @Test
    public void verificarMatrizPerfectaTest() {
        Tablero tablero = new Tablero(15);
        boolean perfecta = true;
        boolean obtenida = tablero.verificarMatrizPerfecta(25);
        assertEquals(perfecta, obtenida);
    }

    @Test
    public void verificarMatrizNoPerfectaTest() {
        Tablero tablero = new Tablero(15);
        boolean perfecta = false;
        boolean obtenida = tablero.verificarMatrizPerfecta(24);
        assertEquals(perfecta, obtenida);
    }

    @Test
    public void movimientoArribaTest() {
        Tablero tablero = new Tablero(15);
        String esperado = "Arriba";
        String obtenido = tablero.direccionString(0);
        assertEquals(esperado, obtenido);
    }
    @Test
    public void movimientoIzquierdaTest() {
        Tablero tablero = new Tablero(15);
        String esperado = "Izquierda";
        String obtenido = tablero.direccionString(1);
        assertEquals(esperado, obtenido);
    }
    @Test
    public void movimientoDerechaTest() {
        Tablero tablero = new Tablero(15);
        String esperado = "Derecha";
        String obtenido = tablero.direccionString(2);
        assertEquals(esperado, obtenido);
    }
    @Test
    public void movimientoAbajoTest() {
        Tablero tablero = new Tablero(15);
        String esperado = "Abajo";
        String obtenido = tablero.direccionString(3);
        assertEquals(esperado, obtenido);
    }
}
