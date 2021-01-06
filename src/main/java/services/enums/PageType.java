package services.enums;

public enum PageType {
	MAIN(1), UPDATE(2);

	private PageType(int i) {
		this.id = i;
	}
	
	private int id;
	
	public int getID(){
		return id;
	}
}
