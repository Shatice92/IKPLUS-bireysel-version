package org.hatice.ikplus.enums;

public enum ExpensesType {
	DRAFT,          // Harcama taslak olarak kaydedildi, henüz gönderilmedi.
	SUBMITTED,      // Harcama onay için yöneticilere gönderildi.
	UNDER_REVIEW,   // Harcama yöneticiler tarafından inceleniyor.
	PENDING_APPROVAL, // Onay süreci bekleniyor.
	APPROVED,       // Harcama yöneticiler tarafından onaylandı.
	REJECTED,       // Harcama reddedildi.
	CANCELLED,      // Harcama iptal edildi.
	PAID           // Harcama ödendi ve kapandı.
}