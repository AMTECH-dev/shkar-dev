package fox;


public interface Pet {
	public enum SEX {MALE, FEMA}

	public String getName();
	public SEX getSex();
	public float getAge();
	public String getColor();
}