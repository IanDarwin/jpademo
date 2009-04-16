package domain;

public enum PrefType {
	SPAM_ME("T"),
	HELP_ME("T");
	
	private String value;

	public String getValue() {
		return value;
	}

	private PrefType(String value) {
		this.value = value;
	}
}
