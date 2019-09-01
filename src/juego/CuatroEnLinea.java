package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas: El tablero constan de al menos 4 filas y 4 columnas, se juega de a dos personas las 
 * 		   cuales sueltan fichas de a una por turno sobre las columnas de dicho tablero. 
 *         El Jugador que logre colocar 4 fichas consecutivas del mismo color ya sea de forma 
 *         horizontal, vertical o diagonal es el Ganador. Si no quedan casilleros libres y los
 *         jugadores no lograron formar 4 fichas en línea, entonces se declara empate, por lo 
 *         tanto no habrá Ganador y se termina el Juego....
 *
 */
public class CuatroEnLinea {
	
	private Casillero[][] casilleros; 
	private int filas;
	private int columnas;
	private String jugadorAzul;
	private String jugadorAmarillo;
	private int casillerosLibres;
	private boolean turnoJugadorRojo;
	
	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero está vacío.
	 * 
	 * @param filas : cantidad de filas que tiene el tablero.
	 * @param columnas : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo : nombre del jugador con fichas amarillas.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorAzul, String jugadorAmarillo) {
		if (filas >= 4 && filas < 11 && columnas >= 4 && columnas < 16) {
			casilleros = new Casillero[filas][columnas];
			this.filas = filas;
			this.columnas = columnas;
			turnoJugadorRojo = true;
			this.casillerosLibres = columnas * filas;
			this.jugadorAzul = jugadorAzul;
			this.jugadorAmarillo = jugadorAmarillo;
			
			for (int i = 0; i < casilleros.length; i++) {
				for (int j = 0; j < casilleros[i].length; j++) {
					casilleros[i][j] = Casillero.VACIO;
				}
			}	
		}else{
			throw new Error("Cantidad de filas o columnas no válida");
		}
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		return casilleros.length;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		return casilleros[0].length;
	}
	
	/**
	 * pre : comprueba que fila está en el intervalo [1, contarFilas()],
	 *       sino devuelve Error.
	 * @param filas
	 */
	public void verificadorDeFilas(int filas){
		if (filas < 1  || filas > contarFilas()) {
			throw new Error("Cantidad de filas no válida");
		}else{
			this.filas = filas;
		}
	}
	
	/**
	 * pre : comprueba que columna esta en el intervalo [1, contarColumnas()],
	 *       sino devuelve Error.
	 * @param columnas
	 */
	public void verificadorDeColumnas(int columnas){
		if (columnas < 1 || columnas > contarColumnas()) {
			throw new Error("Cantidad de columnas no válida");
		}else{
			this.columnas = columnas;
		}
	}
	
	/**
	 * pre : fila está en el intervalo [1, contarFilas()],
	 * 		 columnas está en el intervalo [1, contarColumnas()].
	 * post: indica qué ocupa el casillero en la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		verificadorDeFilas(fila);
		verificadorDeColumnas(columna);
		
		return casilleros[fila -1][columna - 1]; 
	}
	
	/**
	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {
		
		verificadorDeColumnas(columna);
		boolean tiroFicha = false;
		for (int i = casilleros.length - 1; (i >= 0) && (!termino()) && (!tiroFicha); i --) {
			if (casilleros[i][columna - 1] == Casillero.VACIO) {
				if (turnoJugadorRojo) {
					casilleros[i][columna - 1] = Casillero.AZUL;
					casillerosLibres --;
				} else {
					casilleros[i][columna - 1] = Casillero.AMARILLO;
					casillerosLibres --;
				}
				turnoJugadorRojo = !turnoJugadorRojo;
				tiroFicha = true;
					
			}
				
		}
			
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores
	 * 		 ganó o no existen casilleros vacíos.
	 */
	public boolean termino() {
		boolean terminoJuego = false;
		if (hayGanador() || casillerosLibres == 0) {
			terminoJuego = true;
		}
		return terminoJuego;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return (haGanadoJugadorRojo() || haGanadoJugadorAmarillo());
	
	}
	
	/**
	 * post : indica si hay cuatro casilleros del mismo color contiguos en forma vertical.
	 * 
	 * @param color : indica el color de la ficha.
	 * @return TRUE si hay 4 fichas del mismo color consecutivas de forma vertical.
	 *         FALSE si no hay 4 fichas del mismo color consecutivas de forma vertical.
	 */
	public boolean cuatroEnLineaVertical(Casillero color){
		boolean cuatroEnLinea = false;
		for(int j = 0 ; j < casilleros[0].length && !cuatroEnLinea;j++){
			int casillerosContiguosEnLinea = 0;
			for(int i = 0; i < casilleros.length && !cuatroEnLinea;i++){
				if(casilleros[i][j] == color){
					casillerosContiguosEnLinea++;
				}else{
					casillerosContiguosEnLinea = 0;
				}
				if(casillerosContiguosEnLinea == 4){
					cuatroEnLinea = true; 
				}	
			}
		}
		
		return cuatroEnLinea;
	}
	
	/**
	 * post : indica si hay cuatro casilleros del mismo color contiguos en forma horizontal.
	 * 
	 * @param color : indica el color de la ficha.
	 * @return TRUE si hay 4 fichas del mismo color consecutivas de forma horizontal.
	 *         FALSE si no hay 4 fichas del mismo color consecutivas de forma horizontal.
	 */
	public boolean cuatroEnLineaHorizontal(Casillero color){
		boolean cuatroEnLinea = false;
		for (int i = 0; i < casilleros.length && !cuatroEnLinea; i++) {
			int casillerosContiguosEnLinea = 0;
			for (int j = 0; j < casilleros[i].length && !cuatroEnLinea; j++) {
				if (casilleros[i][j] == color) {
					casillerosContiguosEnLinea ++;
				} else {
					casillerosContiguosEnLinea = 0;
				}
				if (casillerosContiguosEnLinea == 4) {
					cuatroEnLinea = true;
				}
			}
		}
		return cuatroEnLinea;
	}
	
	/**
	 * post : indica si hay cuatro casilleros del mismo color contiguos en forma diagonal sentido derecha.
	 * 
	 * @param color : indica el color de la ficha.
	 * @return TRUE si hay 4 fichas del mismo color consecutivas de forma diagonal sentido derecha.
	 *         FALSE si no hay 4 fichas del mismo color consecutivas de forma diagonal sentido deracha.
	 */
	public boolean cuatroEnLineaDiagonalDerecha(Casillero color){
		boolean cuatroEnLinea = false;
		
		for(int i = 0 ; i < casilleros.length && !cuatroEnLinea;i++){
			for(int j = 0; j < casilleros[i].length && !cuatroEnLinea;j++){
				int casillerosContiguosEnLinea = 0;
				for(int k = 0; (k < 4) && (k+i < casilleros.length) && (k+j < casilleros[i].length); k++){
					if(casilleros[i+k][j+k] == color){
						casillerosContiguosEnLinea++;
					}else{
						casillerosContiguosEnLinea = 0;
					}
					if(casillerosContiguosEnLinea == 4){
						cuatroEnLinea = true; 
					}
				}	
			}
		}
		
		return cuatroEnLinea;
	}

	/**
	 * post : indica si hay cuatro casilleros del mismo color contiguos en forma diagonal sentido izquierda.
	 * 
	 * @param color : indica el color de la ficha.
	 * @return TRUE si hay 4 fichas del mismo color consecutivas de forma diagonal sentido izquierda.
	 *         FALSE si no hay 4 fichas del mismo color consecutivas de forma diagonal sentido izquierda.
	 */
	public boolean cuatroEnLineaDiagonalIzquierda(Casillero color){
		boolean cuatroEnLinea = false;
		for(int i = 0 ; i < casilleros.length && !cuatroEnLinea;i++){
			for(int j = 0;j < casilleros[i].length && !cuatroEnLinea;j++){
				int casillerosContiguosEnLinea = 0;
				for(int k = 0; (k < 4) && (i+k < casilleros.length) && (j-k >= 0); k++){
					if(casilleros[i+k][j-k] == color){
						casillerosContiguosEnLinea++;
					}else{
						casillerosContiguosEnLinea = 0;
					}
					
					if(casillerosContiguosEnLinea == 4){
						cuatroEnLinea = true; 
					}
				}		
			}
		}
		
		return cuatroEnLinea;
	}
	
	
	/**
	 * post : indica si el jugador rojo ha ganado.
	 * 
	 * @return TRUE si ganó el jugador rojo.
	 *         FALSE si no ganó el jugador rojo.
	 */
	public boolean haGanadoJugadorRojo(){
		return cuatroEnLineaVertical(Casillero.AZUL) || cuatroEnLineaHorizontal(Casillero.AZUL) || 
				cuatroEnLineaDiagonalDerecha(Casillero.AZUL) || cuatroEnLineaDiagonalIzquierda(Casillero.AZUL);
	}
	
	/**
	 * post : indica si el jugador amarillo ha ganado.
	 * 
	 * @return TRUE si ganó el jugador amarillo.
	 *         FALSE si no ganó el jugador amarillo.
	 */
	public boolean haGanadoJugadorAmarillo(){
		return cuatroEnLineaVertical(Casillero.AMARILLO) || cuatroEnLineaHorizontal(Casillero.AMARILLO) ||
				cuatroEnLineaDiagonalDerecha(Casillero.AMARILLO) || cuatroEnLineaDiagonalIzquierda(Casillero.AMARILLO);			
	}
	
	/**
	 * pre : el juego terminó.
	 * post: devuelve el nombre del jugador que ganó el juego.
	 */
	public String obtenerGanador() {
		String JugadorGanador;
		if (haGanadoJugadorRojo()) {
			JugadorGanador = jugadorAzul;
		} else {
			JugadorGanador = jugadorAmarillo;
		}
		return JugadorGanador;
	}
}

