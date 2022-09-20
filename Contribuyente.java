package afip;
import java.util.Arrays;

public class Contribuyente {
	
	private int idC;
	private String apellido;
	private String nombre;
	private long dni;
	private char sexo;
	private String cuil;
	
	public Contribuyente(int idC, String apellido, String nombre, long dni, char sexo, String cuil) throws Exception {
		setIdC(idC);
		setApellido(apellido);
		setNombre(nombre);
		setDni(dni);
		setSexo(sexo);
		setCuil(cuil);
	}

	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) throws Exception {
		if(validarSexo(sexo)) {
			this.sexo = sexo;
		}else {
			throw new Exception("ERROR: Sexo invalido");
		}
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) throws Exception {
		if(validarCuil(cuil)) {
			this.cuil = cuil;
		}else {
			throw new Exception("ERROR: CUIL invalido");
		}
	}
	
	public boolean validarSexo(char sexo) {
		return (sexo=='h' || sexo=='m');
	}
	
	public boolean validarCuil(String c) {
		boolean ok=false;
		if(c.contains(crearCuil())) {
			ok=true;
		}
		return ok;
	}
	
	public String crearCuil() {
		String cuilString="";
		long [] nM= {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
		long [] nD= {2, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long [] dniAux= {2, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long resultado=0;
		long acum=0;
		for (int i=2;i<10;i++) {
			nD[i]=(long)((dni/(Math.pow(10,nM.length-i-1))/*-acum*//*4,xx 42, 426,-nD[i]*Math.pow(10, i-2))*/));
			nD[i]=nD[i]-acum*10;
			if(i!=2) {
				acum=acum*10+nD[i];
			}else {
				acum=acum+nD[i];
			}
			dniAux[i]=nD[i];
		}
		
		if (sexo=='m') {
			nD[1]=7;
			dniAux[1]=7;
		}
		
		for (int i=0;i<10;i++) {
			nD[i]=nD[i]*nM[i];
			resultado += nD[i];
		}
		
		if(resultado%11==1) {
			if (nD[1]==7) {
				resultado=4;
			}else {
				resultado=9;
			}
			nD[1]=3;
			dniAux[1]=3;
		}else if(resultado%11!=0){
			resultado=11-resultado%11;
		}
		
		for(int i=0;i<10;i++) {
			cuilString=cuilString+String.valueOf(dniAux[i]);
		}
		cuilString=cuilString+String.valueOf(resultado);
		return cuilString;
	}

	@Override
	public String toString() {
		return "Contribuyente [idC=" + idC + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni
				+ ", sexo=" + sexo + ", cuil=" + cuil + "\ncrearCuil()=" + crearCuil() + "]";
	}
	
	
}
