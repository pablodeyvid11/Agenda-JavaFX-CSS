//package services;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//import application.Program;
//import entities.Agenda;
//
//public class Serialize {
//
//	private static ObjectInputStream obj;
//	public static Agenda CarregarAgenda() {
//		isExist();
//		try {
//			FileInputStream in = new FileInputStream(Program.getFileAgenda());
//			if(in.available() > 0) {
//				obj = new ObjectInputStream(in);
//				return (Agenda) obj.readObject();
//			} else {
//				return Agenda.CriarAgenda();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static void AtualizarAgenda() {
//        isExist();
//		try {
//			FileOutputStream out = new FileOutputStream(Program.getFileAgenda());
//			ObjectOutputStream objOout = new ObjectOutputStream(out);
//		
//			objOout.writeObject(Program.getAgenda());
//			objOout.close();
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (IOException io) {
//			io.printStackTrace();
//		}
//	}
//	private static void isExist(){
//		File f = Program.getFileAgenda();
//		if (!f.exists()) {
//			if (f.getParentFile() != null) {
//				if (!f.getParentFile().exists()) {
//					f.getParentFile().mkdirs();
//				}
//			}
//			try {
//				f.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} 
//	}
//}
