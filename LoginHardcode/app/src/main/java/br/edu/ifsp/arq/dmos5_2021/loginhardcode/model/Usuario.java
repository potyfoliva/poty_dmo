package br.edu.ifsp.arq.dmos5_2021.loginhardcode.model;

public class Usuario {
    private String userName;
    private int password;

    public Usuario(String userName, int password) {
        if(userName != null && !userName.isEmpty()){
            this.userName = userName;
            this.password = password;
        }else{
            this.userName = "default";
            this.password = 0;
        }
    }

    public String getUserName() {
        return userName;
    }

    public boolean validate(String userName, int password){
        if(userName != null){
            return this.userName.equalsIgnoreCase(userName) && password == password;
        }
        return false;
    }
}
