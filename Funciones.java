package funciones;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Funciones {
	
	
	public static boolean esBisiesto(int anio) {
		return (anio%4==0 && !(anio%100==0 && anio%400!=0));
	}
	
	public static boolean esFechaValida(LocalDate fecha) {
		boolean ok=true;
		if (fecha.getDayOfMonth()==29 && fecha.getMonthValue()==2) {
			ok=esBisiesto(fecha.getYear());
		}
		return ok;
	}
	
	public static String traerFechaCorta(LocalDate fecha) {
		String fechaCorta=String.valueOf(fecha.getDayOfMonth())+"/"+String.valueOf(fecha.getMonthValue())+"/"+String.valueOf(fecha.getYear());
//		System.out.println(fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear());
		return fechaCorta;
	}

	public static String traerHoraCorta(LocalTime fecha) {
		String fechaCorta=String.valueOf(fecha.getHour())+":"+String.valueOf(fecha.getMinute());
//		System.out.println(fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear());
		return fechaCorta;
	}
	
	public static boolean esDiaHabil(LocalDate fecha){
		int d= fecha.getDayOfWeek().getValue();
		return ((d>=1) && (d<=5));
	}
	
	public static String traerDiaSemana(LocalDate fecha){
		return String.valueOf(fecha.getDayOfWeek());
	}

	public static String traerMesLetras(LocalDate fecha){
		return String.valueOf(fecha.getMonth());
	}
	
	public static String traerFechaLarga(LocalDate fecha){
		int dia=fecha.getDayOfMonth();
		int anio=fecha.getYear();
		
		return traerDiaSemana(fecha)+" "+dia+" de "+ traerMesLetras(fecha)+" del "+anio;
	}
	
	public static int cantDiasMes(int anio, int mes) {
		int cant=31;
		if(mes==4 || mes==6 || mes==9 || mes==11) {
			cant--;
		}else if(mes==2){
			cant=28;
			if(esBisiesto(anio)) {
				cant++;
			}
		}
		return cant;
	}
	
	public static double aproximar2Decimal(double valor) {
		double nuevo=((valor*100)%1)*10;//2.665 266.5 0.5 5
		if(nuevo>=5) {
			nuevo=1;
		}else {
			nuevo=0;
		}
		nuevo=(valor*100-(valor*100)%1+nuevo)/100;
		return nuevo;
	}
	
	public static boolean esNumero(char c) {
		boolean ok=false;
		char[] array= {'0','1','2','3','4','5','6','7','8','9'};
		for(int i=0;i<=9;i++) {
			if(c==array[i]) {
				ok= true;
			}
		}
		return ok;
	}
	
	public static boolean esLetra(char c) {
		boolean ok=false;
		char[] array= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0;i<array.length;i++) {
			if(c==array[i]) {
				ok= true;
			}
		}
		return ok;
	}
	
	public static boolean esCadenaNumero(String c) {
		boolean ok=true;
		for(int i=0;i<c.length();i++) {
			if(!esNumero(c.charAt(i))) {
				ok=false;
				i=c.length();
			}
		}
		return ok;
	}
	
	public static boolean esCadenaLetra(String c) {
		boolean ok=true;
		for(int i=0;i<c.length();i++) {
			if(!esLetra(c.charAt(i))) {
				ok=false;
				i=c.length();
			}
		}
		return ok;
	}
	
	public static int convertirStringAInt(String s) {
		return Integer.parseInt(s);
	}

	public static int convertirCharAInt(char c) {
		return Character.getNumericValue(c);
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