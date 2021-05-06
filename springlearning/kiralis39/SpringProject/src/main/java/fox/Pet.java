package fox;


public interface Pet {
	public enum SEX {MALE, FEMA}

	public String getName();
	public void setName(String value);
	
	public SEX getSex();
	public void setSex(SEX value);
	
	public float getAge();
	public void setAge(float value);
	
	public String getColor();
	public void setColor(String value);
	
	public boolean isHealed();
	public void setHealed(boolean isHealed);
	
	public int getHP();
	public void setHP(int hp);
}