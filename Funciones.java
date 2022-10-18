package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Funciones {
	
	public static void imprimirVector (int [] v){
		
		System.out.print("{");
		for (int e : v){
			System.out.print(e+",");
		}
		System.out.println("}");
		
	}
	
	public static boolean esBisiesto(int anio) {
		
		boolean retornar = false;
		
		if ( (anio % 4 != 0) || (anio % 100 == 0 && anio % 400 != 0) ) {
			retornar = true;
		}
		 return retornar;
	}
	
	public static boolean esFechaValida(LocalDate fecha) {
		
		boolean retornar = true;
		
		if (fecha.getYear() <= 0 ) retornar = false;
		if (fecha.getMonthValue() <= 0 || fecha.getMonthValue() > 12) retornar = false;
		if (fecha.getMonthValue() == 2 && (fecha.getDayOfMonth() < 1 || fecha.getDayOfMonth() > 28)) retornar = false;
		if ((fecha.getMonthValue() == 4 || fecha.getMonthValue() == 6 || fecha.getMonthValue() == 9 || fecha.getMonthValue() == 11) && (fecha.getDayOfMonth() > 30 || fecha.getDayOfMonth() < 1)) retornar = false;
		if ((fecha.getMonthValue() == 1 || fecha.getMonthValue() == 3 || fecha.getMonthValue() == 5 || fecha.getMonthValue() == 7 || fecha.getMonthValue() == 8 || fecha.getMonthValue() == 10 || fecha.getMonthValue() == 12) && (fecha.getDayOfMonth() > 31 || fecha.getDayOfMonth() < 1)) retornar = false;
		if (fecha.getMonthValue() == 2 && fecha.getDayOfMonth() == 29 && esBisiesto(fecha.getYear()) == true) retornar = true;
		
		return retornar;
	}
	
	public static String traerFechaCorta(LocalDate fecha) {
		
		return String.valueOf(fecha.getDayOfMonth()) + "/" + String.valueOf(fecha.getMonthValue()) + "/" + String.valueOf(fecha.getYear());
	}
	
	public static String traerHoraCorta(LocalTime hora) {
		
		return String.valueOf(hora.getHour()) + ":" + String.valueOf(hora.getMinute());
	}
	
	public static boolean esDiaHabil(LocalDate fecha) {
		
		int d = fecha.getDayOfWeek().getValue();
		
		return ((d >= 1) && (d <= 5));
	}
	
	public static String traerDiaDeLaSemana (LocalDate fecha) {
		
		String dia = "";
		
		if (String.valueOf(fecha.getDayOfWeek()) == "MONDAY") dia = "Lunes";
		if (String.valueOf(fecha.getDayOfWeek()) == "TUESDAY") dia = "Martes";
		if (String.valueOf(fecha.getDayOfWeek()) == "WEDNESDAY") dia = "Miercoles";
		if (String.valueOf(fecha.getDayOfWeek()) == "THURSDAY") dia = "Jueves";
		if (String.valueOf(fecha.getDayOfWeek()) == "FRIDAY") dia = "Viernes";
		if (String.valueOf(fecha.getDayOfWeek()) == "SATURDAY") dia = "Sabado";
		if (String.valueOf(fecha.getDayOfWeek()) == "SUNDAY") dia = "Domingo";		
		
		return dia;
	}
	
	public static String traerMesEnLetras (LocalDate fecha) {
		
		String mes = "";
		
		switch (fecha.getMonthValue()) {
		case 1: mes = "Enero"; break;
		case 2: mes = "Febrero"; break;
		case 3: mes = "Marzo"; break;
		case 4: mes = "Abril"; break;
		case 5: mes = "Mayo"; break;
		case 6: mes = "Junio"; break;
		case 7: mes = "Julio"; break;
		case 8: mes = "Agosto"; break;
		case 9: mes = "Septiembre"; break;
		case 10: mes = "Octubre"; break;
		case 11: mes = "Noviembre"; break;
		case 12: mes = "Diciembre"; break;
		}
		return mes;
	}
	
	public static String traerFechaLarga(LocalDate fecha) {
		
		return Funciones.traerDiaDeLaSemana(fecha) + " " + fecha.getDayOfMonth() + " de " + Funciones.traerMesEnLetras(fecha) + " del " + fecha.getYear();
	}
	
	public static int traerCantDiasDeUnMes(int anio, int mes) {
		
		int dias=0;
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			dias =31; 
		}
		
		if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			dias=30;
		}
		
		if (esBisiesto(anio) && mes == 2) {
			dias=29;}
		
		if (esBisiesto(anio)== false && mes == 2) {
			dias=28;
		}
		
		return dias;
	}
	
	public static double aproximar2Decimal(double valor) {
		
		 double valorAux = valor * 1000;
		 
		 if (valorAux % 10 >=5) valorAux = valorAux - valorAux % 10 + 10;
		 else valorAux = valorAux - valorAux % 10;
		 
		 return valorAux / 1000.0;
	 }
	
	public static boolean esNumero(char c) {
		
		return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
	}

	public static boolean esLetra(char c) {
		
		return Character.isLetter(c);
	}
	
	public static boolean esDigito(char c) {
		
		return Character.isDigit(c);
	}

	public static boolean esCadenaNros(String cadena) {
		
		return (cadena.matches("[0-9]+"));
	}

	public static boolean esCadenaLetras(String cadena) {
		
		return (cadena.matches("[a-zA-Z]+"));
	}
	
	public static int convertirCharAInt(char c) {
		
		return Character.getNumericValue(c);
	}
	
	public static int convertirStringAInt(String s) {
		
		return Integer.parseInt(s);
	}

	
	public static double convertirADouble(int n) {
		
		return ((double) n);
	}
	
	public static int calcularEdad(LocalDate nacimiento, LocalDate edadHasta){
										
		Period periodo = Period.between(nacimiento, edadHasta);
	
		return periodo.getYears();
	}
	
	public static boolean estaEntreFechasNoIncluyeExtremos(LocalDate fecha, LocalDate desde, LocalDate hasta) {
		
		return (fecha.isAfter(desde) && fecha.isBefore(hasta) );
	}
	
	public static boolean estaEntreFechasInclusiveExtremos(LocalDate fecha, LocalDate desde, LocalDate hasta) {
		
		return desde.equals(fecha) || Funciones.estaEntreFechasNoIncluyeExtremos(fecha, desde, hasta)  || hasta.equals(fecha);
	}
	
	public static int diasEntreDosFechas(LocalDate desde, LocalDate hasta) {
		
		return (int)ChronoUnit.DAYS.between(desde, hasta); 
	}
	
	//Funciona siempre que horario desde y horario hasta correspondan al mismo dia. Ademas hasta debe ser un horario posterior a desde
	public static double horasPasadas(LocalTime desde, LocalTime hasta) {
		
		return (minutosPasados(desde, hasta)/60);
	}
	
	public static int minutosPasados(LocalTime desde, LocalTime hasta) {
		
		return ((hasta.getHour() - desde.getHour())*60 + hasta.getMinute() - desde.getMinute());
	}
	
}
