package org.hatice.ikplus.enums;

public enum Type {
	
	ANNUAL_LEAVE("Yıllık izin", "Çalışanların her yıl düzenli olarak kullanabileceği yasal izin."),
	SICK_LEAVE("Hastal" +
			                                                                                                           "ık izni", "Çalışanın sağlık nedeniyle aldığı izin."), MATERNITY_LEAVE("Doğum izni (Kadın)", "Kadın çalışanların doğum öncesi ve sonrası kullanabileceği izin."), PATERNITY_LEAVE("Babalık izni", "Baba olan çalışanların doğum sonrası kullanabileceği izin."), MARRIAGE_LEAVE("Evlilik izni", "Çalışanın evlenmesi durumunda verilen izin."), BEREAVEMENT_LEAVE("Ölüm izni", "Aile bireylerinden birinin vefatı durumunda verilen izin."), COMPENSATORY_LEAVE("Telafi izni", "Çalışanın fazla mesailerine karşılık verilen izin."),
	
	// Özel Durumlar İçin Olan İzinler
	UNPAID_LEAVE("Ücretsiz izin", "Çalışanın maaş almadan kullanabileceği uzun süreli izin."),
	STUDY_LEAVE("Eğitim izni", "Çalışanın mesleki gelişim için aldığı izin."), PUBLIC_HOLIDAY("Resmî tatil", "Ulusal ve resmî bayram günleri."), RELIGIOUS_HOLIDAY("Dini bayram tatili", "Ramazan ve Kurban Bayramı tatilleri."),
	
	// İşe Özel İzinler
	EMERGENCY_LEAVE("Acil durum izni", "Acil durumlar için verilen kısa süreli izin."),
	VOTING_LEAVE("Oy kullanma " +
			                                                                                                     "izni"
			, "Seçim günlerinde çalışanlara verilen izin."),
	MILITARY_LEAVE("Askerlik izni", "Zorunlu askerlik görevi " +
			"için verilen izin."),
	MEDICAL_LEAVE("Tıbbi izin", "Sağlık nedenleriyle uzun süreli tedavi gerektiren " +
			"durumlarda verilen izin."),
	
	// Ekstra veya Şirket Politikalarına Bağlı İzinler
	ADOPTION_LEAVE("Evlat edinme izni", "Evlat edinen ebeveynler için verilen izin."),
	SPECIAL_OCCASION_LEAVE("Özel gün izni", "Çalışanın özel günleri (mezuniyet, çocuğun ilk günü gibi) için verilen izin."),
	QUARANTINE_LEAVE("Karantina izni", "Bulaşıcı hastalık durumlarında verilen izin."),
	WORK_FROM_HOME("Evden çalışma izni", "Fiziksel olarak ofiste olmadan kullanılan izin.");
	private final String displayName;
	private final String description;
	
	Type(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}
	
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getDescription() {
		return description;
	}
}