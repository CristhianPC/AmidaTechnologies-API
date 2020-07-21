package amida.cristhian.coopsapi.model.request;

public enum UnitType {
	metric(1), english(2);

	private final Integer code;

	UnitType(Integer code) {
		this.code = code;
	}
}
