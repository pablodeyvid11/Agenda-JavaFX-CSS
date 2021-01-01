import entities.dao.ContatoDAO;

public class test {
	public static void main(String[] args) {
		ContatoDAO dao = new ContatoDAO();
		System.out.println(dao.deleteById(1).toString());
	}
}
