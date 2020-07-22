package amida.cristhian.coopsapi.model.request;

public enum VelocityType {
	speed_dir(1), Default(2);

	private final Integer code;

	VelocityType(Integer code) {
		this.code = code;
	}
}
