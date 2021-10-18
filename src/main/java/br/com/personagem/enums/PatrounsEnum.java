package br.com.personagem.enums;

public enum PatrounsEnum {
	STAG("stag"),
	OTTER("otter"),
	FOX("fox");
	
	private String patroun;
	
	private PatrounsEnum(String patroun) {
		this.patroun = patroun;
	}
	
	public String getPatroun() {
		return patroun;
	}
	
	public static PatrounsEnum get(String patroun) {
		for (PatrounsEnum patrounsEnum : values()) {
			if(patroun.equalsIgnoreCase(patrounsEnum.getPatroun())) {
				return patrounsEnum;
			}
		}
		throw new IllegalArgumentException(String.format("No exists %s", patroun));
	}
	
}
