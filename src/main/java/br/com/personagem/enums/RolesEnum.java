package br.com.personagem.enums;

public enum RolesEnum {
	STUDENT("student"),
	TEACHER("teacher"),
	DIRECTOR("director");
	
	private String role;
	
	private RolesEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	public static RolesEnum get(String role) {
		for (RolesEnum rolesEnum : values()) {
			if(role.equalsIgnoreCase(rolesEnum.getRole())) {
				return rolesEnum;
			}
		}
		throw new IllegalArgumentException(String.format("No exists %s", role));
	}
}
