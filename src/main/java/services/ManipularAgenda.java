//package services;
//
//import application.Program;
//import entities.Contato;
//
//public class ManipularAgenda {
//	public static void addContato(String nome, Double ddd, Double numero) {
//		Integer id = Program.getAgenda().getLista().size();
//		Program.getAgenda().getLista().add(new Contato(id, nome, ddd, numero));
//		Serialize.AtualizarAgenda();
//	}
//	public static void removeContato(Contato contato) {
//		Program.getAgenda().getLista().remove(contato);
//		Serialize.AtualizarAgenda();
//	}
//	
//	public static Contato findById(Integer Id) {
//		for(Contato c : Program.getAgenda().getLista()) {
//			if(c.getId().equals(Id)) return c;
//		}
//		return null;
//	}
//	public static Contato findByNumero(String numero) {
//		for(Contato c : Program.getAgenda().getLista()) {
//			if((String.format("%.0f", c.getDdd()) + String.format("%.0f",c.getNumero())).equals(numero)) return c;
//		}
//		return null;
//	}
//	public static Contato findByNome(String nome) {
//		for(Contato c : Program.getAgenda().getLista()) {
//			if(c.getNome().equals(nome)) return c;
//		}
//		return null;
//	}
//}
