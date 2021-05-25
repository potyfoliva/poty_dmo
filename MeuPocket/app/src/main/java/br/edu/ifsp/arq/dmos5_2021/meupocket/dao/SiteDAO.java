package br.edu.ifsp.arq.dmos5_2021.meupocket.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meupocket.model.Site;

public class SiteDAO {
    private static SiteDAO instance = null;
    private List<Site> siteList;

    public SiteDAO() {
        siteList = new ArrayList<>(9);
        siteList.add(new Site("google", "www.google.com.br"));
        siteList.add(new Site("duckduckgo", "www.duckduckgo.com"));
        siteList.add(new Site("ifsparq", "www.arq.ifsp.edu.br"));
        siteList.add(new Site("youtube", "www.youtube.com"));
        siteList.add(new Site("github", "www.github.com"));
        siteList.add(new Site("w3schools", "www.w3schools.com"));
    }

    public static synchronized SiteDAO getInstance(){
        if(instance == null){
            instance = new SiteDAO();
        }
        return instance;
    }

    public List<Site> getSites(){
        return siteList;
    }

    public void addSite(Site site){
        if(site != null){
            siteList.add(site);
        }
    }

    public Site find(int i){
        return siteList.get(i);
    }

    public Site find(String title){
        for(Site s : siteList){
            if (s.getTitle().equals(title)) {
                return s;
            }
        }
        return null;
    }
}
