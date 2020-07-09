package com.me.vd.main;

public class TestLatihan2 	{
	class Orang{
		protected String nama;
		protected int umur;
		
		public Orang(String nama) {
			this.nama=nama;
		}
		
		public Orang(String nama,int umur) {
			this.nama=nama;
			this.umur=umur;
		}
	}
	
	class Dosen extends Orang{
		private int nip;
		public Dosen(String nama) {
			super(nama);
		}
		
		public Dosen(String nama,int nip) {
			super(nama);
			this.nip=nip;
		}
		
		public Dosen(String nama,int nip, int umur) {
			super(nama);
			this.nip=nip;
			this.umur=umur;
		}
		
		public void info() {
			System.out.println("Nama = "+nama);
			System.out.println("NIP  = "+nip);
			System.out.println("Umur = "+umur);
		}
	}
	
	public static void main(String args[]) {
		System.out.println("Masukkan Identitas Dosen 1 : Agus");
		Dosen dosen1=new TestLatihan2().new Dosen("Agus");
		
		System.out.println("Masukkan Identitas Dosen 2 : Budi, NIP. 1458");
		Dosen dosen2=new TestLatihan2().new Dosen("Budi",1458);

		System.out.println("Masukkan Identitas Dosen 3 : Iwan, NIP. 1215, umur 47");
		Dosen dosen3=new TestLatihan2().new Dosen("Iwan",1215,47);
		
		System.out.println();
		dosen1.info();
		System.out.println();
		dosen2.info();
		System.out.println();
		dosen3.info();
		
	}


}
