package modelo;

@SuppressWarnings("serial")
public class SemillaException extends Exception {
	String mensajeDelError;
	
	public SemillaException(String mensajeDelError){
		this.mensajeDelError=mensajeDelError;
	}
	
	public String getMensajeDelError(){
		return mensajeDelError;
	}
	
}
