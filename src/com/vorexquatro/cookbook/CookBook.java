package com.vorexquatro.cookbook;

import com.vorexquatro.cookbook.controller.Catalogo;
import com.vorexquatro.cookbook.domain.Receita;
import com.vorexquatro.cookbook.enums.Categoria;
import com.vorexquatro.cookbook.view.CatalogoView;

public class CookBook {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.add(new Receita("Cookies da Lara 1", Categoria.DOCE));
        catalogo.add(new Receita("Cookies da Lara 2", Categoria.DOCE));
        catalogo.add(new Receita("Cookies da Lara 3", Categoria.DOCE));
        catalogo.add(new Receita("Cookies da Lara 4", Categoria.DOCE));
        CatalogoView view = new CatalogoView(catalogo);
        view.view();
    }
}
