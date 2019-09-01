package TestJuego;

import juego.Casillero;
import juego.CuatroEnLinea;
import org.junit.Assert;
import org.junit.Test;

public class PruebaCuatroEnLinea {
	
	private String jugadorAzul;
	private String jugadorAmarillo;
	
	public PruebaCuatroEnLinea() {
		jugadorAzul = "Azul";
		jugadorAmarillo = "Amarillo";
	}
	
	@Test 
	public void pruebaQueLosCasillerosInicianVacios(){
		
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, jugadorAzul, jugadorAmarillo);
		
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 2));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 3));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 4));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 2));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 3));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 4));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 2));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 3));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 4));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(4, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(4, 2));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(4, 3));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(4, 4));
		
	}
	
	@Test (expected = Error.class)
	public void pruebaQueSiLaCantidadDeFilasEsMenorA4DevuelveError(){
		CuatroEnLinea juego = new CuatroEnLinea(3, 4, jugadorAzul, jugadorAmarillo);
	}
	
	
	@Test (expected = Error.class)
	public void pruebaQueSiLaCantidadDeColumnasEsMenorA4DevuelveError(){
		CuatroEnLinea juego = new CuatroEnLinea(4, -10, jugadorAzul, jugadorAmarillo);
	}
	
	@Test
	public void pruebaQueCuentaLasFilas(){
		CuatroEnLinea juego = new CuatroEnLinea(5, 5, jugadorAzul, jugadorAmarillo);
		Assert.assertEquals(5, juego.contarFilas());
	}
	
	@Test
	public void pruebaQueCuentaLasColumnas(){
		CuatroEnLinea juego = new CuatroEnLinea(5, 5, jugadorAzul, jugadorAmarillo);
		Assert.assertEquals(5, juego.contarColumnas());
	}
	
	@Test (expected = Error.class)
	public void pruebaQueSiSueltaFichaEnColumnaMenorAUnoDevuelveError(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(0);
	}
	
	@Test (expected = Error.class)
	public void pruebaQueSiSueltaFichaEnColumnaMayorACantidadDeColumnasDevuelveError(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(8);
	}
	
	@Test 
	public void pruebaQueJugadorRojoTiraFichaEnColumna1RespetandoSuTurno(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(1);
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(7, 1));
	}
	
	@Test
	public void pruebaQueJugadorAmarilloTiraFichaEnColumna1RespetandoSuTurno(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(6, 1));
	}
	
	@Test 
	public void pruebaQueHayGanadorSiSeFormaCuatroEnFila(){
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		
		Assert.assertEquals(true, juego.hayGanador());
	}
	
	@Test 
	public void pruebaQueSiNoQuedanCasillerosLibresYNoSeFormo4EnLineaTerminaElJuegoYNoHayGanador(){
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, jugadorAzul, jugadorAmarillo);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		
		Assert.assertEquals(true,juego.termino());
		Assert.assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void pruebaQueGanaJugadorRojoPorFilaHorizontal(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(5);
		juego.soltarFicha(5);
		juego.soltarFicha(6);
		juego.soltarFicha(2);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAzul, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorAmarilloPorFilaHorizontal(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(4);
		juego.soltarFicha(7);
		juego.soltarFicha(5);
		juego.soltarFicha(2);
		juego.soltarFicha(6);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAmarillo, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorRojoPorFilaVertical(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(5);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAzul, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorAmarilloPorFilaVertical(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(5);
		juego.soltarFicha(2);
		juego.soltarFicha(6);
		juego.soltarFicha(2);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAmarillo, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorRojoPorFilaDiagonalDerecha(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(5);
		juego.soltarFicha(4);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAzul, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorAmarilloPorFilaDiagonalDerecha(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAmarillo, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorRojoPorFilaDiagonalIzquierda(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(6);
		juego.soltarFicha(6);
		juego.soltarFicha(6);
		juego.soltarFicha(5);
		juego.soltarFicha(6);
		juego.soltarFicha(5);
		juego.soltarFicha(5);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAzul, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	@Test
	public void pruebaQueGanaJugadorAmarilloPorFilaDiagonalIzquierda(){
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, jugadorAzul, jugadorAmarillo);
		
		juego.soltarFicha(7);
		juego.soltarFicha(7);
		juego.soltarFicha(7);
		juego.soltarFicha(7);
		juego.soltarFicha(6);
		juego.soltarFicha(6);
		juego.soltarFicha(5);
		juego.soltarFicha(6);
		juego.soltarFicha(3);
		juego.soltarFicha(5);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		
		Assert.assertEquals(true, juego.hayGanador());
		Assert.assertEquals(jugadorAmarillo, juego.obtenerGanador());
		Assert.assertEquals(true, juego.termino());
		
	}
	
	
	
	
}
