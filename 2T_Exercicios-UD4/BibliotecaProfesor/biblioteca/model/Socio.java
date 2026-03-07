package biblioteca.model;

/**
* Representa un socio da biblioteca.
*/
public class Socio {
/** Número de socio (DNI sen letra) */
private int numeroSocio;
/** Nome completo */
private String nome="";
/** Dirección postal */
private String direccion="";
/** Email de contacto */
private String email="";

/*
* Construtor por defecto. So se pode utilizar dende dentro do mesmo package
*/
Socio() {   
}


/**
* Construtor completo
*/
public Socio(int numeroSocio, String nome, String direccion, String email) {
    this.numeroSocio = numeroSocio;
    this.nome = nome;
    this.direccion = direccion;
    this.email = email;
}



public int getNumeroSocio() {
return numeroSocio;
}

public String getNome() {
return nome;
}

public String getDireccion() {
return direccion;
}

public String getEmail() {
return email;
}

    /**
     * So será posible poñer/cambiar o número de socio dende o mesmo package
     * @param numeroSocio 
     */
    void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }



/**
* Un Socio se representa como [numero] nome
*/
@Override
public String toString() {
return "["+numeroSocio+"] "+nome;
}

/**
* Dous socios son iguais si teñen o mesmo número de socio
*/
@Override
public boolean equals(Object obj) {
if (obj==null) return false;
if (obj.getClass()!=getClass()) return false;
return ((Socio)obj).numeroSocio==this.numeroSocio;
}
}
