package modelo;

@SuppressWarnings("serial")
public class ConsignaException extends Exception {
	String mensajeDelError;

	public ConsignaException(String mensajeDelError) {
		this.mensajeDelError = mensajeDelError;
	}

	public String getMensajeDelError() {
		return mensajeDelError;
	}

}
