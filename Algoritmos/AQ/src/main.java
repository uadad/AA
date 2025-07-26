import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AQ();
	}

	public static class Complex {
		public ArrayList<Selector> conjuncion;

		public Complex() {
			super();
			conjuncion = new ArrayList<>();
		}

		public boolean esMasgeneral(Complex c) {
			for (Selector s : conjuncion) {
				if (!c.conjuncion.contains(s))
					return false;
			}
			return true;
		}
	
		@Override
		public String toString() {
			return "{" + conjuncion.toString() + "}";
		}

		public boolean nocubreNeg(ArrayList<main.Ejemplo> lista) { 
		  for(Ejemplo ej: lista) {
				if(this.cumple(ej)) return false;
			}
			return true;
		}

		public boolean cumple(main.Ejemplo ej) {  // cuando cumple con todos los selectores
			for(Selector s: conjuncion) {
				if(!s.cumple(ej)) return false;
			}
			
			return true;
		}
	}

	public static class Selector {
		private String atributo, operador, valor;

		public Selector(String atributo, String operador, String valor) {
			super();
			this.atributo = atributo;
			this.operador = operador;
			this.valor = valor;
		}

		public boolean cumple(main.Ejemplo ej) {  // coger el operador y en funcion del ejemplo comprobar que cumpla	
			return false;
		}

		@Override
		public String toString() {
			return String.format("(%s %s %s)", atributo, Operadores, valor);
		}

	}

	public static class Ejemplo {
		public String[] valores, columnas;

		public Ejemplo(String[] valores, String[] columnas) {
			super();
			this.valores = valores;
			this.columnas = columnas;
		}
	}

	static String[] Operadores = { "IGUAL"
			// ,MAYORIGUAL
	};

	public static ArrayList<Selector> generateSeed(Ejemplo ej) {
		ArrayList<Selector> Semilla = new ArrayList<>();

		for (int i = 0; i < ej.columnas.length; i++) {
			for (int j = 0; j < Operadores.length; j++) {
				Selector nuevo = new Selector(ej.columnas[i], Operadores[j], ej.valores[i]);
				Semilla.add(nuevo);
			}
		}
		return Semilla;
	}

	public static void AQ() {
		ArrayList<Complex> covering = new ArrayList<>();

		ArrayList<Ejemplo> listaPos = new ArrayList<>(), listaNeg = new ArrayList<Ejemplo>();

		listaPos.add(new Ejemplo(new String[] { "Antenas", "Nucleo", "Cuerpo", "Colas" },
				new String[] { "1", "2", "Rayado", "0" }));

		listaNeg.add(new Ejemplo(new String[] { "Antenas", "Nucleo", "Cuerpo", "Colas" },
				new String[] { "1", "1", "Blanco", "0" }));

		listaNeg.add(new Ejemplo(new String[] { "Antenas", "Nucleo", "Cuerpo", "Colas" },
				new String[] { "1", "1", "Rayado", "1" }));

		while (!listaPos.isEmpty()) {
			Ejemplo p1 = listaPos.get(0);

			ArrayList<Selector> semilla = generateSeed(p1);

			ArrayList<Complex> LComplex = alg_star(semilla, listaNeg);

			// Complex mejor = LEF(LComplex);

			// listaPos = eliminar_cubiertos(listaPos, mejor);

			// covering.add(mejor);
			System.out.println(p1);
		}
	}

	public static ArrayList<Ejemplo> eliminar_cubiertos(ArrayList<Ejemplo> listaPos, Complex mejor) {
		return null;
	}

	public static Complex LEF(ArrayList<Complex> lComplex) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Complex> alg_star(ArrayList<Selector> semilla, ArrayList<Ejemplo> listaNeg) {
		ArrayList<Complex> E = new ArrayList<>();
		ArrayList<Complex> L = new ArrayList<>();
		L.add(new Complex());

		while (!L.isEmpty()) {
			ArrayList<Complex> Ep = merge(L, semilla);
			System.out.println(Ep.toString());
			Ep = eliminarCubiertos(Ep, E);
            for(Complex c: Ep) {
            	if(!c.cubrir(listaNeg))
            }
		}

		return E;
	}

	public static ArrayList<Complex> eliminarCubiertos(ArrayList<Complex> ep, ArrayList<Complex> e) {
		ArrayList<Complex> nuevoEp = new ArrayList<>(ep);
		ArrayList<Complex> temp = new ArrayList<>();
		for (Complex c : ep) {
			for (Complex c2 : e) {
				if (!c2.esMasgeneral(c)) {
					temp.add(c);
					break;
				}
			}
		}
		nuevoEp.removeAll(temp);
		return nuevoEp;
	}

	public static ArrayList<Complex> merge(ArrayList<Complex> l, ArrayList<Selector> semilla) {
		ArrayList<Complex> merged = new ArrayList<>();

		for (Selector s : semilla) {
			for (Complex c : l) {
				if (!c.conjuncion.contains(s)) {
					Complex nuevo = new Complex();
					nuevo.conjuncion.addAll(c.conjuncion);
					nuevo.conjuncion.add(s);
					merged.add(nuevo);
				}
			}
		}
		return merged;

	}
}
