package amida.cristhian.coopsapi.model.request;

public enum ZoneType {
	gmt(1), lst(2), lst_ldt(3);

	private final Integer code;

	ZoneType(Integer code) {
		this.code = code;
	}
}
