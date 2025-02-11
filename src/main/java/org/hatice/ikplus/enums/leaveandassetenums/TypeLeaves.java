package org.hatice.ikplus.enums.leaveandassetenums;

public enum TypeLeaves {
	
	ANNUAL_LEAVE("Çalışanların her yıl düzenli olarak kullanabileceği yasal izin."),
	SICK_LEAVE("Çalışanın sağlık nedeniyle aldığı izin."),
	MATERNITY_LEAVE("Kadın çalışanların doğum öncesi ve sonrası kullanabileceği izin."),
	PATERNITY_LEAVE( "Baba olan çalışanların doğum sonrası kullanabileceği izin."),
	MARRIAGE_LEAVE( "Çalışanın evlenmesi durumunda verilen izin."),
	BEREAVEMENT_LEAVE( "Aile bireylerinden birinin vefatı durumunda verilen izin."),
	COMPENSATORY_LEAVE( "Çalışanın fazla mesailerine karşılık verilen izin."),
	
	// Özel Durumlar İçin Olan İzinler
	UNPAID_LEAVE("Çalışanın maaş almadan kullanabileceği uzun süreli izin."),
	STUDY_LEAVE("Çalışanın mesleki gelişim için aldığı izin."),
	PUBLIC_HOLIDAY("Ulusal ve resmî bayram günleri."),
	RELIGIOUS_HOLIDAY( "Ramazan ve Kurban Bayramı tatilleri."),
	
	// İşe Özel İzinler
	EMERGENCY_LEAVE("Acil durumlar için verilen kısa süreli izin."),
	VOTING_LEAVE("Seçim günlerinde çalışanlara verilen izin."),
	MILITARY_LEAVE("Zorunlu askerlik görevi  için verilen izin."),
	MEDICAL_LEAVE("Sağlık nedenleriyle uzun süreli tedavi gerektiren durumlarda verilen izin."),
	
	// Ekstra veya Şirket Politikalarına Bağlı İzinler
	ADOPTION_LEAVE("Evlat edinen ebeveynler için verilen izin."),
	SPECIAL_OCCASION_LEAVE("Çalışanın özel günleri mezuniyet, çocuğun ilk günü  gibi için verilen izin."),
	QUARANTINE_LEAVE("Bulaşıcı hastalık durumlarında verilen izin."),
	WORK_FROM_HOME("Fiziksel olarak ofiste olmadan " + "kullanılan izin.");
	
	private final String description;
	
	TypeLeaves(String description) {
		
		this.description = description;
	}
	
	
	public String getDescription() {
		return description;
	}
}