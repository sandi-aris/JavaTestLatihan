package com.me.vd.main;

public class Kalender {
	public int tanggal;
	public int bulan;
	public int tahun;
	
		
		public Kalender(int tanggal) {
			this(tanggal,1,2000);
//			tanggal = 8;
//			bulan = 1;
//			tahun = 2000;
//			this.tanggal = tanggal;
//			this.bulan = bulan;
//			this.tahun = tahun;
		}
		public Kalender(int tanggal, int bulan) {
			
			tanggal = 1;
			bulan = 6;
			tahun = 2003;
			this.tanggal = tanggal;
			this.bulan = bulan;
//			this.tahun = tahun;
		}
		public Kalender(int tanggal, int bulan, int tahun) {
			tahun = 2004;
			this.tanggal = tanggal;
			this.bulan = bulan;
			this.tahun = tahun;
		}
		
	public int getTanggal() {
		return tanggal;
	}
	public void setTanggal(int tanggal) {
		this.tanggal = tanggal;
	}
	public int getBulan() {
		return bulan;
	}
	public void setBulan(int bulan) {
		this.bulan = bulan;
	}
	public int getTahun() {
		return tahun;
	}
	public void setTahun(int tahun) {
		this.tahun = tahun;
	}

}
