package br.edu.ifsp.arq.dmos5_2021.github_dmos5.api;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.github_dmos5.model.RepositorioNome;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("users/{user}/repos")
    Call<List<RepositorioNome>> recuperarRepositorios(@Path("user") String user);
}
